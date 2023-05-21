
## **实验一 基于 Mybatis 的校级课程管理程序**

### 一、任务目的

掌握 MyBatis 的环境搭建、核心配置文件、映射文件，学会使用基于 XML 和基于注解的 MyBatis 进行关系数据库的增删查改操作。

### 二、实验要求

1. 技术选型：Java + Spring + MyBatis
2. 实验结果在 Test 包中创建测试类，将实验结果直接输出到控制台
3. 提交实验报告([github地址](https://github.com/Linux-lilonghua/JavaWebCode/tree/master/demo1))

### 三、实验环境

* JDK 1.8.0_171
* MySQL 8.0.31
* maven-3.9.1
* IDEA-2022

### 四、实验内容

##### 1.搭建实验数据库。

创建 mybatis 数据库，并创建两个表，分别为⼀个课程表 c_course 和⼀个学院表 c_course, 学院表和课程表之间是⼀对多的关系。
课程表 c_course 表:
|课程id(id) |课程名(name)|课时(hours)|开课学院(sid)|
|:-:|:--:|:-:|:-:|
|1|C语言程序设计|70|1|
|2|Python程序设计|70|1|
|3|大学英语|96|2|
|4|高级Web技术|32|1|
|5|改革开放与新时期党的历史研究|32|3|
|6|企业战略管理|32|4|
学院表 s_school 表:
|学院 id(id)|学院名称(schoolname)|
|:-:|:-:|
|1|计算机学院|
|2|外国语学院|
|3|马克思学院|
|4|商学院|

SQL 代码如下：

```
USE mybatis;
CREATE TABLE s_school (
id int(32) PRIMARY KEY AUTO_INCREMENT,
schoolname varchar(40)
);
INSERT INTO s_school VALUES (1, '计算机学院');
INSERT INTO s_school VALUES (2, '外国语学院');
INSERT INTO s_school VALUES (3, '⻢克思学院');
INSERT INTO s_school VALUES (4, '商学院');

CREATE TABLE c_course (
id int(32) PRIMARY KEY AUTO_INCREMENT,
name varchar(40),
hours int,
sid int(32) NOT NULL,
FOREIGN KEY(sid) REFERENCES s_school(id)
);
INSERT INTO c_course VALUES (1, 'C语⾔程序设计', 70,1);
INSERT INTO c_course VALUES (2, 'Python程序设计', 70,1);
INSERT INTO c_course VALUES (3, '⼤学英语', 96,2);
INSERT INTO c_course VALUES (4, '⾼级Web技术', 32,1);
INSERT INTO c_course VALUES (5, '改⾰开放与新时期党的历史研究', 32,3);
INSERT INTO c_course VALUES (6, '企业战略管理', 32,4);
```

##### 2.创建 Maven 工程，命名为 demo1，在 pom.XML 中导入了相关依赖。

```
 <dependencies>
        <!--Mybatis核心包-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.13</version>
        </dependency>
        <!--MYSQL驱动包-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.32</version>
        </dependency>
        <!--JUnit测试包-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

##### 3.连接数据库，创建 db.properties 文件。

```
mysql.driver=com.mysql.cj.jdbc.Driver
mysql.url=jdbc:mysql://localhost:3306/mybatis?\
  serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false
mysql.username=root
mysql.password=llh1314520
```

##### 4. 编写 MyBatis 核心配置文件 mybatis-config.xml。

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 环境配置 -->
    <!-- 加载类路径下的属性⽂件 -->
    <properties resource="db.properties"/>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!-- 数据库连接相关配置 ,db.properties⽂件中的内容-->
            <dataSource type="POOLED">
                <property name="driver" value="${mysql.driver}"/>
                <property name="url" value="${mysql.url}"/>
                <property name="username" value="${mysql.username}"/>
                <property name="password" value="${mysql.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mappers/CourseMapper.xml"/>
    </mappers>
</configuration>
```

##### 5.在 src/main/java 目录下创建 org.example.pojo 包，并创建 Course 和 School 两个持久化类，并分别在类中定义相关属性。

```
package org.example.pojo;

public class Course {
      private int id; //课程id
      private String name; //课程名
      private int hours; //课时
      private int sid;  //开课学院
      private String schoolname; //学院名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", sid=" + sid +
                '}';
    }

    public String allinfo(){
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", sid=" + sid +
                ", schoolname='" + schoolname + '\'' +
                '}';
    }
}

```

##### 6.在 src/main/resources 目录下创建一个mappers文件夹，在mappers文件下创建 Course 的 映 射 文 件 CourseMapper.xml，在文件中编写配置。

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.example.pojo.Course">
    <select id="findById" parameterType="int" resultType="org.example.pojo.Course">
        select * from c_course where id=#{id}
    </select>
    <select id="findBySchoolName" parameterType="String" resultType="org.example.pojo.Course">
        select * from c_course as c,s_school as s where c.sid=s.id and s.schoolname=#{id}
    </select>
    <select id="findByAll"  resultType="org.example.pojo.Course">
        select c.id,name,hours,sid from c_course as c,s_school as s where sid in
        (select id from s_school) and s.id=c.sid order by c.sid
    </select>
    <update id="updateByHours" parameterType="int">
        update c_course set hours=hours+#{hours} where id=#{id}
    </update>
    <insert id="insertByCourse" parameterType="org.example.pojo.Course">
        insert into c_course(id,name,hours,sid)values(#{id},#{name},#{hours},#{sid})
    </insert>
</mapper>
```

##### 7. 在核心配置文件 mybatis-config.xml 中添加 mapper 子标签指定 CourseMapper.xml 配置文件所在的位置。

```
 <mappers>
        <mapper resource="mappers/CourseMapper.xml"/>
 </mappers>
```

##### 8.MyBatisUtils ⼯具类。

```
package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    // 初始化SqlSessionFactory对象
    static {
        try {
// 使⽤MyBatis提供的Resources类加载MyBatis的配置⽂件
            Reader reader =
                    Resources.getResourceAsReader("mybatis-config.xml");
// 构建SqlSessionFactory⼯⼚
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 获取SqlSession对象的静态⽅法
    public static SqlSession getSession() {
        //开启一个事务
        return sqlSessionFactory.openSession(true);
    }
}
```

##### 9.编写测试类。

```
Scanner sc = new Scanner(System.in);
MybatisUtil mybatisUtil = new MybatisUtil();
SqlSession sqlSession = mybatisUtil.getSession();
```

①查询 id=2 的课程信息；
```
 @Test
    //通过id查询的课程信息
    public void findById(){
        System.out.print("请输入您要查询的课程id：");
        int it = sc.nextInt();
        Course course = sqlSession.selectOne("findById", it);
        System.out.println("查询id=" + it + "的课程信息为：");
        System.out.println(course.toString());
    }
```

②查询出所有计算机学院开设的课程信息 ；
```
@Test
    //通过学院名称查出这个学院开的所有课程信息
    public void findBySchoolName() {
        System.out.print("请输入您要查询的学院名称：");
        String str = sc.next();
        List<Course> list = sqlSession.selectList("findBySchoolName", str);
        System.out.println("查询" + str + "的所有课程信息结果为：");
        System.out.println(list);
    }
```

③将 id=4 这⻔课程的课时数修改为 32+8=40；
```
 @Test
    //通过课程id修改这⻔课程的课时数
    public void updateByHours() {
        Course course = new Course();
        System.out.print("请输入要修改课程课时数的id：");
        int it=sc.nextInt();
        System.out.print("请输入要增加的课程课时数数量：");
        int num=sc.nextInt();
        course.setId(it);
        course.setHours(num);
        int result = sqlSession.update("updateByHours", course);
        if(result==1)
        System.out.println("修改成功!");
        else System.out.println("修改失败！");
    }
```

④插入一条新的课程记录： name=”大数据存储“，hours=32，sid =1；
```
 @Test
    //插⼊⼀条新的课程记录
    public void insertByCourse() {
        Course course = new Course();
        System.out.print("请输入要插入的课程id：");
        int id=sc.nextInt();
        System.out.print("请输入要插入的课程名：");
        String str=sc.next();
        System.out.print("请输入要插入的课时：");
        int hour=sc.nextInt();
        System.out.print("请输入要插入的开课学院id：");
        int sid=sc.nextInt();
        course.setSid(id);
        course.setName(str);
        course.setHours(hour);
        course.setSid(sid);
        int result = sqlSession.insert("insertByCourse", course);
        if(result==1)
        System.out.println("插入成功！");
        else System.out.println("插入失败！");
    }
```

⑤输出所有的学院开设的课程信息。
```
 @Test
        //输出所有的学院开设的课程信息
    public void findByAll() {
        List<Course> list = sqlSession.selectList("findByAll");
            System.out.println("所有的学院开设的课程信息为");
            System.out.println(list);
        }
```

### 五、实验结果

1. 查询 id=2 的课程信息；
![](D:/VSCode/code/Markdown/image/1.1.png)
2. 查询出所有计算机学院开设的课程信息 ；
![](D:/VSCode/code/Markdown/image/1.2.png)
3. 将 id=4 这⻔课程的课时数修改为 32+8=40；
![](D:/VSCode/code/Markdown/image/1.3.png)
4. 插入一条新的课程记录： name=”大数据存储“，hours=32，id =1；
![](D:/VSCode/code/Markdown/image/1.4png)
5. 输出所有的学院开设的课程信息。
![](D:/VSCode/code/Markdown/image/1.5.png)

### 六、实验心得

通过这个实验，我学会了如何通过 MyBatis 配置文件和映射文件来实现对数据库表的增删改查等操作，能够更好地理解 MyBatis 的核心组件之间的关系，如SqlSessionFactory、SqlSession 和 Mapper 接口等。
