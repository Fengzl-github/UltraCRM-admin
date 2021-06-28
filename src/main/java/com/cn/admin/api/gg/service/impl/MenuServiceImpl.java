package com.cn.admin.api.gg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cn.admin.api.gg.dto.MenuDTO;
import com.cn.admin.api.gg.service.MenuService;
import com.cn.admin.api.mapper.callthink.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 14:07
 *@Desc
 **/
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public JSONArray getMenuData(Integer role) {

        List<MenuDTO> Menus1 = menuMapper.getMenu1Data(role);
        StringBuilder sb = getJsonListFormat(Menus1, role);
        log.info("菜单数据：{}", sb.toString());
        JSONArray json = JSONArray.parseArray(sb.toString());
        return json;
    }

    /**
     * @Desc 递归生成菜单数据
     * @param menuData
     * @param role
     **/
    public StringBuilder getJsonListFormat(List<MenuDTO> menuData, Integer role) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MenuDTO menu = null;
        String menuId = "";
        for (int i = 0; i < menuData.size(); i++) {
            menu = menuData.get(i);
            menuId = menu.getMId();

            if (i > 0) sb.append(",");
            sb.append("{");
            sb.append("\"id\": \"" + menuId + "\", ");
            sb.append("\"title\": \"" + menu.getTitle() + "\", ");
            sb.append("\"icon\": \"" + menu.getIcon() + "\", ");
            sb.append("\"url\": \"" + menu.getUrl() + "\"");
            //是否有下一级菜单
            List<MenuDTO> menuNextData = menuMapper.getNextMenuData(menuId, role);
            if (menuNextData.size() > 0) {
                sb.append(", \"children\": " + getJsonListFormat(menuNextData, role));
            }

            sb.append("}");
        }

        sb.append("]");
        return sb;
    }
}
