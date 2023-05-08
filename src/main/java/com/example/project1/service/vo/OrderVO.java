package com.example.project1.service.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.project1.pojo.MallShipping;

import lombok.Data;

@Data
public class OrderVO {
    private Integer id;

    private Long orderNo;
    private BigDecimal payment;
    private Integer paymentType;
    private Integer postage;
    private Integer status;
    private Date paymentTime;
    private Date sendTime;
    private Date endTime;
    private Date closeTime;
    private Date createTime;
    private List<OrderItemVO> orderItemVoList;
    private Integer shippingId;
	private MallShipping shippingVo;
}
