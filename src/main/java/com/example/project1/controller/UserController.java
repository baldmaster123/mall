package com.example.project1.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.consts.MallConsts;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.form.LoginUserForm;
import com.example.project1.form.RegisterUserForm;
import com.example.project1.pojo.MallUser;
import com.example.project1.service.IUserService;
import com.example.project1.service.vo.ResponceVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponceVO register(@RequestBody @Valid RegisterUserForm userForm, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            //log.info("请求参数有误,{}", bindingResult.getFieldError());
            return ResponceVO.error(ResponceEnum.PARAM_ERROR);
        }
        //log.info("username={}", userForm.getUsername());
        MallUser user = new MallUser();
        BeanUtils.copyProperties(userForm, user);

        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponceVO login(@RequestBody @Valid LoginUserForm userForm, BindingResult bindingResult, HttpSession httpSession) {
        
        if (bindingResult.hasErrors()) {
            //log.info("请求参数有误,{}", bindingResult.getFieldError());
            return ResponceVO.error(ResponceEnum.PARAM_ERROR);
        }
        //log.info("username={}", userForm.getUsername());
        MallUser user = new MallUser();
        BeanUtils.copyProperties(userForm, user);

        ResponceVO loginVo = userService.login(user);

        httpSession.setAttribute(MallConsts.CURRENTUSER, loginVo.getData());

        return loginVo;
    }

    @GetMapping
    public ResponceVO userInfo(HttpSession httpSession) {
        MallUser user = (MallUser)httpSession.getAttribute(MallConsts.CURRENTUSER);
        
        if(user == null){
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }
        //System.out.println(user.getUsername());
        return ResponceVO.success(ResponceEnum.LOGIN_SUCCESS, user);
    }
}
