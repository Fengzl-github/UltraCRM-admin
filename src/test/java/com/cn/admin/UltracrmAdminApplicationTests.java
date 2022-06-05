package com.cn.admin;


import com.cn.admin.api.gg.service.impl.ElasticServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 15:27
 *@Desc
 **/
@RunWith(SpringRunner.class)
//原因:websocket是需要依赖tomcat等容器的启动。所以在测试过程中我们要真正的启动一个tomcat作为容器。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UltracrmAdminApplicationTests {
    @Resource
    private ElasticServiceImpl elasticService;

    @Test
    public void contextLoads() {

//        menuMapper.addSpaceForMenuSort(1, 1, "1");

//        elasticService.saveDocment();
        elasticService.searchIndex();
//        elasticService.existindex();

    }
}