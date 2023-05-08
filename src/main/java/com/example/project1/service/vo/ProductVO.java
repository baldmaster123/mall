package com.example.project1.service.vo;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductVO {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private Integer status;
    private BigDecimal price;
}