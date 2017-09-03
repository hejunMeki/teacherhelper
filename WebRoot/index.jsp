<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="teacher" value="${sessionScope.teacher}" ></c:set>
<c:if test="${teacher==null}">
	<%response.sendRedirect("login.jsp");%>
</c:if>
<jsp:useBean id="ct" class="com.teacherhelper.javabean.SelectTimeBean"></jsp:useBean>
<c:set var="ctime" value="${ct.currenttime}" scope="session"></c:set>   <!-- 当前学期时间 -->
<jsp:useBean id="slttime" class="com.teacherhelper.javabean.SelectTimeBean"/>
<c:set var="time" value="${slttime.list}" scope="session"></c:set> <!-- 学期列表 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>    
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1>教师工作量助手 </h1>
  </div>
 <div class="head-l"> <span class="user-info">欢迎您 ${teacher.ceName} ${teacher.proName} ${teacher.tName}(${teacher.oName},${teacher.tId})</span><a class="button button-little bg-red" href="ExitServlet"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>个人工作量查询</h2>
  <ul style="display:block">
     <li><a href="showClasses?ctt=${ctime}" target="right"><span class="icon-caret-right"></span>查询全部</a></li>
    <li><a href="list-1.jsp?ctt=${ctime}" target="right"><span class="icon-caret-right"></span>讲课学时</a></li>
    <li><a href="list-4.jsp?ctt=${ctime}" target="right"><span class="icon-caret-right"></span>课设实习</a></li>
    <li><a href="showClasses?ctt=${ctime}" target="right"><span class="icon-caret-right"></span>毕业设计</a></li>  
    <li><a href="CdesignServlet" target="right"><span class="icon-caret-right"></span>课程设计分配</a></li> 
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>其他设置</h2>
  <ul>
    <li><a href="pass.html" target="right"><span class="icon-caret-right"></span>密码修改</a></li>
    <!-- <li><a href="add.html" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
    <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>分类管理</a></li>  -->       
  </ul>  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  
  
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="info.html" name="right" width="100%" height="100%"></iframe>
</div>

</body>
</html>