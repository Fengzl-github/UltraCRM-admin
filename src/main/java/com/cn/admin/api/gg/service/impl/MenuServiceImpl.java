package com.cn.admin.api.gg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cn.admin.api.gg.dto.MenuDTO;
import com.cn.admin.api.gg.service.MenuService;
import com.cn.admin.api.gg.vo.menu.MenuEditVO;
import com.cn.admin.api.mapper.callthink.MenuMapper;
import com.cn.common.exception.FzlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * @Author fengzhilong
     * @Desc 获取登录后菜单列表
     * @Date 2021/7/22 16:02
     * @param role
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    @Cacheable(cacheNames = "menu#60*60*3", key = "'role'+#a0")
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
            menuId = menu.getMid();

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


    /**
     * @Author fengzhilong
     * @Desc 菜单管理-全部菜单数据
     * @Date 2021/7/22 16:03
     * @param
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    public JSONArray getEditMenuData(Integer role) {
        List<MenuDTO> Menus1 = menuMapper.getAllMenu1Data(role);
        StringBuilder sb = getJsonListFormat2(Menus1, role);
        log.info("[菜单管理] ## 菜单数据：{}", sb.toString());
        JSONArray json = JSONArray.parseArray(sb.toString());
        return json;
    }

    /**
     * @Author fengzhilong
     * @Desc 拖拽修改菜单
     * @Date 2021/7/23 15:32
     * @param menuEditVO
     * @return void
     **/
    @Transactional
    @Override
    public void updateMenuNode(MenuEditVO menuEditVO) {

        // 获取拖拽节点和目标节点菜单数据
        MenuDTO sourceMenu = menuMapper.findByMid(menuEditVO.getSourceId());
        MenuDTO targetMenu = menuMapper.findByMid(menuEditVO.getTargetId());
        String sourcePid = sourceMenu.getPid();
        String targetPid = targetMenu.getPid();
        if (menuEditVO.getDropType().equals("before")){ //前
            if (sourcePid.equals(targetPid)){
                //排序继承目标节点,目标节点及以后所有节点排序+1
                menuMapper.updateMenuSortByMid(targetMenu.getMenuSort(), menuEditVO.getSourceId());

                menuMapper.addSpaceForMenuSort(1, targetMenu.getMenuSort()-1, targetPid);

            }else {
                //拖拽节点继承目标节点父id,mid修改为目标节点格式,如果拖拽节点下有子节点,同步修改 (排序问题)
            }
        }else if (menuEditVO.getDropType().equals("inner")){ // 中
            //拖拽节点继承目标节点父id,mid修改为目标节点格式,如果拖拽节点下有子节点,同步修改 (排序问题)
            System.out.println("inner");
        }else if (menuEditVO.getDropType().equals("after")){ //后
            if (sourcePid.equals(targetPid)){
                //目标节点以后所有节点排序+2,排序继承目标节点+1
            }else {
                //拖拽节点继承目标节点父id,mid修改为目标节点格式,如果拖拽节点下有子节点,同步修改 (排序问题)
            }
        }else {
            throw new FzlException("拖拽类型有误");
        }
        // 1.父id相同,改变排序

        // 1.1 加入


        // 2.父id不同,改变拖拽节点的父id和排序

    }

    /**
     * @Desc 递归生成菜单数据
     * @param menuData
     * @param role
     **/
    public StringBuilder getJsonListFormat2(List<MenuDTO> menuData, Integer role) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MenuDTO menu;
        String menuId;
        for (int i = 0; i < menuData.size(); i++) {
            menu = menuData.get(i);
            menuId = menu.getMid();

            if (i > 0) sb.append(",");
            sb.append("{");
            sb.append("\"id\": \"" + menuId + "\", ");
            sb.append("\"label\": \"" + menu.getTitle() + "\", ");
            sb.append("\"icon\": \"" + menu.getIcon() + "\", ");
            sb.append("\"url\": \"" + menu.getUrl() + "\", ");
            sb.append("\"role\": \"" + menu.getRole() + "\", ");
            sb.append("\"visible\": \"" + menu.getVisible() + "\"");
            //是否有下一级菜单
            List<MenuDTO> menuNextData = menuMapper.getNextAllMenuData(menuId, role);
            if (menuNextData.size() > 0) {
                sb.append(", \"children\": " + getJsonListFormat2(menuNextData, role));
            }

            sb.append("}");
        }

        sb.append("]");
        return sb;
    }
}
