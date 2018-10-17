package com.wind.jpa.common.persistence.base;

import java.util.List;

/**
 * @Title: IBaseService
 * @Package com.wind.sample.common.persistence.base
 * @Description: 基础
 * @author huanghy
 * @date 2018/9/14 14:53
 * @version V1.0
 */
public interface BaseService<R, PK> {

    /**
     * 添加
     * @param record
     * @return
     */
    int save(R record);

    /**
     * 删除
     * @param record
     * @return
     */
    int delete(R record);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    R findById(PK id);

    /**
     * 查询列表
     * @param record
     * @return
     */
    List<R> findList(R record);

    /**
     * 修改
     * @param record
     * @return
     */
    int update(R record);

}
