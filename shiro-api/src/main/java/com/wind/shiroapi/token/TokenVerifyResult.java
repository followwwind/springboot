package com.wind.shiroapi.token;

/**
 * @Title: TokenVerifyResult
 * @Package com.wind.shiroapi.token
 * @Description: Token 校验结果枚举定义
 * @author wind
 * @date 2018/10/17 9:32
 * @version V1.0
 */
public enum TokenVerifyResult {

    /**
     * 校验通过
     */
    PASSED(1, "校验通过!"),

    /**
     * Token为空
     */
    TOKEN_IS_NULL(2, "Token为空!"),

    /**
     * Token已过期
     */
    TOKEN_EXPIRED(3, "Token已过期!"),

    /**
     * Token不匹配
     */
    TOKEN_MISMATCH(4, "Token不匹配!");


    TokenVerifyResult(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    /** 值 */
    private final Integer code;

    /** 描述 */
    private final String value;

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
