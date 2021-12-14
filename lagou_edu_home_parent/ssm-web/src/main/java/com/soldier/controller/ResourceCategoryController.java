package com.soldier.controller;

import com.soldier.domian.ResourceCategory;
import com.soldier.domian.ResponseResult;
import com.soldier.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
        查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    /*
        添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        // 判断是否携带id
        if (resourceCategory.getId() == null){

            // 添加操作
            resourceCategoryService.saveResource(resourceCategory);
            return new ResponseResult(true,200,"添加成功",null);
        }else {

            // 修改操作
            resourceCategoryService.updateResource(resourceCategory);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /*
        删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        resourceCategoryService.deleteResource(id);

        return new ResponseResult(true,200,"响应成功",null);
    }
}
