package com.dloxu.springboot_news;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.dloxu.springboot_news.dao")
@EnableTransactionManagement
@EnableCaching
public class SpringbootNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNewsApplication.class, args);
    }

}
