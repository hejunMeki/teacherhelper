<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${teacher==null}">
	<%
	response.sendRedirect("login.jsp");%>
</c:if>
<%@ page import="com.teacherhelper.dao.*" %>
<%@ page import="com.teacherhelper.javabean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<script type="text/javascript">
//搜索

</script>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> </div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">   
        <li>时间区间搜索
			<!-- 没有设置选定？？？ -->
        <select name="s_ishome" class="input" onchange="window.location.href='list-5.jsp?selecttime='+this.options[this.selectedIndex].value"   style="width:200px; line-height:17px; display:inline-block">
           <c:forEach var="currentTime" items="${sessionScope.time}"  >
           			
           		
           						<option value="${currentTime}" >${currentTime}</option>
           
           </c:forEach>
          <option value="all">查看历史毕设</option>
          </select>
    
        </li>
        <li> <a class="button border-main icon-edit" href="add.html"> 导出Excel</a> </li>
      </ul>
    </div>
    <%
        String tm;
        Boolean isAll=false;
		String defaulttime=request.getParameter("ctt");
		if(defaulttime!=null)
 		{
 			if(defaulttime.endsWith("1"))
 				tm=defaulttime;			
 			else 
 				tm=null;
 		}
 		else{
 			tm=request.getParameter("selecttime")+"";
 			if(tm.equals("all"))
 			{
 				isAll=true;
 			}
 		}
    
    %>
    <table class="table table-hover text-center">
      <tr>
        <th width="150" style="text-align:left; padding-left:20px;">职称系数</th>
        <th>学生人数</th>
        <th>指导周数</th>
        <th>毕业教时</th>
      	<%
      	if(isAll){
      	%>
      	<th>毕业时间</th>
      	<%} %>
      </tr>

 		<%
 		CourseInfoDao dao=new CourseInfoDao();
 		double alltimes=0;      //记录总共学时
 		//获取教师id
 		TeacherBean teacher=(TeacherBean)session.getAttribute("teacher");
 		if(teacher!=null)
 		{
 			String tId=teacher.gettId();    //教师号
 	 		//System.out.print(tId);
 	 		double pRa=teacher.getpRatio();
 	 		//String tm="2016-2017/2";
 	 		List list;
 	 		//判断是否是查询全部
 	 		if(isAll)   //tm.equals("all")
 	 		{
 	 			list=dao.getAllGdsnByTid(tId,pRa);
 	 		}
 	 		else
 	 		{		
 	 				list=dao. getGdsnByTidTm(tId, tm,pRa);
 	 		}		
 	 		for(int i=0;i<list.size();i++)
 	 		{
 	 			GdesignBean classes=(GdesignBean)list.get(i);
 	 			double t=classes.getTimes();
 	 			alltimes+=t;
 	 			%>
 	 			<tr>
 	          <td style="text-align:left; padding-left:20px;">
 	           <%=classes.getpId()%></td>
 	           <td><%=classes.getStuNum()%></td>
 	          <td><%=classes.getWeeks()%></td>
 	          <td><%=t%></td>
 	          <%if(isAll)
 	          {%>
 	        	  <td><%=classes.getTerm()%></td>
 	         <% }
 	     
 	        	  %>
 	          </tr>
 	 			<%
 	 		}
 	 		%>
 	<% }alltimes=dao.formatDouble(alltimes); %>		
 		<tr><td></td><td></td><td></td><td><span>总学时：<%=alltimes%></span></tr>
     </table>
  </div>
</form> 
</body>
</html>