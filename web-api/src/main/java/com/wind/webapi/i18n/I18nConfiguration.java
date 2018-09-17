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
 * @Title: I18nConfiguration
 * @Package com.wind.webapi.i18n
 * @Description: spring boot 国际化配置
 * @author wind
 * @date 2018/9/17 17:39
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