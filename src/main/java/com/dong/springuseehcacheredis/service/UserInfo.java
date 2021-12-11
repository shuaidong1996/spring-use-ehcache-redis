package com.dong.springuseehcacheredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: dong
 * @Date: 2021/12/11
 */
@RestController("user")
public class UserInfo {
    @Autowired
    private SimpleCacheManager simpleCacheManager;

    /**
     * 默认cache（ConcurrentMapCacheFactoryBean）
     */
    @Cacheable(value = "userCache", key = "#userName", condition = "#userName.length() < 6")
    @RequestMapping("/findUserId")
    public String findUserId(String userName) {
        System.out.println("userName: " + userName + " -> run");
        return userName + ":id";
    }

    @RequestMapping("/getCache")
    public String getCache(String key) {
        Cache cache = simpleCacheManager.getCache("userCache");
        String res = (String) Optional.ofNullable(cache.get(key)).orElse(() -> "null").get();
        System.out.println("getCache key:" + key + ",val:" + res);
        return res;
    }

    /**
     * 自定义cache
     */
    @Cacheable(value = "帅东自定义cache", key = "#userName", condition = "#userName.length() < 6")
    @RequestMapping("/findUserIdBySelf")
    public String findUserIdBySelf(String userName) {
        System.out.println("userName: " + userName + " -> run");
        return userName + ":id";
    }

    @RequestMapping("/getCacheBySelf")
    public String getCacheBySelf(String key) {
        Cache cache = simpleCacheManager.getCache("帅东自定义cache");
        String res = (String) Optional.ofNullable(cache.get(key)).orElse(() -> "null").get();
        System.out.println("getCacheBySelf key:" + key + ",val:" + res);
        return res;
    }
}