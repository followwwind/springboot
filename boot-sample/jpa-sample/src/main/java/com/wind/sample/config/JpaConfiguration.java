package com.wind.sample.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title: JpaConfiguration
 * @Package com.wind.sample.config
 * @Description: TODO
 * @author wind
 * @date 2018/9/21 15:45
 * @version V1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
//@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.wind.sample.dao")
@EntityScan(basePackages = "com.wind.sample.entity.po")
public class JpaConfiguration {

}
