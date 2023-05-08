package com.example.project1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project1.service.vo.ResponceVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CartServiceTest {
    @Autowired
    private ICartService cartService;

    @Test
    public void testList() {
        ResponceVO list = cartService.list(123);
        log.info("etstlist={}", list);
    }

}
