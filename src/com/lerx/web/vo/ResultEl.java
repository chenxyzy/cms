package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;

import com.lerx.style.site.dao.ISiteStyleDao;
import com.opensymphony.xwork2.ActionSupport;

public class ResultEl {
	
	private String mes;
	private String codeStr;
	private String refererUrl;
	private int mod;
	private int waitSecs;
	private ActionSupport as;
	private HttpServletRequest request;
	private String imgUrl;
	private ISiteStyleDao siteStyleDaoImp;
	private int styleID;
	
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCodeStr() {
		return codeStr;
	}
	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getWaitSecs() {
		return waitSecs;
	}
	public void setWaitSecs(int waitSecs) {
		this.waitSecs = waitSecs;
	}
	public ISiteStyleDao getSiteStyleDaoImp() {
		return siteStyleDaoImp;
	}
	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}
	public int getStyleID() {
		return styleID;
	}
	public void setStyleID(int styleID) {
		this.styleID = styleID;
	}
	
}
