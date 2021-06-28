package com.cn.admin.api.gg.dto;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 16:01
 *@Desc
 **/
@Data
public class UserDTO {
    private Integer id;

    private String createTime;

    private String updateTime;

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

    private String password;

    private Integer isDel;

    private String provinceCode;

    private String province;

    private String cityCode;

    private String city;

    private String countyCode;

    private String county;

}
