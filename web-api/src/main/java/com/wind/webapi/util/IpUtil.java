package com.wind.webapi.util;


import com.wind.common.constant.Constants;
import com.wind.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Title: IpUtil
 * @Package com.wind.webapi.util
 * @Description: IP地址工具类
 * @author wind
 * @date 2018/10/17 8:32
 * @version V1.0
 */
public class IpUtil {

    /**
     * 获取客户端IP<br>
     * 默认检测的Header：<br>
     * 1、X-Forwarded-For<br>
     * 2、X-Real-IP<br>
     * 3、Proxy-Client-IP<br>
     * 4、WL-Proxy-Client-IP<br>
     * otherHeaderNames参数用于自定义检测的Header
     *
     * @param request 请求对象
     * @param otherHeaderNames 其他自定义头文件
     * @return IP地址
     */
    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
        List<String> list = new ArrayList<>(Arrays.asList(headers));
        if (otherHeaderNames != null && otherHeaderNames.length > 0) {
            list.addAll(Arrays.asList(otherHeaderNames));
        }

        Optional<String> opt = list.stream().filter(header -> !isUnknown(request.getHeader(header))).findFirst();
        String ip = opt.map(request::getHeader).orElse(request.getRemoteAddr());
        return getMultistageReverseProxyIp(ip);
    }


    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(Constants.COMMA_STR) > 0) {
            final String[] ips = ip.trim().split(Constants.COMMA_STR);
            for (String subIp : ips) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    public static boolean isUnknown(String checkString) {
        return StringUtil.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }


}
