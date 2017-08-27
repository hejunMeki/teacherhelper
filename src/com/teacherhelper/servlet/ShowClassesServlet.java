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
		//�����������
		req.setCharacterEncoding("UTF-8");
		//��ȡҪ��ѯ��ʱ��
		String currentTime=req.getParameter("ctt");
		//��ȡ��¼���û�
		TeacherBean teacher=(TeacherBean)req.getSession().getAttribute("teacher");
		//����service����
		CourseInfoService service=new CourseInfoService();
		//ְ��ϵ��
		double pRa=teacher.getpRatio();
		//��ʦid
		String tId=teacher.gettId();
		//������Ӧ��service����
		List<ClassesBean>	classes=null;
		List<CdesignBean>	cDesigns=null;
		List<GdesignBean>	gDesigns=null;
		//��ѯȫ��
		if(currentTime.equals("all"))
		{
			classes=service.getAllClassesByTid(tId, pRa);
			cDesigns=service.  getAllCdesignByTea(tId);
			gDesigns=service. getAllGdsnByTid(tId, pRa);
		}
		else
		{
			//����ѧ�ڲ�ѯ
			classes=service.getClasses(tId, currentTime, pRa);
			cDesigns=service. getCdesignByTeaTm(tId, currentTime);
			gDesigns=service. getGdsnByTidTm(tId, currentTime,pRa);
		}
		/*
		 * ����Щֵ������������
		 */
		req.setAttribute("classes", classes);
		req.setAttribute("cDesigns", cDesigns);
		req.setAttribute("gDesigns", gDesigns);
		req.setAttribute("ctTime", currentTime);
		/*
		 * ������ת��
		 */
		req.getRequestDispatcher("/list.jsp").forward(req, resp);;
		
	}
	

}
