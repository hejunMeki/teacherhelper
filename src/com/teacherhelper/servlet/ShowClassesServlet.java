package com.teacherhelper.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacherhelper.javabean.CdesignBean;
import com.teacherhelper.javabean.ClassesBean;
import com.teacherhelper.javabean.GdesignBean;
import com.teacherhelper.javabean.TeacherBean;
import com.teacherhelper.service.CourseInfoService;
@WebServlet("/showClasses")
public class ShowClassesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置请求编码
		req.setCharacterEncoding("UTF-8");
		//获取要查询的时间
		String currentTime=req.getParameter("ctt");
		//获取登录的用户
		TeacherBean teacher=(TeacherBean)req.getSession().getAttribute("teacher");
		//创建service对象
		CourseInfoService service=new CourseInfoService();
		//职称系数
		double pRa=teacher.getpRatio();
		//教师id
		String tId=teacher.gettId();
		//调用相应的service方法
		List<ClassesBean>	classes=null;
		List<CdesignBean>	cDesigns=null;
		List<GdesignBean>	gDesigns=null;
		//查询全部
		if(currentTime.equals("all"))
		{
			classes=service.getAllClassesByTid(tId, pRa);
			cDesigns=service.  getAllCdesignByTea(tId);
			gDesigns=service. getAllGdsnByTid(tId, pRa);
		}
		else
		{
			//按照学期查询
			classes=service.getClasses(tId, currentTime, pRa);
			cDesigns=service. getCdesignByTeaTm(tId, currentTime);
			gDesigns=service. getGdsnByTidTm(tId, currentTime,pRa);
		}
		/*
		 * 将这些值保存在请求中
		 */
		req.setAttribute("classes", classes);
		req.setAttribute("cDesigns", cDesigns);
		req.setAttribute("gDesigns", gDesigns);
		req.setAttribute("ctTime", currentTime);
		/*
		 * 将请求转发
		 */
		req.getRequestDispatcher("/list.jsp").forward(req, resp);;
		
	}
	

}
