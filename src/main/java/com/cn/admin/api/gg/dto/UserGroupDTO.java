package com.cn.admin.api.gg.dto;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/6/7 16:04
 *@Desc
 **/
@Data
public class UserGroupDTO {
    /**用户id**/
    private Integer id;
    /**创建时间**/
    private String createTime;
    /**ghid**/
    private String ghid;
    /**姓名**/
    private String name;
    /**手机号**/
    private String mobile;
    /**性别 0-男，1-女，2-不详**/
    private Integer sex;
    /**年龄**/
    private Integer age;
    /**地址**/
    private String address;
    /**头像地址**/
    private String headUrl;
    /**是否删除 0-正常，1-删除**/
    private Integer isDel;
    /**用户类型 0-普通用户，1-VIP用户**/
    private Integer userType;
    /**昵称**/
    private String nickName;
    /**用户权限 **/
    private Integer userRole;
    /**组id**/
    private Integer groupId;
    /**组名称**/
    private String groupName;

    private String provinceCode;

    private String province;

    private String cityCode;

    private String city;

    private String countyCode;

    private String county;

}
