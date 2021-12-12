package com.dong.springuseehcacheredis.test;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: dong
 * @Date: 2021/12/12
 */
@RestController
public class CacheTest {
    @Resource
    private CacheManager cacheManager;

    @RequestMapping("/getCache")
    public String getCache(String key) {
        Cache cache = cacheManager.getCache("userCache");
        String res = (String) Optional.ofNullable(cache.get(key)).orElse(() -> "null").get();
        System.out.println("getCache key:" + key + ",val:" + res);
        return res;
    }

    @RequestMapping("/getCacheBySelf")
    public String getCacheBySelf(String key) {
        Cache cache = cacheManager.getCache("帅东自定义cache");
        String res = (String) Optional.ofNullable(cache.get(key)).orElse(() -> "null").get();
        System.out.println("getCacheBySelf key:" + key + ",val:" + res);
        return res;
    }

    @CacheEvict(value="userCache", allEntries=true)
    @RequestMapping("/deleteAll")
    public void deleteAll() {
        System.out.println("deleteAll");

    }
}
