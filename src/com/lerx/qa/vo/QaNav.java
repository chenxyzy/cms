package com.lerx.qa.vo;

import com.lerx.style.qa.vo.QaStyle;

public class QaNav {

	private Integer id;
	private QaNav parentNav;
	private String title;
	private boolean state;
	private boolean loginNeed;
	private boolean sendMail;
	private String description;
	private String powerMask;
	private String adminEmail;
	private String sendEmail;
	private String serverOfSendEmail;
	private String usernameOfSendEmail;
	private String passwordOfSendEmail;
	private String sendTemplateForAdd;
	private String sendTemplateForReply;
	private String sendTitle;
	private QaStyle style;
	private String displayOrder;
	private int numberShowOn;
	private String staticHtmlFolder;
	private boolean refuseStaticHtml;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public QaNav getParentNav() {
		return parentNav;
	}
	public void setParentNav(QaNav parentNav) {
		this.parentNav = parentNav;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isSendMail() {
		return sendMail;
	}
	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isLoginNeed() {
		return loginNeed;
	}
	public void setLoginNeed(boolean loginNeed) {
		this.loginNeed = loginNeed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPowerMask() {
		return powerMask;
	}
	public void setPowerMask(String powerMask) {
		this.powerMask = powerMask;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getServerOfSendEmail() {
		return serverOfSendEmail;
	}
	public void setServerOfSendEmail(String serverOfSendEmail) {
		this.serverOfSendEmail = serverOfSendEmail;
	}
	public String getUsernameOfSendEmail() {
		return usernameOfSendEmail;
	}
	public void setUsernameOfSendEmail(String usernameOfSendEmail) {
		this.usernameOfSendEmail = usernameOfSendEmail;
	}
	public String getPasswordOfSendEmail() {
		return passwordOfSendEmail;
	}
	public void setPasswordOfSendEmail(String passwordOfSendEmail) {
		this.passwordOfSendEmail = passwordOfSendEmail;
	}

	public String getSendTemplateForAdd() {
		return sendTemplateForAdd;
	}
	public void setSendTemplateForAdd(String sendTemplateForAdd) {
		this.sendTemplateForAdd = sendTemplateForAdd;
	}
	public String getSendTemplateForReply() {
		return sendTemplateForReply;
	}
	public void setSendTemplateForReply(String sendTemplateForReply) {
		this.sendTemplateForReply = sendTemplateForReply;
	}
	public String getSendTitle() {
		return sendTitle;
	}
	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}
	public QaStyle getStyle() {
		return style;
	}
	public void setStyle(QaStyle style) {
		this.style = style;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public int getNumberShowOn() {
		return numberShowOn;
	}
	public void setNumberShowOn(int numberShowOn) {
		this.numberShowOn = numberShowOn;
	}
	public String getStaticHtmlFolder() {
		return staticHtmlFolder;
	}
	public void setStaticHtmlFolder(String staticHtmlFolder) {
		this.staticHtmlFolder = staticHtmlFolder;
	}
	public boolean isRefuseStaticHtml() {
		return refuseStaticHtml;
	}
	public void setRefuseStaticHtml(boolean refuseStaticHtml) {
		this.refuseStaticHtml = refuseStaticHtml;
	}
	
}
