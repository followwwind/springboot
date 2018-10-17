package com.wind.shiroapi.authc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.realm.Realm;

/**
 * @Title: CustomFirstSuccessfulStrategy
 * @Package com.wind.shiroapi.authc
 * @Description: 自定义多Realm情况下只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略
 * @author wind
 * @date 2018/10/17 13:29
 * @version V1.0
 */
public class CustomFirstSuccessfulStrategy extends FirstSuccessfulStrategy {

    /**
     * 重写afterAttempt 如果是AuthenticationException异常则直接抛出
     * @param realm
     * @param token
     * @param singleRealmInfo
     * @param aggregateInfo
     * @param t
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token,
                                           AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo,
                                           Throwable t) throws AuthenticationException {
        if (t instanceof AuthenticationException){
            throw (AuthenticationException) t;
        }
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }
}
