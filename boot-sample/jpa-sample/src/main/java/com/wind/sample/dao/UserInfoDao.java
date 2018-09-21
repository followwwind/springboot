package com.wind.sample.dao;

import com.wind.sample.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Title: UserInfoDao
 * @Package com.wind.sample.dao
 * @Description: TODO
 * @author huanghy
 * @date 2018/9/14 14:46
 * @version V1.0
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long>{

}
