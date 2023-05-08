package com.example.project1.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.dao.MallShippingMapper;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.form.ShippingForm;
import com.example.project1.pojo.MallShipping;
import com.example.project1.service.IShippingService;
import com.example.project1.service.vo.ResponceVO;
import com.example.project1.service.vo.shippingVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ShippingServiceImpl implements IShippingService{

    @Autowired
    private MallShippingMapper mallShippingMapper;

    @Override
    public ResponceVO add(ShippingForm shippingForm, Integer userId) {
        MallShipping shipping = new MallShipping();
        BeanUtils.copyProperties(shippingForm, shipping);
        shipping.setUserId(userId);
        
        int row = mallShippingMapper.insertSelective(shipping);
        if (row == 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }
        shippingVO shippingVO = new shippingVO();
        shippingVO.setShippingId(shipping.getId());
        return ResponceVO.success(ResponceEnum.SUCCESS, shippingVO);
    }

    @Override
    public ResponceVO delete(Integer shippingId, Integer userId) {
        int row = mallShippingMapper.deleteByPrimaryKeyAndUserId(shippingId, userId);
        if(row == 0){
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        return ResponceVO.success();
    }

    @Override
    public ResponceVO update(ShippingForm shippingForm, Integer shippingId, Integer userId) {
        MallShipping shipping = new MallShipping();
        BeanUtils.copyProperties(shippingForm, shipping);
        shipping.setId(shippingId);
        shipping.setUserId(userId);

        int row = mallShippingMapper.updateByPrimaryKeySelective(shipping);
        if (row == 0) {
            return ResponceVO.error(ResponceEnum.UPDATE_SHIPPING_ERROR);
        }

        return ResponceVO.success();
    }

    @Override
    public ResponceVO list(Integer userId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<MallShipping> mallShippingList = mallShippingMapper.selectByUserId(userId);
        
        if(mallShippingList == null){
            return ResponceVO.error(ResponceEnum.NEED_LOGIN);
        }

        PageInfo<MallShipping> page = new PageInfo<>(mallShippingList);
        return ResponceVO.success(ResponceEnum.SUCCESS, page);
    }


    

    

    
}
