package com.dong.springuseehcacheredis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

/**
 * @Author: dong
 * @Date: 2021/12/12
 */
@ConditionalOnProperty(prefix = "spring.cache", value = "name", havingValue = "eh")
@Configuration
@EnableCaching
public class EhCacheConfig {

//    @Bean
//    public EhCacheManagerFactoryBean getEhCacheManagerFactoryBean() throws MalformedURLException {
//
//        return ehCacheManagerFactoryBean;
//    }

    @Bean
    public CacheManager cacheManager() throws MalformedURLException {
        System.out.println("启用----EhCache");
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new UrlResource("classpath:ehcache/ehcache.xml"));
        ehCacheManagerFactoryBean.afterPropertiesSet();

        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheCacheManager;
    }
}
