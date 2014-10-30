package com.lerx.album.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class Category {
	
	private long id;
	private String title;
	private Album firstAlb;
	private User user;
	private boolean state;
	private Timestamp createTime;
	
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
	public Album getFirstAlb() {
		return firstAlb;
	}
	public void setFirstAlb(Album firstAlb) {
		this.firstAlb = firstAlb;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
