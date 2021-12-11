package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domian.Resource;
import com.soldier.domian.ResourceVo;
import com.soldier.domian.ResponseResult;
import com.soldier.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    public ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        return responseResult;
    }
}
