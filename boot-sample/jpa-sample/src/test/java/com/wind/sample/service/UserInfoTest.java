package com.wind.sample.service;

import com.wind.common.util.IdGenUtil;
import com.wind.sample.JpaRunner;
import com.wind.sample.entity.po.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoTest {

    @Autowired
    private UserInfoService userInfoService;


    @Test
    public void save(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(IdGenUtil.getUUID());
        userInfo.setUsername("follow");
        userInfo.setPassword("123456");

        userInfoService.save(userInfo);
    }

    @Test
    public void findById(){
        UserInfo userInfo= userInfoService.findById(1L);
        if(userInfo != null){
            System.out.println(userInfo.getUsername());
        }

    }

    @Test
    public void findList(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("wind");
        List<UserInfo> list = userInfoService.findList(userInfo);
        System.out.println(list.size());
    }

    @Test
    public void update(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setPassword("password");
        userInfoService.update(userInfo);
    }
}
