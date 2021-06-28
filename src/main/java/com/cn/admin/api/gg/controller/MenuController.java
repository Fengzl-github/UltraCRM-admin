package com.cn.admin.api.gg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.admin.api.base.PmAgent;
import com.cn.admin.api.base.PmJwtToken;
import com.cn.admin.api.gg.service.MenuService;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author fengzhilong
 *@Date 2021/5/21 14:39
 *@Desc
 **/
@Slf4j
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/getMenuData")
    public ResResult myindex(HttpServletRequest request) {
        Integer role = 0;
        try {
            String strAgent = PmJwtToken.getPmAgent(request.getHeader("token"));
            PmAgent pmAgent = JSONObject.parseObject(strAgent, PmAgent.class);
            role = pmAgent.getUserRole();
            log.info("【获取用户权限】-> role:{}", role);
        } catch (Exception e) {
            log.error("【获取用户权限失败，采用默认权限值0】");
        }
        JSONArray menuData = menuService.getMenuData(role);

        return ResCode.OK.setData(menuData);
    }
}
