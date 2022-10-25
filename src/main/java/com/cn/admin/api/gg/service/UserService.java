package com.cn.admin.api.gg.service;

import com.cn.admin.api.gg.dto.UserGroupDTO;
import com.cn.admin.api.gg.vo.user.UserQueryVO;
import com.cn.admin.api.gg.vo.user.UserVO;
import com.cn.admin.api.util.JsonPage;
import com.github.pagehelper.PageInfo;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 17:31
 *@Desc
 **/
public interface UserService {

    /**
     * @Author fengzhilong
     * @Desc  用户列表
     * @Date 2021/6/7 16:41
     * @param userQueryVO
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    PageInfo<UserGroupDTO> listUserPage(UserQueryVO userQueryVO, JsonPage pageable);

    /**
     * @Author fengzhilong
     * @Desc  添加修改用户
     * @Date 2021/6/7 18:00
     * @param userVO
     * @return com.cn.common.vo.ResResult
     **/
    void saveUserInfo(UserVO userVO);

    /**
     * @Author fengzhilong 
     * @Desc 删除用户
     * @Date 2021/7/19 14:13
     * @param id 
     * @return void
     **/
    void removeUser(String id);
}
