package com.soldier.dao;

import com.soldier.domian.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
        用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(@Param("id") int id,@Param("status") String status);

    /*
        用户登陆（根据用户名查询具体的用户信息）
     */
    public User login(User user);

    /*
        根据用户id清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
        1.根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(int id);

    /*
        2.根据角色ID查询角色所拥有的的顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(@Param("ids") List<Integer> ids);

    /*
        3.根据Pid，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(int pid);

    /*
        4.获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(@Param("ids") List<Integer> ids);

    public List<Resource> findResourceById(@Param("ids") List<Integer> ids);

    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test71();
    public void test81();
    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
    public void test7();
    public void test8();
}
