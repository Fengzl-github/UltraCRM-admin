package com.cn.admin.api.gg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.admin.api.base.PmAgent;
import com.cn.admin.api.base.PmJwtToken;
import com.cn.admin.api.gg.service.MenuService;
import com.cn.admin.api.gg.vo.menu.MenuEditVO;
import com.cn.admin.api.gg.vo.menu.MenuVO;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    /**
     * @Author fengzhilong
     * @Desc 获取登录后菜单列表
     * @Date 2021/7/22 16:02
     * @param request
     * @return com.cn.common.vo.ResResult
     **/
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

    /**
     * @Author fengzhilong
     * @Desc 菜单管理-全部菜单数据
     * @Date 2021/7/22 16:03
     * @param
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getEditMenuData")
    public ResResult getEditMenuData(Integer role){

        JSONArray json = menuService.getEditMenuData(role);
        
        return ResCode.OK.setData(json);
    }


    /**
     * @Author fengzhilong 
     * @Desc 拖拽修改菜单
     * @Date 2021/7/26 17:07
     * @param menuEditVO 
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/updateMenuNode")
    public ResResult updateMenuNode(@Valid @RequestBody MenuEditVO menuEditVO){

        menuService.updateMenuNode(menuEditVO);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 编辑保存菜单
     * @Date 2021/8/3 11:52
     * @param menuVO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveMenu")
    public ResResult saveMenu(@Valid @RequestBody MenuVO menuVO){

        menuService.saveMenu(menuVO);

        return ResCode.OK.msg("操作成功");
    }
}
