package com.lerx.sys.vo;

import com.lerx.user.vo.User;

public class Task {
	private long id;
	private String title;
	private String body;
	private String ug;
	private User publisher;
	
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
	public String getUg() {
		return ug;
	}
	public void setUg(String ug) {
		this.ug = ug;
	}
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	
	
	
}
