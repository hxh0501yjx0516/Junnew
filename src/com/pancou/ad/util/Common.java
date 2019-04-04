package com.pancou.ad.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;


public class Common extends DispatchAction{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 手机号码验证
	 * @param listM
	 * @return
	 */
	public static boolean checkMobileList(String mobile) //手机号码格式验证
	{
		boolean blRet=false;
		Matcher m = null;
		/** 
		Pattern mobileRegExp = Pattern
				.compile( "^(13[0-9]|15[0-9]|18[8|9])\\d{8}$" );
		m = mobileRegExp.matcher(mobile);*/
		// 验证11位数字
		Pattern mobileRegExp = Pattern.compile( "^\\d{11}$" );
		m = mobileRegExp.matcher(mobile);
		
		if (!m.matches()) {
			blRet = false;
//			System.out.println(mobile+"error");
		} else {
			blRet = true;			
		}
		return blRet;
		//return true;
	}
	
	/**
	 * 手机号码验证
	 * @param listM
	 * @return
	 */
	public static boolean checkMobileList(String[] mobile) //手机号码格式验证
	{
		boolean blRet=false;
		Matcher m = null;
		 
//		Pattern mobileRegExp = Pattern
//				.compile( "^(13[0-9]|15[0-9]|18[8|9])\\d{8}$" );
		// 验证11数字
		Pattern mobileRegExp = Pattern.compile( "^\\d{11}$" );
		
		for(int i = 0; i < mobile.length; i ++){
			m = mobileRegExp.matcher(mobile[i]);			
			if (!m.matches()) {
				blRet = false;
				return blRet;
			} 
		}
		blRet = true;
		return blRet;
//		return true;
	}
	


	
	/**
	* 删除单个文件
	* @param fileName 要删除的文件的文件名
	* @return 单个文件删除成功返回true，否则返回false
	*/
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
//				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
		
	}


	public static void main(String[] args) {
		Common com = new Common();
		String[] list = new String[3];
		list[0]="13236675300";
		list[1]="13136675300";
		list[2]="13323667530";
		System.out.println(com.checkMobileList(list));
		
	}
}
