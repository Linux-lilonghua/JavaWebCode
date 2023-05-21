<%--
  Created by IntelliJ IDEA.
  User: lilonghua
  Date: 2023/4/24
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校级课程管理系统</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
</head>
<body>
<div class="box">
<div class="box1">
<img src="${pageContext.request.contextPath}/Image/pexels-pixabay-2619091.jpg" height="100%" width="100%">
</div>
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
</div>
</body>

<style>
    .box{
        margin-right: 0;
        margin-left: 0;
        zoom: 1;
        display: block;
        box-sizing: border-box;
    }
    .box1{
        height: 100%;
        width: 60%;
        display: block;
        box-sizing: border-box;
        float: left;
    }
    .box2 {
        width: 40%;
        height: 100%;
        display: block;
        box-sizing: border-box;
        float: left;
        text-align: center;
        margin-top: 10%;
    }
     .box2 button {
        margin-top: 0px;
        width: 60px;
        height: 30px;
        border-radius: 10px;
        border: 0;
        color: #fff;
        font-size: 10px;
        background-image: linear-gradient(to right, #30cfd0, #330867);
    }
</style>
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
</html>
