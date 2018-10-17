package com.wind.shiroapi.cache.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager implements CacheManager {

    private Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时间，单位：秒
     */
    private static final int DEFAULT_EXPIRE = 1800;

    private int expire = DEFAULT_EXPIRE;

    /**
     * 缓存key前缀
     */
    private String cacheKeyPrefix = "shiro_cache:";

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<>(cacheKeyPrefix + name, redisTemplate, expire);
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getCacheKeyPrefix() {
        return cacheKeyPrefix;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }
}
