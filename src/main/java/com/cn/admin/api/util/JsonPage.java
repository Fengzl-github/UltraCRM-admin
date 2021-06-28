package com.cn.admin.api.util;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 14:26
 *@Desc
 **/
@Data
public class JsonPage {

    private Integer page =1;

    private Integer size = 15;

}
