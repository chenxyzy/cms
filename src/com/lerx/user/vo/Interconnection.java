package com.lerx.user.vo;

import java.sql.Timestamp;

public class Interconnection {
	private long id;
	private int type;
	private String openID;
	private User user;
	private Timestamp createTimstamp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getCreateTimstamp() {
		return createTimstamp;
	}
	public void setCreateTimstamp(Timestamp createTimstamp) {
		this.createTimstamp = createTimstamp;
	}
	
}
