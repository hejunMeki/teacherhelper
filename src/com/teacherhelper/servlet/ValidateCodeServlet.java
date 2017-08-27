package com.teacherhelper.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * time:2017/05/24 ������֤��
 */
public class ValidateCodeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  VCode v = new VCode(70,35); 
		  BufferedImage img = v.getImage(); 
		  String str=v.getStrbdr();   //Ϊʲô��һ��û��ִ����
		  System.out.println(":::::"+str);
		  HttpSession session=request.getSession();
		  session.setAttribute("code", str);     //�����ɵ���֤����session��
		  v.saveImage(img,response.getOutputStream()); 
		  
		
		 } 
		  
		 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  VCode v = new VCode(70,35 ); 
		  BufferedImage img = v.getImage(); 
		  String str=v.getStrbdr();
		  System.out.println(":::::"+str);
		  HttpSession session=request.getSession();
		  session.setAttribute("code", str);     //�����ɵ���֤����session��
		  v.saveImage(img,response.getOutputStream());     //������֤��ͼƬ��ʾ
		  
		 
		 } 

}
