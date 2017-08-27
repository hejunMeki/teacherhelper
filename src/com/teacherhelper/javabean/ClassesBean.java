package com.teacherhelper.javabean;
/*
 * 讲课信息对应的Javabean
 * 
 */
public class ClassesBean {
	private String cName;   //课程名称
	private double pRadio;    //职称系数
	private double difficulty;     //课程系数
	private int stuNum;      //学生人数
	private int claNum;     //班级数
	private double cHours;  //课程理论学时
	private double dftNum;    //课程难度系数
	private double testTimes;    //实验实际学时
	private double classTimes;    //上课实际学时
	private String terms;        //授课学期
	
	
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
