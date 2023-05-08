package com.example.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.service.ICategoryService;
import com.example.project1.service.vo.ResponceVO;

@RestController
public class CategoryController {

    @Autowired 
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponceVO getAllActiveCategories() {
        ResponceVO selectAll = categoryService.selectAll();
        return selectAll;
    }
}
