package com.wind.shiro.security.token.impl;

import com.wind.common.util.JwtUtil;
import com.wind.common.util.StringUtil;
import com.wind.shiro.config.JwtConfig;
import com.wind.shiro.security.token.AbstractTokenManager;
import com.wind.shiro.security.token.StatelessAuthenticationToken;
import com.wind.shiro.security.token.TokenVerifyResult;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: SimpleJwtTokenManagerImpl
 * @Package com.wind.shiro.security.token.impl
 * @Description: 简单的JwtTokenManager，服务端生成token发送给客户端 ，客户端自己保存token
 * @author wind
 * @date 2018/10/17 9:38
 * @version V1.0
 */
public class SimpleJwtTokenManagerImpl extends AbstractTokenManager {


    public SimpleJwtTokenManagerImpl() {

    }

    @Override
    public String createToken(String userId, String compId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("compId", compId);
        return JwtUtil.createJWT(System.currentTimeMillis(), userId, map, JwtConfig.ISSUER, JwtConfig.SECRET);
    }


    @Override
    public StatelessAuthenticationToken create(String userId, String compId) {
        String token = createToken(userId, compId);
        //TODO redis缓存
        return new StatelessAuthenticationToken(userId, compId, token);
    }

    @Override
    public TokenVerifyResult verify(StatelessAuthenticationToken token) {
        String userId = token.getUserId();
        return "111111".equals(userId) ? TokenVerifyResult.PASSED : TokenVerifyResult.TOKEN_MISMATCH;
    }

    @Override
    public StatelessAuthenticationToken get(String authentication) {
        if (StringUtil.isBlank(authentication)) {
            return null;
        }
        try {
            Claims claims = JwtUtil.parseJWT(authentication, JwtConfig.ISSUER, JwtConfig.SECRET);
            String subject = claims.getSubject();
            String compId = claims.get("compId") == null ? null : claims.get("compId").toString();
            return new StatelessAuthenticationToken(subject, compId, authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }





        return null;
    }

    @Override
    public void delete(String userId) {

    }

    @Override
    public void update(StatelessAuthenticationToken token) {

    }
}
