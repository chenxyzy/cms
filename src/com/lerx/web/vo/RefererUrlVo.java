package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

public class RefererUrlVo {
	private ActionSupport as;
	private HttpServletRequest request;
	private String refererUrl;
	private String workingUrl;
	
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	
	public String getWorkingUrl() {
		return workingUrl;
	}
	public void setWorkingUrl(String workingUrl) {
		this.workingUrl = workingUrl;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	
}
