package com.lerx.user.vo;

import java.sql.Timestamp;

public class User {
	private Long id;
	private UserGroup userGroup;
	private String userName;
	private String remName;
	private String password;
	private String salt;
	private boolean state;
	private User passer;
	private Passer postTo;
	private Timestamp passingTime;
	private boolean album;
	private String email;
	private String avatarFile;
	private boolean avatarFileLock;
	private Timestamp lastLoginTimstamp;
	private String lastLoginIp;
	private Timestamp regTimstamp;
	private String regIp;
	private String uuid;
	private long articleThreadsPassed;
	private long articleThreadsCount;
	private int allScore;
	private int bbsScore;
	private int spaceState;		//个人空间状态  0：未开通  1: 已开通  2:开通且是管理员
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRemName() {
		return remName;
	}
	public void setRemName(String remName) {
		this.remName = remName;
	}
	public String getPassword() {
		return password;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public User getPasser() {
		return passer;
	}
	public void setPasser(User passer) {
		this.passer = passer;
	}
	public Passer getPostTo() {
		return postTo;
	}
	public void setPostTo(Passer postTo) {
		this.postTo = postTo;
	}
	public boolean isAlbum() {
		return album;
	}
	public void setAlbum(boolean album) {
		this.album = album;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAvatarFile() {
		return avatarFile;
	}
	public void setAvatarFile(String avatarFile) {
		this.avatarFile = avatarFile;
	}
	public boolean isAvatarFileLock() {
		return avatarFileLock;
	}
	public void setAvatarFileLock(boolean avatarFileLock) {
		this.avatarFileLock = avatarFileLock;
	}
	public Timestamp getLastLoginTimstamp() {
		return lastLoginTimstamp;
	}
	public void setLastLoginTimstamp(Timestamp lastLoginTimstamp) {
		this.lastLoginTimstamp = lastLoginTimstamp;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public Timestamp getRegTimstamp() {
		return regTimstamp;
	}
	public void setRegTimstamp(Timestamp regTimstamp) {
		this.regTimstamp = regTimstamp;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public int getAllScore() {
		return allScore;
	}
	public void setAllScore(int allScore) {
		this.allScore = allScore;
	}
	public int getBbsScore() {
		return bbsScore;
	}
	public void setBbsScore(int bbsScore) {
		this.bbsScore = bbsScore;
	}
	public int getSpaceState() {
		return spaceState;
	}
	public void setSpaceState(int spaceState) {
		this.spaceState = spaceState;
	}
	public Timestamp getPassingTime() {
		return passingTime;
	}
	public void setPassingTime(Timestamp passingTime) {
		this.passingTime = passingTime;
	}
	
}
