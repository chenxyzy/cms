package com.lerx.blog.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class Blog {
	private long id;
	private String title;
	private String body;
	private User user;
	private Timestamp addTime;
	private Timestamp lastEditTime;
	private String lastViewIp;
	private long views;
	private Timestamp lastViewTime;
	private String thumbnail; 			// 缩略图
	private boolean transmit;			//是否转载	
	private Blog tranblog;				//被转载的blog
	private String linkUrl;
	private BlogGroup bg;
	private int state;
	private boolean htmlCreated;
	private String htmlUrlPath;
	private String htmlURLFile;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getLastViewIp() {
		return lastViewIp;
	}
	public void setLastViewIp(String lastViewIp) {
		this.lastViewIp = lastViewIp;
	}
	public Timestamp getLastViewTime() {
		return lastViewTime;
	}
	public void setLastViewTime(Timestamp lastViewTime) {
		this.lastViewTime = lastViewTime;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public boolean isTransmit() {
		return transmit;
	}
	public void setTransmit(boolean transmit) {
		this.transmit = transmit;
	}
	public Blog getTranblog() {
		return tranblog;
	}
	public void setTranblog(Blog tranblog) {
		this.tranblog = tranblog;
	}
	public BlogGroup getBg() {
		return bg;
	}
	public void setBg(BlogGroup bg) {
		this.bg = bg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public Timestamp getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Timestamp lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public boolean isHtmlCreated() {
		return htmlCreated;
	}
	public void setHtmlCreated(boolean htmlCreated) {
		this.htmlCreated = htmlCreated;
	}
	public String getHtmlUrlPath() {
		return htmlUrlPath;
	}
	public void setHtmlUrlPath(String htmlUrlPath) {
		this.htmlUrlPath = htmlUrlPath;
	}
	public String getHtmlURLFile() {
		return htmlURLFile;
	}
	public void setHtmlURLFile(String htmlURLFile) {
		this.htmlURLFile = htmlURLFile;
	}
}
