package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * �������ݿ�float�Ŀ����Ͷ���������������
 */
public class testDao {
    	public static void main(String args[])
    	{
    		Connection conn=GetConnection.getCon();    		
    		try {
    			String sql="select * from test";
				Statement statement=conn.createStatement();
				ResultSet result=statement.executeQuery(sql);
				if(result.next())
				{
					System.out.println(result.getDouble("id"));
					System.out.println(result.getString("name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

}
