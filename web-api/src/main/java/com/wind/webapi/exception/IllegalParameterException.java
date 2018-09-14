package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * 
 * @fileName IllegalParameterException.java
 * @package com.jd.ofitpro.manage.common.exception
 * @description 非法参数异常
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:18:47
 * @version V1.0
 */
public class IllegalParameterException extends BaseException {
	/**
     * 
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
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