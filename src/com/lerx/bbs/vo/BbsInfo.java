package com.lerx.bbs.vo;

public class BbsInfo {
	private Integer id;
	private String name;
	private String url;
	private boolean state;
	private boolean postMustInGroup;
	private String closeAnnounce;
	private int pageSizeOfTitle;				//每页多少行记录
	private int pageSizeOfTheme;				//内容页每页多少记录
	private int timesOfLockTheme;		//新贴发布后多长时间锁定，将无法修改
	
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isPostMustInGroup() {
		return postMustInGroup;
	}
	public void setPostMustInGroup(boolean postMustInGroup) {
		this.postMustInGroup = postMustInGroup;
	}
	public String getCloseAnnounce() {
		return closeAnnounce;
	}
	public void setCloseAnnounce(String closeAnnounce) {
		this.closeAnnounce = closeAnnounce;
	}
	public int getPageSizeOfTitle() {
		return pageSizeOfTitle;
	}
	public void setPageSizeOfTitle(int pageSizeOfTitle) {
		this.pageSizeOfTitle = pageSizeOfTitle;
	}
	public int getPageSizeOfTheme() {
		return pageSizeOfTheme;
	}
	public void setPageSizeOfTheme(int pageSizeOfTheme) {
		this.pageSizeOfTheme = pageSizeOfTheme;
	}
	public int getTimesOfLockTheme() {
		return timesOfLockTheme;
	}
	public void setTimesOfLockTheme(int timesOfLockTheme) {
		this.timesOfLockTheme = timesOfLockTheme;
	}
	
	
}
