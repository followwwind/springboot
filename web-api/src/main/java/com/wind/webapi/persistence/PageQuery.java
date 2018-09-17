package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Title: PageQuery
 * @Package com.wind.webapi.persistence
 * @Description: 分页查询参数对象封装类
 * @author wind
 * @date 2018/9/17 17:44
 * @version V1.0
 */
public class PageQuery implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNumber = 1;

    /**
     * 该页记录条数
     */
    private Integer lineNumber = 10;

    public Integer getPageNumber() {
        if(pageNumber != null && pageNumber <= 1) {
            return 1;
        }
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("sqlMap").toString();
    }
}
