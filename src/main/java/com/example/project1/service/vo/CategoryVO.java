package com.example.project1.service.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CategoryVO {    
    private Integer id;
    private Integer parentId;
    private String name;
    private String sortOrder;
    private List<CategoryVO> subCatagory = new ArrayList<>();
}
