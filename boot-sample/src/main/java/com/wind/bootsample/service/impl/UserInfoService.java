package com.wind.bootsample.service.impl;

import com.wind.bootsample.base.BaseService;
import com.wind.bootsample.dao.UserInfoDao;
import com.wind.bootsample.entity.UserInfo;
import com.wind.bootsample.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoService extends BaseService<UserInfo, Long> implements IUserInfoService{

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public int insert(UserInfo record) {
        record.setCreateTime(new Date());
        userInfoDao.save(record);
        return 0;
    }

    @Override
    public int deleteRecord(UserInfo record) {
        return 0;
    }

    @Override
    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoDao.findOne(id);
    }

    @Override
    public List<UserInfo> selectByCondition(UserInfo record) {
        Example<UserInfo> example = Example.of(record);
        return userInfoDao.findAll(example);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return 0;
    }
}
