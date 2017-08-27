package com.teacherhelper.javabean;
/*
 * time：2017/05/23
 * 课程设计对应的Javabean类
 */
public class CdesignBean {
	private String cName;          //课程设计名字
	private int weeks;                 //周数
	private int stuNum;           // 学生人数
	private double times;                //实践学时
	private String terms;               //授课学期
	
	public CdesignBean() {
	}
	
	public CdesignBean(String cName, int weeks, int stuNum, double times, String terms) {
		this.cName = cName;
		this.weeks = weeks;
		this.stuNum = stuNum;
		this.times = times;
		this.terms = terms;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	
	

}
