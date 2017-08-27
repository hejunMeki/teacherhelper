package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;
import com.teacherhelper.javabean.TeacherBean;

/*
 * login�����ݿ����
 * time:2017/05/20
 * ͨ���洢����ʵ�ֲ�ѯ
 */
public class LoginDao {
	private Connection conn;
	private Statement statement;         //����ִ��
	private PreparedStatement prestatement;       //Ԥ����ִ��
	private ResultSet result;        //�����
	/*
	 * ���ݵ�¼�˺������ѯ�Ƿ���ڸ��û�
	 *������   ��ѯ����¼��ʦ��ȫ����Ϣ
	 */
	public TeacherBean toTogin(String tId,String password)
	{
		conn=GetConnection.getCon();    //��ȡ���ݿ�����		
		try {
			String sql="call proce_login(?,?)";      //����sqlִ�����
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			prestatement.setString(2,password);
			/*
			 * ��ѯ
			 */
			result=prestatement.executeQuery();
			//������û�����
			if(result.next())
			{
				/*
				 * ��ѯ��ʦ����Ϣ
				 * ͨ���洢����proce_teacher��ʵ��
				 */
				TeacherBean teacher=new TeacherBean();
				teacher.setPermission(result.getInt("permission"));   //����Ȩ��
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
	 * �ر���Դ
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
	 * ����1  toLogin����  ���Գɹ�
	 * 
	 */
//	public static void main(String[] args) {
//		LoginDao dao=new LoginDao();
//		TeacherBean login=dao.toTogin("00001", "zhoujun");
//		if(login==null)
//		{
//			System.out.println("���û������ڣ���������");
//		}
//		else
//			System.out.println(login.gettId()+":::::::"+login.getoName()+":::::::"+login.getCeName()+":::::::"+login.getpRatio()+":::::::"+login.getProName()+":::::::"+login.gettName());
	//}

}
