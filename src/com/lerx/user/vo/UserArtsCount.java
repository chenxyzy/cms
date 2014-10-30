package com.lerx.user.vo;

public class UserArtsCount {
	
	private Long id;
	private User user;
	private int timeKey;
	private String artGids;
	private long articleThreadsPassed;
	private long articleThreadsCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTimeKey() {
		return timeKey;
	}
	public void setTimeKey(int timeKey) {
		this.timeKey = timeKey;
	}
	public String getArtGids() {
		return artGids;
	}
	public void setArtGids(String artGids) {
		this.artGids = artGids;
	}
	public long getArticleThreadsPassed() {
		return articleThreadsPassed;
	}
	public void setArticleThreadsPassed(long articleThreadsPassed) {
		this.articleThreadsPassed = articleThreadsPassed;
	}
	public long getArticleThreadsCount() {
		return articleThreadsCount;
	}
	public void setArticleThreadsCount(long articleThreadsCount) {
		this.articleThreadsCount = articleThreadsCount;
	}
	
	
}
