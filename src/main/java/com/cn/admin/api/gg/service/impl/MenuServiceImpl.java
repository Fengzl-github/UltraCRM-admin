package com.cn.admin.api.gg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cn.admin.api.gg.dto.MenuDTO;
import com.cn.admin.api.gg.mapstruct.menu.MenuConvertet;
import com.cn.admin.api.gg.service.MenuService;
import com.cn.admin.api.gg.vo.menu.MenuEditVO;
import com.cn.admin.api.gg.vo.menu.MenuVO;
import com.cn.admin.api.mapper.callthink.MenuMapper;
import com.cn.common.exception.FzlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
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

        List<MenuDTO> menus1 = menuMapper.getMenu1Data(role);
        StringBuilder sb = getJsonListFormat(menus1, role);
        log.info("菜单数据：{}", sb.toString());
        return JSONArray.parseArray(sb.toString());
    }

    /**
     * @Desc 递归生成菜单数据
     * @param menuData
     * @param role
     **/
    public StringBuilder getJsonListFormat(List<MenuDTO> menuData, Integer role) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MenuDTO menu;
        String menuId;
        for (int i = 0; i < menuData.size(); i++) {
            menu = menuData.get(i);
            menuId = menu.getMid();

            if (i > 0) {
                sb.append(",");
            }
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
        List<MenuDTO> menus1 = menuMapper.getAllMenu1Data(role);
        StringBuilder sb = getJsonListFormat2(menus1, role);
        log.info("[菜单管理] ## 菜单数据：{}", sb.toString());
        return JSONArray.parseArray(sb.toString());
    }

    /**
     * @Author fengzhilong
     * @Desc 拖拽修改菜单
     * @Date 2021/7/23 15:32
     * @param menuEditVO
     * @return void
     *
     * @Transactional - 默认情况下只对unchecked异常进行回滚,对checked异常不回滚
     *  如果想checked异常也回滚,添加rollbackFor属性
     *  unchecked异常: 派生于Error和runtimeException的异常,如NullPointerException ArrayIndexOutOfBoundsException ArithmeticException IllegalArgumentException OutOfMemoryError StackOverflowError
     *  checked异常: 继承自Exception的异常,如ClassNotFoundException IOException sqlException timeoutException
     *  注: 所有的Error、RuntimeException及其子类都属于 unchecked exception 非检查异常
     *     其他异常属于 checked exception 必检异常
     **/
    @Transactional(rollbackFor = SQLException.class)
    @Override
    public synchronized void updateMenuNode(MenuEditVO menuEditVO) {

        // 获取拖拽节点和目标节点菜单数据
        MenuDTO sourceMenu = menuMapper.findByMid(menuEditVO.getSourceId());
        MenuDTO targetMenu = menuMapper.findByMid(menuEditVO.getTargetId());
        String sourcePid = sourceMenu.getPid();
        String targetPid = targetMenu.getPid();
        //前
        if ("before".equals(menuEditVO.getDropType())) {
            if (sourcePid.equals(targetPid)) {
                //上移
                if (sourceMenu.getMenuSort() > targetMenu.getMenuSort()) {
                    //目标节点-源节点之间,排序+1;源节点继承目标节点
                    menuMapper.addSpaceForMenuSortBwn(1, targetMenu.getMenuSort(), sourceMenu.getMenuSort(), targetPid);

                    menuMapper.updateMenuSortByMid(targetMenu.getMenuSort(), menuEditVO.getSourceId());
                } else {//下移
                    //源节点-目标节点-1之间,排序-1;源节点继承目标节点
                    menuMapper.addSpaceForMenuSortBwn(-1, sourceMenu.getMenuSort() + 1, targetMenu.getMenuSort(), targetPid);

                    menuMapper.updateMenuSortByMid(targetMenu.getMenuSort() - 1, menuEditVO.getSourceId());
                }
            } else {
                //大于等于目标节点排序 +1; 源节点mid修改为目标节点pid+"-"+max(sort);源节点sort修改为目标节点sort
                //有子节点改变子节点mid和pid;大于等于源节点sort -1
                menuMapper.addSpaceForMenuSort(1, targetMenu.getMenuSort(), targetPid);
                menuMapper.addSpaceForMenuSort(-1, sourceMenu.getMenuSort() + 1, sourcePid);
                String maxMid = menuMapper.getMaxMid(targetPid);

                int i = Integer.parseInt(maxMid.substring(maxMid.length() - 1)) + 1;
                String newMid = maxMid.substring(0, maxMid.length() - 1) + i;

                //递归修改源节点下子节点数据
                updateSubMidAndPid(menuEditVO.getSourceId(), newMid);
                menuMapper.updateMidAndPidAndSort(newMid, targetPid, menuEditVO.getSourceId(), targetMenu.getMenuSort());
                /*if (!sourcePid.equals("0")) {
                    menuMapper.updateMidForMenuSort(sourcePid, sourceMenu.getMenuSort());
                }*/
            }
            // 中
        } else if ("inner".equals(menuEditVO.getDropType())) {

            //拖拽节点继承目标节点父id,mid修改为目标节点格式,如果拖拽节点下有子节点,同步修改 (排序问题)
            int menuSort = 1;
            List<MenuDTO> nextAllMenuData = menuMapper.getNextAllMenuData(menuEditVO.getTargetId(), null);
            menuSort += nextAllMenuData.size();
            String newMid = menuEditVO.getTargetId() + "-" + menuSort;
            menuMapper.updateMidAndPidAndSort(newMid, menuEditVO.getTargetId(), menuEditVO.getSourceId(), menuSort);
            menuMapper.addSpaceForMenuSort(-1, sourceMenu.getMenuSort(), sourcePid);
            //递归修改源节点下子节点数据
            updateSubMidAndPid(menuEditVO.getSourceId(), newMid);
        } else if (menuEditVO.getDropType().equals("after")) { //后
            if (sourcePid.equals(targetPid)) {
                //上移
                if (sourceMenu.getMenuSort() > targetMenu.getMenuSort()) {
                    //目标节点-源节点之间,排序+1;源节点继承目标节点
                    menuMapper.addSpaceForMenuSortBwn(1, targetMenu.getMenuSort() + 1, sourceMenu.getMenuSort(), targetPid);

                    menuMapper.updateMenuSortByMid(targetMenu.getMenuSort() + 1, menuEditVO.getSourceId());
                } else {//下移
                    //源节点-目标节点-1之间,排序-1;源节点继承目标节点
                    menuMapper.addSpaceForMenuSortBwn(-1, sourceMenu.getMenuSort() + 1, targetMenu.getMenuSort() + 1, targetPid);

                    menuMapper.updateMenuSortByMid(targetMenu.getMenuSort(), menuEditVO.getSourceId());
                }
            } else {
                //大于等于目标节点+1的排序 +1; 源节点mid修改为目标节点pid+"-"+max(sort);源节点sort修改为目标节点sort
                //有子节点改变子节点mid和pid;大于等于源节点+1的sort -1
                menuMapper.addSpaceForMenuSort(1, targetMenu.getMenuSort() + 1, targetPid);
                menuMapper.addSpaceForMenuSort(-1, sourceMenu.getMenuSort() + 1, sourcePid);
                String maxMid = menuMapper.getMaxMid(targetPid);

                int i = Integer.parseInt(maxMid.substring(maxMid.length() - 1)) + 1;
                String newMid = maxMid.substring(0, maxMid.length() - 1) + i;
                //递归修改源节点下子节点数据
                updateSubMidAndPid(menuEditVO.getSourceId(), newMid);
                menuMapper.updateMidAndPidAndSort(newMid, targetPid, menuEditVO.getSourceId(), targetMenu.getMenuSort() + 1);
                /*if (!sourcePid.equals("0")) {
                    menuMapper.updateMidForMenuSort(sourcePid, sourceMenu.getMenuSort());
                }*/
            }
        } else {
            throw new FzlException("拖拽类型有误");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 编辑保存菜单
     * @Date 2021/8/3 11:52
     * @param menuVO
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    public void saveMenu(MenuVO menuVO) {
        MenuDTO menuDTO = menuMapper.findByMid(menuVO.getMid());
        MenuConvertet.INSTANCE.updateMenu(menuVO, menuDTO);

        menuMapper.insertOrUpdate(menuDTO);
    }

    /**
     * 递归修改子菜单的mid和pid
     * @param sourceId 拖拽节点mid
     * @param newMid 拖拽节点新的mid
     * 拖拽节点下有子节点,pid修改为newMid,mid修改为newMid+'-'+sort
     **/
    @Transactional(rollbackFor = SQLException.class)
    public void updateSubMidAndPid(String sourceId, String newMid) {
        List<MenuDTO> nextAllMenuData = menuMapper.getNextAllMenuData(sourceId, null);
        String nextMid = "", newPid = "";
        for (MenuDTO menu : nextAllMenuData) {
            nextMid = menu.getMid();
            newPid = newMid + "-" + menu.getMenuSort();
            menuMapper.updateMidAndPidAndSort(newPid, newMid, nextMid, null);

            updateSubMidAndPid(nextMid, newPid);
        }
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

            if (i > 0) {
                sb.append(",");
            }
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
