package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * @Title: BaseException
 * @Package com.wind.webapi.exception
 * @Description: 异常基类
 * @author wind
 * @date 2018/9/17 17:41
 * @version V1.0
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    protected HttpCode httpCode;

    public BaseException() {
    }

    public BaseException(Throwable ex) {
        super(ex);
    }

    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(HttpCode httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public BaseException(String message, Throwable ex) {
        super(message, ex);
    }
    
    public BaseException(HttpCode httpCode, Throwable ex) {
        super(ex);
        this.httpCode = httpCode;
    }
    
    public BaseException(HttpCode httpCode, String message, Throwable ex) {
        super(message, ex);
        this.httpCode = httpCode;
    }
    
    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

}