package com.teacherhelper.javabean;
/*
 * ������Ϣ��Ӧ��Javabean
 * 
 */
public class ClassesBean {
	private String cName;   //�γ�����
	private double pRadio;    //ְ��ϵ��
	private double difficulty;     //�γ�ϵ��
	private int stuNum;      //ѧ������
	private int claNum;     //�༶��
	private double cHours;  //�γ�����ѧʱ
	private double dftNum;    //�γ��Ѷ�ϵ��
	private double testTimes;    //ʵ��ʵ��ѧʱ
	private double classTimes;    //�Ͽ�ʵ��ѧʱ
	private String terms;        //�ڿ�ѧ��
	
	
	public ClassesBean() {
	}
	public ClassesBean(String cName, double pRadio, double difficulty, int stuNum, int claNum, double cHours,
			double dftNum, double testTimes, double classTimes, String terms) {
		this.cName = cName;
		this.pRadio = pRadio;
		this.difficulty = difficulty;
		this.stuNum = stuNum;
		this.claNum = claNum;
		this.cHours = cHours;
		this.dftNum = dftNum;
		this.testTimes = testTimes;
		this.classTimes = classTimes;
		this.terms = terms;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public double getpRadio() {
		return pRadio;
	}
	public void setpRadio(double pRadio) {
		this.pRadio = pRadio;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getClaNum() {
		return claNum;
	}
	public void setClaNum(int claNum) {
		this.claNum = claNum;
	}
	public double getcHours() {
		return cHours;
	}
	public void setcHours(double cHours) {
		this.cHours = cHours;
	}
	public double getDftNum() {
		return dftNum;
	}
	public void setDftNum(double dftNum) {
		this.dftNum = dftNum;
	}
	public double getTestTimes() {
		return testTimes;
	}
	public void setTestTimes(double testTimes) {
		this.testTimes = testTimes;
	}
	public double getClassTimes() {
		return classTimes;
	}
	public void setClassTimes(double classTimes) {
		this.classTimes = classTimes;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	
	
	
	
	
	
	

	
	
}
