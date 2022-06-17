package com.cn.admin.api.gg.controller;

import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.Platform;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.cn.admin.api.gg.service.SendSmsService;
import com.cn.admin.api.gg.service.TestService;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    @GetMapping("/lock")
    public ResResult testLock(HttpServletRequest request){

        log.info("进入方法.....{}", System.currentTimeMillis());
//        testService.testLock();

        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));

        Browser browser = userAgent.getBrowser();
        Platform platform = userAgent.getPlatform();
        System.out.println(platform.getName());
        System.out.println(browser.getName());
        return ResCode.OK.msg("操作成功");
    }
}
