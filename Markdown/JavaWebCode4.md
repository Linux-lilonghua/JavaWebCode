## **高级 Web 技术实验四：基于 SSM 的校级课程管理系统**

### 一、任务目的

进一步掌握 SpringMVC 的数据绑定和⻚面跳转技术，能利用 JSP 视图技术来完成实际系统的开发。

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

##### 1. 用户登陆：如果用户输入正确的邮箱密码以及验证码，提交表单给后端，后端查询数据库后确认邮箱和密码匹配后则自动进入到课程管理主界面。否则，返回登陆失败的提示。页面上如果邮箱和密码有一个为空的时候点击登陆按钮时前端需要显示“不能为空”。

①login.jsp
```
<form action="${pageContext.request.contextPath}/login" name="forms" method="post">
    <div class="box2">
            <span>${msg}</span><br/>
            <h2>LOGIN</h2>
            <p></p>
            <div id="input_box">
                <input id="username" type="text" name="email" placeholder="用户名">
            </div><br>
            <div>
                <input id="pwd" type="password" name="password" placeholder="密码">
            </div><br>
            <div>
                <input id="checkcode" type="text" name="checkcode" placeholder="请输入验证码">
            </div><br>
            <div>
                <img src="checkCode.login" alt="" width="170"
                      class="passcode" style="height: 30px;
                                        cursor: pointer;"
                     onclick="this.src=this.src+'?'"><br>
            </div>
            <div><br>
                <button id="btn" onclick="login()">登录</button>
            </div>
    </div>
</form>
```

②javascript
```
<script>
    function login() {
        var x = document.getElementById("username").value;
        var y = document.getElementById("pwd").value;
        var z = document.getElementById("checkcode").value;
        if (x == null || x == "") {
            alert("用户名不能为空，请输入用户名");
        }
        else if (y == null || y == "") {
            alert("密码不能为空，请输入密码");
        }
        else if (z == null || z == "") {
            alert("验证码不能为空，请输入验证码");
        }
        else {
           document.getElementsByTagName("forms").submit();
        }
    }
</script>
```

Controller 层
```
 /*
    用户登入
     */
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, String checkcode){
        //RandomValidateCode.RANDOMCODEKEY为工具类定义的变量，即验证码图片内正确识别后的数据
        String vcode = (String)
                request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
        System.out.println(vcode);
        try{
            User u=userService.login(user);
            /*
            用户名和密码是否查询用户信息
            是：跳转到后台首页
            否：跳转到登入页面
             */
                if (u != null) {
                    if (checkcode.equals(vcode)) {
                        request.getSession().setAttribute("USER_SESSION", u);
                        return "main";
                    }
                    else {
                        request.getSession().setAttribute("msg", "验证码错误！");
                        return "redirect:tologin";
                    }
                }
                else {
                    request.getSession().setAttribute("msg", "用户名或密码错误!");
                    return "redirect:tologin";
                }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统出错啦！");
            return "redirect:tologin";
        }
    }
```

##### 2. 用户登陆成功，转向课程管理主界面main.jsp，右上角显示用户邮箱和一个“退出”按钮（或超链接）。

```
<%--
  Created by IntelliJ IDEA.
  User: lilonghua
  Date: 2023/4/24
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台系统</title>
</head>
<body>
<li>您好:${USER_SESSION.name}</li>
<li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
<li><a href="${pageContext.request.contextPath}/course/allCourse">课程信息</a></li>
</body>
</html>
```

##### 3.定义一个请求拦截器，用于对用户请求进行拦截和处理。

在Spring MVC中配置拦截器

```
<!--配置拦截器-->
    <mvc:interceptors>
        <!--拦截所有请求-->
        <bean class="org.example.interceptor.ResourcesInterceptor"/>
    </mvc:interceptors>
```
创建一个ResourcesInterceptor类去继承HandlerInterceptorAdapter接口
```
public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception{
        User user=(User)request.getSession().getAttribute("USER_SESSION");
        //如果用户是已登入状态,放行
        if(user!=null){
            return true;
        }
        //获取请求路径
        String url=request.getRequestURI();
        //用户登入的相关请求,放行
        if(url.indexOf("login")>=0 || url.indexOf(".jpg")>=0)
            return true;
        //其他情况都直接跳转到登入页面
        request.setAttribute("msg","您还没有登入,请先登入!");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
```

##### 4.生成验证码图片的工具类。
```
package org.example.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RandomValidateCode {
    public static final String RANDOMCODEKEY = "randomcode_key";// 放到session中的key
    private Random random = new Random();
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串
    private int width = 80;// 图片宽
    private int height = 26;// 图片高
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(160, 200));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /*
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
```

### 五、实验结果

1.前端用户登录界面

![](D:/VSCode/code/Markdown/image/4.1.png)

2.用户非法登入

![](D:/VSCode/code/Markdown/image/4.2.png)

3.用户登陆成功，转向课程管理主界面，右上角显示用户邮箱和一个“退出”按钮（或超链
接）。

![](D:/VSCode/code/Markdown/image/4.3.png)

### 六、实验心得

进一步了掌握 SpringMVC 的数据绑定和页面跳转技术，能利用 JSP 视图技术来完成实际系统的开发，在做实验过程中通过上网查资料修改，学会了把生成验证码图片功能加入登录界面，将其与用户输入的验证码比较，提高了登录注册的安全性，整个实验过程让我对 ssm 的整合有了更深的理解，学会将业务逻辑、数据访问、控制层分离，会用注解、XML 配置完成事务管理。
