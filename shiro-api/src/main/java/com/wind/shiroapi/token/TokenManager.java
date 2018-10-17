package com.wind.shiroapi.token;

/**
 * @Title: TokenManager
 * @Package com.wind.shiroapi.token
 * @Description: 对token进行操作的接口
 * @author wind
 * @date 2018/10/17 13:37
 * @version V1.0
 */
public interface TokenManager {

    /**
     * 创建一个token
     * @param userId 用户id
     * @param compId 组织id
     * @return 生成的token
     */
    StatelessAuthenticationToken create(String userId, String compId);

    /**
     * 检查token是否有效
     * @param token
     * @return 是否有效
     */
    TokenVerifyResult verify(StatelessAuthenticationToken token);

    /**
     * 从字符串中解析token
     * @param authentication 加密后token字符串
     * @return
     */
    StatelessAuthenticationToken get(String authentication);

    /**
     * 删除token
     * @param userId 用户id
     */
    void delete(String userId);

    /**
     *
     * </p>更新token</p>
     * @param token
     * @author yujl@ancda.com
     * @date 2017年7月24日
     */
    void update(StatelessAuthenticationToken token);
}
