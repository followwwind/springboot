package com.wind.shiroapi.token;

/**
 * @Title: AbstractTokenManager
 * @Package com.wind.shiroapi.token
 * @Description: 通用TokenManager
 * @author wind
 * @date 2018/10/17 9:40
 * @version V1.0
 */
public abstract class AbstractTokenManager implements TokenManager{

    /**
     * 根据用户id创建token
     * @param userId
     * @param compId
     * @return
     */
    public abstract String createToken(String userId, String compId);

}
