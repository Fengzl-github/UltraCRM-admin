package com.cn.admin.api.gg.controller;

import com.cn.admin.api.gg.service.SendSmsService;
import com.cn.admin.api.gg.service.TestService;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fengzl
 * @Date 2022/3/12 14:32
 * @Desc
 **/
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class LockTestController {

    private final TestService testService;
    private final SendSmsService sendSmsService;

    @GetMapping("/thread")
    public ResResult testThread(){
        log.info("当前系统cpu数:{}",Runtime.getRuntime().availableProcessors());

        for (int i = 1; i <= 300; i++) {
            sendSmsService.sendMsg(i);
        }

        return ResCode.OK;
    }


    @PostMapping("/lock")
    public ResResult testLock(){

        log.info("进入方法.....{}", System.currentTimeMillis());
        testService.testLock();

        return ResCode.OK;
    }
}
