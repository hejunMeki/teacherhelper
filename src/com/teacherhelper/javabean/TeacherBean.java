package com.teacherhelper.javabean;
/*
 * TeacherBean 的改进版  全部接受显示信息
 */
public class TeacherBean {

	private String tId;     //教师id
	private double  pRatio;       //职称系数
	private String oName;      //教研室名
	private String ceName;   //学院名
	private String tName;    //教师姓名
	private String proName;     //职位名
	private int permission;     //该教师的权限
	
	
	public TeacherBean() {
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public double getpRatio() {
		return pRatio;
	}
	public void setpRatio(double pRatio) {
		this.pRatio = pRatio;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getCeName() {
		return ceName;
	}
	public void setCeName(String ceName) {
		this.ceName = ceName;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
	
	
}
