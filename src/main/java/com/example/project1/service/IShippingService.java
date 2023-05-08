package com.example.project1.service;

import com.example.project1.form.ShippingForm;
import com.example.project1.service.vo.ResponceVO;

public interface IShippingService {
    ResponceVO add(ShippingForm shippingForm, Integer userId);

    ResponceVO delete(Integer shippingId, Integer userId);

    ResponceVO update(ShippingForm shippingForm, Integer shippingId, Integer userId);

    ResponceVO list(Integer userId, Integer pageIndex, Integer pageSize);
}
