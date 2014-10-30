package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActionPage {
	
	private String refererUrl;
	private String workingUrl;
	private HttpServletRequest request;
	private ActionSupport actionSupport;
	private ActionContext ac;
	
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
	public ActionSupport getActionSupport() {
		return actionSupport;
	}
	public void setActionSupport(ActionSupport actionSupport) {
		this.actionSupport = actionSupport;
	}
	public ActionContext getAc() {
		return ac;
	}
	public void setAc(ActionContext ac) {
		this.ac = ac;
	}
	
	
}
