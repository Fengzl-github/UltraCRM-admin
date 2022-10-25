package com.cn.admin.api.gg.dto;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:58
 *@Desc
 **/
@Data
public class MenuDTO {

    private Integer id;

    private String title;

    private String mid;

    private String pid;

    private String url;

    private String icon;

    private Integer menuSort;

    private Integer role;

    private Integer visible;
}
