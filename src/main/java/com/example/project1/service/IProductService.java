package com.example.project1.service;

import com.example.project1.service.vo.ResponceVO;

public interface IProductService {
    ResponceVO selectAvtiveProductIncludingSubById(Integer categoryId, Integer pageIndex, Integer pageSize);

    ResponceVO selectById(Integer id);

    ResponceVO selectByProductId(Integer productId);
}
