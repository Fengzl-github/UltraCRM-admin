package com.cn.admin.api.gg.controller;

import com.cn.admin.api.gg.service.LoginService;
import com.cn.admin.api.gg.vo.login.LoginVO;
import com.cn.common.vo.ResResult;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 10:51
 *@Desc
 **/
@Api(tags = "登录模块")
@ApiSupport(author = "Fengzl", order = 1)
@RestController
@Slf4j
@RequestMapping("/login")
@Validated
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("/verification")
    public ResResult verification(@Validated @RequestBody LoginVO loginVO){

        return loginService.verification(loginVO);

    }
}
