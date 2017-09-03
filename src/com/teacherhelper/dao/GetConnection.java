package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 数据库连接类
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
			Class.forName(className);   //动态加载驱动类
			System.out.println("驱动加载成功！！！");
			con=DriverManager.getConnection(URL,username,password);
			System.out.println("数据库连接成功！！！");
			} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败！！！");
			e.printStackTrace();
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("数据库连接失败！！！");
				e.printStackTrace();
			}
		
		return con;
	}
	/*
	 * 关闭数据库
	 */
	public static void closeCon(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
				System.out.println("数据库关闭成功！！！");
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
				System.out.println("数据库执行关闭成功！！！");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 关闭数据集
	 */
	public static void closeResult(ResultSet result)
	{
		if(result!=null)
		{
			try {
				result.close();
				System.out.println("数据库结果集关闭成功！！！");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * 测试是否可以连接
	 */
//	public static void main(String[] args) {
//		GetConnection.getCon();
//	}

}
