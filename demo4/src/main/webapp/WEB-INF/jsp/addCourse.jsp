<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>新增课程</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
</head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="page-header">
        <h1>
          <small>新增课程</small>
        </h1>
      </div>
    </div>
  </div>
  <div class="xiao">
  <form action="${pageContext.request.contextPath}/course/addCourse" method="post" enctype="multipart/form-data">
    <div class="input_box">
      课程名：<input type="text" name="name"><br><br><br>
      课时数：<input type="text" name="hours"><br><br><br>
      课程所属学院id：<input type="text" name="sid" ><br><br><br>
      <div class="btn">
        <input type="submit" value="添加"/>
      </div>
    </div>
    <div class="Imaged">
    <div id="wenZi">课程图片</div>
    <img  width="200" height="250" src="${pageContext.request.contextPath}/Image/undraw_To_the_moon_re_q21i.png"/>
    <div class="btn">
      <input type="button" value="更换图片" id="ImageButton"/>
    </div>
      <div id="fileImage">
    <input type="file" name="file" multiple="multiple" id="editImage" accept="image/*"
           style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 30px;"/>
      </div>
    </div>
  </form>
  </div>
</div>
</body>
<style>

  .Imaged{
    position: absolute;
    width: 200px;
    top: 215px;
    left: 475px;
  }
  .input_box{
    position: absolute;
    width: 200px;
    top: 227px;
    right: 465px;
  }
  .xiao {
    text-align: center;
  }
</style>

<!--图片预览功能-->
<script type="text/javascript">
  $('#ImageButton').on('click', function() {
    $('#editImage').click();
  });

  $('#editImage').on('change', function() {
    var reader = new FileReader();//创建一个文件读取对象
    reader.onload = function(e) {//图像读取完成后显示图像
      $('.Imaged img').attr('src', e.target.result);
    }
    reader.readAsDataURL($(this).get(0).files[0]);//读取图像文件
  });
</script>
</html>