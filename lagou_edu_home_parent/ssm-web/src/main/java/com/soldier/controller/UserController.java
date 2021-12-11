package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domian.ResponseResult;
import com.soldier.domian.Role;
import com.soldier.domian.User;
import com.soldier.domian.UserVo;
import com.soldier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页&多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        return responseResult;
    }

    /*
        修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){

        userService.updateUserStatus(id,status);

        Map<String, String> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    /*
        用户登陆
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.login(user);
        if (user1 != null){

            // 保存用户id及access_toke到session中
            HttpSession session = request.getSession();
            String access_toke = UUID.randomUUID().toString();
            session.setAttribute("access_toke",access_toke);
            session.setAttribute("user_id",user1.getId());

            // 将查询到的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_toke",access_toke);
            map.put("user_id",user1.getId());

            // 查询出来的user响应到前台,存到map中
            map.put("user",user1);

            ResponseResult responseResult = new ResponseResult(true, 1, "响应成功", map);
            return responseResult;

        }else {
            return new ResponseResult(false,400,"用户名密码错误",null);
        }
    }

    /*
        分配角色（回显）
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){

        List<Role> list = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"响应成功",list);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"响应成功",null);
    }

    /*
        获取用户权限进行菜单动态展示的方法
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        // 1.获取请求头中的toke
        String header_toke = request.getHeader("Authorization");

        // 2.获取session中的toke
        String session_toke = (String) request.getSession().getAttribute("access_toke");

        // 3.判断toke是否一致
        if (header_toke.equals(session_toke)){
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }
    }
}
