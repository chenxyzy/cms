package com.lerx.user.vo;

public class Passer {
	
	/*
	 * 用户审核者
	 * 由该用户制定问答由请求者直接回答进行审核或手工审核
	 */
	private long id;
	private User user;
	private UserGroup toUG;
	private String passerInf1;			//例如：单位
	private boolean lockPasserInf1;
	private String passerInf2;			//例如：姓名
	private boolean lockPasserInf2;
	private boolean state;
	private String question1;
	private String question2;
	private String answer1;
	private String answer2;
	private String priTag1;				//标签1，将记录到被审核者信息中
	private String priTag2;				//同上，若空将不记录或重新输入记录
	private String questionForPriTag1;
	private String questionForPriTag2;
	private boolean lockPriTag1;
	private boolean lockPriTag2;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserGroup getToUG() {
		return toUG;
	}
	public void setToUG(UserGroup toUG) {
		this.toUG = toUG;
	}
	public boolean isLockPasserInf1() {
		return lockPasserInf1;
	}
	public void setLockPasserInf1(boolean lockPasserInf1) {
		this.lockPasserInf1 = lockPasserInf1;
	}
	public boolean isLockPasserInf2() {
		return lockPasserInf2;
	}
	public void setLockPasserInf2(boolean lockPasserInf2) {
		this.lockPasserInf2 = lockPasserInf2;
	}
	public String getPasserInf1() {
		return passerInf1;
	}
	public void setPasserInf1(String passerInf1) {
		this.passerInf1 = passerInf1;
	}
	public String getPasserInf2() {
		return passerInf2;
	}
	public void setPasserInf2(String passerInf2) {
		this.passerInf2 = passerInf2;
	}
	public String getPriTag1() {
		return priTag1;
	}
	public void setPriTag1(String priTag1) {
		this.priTag1 = priTag1;
	}
	public String getPriTag2() {
		return priTag2;
	}
	public void setPriTag2(String priTag2) {
		this.priTag2 = priTag2;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
	public String getQuestionForPriTag1() {
		return questionForPriTag1;
	}
	public void setQuestionForPriTag1(String questionForPriTag1) {
		this.questionForPriTag1 = questionForPriTag1;
	}
	public String getQuestionForPriTag2() {
		return questionForPriTag2;
	}
	public void setQuestionForPriTag2(String questionForPriTag2) {
		this.questionForPriTag2 = questionForPriTag2;
	}
	public boolean isLockPriTag1() {
		return lockPriTag1;
	}
	public void setLockPriTag1(boolean lockPriTag1) {
		this.lockPriTag1 = lockPriTag1;
	}
	public boolean isLockPriTag2() {
		return lockPriTag2;
	}
	public void setLockPriTag2(boolean lockPriTag2) {
		this.lockPriTag2 = lockPriTag2;
	}
	
	
	
}
