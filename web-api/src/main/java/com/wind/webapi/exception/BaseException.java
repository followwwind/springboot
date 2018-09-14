package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * 
 * @fileName BaseException.java
 * @package com.ancda.palmbaby.ancda.common.exception
 * @description 异常基类
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:18:32
 * @version V1.0
 */
public abstract class BaseException extends RuntimeException {

    /**
     * 
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    private static final long serialVersionUID = 6503055422698438458L;
    
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