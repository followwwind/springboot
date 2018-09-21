package com.wind.sample.service.impl;

import com.wind.sample.common.persistence.base.BaseServiceImpl;
import com.wind.sample.dao.UserInfoDao;
import com.wind.sample.entity.po.UserInfo;
import com.wind.sample.service.UserInfoService;
import com.wind.webapi.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Long> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int save(UserInfo record) {
        record.setCreateTime(new Date());
        userInfoDao.save(record);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int delete(UserInfo record) {
        userInfoDao.delete(record);
        return 1;
    }

    @Override
    public UserInfo findById(Long id) {
        Optional<UserInfo> opt = userInfoDao.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<UserInfo> findList(UserInfo record) {
        Example<UserInfo> example = Example.of(record);
        return userInfoDao.findAll(example);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int update(UserInfo record) {
        userInfoDao.saveAndFlush(record);
        return 1;
    }
}
