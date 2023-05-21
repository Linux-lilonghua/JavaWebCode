package org.example.dao;
import org.example.pojo.Course;
import java.util.List;

public interface CourseMapper {
    int addCourse(Course course);//增加一个Course
    int deleteCourseById(int id);//根据id删除一个Course
    int updateCourse(Course course);//修改一个Course
    Course queryCourseById(int id);//根据id查询,返回一个Course
    List<Course> queryAllCourse();//查询全部Course,返回list集合
}
