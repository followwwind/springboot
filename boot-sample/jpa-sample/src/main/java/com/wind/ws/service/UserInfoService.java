package com.wind.ws.service;

import com.wind.ws.common.persistence.base.BaseService;
import com.wind.ws.entity.po.UserInfo;
import com.wind.ws.entity.query.UserSaveQ;
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
