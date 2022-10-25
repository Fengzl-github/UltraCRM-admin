package com.cn.admin.api.gg.service.impl;

import com.cn.admin.api.gg.dto.TestDTO;
import com.cn.admin.api.gg.service.TestService;
import com.cn.admin.api.mapper.crm.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/11 11:06
 *@Desc
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Resource
    private TransactionDefinition transactionDefinition;

    @Override
    public List<TestDTO> getTest() {

        return testMapper.findAllData();
    }
    private static final Object obj = new Object();


    @Override
    public void testLock() {

        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            synchronized (obj){
                Integer num = testMapper.findNum();
                log.info("获取的数据:{}", num);

                testMapper.addNum(++num);
                dataSourceTransactionManager.commit(transaction);
            }
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                dataSourceTransactionManager.rollback(transaction);
            }
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
