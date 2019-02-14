package com.g7go.redis.common.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * Redis缓存配置类
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 13:43$
 * @Version: 1.0
 */

@Configuration
public class RedisConfig {


    /**
     * @param redisConnectionFactory
     * @return 自定义redisTemplate，自带的bean没有序列化器
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值的序列化器
        redisTemplate.setValueSerializer(new RedisConverter());
        return redisTemplate;
    }

    /**
     * key生成策略
     *
     * @return
     */
    @Bean
    public KeyGenerator accountKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //first parameter is caching object
                //second paramter is the name of the method, we like the caching key has nothing to do with method name
                //third parameter is the list of parameters in the method being called
                return target.getClass().toString() + "accountId:" + params[0].toString();
            }
        };
    }
}