package com.wind.shiro.security.util;

import com.wind.shiro.security.subject.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: SecurityUtil
 * @Package com.wind.shiro.security.util
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 14:05
 * @version V1.0
 */
public class SecurityUtil {

    private static Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private static final String USER_CACHE = "user:";
    private static final String USER_CACHE_INFO = "userInfo";

    private static final String CACHE_AUTH_INFO = "authInfo:";
    private static final String CACHE_ROLE_LIST = "roleList:";
    private static final String CACHE_RESOURCE_LIST = "resourceList:";


    public static AuthorizationInfo getAuthInfo(String userId, String compId) {
        return (AuthorizationInfo) CacheUtil.get (USER_CACHE + userId, CACHE_AUTH_INFO + compId);
    }

    /**
     * 设置授权信息
     * @param userId
     * @param compId
     * @param authorizationInfo
     */
    public static void putAuthInfo(String userId, String compId, AuthorizationInfo authorizationInfo) {
        CacheUtil.put(USER_CACHE + userId, CACHE_AUTH_INFO + compId, authorizationInfo);
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal(){
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal)subject.getPrincipal();

            if (principal != null){
                return principal;
            }
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e){

        }
        return new Principal();
    }

    /**
     * 清除当前用户所有缓存
     */
    public static void clearAllCache(String userId){
        CacheUtil.removeAll(USER_CACHE + userId);
    }

    /**
     * 清除指定用户缓存
     * @param userId
     */
    public static void clearUserCache(String userId){
        CacheUtil.remove(USER_CACHE + userId, USER_CACHE_INFO);
    }
}
