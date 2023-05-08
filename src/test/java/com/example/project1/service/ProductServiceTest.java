package com.example.project1.service;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project1.service.vo.ResponceVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProductServiceTest {
    @Autowired
    private ICategoryService categoryService;

    @Autowired 
    private IProductService productService;

    @Test
    public void testSelectById(){

        Set<Integer> selectSubCategoryIdById = categoryService.selectSubCategoryIdById(100001);
        log.info("categoryidset={}",selectSubCategoryIdById);

    }


    @Test
    public void testSelectByCateId() {
        ResponceVO selectAvtiveProductIncludingSubById = productService.selectAvtiveProductIncludingSubById(100002, 1, 2);
        log.info("selectAvtiveProductIncludingSubById={}",selectAvtiveProductIncludingSubById);
    }

    @Test
    public void testSelectByProductId() {
        ResponceVO product = productService.selectById(26);
        log.info("profuct={}", product);
    }
}
