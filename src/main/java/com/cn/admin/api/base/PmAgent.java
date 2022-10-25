package com.cn.admin.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/5/21 10:28
 *@Desc
 **/
@Data
@ApiModel(value = "用户对象")
public class PmAgent {

    @ApiModelProperty(value = "账号")
    private String ghid;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "性别", notes = "1-男,2-女")
    private Integer sex;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "角色")
    private Integer userRole;
    @ApiModelProperty(value = "类型")
    private Integer userType;
    @ApiModelProperty(value = "住址")
    private String address;
    @ApiModelProperty(value = "头像地址")
    private String headUrl;

}
