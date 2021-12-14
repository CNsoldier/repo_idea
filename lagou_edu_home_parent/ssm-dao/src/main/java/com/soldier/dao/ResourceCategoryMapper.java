package com.soldier.dao;

import com.soldier.domian.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*
        查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();

    /*
        添加资源分类
     */
    public void saveResource(ResourceCategory resourceCategory);

    /*
        更新资源分类
     */
    public void updateResource(ResourceCategory resourceCategory);

    /*
        删除资源分类
     */
    public void deleteResource(Integer id);
}
