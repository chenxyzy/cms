package com.lerx.article.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class ArticleThread {
	private Long id;
	private String title;
	private boolean eyeCatching;
	private boolean topOne;
	private String accessionalTitle;
	private String pensileTitle;
	private String shortTitle;
	private ArticleGroup articleGroup;
	private int location; // 文章位置 0文章系统，1个人空间  个人空间拟用新表，此属性将无效
	private boolean state;
	private boolean notice;
	private boolean noticeShowBody;
	private boolean soul;
	private boolean byEditor;
	private int views;
	private int price;
	private String synopsis; // 简介
	private String author;
	private String authorDept;
	private String authorEmail;
	private String authorUrl;
	private Timestamp addTime;
	private long addTimeLong;
	private Timestamp lastEditTime;
	private long lastEditTimeLong;
	private String lastViewIp;
	private Timestamp lastViewTime;
	private long lastViewTimeLong;
	private User user;
	private String member;
	private String passer; // 审核者
	private Timestamp passingTime; // 审核时间
	private long passingTimeLong;
	private String mainImg;
	private String thumbnail; // 缩略图
	private String mainImgExplain;
	private String journal;
	private String linkTitle;
	private String linkUrl;
	private boolean linkJump; // 连接类型 是否直接跳转
	private String mentor;
	private String body;
	private boolean htmlCreated;
	private boolean comment;
	private String htmlUrlPath;
	private String htmlURLFile;
	private String encryptedParmStr;
	private int defaultAttaMediaFormat;
	private String attaLineOrderFormatStr;
	
	private long proponents;			//支持者数
	private long opponents;				//反对者数
	private long neutrals;				//中立者数
	private boolean pollAllow;
	

//	public ArticleThread() {
//
//	}

//	public ArticleThread(Long id,String title,Timestamp addTime, boolean eyeCatching,
//			String shortTitle, boolean state, int location, boolean notice,
//			boolean soul, int views, String author, String authorDept,
//			String member,String mainImg, String thumbnail, String linkUrl,
//			String linkTitle, boolean linkJump, 
//			boolean htmlCreated, String htmlUrlPath,
//			String htmlURLFile) {
//		this.id = id;
//		System.out.println("addTime.toString():");
//		System.out.println("addTime.toString():"+addTime.toString());
//		this.addTime=TimeUtil.stringToTimestamp(addTime.toString());
//		this.author = author;
//		this.authorDept = authorDept;
//		this.eyeCatching = eyeCatching;
//		this.htmlCreated = htmlCreated;
//		this.htmlURLFile = htmlURLFile;
//		this.htmlUrlPath = htmlUrlPath;
//		this.linkJump = linkJump;
//		this.linkTitle = linkTitle;
//		this.linkUrl = linkUrl;
//		this.location = location;
//		this.member = member;
//		this.notice = notice;
//		this.shortTitle = shortTitle;
//		this.soul = soul;
//		this.state = state;
//		this.mainImg=mainImg;
//		this.thumbnail = thumbnail;
//		this.title = title;
//		this.views = views;
//	}

	public boolean isByEditor() {
		return byEditor;
	}

	public void setByEditor(boolean byEditor) {
		this.byEditor = byEditor;
	}

	public boolean isTopOne() {
		return topOne;
	}

	public void setTopOne(boolean topOne) {
		this.topOne = topOne;
	}

	public boolean isSoul() {
		return soul;
	}

	public void setSoul(boolean soul) {
		this.soul = soul;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEyeCatching() {
		return eyeCatching;
	}

	public void setEyeCatching(boolean eyeCatching) {
		this.eyeCatching = eyeCatching;
	}

	public String getAccessionalTitle() {
		return accessionalTitle;
	}

	public void setAccessionalTitle(String accessionalTitle) {
		this.accessionalTitle = accessionalTitle;
	}

	public String getPensileTitle() {
		return pensileTitle;
	}

	public void setPensileTitle(String pensileTitle) {
		this.pensileTitle = pensileTitle;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public ArticleGroup getArticleGroup() {
		return articleGroup;
	}

	public void setArticleGroup(ArticleGroup articleGroup) {
		this.articleGroup = articleGroup;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public boolean isNoticeShowBody() {
		return noticeShowBody;
	}

	public void setNoticeShowBody(boolean noticeShowBody) {
		this.noticeShowBody = noticeShowBody;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorDept() {
		return authorDept;
	}

	public void setAuthorDept(String authorDept) {
		this.authorDept = authorDept;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getPasser() {
		return passer;
	}

	public void setPasser(String passer) {
		this.passer = passer;
	}

	public Timestamp getPassingTime() {
		return passingTime;
	}

	public void setPassingTime(Timestamp passingTime) {
		this.passingTime = passingTime;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMainImgExplain() {
		return mainImgExplain;
	}

	public void setMainImgExplain(String mainImgExplain) {
		this.mainImgExplain = mainImgExplain;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public boolean isLinkJump() {
		return linkJump;
	}

	public void setLinkJump(boolean linkJump) {
		this.linkJump = linkJump;
	}

	public String getMentor() {
		return mentor;
	}

	public void setMentor(String mentor) {
		this.mentor = mentor;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public int getDefaultAttaMediaFormat() {
		return defaultAttaMediaFormat;
	}

	public void setDefaultAttaMediaFormat(int defaultAttaMediaFormat) {
		this.defaultAttaMediaFormat = defaultAttaMediaFormat;
	}

	public String getAttaLineOrderFormatStr() {
		return attaLineOrderFormatStr;
	}

	public void setAttaLineOrderFormatStr(String attaLineOrderFormatStr) {
		this.attaLineOrderFormatStr = attaLineOrderFormatStr;
	}

	public long getOpponents() {
		return opponents;
	}

	public void setOpponents(long opponents) {
		this.opponents = opponents;
	}

	public long getProponents() {
		return proponents;
	}

	public void setProponents(long proponents) {
		this.proponents = proponents;
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

	public long getAddTimeLong() {
		return addTimeLong;
	}

	public void setAddTimeLong(long addTimeLong) {
		this.addTimeLong = addTimeLong;
	}

	public long getLastEditTimeLong() {
		return lastEditTimeLong;
	}

	public void setLastEditTimeLong(long lastEditTimeLong) {
		this.lastEditTimeLong = lastEditTimeLong;
	}

	public long getLastViewTimeLong() {
		return lastViewTimeLong;
	}

	public void setLastViewTimeLong(long lastViewTimeLong) {
		this.lastViewTimeLong = lastViewTimeLong;
	}

	public long getPassingTimeLong() {
		return passingTimeLong;
	}

	public void setPassingTimeLong(long passingTimeLong) {
		this.passingTimeLong = passingTimeLong;
	}

}
