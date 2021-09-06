package com.cn.admin.api.mapper.callthink;

import com.cn.admin.api.gg.dto.option.IntOption;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/7/28 14:29
 *@Desc
 **/
@Repository
public interface RoleMapper {

    List<IntOption> findAllOptions();
}
