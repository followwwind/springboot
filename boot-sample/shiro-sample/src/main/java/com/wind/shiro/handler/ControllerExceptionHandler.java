package com.wind.shiro.handler;

import com.wind.webapi.constants.HttpCode;
import com.wind.webapi.message.JsonResult;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: ControllerExceptionHandler
 * @Package com.wind.shiro.handler
 * @Description: 自定义统一异常处理类
 * @author wind
 * @date 2018/10/19 8:48
 * @version V1.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * shiro权限异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { UnauthorizedException.class })
    public JsonResult unauthorizedException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new JsonResult(HttpCode.UNAUTHORIZED, null, ex.getMessage());
    }

}
