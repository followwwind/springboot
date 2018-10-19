package com.wind.shiro.security.realm;

import com.wind.common.util.StringUtil;
import com.wind.shiro.entity.LoginType;
import com.wind.shiro.entity.UserVO;
import com.wind.shiro.security.subject.Principal;
import com.wind.shiro.security.token.FormAuthenticationToken;
import com.wind.shiro.security.token.StatelessAuthenticationToken;
import com.wind.shiro.security.token.TokenManager;
import com.wind.shiro.security.token.TokenVerifyResult;
import com.wind.shiro.security.util.SecurityUtil;
import com.wind.webapi.constants.DelFlag;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: StatelessAuthorizingRealm
 * @Package com.wind.shiro.security.realm
 * @Description: 基于Json Web Token的无状态Realm
 * @author wind
 * @date 2018/10/17 10:18
 * @version V1.0
 */
public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(StatelessAuthorizingRealm.class);

    @Autowired
    private TokenManager tokenManager;

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

        UserVO user = "111111".equals(principal.getId()) ? new UserVO() : null;
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 添加用户权限
            info.addStringPermission("user");
            return info;
        }
        return null;
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
        if(token instanceof FormAuthenticationToken){
            FormAuthenticationToken authToken = (FormAuthenticationToken) token;
            return dealFormToken(authToken);
        }else if(token instanceof StatelessAuthenticationToken){
            StatelessAuthenticationToken authToken = (StatelessAuthenticationToken) token;
            return dealStatelessToken(authToken);
        }else{
            return null;
        }
    }

    /**
     * 处理登录
     * @param authToken
     * @return
     */
    private AuthenticationInfo dealFormToken(FormAuthenticationToken authToken){
        String username = authToken.getUsername();
        Integer loginType = authToken.getLoginType();
        // 校验用户名
        UserVO user = "18771933975".equals(username) ? new UserVO() : null;
        if (user == null) {
            // 账号不存在
            throw new UnknownAccountException();
        } else if (DelFlag.DELETE.equalCode(user.getDelFlag())) {
            // 账号已禁用
            throw new DisabledAccountException();
        } else if (LoginType.PASSWORD.equalCode(loginType)) {
            if (StringUtil.isBlank(user.getSalt()) || StringUtil.isBlank(user.getPassword())) {
                throw new CredentialsException();
            }
            // 密码登陆处理
            return new SimpleAuthenticationInfo(new Principal(user.getUserId(), username, "", loginType), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        } else {
            // 手机号验证码登陆处理
            // 从redis获取手机验证码
            String captcha = "123456";
            user.setUserId("111111");
            return new SimpleAuthenticationInfo(new Principal(user.getUserId(), username, "", loginType), captcha, null, getName());
        }
    }

    /**
     * 处理带token切换登录
     * @param authToken
     * @return
     */
    private AuthenticationInfo dealStatelessToken(StatelessAuthenticationToken authToken){
        TokenVerifyResult result = tokenManager.verify(authToken);
        String token = authToken.getToken();
        if (result == TokenVerifyResult.PASSED) {
            String userId = authToken.getUserId();
            String compId = authToken.getCompId();
            UserVO user;
            try {
                user = "111111".equals(userId) ? new UserVO() : null;
            } catch (Exception ex) {
                throw new ConcurrentAccessException("Repeated account!");
            }

            if (user == null) {
                throw new UnknownAccountException("User does not exist!");
            }
            String username = user.getUsername();
            return new SimpleAuthenticationInfo(new Principal(userId, username, compId), token, getName());
        } else if (result == TokenVerifyResult.TOKEN_IS_NULL) {
            throw new UnknownAccountException("Token is empty!");
        } else if (result == TokenVerifyResult.TOKEN_EXPIRED) {
            throw new ExpiredCredentialsException("Token has expired!");
        } else if (result == TokenVerifyResult.TOKEN_MISMATCH) {
            throw new ConcurrentAccessException("The account has been logged in elsewhere!");
        }
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
//        return token instanceof StatelessAuthenticationToken;
        return true;
    }
}
