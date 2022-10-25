package com.cn.admin.api.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 *@Author fengzhilong
 *@Date 2021/5/7 15:14
 *@Desc
 **/
@Configuration
@MapperScan(basePackages = CallThinkConfig.PACKAGE, sqlSessionFactoryRef = "callSqlSessionFactory")
public class CallThinkConfig {

    static final String PACKAGE = "com.cn.admin.api.mapper.callthink";
    private static final String MAPPER_LOCATION = "classpath:mapper/callthink/*.xml";


    @Bean(name = "callDataSource")
    @ConfigurationProperties(prefix = "callthink.datasource")
    public DataSource callDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "callTransactionManager")
    public DataSourceTransactionManager callTransactionManager(){
        return new DataSourceTransactionManager(callDataSource());
    }

    @Bean(name = "callSqlSessionFactory")
    public SqlSessionFactory callSqlSessionFactory(@Qualifier("callDataSource") DataSource callDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory =new SqlSessionFactoryBean();

        sessionFactory.setDataSource(callDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(CallThinkConfig.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }

}
