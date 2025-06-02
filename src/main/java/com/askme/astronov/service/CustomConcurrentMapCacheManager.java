//package com.askme.astronov.service;
//
//import org.springframework.cache.Cache;
//import org.springframework.cache.concurrent.ConcurrentMapCache;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class CustomConcurrentMapCacheManager extends ConcurrentMapCacheManager {
//
//    private static final float LOAD_FACTOR = 0.2f;
//    private static final int INITIAL_CAPACITY = 256;
//
//    public CustomConcurrentMapCacheManager(String... cacheNames) {
//        super(cacheNames);
//    }
//
//    @Override
//    protected Cache createConcurrentMapCache(String name) {
//        return new ConcurrentMapCache(
//                name,
//                new ConcurrentHashMap<>(INITIAL_CAPACITY, LOAD_FACTOR),
//                this.isAllowNullValues()
//        );
//    }
//}
