package com.teacherhelper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.teacherhelper.javabean.CdesignBean;
import com.teacherhelper.javabean.ClassesBean;
import com.teacherhelper.javabean.GdesignBean;

/*
 * ��ʦ�Ŀγ���Ϣ���ݿ����
 */
public class CourseInfoDao {
	private Connection conn;
	private Statement statement;         //����ִ��
	private PreparedStatement prestatement;       //Ԥ����ִ��
	private ResultSet result,result2;        //�����
	private List<ClassesBean> list;        //�ڿμ���
	private List<CdesignBean> dlist;      //�γ���Ƽ���
	private List<GdesignBean> glist;               //��ҵ��Ƽ���
	/*
	 * ���ݰ༶�Ų�ѯ����
	 * �÷������������������� ��������������conn����
	 */
	public int getStuNumber(String classId)
	{
		int number=0;		
		try {
			String sql="call proce_selectClass(?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, classId);
			result2=prestatement.executeQuery();
			if(result2.next())
				number=result2.getInt("nunmber");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}
	
	/*
	 * time:2017/05/21
	 * ���ݽ�ʦ�ź�ʱ����ҽ�ʦ�ο���Ϣ  ͬʱ����ְ��ϵ��
	 * ִ�д洢����proce_sltClsByTeaTerm
	 * ��β�ѯ�༶���� �����ķ���Ч�ʸ���
	 */
	public List<ClassesBean> getClasses(String tId,String term,double a)
	{
		list=new ArrayList<ClassesBean>();
		conn=GetConnection.getCon(); 
		try {
			String sql="call proce_sltClsByTeaTerm(?,?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			prestatement.setString(2, term);
			result=prestatement.executeQuery();
			while(result.next())
			{
				String classId=result.getString("classId");
				String[] str=classId.split(",");      //�и��ַ���
				int classNumber=str.length;      //�༶��
				int studentNumber=0;
				ClassesBean cb=new ClassesBean();
				double b=result.getDouble("difficulty");      //�γ�ϵ��
				double c=result.getDouble("dftNum");      //�ڿ��Ѷ�ϵ��
				double cHours=result.getDouble("cHours");   //�ڿ����ۿ�ʱ
				/*
				 * ����ʵ���ڿ�ѧʱ
				 * �����ṩ�Ĺ�ʽ����
				 * double���͵�λ��̫��
				 */
				double classTimes=a*(b*(1+(classNumber-1)*0.2))*cHours*c;
				classTimes=formatDouble(classTimes);    //����С��
				/*
				 * ����ʵ��ѧʱ����Ҫע��
				 */
				double testTime=result.getDouble("testTimes");
				/*
				 * �ÿγ���ʵ��ͼ���ʵ��ʵ��ѧʱ
				 */
				double testTimes=0;    //����ʵ��ʵ��ѧʱ
				if(testTime!=0.0)
				{
					testTimes=a*testTime*classNumber*c;
					testTimes=formatDouble(testTimes);    //����С��
				}		
				/*
				 * ѭ����ѯ�༶����
				 * ����getStuNumber����
				 */
				for(int i=0;i<classNumber;i++)
				{
					studentNumber+=getStuNumber(str[i]);
				}
			/*
			 * ��ʼ����õĲ�����װ
			 */
				cb.setcName(result.getString("cName"));       // �γ�����
				cb.setTerms(term);       //�ڿ�����
				cb.setpRadio(a);     //ְ��ϵ��
				cb.setDifficulty(b);     //�γ�ϵ��
				cb.setStuNum(studentNumber);    //ѧ������
				cb.setClaNum(classNumber);    // �༶��
				cb.setcHours(cHours);     //�γ�����ѧʱ
				cb.setDftNum(c);     //�ڿ��Ѷ�ϵ��
				cb.setTestTimes(testTimes);     //ʵ��ʵ��ѧʱ
				cb.setClassTimes(classTimes);    //�ڿ�ʵ��ѧʱ
				list.add(cb);     //���뼯��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return list;
	}
	
	/*
	 * time:2017/05/22
	 * ���ݽ�ʦ�Ų�ѯ�ý�ʦ��ʷ�ο���Ϣ
	 * Ϊʲô������getClasses������ʵ���أ���ΪҪ���ٶ���������ӶϿ����ݿ�����⣿�д����
	 * ����ְ��ϵ��a
	 */
	public List<ClassesBean> getAllClassesByTid(String tId,double a)
	{
		list=new ArrayList<ClassesBean>();
		conn=GetConnection.getCon(); 
		try {
			String sql="call procedure_sltClsByTea(?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			result=prestatement.executeQuery();
			while(result.next())
			{
				String classId=result.getString("classId");
				String[] str=classId.split(",");      //�и��ַ���
				int classNumber=str.length;      //�༶��
				int studentNumber=0;
				ClassesBean cb=new ClassesBean();
				double b=result.getDouble("difficulty");      //�γ�ϵ��
				double c=result.getDouble("dftNum");      //�ڿ��Ѷ�ϵ��
				double cHours=result.getDouble("cHours");   //�ڿ����ۿ�ʱ
				/*
				 * ����ʵ���ڿ�ѧʱ
				 * �����ṩ�Ĺ�ʽ����
				 * double���͵�λ��̫��
				 */
				double classTimes=a*(b*(1+(classNumber-1)*0.2))*cHours*c;
				classTimes=formatDouble(classTimes);   //����
				/*
				 * ����ʵ��ѧʱ����Ҫע��
				 */
				double testTime=result.getDouble("testTimes");
				/*
				 * �ÿγ���ʵ��ͼ���ʵ��ʵ��ѧʱ
				 */
				double testTimes=0;    //����ʵ��ʵ��ѧʱ
				if(testTime!=0.0)
				{
					testTimes=a*testTime*classNumber*c;
					testTimes=formatDouble(testTimes);   //����
				}		
				/*
				 * ѭ����ѯ�༶����
				 * ����getStuNumber����
				 */
				for(int i=0;i<classNumber;i++)
				{
					studentNumber+=getStuNumber(str[i]);
				}
			/*
			 * ��ʼ����õĲ�����װ
			 */
				cb.setcName(result.getString("cName"));       // �γ�����
				cb.setTerms(result.getString("terms"));       //�ڿ�����
				cb.setpRadio(a);     //ְ��ϵ��
				cb.setDifficulty(b);     //�γ�ϵ��
				cb.setStuNum(studentNumber);    //ѧ������
				cb.setClaNum(classNumber);    // �༶��
				cb.setcHours(cHours);     //�γ�����ѧʱ
				cb.setDftNum(c);     //�ڿ��Ѷ�ϵ��
				cb.setTestTimes(testTimes);     //ʵ��ʵ��ѧʱ
				cb.setClassTimes(classTimes);    //�ڿ�ʵ��ѧʱ
				list.add(cb);     //���뼯��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return list;
	}
	
	/*
	 * time��2017/05/23
	 * ���ݽ�ʦ�ź�ѧ��ʱ���ѯ�ý�ʦ��ѧ�ڵĿγ��������
	 * ͨ���洢����proce_sltDdsnByTeaʵ��
	 */
	public List<CdesignBean> getCdesignByTeaTm(String tId,String term)
	{
		dlist=new ArrayList<CdesignBean>();
		conn=GetConnection.getCon(); 
		try {
			String sql="call proce_sltDdsnByTea(?,?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			prestatement.setString(2, term);
			result=prestatement.executeQuery();
			while(result.next())
			{
//				String classId=result.getString("classId");
//				String[] str=classId.split(",");      //�и��ַ���
//				int classNumber=str.length;      //�༶��
//				int studentNumber=0;
				CdesignBean cb=new CdesignBean();
				int studentNumber=result.getInt("stus");//��ȡѧ������
				/*
				 * ѭ����ѯ�༶����
				 * ����getStuNumber����
				 */
//				for(int i=0;i<classNumber;i++)
//				{
//					studentNumber+=getStuNumber(str[i]);
//				}
				int weeks=result.getInt("week");
				double times=studentNumber*weeks;
				times=formatDouble(times); 
			/*
			 * ��ʼ����õĲ�����װ
			 */
				cb.setcName(result.getString("cName"));       // �γ�����
				cb.setTerms(term);       //�ڿ�����
				cb.setStuNum(studentNumber);    //ѧ������
				cb.setWeeks(weeks);    //����
				cb.setTimes(times);    //ʵ�ʽ�ʱ
				dlist.add(cb);     //���뼯��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return dlist;
	}
	
	/*
	 * ����proce_selAllCdsnByTea�洢���̲�ѯ��ʦ��ʷ�γ����
	 */
	
	public List<CdesignBean> getAllCdesignByTea(String tId)
	{
		dlist=new ArrayList<CdesignBean>();
		conn=GetConnection.getCon(); 
		try {
			String sql="call proce_selAllCdsnByTea(?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, tId);
			result=prestatement.executeQuery();
			while(result.next())
			{
//				String classId=result.getString("classId");
//				String[] str=classId.split(",");      //�и��ַ���
//				int classNumber=str.length;      //�༶��
//				int studentNumber=0;
				CdesignBean cb=new CdesignBean();
				int studentNumber=result.getInt("stus");
				/*
				 * ѭ����ѯ�༶����
				 * ����getStuNumber����
				 */
//				for(int i=0;i<classNumber;i++)
//				{
//					studentNumber+=getStuNumber(str[i]);
//				}
				int weeks=result.getInt("week");
				double times=studentNumber*weeks;
				times=formatDouble(times); 
			/*
			 * ��ʼ����õĲ�����װ
			 */
				cb.setcName(result.getString("cName"));       // �γ�����
				cb.setTerms(result.getString("csDsTime"));       //�ڿ�����
				cb.setStuNum(studentNumber);    //ѧ������
				cb.setWeeks(weeks);    //����
				cb.setTimes(times);    //ʵ�ʽ�ʱ
				dlist.add(cb);     //���뼯��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return dlist;
	}
	/*
	 * ���ݽ�ʦ�ź�ѧ�ڲ�ѯ��ҵ��ƿγ���Ϣ
	 * ���ô洢����proce_sltGdsnByTeaTmʵ��
	 * �������pId
	 */
	public List<GdesignBean> getGdsnByTidTm(String id,String term,double pId)
	{
		glist=new ArrayList<GdesignBean>();
		conn=GetConnection.getCon();
		try {
			String sql="call proce_sltGdsnByTeaTm(?,?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, id);
			prestatement.setString(2, term);
			result=prestatement.executeQuery();
			while(result.next())
			{
				GdesignBean gdsn=new GdesignBean();
				int stuNum=result.getInt("stunum");
				int weeks=result.getInt("week");
				//�����ҵ��ʱ
				double times=pId*stuNum*weeks;
				times=formatDouble(times); 
				gdsn.setpId(pId);
				gdsn.setStuNum(stuNum);
				gdsn.setTerm(term);
				gdsn.setTimes(times);
				gdsn.setWeeks(weeks);
				glist.add(gdsn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return glist;
	}
	
	
	/*
	 * ���ݽ�ʦ�Ų�ѯ��ʷ���б�ҵ��ƿγ���Ϣ
	 * ���ô洢����proce_sltAllGdsnByTeaʵ��
	 * �������pId
	 */
	public List<GdesignBean> getAllGdsnByTid(String id,double pId)
	{
		glist=new ArrayList<GdesignBean>();
		conn=GetConnection.getCon();
		try {
			String sql="call proce_sltAllGdsnByTea(?)";
			prestatement=(PreparedStatement) conn.prepareStatement(sql);
			prestatement.setString(1, id);
			result=prestatement.executeQuery();
			while(result.next())
			{
				GdesignBean gdsn=new GdesignBean();
				int stuNum=result.getInt("stunum");
				int weeks=result.getInt("week");
				//�����ҵ��ʱ
				double times=pId*stuNum*weeks;
				times=formatDouble(times); 
				gdsn.setpId(pId);
				gdsn.setStuNum(stuNum);
				gdsn.setTerm(result.getString("gDsTime"));
				gdsn.setTimes(times);
				gdsn.setWeeks(weeks);
				glist.add(gdsn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return glist;
	}
	/*
	 * �ر���Դ
	 * GetConnection���еķ�����������Ƿ�Ϊ��
	 */
	public void closeAll()
	{
			GetConnection.closeResult(result2);
			GetConnection.closeResult(result);
			GetConnection.closeStatement(statement);
			GetConnection.closeStatement(prestatement);
			GetConnection.closeCon(conn);	
	}
	
	/*
	 * double�����������С�������Сλ��
	 */
	public double formatDouble(double dbl)
	{
		NumberFormat format=NumberFormat.getInstance();
		format.setMaximumFractionDigits(3);     
		double fmtdbl=Double.parseDouble(format.format(dbl));
		return fmtdbl;
	}
	/*
	 * ����1����  getStuNumber  ���Գɹ�
	 * ����2���� getClasses���Գɹ�
	 * ����3���� getAllClassesByTid�ɹ�
	 * ���Է���4 getCdesignByTeaTm  �ɹ�
	 * ���Է���5 getAllCdesignByTea  �ɹ�
	 * ���Է���6 getGdsnByTidTm �ɹ�
	 * ���Է���7 getAllGdsnByTid
	 */
	public static void main(String[] args) {
//		int i=new CourseInfoDao().getStuNumber("1401");
//		System.out.println(i);
//		int j=new CourseInfoDao().getClasses("00001", "2017-2018/1");
//		System.out.println(j);
//		List<ClassesBean> list=new CourseInfoDao().getClasses("00002", "2017-2018/2", 1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			ClassesBean cls=list.get(i);	
//			System.out.println("�༶����"+cls.getClaNum()+":::ѧ����:::"+cls.getStuNum()+":::ʵ��ѧʱ::::"+cls.getTestTimes()+":::�ڿ�ѧʱ:::"+cls.getClassTimes()+":::::�ڿ�ѧ��"+cls.getTerms());
//		}
//		List<ClassesBean> list=new CourseInfoDao().getAllClassesByTid("00003", 1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			ClassesBean cls=list.get(i);	
//			System.out.println("�༶����"+cls.getClaNum()+":::ѧ����:::"+cls.getStuNum()+":::ʵ��ѧʱ::::"+cls.getTestTimes()+":::�ڿ�ѧʱ:::"+cls.getClassTimes()+":::::�ڿ�ѧ��"+cls.getTerms());
//		}
//		List<CdesignBean> list=new CourseInfoDao().getCdesignByTeaTm("00001","2017-2018/2");
//		for(int i=0;i<list.size();i++)
//		{
//			CdesignBean cls=list.get(i);	
//			System.out.println(":::ѧ����:::"+cls.getStuNum()+":::�ڿ�ѧʱ:::"+cls.getTimes()+":::::�ڿ�ѧ��"+cls.getTerms()+"�ڿ�����"+cls.getWeeks()+"�ڿ�"+cls.getcName());
//		}
		List<CdesignBean> list=new CourseInfoDao().getAllCdesignByTea("00002");
		for(int i=0;i<list.size();i++)
		{
			CdesignBean cls=list.get(i);	
			System.out.println(":::ѧ����:::"+cls.getStuNum()+":::�ڿ�ѧʱ:::"+cls.getTimes()+":::::�ڿ�ѧ��"+cls.getTerms()+"�ڿ�����"+cls.getWeeks()+"�ڿ�"+cls.getcName());
		}
//		List<GdesignBean> list=new CourseInfoDao().getGdsnByTidTm("00001", "2016-2017/2", 1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			GdesignBean cls=list.get(i);	
//			System.out.println(":::ѧ����:::"+cls.getStuNum()+":::�ڿ�ѧʱ:::"+cls.getTimes()+"�ڿ�����"+cls.getWeeks()+"�ڿ�����"+cls.getTerm()+"ְλϵ����"+cls.getpId());
//		}
//		List<GdesignBean> list=new CourseInfoDao().getAllGdsnByTid("00002",  1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			GdesignBean cls=list.get(i);	
//			System.out.println(":::ѧ����:::"+cls.getStuNum()+":::�ڿ�ѧʱ:::"+cls.getTimes()+"�ڿ�����"+cls.getWeeks()+"�ڿ�����"+cls.getTerm()+"ְλϵ����"+cls.getpId());
//		}
	}

}
