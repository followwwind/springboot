package com.wind.webapi.exception;

import com.wind.webapi.constants.HttpCode;

/**
 * 
 * @fileName BusinessException.java
 * @package com.ancda.palmbaby.ancda.common.exception
 * @description 业务异常
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午11:18:39
 * @version V1.0
 */
public class BusinessException extends BaseException {
    
    /**
     * 
     * @author yujl@ancda.com
     * @date 2017年4月24日
     */
    private static final long serialVersionUID = -8632520309363933123L;

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