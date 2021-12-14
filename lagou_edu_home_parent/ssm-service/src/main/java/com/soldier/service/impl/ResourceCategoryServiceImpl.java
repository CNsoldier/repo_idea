package com.soldier.service.impl;

import com.soldier.dao.ResourceCategoryMapper;
import com.soldier.domian.ResourceCategory;
import com.soldier.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void saveResource(ResourceCategory resourceCategory) {

        // 封装数据
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        // 调用mapper
        resourceCategoryMapper.saveResource(resourceCategory);
    }

    @Override
    public void updateResource(ResourceCategory resourceCategory) {

        // 封装数据
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        // 调用mapper
        resourceCategoryMapper.updateResource(resourceCategory);
    }

    @Override
    public void deleteResource(Integer id) {

        resourceCategoryMapper.deleteResource(id);
    }


}
