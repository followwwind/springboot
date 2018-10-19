package com.wind.shiro.security.cache.session;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Title: SessionCacheManager
 * @Package com.wind.shiro.security.cache.session
 * @Description: 自定义授权缓存管理类
 * @author wind
 * @date 2018/10/17 10:13
 * @version V1.0
 */
public class SessionCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new SessionCache<>(name);
    }
}
