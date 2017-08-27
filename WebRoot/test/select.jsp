<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
</head>
<body>
	<form action="test.jsp"  method="post">
			<select id="setTime"  onchange="window.location.href='test.jsp?pid='+this.options[this.selectedIndex].value">
						<option value="2" selected>aaa</option>
						<option value="3">bbb</option>
						<option value="4">ccc</option>
			</select>
	
	</form>
</body>
</html>