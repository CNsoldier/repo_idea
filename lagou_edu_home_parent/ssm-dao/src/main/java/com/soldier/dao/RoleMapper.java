package com.soldier.dao;

import com.soldier.domian.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询关联的菜单信息ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);

    /*
        查询当前角色所拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryById(Integer roleId);

    /*
        查询当前角色所拥有的资源信息
     */
    public List<Resource> findResourceByRoleId(Integer id);

    /*
        1.根据roleId清空中间表的关联关系
     */
    public void deleteRoleResourceRelation(Integer roleId);

    /*
        2.为角色分配资源信息
     */
    public void roleContextResource(Role_resource_relation role_resource_relation);
}
