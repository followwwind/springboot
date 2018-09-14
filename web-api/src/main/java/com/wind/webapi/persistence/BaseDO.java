package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 
 * @fileName BaseDO.java
 * @package com.ancda.palmbaby.ancda.common.persistence
 * @description 基础DO实体类
 * @author yujl@ancda.com
 * @date 2017年6月25日 下午4:28:33
 * @version v1.0
 */
public abstract class BaseDO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	protected T id;
	

	public BaseDO() {
	}
	
	public BaseDO(T id) {
		this();
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
