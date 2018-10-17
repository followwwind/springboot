package com.wind.jpa.entity.query;

import com.wind.webapi.persistence.BaseQuery;

import javax.validation.constraints.NotBlank;

/**
 * @Title: UserSaveQ
 * @Package com.wind.sample.entity.query
 * @Description: 用户信息新增
 * @author wind
 * @date 2018/9/21 15:37
 * @version V1.0
 */
public class UserSaveQ extends BaseQuery {

    private String userId;

    @NotBlank(message = "username不能为空")
    private String username;

    private String password;

    private Integer age;

    private Integer type;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
