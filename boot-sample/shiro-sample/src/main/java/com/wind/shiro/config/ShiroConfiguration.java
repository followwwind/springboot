package com.wind.shiro.config;

import com.wind.shiro.security.authc.CustomFirstSuccessfulStrategy;
import com.wind.shiro.security.cache.redis.RedisCacheManager;
import com.wind.shiro.security.filter.AuthenticationFilter;
import com.wind.shiro.security.mgt.StatelessSubjectFactory;
import com.wind.shiro.security.realm.StatelessAuthorizingRealm;
import com.wind.shiro.security.token.TokenManager;
import com.wind.shiro.security.token.impl.SimpleJwtTokenManagerImpl;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ShiroConfiguration
 * @Package com.wind.shiro.config
 * @Description: Shiro 配置
 * @author wind
 * @date 2018/10/17 13:23
 * @version V1.0
 */
@Configuration
@AutoConfigureAfter(value={ShiroWebAutoConfiguration.class, ShiroWebFilterConfiguration.class})
public class ShiroConfiguration {

    @Value("#{ @environment['shiro.loginUrl'] ?: '/login.jsp' }")
    private String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/' }")
    private String successUrl;

    @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }")
    private String unauthorizedUrl;

    private final String STATELESS_AUTH = "statelessAuth";

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/api/login", "anon");
        chainDefinition.addPathDefinition("/api/hello/sayHello", "anon");
        chainDefinition.addPathDefinition("/api/**", STATELESS_AUTH);
        return chainDefinition;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setAuthenticator(authenticator());
//        securityManager.setRealms(realms());
        securityManager.setRealm(statelessRealm());
        // 设置缓存管理器
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSubjectDAO(subjectDAO());
        return securityManager;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        return new RedisCacheManager();
    }

    /**
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 由于采用无状态，所以没必要再定期过期会话了。
     * @return
     */
    @Bean
    public SubjectDAO subjectDAO() {
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        return subjectDAO;
    }

    /**
     * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions
     * 所以需要配合context.setSessionCreationEnabled(false)
     * @return
     */
    @Bean
    public SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 禁用Sessions 作为存储策略的实现
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * 重写FirstSuccessfulStrategy，如果是AuthenticationException异常则直接抛出
     * @return
     */
    @Bean
    public AuthenticationStrategy authenticationStrategy() {
        return new CustomFirstSuccessfulStrategy();
    }

    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(authenticationStrategy());
        return authenticator;
    }

    @Bean
    public TokenManager tokenManager() {
        return new SimpleJwtTokenManagerImpl();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put(STATELESS_AUTH, new AuthenticationFilter(tokenManager()));
        filterFactoryBean.setFilters(filters);
        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        return filterFactoryBean;
    }

    /**
     * shiro过滤器
     * @param shiroFilterFactoryBean
     * @return
     * @throws Exception
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(ShiroFilterFactoryBean shiroFilterFactoryBean)
            throws Exception {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        AbstractShiroFilter filter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        if(filter != null){
            filterRegistration.setFilter(filter);
        }
        // 激活注册拦截器
        filterRegistration.setEnabled(true);
        // 添加拦截路径
        filterRegistration.addUrlPatterns("/api/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistration.setOrder(1);
        return filterRegistration;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    @Bean
    public SubjectFactory subjectFactory() {
        return new StatelessSubjectFactory();
    }

    @Bean
    public List<Realm> realms() {
        List<Realm> realms = new ArrayList<>();
        realms.add(statelessRealm());
        return realms;
    }

    /**
     * 无状态域
     * @return
     */
    @Bean
    public Realm statelessRealm() {
        return new StatelessAuthorizingRealm();
    }

    /**
     * shiro生命周期处理
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用shiro注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
