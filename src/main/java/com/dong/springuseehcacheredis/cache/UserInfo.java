package com.dong.springuseehcacheredis.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dong
 * @Date: 2021/12/11
 */
@RestController("user")
public class UserInfo {
    @Cacheable(value = "userCache", key = "#userName", condition = "#userName.length() < 6")
    @RequestMapping("/findUserId")
    public String findUserId(String userName) {
        System.out.println("userName: " + userName + " -> run");
        return userName + ":id";
    }

    @CacheEvict(value = "userCache", key = "#userName")
    @RequestMapping("/delete")
    public String delete(String userName) {
        System.out.println("userName: " + userName);
        return userName;
    }

    @CacheEvict(value = "userCache", allEntries = true)
    @RequestMapping("/deleteAll")
    public String deleteAll(String userName) {
        System.out.println("userName: " + userName);
        return userName;
    }
}