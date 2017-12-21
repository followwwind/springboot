package com.wind.sample.base;

import java.util.List;

public interface IBaseService<R, PK> {

    int insert(R record);

    int deleteRecord(R record);

    R selectByPrimaryKey(PK id);

    List<R> selectByCondition(R record);

    int updateByPrimaryKeySelective(R record);

}
