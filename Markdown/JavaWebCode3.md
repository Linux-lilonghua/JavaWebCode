## **基于 SpringMVC 的高级应用**

### 一、任务目的

掌握SpringMVC的⽂件上传和展示。

### 二、实验要求

1、技术选型：Java + Spring + MyBatis + SpringMVC
2、提交实验报告([github地址](https://github.com/Linux-lilonghua/JavaWebCode/tree/master/demo1))

### 三、实验环境

* JDK 1.8.0_171
* MySQL 8.0.31
* maven-3.9.1
* IDEA-2022
* tomcat7

### 四、实验内容

##### 1. 在 pom.XML 配置依赖 commons-fileupload 和 commons-io ，Maven 将会自动从 Maven 中央库中下载这些依赖项的 jar 包，并将其添加到项目的类路径下，以供项目使用。

```
<!--文件上传所需要的依赖-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.5</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.13.4</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.4.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.13.4</version>
        </dependency>
```

##### 2. 在 Spring-mvc.XML 中加入 MultipartFile 接口，用于接收上传的文件内容。

```
<!--配置多部件解析器-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--设置请求编码方式-->
        <property name="defaultEncoding" value="UTF-8"/>
        <!--设置允许上传文件的最大值-->
        <property name="maxUploadSize" value="9097152"/>
    </bean>
```

##### 3. 文件上传。通过 MultipartFile 类型的文件对象获取上传文件的原始文件名，提取出文件后缀，使用自己编写的JSONFileUtils类的静态方法生成一个新的唯一的文件名来保存上传的文件。

```
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
            course.setImage("test1.jpg");
        }

        courseService.addCourse(course);
        return "redirect:/course/allCourse";
    }
```

### 五、实验结果

##### 1. 展示课程：

![](D:/VSCode/code/Markdown/image/3.1.png)

##### 2. 新增课程：

![](D:/VSCode/code/Markdown/image/3.2.png)

##### 3. 修改课程：

![](D:/VSCode/code/Markdown/image/3.3.png)

### 六、实验心得

学会了文件的上传和下载以及图片的显示。
