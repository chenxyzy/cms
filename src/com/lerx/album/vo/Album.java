package com.lerx.album.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

/*
 * 话题
 */

public class Album {
	
	private long id;
	private String subTitle;
	private String author;
	private String authorEmail;
	
	private User leader;
	private User passer;
	private String introduction;	//导语
	private String conclusion;		//结论
	
	private Timestamp createTime;
	private Timestamp lastEditTime;
	private String lastViewIp;
	private Timestamp lastViewTime;
	
	private String salt;
	private String password;
	
	private boolean locking;		//锁定
	
	private long views;
	private String investigationTitle;
	private long agrees;				//赞成
	private long opposes;				//反对
	private boolean htmlCreated;
	private String htmlUrlPath;
	private String htmlURLFile;
	private String encryptedParmStr;
	private boolean state;
	private boolean def;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	public User getPasser() {
		return passer;
	}
	public void setPasser(User passer) {
		this.passer = passer;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Timestamp lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public String getLastViewIp() {
		return lastViewIp;
	}
	public void setLastViewIp(String lastViewIp) {
		this.lastViewIp = lastViewIp;
	}
	public Timestamp getLastViewTime() {
		return lastViewTime;
	}
	public void setLastViewTime(Timestamp lastViewTime) {
		this.lastViewTime = lastViewTime;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLocking() {
		return locking;
	}
	public void setLocking(boolean locking) {
		this.locking = locking;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public String getInvestigationTitle() {
		return investigationTitle;
	}
	public void setInvestigationTitle(String investigationTitle) {
		this.investigationTitle = investigationTitle;
	}
	public long getAgrees() {
		return agrees;
	}
	public void setAgrees(long agrees) {
		this.agrees = agrees;
	}
	public long getOpposes() {
		return opposes;
	}
	public void setOpposes(long opposes) {
		this.opposes = opposes;
	}
	public boolean isHtmlCreated() {
		return htmlCreated;
	}
	public void setHtmlCreated(boolean htmlCreated) {
		this.htmlCreated = htmlCreated;
	}
	public String getHtmlUrlPath() {
		return htmlUrlPath;
	}
	public void setHtmlUrlPath(String htmlUrlPath) {
		this.htmlUrlPath = htmlUrlPath;
	}
	public String getHtmlURLFile() {
		return htmlURLFile;
	}
	public void setHtmlURLFile(String htmlURLFile) {
		this.htmlURLFile = htmlURLFile;
	}
	public String getEncryptedParmStr() {
		return encryptedParmStr;
	}
	public void setEncryptedParmStr(String encryptedParmStr) {
		this.encryptedParmStr = encryptedParmStr;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isDef() {
		return def;
	}
	public void setDef(boolean def) {
		this.def = def;
	}

}
