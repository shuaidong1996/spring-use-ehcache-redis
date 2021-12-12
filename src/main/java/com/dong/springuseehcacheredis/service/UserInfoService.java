package com.dong.springuseehcacheredis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dong
 * @Date: 2021/12/11
 */
@RestController
public class UserInfoService {
    /**
     * 默认cache（ConcurrentMapCacheFactoryBean）
     */
    @Cacheable(value = "userCache", key = "#userName", condition = "#userName.length() < 6")
    @RequestMapping("/findUserId")
    public String findUserId(String userName) {
        System.out.println("userName: " + userName + " -> run");
        return userName + ":id";
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
}