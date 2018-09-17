package com.wind.webapi.validator;

import com.baidu.unbiz.fluentvalidator.interceptor.FluentValidateInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;

/**
 * @Title: ValidatorConfiguration
 * @Package com.wind.webapi.validator
 * @Description: spring boot 验证插件配置
 * @author wind
 * @date 2018/9/17 18:04
 * @version V1.0
 */
@Aspect
@Configuration
public class ValidatorConfiguration {

    private final static String LANG = "zh_CN";
    
    @Bean
    public CustomValidateCallback customValidateCallback() {
        return new CustomValidateCallback();
    }
    
    @Bean("fluentValidateInterceptor")
    public FluentValidateInterceptor fluentValidateInterceptor() {
        FluentValidateInterceptor fluentValidateInterceptor = new FluentValidateInterceptor();
        fluentValidateInterceptor.setCallback(customValidateCallback());
        fluentValidateInterceptor.setValidator(hibernateValidator());
        fluentValidateInterceptor.setHibernateDefaultErrorCode(500);
        fluentValidateInterceptor.setLocale(LANG);
        return fluentValidateInterceptor;
    }
    
//    @Bean
//    public Advisor fluentValidateAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(* com.ancda.cloudservice.service.impl.*.*(..))");
//        return new DefaultPointcutAdvisor(pointcut, fluentValidateInterceptor());
//    }

    @Bean
    public Validator hibernateValidator() {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
    
}
