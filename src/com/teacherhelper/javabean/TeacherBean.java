package com.teacherhelper.javabean;
/*
 * TeacherBean �ĸĽ���  ȫ��������ʾ��Ϣ
 */
public class TeacherBean {

	private String tId;     //��ʦid
	private double  pRatio;       //ְ��ϵ��
	private String oName;      //��������
	private String ceName;   //ѧԺ��
	private String tName;    //��ʦ����
	private String proName;     //ְλ��
	private int permission;     //�ý�ʦ��Ȩ��
	
	
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
