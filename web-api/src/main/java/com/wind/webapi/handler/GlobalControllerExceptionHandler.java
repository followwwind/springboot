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
 * 
 * @fileName GlobalControllerExceptionHandler.java
 * @package com.ancda.palmbaby.ancda.common.handler
 * @description 统一异常处理类
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:17:42
 * @version V1.0
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * 
     * </p>非法的请求参数异常处理</p>
     * @param ex
     * @return
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    @ExceptionHandler(value = { IllegalParameterException.class })
    public JsonResult illegalParameterException(IllegalParameterException ex) {
        logger.error("illegal parameter", ex);
        return new JsonResult(ex.getHttpCode(), null, ex.getMessage());
    }
    
    /**
     * 
     * </p>登陆异常处理</p>
     * @param ex
     * @return
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    @ExceptionHandler(value = { LoginException.class })
    public JsonResult loginException(LoginException ex) {
        logger.error("login fail", ex);
        return new JsonResult(HttpCode.USER_LOGIN_FAIL,null, ex.getMessage());
    }
    
    /**
     * 
     * </p>业务异常处理</p>
     * @param ex
     * @return
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    @ExceptionHandler(value = { BusinessException.class })
    public JsonResult businessException(BusinessException ex) {
        logger.error(ex.getHttpCode().getMsg(), ex);
        return new JsonResult(ex.getHttpCode(), null, ex.getMessage());
    }
    
    /**
     * 
     * </p>404处理</p>
     * @param ex
     * @return
     * @author yujl@ancda.com
     * @date 2017年4月25日
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.warn(HttpCode.NOT_FOUND.getMsg(), ex);
        return new JsonResult(HttpCode.NOT_FOUND, null, ex.getMessage());
    }

    /**
     *
     * </p>全局异常处理</p>
     * @param ex
     * @return
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    @ExceptionHandler(value = { Exception.class })
    public JsonResult unknownException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new JsonResult(HttpCode.INTERNAL_SERVER_ERROR, null, HttpCode.INTERNAL_SERVER_ERROR.getMsg());
    }

}