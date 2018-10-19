package com.wind.webapi.constants;

/**
 * @Title: DelFlag
 * @Package com.wind.webapi.constants
 * @Description: 删除标记枚举
 * @author huanghy
 * @date 2018/10/18 13:45
 * @version V1.0
 */
public enum DelFlag implements BaseEnum {

    /**
     * 未删除
     */
    NORMAL(0, "未删除"),

    /**
     * 已删除
     */
    DELETE(1, "已删除");

    DelFlag(Integer code, String value) {
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

    @Override
    public boolean equalCode(Integer code) {
        return this.code.equals(code);
    }
}
