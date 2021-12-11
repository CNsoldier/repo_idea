package com.soldier.service.impl;

import com.soldier.dao.MenuMapper;
import com.soldier.domian.Menu;
import com.soldier.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    @Override
    public List<Menu> findAllMenu() {

        List<Menu> list = menuMapper.findAllMenu();
        return list;
    }

    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
