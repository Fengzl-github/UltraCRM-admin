package com.cn.admin.api.gg.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 16:01
 *@Desc
 **/
@Data
public class UserVO {

    private Integer id;

    @NotBlank(message = "缺少ghid")
    private String ghid;
    @NotBlank(message = "缺少姓名")
    private String name;

    private String nickName;
    @NotBlank(message = "缺少手机号")
    private String mobile;

    private Integer sex;

    private Integer age;
    @NotNull(message = "缺少用户权限")
    private Integer userRole;
    @NotNull(message = "缺少用户类型")
    private Integer userType;

    private String address;

    private String headUrl;

    private String password;

    private String provinceCode;

    private String province;

    private String cityCode;

    private String city;

    private String countyCode;

    private String county;

}
