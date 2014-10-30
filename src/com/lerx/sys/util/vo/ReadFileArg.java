package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

public class ReadFileArg {

	private ActionSupport as;
	private HttpServletRequest request;
	private String fileName;
	private String subFolder;
	private String rootFolder;
	
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSubFolder() {
		return subFolder;
	}
	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}
	public String getRootFolder() {
		return rootFolder;
	}
	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
	
	
}
