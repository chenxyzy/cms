package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

public class FileReadArgs {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionSupport as;
	private String fileName;
	private String rootFolder;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRootFolder() {
		return rootFolder;
	}
	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
	
}
