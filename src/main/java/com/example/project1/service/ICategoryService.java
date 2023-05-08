package com.example.project1.service;

import java.util.Set;

import com.example.project1.service.vo.ResponceVO;

public interface ICategoryService {

    ResponceVO selectAll();

    Set<Integer> selectSubCategoryIdById(Integer id);
}
