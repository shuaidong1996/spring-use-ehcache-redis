package com.dong.springuseehcacheredis.config;

import com.dong.springuseehcacheredis.custom.MyCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Author: dong
 * @Date: 2021/12/12
 */
@ConditionalOnProperty(prefix = "spring.cache", value = "name", havingValue = "simple")
@Configuration
@EnableCaching
public class SimpleCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        System.out.println("启用----SimpleCache");
        //普通cache
        ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache("userCache", true);

        //自定义cache
        MyCache myCache = new MyCache();
        myCache.setName("帅东自定义cache");

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(concurrentMapCache, myCache));
        return simpleCacheManager;
    }
}
