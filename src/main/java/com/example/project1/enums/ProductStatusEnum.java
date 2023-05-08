package com.example.project1.enums;

import lombok.Data;

@Data
public class ProductStatusEnum {
    public static final Integer ON_SALE = 1;
    public static final Integer OFF_SALE = 2;
    public static final Integer DELETE = 3;
}
