package com.lerx.user.vo;

import java.sql.Timestamp;

public class Integral {
	
	private long id;
	private User user;
	private int type;
	private long tagId;
	private Timestamp recTime;
	private int value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	public Timestamp getRecTime() {
		return recTime;
	}
	public void setRecTime(Timestamp recTime) {
		this.recTime = recTime;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
