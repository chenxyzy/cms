package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.lerx.qa.vo.QaNav;
import com.lerx.site.vo.SiteInfo;
import com.opensymphony.xwork2.ActionSupport;

public class MaiCreateArg {
	private SiteInfo site;
	private HttpServletRequest request;
	private QaNav qn;
	private ActionSupport as;
	private int mod;
	private String rootFolder;
	private int sta;
	private String fileName;
	public int getSta() {
		return sta;
	}
	public void setSta(int sta) {
		this.sta = sta;
	}
	public SiteInfo getSite() {
		return site;
	}
	public void setSite(SiteInfo site) {
		this.site = site;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public QaNav getQn() {
		return qn;
	}
	public void setQn(QaNav qn) {
		this.qn = qn;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public String getRootFolder() {
		return rootFolder;
	}
	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}	
