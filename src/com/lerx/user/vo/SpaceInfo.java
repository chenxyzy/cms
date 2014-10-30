package com.lerx.user.vo;

import java.sql.Timestamp;

public class SpaceInfo extends User{
	private String spaceTitle;
	private boolean spaceState;
	private Timestamp createTime;
	private long views;
	private String lastViewIp;
	
	public String getSpaceTitle() {
		return spaceTitle;
	}
	public void setSpaceTitle(String spaceTitle) {
		this.spaceTitle = spaceTitle;
	}
	public boolean isSpaceState() {
		return spaceState;
	}
	public void setSpaceState(boolean spaceState) {
		this.spaceState = spaceState;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public String getLastViewIp() {
		return lastViewIp;
	}
	public void setLastViewIp(String lastViewIp) {
		this.lastViewIp = lastViewIp;
	}
	
}
