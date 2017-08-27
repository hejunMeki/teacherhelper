<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="testlogin.jsp" name="form1" method="post">
				<table align="center">
						<tr>
							<td>用户账号:</td>
							<td><input type="text" name="tId"></td>
						</tr>
						<tr>
							<td>用户密码:</td>
							<td><input type="password" name="password" ></td>
						</tr>
						
						<tr>
						<td></td>
						<td>
						<input type="submit" value="登录"  name="login">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置">
						</td>
						</tr>
				</table>
		
		
		</form>
</body>
</html>