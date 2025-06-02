package com.askme.astronov.service;

public interface CacheService {

    void put(String cacheName, String key, Object value);
    void put(String cacheName, String key,  Object value, int expiration);
    Object get(String cacheName, String key);
    void remove(String cacheName, String key, boolean deleteAll);
    void update(String cacheName, String key, Object value);
    void update(String cacheName, String key, Object value, int expiration);
}
