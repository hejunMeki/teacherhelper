package com.teacherhelper.javabean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/*
 * ���ɿγ̵�ʱ������
 */
public class SelectTimeBean {
	private List<String> list;       // ʱ�� ʱ���ʽΪ2016-2017/1	
	private String currenttime;     //��ǰѧ��

	
	
	public String getCurrenttime() {
		return getCurrentTime();
	}


	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}


	public List<String> getList() {
		return getSelectTimes();
	}


	public void setList(List<String> list) {
		this.list = list;
	}
    public String getCurrentTime()
    {
    	Calendar calendar=Calendar.getInstance();
		 int year=calendar.get(Calendar.YEAR);
		 int month=calendar.get(Calendar.MONTH);
		 String str;
		 if(month>=7)
		 {
			 str=year+"-"+(year+1)+"/"+1;
		 }
		 else
		 {
			 str=(year-1)+"-"+year+"/"+2;
		 }
		 return str;
    }

	/*
	 * ���ݵ�ǰʱ���Զ������б�
	 * ʹ��Calendar������ȡʱ�� 
	 * ע��Calendar���·��Ǵ�0��ʼ��ʾ
	 * ʱ���б�Ĭ��2016�꿪ʼ
	 */
	public List<String> getSelectTimes()
	{
		 list=new ArrayList<String>();
		//��ȡ��ǰʱ�� ����ȡ�·ݺ����
		 Calendar calendar=Calendar.getInstance();
		 int year=calendar.get(Calendar.YEAR);
		 int month=calendar.get(Calendar.MONTH);
		 String time;
		 for(int i=2016;i<year;i++)
		 {
			 time=(i-1)+"-"+i+"/"+2;
			 list.add(time);
			 time=(i)+"-"+(i+1)+"/"+1;
			 list.add(time);
		 }
		 if(month>=7)
		 {
			 time=(year-1)+"-"+year+"/"+2;
			 list.add(time);
			 time=(year)+"-"+(year+1)+"/"+1;
			 list.add(time);		 
		 }	
		 else{
			 time=(year-1)+"-"+year+"/"+2;
			 list.add(time);
		 }
		 Collections.reverse(list);    //�����Ϸ�ת
		return list;
	}
	/*
	 * ���Ը÷���  �ɹ�
	 */
//    public static void main(String[] args) {
//		List list=new SelectTimeBean().getSelectTimes();
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i));
//		}
//		System.out.println(list.size());
//    	System.out.println(new SelectTimeBean().getCurrenttime());
//	}
}
