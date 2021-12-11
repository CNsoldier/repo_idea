package com.soldier.service;

import com.github.pagehelper.PageInfo;
import com.soldier.domian.Resource;
import com.soldier.domian.ResourceVo;

import java.util.List;

public interface ResourceService {

    /*
        资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
