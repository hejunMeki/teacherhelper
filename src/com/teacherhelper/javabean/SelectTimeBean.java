package com.teacherhelper.javabean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/*
 * 生成课程的时间序列
 */
public class SelectTimeBean {
	private List<String> list;       // 时间 时间格式为2016-2017/1	
	private String currenttime;     //当前学期

	
	
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
	 * 根据当前时间自动生成列表
	 * 使用Calendar类来获取时间 
	 * 注意Calendar的月份是从0开始表示
	 * 时间列表默认2016年开始
	 */
	public List<String> getSelectTimes()
	{
		 list=new ArrayList<String>();
		//获取当前时间 并提取月份和年份
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
		 Collections.reverse(list);    //将集合反转
		return list;
	}
	/*
	 * 测试该方法  成功
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
