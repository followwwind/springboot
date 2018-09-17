package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * @Title: IllegalParameterException
 * @Package com.wind.webapi.exception
 * @Description: 非法参数异常
 * @author wind
 * @date 2018/9/17 17:42
 * @version V1.0
 */
public class IllegalParameterException extends BaseException {

    private static final long serialVersionUID = 1L;

    public IllegalParameterException() {
	}

	public IllegalParameterException(Throwable ex) {
		super(ex);
		this.httpCode = HttpCode.BAD_REQUEST;
	}

	public IllegalParameterException(String message) {
		super(message);
		this.httpCode = HttpCode.BAD_REQUEST;
	}
	
	public IllegalParameterException(HttpCode httpCode, String message) {
        super(httpCode, message);
    }
    
    public IllegalParameterException(HttpCode httpCode, Throwable ex) {
        super(httpCode, ex);
    }
	
	public IllegalParameterException(HttpCode httpCode, String message, Throwable ex) {
        super(httpCode, message, ex);
    }
}