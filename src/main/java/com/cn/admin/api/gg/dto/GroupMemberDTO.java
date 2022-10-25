package com.cn.admin.api.gg.dto;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 17:58
 *@Desc
 **/
@Data
public class GroupMemberDTO {

    private Integer id;

    private String ghid;

    private String name;

    private Integer groupId;

    private String groupName;


    public GroupMemberDTO(UserDTO userDTO, GroupDTO groupDTO) {
        if (userDTO != null) {
            this.ghid = userDTO.getGhid();
            this.name = userDTO.getName();
        }
        if (groupDTO != null) {
            this.groupId = groupDTO.getId();
            this.groupName = groupDTO.getGroupName();
        }
    }

    public GroupMemberDTO() {
    }
}
