package com.soldier.controller;

import com.soldier.domian.Menu;
import com.soldier.domian.ResponseResult;
import com.soldier.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
        查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> list = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    /*
        回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        // 根据id的值判断当前操作是更新还是添加操作，判断id值是否为-1
        if (id == -1){
            // 添加操作 回显信息中不需要查询menu信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加回显成功", map);
            return responseResult;
        }else {
            // 修改操作 回显所有menu信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            ResponseResult responseResult = new ResponseResult(true, 200, "修改回显成功", map);
            return responseResult;
        }
    }
}
