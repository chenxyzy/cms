package com.lerx.qa.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class QaItem {
	
	private long id;
	private String salt;
	private String author;
	private String title;
	private String question;
	private String answer;
	private String questioner;
	private String replier;
	
	private String addIp;
	private Timestamp addTime;
	private Timestamp replyTime;
	private String lastViewIp;
	private Timestamp lastViewTime;
	private String email;
	private String addr;
	private String qq;
	private String phone;
	private boolean open;
	private boolean state;
	private int views;
	private String password;
	private boolean htmlCreated;
	private String htmlUrlPath;
	private String htmlURLFile;
	private User addUser;
	private User replyUser;
	private QaNav qn;
	private String extInf1;
	private String extInf2;
	private String extInf3;
	private String extInf4;
	private String extInf5;
	private String extInf6;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestioner() {
		return questioner;
	}
	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}
	public String getReplier() {
		return replier;
	}
	public void setReplier(String replier) {
		this.replier = replier;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Timestamp getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public User getAddUser() {
		return addUser;
	}
	public void setAddUser(User addUser) {
		this.addUser = addUser;
	}
	public User getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(User replyUser) {
		this.replyUser = replyUser;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public QaNav getQn() {
		return qn;
	}
	public void setQn(QaNav qn) {
		this.qn = qn;
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
	public String getExtInf1() {
		return extInf1;
	}
	public void setExtInf1(String extInf1) {
		this.extInf1 = extInf1;
	}
	public String getExtInf2() {
		return extInf2;
	}
	public void setExtInf2(String extInf2) {
		this.extInf2 = extInf2;
	}
	public String getExtInf3() {
		return extInf3;
	}
	public void setExtInf3(String extInf3) {
		this.extInf3 = extInf3;
	}
	public String getExtInf4() {
		return extInf4;
	}
	public void setExtInf4(String extInf4) {
		this.extInf4 = extInf4;
	}
	public String getExtInf5() {
		return extInf5;
	}
	public void setExtInf5(String extInf5) {
		this.extInf5 = extInf5;
	}
	public String getExtInf6() {
		return extInf6;
	}
	public void setExtInf6(String extInf6) {
		this.extInf6 = extInf6;
	}
	
}
