package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * @Title: BusinessException
 * @Package com.wind.webapi.exception
 * @Description: 业务异常
 * @author wind
 * @date 2018/9/17 17:41
 * @version V1.0
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException() {
    }

    public BusinessException(Throwable ex) {
        super(ex);
        this.httpCode = HttpCode.INTERNAL_SERVER_ERROR;
    }

    public BusinessException(String message) {
        super(message);
        this.httpCode = HttpCode.INTERNAL_SERVER_ERROR;
    }
    
    public BusinessException(HttpCode httpCode, String message) {
        super(httpCode, message);
    }
    
    public BusinessException(HttpCode httpCode, Throwable ex) {
        super(httpCode, ex);
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
        this.httpCode = HttpCode.INTERNAL_SERVER_ERROR;
    }
    
    public BusinessException(HttpCode httpCode, String message, Throwable ex) {
        super(httpCode, message, ex);
    }

}