package com.wind.shiroapi.session;

import com.google.common.collect.Sets;
import com.wind.webapi.util.ServletUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisSessionDAO
 * @Package com.wind.shiroapi.session
 * @Description: 自定义Redis授权会话管理类
 * @author wind
 * @date 2018/10/17 13:11
 * @version V1.0
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String sessionKeyPrefix = "shiro_session:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 毫秒换算秒
     */
    private static final int MILLISECONDS_IN_A_SECOND = 1000;

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }

        HttpServletRequest request = ServletUtil.getRequest();
        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不更新SESSION
            if (ServletUtil.isStaticFile(uri)){
                return;
            }
        }

        try {
            redisTemplate.opsForValue().set(sessionKeyPrefix + session.getId(), session);

            // 设置超期时间
            int timeoutSeconds = (int)(session.getTimeout() / MILLISECONDS_IN_A_SECOND);
            redisTemplate.expire((sessionKeyPrefix + session.getId()), timeoutSeconds, TimeUnit.SECONDS);

            logger.debug("update session. sessionId is {}, uri is {}", session.getId(), request != null ? request.getRequestURI() : "");
        } catch (Exception e) {
            logger.error("update session failed. sessionId is {}, uri is {}", session.getId(), request != null ? request.getRequestURI() : "", e);
        }
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        try {
            redisTemplate.delete(sessionKeyPrefix + session.getId());

            logger.debug("delete session. sessionId is {}", session.getId());
        } catch (Exception e) {
            logger.error("delete session failed. sessionId is {}", session.getId(), e);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = Sets.newHashSet();

        try {
            Set<String> keys = redisTemplate.keys(sessionKeyPrefix + "*");

            if(keys == null){
                return sessions;
            }

            for (String key : keys) {
                sessions.add((Session)redisTemplate.opsForValue().get(key));
            }

            logger.info("getActiveSessions size: {} ", sessions.size());
        } catch (Exception e) {
            logger.error("getActiveSessions failed.", e);
        }

        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        HttpServletRequest request = ServletUtil.getRequest();

        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不创建SESSION
            if (ServletUtil.isStaticFile(uri)){
                return null;
            }
        }

        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.update(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;

        HttpServletRequest request = ServletUtil.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            // 如果是静态文件，则不获取SESSION
            if (ServletUtil.isStaticFile(uri)) {
                return null;
            }

            session = (Session)request.getAttribute("session_"+sessionId);
        }

        if (session != null){
            return session;
        }

        try {
            session = (Session) redisTemplate.opsForValue().get(sessionKeyPrefix + sessionId);

            logger.debug("doReadSession sessionId is {}, uri is {}", sessionId, request != null ? request.getRequestURI() : "");
        } catch (Exception e) {
            logger.error("doReadSession failed. sessionId is {}, uri is {}", sessionId, request != null ? request.getRequestURI() : "", e);
        }

        if (request != null && session != null){
            request.setAttribute("session_" + sessionId, session);
        }

        return session;
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        try{
            return super.readSession(sessionId);
        }catch (UnknownSessionException e) {
            return null;
        }
    }

    public String getSessionKeyPrefix() {
        return sessionKeyPrefix;
    }

    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }

}
