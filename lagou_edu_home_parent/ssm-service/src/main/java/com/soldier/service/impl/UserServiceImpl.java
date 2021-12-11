package com.soldier.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soldier.dao.UserMapper;
import com.soldier.domian.*;
import com.soldier.service.UserService;
import com.soldier.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());

        List<User> list = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {

        userMapper.updateUserStatus(id,status);
    }

    @Override
    public User login(User user) throws Exception {

        // 1.调用mapper方法
        User user1 = userMapper.login(user);

        if (user1 != null && Md5.verify(user.getPassword(),"soldier",user1.getPassword())){
            return user1;
        }else {

            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {

        List<Role> list = userMapper.findUserRelationRoleById(id);

        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {

        // 1.根据用户ID清空中间表的关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        // 2.在重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {

            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        // 1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        // 2.获取角色id保存到List集合中
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {

            roleIds.add(role.getId());
        }

        // 3.根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 4.查询封装父菜单关联的子菜单

        for (Menu menu : parentMenu) {

            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getParentId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        // 6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"响应成功",map);
    }
}
