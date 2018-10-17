package com.wind.shiroapi.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Title: StatelessAuthenticationToken
 * @Package com.wind.shiroapi.token
 * @Description: 无状态认证令牌
 * @author wind
 * @date 2018/10/17 9:28
 * @version V1.0
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String userId;

    /** 组织id */
    private String compId;

    /** json web token */
    private String token;

    public StatelessAuthenticationToken() {
    }

    public StatelessAuthenticationToken(String userId, String compId, String token) {
        this.userId = userId;
        this.compId = compId;
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return getUserId();
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
