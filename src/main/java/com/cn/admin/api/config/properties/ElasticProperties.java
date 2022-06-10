package com.cn.admin.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author Fengzl
 * @Date 2022/6/2 14:05
 * @Desc
 */
@Data
@EnableConfigurationProperties(value = ElasticProperties.class)
@ConfigurationProperties(prefix = ElasticProperties.PREFIX)
public class ElasticProperties {

    /**
     * 配置前缀
     */
    public static final String PREFIX = "elasticsearch";


    /**
     * 连接地址
     */
    private String url;

    /**
     * 端口号
     */
    private int port = 9200;

    /**
     * 用户名
     */
    private String user = "elastic";
    /**
     * 密码
     */
    private String password;
}
