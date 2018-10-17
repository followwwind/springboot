package com.wind.shiro.auth;

import com.wind.shiroapi.realm.AuthManager;
import com.wind.shiroapi.subject.Principal;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @Title: AuthManagerImpl
 * @Package com.wind.shiro.auth
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 14:35
 * @version V1.0
 */
public class AuthManagerImpl implements AuthManager {
    
    @Override
    public SimpleAuthenticationInfo getAuthenticationInfo(String id, String compId) {
        return null;
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(Principal principal) {
        return null;
    }
}
