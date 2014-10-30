package com.lerx.sys.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class LogWrite {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(LogWrite.class); 
	public static void logWrite(HttpServletRequest request,String msg){
		
		String info;
		String lb= System.getProperty("line.separator");
//		lb="	"+lb+"	";
//		request.getRemoteHost().getBytes()
		info="	host:"+request.getRemoteHost()+lb + "	pathinfo:" + request.getPathInfo()+lb + "	requesturl:" + request.getRequestURI()+lb +"	Referer:"+request.getHeader("Referer")+lb+ "	ip:"+IpUtil.getRealRemotIP(request); 

		CustomLogger clog=new CustomLogger();
		clog.custom(info+lb+"	info:"+msg);
		
		
	}
}
