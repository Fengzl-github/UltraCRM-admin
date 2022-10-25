package com.cn.admin.api.gg.controller;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.dto.UserGroupDTO;
import com.cn.admin.api.gg.service.GroupService;
import com.cn.admin.api.gg.service.UserService;
import com.cn.admin.api.gg.vo.group.GroupVO;
import com.cn.admin.api.gg.vo.group.UserGroupVO;
import com.cn.admin.api.gg.vo.user.UserQueryVO;
import com.cn.admin.api.gg.vo.user.UserVO;
import com.cn.admin.api.util.JsonPage;
import com.cn.common.exception.FzlException;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 *@Author fengzhilong
 *@Date 2021/5/7 15:55
 *@Desc
 **/
@Slf4j
@RestController
@Validated
public class UserController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;


    /**
     * @Author fengzhilong
     * @Desc 组列表
     * @Date 2021/6/4 17:27
     * @param groupName
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listGroupPage")
    public ResResult listGroupPage(String groupName, JsonPage pageable) {

        if (pageable == null) {
            throw new FzlException("缺少分页参数");
        }

        PageInfo<GroupDTO> page = groupService.listGroupPage(groupName, pageable);

        return ResCode.OK
                .putData("content", page.getList())
                .putData("pages", page.getPages())
                .putData("total", page.getTotal());
    }

    /**
     * @Author fengzhilong
     * @Desc 组详情
     * @Date 2021/6/15 14:19
     * @param id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getGroupInfo")
    public ResResult getGroupInfo(@NotNull(message = "缺少id") Integer id) {
        GroupDTO groupDTO = groupService.getGroupInfo(id);

        return ResCode.OK.setData(groupDTO);
    }

    /**
     * @Author fengzhilong
     * @Desc 保存/修改组信息
     * @Date 2021/6/4 17:28
     * @param groupVO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveGroupInfo")
    public ResResult saveGroupInfo(@Validated @RequestBody GroupVO groupVO) {

        groupService.saveGroupInfo(groupVO);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 删除组
     * @Date 2021/6/4 17:28
     * @param id
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/removeGroup")
    public ResResult removeGroup(@NotNull(message = "缺少id") Integer id) {

        groupService.removeGroup(id);

        return ResCode.OK.msg("删除成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 组管理用户 添加或删除
     * @Date 2021/6/7 10:11
     * @param userGroupVO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/editUserFromGroup")
    public ResResult editUserFromGroup(@Valid @RequestBody UserGroupVO userGroupVO) {

        groupService.editUserFromGroup(userGroupVO);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 用户列表
     * @Date 2021/6/7 16:41
     * @param userQueryVO
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listUserPage")
    public ResResult listUserPage(@RequestBody UserQueryVO userQueryVO, @NotNull(message = "缺少分页参数") JsonPage pageable) {

        PageInfo<UserGroupDTO> page = userService.listUserPage(userQueryVO, pageable);
        return ResCode.OK
                .putData("content", page.getList())
                .putData("pages", page.getPages())
                .putData("total", page.getTotal());
    }


    /**
     * @Author fengzhilong
     * @Desc 添加修改用户
     * @Date 2021/6/7 18:00
     * @param userVO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveUserInfo")
    public ResResult saveUserInfo(@RequestBody UserVO userVO) {

        userService.saveUserInfo(userVO);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 删除用户
     * @Date 2021/7/19 14:13
     * @param ghid
     * @return void
     **/
    @PostMapping("/removeUser")
    public ResResult removeUser(String ghid) {

        userService.removeUser(ghid);

        return ResCode.OK.msg("删除成功");
    }


    //重置密码


    /**
     * 用户设置：账号和组管理，账号加入组、移出组 添加操作记录
     * 权限设置：用户权限管理
     * 菜单管理：admin菜单系统和，crm菜单系统(分库)
     *
     **/
}
