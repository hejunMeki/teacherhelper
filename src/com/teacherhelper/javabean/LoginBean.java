package com.teacherhelper.javabean;
/*
 * ��¼��login��Ӧ��Javabean��
 * time��2017/05/20
 */
public class LoginBean {
	private String tId;      //��ʦ��
	private String password;     //����
	private int permission;       //Ȩ�޵ȼ�
	
	public LoginBean() {
	}
	
	public LoginBean(String tId, String password, int permission) {
		this.tId = tId;
		this.password = password;
		this.permission = permission;
	}

	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	

}
