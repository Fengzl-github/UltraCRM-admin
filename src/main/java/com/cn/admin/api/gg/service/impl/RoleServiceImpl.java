package com.cn.admin.api.gg.service.impl;

import com.cn.admin.api.gg.dto.option.IntOption;
import com.cn.admin.api.gg.service.RoleService;
import com.cn.admin.api.mapper.callthink.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/7/28 14:26
 *@Desc
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    /**
     * @Author fengzhilong
     * @Desc 下拉-权限信息
     * @Date 2021/7/28 14:29
     * @param
     * @return java.util.List<com.cn.admin.api.gg.dto.option.IntOption>
     **/
    @Override
    public List<IntOption> listRoleInfo() {

        return roleMapper.findAllOptions();
    }
}
