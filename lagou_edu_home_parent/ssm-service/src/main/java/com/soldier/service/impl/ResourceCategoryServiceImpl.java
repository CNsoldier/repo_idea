package com.soldier.service.impl;

import com.soldier.dao.ResourceCategoryMapper;
import com.soldier.domian.ResourceCategory;
import com.soldier.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }
}
