package com.cn.admin.api.gg.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 11:28
 *@Desc
 **/
@Data
public class LoginVO {

    @ApiModelProperty(value = "账号",required = true)
    @NotBlank(message = "缺少参数：ghid")
    private String ghid;
    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "缺少参数：password")
    private String password;
}
