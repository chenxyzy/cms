package com.lerx.bbs.vo;

import com.lerx.user.vo.User;

public class BM {
	
	private long id;
	private User user;
	private BbsForum bf;
	
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
	public BbsForum getBf() {
		return bf;
	}
	public void setBf(BbsForum bf) {
		this.bf = bf;
	}
	
	
	
	
}
