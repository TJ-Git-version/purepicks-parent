package com.devsurfer.purepicks.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:38
 * description redis配置
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis连接池工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置key序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value序列化方式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置String序列化方式
        redisTemplate.setStringSerializer(new StringRedisSerializer());
        // 设置hash序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置默认序列化方式
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}

