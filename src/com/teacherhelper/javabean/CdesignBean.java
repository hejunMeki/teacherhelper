package com.teacherhelper.javabean;
/*
 * time��2017/05/23
 * �γ���ƶ�Ӧ��Javabean��
 */
public class CdesignBean {
	private String cName;          //�γ��������
	private int weeks;                 //����
	private int stuNum;           // ѧ������
	private double times;                //ʵ��ѧʱ
	private String terms;               //�ڿ�ѧ��
	
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
