package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ���ݿ�������
 */
public class GetConnection {
	private final static String username="root";
	private final static String password="root";
	private final static String URL="jdbc:mysql://localhost:3306/teacherhelper?unicode=true&characterEncoding=UTF-8";
	private final static String className="com.mysql.jdbc.Driver";
	private  static Connection con;
	public static Connection getCon()
	{
		try {
			Class.forName(className);   //��̬����������
			System.out.println("�������سɹ�������");
			con=DriverManager.getConnection(URL,username,password);
			System.out.println("���ݿ����ӳɹ�������");
			} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("��������ʧ�ܣ�����");
			e.printStackTrace();
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("���ݿ�����ʧ�ܣ�����");
				e.printStackTrace();
			}
		
		return con;
	}
	/*
	 * �ر����ݿ�
	 */
	public static void closeCon(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
				System.out.println("���ݿ�رճɹ�������");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeStatement(Statement statement)
	{
		if(statement!=null)
		{
			try {
				statement.close();
				System.out.println("���ݿ�ִ�йرճɹ�������");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * �ر����ݼ�
	 */
	public static void closeResult(ResultSet result)
	{
		if(result!=null)
		{
			try {
				result.close();
				System.out.println("���ݿ������رճɹ�������");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * �����Ƿ��������
	 */
//	public static void main(String[] args) {
//		GetConnection.getCon();
//	}

}
