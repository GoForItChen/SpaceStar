package com.chen.star.utils;

import com.chen.star.config.bean.SpringContext;
import com.chen.star.config.consts.CacheConst;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * REDIS工具类
 * <p>File: RedisUtils.java </p>
 * <p>Title: RedisUtils </p>
 * <p>Description: RedisUtils </p>
 * <p>Copyright: Copyright (c) 2018/11/6</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class RedisUtils
{
    private static final RedisTemplate redisTemplate = SpringContext.getBean("redisTemplate");
    
    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key)
    {
        if (StringUtils.isBlank(key))
        { return null; }
        String value = null;
        try
        {
            return (String) redisTemplate.opsForValue().get(key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static Object getObject(String key)
    {
        if (StringUtils.isBlank(key))
        { return null; }
        Object value = null;
        try
        {
            return redisTemplate.opsForValue().get(key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 缓存对象
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static Boolean putObject(String key, Object value)
    {
        Boolean result = false;
        try
        {
            result = redisTemplate.opsForValue().setIfAbsent(key, value);
        }
        catch (Exception e)
        {
            log.warn("putObject {} = {} ERROR:{}", key, value.toString(), e);
        }
        return result;
    }
    
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key          缓存的键值
     * @param value        缓存的值
     * @param cacheSeconds 过期时间
     */
    public static void putObject(String key, Object value, int cacheSeconds)
    {
        try
        {
            redisTemplate.opsForValue().set(key, value, cacheSeconds, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            log.warn("putObject {} = {} ERROR:{}", key, value.toString(), e);
        }
    }
    
    /**
     * 消息订阅/发送消息
     *
     * @param channel 通道
     * @param message 消息
     */
    public static void sendMessage(String channel, String message)
    {
        try
        {
            redisTemplate.convertAndSend(channel, message);
        }
        catch (Exception e)
        {
            log.warn("send  {} : {}  ERROR:{}", channel, message, e);
        }
    }
    
    /**
     * 批量把一个数组插入到列表中
     * <p>队列操作</p>
     *
     * @param key   通道
     * @param value 消息
     */
    public static Long leftPush(String key, Object ... value)
    {
        Long count = null;
        try
        {
            count = redisTemplate.opsForList().leftPushAll(key, value);
        }
        catch (Exception e)
        {
            log.warn("send  {} : {}  ERROR:{}", key, value, e);
        }
        return count;
    }
    
    /**
     * 批量把一个集合插入到列表中
     *
     * @param key   通道
     *              <p>队列操作</p>
     * @param value 消息
     */
    public static Long leftPush(String key, List<Object> value)
    {
        Long count = null;
        try
        {
            count = redisTemplate.opsForList().leftPushAll(key, value);
        }
        catch (Exception e)
        {
            log.warn("leftPush  {} : {}  ERROR:{}", key, value, e);
        }
        return count;
    }
    
    /**
     * 取队列长度
     * <p>队列操作</p>
     *
     * @param key 通道
     */
    public static Long listSize(String key)
    {
        Long count = null;
        try
        {
            count = redisTemplate.opsForList().size(key);
        }
        catch (Exception e)
        {
            log.warn("listSize  {} : {}  ERROR:{}", key, count, e);
        }
        return count;
    }
    
    /**
     * 移出队列中指定的元素
     * <p>队列操作</p>
     *
     * @param key 通道
     */
    public static Long removeQueue(String key, Object value)
    {
        Long count = null;
        try
        {
            count = redisTemplate.opsForList().remove(key, -1, value);
        }
        catch (Exception e)
        {
            log.warn("remove  {} : {}  ERROR:{}", key, count, e);
        }
        return count;
    }
    
    /**
     * 取队列元素
     * <p>队列操作</p>
     *
     * @param key  通道
     * @param size 大小
     */
    public static List<Object> lrange(String key, long size)
    {
        List<Object> list = null;
        try
        {
            list = redisTemplate.opsForList().range(key, 0, size);
        }
        catch (Exception e)
        {
            log.warn("lrange  {} : {}  ERROR:{}", key, size, e);
        }
        return list;
    }
    
    /**
     * 移出并获取队列的最后一个元素
     * <p>队列操作</p>
     *
     * @param key 通道
     */
    public static Object rightPop(String key)
    {
        Object value = null;
        try
        {
            if (null == redisTemplate)
            {
                Thread.sleep(CacheConst.DEFAULT_INTERVAL);
            }
            value = redisTemplate.opsForList().rightPop(key);
        }
        catch (Exception e)
        {
            log.warn("rightPop  {}  ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 移出并获取队列的最后一个元素
     * <p>每从sourceKey取出一个消息之后同步会将消息同步到targetKey</p>
     *
     * @param sourceKey 通道
     * @param targetKey 通道
     */
    public static Object rightPopAndLeftPush(String sourceKey, String targetKey)
    {
        Object value = null;
        try
        {
            if (null == redisTemplate)
            {
                Thread.sleep(CacheConst.DEFAULT_INTERVAL);
            }
            value = redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, targetKey, 3, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            log.warn("rightPopAndLeftPush  {} : {}  ERROR:{}", sourceKey, targetKey, e.getMessage());
        }
        return value;
    }
    
    /**
     * 移出SET中的对象
     *
     * @param key
     * @param values
     * @return
     */
    public static Long setRemove(String key, Object ... values)
    {
        Long count = null;
        try
        {
            count = redisTemplate.opsForZSet().remove(key, values);
        }
        catch (Exception e)
        {
            log.warn("expire {}  ERROR:{}", key, e);
        }
        return count;
    }
    
    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @param unit
     * @return
     */
    public static boolean expire(String key, long time, TimeUnit unit)
    {
        boolean flag = false;
        try
        {
            flag = redisTemplate.expire(key, time, unit);
        }
        catch (Exception e)
        {
            log.warn("expire {}  ERROR:{}", key, e);
        }
        return flag;
    }
    
    /**
     * 从Map中删除指定的存储
     *
     * @param key
     * @param hashKey
     * @return 状态码，1成功，0失败
     */
    public static Long delMap(String key, Object hashKey)
    {
        Long flag = null;
        try
        {
            flag = redisTemplate.opsForHash().delete(key, hashKey);
        }
        catch (Exception e)
        {
            log.warn("delMap {} ERROR:{}", key, e);
        }
        return flag;
    }
    
    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public static Map<String, Object> getMap(String key)
    {
        Map<String, Object> map = Maps.newHashMap();
        try
        {
            map = redisTemplate.opsForHash().entries(key);
        }
        catch (Exception e)
        {
            log.warn("getMap {} ERROR:{}", key, e);
        }
        return map;
    }
    
    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public static void putMap(String key, Map<Object, Object> dataMap)
    {
        try
        {
            if (null != dataMap)
            {
                HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
                if (operations != null)
                {
                    operations.putAll(key, dataMap);
                }
            }
        }
        catch (Exception e)
        {
            log.warn("putMap {} = {} ERROR:{}", key, dataMap.hashCode(), e);
        }
    }
    
    /**
     * 获取Map中指定的Key
     *
     * @param cacheKey 缓存Key
     * @param key      hashKey
     * @return 值
     */
    public static Object hget(String cacheKey, Object key)
    {
        if (null == key)
        { return null; }
        Object value = null;
        try
        {
            return redisTemplate.opsForHash().get(cacheKey, key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * map长度
     *
     * @param key
     * @return
     */
    public static Long hSize(String key)
    {
        Long value = null;
        try
        {
            value = redisTemplate.opsForHash().size(key);
        }
        catch (Exception e)
        {
            log.warn("getSize {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 删除缓存
     *
     * @param key 键
     * @return 值
     */
    public static boolean hasKey(String key)
    {
        boolean flag = false;
        try
        {
            flag = redisTemplate.hasKey(key);
        }
        catch (Exception e)
        {
            log.warn("del {} ERROR:{}", key, e);
        }
        return flag;
    }
    
    /**
    * 删除缓存
    *
    * @param key 键
    * @return 值
    */
    public static void del(String key)
    {
        if (StringUtils.isBlank(key))
        { return; }
        try
        {
            redisTemplate.delete(key);
        }
        catch (Exception e)
        {
            log.warn("del {} ERROR:{}", key, e);
        }
    }
    
    /**
     * 删除所有的key
     * 1⃣ * 删除所有 ; 2⃣ new StringBuffer("str").append("*").toString 根据前缀模糊匹配
     *
     * @param pattern 规则
     */
    public static void delkeys(String pattern)
    {
        if (StringUtils.isBlank(pattern))
        { return; }
        try
        {
            Set<String> keySet = redisTemplate.keys(pattern);
            if (keySet.isEmpty())
            { return; }
            redisTemplate.delete(keySet);
        }
        catch (Exception e)
        {
            log.warn("del {} ERROR:{}", pattern, e);
        }
    }
}
