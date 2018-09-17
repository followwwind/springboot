package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * @Title: LoginException
 * @Package com.wind.webapi.exception
 * @Description: 登陆异常
 * @author wind
 * @date 2018/9/17 17:42
 * @version V1.0
 */
public class LoginException extends BaseException {

	private static final long serialVersionUID = 1L;

    public LoginException() {
	}

	public LoginException(String message) {
		super(message);
		this.httpCode = HttpCode.USER_LOGIN_FAIL;
	}

	public LoginException(String message, Exception e) {
		super(message, e);
		this.httpCode = HttpCode.USER_LOGIN_FAIL;
	}
	
	public LoginException(HttpCode httpCode, String message) {
        super(httpCode, message);
    }
    
    public LoginException(HttpCode httpCode, Throwable ex) {
        super(httpCode, ex);
    }

	public LoginException(HttpCode httpCode, String message, Throwable ex) {
        super(httpCode, message, ex);
    }
}