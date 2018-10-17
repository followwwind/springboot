package com.wind.shiroapi.subject;

import java.io.Serializable;

/**
 * @Title: Principal
 * @Package com.wind.shiroapi.subject
 * @Description: 授权用户信息
 * @author wind
 * @date 2018/10/17 10:22
 * @version V1.0
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户uuid */
    private String id;

    /** 用户账号 */
    private String username;

    private String compId;

    /** 登陆类型（1：密码；2：验证码） */
    private Integer loginType;

    public Principal() {
    }

    public Principal(String id, String username, String compId, Integer loginType) {
        this.id = id;
        this.username = username;
        this.compId = compId;
        this.loginType = loginType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
}
