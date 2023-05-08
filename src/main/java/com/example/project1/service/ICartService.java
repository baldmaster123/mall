package com.example.project1.service;

import java.util.List;

import com.example.project1.pojo.MallCart;
import com.example.project1.service.vo.ResponceVO;

public interface ICartService {
    
    ResponceVO list(Integer UserId);

    int insertCart(MallCart cart);

    MallCart selectUserIdWithProductId(Integer userId, Integer productId);

    int updateCartById(MallCart cart);

    int deleteCartByProductd(Integer productId);

    List<MallCart> selectByUserId(Integer userId);
}
