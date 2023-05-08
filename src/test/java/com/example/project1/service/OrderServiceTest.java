package com.example.project1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project1.service.vo.ResponceVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void orderServiceTest() {
        ResponceVO create = orderService.create(4, 7);
        log.info("createTest={}", create);

    }

    @Test
    public void orderListTest() {
        ResponceVO list = orderService.list(4, 1, 5);
        log.info("listtest={}", list);
    }

    @Test
    public void orderDetailTest() {
        ResponceVO detail = orderService.detail(4, 16800l);
        log.info("detailTest={}", detail);
    }
}
