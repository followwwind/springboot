package com.wind.shiro.security.util;

import com.wind.webapi.util.SpringUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @Title: CacheUtil
 * @Package com.wind.shiro.security.util
 * @Description: Cache工具类
 * @author wind
 * @date 2018/10/17 10:40
 * @version V1.0
 */
public class CacheUtil {

    private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    private static CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);

    private static final String SYS_CACHE = "system";

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入SYS_CACHE缓存
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        return getCache(cacheName).get(key);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 从缓存中移除所有
     * @param cacheName
     */
    public static void removeAll(String cacheName) {
        Cache<String, Object> cache = getCache(cacheName);
        Set<String> keys = cache.keys();
        for (Iterator<String> it = keys.iterator(); it.hasNext();){
            cache.remove(it.next());
        }
        logger.info("Clean cache： {} => {}", cacheName, keys);
    }

    /**
     * 获得一个Cache，没有则抛出异常。
     * @param cacheName
     * @return
     */
    private static Cache<String, Object> getCache(String cacheName){
        Cache<String, Object> cache = cacheManager.getCache(cacheName);
        if (cache == null){
            throw new RuntimeException("No " + cacheName + " is defined in the current system.");
        }
        return cache;
    }
}
