package com.cn.admin.api.gg.mapstruct.menu;

import com.cn.admin.api.gg.dto.MenuDTO;
import com.cn.admin.api.gg.vo.menu.MenuVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 *@Author fengzhilong
 *@Date 2021/8/3 11:52
 *@Desc
 **/
@Mapper
@Component
public interface MenuConvertet {

    MenuConvertet INSTANCE = Mappers.getMapper(MenuConvertet.class);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenu(MenuVO menuVO, @MappingTarget MenuDTO menuDTO);
}
