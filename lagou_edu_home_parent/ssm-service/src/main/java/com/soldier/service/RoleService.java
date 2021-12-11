package com.soldier.service;

import com.soldier.domian.Role;
import com.soldier.domian.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询关联的菜单信息ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);
}
