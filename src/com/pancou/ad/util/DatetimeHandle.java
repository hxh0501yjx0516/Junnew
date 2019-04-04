package com.pancou.ad.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 时间基本处理类
 * 
 * @author tmb
 * @version 1.10
 */
public class DatetimeHandle {

	/**日期格式："yyyy-MM-dd HH:mm:ss"*/
	public final static String LONG_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	
	/**日期格式："yyyy-MM-dd"*/
	public final static String SHORT_TIME_FORMAT_STRING = "yyyy-MM-dd";
	
	/**
	 * 获得当前时间的格式化字符串
	 * 
	 * @see LONG_TIME_FORMAT_STRING
	 * @return java.lang.String
	 */
	public static String formatCurrentDate () {
		return new SimpleDateFormat (LONG_TIME_FORMAT_STRING).format(new Date ());
	}
	
	/**
	 * 将指定时间按format定义的格式转化
	 * 
	 * @param longDate long 指定时间的毫秒数
	 * @param format java.lang.String 格式化字符串
	 * @return java.lang.String
	 */
	public static String formatDate(long longDate,String format) {
		return formatDate(new Date(longDate), format);
	}
	/**
	 * 将指定时间按format定义的格式转化
	 * 
	 * @param date java.util.Date 指定时间
	 * @param format java.lang.String 格式化字符串
	 * @return java.lang.String
	 */
	public static String formatDate(Date date,String format) {
		if (date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 将指定时间按format定义的格式转化
	 * 
	 * @param date java.util.Date 指定时间
	 * @param format java.lang.String 格式化字符串
	 * @return java.lang.String
	 */
	public static String formatDate(Date date) {
		if (date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		return sdf.format(date);
	}
	/**
	 * 获得当前系统时间毫秒数
	 * 
	 * @return long
	 */
	public static long getCurrentDate()
	{
		return System.currentTimeMillis();
	}
	/**
	 * 将指定符合格式的时间字符串按"yyyy-MM-dd HH:mm:ss"的格式转化为毫秒数
	 * 
	 * @param date java.lang.String 指定时间
	 * @see LONG_TIME_FORMAT_STRING
	 * @return long
	 */
	public static long parseDate(String date) throws ParseException
	{
		if(date==null || date.equals(""))
		{
			return 0L;
		}
		SimpleDateFormat df = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		Date d=df.parse(date);
		return d.getTime();
	}
	/**
	 * 将指定符合格式的时间字符串按format定义的格式转化为毫秒数
	 * 
	 * @param date java.lang.String 指定时间
	 * @param format java.lang.String 格式化字符串
	 * @return long
	 */
	public static long parseDate(String date, String format) throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d=df.parse(date);
		return d.getTime();
	}
	/**
	 * 将指定时间毫秒数格式化为"yyyy-MM-dd HH:mm:ss"的格式的字符串
	 * 
	 * @param longDate long 指定时间的毫秒数
	 * @see LONG_TIME_FORMAT_STRING
	 * @return java.lang.String
	 */
	public static String formatDate(Long longDate) throws ParseException
	{
		if(longDate==null)
		{
			return "";
		}
		Date date=new Date(longDate);
		SimpleDateFormat df = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		return df.format(date);
	}
	
	/** 报表相关时间标志截取 */
	public static String hourFlag (String time) {
		return time.substring(0,13); 
	}
	
	/** 报表相关时间标志截取 */
	public static String dayFlag (String time) {
		return time.substring(0,10); 
	}
	
	/** 报表相关时间标志截取 */
	public static String monthFlag (String time) {
		return time.substring(0,7); 
	}
	
	/** 报表相关时间标志截取 */
	public static String yearFlag (String time) {
		return time.substring(0,4); 
	}
	
	public static List<Calendar> getTimeDivList(Calendar startCal, Calendar endCal, int addTimeField,
			int addStep){
		List<Calendar> _return = new ArrayList<Calendar> ();
		for (Calendar loopCal = startCal; !loopCal.after(endCal) ; loopCal.add(addTimeField,addStep)) {
			_return.add((Calendar)loopCal.clone());
		}
		return _return;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException{
		Calendar stCal = Calendar.getInstance();
		stCal.setTimeInMillis(DatetimeHandle.parseDate("2009-01", "yyyy-MM"));
		Calendar edCal = Calendar.getInstance();
		edCal.setTimeInMillis(DatetimeHandle.parseDate("2009-12", "yyyy-MM"));
		List<Calendar> list = getTimeDivList(stCal,edCal,Calendar.MONTH,1);
		
		Iterator<Calendar> ite = list.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next().getTime().toLocaleString());
		}
	}
}
