package com.wind.shiroapi.realm;

import com.wind.shiroapi.subject.Principal;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @Title: AuthManager
 * @Package com.wind.shiroapi.realm
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 14:02
 * @version V1.0
 */
public interface AuthManager {

    /**
     * 获取用户信息
     * @param id
     * @param compId
     * @return
     */
    SimpleAuthenticationInfo getAuthenticationInfo(String id, String compId);

    /**
     * 获取用户权限角色信息
     * @param principal
     * @return
     */
    AuthorizationInfo getAuthorizationInfo(Principal principal);
}
