package com.soldier.controller;

import com.soldier.domian.Menu;
import com.soldier.domian.ResponseResult;
import com.soldier.domian.Role;
import com.soldier.domian.RoleMenuVo;
import com.soldier.service.MenuService;
import com.soldier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*
        查询角色信息
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> list = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /*
        查询所有父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        // -1 查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    /*
        根据角色ID查询关联的菜单信息ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> list = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;

    }

    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;
    }

}
