package com.lerx.user.vo;

public class UserGroup {
	private int id;
	private String groupName;
	private String powerMask;
	private String siteArticleGroupsSelectCustomStr;
	private boolean state;
	private int userCount;
	private boolean numberAppearRestrict;
	private String privateHtml;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPowerMask() {
		return powerMask;
	}
	public void setPowerMask(String powerMask) {
		this.powerMask = powerMask;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	public boolean isNumberAppearRestrict() {
		return numberAppearRestrict;
	}
	public void setNumberAppearRestrict(boolean numberAppearRestrict) {
		this.numberAppearRestrict = numberAppearRestrict;
	}
	public String getPrivateHtml() {
		return privateHtml;
	}
	public void setPrivateHtml(String privateHtml) {
		this.privateHtml = privateHtml;
	}
	public String getSiteArticleGroupsSelectCustomStr() {
		return siteArticleGroupsSelectCustomStr;
	}
	public void setSiteArticleGroupsSelectCustomStr(
			String siteArticleGroupsSelectCustomStr) {
		this.siteArticleGroupsSelectCustomStr = siteArticleGroupsSelectCustomStr;
	}
}
