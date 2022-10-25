package com.cn.admin.api.gg.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author Fengzl
 * @Date 2022/5/14 8:54
 * @Desc
 **/
@Component
@Slf4j
public class SendSmsService {

    @Async
    public void sendMsg(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("子线程:{},发送短信[{}]",Thread.currentThread().getName(), i);
    }

}
