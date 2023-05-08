package com.example.project1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.project1.consts.MallConsts;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.form.ShippingForm;
import com.example.project1.pojo.MallUser;
import com.example.project1.service.IShippingService;
import com.example.project1.service.vo.ResponceVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @PostMapping(value = "/shippings")
    public ResponceVO add(@Valid @RequestBody ShippingForm form, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return ResponceVO.error(ResponceEnum.PARAM_ERROR);
        }

        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        return shippingService.add(form, mallUser.getId());
    }

    @DeleteMapping("/shippings/{shippingId}")
    public ResponceVO delete(@PathVariable Integer shippingId, HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        return shippingService.delete(shippingId, mallUser.getId());
    }

    @PutMapping(value = "/shippings/{shippingId}")
    public ResponceVO update(@PathVariable Integer shippingId, @RequestBody ShippingForm form,
            HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        return shippingService.update(form, shippingId, mallUser.getId());
    }

    @GetMapping(value = "/shippings")
    public ResponceVO list(@RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        return shippingService.list(mallUser.getId(), pageIndex, pageSize);
    }

}
