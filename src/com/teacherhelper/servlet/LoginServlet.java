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
 * 处理登录信息
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
		resp.setContentType("text/html;charset=utf-8");      //这句话加上可以防止下面的JavaScript弹出框乱码
		/*
		 * 获取用户输入的信息
		 */
		String name=req.getParameter("name");     //用户名
		String password=req.getParameter("password");     //密码
		String code=req.getParameter("code");     //验证码
		
		/*
		 * 先判断验证码
		 */
		//获取正确验证码
		HttpSession session=req.getSession();
		String trcode=(String)session.getAttribute("code");
		/*
		 * 将用户输入的验证码和正确验证码比较
		 * 忽略大小写
		 */
		if(trcode==null||!trcode.equalsIgnoreCase(code))
		{
			/*
			 * 验证码不正确
			 * 重定位到登录
			 */
			PrintWriter out=resp.getWriter();     //获取响应输出流
			out.println("<script charset='utf-8' language='javascript'>alert('验证码错误！！！');history.go(-1);</script>");
			out.flush();    //刷新输出
			out.close();//关闭流资源
			//req.getRequestDispatcher("SelectServlet").forward(req, resp); 
		}
		/*
		 * 判断用户名密码
		 */
		LoginDao dao=new LoginDao();
		TeacherBean teacher=dao.toTogin(name, password);
		if(teacher!=null)
		{
			req.getSession().setAttribute("teacher", teacher);           //放session还是request里好？
			/*
			 * 判断该教师的权限
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
			PrintWriter out=resp.getWriter();     //获取响应输出流
			out.println("<script charset='utf-8' language='javascript'>alert('用户名或密码错误');history.go(-1);</script>");
			out.flush();    //刷新输出
			out.close();//关闭流资源
		}
	}

}
