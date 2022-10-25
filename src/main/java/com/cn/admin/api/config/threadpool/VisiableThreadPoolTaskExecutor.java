package com.cn.admin.api.config.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Fengzl
 * @Date 2022/5/14 9:29
 * @Desc
 **/
@Slf4j
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor poolExecutor = getThreadPoolExecutor();

        log.info("{}, {}, 线程总数[{}], 已完成数:[{}], 活跃线程数[{}], 队列大小[{}]",
                this.getThreadNamePrefix(),
                prefix,
                poolExecutor.getPoolSize(),
                poolExecutor.getCompletedTaskCount(),
                poolExecutor.getActiveCount(),
                poolExecutor.getQueue().size());
    }


    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1. do executor");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do executor");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("1. do submitListenable");
        return super.submitListenable(task);
    }
}
