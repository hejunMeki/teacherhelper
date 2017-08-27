package com.teacherhelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacherhelper.dao.LoginDao;
import com.teacherhelper.javabean.TeacherBean;
/*
 * �����¼��Ϣ
 */
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");      //��仰���Ͽ��Է�ֹ�����JavaScript����������
		/*
		 * ��ȡ�û��������Ϣ
		 */
		String name=req.getParameter("name");     //�û���
		String password=req.getParameter("password");     //����
		String code=req.getParameter("code");     //��֤��
		
		/*
		 * ���ж���֤��
		 */
		//��ȡ��ȷ��֤��
		HttpSession session=req.getSession();
		String trcode=(String)session.getAttribute("code");
		/*
		 * ���û��������֤�����ȷ��֤��Ƚ�
		 * ���Դ�Сд
		 */
		if(trcode==null||!trcode.equalsIgnoreCase(code))
		{
			/*
			 * ��֤�벻��ȷ
			 * �ض�λ����¼
			 */
			PrintWriter out=resp.getWriter();     //��ȡ��Ӧ�����
			out.println("<script charset='utf-8' language='javascript'>alert('��֤����󣡣���');history.go(-1);</script>");
			out.flush();    //ˢ�����
			out.close();//�ر�����Դ
			//req.getRequestDispatcher("SelectServlet").forward(req, resp); 
		}
		/*
		 * �ж��û�������
		 */
		LoginDao dao=new LoginDao();
		TeacherBean teacher=dao.toTogin(name, password);
		if(teacher!=null)
		{
			req.getSession().setAttribute("teacher", teacher);           //��session����request��ã�
			/*
			 * �жϸý�ʦ��Ȩ��
			 */
			if(teacher.getPermission()==1)
			{
				req.getRequestDispatcher("index.jsp").forward(req, resp); 
			}
			else if(teacher.getPermission()==2)
			{
				req.getRequestDispatcher("index.jsp").forward(req, resp); 
			}
			else
				req.getRequestDispatcher("index.jsp").forward(req, resp); 
			
		}
		else
		{
			PrintWriter out=resp.getWriter();     //��ȡ��Ӧ�����
			out.println("<script charset='utf-8' language='javascript'>alert('�û������������');history.go(-1);</script>");
			out.flush();    //ˢ�����
			out.close();//�ر�����Դ
		}
	}

}
