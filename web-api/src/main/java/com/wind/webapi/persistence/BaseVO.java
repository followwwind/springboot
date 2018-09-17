package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Title: BaseVO
 * @Package com.wind.webapi.persistence
 * @Description: 基础VO实体类
 * @author wind
 * @date 2018/9/17 17:43
 * @version V1.0
 */
public abstract class BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public BaseVO() {
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
