<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${teacher==null}">
	<%
	response.sendRedirect("login.jsp");%>
</c:if>
<!-- 获取当前查询的学期时间 -->
<c:set var="tm" value="${requestScope.ctTime}" scope="page"/>
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
//导出到excel
function exportClick(){	
		var tm=document.getElementById("ddd");
		var index=tm.selectedIndex;
		var time=tm.options[index].value;
		window.location.href='excelServlet?ctt='+time;
}
</script>
</head>
<body>
<form method="post" action="showClasses" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> </div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">   
        <li>时间区间搜索
			<!--  -->
        <select id="ddd" name="s_ishome" class="input" onchange="window.location.href='showClasses?ctt='+this.options[this.selectedIndex].value;"   style="width:200px; line-height:17px; display:inline-block">
           <c:forEach var="currentTime" items="${sessionScope.time}" varStatus="tindex">
           			<c:if test="${currentTime==tm}">
           					<option value="${currentTime}"  selected>${currentTime}</option>
           			</c:if>
           			<c:if test="${currentTime!=tm}">
           					<option value="${currentTime}" >${currentTime}</option>
           			</c:if>     		
           </c:forEach>
           <c:if test="${tm=='all'}">
           		<option value="all" selected>历史授课</option>
           </c:if>
           <c:if test="${tm!=all}">
           		<option value="all">历史授课</option>
           </c:if>
          
          </select>
    
        </li>
        <li> <a class="button border-main icon-edit" href="javascript:void(0);" onclick="exportClick()"> 导出Excel</a> </li>
      </ul>
    </div> 
   
    
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
        <!-- 这样比较对不对    待确定-->
        <c:if test="${tm=='all'}">
        		<th>授课时间</th>
        </c:if>
     
      </tr>
	<c:forEach var="ClsBean"  items="${requestScope.classes}">
	<!-- 计算总学时 -->
	<c:set var="addTime" value="${addTime+ClsBean.testTimes+ClsBean.classTimes}"/>
				<tr>
          <td style="text-align:left; padding-left:20px;">${ClsBean.cName}</td>
          <td> ${ClsBean.pRadio}</td>
           <td>${ClsBean.difficulty}</td>
          <td>${ClsBean.stuNum}</td>
          <td>${ClsBean.claNum}</td>
           <td>${ClsBean.cHours}</td> 
          <td>${ClsBean.dftNum}</td>
           <td>${ClsBean.testTimes}</td>
          <td>${ClsBean.classTimes}</td>
           <c:if test="${tm=='all'}">
        		<td>${ClsBean.terms}</td>
        </c:if>
      </tr>
      
      
	</c:forEach>
	
	<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>	
 		<tr>
        <th width="150" style="text-align:left; padding-left:20px;">课程名称</th>
        <th>周数N</th>
        <th>学生数</th>
        <th></th> <th></th> <th></th> <th></th><th></th>
        <th>课程设计教时</th>
        <c:if test="${tm=='all'}">
        		<th>课设时间</th>
        </c:if>
 		 </tr>
 		 
		<c:forEach var="CDesignBean"  items="${requestScope.cDesigns}">
		<c:set var="addTime" value="${addTime+CDesignBean.times}"/>
				<tr>
          <td style="text-align:left; padding-left:20px;">${CDesignBean.cName}</td>
          <td> ${CDesignBean.weeks}</td>
           <td>${CDesignBean.stuNum}</td>
           <td></td><td></td><td></td><td></td><td></td>
          <td>${CDesignBean.times}</td>
           <c:if test="${tm=='all'}">
        		<td>${CDesignBean.terms}</td>
        </c:if>
        <!-- 暂不做计算总共学时   ${allTimes=allTimes+ClsBean.testTimes+ClsBean.classTimes}-->
      </tr>
	</c:forEach>
 	<tr><td></td><td></td><td><td></td><td></td><td></td><td></td><td></td><td></td></tr> 		
 		
 	
 		<tr>
 		<th width="150" style="text-align:left; padding-left:20px;">       </th>
        <th >职称系数</th>
        <th>学生人数</th>
        <th>指导周数</th>
        <th></th> <th></th> <th></th> <th></th>
        <th>毕业设计教时</th>
       <c:if test="${tm=='all'}">
        		<th>毕业时间</th>
        </c:if>
      </tr>
 		
 		
 		<c:forEach var="GDesignBean"  items="${requestScope.gDesigns}">
 		<c:set var="addTime" value="${addTime+GDesignBean.times}"/>
				<tr>
				<td></td>
          <td style="text-align:left; padding-left:20px;">${GDesignBean.pId}</td>
          <td> ${GDesignBean.stuNum}</td>
           <td>${GDesignBean.weeks}</td>
           <td></td><td></td><td></td><td></td>
          <td>${GDesignBean.times}</td>
           <c:if test="${tm=='all'}">
        		<td>${GDesignBean.term}</td>
        </c:if>
        <!-- 暂不做计算总共学时   ${allTimes=allTimes+ClsBean.testTimes+ClsBean.classTimes}-->
      	</tr>
	</c:forEach>
	
 		<tr><td></td><td></td><td></td><td><td></td><td></td><td></td><td></td><td></td></tr>
 		<tr>
 				
 		</tr>
 		<tr>
 				<td></td><td></td><td></td><td></td>
 			<!-- 先不计算 -->
 				<td><span>总教时：<c:out value="${addTime}"/></span></td>
 				<td></td><td></td><td></td><td></td>
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