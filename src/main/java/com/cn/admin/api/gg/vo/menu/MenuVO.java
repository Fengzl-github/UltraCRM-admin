package com.cn.admin.api.gg.vo.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/8/3 11:41
 *@Desc
 **/
@Data
public class MenuVO {
    @NotBlank(message = "mid不能为空")
    private String mid;
    @NotBlank(message = "title不能为空")
    private String title;

    private String url;

    private String icon;
    @NotNull(message = "role不能为空")
    private Integer role;

    private Integer visible;
}
