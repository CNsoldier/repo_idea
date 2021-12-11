package com.soldier.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soldier.dao.ResourceMapper;
import com.soldier.domian.Resource;
import com.soldier.domian.ResourceVo;
import com.soldier.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {

        // 分页查询
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> list = resourceMapper.findAllResourceByPage(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
