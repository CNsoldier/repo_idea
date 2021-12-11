package com.soldier.dao;

import com.soldier.domian.Resource;
import com.soldier.domian.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*
        资源分页&多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
