package com.wind.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloController
 * @Package com.wind.shiro.controller
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 15:19
 * @version V1.0
 */
@RestController
@RequestMapping("api/hello")
public class HelloController {

    @RequestMapping("sayHello")
    public String sayHello(){
        return "hello";
    }

    @RequiresPermissions("user")
    @RequestMapping("sayHi")
    public String sayHi(){
        return "hi";
    }

    @RequiresPermissions("world")
    @RequestMapping("sayWorld")
    public String sayWorld(){
        return "world";
    }
}
