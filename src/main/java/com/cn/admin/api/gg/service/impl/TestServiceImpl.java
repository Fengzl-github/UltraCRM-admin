package com.cn.admin.api.gg.service.impl;

import com.cn.admin.api.gg.dto.TestDTO;
import com.cn.admin.api.gg.service.TestService;
import com.cn.admin.api.mapper.crm.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/11 11:06
 *@Desc
 **/
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<TestDTO> getTest() {

        return testMapper.findAllData();
    }
}
