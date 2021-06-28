package com.cn.admin.api.gg.mapstruct.user;

import com.cn.admin.api.base.PmAgent;
import com.cn.admin.api.gg.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 *@Author fengzhilong
 *@Date 2021/5/21 10:38
 *@Desc
 **/
@Component
@Mapper
public interface PmAgentConvertet {

    PmAgentConvertet INSTANCE = Mappers.getMapper(PmAgentConvertet.class);


    PmAgent toPmAgent(UserDTO userDTO);
}
