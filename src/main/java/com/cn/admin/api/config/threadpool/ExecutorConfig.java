package com.cn.admin.api.config.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Fengzl
 * @Date 2022/5/14 9:07
 * @Desc
 **/
@Slf4j
@Configuration
public class ExecutorConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor(){

        ThreadPoolTaskExecutor taskExecutor = new VisiableThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("thread-pool-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        log.info("---------线程池初始化配置---------------");
        return taskExecutor;

    }
}
