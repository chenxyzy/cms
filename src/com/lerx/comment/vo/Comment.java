package com.lerx.comment.vo;

import java.sql.Timestamp;

import com.lerx.article.vo.ArticleThread;

public class Comment {
	private Long id;
	private ArticleThread thread;
	private String publisher;
	private String title;
	private String body;
	private String ip;
	private Timestamp addTime;
	private String email;
	private String phone;
	private String publisherFrom;
	private boolean state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArticleThread getThread() {
		return thread;
	}
	public void setThread(ArticleThread thread) {
		this.thread = thread;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPublisherFrom() {
		return publisherFrom;
	}
	public void setPublisherFrom(String publisherFrom) {
		this.publisherFrom = publisherFrom;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
