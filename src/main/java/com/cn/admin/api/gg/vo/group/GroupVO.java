package com.cn.admin.api.gg.vo.group;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 15:16
 *@Desc
 **/
@Data
public class GroupVO {

    private Integer id;
    @NotBlank(message = "组名不能为空")
    private String groupName;
}
