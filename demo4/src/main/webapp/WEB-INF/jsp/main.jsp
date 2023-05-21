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
