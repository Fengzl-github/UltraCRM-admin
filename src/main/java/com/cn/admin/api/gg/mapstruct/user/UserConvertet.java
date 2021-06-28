package com.cn.admin.api.gg.mapstruct.user;

import com.cn.admin.api.gg.dto.UserDTO;
import com.cn.admin.api.gg.vo.user.UserVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 *@Author fengzhilong
 *@Date 2021/6/7 17:17
 *@Desc
 **/
@Component
@Mapper
public interface UserConvertet {

    UserConvertet INSTANCE = Mappers.getMapper(UserConvertet.class);


    UserDTO toUserDTO(UserVO userVO);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDTO(UserVO userVO, @MappingTarget UserDTO user);
}
