package com.chen.star.config.bean;

import com.chen.star.utils.RedisUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.Cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>File：RedisCache.java </p>
 * <p>Title: RedisCache </p>
 * <p>Description: RedisCache </p>
 * <p>Copyright: Copyright (c) 2014 08/15/2015 09:14</p>
 * <p>Company: Blocain</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
public class RedisCache implements Cache
{
    public static final Set<String> cacheKeys     = new HashSet<>();
    
    /** The ReadWriteLock. */
    private final ReadWriteLock     readWriteLock = new ReentrantReadWriteLock();
    
    private String                  id;
    
    public RedisCache(String id)
    {
        if (StringUtils.isBlank(id))
        { throw new IllegalArgumentException("cache id not be null"); }
        this.id = id;
        cacheKeys.add(id);
        log.info("redis-mybatis-cache-id={}", id);
    }
    
    @Override
    public String getId()
    {
        return this.id;
    }
    
    @Override
    public void putObject(Object key, Object value)
    {
        try
        {
            Map<Object, Object> maps = Maps.newHashMap();
            maps.put(String.valueOf(key.hashCode()), value);
            RedisUtils.putMap(getId(), maps);
            if (log.isDebugEnabled())
            {
                log.debug(" Mybatis Cache putObject:" + key.toString());
            }
            RedisUtils.expire(getId(), 5, TimeUnit.MINUTES);
        }
        catch (RuntimeException e)
        {
            log.error("Mybatis Cache putObject {} {}", key.toString(), e);
        }
    }
    
    @Override
    public Object getObject(Object key)
    {
        Object value = null;
        try
        {
            value = RedisUtils.hget(getId(), String.valueOf(key.hashCode()));
            if (log.isDebugEnabled())
            {
                log.debug("Mybatis Cache getObject:" + key.toString());
            }
        }
        catch (RuntimeException e)
        {
            log.error("Mybatis Cache getObject {} {}", key.toString(), e);
        }
        return value;
    }
    
    @Override
    public Object removeObject(Object key)
    {
        Object value = null;
        try
        {
            value = RedisUtils.delMap(getId(), String.valueOf(key.hashCode()));
            if (log.isDebugEnabled())
            {
                log.debug("Mybatis Cache removeObject {} ", key.toString());
            }
        }
        catch (RuntimeException e)
        {
            log.error("Mybatis Cache removeObject {}", e);
        }
        if (log.isDebugEnabled())
        {
            log.debug("Mybatis Cache removeObject:" + key.toString());
        }
        return value;
    }
    
    @Override
    public void clear()
    {
        try
        {
            RedisUtils.del(getId());
            if (log.isDebugEnabled())
            {
                log.debug("remove mybatis cache key {} ", getId());
            }
        }
        catch (RuntimeException e)
        {
            log.error("Mybatis Cache clearCache {}", e);
        }
    }
    
    @Override
    public int getSize()
    {
        int result = 0;
        try
        {
            result = RedisUtils.hSize(getId()).intValue();
            if (log.isDebugEnabled())
            {
                log.debug("Mybatis Cache getSize {}", getId());
            }
        }
        catch (RuntimeException e)
        {
            log.error("Mybatis Cache getSize  {}", e);
        }
        return result;
    }
    
    @Override
    public ReadWriteLock getReadWriteLock()
    {
        return readWriteLock;
    }
}
