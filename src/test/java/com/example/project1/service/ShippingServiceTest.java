package com.example.project1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project1.form.ShippingForm;
import com.example.project1.service.vo.ResponceVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ShippingServiceTest {
    @Autowired
    private IShippingService shippingService ;

    @Test
    public void AddTest() {
        ShippingForm form = new ShippingForm();
        form.setReceiverAddress("gfd");
        form.setReceiverCity("dsagads");
        form.setReceiverDistrict("dsfglnsdl");
        form.setReceiverMobile("adskugnskdj");
        form.setReceiverName("chensj");
        form.setReceiverPhone("kjsfngkjdsa");
        form.setReceiverProvince("sdkfgbd");
        form.setReceiverZip("130000");        
        
        ResponceVO add = shippingService.add(form, 4);
        log.info("addtest={}", add);
    }

    @Test
    public void deleteTest() {
        ResponceVO delete = shippingService.delete(5, 2);
        log.info("delete={}", delete);
    }

    @Test
    public void updateTset() {
        ShippingForm form = new ShippingForm();
        form.setReceiverAddress("中国");
        form.setReceiverCity("dsagads");
        form.setReceiverDistrict("dsfglnsdl");
        form.setReceiverMobile("adskugnskdj");
        form.setReceiverName("chensj");
        form.setReceiverPhone("kjsfngkjdsa");
        form.setReceiverProvince("sdkfgbd");
        form.setReceiverZip("130000");  

        shippingService.update(form, 7, 4);
    }

    @Test
    public void listTest() {
        ResponceVO list = shippingService.list(4, 1, 2);
        log.info("list={}", list);
    }



}
