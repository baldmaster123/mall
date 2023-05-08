package com.example.project1.enums;

import lombok.Getter;

@Getter
public enum ResponceEnum {
    SUCCESS(0,"请求成功"),
    LOGIN_SUCCESS(1,"登陆成功"),
    ERROR(-1, "服务端异常"),
    PARAM_ERROR(-2,"参数错误"),
    PASSWORD_ERROR(1, "密码错误"),
    USER_EXIST(2,"用户名已存在"),
    EMAIL_EXIST(3,"邮箱已存在"),
    USERNAME_OR_PASSWORD_ERROR(4,"用户名或密码错误"),
    PRODUCT_OFF_SALE_OR_DELETE(5,"产品下架或删除"),
    PRODUCT_STOCK_LACK(6,"商品缺货"),
    CART_EMPTY(7,"购物车为空"),
    UPDATE_SHIPPING_ERROR(8, "更新购物车失败"),    
    SHIPPPING_NOT_EXIT(9,"收货地址不存在"),
    NO_CART_SELECTED(11, "没有商品被选中"),
    ORDER_EMPTY(12,"订单为空"),
    ORDER_STATUS_ERROR(13, "订单状态有误"),
    NEED_LOGIN(10,"请先登陆");
    


    private int code;
    private String message;

    ResponceEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    
}
