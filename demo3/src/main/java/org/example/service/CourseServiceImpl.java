package org.example.service;
import org.example.dao.CourseMapper;
import org.example.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper courseMapper;
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    public int deleteCourseById(int id) {
        return courseMapper.deleteCourseById(id);
    }

    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    public Course queryCourseById(int id) {
        return courseMapper.queryCourseById(id);
    }

    public List<Course> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }

}
