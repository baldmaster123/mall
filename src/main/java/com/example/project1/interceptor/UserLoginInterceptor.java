package com.example.project1.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.project1.consts.MallConsts;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallUser;
import com.example.project1.service.vo.ResponceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        MallUser user = (MallUser) request.getAttribute(MallConsts.CURRENTUSER);
        if(user == null){
            response.getWriter().print(ResponceVO.error(ResponceEnum.NEED_LOGIN));
            //response.getOutputStream().print(ResponceVO.error(ResponceEnum.NEED_LOGIN).toString());
            //throw new LoginException();
            //return false;
        }
        return true;
    }
}
