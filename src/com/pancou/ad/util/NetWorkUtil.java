package com.pancou.ad.util;

import java.net.InetAddress;
import java.net.NetworkInterface;

import javax.servlet.http.HttpServletRequest;


public class NetWorkUtil {
	
	/**
	 * 取得客户端真实IP
	 * @param request
	 * @return
	 */
	public static final String getRemoteIP(HttpServletRequest request){
		String remoteIp = request.getHeader("x-forwarded-for");
	       if(remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
	           remoteIp = request.getHeader("Proxy-Client-IP");
	       }
	       if(remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
	           remoteIp = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
	            remoteIp = request.getHeader("HTTP_CLIENT_IP");
	        }
	        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
	            remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	       if(remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
	           remoteIp = request.getRemoteAddr();
	       }
	       return remoteIp;
	}
	
	/**
	 * IPv4转换为long
	 * @param remoteIp
	 * @return
	 */
	public static final long getLongIP(String remoteIp){
		long longIp = 0;
		if (!"".equals(remoteIp)){
			String[] ipArray = remoteIp.split("\\.");
			longIp =  16777216L*Long.parseLong(ipArray[0]) + 65536L*Long.parseLong(ipArray[1]) + 256*Long.parseLong(ipArray[2]) + Long.parseLong(ipArray[3]); 
		}
		return longIp;
	}
	
	/**
	 * long转换为IPv4
	 * @param longIp
	 * @return
	 */
	public static final String longIptoString(long longIp){
		long mask[] = {0x000000FF,0x0000FF00,0x00FF0000,0xFF000000};  
        long num = 0;  
        StringBuffer ipInfo = new StringBuffer();  
        for(int i=0;i<4;i++){  
            num = (longIp & mask[i])>>(i*8);  
            if(i>0) ipInfo.insert(0,".");  
            ipInfo.insert(0,Long.toString(num,10));  
        }  
        return ipInfo.toString(); 
	}
	
	/**
	 * 取得客户端mac
	 * @param ia
	 * @return
	 * @throws Exception
	 */
	public static final String getMACAddress(InetAddress ia)throws Exception{
		//获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		//mac地址拼装
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<mac.length;i++){
			if(i!=0){
				sb.append("-");
			}

			//mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length()==1?0+s:s);
		}

		//把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}
}
