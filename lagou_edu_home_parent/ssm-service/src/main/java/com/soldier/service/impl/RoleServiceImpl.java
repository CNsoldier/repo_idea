package com.soldier.service.impl;

import com.soldier.dao.RoleMapper;
import com.soldier.domian.*;
import com.soldier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        // 1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        // 2.为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);

            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");

            roleMapper.roleContextMenu(roleMenuRelation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {

        // 调用根据roleID清空中间表关系
        roleMapper.deleteRoleContextMenu(roleId);


        roleMapper.deleteRole(roleId);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        List<ResourceCategory> categoryList = roleMapper.findResourceCategoryById(roleId);
        for (ResourceCategory resourceCategory : categoryList) {

            List<Resource> resourceList = roleMapper.findResourceByRoleId(resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
            }
        return categoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVO roleResourceVO) {
        
        // 1.清空关联表关系
        roleMapper.deleteRoleResourceRelation(roleResourceVO.getRoleId());
        
        // 2.为角色分配资源
        for (Integer resourceId : roleResourceVO.getResourceIdList()) {

            // 1.封装数据
            Role_resource_relation roleResourceRelation = new Role_resource_relation();
            Date date = new Date();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVO.getRoleId());
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            // 2.调用mapper
            roleMapper.roleContextResource(roleResourceRelation);
        }
    }
}
