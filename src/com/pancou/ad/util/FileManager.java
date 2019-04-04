package com.pancou.ad.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

public class FileManager {
	
	public static final int BUFFER_SIZE = 1024;
	public static final String UPLOAD_PATH = "img";
	public static final String UPLOAD_CMS_PATH = "cms";
	public static final String UPLOAD_SNAPSHOT_PATH = "snapshot";
	
	/**
	 * ��������ϴ��ļ�
	 * @param request
	 * @param formFile �ϴ��ļ�FormFile
	 * @param subDir �ϴ��ļ�Ŀ¼���粻��д�򱣴����Զ����ͼƬ��Ŀ¼�£�
	 * @return �ϴ��ļ��ķ�����·��
	 * @throws Exception
	 */
	public static String uploadFile(HttpServletRequest request,FormFile formFile,String subDir) throws Exception{
		try {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR );
			int month = cal.get(Calendar.MONTH )+1;
			String realPath = "";
			String addPath ="";
			if(null!=subDir && !"".equals(subDir)){
				addPath = File.separator+UPLOAD_PATH+File.separator+subDir;
				realPath = Configure.get("upload.img.url")+File.separator+addPath;
			}else{
				 addPath = File.separator+UPLOAD_PATH+ File.separator+year+File.separator+month;
				 realPath = Configure.get("upload.img.url")+addPath;
			}
			String outFileName = formFile.getFileName();
			createDir(realPath);
			String sux = outFileName.substring(outFileName.lastIndexOf("."));
			String realName = File.separator+System.currentTimeMillis()+sux;
			BufferedInputStream bis = new BufferedInputStream(formFile.getInputStream());
			FileOutputStream fos=new FileOutputStream(realPath+realName);
			
			byte[] b=new byte[BUFFER_SIZE];
			while(bis.read(b)!=-1)
			{
				fos.write(b);
				fos.flush();
			}
			bis.close();
			fos.close();
			return addPath+realName;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void createDir(String dirPath) throws Exception {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	//�ж��ǲ���һ��ͼƬ�ļ�����
	public static boolean isPhoto(String fileName) {
	        if (fileName.equals(""))
	            return false;
	        if ((fileName.toLowerCase().endsWith(".jpeg")) || (fileName.toLowerCase().endsWith(".jpg")) || (fileName.toLowerCase().endsWith(".gif")) || (fileName.toLowerCase().endsWith(".png")) || (fileName.toLowerCase().endsWith(".bmp")))
	            return true;
	        else
	            return false;
	    }
}
