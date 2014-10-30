package com.lerx.admin.vo;

import java.sql.Timestamp;

public class Admin {
	
	private Integer id;
	
	private String name;
	private String password;
	private String salt;
	private String lastLoginIP;
	private Timestamp lastLoginDatetime;
	private boolean state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public Timestamp getLastLoginDatetime() {
		return lastLoginDatetime;
	}

	public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
