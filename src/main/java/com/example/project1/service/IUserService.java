package com.example.project1.service;

import com.example.project1.pojo.MallUser;
import com.example.project1.service.vo.ResponceVO;

/**
 * IUserService
 */

public interface IUserService {

    /**
     * 注册
     * @param user
     * @return 
     */
    ResponceVO register(MallUser user);

    ResponceVO login(MallUser user);
    
}