package com.askme.astronov.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ConditionalOnMissingBean(RedisCacheServiceImpl.class)
public class LocalCacheServiceImpl implements CacheService {

    private static final float LOAD_FACTOR = 0.2f;
    private static final int INITIAL_CAPACITY = 256;
    private static final int MAX_SIZE = 2048;
    private static final int DEFAULT_EXPIRATION = 60;
    private final ConcurrentHashMap<String, Node> cache = new ConcurrentHashMap<>(INITIAL_CAPACITY, LOAD_FACTOR);

    @Override
    public void put(String cacheName, String key, Object value) {
        put(cacheName, key, value, DEFAULT_EXPIRATION);
    }

    @Override
    public void put(String cacheName, String key, Object value, int expiration) {
        clearExpiredRecords();

        if (cache.size() > MAX_SIZE) {
            cache.clear();
        }
        if (cacheName == null || key == null) return;
        cache.putIfAbsent(cacheName + ":" + key, new Node(LocalDateTime.now().plusMinutes(expiration), value));
    }

    @Override
    public Object get(String cacheName, String key) {
        clearExpiredRecords();

        if (cacheName == null || key == null) return null;
        Node result = cache.get(cacheName + ":" + key);
        return result != null? result.getValue() : null;
    }

    @Override
    public void remove(String cacheName, String key, boolean deleteAll) {
        clearExpiredRecords();

        if (deleteAll) {
            cache.clear();
            return;
        }
        if (cacheName == null || key == null) return;
        cache.remove(cacheName + ":" + key);
    }

    @Override
    public void update(String cacheName, String key, Object value) {
        update(cacheName, key, value, DEFAULT_EXPIRATION);
    }

    @Override
    public void update(String cacheName, String key, Object value, int expiration) {
        clearExpiredRecords();

        if (cacheName == null || key == null) return;
        cache.put(cacheName + ":" + key, new Node(LocalDateTime.now().plusMinutes(expiration), value));
    }

    private void clearExpiredRecords() {
        cache.entrySet().stream()
                .filter(entry -> entry.getValue().getExpiration().isBefore(LocalDateTime.now()))
                .map(Map.Entry::getKey)
                .forEach(cache::remove);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class Node {
        private LocalDateTime expiration;
        private Object value;
    }
}
