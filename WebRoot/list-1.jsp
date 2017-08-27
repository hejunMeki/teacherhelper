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
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> </div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">   
        <li>时间区间搜索
			<!-- 没有设置选定？？？ -->
        <select name="s_ishome" class="input" onchange="window.location.href='list-1.jsp?selecttime='+this.options[this.selectedIndex].value;"   style="width:200px; line-height:17px; display:inline-block">
           <c:forEach var="currentTime" items="${sessionScope.time}" varStatus="tindex">
           			<option value="${currentTime}" >${currentTime}</option>         		
           </c:forEach>
          <option value="all">查看历史授课</option>
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
 			tm=defaulttime;
 		}
 		else{
 			tm=request.getParameter("selecttime")+"";    //避免空
 		//	if(tm==null)
 			//	System.out.println("哈哈哈哈哈");
 			if(tm.equals("all"))
 			{
 				isAll=true;
 			}
 		}
    
    %>
    
    
    <table class="table table-hover text-center">
      <tr>
        <th width="150" style="text-align:left; padding-left:20px;">课程名称</th>
        <th>职称系数</th>
        <th>课程系数</th>
        <th>人数</th>
        <th>班数N</th>
        <th>理论学时</th>
        <th>难度系数</th>
        <th>实验学时</th>
        <th>讲课教时</th>
      	<%
      	if(isAll){
      	%>
      	<th>授课时间</th>
      	<%} %>
      </tr>



 		<%
 		CourseInfoDao dao=new CourseInfoDao();
 		double alltimes=0;      //记录授课总共学时
 		double ctime=0;     //记录课程设计总共学时
 		double gtime=0;      //记录毕业设计总共学时
 		double sumTimes=0;    //全部学时
 		//获取教师id
 		TeacherBean teacher=(TeacherBean)session.getAttribute("teacher");
 		if(teacher!=null)
 		{
 			String tId=teacher.gettId();    //教师号
 	 		//System.out.print(tId);
 	 		double pRa=teacher.getpRatio();
 	 		//String tm="2016-2017/2";
 	 		List list;     //接收课程
 	 		List list2;   //接收课程设计
 	 		List list3;   //接收毕业设计
 	 		//判断是否是查询全部
 	 		if(isAll)   //tm.equals("all")
 	 		{
 	 			list=dao.getAllClassesByTid(tId, pRa);
 	 			list2=dao.getAllCdesignByTea(tId);
 	 			list3=dao.getAllGdsnByTid(tId,pRa);
 	 		}
 	 		else
 	 		{
 	 			list=dao.getClasses(tId, tm, pRa);
 	 			list2=dao. getCdesignByTeaTm(tId, tm);
 	 			list3=dao. getGdsnByTidTm(tId, tm,pRa);
 	 		}
 	 		for(int i=0;i<list.size();i++)
 	 		{
 	 			ClassesBean classes=(ClassesBean)list.get(i);
 	 			double t=classes.getTestTimes();
 	 			double c=classes.getClassTimes();
 	 			alltimes+=t+c;
 	 			%>
 	 			<tr>
          <td style="text-align:left; padding-left:20px;">
           <%=classes.getcName() %></td>
          <td> <%=classes.getpRadio()%></td>
           <td> <%=classes.getDifficulty()%></td>
          <td><%=classes.getStuNum()%></td>
          <td><%=classes.getClaNum()%></td>
           <td><%=classes.getcHours()%></td> 
          <td><%=classes.getDftNum()%></td>
           <td><%=t%></td>
          <td><%=c%></td>
          <%if(isAll)
          {%>
        	  <td><%=classes.getTerms()%></td>
         <% }
     
        	  %>
          </tr>
 			<%
 		}
 		%>
 		<%
 		alltimes=dao.formatDouble(alltimes);      
 		%>		
 		<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><span>总学时：<%=alltimes%></span></td></tr>
 		
 		<tr>
        <th width="150" style="text-align:left; padding-left:20px;">课程名称</th>
        <th>周数N</th>
        <th>学生数</th>
        <th></th> <th></th> <th></th> <th></th><th></th>
        <th>课程设计教时</th>
      	<%
      	if(isAll){
      	%>
      	<th>课设时间</th>
      	<%} %>
 		
 		 </tr>
      <% for(int i=0;i<list2.size();i++)
 	 		{
 	 			CdesignBean classes=(CdesignBean)list2.get(i);
 	 			double t=classes.getTimes();
 	 			ctime+=t;
 	 			%>
 	 			<tr>
 	          <td style="text-align:left; padding-left:20px;">
 	           <%=classes.getcName() %></td>
 	           <td><%=classes.getWeeks()%></td>
 	          <td><%=classes.getStuNum()%></td>
 	            <td></td><td></td><td></td><td></td><td></td>
 	          <td><%=t%></td>
 	          <%if(isAll)
 	          {%>
 	        	  <td><%=classes.getTerms()%></td>
 	         <% }
 	     
 	        	  %>
 	          </tr>
 	 			<%
 	 		}
 	 		%>
 		<%ctime=dao.formatDouble(ctime);  %>			
 		<tr></td><td></td><td></td><td><td></td><td></td><td></td><td></td><td></td><td><span>总学时：<%=ctime%></span></td></tr> 		
 		
 		<tr>
 		<th width="150" style="text-align:left; padding-left:20px;">       </th>
        <th >职称系数</th>
        <th>学生人数</th>
        <th>指导周数</th>
        <th></th> <th></th> <th></th> <th></th>
        <th>毕业设计教时</th>
      	<%
      	if(isAll){
      	%>
      	<th>毕业时间</th>
      	<%} %>
      </tr>
 		
 		<% 
 		for(int i=0;i<list3.size();i++)
 	 		{
 	 			GdesignBean classes=(GdesignBean)list3.get(i);
 	 			double t=classes.getTimes();
 	 			gtime+=t;
 	 			%>
 	 			<tr>
 	 			<td style="text-align:left; padding-left:20px;"></td>
 	          <td >
 	           <%=classes.getpId()%></td>
 	           <td><%=classes.getStuNum()%></td>
 	          <td><%=classes.getWeeks()%></td>
 	           <td></td><td></td><td></td><td></td>
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
 	<%gtime=dao.formatDouble(gtime); %>		
 		<tr><td></td><td></td><td></td><td><td></td><td></td><td></td><td></td><td><span>总学时：<%=gtime%></span></tr>
 		
 		<%} %>	
 		<tr>
 				
 		</tr>
 		<tr>
 				<td></td><td></td><td></td><td></td>
 				<%
 				sumTimes=alltimes+gtime+ctime;
 				sumTimes=dao.formatDouble(sumTimes);
 				%>
 				<td>全部学时<%=sumTimes%></td>
 				<td></td><td></td><td></td>
 		</tr>
     </table>
  </div>
</form> 
<script type="text/javascript">
//搜索
function changesearch(){	
		var tm=document.s_ishome.value;
}
</script>
</body>
</html>