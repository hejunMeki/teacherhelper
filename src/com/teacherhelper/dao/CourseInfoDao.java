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
 * 教师的课程信息数据库操作
 */
public class CourseInfoDao {
	private Connection conn;
	private Statement statement;         //处理执行
	private PreparedStatement prestatement;       //预处理执行
	private ResultSet result,result2;        //结果集
	private List<ClassesBean> list;        //授课集合
	private List<CdesignBean> dlist;      //课程设计集合
	private List<GdesignBean> glist;               //毕业设计集合
	/*
	 * 根据班级号查询人数
	 * 该方法用于其它方法调用 与其它方法共用conn对象
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
	 * 根据教师号和时间查找教师任课信息  同时传入职称系数
	 * 执行存储过程proce_sltClsByTeaTerm
	 * 多次查询班级人数 这样的方法效率高吗
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
				String[] str=classId.split(",");      //切割字符串
				int classNumber=str.length;      //班级数
				int studentNumber=0;
				ClassesBean cb=new ClassesBean();
				double b=result.getDouble("difficulty");      //课程系数
				double c=result.getDouble("dftNum");      //授课难度系数
				double cHours=result.getDouble("cHours");   //授课理论课时
				/*
				 * 计算实际授课学时
				 * 根据提供的公式计算
				 * double类型的位数太多
				 */
				double classTimes=a*(b*(1+(classNumber-1)*0.2))*cHours*c;
				classTimes=formatDouble(classTimes);    //处理小数
				/*
				 * 计算实验学时这里要注意
				 */
				double testTime=result.getDouble("testTimes");
				/*
				 * 该课程有实验就计算实验实际学时
				 */
				double testTimes=0;    //定义实验实际学时
				if(testTime!=0.0)
				{
					testTimes=a*testTime*classNumber*c;
					testTimes=formatDouble(testTimes);    //处理小数
				}		
				/*
				 * 循环查询班级人数
				 * 调用getStuNumber方法
				 */
				for(int i=0;i<classNumber;i++)
				{
					studentNumber+=getStuNumber(str[i]);
				}
			/*
			 * 开始将获得的参数封装
			 */
				cb.setcName(result.getString("cName"));       // 课程名字
				cb.setTerms(term);       //授课日期
				cb.setpRadio(a);     //职称系数
				cb.setDifficulty(b);     //课程系数
				cb.setStuNum(studentNumber);    //学生人数
				cb.setClaNum(classNumber);    // 班级数
				cb.setcHours(cHours);     //课程理论学时
				cb.setDftNum(c);     //授课难度系数
				cb.setTestTimes(testTimes);     //实验实际学时
				cb.setClassTimes(classTimes);    //授课实际学时
				list.add(cb);     //加入集合
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
	 * 根据教师号查询该教师历史任课信息
	 * 为什么不调用getClasses方法来实现呢？因为要面临多次连续连接断开数据库的问题？有待解决
	 * 传入职称系数a
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
				String[] str=classId.split(",");      //切割字符串
				int classNumber=str.length;      //班级数
				int studentNumber=0;
				ClassesBean cb=new ClassesBean();
				double b=result.getDouble("difficulty");      //课程系数
				double c=result.getDouble("dftNum");      //授课难度系数
				double cHours=result.getDouble("cHours");   //授课理论课时
				/*
				 * 计算实际授课学时
				 * 根据提供的公式计算
				 * double类型的位数太多
				 */
				double classTimes=a*(b*(1+(classNumber-1)*0.2))*cHours*c;
				classTimes=formatDouble(classTimes);   //处理
				/*
				 * 计算实验学时这里要注意
				 */
				double testTime=result.getDouble("testTimes");
				/*
				 * 该课程有实验就计算实验实际学时
				 */
				double testTimes=0;    //定义实验实际学时
				if(testTime!=0.0)
				{
					testTimes=a*testTime*classNumber*c;
					testTimes=formatDouble(testTimes);   //处理
				}		
				/*
				 * 循环查询班级人数
				 * 调用getStuNumber方法
				 */
				for(int i=0;i<classNumber;i++)
				{
					studentNumber+=getStuNumber(str[i]);
				}
			/*
			 * 开始将获得的参数封装
			 */
				cb.setcName(result.getString("cName"));       // 课程名字
				cb.setTerms(result.getString("terms"));       //授课日期
				cb.setpRadio(a);     //职称系数
				cb.setDifficulty(b);     //课程系数
				cb.setStuNum(studentNumber);    //学生人数
				cb.setClaNum(classNumber);    // 班级数
				cb.setcHours(cHours);     //课程理论学时
				cb.setDftNum(c);     //授课难度系数
				cb.setTestTimes(testTimes);     //实验实际学时
				cb.setClassTimes(classTimes);    //授课实际学时
				list.add(cb);     //加入集合
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
	 * time：2017/05/23
	 * 根据教师号和学期时间查询该教师该学期的课程设计任务
	 * 通过存储过程proce_sltDdsnByTea实现
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
//				String[] str=classId.split(",");      //切割字符串
//				int classNumber=str.length;      //班级数
//				int studentNumber=0;
				CdesignBean cb=new CdesignBean();
				int studentNumber=result.getInt("stus");//获取学生人数
				/*
				 * 循环查询班级人数
				 * 调用getStuNumber方法
				 */
//				for(int i=0;i<classNumber;i++)
//				{
//					studentNumber+=getStuNumber(str[i]);
//				}
				int weeks=result.getInt("week");
				double times=studentNumber*weeks;
				times=formatDouble(times); 
			/*
			 * 开始将获得的参数封装
			 */
				cb.setcName(result.getString("cName"));       // 课程名字
				cb.setTerms(term);       //授课日期
				cb.setStuNum(studentNumber);    //学生人数
				cb.setWeeks(weeks);    //周数
				cb.setTimes(times);    //实际教时
				dlist.add(cb);     //加入集合
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
	 * 根据proce_selAllCdsnByTea存储过程查询教师历史课程设计
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
//				String[] str=classId.split(",");      //切割字符串
//				int classNumber=str.length;      //班级数
//				int studentNumber=0;
				CdesignBean cb=new CdesignBean();
				int studentNumber=result.getInt("stus");
				/*
				 * 循环查询班级人数
				 * 调用getStuNumber方法
				 */
//				for(int i=0;i<classNumber;i++)
//				{
//					studentNumber+=getStuNumber(str[i]);
//				}
				int weeks=result.getInt("week");
				double times=studentNumber*weeks;
				times=formatDouble(times); 
			/*
			 * 开始将获得的参数封装
			 */
				cb.setcName(result.getString("cName"));       // 课程名字
				cb.setTerms(result.getString("csDsTime"));       //授课日期
				cb.setStuNum(studentNumber);    //学生人数
				cb.setWeeks(weeks);    //周数
				cb.setTimes(times);    //实际教时
				dlist.add(cb);     //加入集合
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
	 * 根据教师号和学期查询毕业设计课程信息
	 * 利用存储过程proce_sltGdsnByTeaTm实现
	 * 传入参数pId
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
				//计算毕业教时
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
	 * 根据教师号查询历史所有毕业设计课程信息
	 * 利用存储过程proce_sltAllGdsnByTea实现
	 * 传入参数pId
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
				//计算毕业教时
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
	 * 关闭资源
	 * GetConnection类中的方法会检查参数是否为空
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
	 * double型数据允许的小数点后最小位数
	 */
	public double formatDouble(double dbl)
	{
		NumberFormat format=NumberFormat.getInstance();
		format.setMaximumFractionDigits(3);     
		double fmtdbl=Double.parseDouble(format.format(dbl));
		return fmtdbl;
	}
	/*
	 * 测试1方法  getStuNumber  测试成功
	 * 测试2方法 getClasses测试成功
	 * 测试3方法 getAllClassesByTid成功
	 * 测试方法4 getCdesignByTeaTm  成功
	 * 测试方法5 getAllCdesignByTea  成功
	 * 测试方法6 getGdsnByTidTm 成功
	 * 测试方法7 getAllGdsnByTid
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
//			System.out.println("班级数："+cls.getClaNum()+":::学生数:::"+cls.getStuNum()+":::实验学时::::"+cls.getTestTimes()+":::授课学时:::"+cls.getClassTimes()+":::::授课学期"+cls.getTerms());
//		}
//		List<ClassesBean> list=new CourseInfoDao().getAllClassesByTid("00003", 1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			ClassesBean cls=list.get(i);	
//			System.out.println("班级数："+cls.getClaNum()+":::学生数:::"+cls.getStuNum()+":::实验学时::::"+cls.getTestTimes()+":::授课学时:::"+cls.getClassTimes()+":::::授课学期"+cls.getTerms());
//		}
//		List<CdesignBean> list=new CourseInfoDao().getCdesignByTeaTm("00001","2017-2018/2");
//		for(int i=0;i<list.size();i++)
//		{
//			CdesignBean cls=list.get(i);	
//			System.out.println(":::学生数:::"+cls.getStuNum()+":::授课学时:::"+cls.getTimes()+":::::授课学期"+cls.getTerms()+"授课周数"+cls.getWeeks()+"授课"+cls.getcName());
//		}
		List<CdesignBean> list=new CourseInfoDao().getAllCdesignByTea("00002");
		for(int i=0;i<list.size();i++)
		{
			CdesignBean cls=list.get(i);	
			System.out.println(":::学生数:::"+cls.getStuNum()+":::授课学时:::"+cls.getTimes()+":::::授课学期"+cls.getTerms()+"授课周数"+cls.getWeeks()+"授课"+cls.getcName());
		}
//		List<GdesignBean> list=new CourseInfoDao().getGdsnByTidTm("00001", "2016-2017/2", 1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			GdesignBean cls=list.get(i);	
//			System.out.println(":::学生数:::"+cls.getStuNum()+":::授课学时:::"+cls.getTimes()+"授课周数"+cls.getWeeks()+"授课日期"+cls.getTerm()+"职位系数："+cls.getpId());
//		}
//		List<GdesignBean> list=new CourseInfoDao().getAllGdsnByTid("00002",  1.3);
//		for(int i=0;i<list.size();i++)
//		{
//			GdesignBean cls=list.get(i);	
//			System.out.println(":::学生数:::"+cls.getStuNum()+":::授课学时:::"+cls.getTimes()+"授课周数"+cls.getWeeks()+"授课日期"+cls.getTerm()+"职位系数："+cls.getpId());
//		}
	}

}
