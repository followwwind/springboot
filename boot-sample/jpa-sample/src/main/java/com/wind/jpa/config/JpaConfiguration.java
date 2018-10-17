package com.wind.jpa.config;

import com.baidu.unbiz.fluentvalidator.interceptor.FluentValidateInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: JpaConfiguration
 * @Package com.wind.sample.config
 * @Description: TODO
 * @author wind
 * @date 2018/9/21 15:45
 * @version V1.0
 */
@Aspect
@Configuration
public class JpaConfiguration {

    @Autowired
    @Qualifier("fluentValidateInterceptor")
    private FluentValidateInterceptor interceptor;

    @Bean
    public Advisor fluentValidateAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.wind.sample.service.impl.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, interceptor);
    }

}
