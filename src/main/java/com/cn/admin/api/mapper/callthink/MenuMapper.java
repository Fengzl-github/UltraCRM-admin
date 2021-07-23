package com.cn.admin.api.mapper.callthink;

import com.cn.admin.api.gg.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:56
 *@Desc
 **/
@Repository
public interface MenuMapper {

    List<MenuDTO> getMenu1Data(Integer role);

    List<MenuDTO> getNextMenuData(@Param("pid") String menuId, @Param("role") Integer role);

    List<MenuDTO> getAllMenu1Data(Integer role);

    List<MenuDTO> getNextAllMenuData(@Param("pid") String menuId, @Param("role") Integer role);

    MenuDTO findByMid(String mid);

    void updateMenuSortByMid(@Param("menuSort") Integer menuSort, @Param("mid") String mid);

    void addSpaceForMenuSort(@Param("space") int space, @Param("menuSort") Integer menuSort, @Param("pid") String pid);
}
