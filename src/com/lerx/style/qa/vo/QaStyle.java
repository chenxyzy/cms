package com.lerx.style.qa.vo;

public class QaStyle implements Cloneable{
	
	private Integer id;
	private String styleName;
	private String author;
	private String description;
	private boolean state;
	
	private QaStyleSubElementInCommon publicStyle;
	private QaStyleSubElementInCommon indexStyle;
	private QaStyleSubElementInCommon navStyle;
	private QaStyleSubElementInCommon itemStyle;
	
	
	private String addAreaCode;  	//增加问题区域
	private String replyAreaCode;	//回答问题区域
	private String locationSplitStr;
	private String ajaxRealTimeCode;
	private String publicCode1;
	private String publicCode2;
	private String publicCode3;
	private String publicCode4;
	private String processedStr;
	private String noProcessedStr;
	private String openStr;
	private String noOpenStr;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
	public String getAddAreaCode() {
		return addAreaCode;
	}
	public void setAddAreaCode(String addAreaCode) {
		this.addAreaCode = addAreaCode;
	}
	public String getReplyAreaCode() {
		return replyAreaCode;
	}
	public void setReplyAreaCode(String replyAreaCode) {
		this.replyAreaCode = replyAreaCode;
	}
	
	public String getAjaxRealTimeCode() {
		return ajaxRealTimeCode;
	}
	public void setAjaxRealTimeCode(String ajaxRealTimeCode) {
		this.ajaxRealTimeCode = ajaxRealTimeCode;
	}
	
	public String getPublicCode1() {
		return publicCode1;
	}
	public void setPublicCode1(String publicCode1) {
		this.publicCode1 = publicCode1;
	}
	public String getPublicCode2() {
		return publicCode2;
	}
	public void setPublicCode2(String publicCode2) {
		this.publicCode2 = publicCode2;
	}
	public String getPublicCode3() {
		return publicCode3;
	}
	public void setPublicCode3(String publicCode3) {
		this.publicCode3 = publicCode3;
	}
	public String getPublicCode4() {
		return publicCode4;
	}
	public void setPublicCode4(String publicCode4) {
		this.publicCode4 = publicCode4;
	}
	
	public String getProcessedStr() {
		return processedStr;
	}
	public void setProcessedStr(String processedStr) {
		this.processedStr = processedStr;
	}
	public String getNoProcessedStr() {
		return noProcessedStr;
	}
	public void setNoProcessedStr(String noProcessedStr) {
		this.noProcessedStr = noProcessedStr;
	}
	
	public String getOpenStr() {
		return openStr;
	}
	public void setOpenStr(String openStr) {
		this.openStr = openStr;
	}
	public String getNoOpenStr() {
		return noOpenStr;
	}
	public void setNoOpenStr(String noOpenStr) {
		this.noOpenStr = noOpenStr;
	}
	
	public QaStyleSubElementInCommon getPublicStyle() {
		return publicStyle;
	}
	public void setPublicStyle(QaStyleSubElementInCommon publicStyle) {
		this.publicStyle = publicStyle;
	}
	public QaStyleSubElementInCommon getIndexStyle() {
		return indexStyle;
	}
	public void setIndexStyle(QaStyleSubElementInCommon indexStyle) {
		this.indexStyle = indexStyle;
	}
	public QaStyleSubElementInCommon getNavStyle() {
		return navStyle;
	}
	public void setNavStyle(QaStyleSubElementInCommon navStyle) {
		this.navStyle = navStyle;
	}
	public QaStyleSubElementInCommon getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(QaStyleSubElementInCommon itemStyle) {
		this.itemStyle = itemStyle;
	}
	public String getLocationSplitStr() {
		return locationSplitStr;
	}
	public void setLocationSplitStr(String locationSplitStr) {
		this.locationSplitStr = locationSplitStr;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object o = null;
		try {
			o = (QaStyle) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	}
	
}
