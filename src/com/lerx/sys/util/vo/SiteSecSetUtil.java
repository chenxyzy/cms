package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.lerx.site.vo.SiteInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SiteSecSetUtil {
	
	private SiteInfo site;
	private HttpServletRequest request;
	private ActionSupport as;
	
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
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	
	
}
