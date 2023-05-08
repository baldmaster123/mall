package com.example.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.service.IProductService;
import com.example.project1.service.vo.ResponceVO;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponceVO list(@RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
                
            return productService.selectAvtiveProductIncludingSubById(categoryId, pageIndex, pageSize);
                
    }

    @GetMapping("/products/{productId}")
    public ResponceVO detail(@PathVariable Integer productId) {
        return productService.selectById(productId);
    }
}
