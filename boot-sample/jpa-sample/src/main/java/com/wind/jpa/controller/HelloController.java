package com.wind.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloController
 * @Package com.wind.sample.controller
 * @Description: TODO
 * @author huanghy
 * @date 2018/9/14 15:13
 * @version V1.0
 */
@RestController
public class HelloController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String say(){
        return "Hello, Spring Boot";
    }
}
