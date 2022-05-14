package com.cn.admin.api.gg.service;

import com.cn.admin.api.gg.dto.TestDTO;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/11 11:05
 *@Desc
 **/
public interface TestService {

    List<TestDTO> getTest();

    /**
     * 测试service层事务和锁
     */
    void testLock();
}
