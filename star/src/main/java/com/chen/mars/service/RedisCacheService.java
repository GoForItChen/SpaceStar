package com.chen.mars.service;


/**
 * redis缓存 服务接口
 * <p>File：SysParameterService.java </p>
 * <p>Title: SysParameterService </p>
 * <p>Description:SysParameterService </p>
 * <p>Copyright: Copyright (c) May 26, 2020</p>
 * <p>Company: Blocain</p>
 * @author Chen
 * @version 1.0
 */
public interface RedisCacheService {

    /**
     * 清除指定缓存
     *
     * @param key
     */
    void delete(String key);

    /**
     * 清除所有缓存
     *
     */
    void cleanAll();

    /**
     * 清除Mybatis缓存
     *
     */
    void cleanMybatis();

    /**
     * 清除会话缓存
     *
     */
    void cleanSession();

    /**
     * 设置缓存
     *
     * @param key
     */
    Boolean setValue(String key, Object value);

    /**
     * 获取缓存
     *
     * @param key
     */
    Object getValue(String key);

}
