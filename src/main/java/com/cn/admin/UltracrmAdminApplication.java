package com.cn.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.cn.admin.api")
@ServletComponentScan(basePackages = "com.cn.admin.api")
@EnableAsync
@EnableCaching
@EnableScheduling
public class UltracrmAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltracrmAdminApplication.class, args);
    }

}
