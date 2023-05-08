package com.example.project1.service.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class OrderItemVO {
    private Long orderNo;
    private Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Date createTime;
}
