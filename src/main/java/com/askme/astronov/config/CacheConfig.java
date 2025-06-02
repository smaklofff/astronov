package com.askme.astronov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.util.List;
import java.util.Map;


@Configuration
public class CacheConfig {

    @Bean
    @Profile("dev")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    @Profile("dev")
    public RedisTemplate<String, List<Map<String, ?>>> redisCacheTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<Map<String, ?>>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

//    @Bean
//    @Profile("dev")
//    public CacheManager redisCacheManager(RedisConnectionFactory factory) {
//        return RedisCacheManager.builder(factory).build();
//    }
//
//    @Bean
//    @Profile("!dev")
//    public CacheManager simpleCacheManager() {
//        return new CustomConcurrentMapCacheManager("survey");
//    }
}
