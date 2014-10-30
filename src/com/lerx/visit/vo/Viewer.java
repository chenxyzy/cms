package com.lerx.visit.vo;

import java.sql.Timestamp;

public class Viewer {
	
	/*
	 * 计数功能     考虑到免费计数器太多，价值不大 ，未开发
	 */
	private long id;
	private Timestamp viewTime;
	private String ip;
	private String referrer;
	private String positionUrl;
	private String os;
	private String browser;
	private int scwidth;
	private int scheight;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getViewTime() {
		return viewTime;
	}
	public void setViewTime(Timestamp viewTime) {
		this.viewTime = viewTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getPositionUrl() {
		return positionUrl;
	}
	public void setPositionUrl(String positionUrl) {
		this.positionUrl = positionUrl;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public int getScwidth() {
		return scwidth;
	}
	public void setScwidth(int scwidth) {
		this.scwidth = scwidth;
	}
	public int getScheight() {
		return scheight;
	}
	public void setScheight(int scheight) {
		this.scheight = scheight;
	}
	
}
