package com.cn.admin.api.gg.service.impl;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.dto.GroupMemberDTO;
import com.cn.admin.api.gg.dto.UserDTO;
import com.cn.admin.api.gg.dto.option.IntOption;
import com.cn.admin.api.gg.mapstruct.group.GroupConvertet;
import com.cn.admin.api.gg.service.GroupService;
import com.cn.admin.api.gg.vo.group.GroupVO;
import com.cn.admin.api.gg.vo.group.UserGroupVO;
import com.cn.admin.api.mapper.callthink.GroupMapper;
import com.cn.admin.api.mapper.callthink.UserMapper;
import com.cn.admin.api.util.JsonPage;
import com.cn.common.exception.FzlException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 17:31
 *@Desc
 **/
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * @Author fengzhilong
     * @Desc 组列表
     * @Date 2021/6/4 17:27
     * @param groupName
     * @param pageable
     * @return com.github.pagehelper.PageInfo<com.cn.admin.api.gg.dto.GroupDTO>
     **/
    @Override
    public PageInfo<GroupDTO> listGroupPage(String groupName, JsonPage pageable) {

        PageHelper.startPage(pageable.getPage(), pageable.getSize());
        List<GroupDTO> groupList = groupMapper.findAll(groupName);

        PageInfo<GroupDTO> page = new PageInfo<>(groupList);
        return page;
    }

    /**
     * @Author fengzhilong
     * @Desc 获取组详情
     * @Date 2021/6/15 14:18
     * @param id
     * @return com.cn.admin.api.gg.dto.GroupDTO
     **/
    @Override
    public GroupDTO getGroupInfo(Integer id) {

        return groupMapper.findById(id);
    }

    /**
     * @Author fengzhilong
     * @Desc 保存/修改组信息
     * @Date 2021/6/4 17:28
     * @param groupVO
     * @return void
     **/
    @Override
    public void saveGroupInfo(GroupVO groupVO) {
        GroupDTO groupDTO;
        if (groupVO.getId() == null) {
            groupDTO = GroupConvertet.INSTANCE.toGroupDTO(groupVO);
        } else {
            groupDTO = groupMapper.findById(groupVO.getId());
            GroupConvertet.INSTANCE.updateGroupDTO(groupVO, groupDTO);
        }
        groupMapper.insertOrUpdate(groupDTO);

    }

    /**
     * @Author fengzhilong
     * @Desc 删除组
     * @Date 2021/6/4 17:30
     * @param id
     * @return void
     **/
    @Override
    public void removeGroup(Integer id) {

        //组下有用户处理
        List<GroupMemberDTO> memberList = groupMapper.findMemberByGroupId(id);
        if (memberList.size() > 0) {
            throw new FzlException("该组下有用户，不可删除");
        }
        groupMapper.removeGroup(id);
    }

    /**
     * @Author fengzhilong
     * @Desc 组管理用户 添加或删除
     * @Date 2021/6/7 10:11
     * @param userGroupVO
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    public void editUserFromGroup(UserGroupVO userGroupVO) {
        //获取关联表信息
        GroupMemberDTO memberInfo = groupMapper.findMemberInfo(userGroupVO);
        if (memberInfo == null && userGroupVO.getFlag() == 2) {
            throw new FzlException("用户与组关联信息不存在");
        }
        if (memberInfo != null && userGroupVO.getFlag() == 1) {
            throw new FzlException("用户与组关联信息已存在");
        }

        // 获取组信息
        GroupDTO groupInfo = groupMapper.findById(userGroupVO.getGroupId());
        if (groupInfo == null) {
            throw new FzlException("组不存在");
        }
        // 获取用户信息
        UserDTO userInfo = userMapper.findByGhid(userGroupVO.getGhid());
        if (userInfo == null) {
            throw new FzlException("用户不存在");
        }

        //加入
        if (userGroupVO.getFlag() == 1) {
            memberInfo = new GroupMemberDTO(userInfo, groupInfo);
            groupMapper.insertGroupMember(memberInfo);

        } else if (userGroupVO.getFlag() == 2) { //移出
            groupMapper.removeMember(memberInfo.getId());
        }

    }

    /**
     * @Author fengzhilong
     * @Desc 下拉-组信息
     * @Date 2021/6/15 16:00
     * @param
     * @return java.util.List
     **/
    @Override
    public List<IntOption> listGroupInfo() {

        return groupMapper.findAllOptions();
    }
}
