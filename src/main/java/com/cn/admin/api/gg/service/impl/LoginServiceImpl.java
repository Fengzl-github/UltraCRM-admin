package com.cn.admin.api.gg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cn.admin.api.base.PmJwtToken;
import com.cn.admin.api.gg.dto.UserDTO;
import com.cn.admin.api.gg.mapstruct.user.PmAgentConvertet;
import com.cn.admin.api.gg.service.LoginService;
import com.cn.admin.api.gg.vo.login.LoginVO;
import com.cn.admin.api.mapper.callthink.UserMapper;
import com.cn.common.exception.FzlException;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *@Author fengzhilong
 *@Date 2021/5/14 11:39
 *@Desc
 **/
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String tokenKey = "adminToken";

    @Override
    public ResResult verification(LoginVO loginVO) {

        UserDTO user = userMapper.validLogin(loginVO);
        if (user == null) {
            log.info("{},登录失败：账号或密码错误", loginVO.getGhid());
            throw new FzlException("账号或密码错误");
        }
        String token = PmJwtToken.getJwtToken(loginVO.getGhid(), JSONObject.toJSONString(user));
        log.info("{},登录成功 -> 生成token：{}", loginVO.getGhid(), token);

        //存储token到redis缓存 失效时间 2分钟
        redisTemplate.boundHashOps(tokenKey).put(user.getGhid(), token);


        return ResCode.OK
                .putData("content", PmAgentConvertet.INSTANCE.toPmAgent(user))
                .putData("token", token);
    }
}
