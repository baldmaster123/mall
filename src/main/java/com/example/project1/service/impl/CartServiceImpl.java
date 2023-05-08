package com.example.project1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.dao.MallCartMapper;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallCart;
import com.example.project1.service.ICartService;
import com.example.project1.service.vo.CartVO;
import com.example.project1.service.vo.ResponceVO;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private MallCartMapper mallCartMapper;

    @Override
    public ResponceVO list(Integer UserId) {

        List<MallCart> cartList = mallCartMapper.selectByUserId(UserId);
        if(cartList.size() == 0){
            return ResponceVO.error(ResponceEnum.CART_EMPTY);
        }
        List<CartVO> cartVOList = new ArrayList<>();
        
        for(MallCart cart : cartList){
            CartVO cartVO = new CartVO();            
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS, cartVOList);
    }

    @Override
    public int insertCart(MallCart cart) {
        return mallCartMapper.insertSelective(cart);
    }

    @Override
    public MallCart selectUserIdWithProductId(Integer userId, Integer productId) {
        return mallCartMapper.selectUserIdWithProductId(userId, productId);
    }

    @Override
    public int updateCartById(MallCart cart) {
        return mallCartMapper.updateByPrimaryKeySelective(cart);

    }

    @Override
    public int deleteCartByProductd(Integer productId) {
        return mallCartMapper.deleteByProductId(productId);

    }

    @Override
    public List<MallCart> selectByUserId(Integer userId) {
        return mallCartMapper.selectByUserId(userId);
    }

}
