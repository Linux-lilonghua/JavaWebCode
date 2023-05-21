package org.example.controller;
import org.example.pojo.Course;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping("/success")
    public String success(){
        System.out.println("成功界面！");
        return "success";
    }
    //查询全部课程信息
    @RequestMapping("/allCourse")
    public String list(Model model){
        List<Course> course = courseService.queryAllCourse();
        for(Course b:course){
            System.out.println(b);
        }
        model.addAttribute("course",course);
        return "allCourse";
    }

    //添加课程信息
    @RequestMapping("/toAddCourse")
    public String toAddPaper() {
        return "addCourse";
    }
    @RequestMapping("/addCourse")
    public String addPaper(Course course) {
        System.out.println(course);
        courseService.addCourse(course);
        return "redirect:/course/allCourse";
    }
    //修改课程信息
    @RequestMapping("/toUpdateCourse")
    public String toUpdateCourse(Model model, int id) {
        Course course = courseService.queryCourseById(id);
        System.out.println(course);
        model.addAttribute("course",course );
        return "updateCourse";
    }
    @RequestMapping("/updateCourse")
    public String updateCourse(Model model, Course course) {
        System.out.println(course);
        courseService.updateCourse(course);
        Course c = courseService.queryCourseById(course.getId());
        model.addAttribute("course",c);
        return "redirect:/course/allCourse";
    }
    //删除课程信息
    @RequestMapping("/del/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return "redirect:/course/allCourse";
    }


}