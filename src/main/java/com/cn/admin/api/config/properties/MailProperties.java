package com.cn.admin.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author Fengzl
 * @Date 2022/2/1010:23
 * @Desc 邮件相关配置 (自定义配置示例)
 */
@Data
@EnableConfigurationProperties(value = MailProperties.class)
@ConfigurationProperties(value = MailProperties.PREFIX)
public class MailProperties {

    /**
     * 配置前缀
     */
    public static final String PREFIX = "mail";


    /**
     * 邮箱地址
     */
    private String username;

    /**
     * 密码
     */
    private String password;



}
