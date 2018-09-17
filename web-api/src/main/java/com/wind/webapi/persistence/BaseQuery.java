package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Title: BaseQuery
 * @Package com.wind.webapi.persistence
 * @Description: 基础Query实体类
 * @author wind
 * @date 2018/9/17 17:43
 * @version V1.0
 */
public abstract class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("sqlMap").toString();
    }

}
