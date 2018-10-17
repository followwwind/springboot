package com.wind.shiroapi.realm;

import com.wind.shiroapi.subject.Principal;
import com.wind.shiroapi.token.StatelessAuthenticationToken;
import com.wind.shiroapi.token.TokenManager;
import com.wind.shiroapi.token.TokenVerifyResult;
import com.wind.shiroapi.util.SecurityUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: StatelessAuthorizingRealm
 * @Package com.wind.shiroapi.realm
 * @Description: 基于Json Web Token的无状态Realm
 * @author wind
 * @date 2018/10/17 10:18
 * @version V1.0
 */
public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(StatelessAuthorizingRealm.class);

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthManager authManager;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        logger.info("StatelessAuthorizingRealm.getAuthorizationInfo params: userId is {}, compId is {}.",
                principal.getId(), principal.getCompId());
        AuthorizationInfo info = SecurityUtil.getAuthInfo(principal.getId(), principal.getCompId());
        if (info == null) {
            info = doGetAuthorizationInfo(principals);
            if (info != null) {
                SecurityUtil.putAuthInfo(principal.getId(), principal.getCompId(), info);
            }
        }
        return info;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        logger.info("StatelessAuthorizingRealm.doGetAuthorizationInfo params: userId is {}, compId is {}.",
                principal.getId(), principal.getCompId());
        return authManager.getAuthorizationInfo(principal);
    }

    /**
     * 认证回调函数, 登录时调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        logger.info("StatelessAuthorizingRealm.doGetAuthenticationInfo params: token is {}", token);
        StatelessAuthenticationToken authenticationToken = (StatelessAuthenticationToken) token;
        TokenVerifyResult result = tokenManager.verify(authenticationToken);
        if (result == TokenVerifyResult.PASSED) {
            String userId = authenticationToken.getUserId();
            String compId = authenticationToken.getCompId();
            return authManager.getAuthenticationInfo(userId, compId);
        } else if (result == TokenVerifyResult.TOKEN_IS_NULL) {
            throw new UnknownAccountException("Token is empty!");
        } else if (result == TokenVerifyResult.TOKEN_EXPIRED) {
            throw new ExpiredCredentialsException("Token has expired!");
        } else if (result == TokenVerifyResult.TOKEN_MISMATCH) {
            throw new ConcurrentAccessException("The account has been logged in elsewhere!");
        }
        return null;
    }
}
