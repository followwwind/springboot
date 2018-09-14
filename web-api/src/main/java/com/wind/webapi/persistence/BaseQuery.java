package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @fileName BaseQuery
 * @package com.ancda.palmbaby.ancda.common.persistence
 * @description 基础Query实体类
 * @author yujl@ancda.com
 * @date 2018-05-05 18:48:08
 * @version V1.0
 */
public abstract class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("sqlMap").toString();
    }

}
