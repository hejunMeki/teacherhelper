package com.teacherhelper.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * time:2017/05/24 生成验证码
 */
public class ValidateCodeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  VCode v = new VCode(70,35); 
		  BufferedImage img = v.getImage(); 
		  String str=v.getStrbdr();   //为什么这一步没有执行呢
		  System.out.println(":::::"+str);
		  HttpSession session=request.getSession();
		  session.setAttribute("code", str);     //将生成的验证码存进session域
		  v.saveImage(img,response.getOutputStream()); 
		  
		
		 } 
		  
		 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  VCode v = new VCode(70,35 ); 
		  BufferedImage img = v.getImage(); 
		  String str=v.getStrbdr();
		  System.out.println(":::::"+str);
		  HttpSession session=request.getSession();
		  session.setAttribute("code", str);     //将生成的验证码存进session域
		  v.saveImage(img,response.getOutputStream());     //将该验证码图片显示
		  
		 
		 } 

}
