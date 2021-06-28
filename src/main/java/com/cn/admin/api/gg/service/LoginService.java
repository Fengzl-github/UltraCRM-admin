package com.cn.admin.api.gg.service;

import com.cn.admin.api.gg.vo.login.LoginVO;
import com.cn.common.vo.ResResult;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 11:39
 *@Desc
 **/
public interface LoginService {

    ResResult verification(LoginVO loginVO);
}
