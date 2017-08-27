package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;
import com.teacherhelper.javabean.TeacherBean;

/*
 * login的数据库操作
 * time:2017/05/20
 * 通过存储过程实现查询
 */
public class LoginDao {
	private Connection conn;
	private Statement statement;         //处理执行
	private PreparedStatement prestatement;       //预处理执行
	private ResultSet result;        //结果集
	/*
	 * 根据登录账号密码查询是否存在该用户
	 *存在则   查询出登录教师的全部信息
	 */
	public TeacherBean toTogin(String tId,String password)
	{
		conn=GetConnection.getCon();    //获取数据库连接		
		try {
			String sql="call proce_login(?,?)";      //定义sql执行语句
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			prestatement.setString(2,password);
			/*
			 * 查询
			 */
			result=prestatement.executeQuery();
			//如果该用户存在
			if(result.next())
			{
				/*
				 * 查询教师的信息
				 * 通过存储过程proce_teacher来实现
				 */
				TeacherBean teacher=new TeacherBean();
				teacher.setPermission(result.getInt("permission"));   //设置权限
				String sqll="call proce_teacher(?)";
				prestatement=(PreparedStatement) conn.prepareStatement(sqll);
				prestatement.setString(1, tId);
				result=prestatement.executeQuery();
				while(result.next())
				{
					teacher.settId(tId);
					teacher.setpRatio(result.getDouble("pRatio"));
					teacher.setoName(result.getString("oName"));
				    teacher.setCeName(result.getString("ceName"));
				    teacher.settName(result.getString("tName"));
				    teacher.setProName(result.getString("proName"));
				}
				return teacher;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return null;	
	}
	/*
	 * 关闭资源
	 */
	public void closeAll()
	{
		if(result!=null)
			GetConnection.closeResult(result);
		if(statement!=null)
			GetConnection.closeStatement(statement);
		if(prestatement!=null)
			GetConnection.closeStatement(prestatement);
		if(conn!=null)
			GetConnection.closeCon(conn);
	}
	
	/*
	 * 测试1  toLogin方法  测试成功
	 * 
	 */
//	public static void main(String[] args) {
//		LoginDao dao=new LoginDao();
//		TeacherBean login=dao.toTogin("00001", "zhoujun");
//		if(login==null)
//		{
//			System.out.println("该用户不存在！！！！！");
//		}
//		else
//			System.out.println(login.gettId()+":::::::"+login.getoName()+":::::::"+login.getCeName()+":::::::"+login.getpRatio()+":::::::"+login.getProName()+":::::::"+login.gettName());
	//}

}
