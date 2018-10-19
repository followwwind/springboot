package com.wind.ws.entity.po;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: UserInfo
 * @Package com.wind.sample.entity
 * @Description: TODO
 * @author huanghy
 * @date 2018/9/14 14:48
 * @version V1.0
 */
@Entity
@Table(name="user_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", columnDefinition = "varchar(32) comment 'userId'", unique = true)
    private String userId;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "varchar(64) comment '密码'")
    private String password;

    private Integer age;

    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
