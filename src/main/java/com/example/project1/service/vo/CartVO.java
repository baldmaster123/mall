package com.example.project1.service.vo;

import java.util.Date;
import lombok.Data;

@Data
public class CartVO {
    private Integer id;
    private Integer productId;
    private Date createTime;
    private Date updateTime;
    private Boolean selected;
    private Integer count;
}
