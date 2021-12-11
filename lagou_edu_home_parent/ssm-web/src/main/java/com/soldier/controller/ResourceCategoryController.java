package com.soldier.controller;

import com.soldier.domian.ResourceCategory;
import com.soldier.domian.ResponseResult;
import com.soldier.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
