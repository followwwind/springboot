package com.wind.jpa.service;

import com.wind.jpa.common.persistence.base.BaseService;
import com.wind.jpa.entity.po.UserInfo;
import com.wind.jpa.entity.query.UserSaveQ;
import com.wind.webapi.message.JsonResult;

/**
 * @Title: UserInfoService
 * @Package com.wind.sample.service
 * @Description: TODO
 * @author huanghy
 * @date 2018/9/14 15:02
 * @version V1.0
 */
public interface UserInfoService extends BaseService<UserInfo, Long>{

    /**
     * 新增
     * @param userSaveQ
     * @return
     */
    JsonResult add(UserSaveQ userSaveQ);
}
