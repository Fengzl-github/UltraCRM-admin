package com.cn.admin.api.gg.vo.group;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 17:32
 *@Desc
 **/
@Data
public class UserGroupVO {

    @NotBlank(message = "缺少ghid")
    private String ghid;
    @NotNull(message = "缺少groupId")
    private Integer groupId;

    @NotNull(message = "缺少flag参数")
    @Min(value = 1, message = "flag参数错误")
    @Max(value = 2, message = "flag参数错误")
    private Integer flag;
}
