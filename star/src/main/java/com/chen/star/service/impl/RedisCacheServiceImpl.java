package com.chen.star.service.impl;

import com.chen.star.config.bean.RedisCache;
import com.chen.star.config.consts.CacheConst;
import com.chen.star.service.RedisCacheService;
import com.chen.star.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cleanAll() {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            log.info("redis cleanAll ok");
            return "ok";
        });
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cleanMybatis() {
        for (String key : RedisCache.cacheKeys) {
            redisTemplate.delete(key);
            log.info("mybatis cache remove key=" + key);
        }
        log.info("cleanMybatis ok");
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cleanSession() {
        try {
            Set<String> keys = redisTemplate.keys(CacheConst.BOSS_SHIRO_CACHE_PREFIX + "*");
            for (String key : keys) {
                redisTemplate.delete(key);
            }
            log.info("cleanSession boss OK");
            keys = redisTemplate.keys(CacheConst.WEB_SHIRO_CACHE_PREFIX + "*");
            for (String key : keys) {
                redisTemplate.delete(key);
            }
            log.info("cleanSession web OK");
        } catch (Exception e) {
            log.error("cleanSession:{}", e.getMessage());
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean setValue(String key, Object value) {
        return RedisUtils.putObject(key, value);
    }

    @Override
    public Object getValue(String key) {
        return RedisUtils.getObject(key);
    }
}
