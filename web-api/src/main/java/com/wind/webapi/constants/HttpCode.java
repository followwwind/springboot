package com.wind.webapi.constants;

import com.wind.webapi.util.LocaleMessageSourceUtil;

/**
 * 
 * @fileName HttpCode.java
 * @package com.ancda.palmbaby.ancda.common.constant
 * @description Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 * @author yujl@ancda.com
 * @date 2017年6月23日 上午10:58:31
 * @version V1.0
 */
public enum HttpCode implements BaseCode {

    /** 200请求成功 */
    OK(200),
    /** 0操作失败 */
    FAIL(0),
    /** 100 数据为空 */
    DATA_IS_EMPTY(100),
    /** 207频繁操作 */
    MULTI_STATUS(207),
    /** 400请求参数出错 */
    BAD_REQUEST(400),
    /** 401没有权限访问 */
    UNAUTHORIZED(401),
    /** 403禁止访问 */
    FORBIDDEN(403),
    /** 404找不到页面 */
    NOT_FOUND(404),
    /** 408请求超时 */
    REQUEST_TIMEOUT(408),
    /** 410请求资源不可用 */
    GONE(410),
    /** 500服务器出错 */
    INTERNAL_SERVER_ERROR(500),


    /** ######## 用户相关 ######## */
    /** 100001登录失败 */
    USER_LOGIN_FAIL(100001),
    /** 100002用户不存在 */
    USER_NOT_FOUND(100002),
    /** 100003密码错误 */
    USER_PASSWORD_ERROR(100003),
    /** 100004账号被锁定 */
    USER_ACCOUNT_LOCK(100004),
    /** 100005账号已被禁用 */
    USER_ACCOUNT_DISABLED(100005),
    /** 100006Token已过期 */
    USER_TOKEN_EXPIRED(100006),
    /** 100007账号以在其他地方登陆 */
    USER_LOG_IN_ELSEWHERE(100007),
    /** 100008手机号重复 **/
    USER_PHONE_REPEAT(100008),
    /** 100009当日短信发送次数超限 **/
    SMS_TIMES_LIMIT(100009),
    /** 100010验证码过期 **/
    CAPTCHA_EXPIRED(100010),
    /** 100011验证码错误 **/
    CAPTCHA_ERROR(100011),
    /** 100012验证码发送失败 **/
    CAPTCHA_FAIL(100012),

    /** 当前机构下该用户已存在*/
    ORGANIZATION_NAME_ALREADY_EXISTS(100013),

    /** 用户名已存在 */
    LOGIN_NAME_ALREADY_EXISTS(100014),
    /** 用户名不存在 */
    LOGIN_NAME_NOT_EXISTS(100015),

    ;

    private final Integer code;

    HttpCode(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return LocaleMessageSourceUtil.getMessage(HTTP_CODE + this.code);
    }

    public static HttpCode valueOf(int code) {
        for (HttpCode status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    public boolean equalCode(Integer code) {
        return this.code.equals(code);
    }
}