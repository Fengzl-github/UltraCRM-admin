package com.cn.admin.api.gg.service;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.dto.option.IntOption;
import com.cn.admin.api.gg.vo.group.GroupVO;
import com.cn.admin.api.gg.vo.group.UserGroupVO;
import com.cn.admin.api.util.JsonPage;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 17:31
 *@Desc
 **/
public interface GroupService {

    /**
     * @Author fengzhilong 
     * @Desc  组列表
     * @Date 2021/6/4 17:27
     * @param groupName
	 * @param pageable 
     * @return com.github.pagehelper.PageInfo<com.cn.admin.api.gg.dto.GroupDTO>
     **/
    PageInfo<GroupDTO> listGroupPage(String groupName, JsonPage pageable);
    
    /**
     * @Author fengzhilong 
     * @Desc  下拉-组信息
     * @Date 2021/6/15 16:06
     * @param  
     * @return java.util.List
     **/
    List<IntOption> listGroupInfo();

    /**
     * @Author fengzhilong
     * @Desc  获取组详情
     * @Date 2021/6/15 14:18
     * @param id
     * @return com.cn.admin.api.gg.dto.GroupDTO
     **/
    GroupDTO getGroupInfo(Integer id);

    /**
     * @Author fengzhilong 
     * @Desc  保存/修改组信息
     * @Date 2021/6/4 17:28
     * @param groupVO 
     * @return void
     **/
    void saveGroupInfo(GroupVO groupVO);

    /**
     * @Author fengzhilong 
     * @Desc  删除组
     * @Date 2021/6/4 17:29
     * @param id 
     * @return void
     **/
    void removeGroup(Integer id);

    /**
     * @Author fengzhilong
     * @Desc  组管理用户 添加或删除
     * @Date 2021/6/7 10:11
     * @param userGroupVO
     * @return com.cn.common.vo.ResResult
     **/
    void editUserFromGroup(UserGroupVO userGroupVO);


}
