package com.cn.admin.api.gg.service;

import com.alibaba.fastjson.JSONArray;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:07
 *@Desc
 **/
public interface MenuService {

    JSONArray getMenuData(Integer role);
}
