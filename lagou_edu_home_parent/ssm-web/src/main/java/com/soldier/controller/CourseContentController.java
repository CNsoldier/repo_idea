package com.soldier.controller;

import com.soldier.domian.Course;
import com.soldier.domian.CourseLesson;
import com.soldier.domian.CourseSection;
import com.soldier.domian.ResponseResult;
import com.soldier.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /*
        课程内容展示
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /*
        回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        Map<String, Object> map = new HashMap<>();
        map.put("id",course.getId());
        map.put("courseName",course.getCourseName());

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    /*
        新增&更新章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        // 判断是否携带了章节ID
        if (courseSection.getId() == null){

            // 新增操作
            courseContentService.saveSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);

            return responseResult;
        }else {

            // 更新操作
            courseContentService.updateSection(courseSection);

            ResponseResult responseResult = new ResponseResult(true, 200, "更新成功", null);

            return responseResult;
        }

    }

    /*
        修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    /*
        新增课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){

        courseContentService.saveLesson(courseLesson);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;
    }
}
