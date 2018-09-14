package com.wind.webapi.filter;


import com.wind.common.util.IdGenUtil;
import com.wind.common.util.StringUtil;
import com.wind.webapi.util.IpUtil;
import org.slf4j.MDC;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @fileName CrossFilter.java
 * @package com.jd.ofitpro.manage.api.filter
 * @description 跨域设置过滤器，由于SpringShiroFilter的顺序为1，此处为确保跨域设置以及i18n正确配置，将顺序设置为0
 * @author yujl@ancda.com
 * @date 2017年7月20日 上午9:44:06
 * @version V1.0
 */
@Component
@Order(0)
public class CrossFilter extends OncePerRequestFilter {

    private final String PARAM_NAME = "lang";

    private final String IGNORE_METHOD = "OPTIONS";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        response.addHeader("Access-Control-Max-Age", "100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-API-TOKEN");
        response.addHeader("Access-Control-Allow-Credentials", "false");
        response.addHeader("Access-Control-Expose-Headers", "X-API-TOKEN");

        // 非简单请求跨域，请求方法为OPTIONS，直接返回200状态码
        if(IGNORE_METHOD.equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String slbId = request.getHeader("SLB-ID");
        // TODO 全局请求ID，由于在分布式环境下的链路追踪，后期改造，通过nginx生成requestId
        MDC.put("requestId", IdGenUtil.getUUID());
        MDC.put("instanceId", slbId != null ? slbId : "");
        MDC.put("ip", IpUtil.getClientIP(request));

        // i18n设置
        String lang = request.getParameter(PARAM_NAME);
        if (StringUtil.isNotBlank(lang)) {
            try {
                LocaleContextHolder.setLocale(StringUtils.parseLocaleString(lang));
            } catch (Exception e) {
            }
        }

        filterChain.doFilter(request, response);
        MDC.clear();
    }

}
