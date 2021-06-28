package com.cn.admin.api.base;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/5/21 10:28
 *@Desc
 **/
@Data
public class PmAgent {

    private String ghid;

    private String name;

    private String nickName;

    private String mobile;

    private Integer sex;

    private Integer age;

    private Integer userRole;

    private Integer userType;

    private String address;

    private String headUrl;

}
