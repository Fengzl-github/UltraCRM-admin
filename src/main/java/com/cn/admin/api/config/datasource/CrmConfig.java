package com.cn.admin.api.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *@Author fengzhilong
 *@Date 2021/5/8 11:38
 *@Desc
 **/
@Configuration
@MapperScan(basePackages = CrmConfig.PACKAGE, sqlSessionFactoryRef = "crmSqlSessionFactory")
public class CrmConfig {

    static final String PACKAGE = "com.cn.admin.api.mapper.crm";
    private static final String MAPPER_LOCATION = "classpath:mapper/crm/*.xml";

    @Bean(name = "crmDataSource")
    @ConfigurationProperties(prefix = "crm.datasource")
    @Primary
    public DataSource crmDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "crmTransactionManager")
    @Primary
    public DataSourceTransactionManager crmTransactionManager() {
        return new DataSourceTransactionManager(crmDataSource());
    }

    @Bean(name = "crmSqlSessionFactory")
    @Primary
    public SqlSessionFactory crmSqlSessionFactory(@Qualifier("crmDataSource") DataSource crmDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(crmDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(CrmConfig.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }

}
