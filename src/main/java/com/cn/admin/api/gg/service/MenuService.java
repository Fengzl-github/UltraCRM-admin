package com.cn.admin.api.gg.service;

import com.alibaba.fastjson.JSONArray;
import com.cn.admin.api.gg.vo.menu.MenuEditVO;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:07
 *@Desc
 **/
public interface MenuService {

    /**
     * @Author fengzhilong
     * @Desc 获取登录后菜单列表
     * @Date 2021/7/22 16:02
     * @param role
     * @return com.cn.common.vo.ResResult
     **/
    JSONArray getMenuData(Integer role);


    /**
     * @Author fengzhilong
     * @Desc 菜单管理-全部菜单数据
     * @Date 2021/7/22 16:03
     * @param
     * @return com.cn.common.vo.ResResult
     **/
    JSONArray getEditMenuData(Integer role);

    /**
     * @Author fengzhilong
     * @Desc 拖拽修改菜单
     * @Date 2021/7/23 15:32
     * @param menuEditVO
     * @return void
     **/
    void updateMenuNode(MenuEditVO menuEditVO);
}
