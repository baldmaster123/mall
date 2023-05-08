
package com.example.project1.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project1.dao.MallCategoryMapper;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallCategory;
import com.example.project1.service.ICategoryService;
import com.example.project1.service.vo.CategoryVO;
import com.example.project1.service.vo.ResponceVO;
@Service
//@Slf4j
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private MallCategoryMapper mallCategoryMapper;

    @Override
    public ResponceVO selectAll() {
        List<MallCategory> categoryList = mallCategoryMapper.selectAllActive();
        //log.info("categoryList={}",categoryList);

        List<CategoryVO> categoryVOList = new ArrayList<>();       
        for(MallCategory category: categoryList){
            CategoryVO categoryVO = new CategoryVO();            
            BeanUtils.copyProperties(category, categoryVO);
            categoryVOList.add(categoryVO);
        }
        //log.info("categoryvolist={}", categoryVOList);

        Map<Integer,CategoryVO> categoryVOMap = new HashMap<>();
        for(CategoryVO categoryVO : categoryVOList){
            categoryVOMap.put(categoryVO.getId(), categoryVO);
        }
        //log.info("categoryvomap={}",categoryVOMap.toString());
        for(CategoryVO categoryVO : categoryVOList){
            if(categoryVO.getParentId() != 0){
                //log.info("parentid={}", categoryVO.getParentId())
                categoryVOMap.get(categoryVO.getParentId()).getSubCatagory().add(categoryVO);
                
            }
        }
        
        List<CategoryVO> successData = new ArrayList<>(); 
        for (CategoryVO categoryVO : categoryVOList) {
            if (categoryVO.getParentId() == 0) {
                successData.add(categoryVO);
            }
        }

        //log.info("success={}", ResponceVO.success(ResponceEnum.SUCCESS, successData.toArray()));
        return ResponceVO.success(ResponceEnum.SUCCESS, successData);
    }
    
    @Override
    public Set<Integer> selectSubCategoryIdById(Integer id) {

        List<MallCategory> ActiveCategories = mallCategoryMapper.selectAllActive();
        
        Set<Integer> idSet = new HashSet<>();
        
        for(MallCategory category : ActiveCategories){
            if(category.getId().equals(id) || category.getParentId().equals(id)){
                idSet.add(category.getId());
            }            
        }
        return idSet;
    }
    
}
