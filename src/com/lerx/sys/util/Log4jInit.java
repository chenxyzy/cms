package com.lerx.sys.util;

import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 8421025842405267201L;  
	@SuppressWarnings("unused")
	public void init(ServletConfig config) throws ServletException {    
		  super.init(config);    
		  String prefix = config.getServletContext().getRealPath("/");    
		  String file = config.getInitParameter("log4j");    
		  String filePath = prefix + file;    
		  String outputDir = config.getInitParameter("outputDir");    
		   
		  Properties props = new Properties();    
		  try {    
		   FileInputStream istream = new FileInputStream(filePath);    
		   props.load(istream);    
		   istream.close();    
		   String logFile = prefix + outputDir + File.separator   
		     + props.getProperty("log4j.appender.appender1.File");   
		   props.setProperty("log4j.appender.appender1.File", logFile);   
		   PropertyConfigurator.configure(props);  
		   Logger log=Logger.getLogger(Log4jInit.class);
		   //log.error("dsafas");
		   System.out.println("启动日志成功！");   
		  } catch (IOException e) {   
			  System.out.println(e);
		   System.out.println("启动日志失败！");    
		  }    
		 }    
}
