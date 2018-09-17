package com.wind.webapi.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: Page
 * @Package com.wind.webapi.persistence
 * @Description: 分页实体类
 * @author wind
 * @date 2018/9/17 17:44
 * @version V1.0
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNumber = 1;

    /**
     * 该页记录条数
     */
    private Integer lineNumber = 10;

    /**
     * 记录总条数
     */
    private Long totalCount;

    /** 结果集 **/
    private Object data;

    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，而出现一些问题。
     * @param data          page结果
     */
    public Page(List<T> data) {
        if (data instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) data;
            this.pageNumber = page.getPageNum();
            this.lineNumber = page.getPageSize();
            this.totalCount = page.getTotal();
            this.data = page;
        }
    }

    /**
     * 构造方法
     * @param pageNum 当前页码
     * @param pageSize 分页大小
     * @param total 数据条数
     * @param data 本页数据对象列表
     */
    public Page(int pageNum, int pageSize, long total, List<T> data) {
        this.setTotalCount(total);
        this.setPageNumber(pageNum);
        this.setLineNumber(pageSize);
        this.setData(data);
    }

    public int getPageNumber() {
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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("data").toString();
    }

}
