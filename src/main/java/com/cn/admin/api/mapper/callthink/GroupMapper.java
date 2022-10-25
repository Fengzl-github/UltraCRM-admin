package com.cn.admin.api.mapper.callthink;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.dto.GroupMemberDTO;
import com.cn.admin.api.gg.dto.option.IntOption;
import com.cn.admin.api.gg.vo.group.UserGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 10:55
 *@Desc 组
 **/
@Repository
public interface GroupMapper {

    GroupDTO findById(Integer id);

    List<GroupDTO> findAll(String groupName);

    void insertOrUpdate(GroupDTO groupDTO);

    void removeGroup(Integer id);

    GroupMemberDTO findMemberInfo(UserGroupVO userGroupVO);

    void removeMember(Integer id);

    void removeMemberByGhid(String ghid);

    void insertGroupMember(GroupMemberDTO memberInfo);

    List<GroupMemberDTO> findMemberByGroupId(Integer groupId);

    List<IntOption> findAllOptions();
}
