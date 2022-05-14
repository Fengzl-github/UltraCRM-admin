package com.cn.admin.api.mapper.crm;

import com.cn.admin.api.gg.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/11 10:09
 *@Desc
 **/
@Repository
@Mapper
public interface TestMapper {

    List<TestDTO> findAllData();


    Integer findNum();

    void addNum(@Param("num") Integer num);
}
