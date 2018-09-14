package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * 
 * @fileName LoginException.java
 * @package com.ancda.palmbaby.ancda.common.exception
 * @description 登陆异常
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:18:56
 * @version V1.0
 */
public class LoginException extends BaseException {
	/**
     * 
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    private static final long serialVersionUID = -944197010226222774L;

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