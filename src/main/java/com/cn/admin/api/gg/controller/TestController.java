package com.cn.admin.api.gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author fengzhilong
 *@Date 2021/6/1 10:37
 *@Desc
 **/
@Controller
@RequestMapping("/page")
public class TestController {

    @GetMapping("/mapList")
    public String mapList(){
        return "test/mapList";
    }
    @GetMapping("/websocket")
    public String websocket(){
        return "test/websocket";
    }
    @GetMapping("/area")
    public String area(){
        return "test/area";
    }
}
