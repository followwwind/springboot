package com.wind.shiroapi.token.impl;

import com.wind.common.util.JwtUtil;
import com.wind.common.util.StringUtil;
import com.wind.shiroapi.token.AbstractTokenManager;
import com.wind.shiroapi.token.StatelessAuthenticationToken;
import com.wind.shiroapi.token.TokenVerifyResult;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: SimpleJwtTokenManagerImpl
 * @Package com.wind.shiroapi.token.impl
 * @Description: 简单的JwtTokenManager，服务端生成token发送给客户端 ，客户端自己保存token
 * @author wind
 * @date 2018/10/17 9:38
 * @version V1.0
 */
public class SimpleJwtTokenManagerImpl extends AbstractTokenManager {

    private String issuer;

    private String secret;

    public SimpleJwtTokenManagerImpl(String issuer, String secret) {
        this.issuer = issuer;
        this.secret = secret;
    }

    @Override
    public String createToken(String userId, String compId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("compId", compId);
        return JwtUtil.createJWT(System.currentTimeMillis(), userId, map, issuer, secret);
    }


    @Override
    public StatelessAuthenticationToken create(String userId, String compId) {
        return null;
    }

    @Override
    public TokenVerifyResult verify(StatelessAuthenticationToken token) {
        return null;
    }

    @Override
    public StatelessAuthenticationToken get(String authentication) {
        if (StringUtil.isBlank(authentication)) {
            return null;
        }

        try {
            Claims claims = JwtUtil.parseJWT(authentication, issuer, secret);

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
