package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 
 * @fileName BaseVO.java
 * @package com.ancda.palmbaby.ancda.common.persistence
 * @description 基础VO实体类
 * @author yujl
 * @date 2017年6月25日 下午4:28:33
 * @version v1.0
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
