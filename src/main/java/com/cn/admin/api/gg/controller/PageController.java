package com.cn.admin.api.gg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:04
 *@Desc
 **/
@Controller
@Slf4j
@RequestMapping("/page")
public class PageController {

    @GetMapping("/myindex")
    public String myindex() {
        return "myindex";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/userList")
    public String userList() {
        return "user/userList";
    }

    @GetMapping("/groupList")
    public String groupList() {
        return "user/groupList";
    }

    @GetMapping("/userManagement")
    public String userManagement() {
        return "user/userManagement";
    }

    @GetMapping("/menuList")
    public String menuList() {
        return "jmbar/jmbarList";
    }
}
