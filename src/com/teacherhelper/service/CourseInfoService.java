package com.teacherhelper.service;

import java.util.List;

import com.teacherhelper.dao.CourseInfoDao;
import com.teacherhelper.javabean.CdesignBean;
import com.teacherhelper.javabean.ClassesBean;
import com.teacherhelper.javabean.GdesignBean;

/*
 * 
     * @ClassName: CourseInfoService  
     * @Description:   service层
     * @author  hejun
     * @date 2017年7月13日  上午9:22:49
 */
public class CourseInfoService {
	/*
	 * time:2017/07/13
	 * 根据教师号和时间查找教师任课信息  同时传入职称系数
	 */
	public List<ClassesBean> getClasses(String tId,String term,double a)
	{
		return new CourseInfoDao().getClasses(tId, term, a);
	}
	/*
	 * time：2017/07/13
	 * 根据教师号和学期时间查询该教师该学期的课程设计任务
	 */
	public List<CdesignBean> getCdesignByTeaTm(String tId,String term)
	{
		return new CourseInfoDao().getCdesignByTeaTm(tId,term);
	}
	/*
	 * 根据教师号和学期查询毕业设计课程信息
	 */
	public List<GdesignBean> getGdsnByTidTm(String id,String term,double pId)
	{
		return new CourseInfoDao().getGdsnByTidTm(id,term,pId);
	}
	
	/*
	 * time:2017/07/13
	 * 根据教师号查询该教师历史任课信息
	 */
	public List<ClassesBean> getAllClassesByTid(String tId,double a)
	{
		return new CourseInfoDao().getAllClassesByTid(tId,a);
	}
	/*
	 * 查询教师历史课程设计
	 */
	public List<CdesignBean> getAllCdesignByTea(String tId)
	{
		return new CourseInfoDao(). getAllCdesignByTea(tId);
	}
	/*
	 * 根据教师号查询历史所有毕业设计课程信息
	 */
	public List<GdesignBean> getAllGdsnByTid(String id,double pId)
	{
		return new CourseInfoDao(). getAllGdsnByTid(id,pId);
	}

}
