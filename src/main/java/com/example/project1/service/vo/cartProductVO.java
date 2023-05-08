package com.example.project1.service.vo;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class cartProductVO {
    private Integer productId;
    private Integer quantity;
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private Integer productStock;
    private Boolean productSelected;
}
