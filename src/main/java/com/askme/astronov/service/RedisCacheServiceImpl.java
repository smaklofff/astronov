package com.askme.astronov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final int DEFAULT_EXPIRATION = 20;

    @Override
    public void put(String cacheName, String key, Object value) {
        put(cacheName, key, value, DEFAULT_EXPIRATION);
    }

    @Override
    public void put(String cacheName, String key, Object value, int expiration) {
        redisTemplate.opsForValue().setIfAbsent(cacheName + ":" + key, value, Duration.ofMinutes(expiration));
    }

    @Override
    public void update(String cacheName, String key, Object value) {
        update(cacheName, key, value, DEFAULT_EXPIRATION);
    }

    @Override
    public void update(String cacheName, String key, Object value, int expiration) {
        if (cacheName == null || key == null) return;
        redisTemplate.opsForValue().set(cacheName + ":" + key, value);
    }

    @Override
    public Object get(String cacheName, String key) {
        return cacheName != null && key != null? redisTemplate.opsForValue().get(cacheName + ":" + key): null;
    }

    @Override
    public void remove(String cacheName, String key, boolean deleteAll) {
        if (deleteAll) {
            redisTemplate.delete("*");
            return;
        }
        if (cacheName == null || key == null) return;
        redisTemplate.delete(cacheName + ":" + key);
    }
}
