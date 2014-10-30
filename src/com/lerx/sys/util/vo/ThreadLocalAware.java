package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

public class ThreadLocalAware {
	
	private HttpServletRequest request;
	private ActionSupport actionSupport;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public ActionSupport getActionSupport() {
		return actionSupport;
	}
	public void setActionSupport(ActionSupport actionSupport) {
		this.actionSupport = actionSupport;
	}
	
	
}
