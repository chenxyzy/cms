package com.lerx.bbs.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class BbsTheme {
	private Long id;
	private BbsTheme rootTheme;
	private BbsForum forum;
	private String title;
	private boolean state;
	private boolean top;
	private boolean shield;
	private boolean soul;
	private int topMod;
	private int views;
	private User user;
	private User passer; //审核者
	private String body;
	private Timestamp addTime;
	private long addTimeUnix;
	private Timestamp lastEditTime;
	private User lastEditUser;
	private String lastViewIp;
	private Timestamp lastViewTime;
	private String quote;
	private String secCode;
	private int exoticActors;
	private int reps;
	private Timestamp chgTime;
	private boolean sink;
	private String addIp;
	private String lastEditIp;
	private boolean seeAfterReply;
	
	private long proponents;			//支持者数
	private long opponents;				//反对者数
	private long neutrals;				//中立者数
	private boolean pollAllow;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BbsTheme getRootTheme() {
		return rootTheme;
	}
	public void setRootTheme(BbsTheme rootTheme) {
		this.rootTheme = rootTheme;
	}
	public BbsForum getForum() {
		return forum;
	}
	public void setForum(BbsForum forum) {
		this.forum = forum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public boolean isShield() {
		return shield;
	}
	public void setShield(boolean shield) {
		this.shield = shield;
	}
	public boolean isSoul() {
		return soul;
	}
	public void setSoul(boolean soul) {
		this.soul = soul;
	}
	
	public int getTopMod() {
		return topMod;
	}
	public void setTopMod(int topMod) {
		this.topMod = topMod;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getPasser() {
		return passer;
	}
	public void setPasser(User passer) {
		this.passer = passer;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Timestamp getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Timestamp lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public User getLastEditUser() {
		return lastEditUser;
	}
	public void setLastEditUser(User lastEditUser) {
		this.lastEditUser = lastEditUser;
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
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public int getExoticActors() {
		return exoticActors;
	}
	public void setExoticActors(int exoticActors) {
		this.exoticActors = exoticActors;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public long getAddTimeUnix() {
		return addTimeUnix;
	}
	public void setAddTimeUnix(long addTimeUnix) {
		this.addTimeUnix = addTimeUnix;
	}
	public Timestamp getChgTime() {
		return chgTime;
	}
	public void setChgTime(Timestamp chgTime) {
		this.chgTime = chgTime;
	}
	public boolean isSink() {
		return sink;
	}
	public void setSink(boolean sink) {
		this.sink = sink;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	public String getLastEditIp() {
		return lastEditIp;
	}
	public void setLastEditIp(String lastEditIp) {
		this.lastEditIp = lastEditIp;
	}
	public boolean isSeeAfterReply() {
		return seeAfterReply;
	}
	public void setSeeAfterReply(boolean seeAfterReply) {
		this.seeAfterReply = seeAfterReply;
	}
	public long getProponents() {
		return proponents;
	}
	public void setProponents(long proponents) {
		this.proponents = proponents;
	}
	public long getOpponents() {
		return opponents;
	}
	public void setOpponents(long opponents) {
		this.opponents = opponents;
	}
	public long getNeutrals() {
		return neutrals;
	}
	public void setNeutrals(long neutrals) {
		this.neutrals = neutrals;
	}
	public boolean isPollAllow() {
		return pollAllow;
	}
	public void setPollAllow(boolean pollAllow) {
		this.pollAllow = pollAllow;
	}
		
}
