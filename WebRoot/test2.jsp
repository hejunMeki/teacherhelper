<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试2 </title>
<script type="text/javascript"> 
 function changeNext(){ 
  var a=document.getElementById("img"); 
  a.src="ValidateCodeServlet?a="+new Date().getTime(); 
 } 
</script>
</head>
<body>
<form action="" method="post"> 
  <table align="center"> 
   <tr> 
    <td><img id="img" alt="" src="ValidateCodeServlet" onclick="changeNext()"></td> 
   </tr> 
  </table> 
 </form> 
 显示验证码：
 <!-- <%=session.getAttribute("code") %> -->
 <a href="testdemo.jsp">测试</a>
</body>
</html>