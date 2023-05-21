package org.example.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.Course;
import org.example.service.CourseService;
import org.example.utils.JSONFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
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
    public String addPaper(Course course, MultipartFile file, HttpServletRequest request) throws Exception {
        //设置图片存放的路径
        String path=request.getServletContext().getRealPath("/")+"Image/";
        ObjectMapper mapper=new ObjectMapper();
        if(!file.isEmpty()) {
            //获取上传文件的名称
            String filename = file.getOriginalFilename();
            System.out.println(filename);
            ArrayList<String> list=new ArrayList<>();
            //读取file.json文件中的文件名称
            String json= JSONFileUtils.readFile(path+"files.json");
            if(json.length()>0){
                //将files.json的内容转换为集合
                list=mapper.readValue(json, new TypeReference<ArrayList<String>>() {
                });
                for(String image:list){
                    //如果上传的文件在files.json文件有同名文件，将当前上传文件重命名，以避免重名
                    if(filename.equals(image)){
                        String[] split=filename.split("\\.");
                        filename=split[0]+"(1)."+split[1];
                    }
                }
            }
            //文件保存的全路径
            String filepath = path + filename;
            System.out.println(filepath);
            //保存上传的文件
            file.transferTo(new File(filepath));
            list.add(filename);
            course.setImage(filename);
            System.out.println(filename);
            //将集合转换成json
            json=mapper.writeValueAsString(list);
            //将上传文件的名称保存在files.json文件中
            JSONFileUtils.writeFile(json,path+"files.json");
        }
        else {
            course.setImage("undraw_To_the_moon_re_q21i.png");
        }

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