package com.soldier.service;

import com.soldier.domian.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    /*
        查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();
}