package com.wind.shiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: JwtConfig
 * @Package com.wind.shiro.config
 * @Description: jwt配置
 * @author wind
 * @date 2018/10/18 15:12
 * @version V1.0
 */
@Component
public class JwtConfig {

    public static String ISSUER;

    public static String SECRET;

    public static String TOKEN_HEADER;

    public static String TOKEN_SUFFIX;

    @Value("${jwt.issuer:wind}")
    public void setISSUER(String issuer) {
        JwtConfig.ISSUER = issuer;
    }

    @Value("${jwt.secret:N2QyZXYqeVdndkt3ak5eXks3UTZYbTBPZFRwV2ZxUjQ}")
    public void setSECRET(String secret) {
        JwtConfig.SECRET = secret;
    }

    @Value("${jwt.token:X-API-TOKEN}")
    public void setTokenHeader(String tokenHeader) {
        JwtConfig.TOKEN_HEADER = tokenHeader;
    }

    @Value("${jwt.suffix:}")
    public void setTokenSuffix(String tokenSuffix) {
        JwtConfig.TOKEN_SUFFIX = tokenSuffix;
    }
}
