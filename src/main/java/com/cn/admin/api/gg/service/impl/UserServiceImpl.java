package com.cn.admin.api.gg.service.impl;

import com.cn.admin.api.gg.dto.UserDTO;
import com.cn.admin.api.gg.dto.UserGroupDTO;
import com.cn.admin.api.gg.mapstruct.user.UserConvertet;
import com.cn.admin.api.gg.service.UserService;
import com.cn.admin.api.gg.vo.user.UserQueryVO;
import com.cn.admin.api.gg.vo.user.UserVO;
import com.cn.admin.api.mapper.callthink.GroupMapper;
import com.cn.admin.api.mapper.callthink.UserMapper;
import com.cn.admin.api.util.JsonPage;
import com.cn.common.exception.FzlException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/5/7 17:31
 *@Desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GroupMapper groupMapper;


    /**
     * @Author fengzhilong
     * @Desc 用户列表
     * @Date 2021/6/7 16:41
     * @param userQueryVO
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    public PageInfo<UserGroupDTO> listUserPage(UserQueryVO userQueryVO, JsonPage pageable) {

        PageHelper.startPage(pageable.getPage(), pageable.getSize());
        List<UserGroupDTO> userList = userMapper.findAllUser(userQueryVO);
        PageInfo<UserGroupDTO> page = new PageInfo<>(userList);

        return page;
    }


    /**
     * @Author fengzhilong
     * @Desc  添加修改用户
     * @Date 2021/6/7 18:00
     * @param userVO
     * @return com.cn.common.vo.ResResult
     **/
    @Override
    public void saveUserInfo(UserVO userVO) {
        UserDTO user = null;
        if (userVO.getId() == null) {
            userVO.setPassword("1234");
            user = UserConvertet.INSTANCE.toUserDTO(userVO);
        }
        if (userVO.getId() != null) {
            user = userMapper.findByid(userVO.getId());
            if (user == null) {
                throw new FzlException("用户不存在");
            }
            UserConvertet.INSTANCE.updateUserDTO(userVO, user);
        }

        userMapper.insertOrUpdate(user);

    }


    /**
     * @Author fengzhilong
     * @Desc 删除用户
     * @Date 2021/7/19 13:42
     * @param ghid
     * @return void
     **/
    @Override
    public void removeUser(String ghid) {

        userMapper.removeUser(ghid);

        groupMapper.removeMemberByGhid(ghid);


    }
}
