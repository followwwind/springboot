package com.wind.shiro.entity;

import com.wind.webapi.persistence.BaseQuery;

/**
 * @Title: LoginQ
 * @Package com.wind.shiro.entity
 * @Description: 登录
 * @author wind
 * @date 2018/10/17 15:20
 * @version V1.0
 */
public class LoginQ extends BaseQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆类型(1:密码，2：验证码)
     */
    private Integer loginType;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
