package com.cn.admin.api.config.knife4j;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author Fengzl
 * @Date 2022/5/23 9:21
 * @Desc
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 引入knife4j提供的扩展类
     */
    private  OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }


    @Bean("defaultApi2")
    public Docket defaultApi2() {

        String groupName = "Admin接口";

        log.info("------------------ Knife4j 配置 --------------------");
        log.info("------------------ Knife4j 接口文档访问路径: ip:port/doc.html --------------------");

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Admin 接口文档")
                        .description("# UltraCRM-admin APIs")
                        .termsOfServiceUrl("http://127.0.0.1/")
                        .contact(new Contact("冯志龙","", ""))
                        .version("1.0")
                        .build())
                .groupName(groupName)
                .select()
                // 指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.cn.admin.api.gg.controller"))
                .paths(PathSelectors.any())
                .build()
                // 赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions(groupName));

        return docket;
    }
}
