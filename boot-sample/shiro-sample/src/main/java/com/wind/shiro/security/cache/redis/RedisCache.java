package com.wind.shiro.security.cache.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisCache
 * @Package com.wind.shiro.security.cache.redis
 * @Description: Shiro缓存Redis实现类
 * @author wind
 * @date 2018/10/17 9:58
 * @version V1.0
 */
public class RedisCache<K, V> implements Cache<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    /**
     * redis实例
     */
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 过期时间， -1表示永不过期
     */
    private int expire = 0;

    /**
     * 缓存key前缀
     */
    private String cacheKeyName;


    public RedisCache(String cacheKeyName, RedisTemplate<String, Object> redisTemplate, int expire) {
        this.cacheKeyName = cacheKeyName;
        this.redisTemplate = redisTemplate;
        if (expire != -1) {
            this.expire = expire;
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("remove {} from the redis", cacheKeyName);
        redisTemplate.delete(cacheKeyName);
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("get objects from Redis. key is {}, hashKey is {}", cacheKeyName, key);

        if (key == null){
            return null;
        }

        return (V) redisTemplate.opsForHash().get(cacheKeyName, key.toString());
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<>();
        try {
            Set<Object> set = redisTemplate.opsForHash().keys(cacheKeyName);

            for(Object key : set){
                if (key != null){
                    keys.add((K) key);
                }
            }
            logger.debug("returns all fields. key is {}, fields is {}", cacheKeyName, keys);
            return keys;
        } catch (Exception e) {
            logger.warn("get all fields fail. key is {}", cacheKeyName, e);
        }

        return keys;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (key == null){
            return null;
        }

        try {
            redisTemplate.opsForHash().put(cacheKeyName, key.toString(), value);
            if (expire != -1) {
                redisTemplate.expire(cacheKeyName, expire, TimeUnit.SECONDS);
            }
            logger.debug("put field. key is {}, field is {}, value is {}", cacheKeyName, key, value);
        } catch (Exception e) {
            logger.warn("put field failed, key is {}, field is {}", cacheKeyName, key, e);
        }

        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V value = null;

        if (key == null) {
            return null;
        }

        try {
            value = (V) redisTemplate.opsForHash().get(cacheKeyName, key.toString());
            redisTemplate.opsForHash().delete(cacheKeyName, key.toString());
            logger.debug("remove field. key is {}, field is {}", cacheKeyName, key);
        } catch (Exception e) {
            logger.warn("remove {} {}", cacheKeyName, key, e);
        }

        return value;
    }

    @Override
    public int size() {
        int size = 0;
        try {
            size = redisTemplate.opsForHash().size(cacheKeyName).intValue();
            logger.debug("get cache length. key is {}, size is {}", cacheKeyName, size);
        } catch (Exception e) {
            logger.warn("get cache length failed. key is {}",  cacheKeyName, e);
        }

        return size;
    }


    @Override
    public Collection<V> values() {
        Collection<V> list = Collections.emptyList();

        try {
            List<Object> values = redisTemplate.opsForHash().values(cacheKeyName);
            for (Object obj : values) {
                list.add((V)obj);
            }
            logger.debug("get cache values. key is {}, values is {} ", cacheKeyName, list);
        } catch (Exception e) {
            logger.error("get cache values failed. key is {}", cacheKeyName, e);
        }

        return list;
    }

}
