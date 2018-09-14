package com.wind.webapi.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * 
 * @fileName I18nConfiguration.java
 * @package com.ancda.payservice.config.i18n
 * @description spring boot 国际化配置
 * @author yujl@ancda.com
 * @date 2017年6月23日 下午12:57:43
 * @version V1.0
 */
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

     @Bean
     public LocaleResolver localeResolver() {
         CookieLocaleResolver slr = new CookieLocaleResolver();
         slr.setDefaultLocale(Locale.CHINA);
         return slr;
     }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：lang
        lci.setParamName("lang");
        return lci;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}