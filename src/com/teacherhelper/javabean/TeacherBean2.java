package com.teacherhelper.javabean;
/*
 * teacher���Ӧ��Javabean��
 * time��2017/05/20
 * ��ɾ��
 */
public class TeacherBean2 {
	private String tId;     //��ʦid
	private int pId;       //ְ��id
	private int oId;      //������id
	private String ceId;   //ѧԺid
	private String tName;    //��ʦ����
	private String proId;     //ְλid
	
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
