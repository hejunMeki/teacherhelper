package com.teacherhelper.service;

import java.util.List;

import com.teacherhelper.dao.CourseInfoDao;
import com.teacherhelper.javabean.CdesignBean;
import com.teacherhelper.javabean.ClassesBean;
import com.teacherhelper.javabean.GdesignBean;

/*
 * 
     * @ClassName: CourseInfoService  
     * @Description:   service��
     * @author  hejun
     * @date 2017��7��13��  ����9:22:49
 */
public class CourseInfoService {
	/*
	 * time:2017/07/13
	 * ���ݽ�ʦ�ź�ʱ����ҽ�ʦ�ο���Ϣ  ͬʱ����ְ��ϵ��
	 */
	public List<ClassesBean> getClasses(String tId,String term,double a)
	{
		return new CourseInfoDao().getClasses(tId, term, a);
	}
	/*
	 * time��2017/07/13
	 * ���ݽ�ʦ�ź�ѧ��ʱ���ѯ�ý�ʦ��ѧ�ڵĿγ��������
	 */
	public List<CdesignBean> getCdesignByTeaTm(String tId,String term)
	{
		return new CourseInfoDao().getCdesignByTeaTm(tId,term);
	}
	/*
	 * ���ݽ�ʦ�ź�ѧ�ڲ�ѯ��ҵ��ƿγ���Ϣ
	 */
	public List<GdesignBean> getGdsnByTidTm(String id,String term,double pId)
	{
		return new CourseInfoDao().getGdsnByTidTm(id,term,pId);
	}
	
	/*
	 * time:2017/07/13
	 * ���ݽ�ʦ�Ų�ѯ�ý�ʦ��ʷ�ο���Ϣ
	 */
	public List<ClassesBean> getAllClassesByTid(String tId,double a)
	{
		return new CourseInfoDao().getAllClassesByTid(tId,a);
	}
	/*
	 * ��ѯ��ʦ��ʷ�γ����
	 */
	public List<CdesignBean> getAllCdesignByTea(String tId)
	{
		return new CourseInfoDao(). getAllCdesignByTea(tId);
	}
	/*
	 * ���ݽ�ʦ�Ų�ѯ��ʷ���б�ҵ��ƿγ���Ϣ
	 */
	public List<GdesignBean> getAllGdsnByTid(String id,double pId)
	{
		return new CourseInfoDao(). getAllGdsnByTid(id,pId);
	}

}
