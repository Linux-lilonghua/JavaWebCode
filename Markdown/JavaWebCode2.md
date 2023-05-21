## **基于 SpringMVC 的应用**

### 一、任务目的

掌握 MyBatis 的环境搭建、核心配置文件、映射文件，学会使用基于 XML 和基于注解的 MyBatis 进行关系数据库的增删查改操作。

### 二、实验要求

1. 技术选型：Java + Spring + MyBatis +SpringMVC
2. 实验交互及结果要以Web视图形式进⾏展示
3. 提交实验报告([github地址](https://github.com/Linux-lilonghua/JavaWebCode/tree/master/demo1))

### 三、实验环境

* JDK 1.8.0_171
* MySQL 8.0.31
* maven-3.9.1
* IDEA-2022
* tomcat7

### 四、实验内容

该实验继续沿⽤⾼级Web 技术实验⼀中的题⽬背景，结合自己设计的数据模型和表设计，在此基础上，结合SpringMVC 框架完成。

##### 1.展示课程

①allCourse.jsp 核心部分。
```
 <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>课程id</th>
                    <th>课程名</th>
                    <th>课时数</th>
                    <th>课程所属学院id</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${requestScope.get('course')}">
                    <tr>
                        <td>${course.getId()}</td>
                        <td>${course.getName()}</td>
                        <td>${course.getHours()}</td>
                        <td>${course.getSid()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/course/toUpdateCourse?id=${course.getId()}">更改</a> |
                            <a href="${pageContext.request.contextPath}/course/del/${course.getId()}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
```

②Dao 层映射文件 CourseMapper.XML 核心部分
```
 <select id="queryAllCourse" resultType="Course">
        select * from mybatis.c_course;
</select>
```

③Service 层
```
 public List<Course> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }
```

④Controller 层
```
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
```

##### 2.新增课程

①addCourse.jsp 核心部分。
```
<form action="${pageContext.request.contextPath}/course/addCourse" method="post">
    课程名：<input type="text" name="name"><br><br><br>
    课时数：<input type="text" name="hours"><br><br><br>
    课程所属学院id：<input type="text" name="sid"><br><br><br>
    <input type="submit" value="添加">
  </form>
```

②Dao 层映射文件 CourseMapper.XML 核心部分
```
 <insert id="addCourse" parameterType="Course">
        insert into mybatis.c_course(name,hours,sid)
        values (#{name},#{hours},#{sid});
    </insert>
```

③Service 层
```
public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }
```

④Controller 层
```
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
```

##### 3. 修改课程

①updateCourse.jsp 核心部分。
```
<form action="${pageContext.request.contextPath}/course/updateCourse" method="post">
        <input type="hidden" name="id" value="${course.getId()}"/>
        课程名：<input type="text" name="name" value="${course.getName()}"/>
        课时数：<input type="text" name="hours" value="${course.getHours()}"/>
        课程所属学院：<input type="text" name="sid" value="${course.getSid()}"/>
        <input type="submit" value="提交"/>
    </form>
```

②Dao 层映射文件 CourseMapper.XML 核心部分
```
<update id="updateCourse" parameterType="Course">
        update mybatis.c_course
        set name = #{name},hours = #{hours},sid=#{sid}
        where id = #{id};
    </update>
```
③Service 层
```
 public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }
```

④Controller 层
```
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
```

##### 4. 删除课程

①Dao 层映射文件 CourseMapper.XML 核心部分
```
 <delete id="deleteCourseById" parameterType="int">
        delete from mybatis.c_course where id = #{id};
    </delete>
```

②Service 层
```
 public int deleteCourseById(int id) {
        return courseMapper.deleteCourseById(id);
    }

```

③Controller 层
```
 //删除课程信息
    @RequestMapping("/del/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return "redirect:/course/allCourse";
    }
```

### 五、实验结果

##### 1. 展示课程：

![](D:/VSCode/code/Markdown/image/2.1.png)

##### 2. 新增课程：

![](D:/VSCode/code/Markdown/image/2.2.png)

##### 3. 修改课程：

![](D:/VSCode/code/Markdown/image/2.3.png)

### 六、实验心得

本次实验通过跟着网上的视频教程一起学习完成，帮助我掌握了 SpringMVC 的数据绑定和响应，学会了使用 JSP 页面视图进行交互和结果的页面展示，让我对SpringMVC 的执行流程和工作原理有了更深入的理解，在用户提交表单时，我们可以使用数据绑定将表单数据绑定到后端 Java 对象中，然后通过业务逻辑处理后，再将处理结果返回给前端页面。在这个过程中，SpringMVC 的数据绑定是比较高效灵活的。
