package com.cn.admin.api.gg.mapstruct.group;

import com.cn.admin.api.gg.dto.GroupDTO;
import com.cn.admin.api.gg.vo.group.GroupVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 *@Author fengzhilong
 *@Date 2021/6/4 16:13
 *@Desc
 **/
@Mapper
@Component
public interface GroupConvertet {

    GroupConvertet INSTANCE = Mappers.getMapper(GroupConvertet.class);

    GroupDTO toGroupDTO(GroupVO groupVO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGroupDTO(GroupVO groupVO, @MappingTarget() GroupDTO groupDTO);
}
