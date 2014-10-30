package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

public class SecFilePosition {
	
	private HttpServletRequest request;
	private String secFileName;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getSecFileName() {
		return secFileName;
	}
	public void setSecFileName(String secFileName) {
		this.secFileName = secFileName;
	}
	
}
