package com.example.project1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.dao.MallProductMapper;
import com.example.project1.enums.ProductStatusEnum;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallProduct;
import com.example.project1.pojo.MallProductWithBLOBs;
import com.example.project1.service.ICategoryService;
import com.example.project1.service.IProductService;
import com.example.project1.service.vo.ProductVO;
import com.example.project1.service.vo.ResponceVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

// import lombok.extern.slf4j.Slf4j;

@Service
// @Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private MallProductMapper mallProductMapper;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public ResponceVO selectAvtiveProductIncludingSubById(Integer categoryId, Integer pageIndex, Integer pageSize) {
        Set<Integer> subCategoryId = null;

        if (categoryId != null) {
            subCategoryId = categoryService.selectSubCategoryIdById(categoryId);
        }

        PageHelper.startPage(pageIndex, pageSize);
        List<MallProduct> mallProductList = mallProductMapper.selectByIdSet(subCategoryId);

        List<ProductVO> productVOList = new ArrayList<>();

        for (MallProduct product : mallProductList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOList.add(productVO);
        }

        PageInfo<ProductVO> pageInfo = new PageInfo<>(productVOList);

        return ResponceVO.success(ResponceEnum.SUCCESS, pageInfo);
    }

    @Override
	public ResponceVO selectById(Integer id) {
        MallProductWithBLOBs product = mallProductMapper.selectByPrimaryKey(id);

        if(product == null || product.getStatus().equals(ProductStatusEnum.DELETE) || product.getStatus().equals(ProductStatusEnum.OFF_SALE)){
            return ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS, product);
    }

    @Override
    public ResponceVO selectByProductId(Integer productId) {
        MallProduct product = mallProductMapper.selectByPrimaryKey(productId);
        //log.info("akhsdfbjhdsab+{}", product);
        if (product == null || !product.getStatus().equals(ProductStatusEnum.ON_SALE)) {
            return ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        if(product.getStock() <= 0){
            return ResponceVO.error(ResponceEnum.PRODUCT_STOCK_LACK);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS, product);
    }

}
