package com.wind.shiroapi.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Title: FormAuthenticationToken
 * @Package com.wind.shiroapi.token
 * @Description: 用户自定义表单
 * @author wind
 * @date 2018/10/17 15:40
 * @version V1.0
 */
public class FormAuthenticationToken implements AuthenticationToken {

    /**
     * 登陆类型（1：密码登陆， 2：验证码登陆）
     */
    private Integer loginType;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登陆凭证
     */
    private char[] credentials;

    public FormAuthenticationToken(Integer loginType, String username, char[] credentials) {
        this.loginType = loginType;
        this.username = username;
        this.credentials = credentials;
    }

    public FormAuthenticationToken(final int loginType,final String username, final String credentials) {
        this(loginType, username, credentials != null ? credentials.toCharArray() : null);
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    /**
     * 清除
     */
    public void clear() {
        this.username = null;

        if (this.credentials != null) {
            for (int i = 0; i < credentials.length; i++) {
                this.credentials[i] = 0x00;
            }
            this.credentials = null;
        }

    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCredentials(char[] credentials) {
        this.credentials = credentials;
    }
}
