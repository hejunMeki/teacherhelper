<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:useBean id="login" class="com.teacherhelper.javabean.LoginBean"/>
<jsp:setProperty property="tId" name="login"/>
<jsp:setProperty property="password" name="login"/>

<jsp:useBean id="teachlog" class="com.teacherhelper.dao.LoginDao"/>

</jsp:setProperty>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试登录</title>
</head>
<body>
			
</body>
</html>