package com.cn.admin.api.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2021/5/8 10:45
 *@Desc
 **/
@Slf4j
@Component
public class DruidConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){

        log.info("init Druid Monitor Servlet...");
        ServletRegistrationBean servletRegistrationBean =new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initMap = new HashMap<>();
        initMap.put("loginUsername", "admin");
        initMap.put("loginPassword", "admin");

        servletRegistrationBean.setInitParameters(initMap);
        servletRegistrationBean.setEnabled(true);

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        log.info("Druid Filter...");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(filterMap);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        filterRegistrationBean.setEnabled(true);

        return filterRegistrationBean;

    }
}
