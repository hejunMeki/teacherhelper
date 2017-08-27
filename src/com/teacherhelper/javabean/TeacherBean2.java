package com.teacherhelper.javabean;
/*
 * teacher表对应的Javabean类
 * time：2017/05/20
 * 可删除
 */
public class TeacherBean2 {
	private String tId;     //教师id
	private int pId;       //职称id
	private int oId;      //教研室id
	private String ceId;   //学院id
	private String tName;    //教师姓名
	private String proId;     //职位id
	
	public TeacherBean2() {
	}
	
	
	public TeacherBean2(String tId, int pId, int oId, String ceId, String tName, String proId) {
		this.tId = tId;
		this.pId = pId;
		this.oId = oId;
		this.ceId = ceId;
		this.tName = tName;
		this.proId = proId;
	}


	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getCeId() {
		return ceId;
	}
	public void setCeId(String ceId) {
		this.ceId = ceId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	
	
}
