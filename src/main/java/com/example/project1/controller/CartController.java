package com.example.project1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.consts.MallConsts;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallCart;
import com.example.project1.pojo.MallUser;
import com.example.project1.service.ICartService;
import com.example.project1.service.IProductService;
import com.example.project1.service.vo.CartVO;
import com.example.project1.service.vo.ResponceVO;

import jakarta.servlet.http.HttpSession;
// import lombok.extern.slf4j.Slf4j;

@RestController
// @Slf4j
public class CartController {
    private Integer SUCCESS_CODE = 0;
    private Boolean SELECTED = true;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;
    private int insertCart;

    @GetMapping("/carts")
    public ResponceVO carts(HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }
        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }
        Integer userId = mallUser.getId();
        List<MallCart> MallCartList = cartService.selectByUserId(userId);
        List<CartVO> cartVOList = new ArrayList<>();
        for(MallCart cart : MallCartList){
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS, cartVOList);
    }

    @PostMapping("/carts")
    public ResponceVO add(@RequestParam(name = "productId", required = true) Integer productId,
            @RequestParam(name = "count", required = false, defaultValue = "1") Integer count,
            HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallCart CartWithGivenUserIdAndProductId = cartService.selectUserIdWithProductId(mallUser.getId(), productId);
        if (CartWithGivenUserIdAndProductId != null) {
            CartWithGivenUserIdAndProductId.setCount(CartWithGivenUserIdAndProductId.getCount() + 1);
            CartWithGivenUserIdAndProductId.setUpdateTime(null);
            cartService.updateCartById(CartWithGivenUserIdAndProductId);
            return carts(httpSession);
        }

        ResponceVO productResp = productService.selectByProductId(productId);
        // log.info("dkghasdkjgn={}", productResp);
        if (productResp.getStatus().equals(SUCCESS_CODE)) {
            MallCart cart = new MallCart();
            cart.setProductId(productId);
            cart.setSelected(SELECTED);
            cart.setUserId(mallUser.getId());
            cart.setCount(count);
            insertCart = cartService.insertCart(cart);
        }

        if (insertCart > 0) {
            return carts(httpSession);
        }

        return (ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE));

    }

    @PutMapping("/carts/{productId}")
    public ResponceVO updateCart(@PathVariable Integer productId, @RequestParam Boolean selected,
            HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallCart CartWithGivenUserIdAndProductId = cartService.selectUserIdWithProductId(mallUser.getId(), productId);
        if (CartWithGivenUserIdAndProductId != null) {
            CartWithGivenUserIdAndProductId.setUpdateTime(null);
            CartWithGivenUserIdAndProductId.setSelected(selected);
            cartService.updateCartById(CartWithGivenUserIdAndProductId);
            return carts(httpSession);
        }

        return (ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE));

    }

    @DeleteMapping("/carts/{productId}")
    public ResponceVO deleteCart(@PathVariable Integer productId,HttpSession httpSession) {
        if (httpSession.isNew()) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        MallUser mallUser = (MallUser) httpSession.getAttribute(MallConsts.CURRENTUSER);
        if (mallUser == null) {
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }
        
        int deleteCartByProductd = cartService.deleteCartByProductd(productId);

        if (deleteCartByProductd > 0) {
            return carts(httpSession);
        }

        return ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE);
    }



}
