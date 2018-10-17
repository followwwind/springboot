package com.wind.shiroapi.filter;

import com.wind.common.util.JsonUtil;
import com.wind.shiroapi.token.StatelessAuthenticationToken;
import com.wind.shiroapi.token.TokenManager;
import com.wind.webapi.constants.HttpCode;
import com.wind.webapi.message.JsonResult;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * @Title: AuthenticationFilter
 * @Package com.wind.shiroapi.filter
 * @Description: shiro过滤器
 * @author wind
 * @date 2018/10/17 11:00
 * @version V1.0
 */
public class AuthenticationFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private TokenManager tokenManager;

    private String tokenHeader;

    private String tokenSuffix;

    private final String START = "^";

    private final String END = "\\S+";

    public AuthenticationFilter(TokenManager tokenManager, String tokenHeader, String tokenSuffix) {
        this.tokenManager = tokenManager;
        this.tokenHeader = tokenHeader;
        this.tokenSuffix = tokenSuffix;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o){
        logger.info("SwitchAuthenticationFilter.isAccessAllowed");
        // token授权信息放在请求头中传入
        String authorization = getAuthorization(request);

        // 无授权头返回
        if (authorization == null || "".equals(authorization)) {
            onLoginFail(response, new JsonResult(HttpCode.UNAUTHORIZED));
            return false;
        }

        StatelessAuthenticationToken accessToken;
        try {
            // 获取无状态Token
            accessToken = tokenManager.get(authorization);

            // 委托给Realm进行登录
            getSubject(request, response).login(accessToken);

            MDC.put("userId", accessToken.getUserId());
            MDC.put("compId", accessToken.getCompId());
        } catch (UnknownAccountException e) {
            onLoginFail(response, new JsonResult(HttpCode.UNAUTHORIZED));
            return false;
        } catch (ExpiredCredentialsException e) {
            // token已过期
            onLoginFail(response, HttpCode.USER_TOKEN_EXPIRED.getJsonResult());
            return false;
        } catch (ConcurrentAccessException e) {
            // 账号已在其他地方登陆
            onLoginFail(response, HttpCode.USER_LOG_IN_ELSEWHERE.getJsonResult());
            return false;
        } catch (Exception e) {
            onLoginFail(response, new JsonResult(HttpCode.UNAUTHORIZED));
            return false;
        }

        // 校验通过更新token过期日期
        tokenManager.update(accessToken);

        // 通过isPermitted才能调用doGetAuthorizationInfo方法获取权限信息
        getSubject(request,response).isPermitted(((HttpServletRequest)request).getRequestURI());

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        return false;
    }

    private String getAuthorization(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String token = httpRequest.getHeader(tokenHeader);
        if (!"".equals(token)  && Pattern.matches(START + tokenSuffix + END, token)) {
            return token.replaceFirst(tokenSuffix, "");
        }
        return null;
    }

    private void onLoginFail(ServletResponse response, JsonResult responseMessage) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 设置编码格式
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = httpResponse.getWriter();
            out.write(JsonUtil.toJson(responseMessage));
            out.flush();
        } catch (IOException ex) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
