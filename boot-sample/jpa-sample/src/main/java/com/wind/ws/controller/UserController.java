package com.wind.ws.controller;

import com.wind.ws.entity.po.UserInfo;
import com.wind.ws.entity.query.UserSaveQ;
import com.wind.ws.service.UserInfoService;
import com.wind.webapi.constants.HttpCode;
import com.wind.webapi.message.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: UserController
 * @Package com.wind.sample.controller
 * @Description: TODO
 * @author wind
 * @date 2018/9/21 14:54
 * @version V1.0
 */
@RestController
@RequestMapping(value = "userInfo")
@Api(value="userInfo")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @PostMapping(value = "userInfo/list")
    public List<UserInfo> list(UserInfo userInfo){
        return userInfoService.findList(userInfo);
    }


    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
    @GetMapping(value = "userInfo/{id}")
    public UserInfo findById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

    @PostMapping(value = "userInfo/add")
    public JsonResult save(@RequestBody UserSaveQ userSaveQ){
        userInfoService.add(userSaveQ);
        return HttpCode.OK.getJsonResult();
    }

}
