package com.lerx.web.ajax.service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.sys.util.TimeUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UtilAction extends ActionSupport implements 
		ServletResponseAware {
	private HttpServletResponse response;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void year(){
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		try {
			response.getWriter().write(TimeUtil.getDateVar((java.sql.Date) new java.sql.Date(System.currentTimeMillis()),2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void weekDay(){
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		try {
			response.getWriter().write(TimeUtil.getWeekDay((java.sql.Date) new java.sql.Date(System.currentTimeMillis())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dateAndWeek(){
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		try {
			java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
			response.getWriter().write(TimeUtil.getDate(d)+" "+ TimeUtil.getWeekDay(d));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;

	}


}
