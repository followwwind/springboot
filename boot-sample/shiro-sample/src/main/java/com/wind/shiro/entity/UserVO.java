package com.wind.shiro.entity;

import com.wind.webapi.persistence.BaseVO;

/**
 * @Title: UserVO
 * @Package com.wind.shiro.entity
 * @Description: userVO
 * @author huanghy
 * @date 2018/10/18 13:37
 * @version V1.0
 */
public class UserVO extends BaseVO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户uuid
     */
    private String userId;

    /**
     * 手机号
     */
    private String username;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 删除标记，0表示未删除，1表示删除
     */
    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
