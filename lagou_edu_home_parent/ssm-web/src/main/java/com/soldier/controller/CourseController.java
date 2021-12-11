package com.soldier.controller;

        import com.soldier.domian.Course;
        import com.soldier.domian.CourseVO;
        import com.soldier.domian.ResponseResult;
        import com.soldier.service.CourseService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
        import java.io.File;
        import java.io.IOException;
        import java.lang.reflect.InvocationTargetException;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 多条件课程列表查询
     * @param courseVO
     * @return
     */
    /**
     * 多条件课程列表查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {
        // 调用 service
        List<Course> courseList = courseService.findCourseByCondition(courseVO);

        // 返回响应参数的 JSON 字符串
        return new ResponseResult(true, 200, "响应成功", courseList);
    }

    /*
        课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

        // 1.判断接收到的上传文件是否为空
        if(file.isEmpty()){

            throw new RuntimeException();
        }

        // 2.获取项目的部署路径
        // D:\Tomcat-8.5.55\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        // 3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        // 4.生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }

        // 图片进行了上传
        file.transferTo(filePath);

        // 6.将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成", map);

        return responseResult;
    }

    /*
        新增课时及讲师信息
        新增课时信息和修改课时信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){

            // 调用service 新增操作
            courseService.saveCourseOrTeacher(courseVO);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else {

            // 更新操作
            courseService.updateCourseOrTeacher(courseVO);

            ResponseResult responseResult = new ResponseResult(true, 200, "更新成功", null);
            return responseResult;
        }

    }

    /*
        根据ID查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVO);

        return responseResult;
    }

    /*
        课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){

        // 调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }
}
