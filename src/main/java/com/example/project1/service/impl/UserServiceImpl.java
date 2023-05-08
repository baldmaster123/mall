package com.example.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.example.project1.dao.MallUserMapper;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallUser;
import com.example.project1.service.IUserService;
import com.example.project1.service.vo.ResponceVO;

import java.nio.charset.StandardCharsets;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private MallUserMapper userMapper;

    @Override
    public ResponceVO register(MallUser user) {
        //role默认为custmor
        user.setRole(1);

        // 用户名不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            return ResponceVO.error(ResponceEnum.USER_EXIST);
        }
        // 邮箱不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
            return ResponceVO.error(ResponceEnum.EMAIL_EXIST);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        // 写入数据库
        int insertSelective = userMapper.insertSelective(user);
        if (insertSelective == 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        return ResponceVO.success();
    }

    @Override
    public ResponceVO login(MallUser mallUser) {

        MallUser user =  userMapper.selectByUsername(mallUser.getUsername());
        //用户名不存在
        if(user == null){
            return ResponceVO.error(ResponceEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //密码错误
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(mallUser.getPassword().getBytes()))){
            return ResponceVO.error(ResponceEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        user.setPassword("");

        return ResponceVO.success(ResponceEnum.LOGIN_SUCCESS, user);
    }

}