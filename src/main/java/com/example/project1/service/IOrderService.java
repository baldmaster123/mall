package com.example.project1.service;

import com.example.project1.service.vo.ResponceVO;

public interface IOrderService {
    ResponceVO create(Integer userId, Integer shippingId);
    ResponceVO list(Integer UID, Integer pageNum, Integer pageSize);
    ResponceVO detail(Integer UID, Long orderNo);
    ResponceVO cancel(Integer UID, Long orderNO);
    ResponceVO paid(Integer UID, Long orderNO);
}
