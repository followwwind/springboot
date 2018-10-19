package com.wind.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Title: RedisConfiguration
 * @Package com.wind.shiro.config
 * @Description: redis配置
 * @author wind
 * @date 2018/10/18 14:20
 * @version V1.0
 */
@Configuration
public class RedisConfiguration {

    /**
     * Redis 字符串对象类型模版
     * Spring Boot "spring-boot-starter-data-redis" 自动化配置插件默认提供
     * RedisTemplate<String, String> 以及 RedisTemplate<Object, Object> 模版操作类
     * 但后者并没有实现"对象序列化"，而字符串对象类型 RedisTemplate<String, Object> 需要手动配置并初始化
     * lettuceConnectionFactory 已由 spring-boot-starter-data-redis 实例化为Spring Bean可直接作为参数注入
     */
    @Bean
    public RedisTemplate<String, Object> objectRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
