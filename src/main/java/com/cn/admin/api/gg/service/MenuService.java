package com.cn.admin.api.gg.service;

import com.alibaba.fastjson.JSONArray;
import com.cn.admin.api.gg.vo.menu.MenuEditVO;
import com.cn.admin.api.gg.vo.menu.MenuVO;

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
     * @Desc  
     * @Date 2021/7/23 15:32
     * @param menuEditVO
     * @return void
     **/
    void updateMenuNode(MenuEditVO menuEditVO);

    /**
     * @Author fengzhilong
     * @Desc 编辑保存菜单
     * @Date 2021/8/3 11:52
     * @param menuVO
     * @return com.cn.common.vo.ResResult
     **/
    void saveMenu(MenuVO menuVO);
}
