package com.lerx.site.vo;

public class SiteInfo {
	private Integer id;
	private String fullSiteName; //网站全称
	private String shortSiteName; //网站缩称
	private boolean state;  //网站状态
	private String sessionKey; //网站session值 
	private String keyWords; //网站关键字
	private String description; //网站描述
	private String host; //网站主机名或域名
	private String closeAnnounce; //网站关闭通知
	private String hostVisitAllow; //限定可访问的IP
	private String welcomeStr; //网站欢迎词
	private String spaceName;
	private String bbsName;
	private boolean bbsState;
	private boolean spaceState;
	private int staticHtmlMode; //生成静态文件模式
	private String mailTitleFromSite;
	private String mailBodyForReg;
	private String mailBodyForQaAdd;
	private String mailBodyForQaReply;
	private String mailSmtpServer;
	private String mailSmtpUser;
	private String mailSmtpPws;
	private String mailSenderAddr;
	private String fileUploadExtName;
	private String filterWords;
	private boolean userRegAllow;
	private int actAfterReg;
	private int userGroupOfAutoPassed;
	private boolean oneMailForReg;
	private boolean userLoginAllow;
	private boolean articleAutoPass;
	private boolean commentsOpen;
	private String userGroupWhenNotLoginAllow;
	private int modeOfComment;
	private String userGroupsForStat;
	private String licenseAgreement;
	private long views;
	private long nviews;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isArticleAutoPass() {
		return articleAutoPass;
	}
	public void setArticleAutoPass(boolean articleAutoPass) {
		this.articleAutoPass = articleAutoPass;
	}
	public String getFullSiteName() {
		return fullSiteName;
	}
	public void setFullSiteName(String fullSiteName) {
		this.fullSiteName = fullSiteName;
	}
	public String getShortSiteName() {
		return shortSiteName;
	}
	public void setShortSiteName(String shortSiteName) {
		this.shortSiteName = shortSiteName;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getCloseAnnounce() {
		return closeAnnounce;
	}
	public void setCloseAnnounce(String closeAnnounce) {
		this.closeAnnounce = closeAnnounce;
	}
	public String getHostVisitAllow() {
		return hostVisitAllow;
	}
	public void setHostVisitAllow(String hostVisitAllow) {
		this.hostVisitAllow = hostVisitAllow;
	}
	public String getWelcomeStr() {
		return welcomeStr;
	}
	public void setWelcomeStr(String welcomeStr) {
		this.welcomeStr = welcomeStr;
	}
	
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getBbsName() {
		return bbsName;
	}
	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}
	
	public boolean isBbsState() {
		return bbsState;
	}
	public void setBbsState(boolean bbsState) {
		this.bbsState = bbsState;
	}
	public boolean isSpaceState() {
		return spaceState;
	}
	public void setSpaceState(boolean spaceState) {
		this.spaceState = spaceState;
	}
	public int getStaticHtmlMode() {
		return staticHtmlMode;
	}
	public void setStaticHtmlMode(int staticHtmlMode) {
		this.staticHtmlMode = staticHtmlMode;
	}
	public String getMailTitleFromSite() {
		return mailTitleFromSite;
	}
	public void setMailTitleFromSite(String mailTitleFromSite) {
		this.mailTitleFromSite = mailTitleFromSite;
	}
	public String getMailBodyForReg() {
		return mailBodyForReg;
	}
	public void setMailBodyForReg(String mailBodyForReg) {
		this.mailBodyForReg = mailBodyForReg;
	}
	public String getMailBodyForQaAdd() {
		return mailBodyForQaAdd;
	}
	public void setMailBodyForQaAdd(String mailBodyForQaAdd) {
		this.mailBodyForQaAdd = mailBodyForQaAdd;
	}
	public String getMailBodyForQaReply() {
		return mailBodyForQaReply;
	}
	public void setMailBodyForQaReply(String mailBodyForQaReply) {
		this.mailBodyForQaReply = mailBodyForQaReply;
	}
	public String getMailSmtpServer() {
		return mailSmtpServer;
	}
	public void setMailSmtpServer(String mailSmtpServer) {
		this.mailSmtpServer = mailSmtpServer;
	}
	public String getMailSmtpUser() {
		return mailSmtpUser;
	}
	public void setMailSmtpUser(String mailSmtpUser) {
		this.mailSmtpUser = mailSmtpUser;
	}
	public String getMailSmtpPws() {
		return mailSmtpPws;
	}
	public void setMailSmtpPws(String mailSmtpPws) {
		this.mailSmtpPws = mailSmtpPws;
	}
	public String getMailSenderAddr() {
		return mailSenderAddr;
	}
	public void setMailSenderAddr(String mailSenderAddr) {
		this.mailSenderAddr = mailSenderAddr;
	}
	public String getFileUploadExtName() {
		return fileUploadExtName;
	}
	public void setFileUploadExtName(String fileUploadExtName) {
		this.fileUploadExtName = fileUploadExtName;
	}
	public String getFilterWords() {
		return filterWords;
	}
	public void setFilterWords(String filterWords) {
		this.filterWords = filterWords;
	}
	public boolean isUserRegAllow() {
		return userRegAllow;
	}
	public void setUserRegAllow(boolean userRegAllow) {
		this.userRegAllow = userRegAllow;
	}
	public int getUserGroupOfAutoPassed() {
		return userGroupOfAutoPassed;
	}
	public void setUserGroupOfAutoPassed(int userGroupOfAutoPassed) {
		this.userGroupOfAutoPassed = userGroupOfAutoPassed;
	}
	public boolean isOneMailForReg() {
		return oneMailForReg;
	}
	public void setOneMailForReg(boolean oneMailForReg) {
		this.oneMailForReg = oneMailForReg;
	}
	public boolean isUserLoginAllow() {
		return userLoginAllow;
	}
	public void setUserLoginAllow(boolean userLoginAllow) {
		this.userLoginAllow = userLoginAllow;
	}
	public String getUserGroupWhenNotLoginAllow() {
		return userGroupWhenNotLoginAllow;
	}
	public void setUserGroupWhenNotLoginAllow(String userGroupWhenNotLoginAllow) {
		this.userGroupWhenNotLoginAllow = userGroupWhenNotLoginAllow;
	}
	public int getModeOfComment() {
		return modeOfComment;
	}
	public void setModeOfComment(int modeOfComment) {
		this.modeOfComment = modeOfComment;
	}
	public String getUserGroupsForStat() {
		return userGroupsForStat;
	}
	public void setUserGroupsForStat(String userGroupsForStat) {
		this.userGroupsForStat = userGroupsForStat;
	}
	public String getLicenseAgreement() {
		return licenseAgreement;
	}
	public void setLicenseAgreement(String licenseAgreement) {
		this.licenseAgreement = licenseAgreement;
	}
	public boolean isCommentsOpen() {
		return commentsOpen;
	}
	public void setCommentsOpen(boolean commentsOpen) {
		this.commentsOpen = commentsOpen;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public long getNviews() {
		return nviews;
	}
	public void setNviews(long nviews) {
		this.nviews = nviews;
	}
	public int getActAfterReg() {
		return actAfterReg;
	}
	public void setActAfterReg(int actAfterReg) {
		this.actAfterReg = actAfterReg;
	}
	
}
