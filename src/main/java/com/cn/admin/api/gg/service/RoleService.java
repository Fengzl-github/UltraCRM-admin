package com.cn.admin.api.gg.service;

import com.cn.admin.api.gg.dto.option.IntOption;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/7/28 14:25
 *@Desc
 **/
public interface RoleService {

    /**
     * @Author fengzhilong
     * @Desc  下拉-权限信息
     * @Date 2021/6/15 16:06
     * @param
     * @return java.util.List
     **/
    List<IntOption> listRoleInfo();
}
