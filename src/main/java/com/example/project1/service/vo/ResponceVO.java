package com.example.project1.service.vo;

import com.example.project1.enums.ResponceEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ResponceVO {
    private Integer status;
    private String msg;
    private Object data;

    private ResponceVO(){};

    private ResponceVO(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }

    private ResponceVO(Integer status, Object data){
        this.status = status;
        this.data = data;
    }

    public static ResponceVO success(){
        int status = ResponceEnum.SUCCESS.getCode();
        String msg = ResponceEnum.SUCCESS.getMessage();
        return new ResponceVO(status,msg);
    }

    public static ResponceVO success(ResponceEnum resp){
        int status = resp.getCode();
        String msg = resp.getMessage();
        return new ResponceVO(status,msg);
    }

    public static ResponceVO success(ResponceEnum resp, Object data) {
        int status = ResponceEnum.SUCCESS.getCode();
        return new ResponceVO(status,data);
    }

    public static ResponceVO error(ResponceEnum resp){
        int status = resp.getCode();
        String msg = resp.getMessage();
        return new ResponceVO(status, msg);
    }

}
