package com.wind.webapi.handler;

import com.wind.webapi.constants.HttpCode;
import com.wind.webapi.exception.BusinessException;
import com.wind.webapi.exception.IllegalParameterException;
import com.wind.webapi.message.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.login.LoginException;

/**
 * @Title: GlobalControllerExceptionHandler
 * @Package com.wind.webapi.handler
 * @Description: 统一异常处理类
 * @author wind
 * @date 2018/9/17 17:39
 * @version V1.0
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * 非法的请求参数异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { IllegalParameterException.class })
    public JsonResult illegalParameterException(IllegalParameterException ex) {
        logger.error("illegal parameter", ex);
        return new JsonResult(ex.getHttpCode(), null, ex.getMessage());
    }

    /**
     * 登陆异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { LoginException.class })
    public JsonResult loginException(LoginException ex) {
        logger.error("login fail", ex);
        return new JsonResult(HttpCode.USER_LOGIN_FAIL,null, ex.getMessage());
    }

    /**
     * 业务异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { BusinessException.class })
    public JsonResult businessException(BusinessException ex) {
        logger.error(ex.getHttpCode().getMsg(), ex);
        return new JsonResult(ex.getHttpCode(), null, ex.getMessage());
    }


    /**
     * 404处理
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.warn(HttpCode.NOT_FOUND.getMsg(), ex);
        return new JsonResult(HttpCode.NOT_FOUND, null, ex.getMessage());
    }

    /**
     * 全局异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { Exception.class })
    public JsonResult unknownException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new JsonResult(HttpCode.INTERNAL_SERVER_ERROR, null, HttpCode.INTERNAL_SERVER_ERROR.getMsg());
    }

}