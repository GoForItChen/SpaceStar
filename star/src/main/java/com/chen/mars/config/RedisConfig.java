package com.chen.mars.config;


import com.chen.mars.config.bean.KryoSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;


@Configuration
@ConditionalOnClass({RedisConnectionFactory.class})
public class RedisConfig extends CachingConfigurerSupport
{
    // 缓存管理器
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory)
    {
        return RedisCacheManager.create(redisConnectionFactory);
    }
    
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // value 值的序列化采用 Jackson2JsonRedisSerializer
        template.setValueSerializer(new KryoSerializer<>(Object.class));
        template.setHashValueSerializer(new KryoSerializer<>(Object.class));
        // key 的序列化采用 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    @Bean(name = "limitRedisTemplate")
    public RedisTemplate<String, Serializable> limitRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new KryoSerializer<>(Object.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    @Bean(name = "sessionTemplate")
    public RedisTemplate<String, Serializable> sessionTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    @Bean
    public KeyGenerator wiselyKeyGenerator()
    {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params)
            {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
