package com.teacherhelper.javabean;
/*
 * time:2017/05/20
 * 班级学生表对应的Javabean类
 */
public class StudentBean {
	private String classId;       //班级id
	private String className;      //班级名
	private int number;     //班级人数
	
	
	public StudentBean() {
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
