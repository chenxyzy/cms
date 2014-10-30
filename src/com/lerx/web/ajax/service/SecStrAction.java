package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SecStrAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	
	public void send() throws IOException{
		//增加区域item显示
		System.currentTimeMillis();
		String secStr = null,fileSec="";
		String randKey = StringUtil.randomString(10).toLowerCase();
		try {
			fileSec = SrvInf.readSecStr(request, getText("lerx.hostSecFile"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		secStr = StringUtil.md5(fileSec.concat(randKey)).toLowerCase();
//		String key=StringUtil.strAtBit(secStr, StringUtil.bitForStrCharsAdd(fileSec));
		String outStr=randKey+","+secStr;
//		System.out.println("outStr:"+outStr);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(outStr);
		
		
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		// TODO Auto-generated method stub
		
	}

}
