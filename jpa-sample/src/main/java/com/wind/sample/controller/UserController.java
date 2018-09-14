package com.wind.sample.controller;

import com.wind.sample.entity.UserInfo;
import com.wind.sample.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "userInfo")
@Api(value="userInfo")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @PostMapping(value = "getUserInfos")
    public List<UserInfo> getUserInfos(UserInfo userInfo){
        return userInfoService.selectByCondition(userInfo);
    }


    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
    @GetMapping(value = "getUserInfo/{id}")
    public UserInfo getUserInfo(@PathVariable Long id){
        return userInfoService.selectByPrimaryKey(id);
    }

}
