package com.example.project1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.enums.RoleEnum;
import com.example.project1.pojo.MallUser;

@SpringBootTest
@Transactional
public class UserServiceImpl {
    @Autowired
    private IUserService userService;

    @Test
    public void testRegister(){
        MallUser user = new MallUser();
        user.setEmail("enhao@outlook.com");
        user.setUsername("chehao");
        user.setPassword("123456");
        user.setRole(new RoleEnum().getCUSTOMER());
        userService.register(user);
    }
}
