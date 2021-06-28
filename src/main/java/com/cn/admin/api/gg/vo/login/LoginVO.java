package com.cn.admin.api.gg.vo.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 11:28
 *@Desc
 **/
@Data
public class LoginVO {

    @NotBlank(message = "缺少参数：ghid")
    private String ghid;
    @NotBlank(message = "缺少参数：password")
    private String password;
}
