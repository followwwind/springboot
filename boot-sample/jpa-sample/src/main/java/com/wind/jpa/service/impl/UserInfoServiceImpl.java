package com.wind.jpa.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.wind.jpa.common.persistence.base.BaseServiceImpl;
import com.wind.jpa.dao.UserInfoDao;
import com.wind.jpa.entity.po.UserInfo;
import com.wind.jpa.entity.query.UserSaveQ;
import com.wind.jpa.service.UserInfoService;
import com.wind.webapi.exception.BusinessException;
import com.wind.webapi.message.JsonResult;
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
    public JsonResult add(@FluentValid UserSaveQ userSaveQ) {
        return null;
    }

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
