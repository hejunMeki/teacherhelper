package com.teacherhelper.javabean;
/*
 * time：2017/05/23
 * 毕业设计的javabean类
 */
public class GdesignBean {
	private double pId;           //职称系数
	private int stuNum;        //人数
	private int weeks;                 //周数
	private double times;       //实际学时
	private String term;             //日期	
	
	public GdesignBean() {
	}
	public double getpId() {
		return pId;
	}
	public void setpId(double pId) {
		this.pId = pId;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	
	
	
	

}
