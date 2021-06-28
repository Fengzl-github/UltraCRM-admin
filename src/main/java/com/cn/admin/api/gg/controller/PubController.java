package com.cn.admin.api.gg.controller;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.service.GroupService;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/6/15 15:56
 *@Desc 公共使用
 **/
@RestController
@RequestMapping("/pub")
public class PubController {

    @Autowired
    private GroupService groupService;


    //获取下拉-组信息
    @PostMapping("/getGroupOptions")
    public ResResult getGroupOptions() {

        List<GroupDTO> list = groupService.listGroupInfo();

        return ResCode.OK.setData(list);
    }
}
