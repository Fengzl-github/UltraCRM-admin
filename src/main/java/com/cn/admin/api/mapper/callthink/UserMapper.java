package com.cn.admin.api.mapper.callthink;

import com.cn.admin.api.gg.dto.UserDTO;
import com.cn.admin.api.gg.dto.UserGroupDTO;
import com.cn.admin.api.gg.vo.login.LoginVO;
import com.cn.admin.api.gg.vo.user.UserQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *@Author fengzhilong
 *@Date 2021/5/7 16:50
 *@Desc
 **/
@Mapper
@Repository
public interface UserMapper {

    UserDTO validLogin(LoginVO loginVO);

    UserDTO findByGhid(String ghid);

    UserDTO findByid(Integer id);

    List<UserGroupDTO> findAllUser(UserQueryVO userQueryVO);

    void insertOrUpdate(UserDTO user);

    void removeUser(String id);
}
