package com.soldier.service.impl;

import com.soldier.dao.RoleMapper;
import com.soldier.domian.Role;
import com.soldier.domian.RoleMenuVo;
import com.soldier.domian.Role_menu_relation;
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
}
