package com.wind.shiro.controller;

import com.wind.common.util.RegexUtil;
import com.wind.common.util.StringUtil;
import com.wind.shiro.entity.LoginQ;
import com.wind.shiro.entity.LoginType;
import com.wind.shiro.security.subject.Principal;
import com.wind.shiro.security.token.FormAuthenticationToken;
import com.wind.shiro.security.token.StatelessAuthenticationToken;
import com.wind.shiro.security.token.TokenManager;
import com.wind.shiro.security.util.SecurityUtil;
import com.wind.webapi.constants.HttpCode;
import com.wind.webapi.message.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Title: LoginController
 * @Package com.wind.shiro.controller
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 15:19
 * @version V1.0
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(HttpServletResponse response, @RequestBody LoginQ loginQ) {
        if (loginQ == null) {
            return new JsonResult(HttpCode.BAD_REQUEST);
        }

        String username = loginQ.getUsername();
        String password = loginQ.getPassword();
        Integer loginType = loginQ.getLoginType();
        logger.info("LoginController.login params: username is {}, loginType is {}",
                username, loginType);

        // 参数校验
        if (loginType == null || LoginType.getLoginType(loginType) == null) {
            return new JsonResult(HttpCode.BAD_REQUEST);
        }

        if (StringUtil.isBlank(username) || StringUtil.isBlank(password) || !RegexUtil.checkPhone(username)) {
            return new JsonResult(HttpCode.BAD_REQUEST);
        }

        FormAuthenticationToken token = new FormAuthenticationToken(loginType, username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            token.clear();
            return HttpCode.USER_NOT_FOUND.getJsonResult();
        } catch (IncorrectCredentialsException ice) {
            token.clear();
            return HttpCode.USER_PASSWORD_ERROR.getJsonResult();
        } catch (DisabledAccountException e) {
            token.clear();
            return HttpCode.USER_ACCOUNT_DISABLED.getJsonResult();
        } catch (ExcessiveAttemptsException e) {
            token.clear();
            return HttpCode.USER_ACCOUNT_LOCK.getJsonResult();
        } catch (AuthenticationException e) {
            token.clear();
            return HttpCode.USER_LOGIN_FAIL.getJsonResult();
        }

        Principal principal = SecurityUtil.getPrincipal();
        String id = principal.getId();
        String compId = principal.getCompId();
        StatelessAuthenticationToken authenticationToken = tokenManager.create(id, compId);
        response.setHeader("X-API-TOKEN", authenticationToken.getToken());
        return new JsonResult(HttpCode.OK, null);
    }
    
}
