package com.dong.springuseehcacheredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:cache/simple_cache_manager.xml")
public class SpringUseEhcacheRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUseEhcacheRedisApplication.class, args);
    }

}
