package com.soldier.service;

import com.soldier.domian.Course;
import com.soldier.domian.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
        新增课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        根据ID查询课程信息
     */
    public CourseVO findCourseById(Integer id);

    /*
        更新课程及讲师信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        课程状态管理
     */
    public void updateCourseStatus(int courseId,int status);

}
