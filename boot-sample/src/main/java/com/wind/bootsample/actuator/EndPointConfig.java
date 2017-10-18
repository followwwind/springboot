package com.wind.bootsample.actuator;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 自定义EndPoint配置
 */

@Configuration
public class EndPointConfig {

    /**
     * 可以配置多个EndPoint，方法上添加bean注解
     * @return
     */
    @Bean
    public Endpoint<Map<String, Object>> customEndPoint() {
        return new CustomEndPoint();
    }
}
