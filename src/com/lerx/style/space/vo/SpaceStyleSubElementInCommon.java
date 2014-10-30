package com.lerx.style.space.vo;

public class SpaceStyleSubElementInCommon {
	private Integer id;
	private String htmlTemplate;
	private String cssCode;
	private String htmlCode;
	private String topCode;
	private String footerCode;
	private String titleFormat;
	private String hrefLineFormat;
	private String memberPanelCodeForLogIn;
	private String memberPanelCodeForLogOut;
	private String majorLoopCodeInLump; // 主循环体
	private String minorLoopCodeInLump; // 副循环体
	/*
	 * 设置四个特定子代码，如果除public类外的其它类有此代码，则用此代码替换public中的代码
	 */
	private String specialCode1;
	private String specialCode2;
	private String specialCode3;
	private String specialCode4;
	
	private String editAreaCode;
	private String searchAreaCode;
	private String formTemplate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	public String getCssCode() {
		return cssCode;
	}
	public void setCssCode(String cssCode) {
		this.cssCode = cssCode;
	}
	public String getHtmlCode() {
		return htmlCode;
	}
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}
	public String getTopCode() {
		return topCode;
	}
	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}
	public String getFooterCode() {
		return footerCode;
	}
	public void setFooterCode(String footerCode) {
		this.footerCode = footerCode;
	}
	public String getTitleFormat() {
		return titleFormat;
	}
	public void setTitleFormat(String titleFormat) {
		this.titleFormat = titleFormat;
	}
	public String getHrefLineFormat() {
		return hrefLineFormat;
	}
	public void setHrefLineFormat(String hrefLineFormat) {
		this.hrefLineFormat = hrefLineFormat;
	}
	public String getMemberPanelCodeForLogIn() {
		return memberPanelCodeForLogIn;
	}
	public void setMemberPanelCodeForLogIn(String memberPanelCodeForLogIn) {
		this.memberPanelCodeForLogIn = memberPanelCodeForLogIn;
	}
	public String getMemberPanelCodeForLogOut() {
		return memberPanelCodeForLogOut;
	}
	public void setMemberPanelCodeForLogOut(String memberPanelCodeForLogOut) {
		this.memberPanelCodeForLogOut = memberPanelCodeForLogOut;
	}
	public String getMajorLoopCodeInLump() {
		return majorLoopCodeInLump;
	}
	public void setMajorLoopCodeInLump(String majorLoopCodeInLump) {
		this.majorLoopCodeInLump = majorLoopCodeInLump;
	}
	public String getMinorLoopCodeInLump() {
		return minorLoopCodeInLump;
	}
	public void setMinorLoopCodeInLump(String minorLoopCodeInLump) {
		this.minorLoopCodeInLump = minorLoopCodeInLump;
	}
	public String getSpecialCode1() {
		return specialCode1;
	}
	public void setSpecialCode1(String specialCode1) {
		this.specialCode1 = specialCode1;
	}
	public String getSpecialCode2() {
		return specialCode2;
	}
	public void setSpecialCode2(String specialCode2) {
		this.specialCode2 = specialCode2;
	}
	public String getSpecialCode3() {
		return specialCode3;
	}
	public void setSpecialCode3(String specialCode3) {
		this.specialCode3 = specialCode3;
	}
	public String getSpecialCode4() {
		return specialCode4;
	}
	public void setSpecialCode4(String specialCode4) {
		this.specialCode4 = specialCode4;
	}
	public String getEditAreaCode() {
		return editAreaCode;
	}
	public void setEditAreaCode(String editAreaCode) {
		this.editAreaCode = editAreaCode;
	}
	public String getSearchAreaCode() {
		return searchAreaCode;
	}
	public void setSearchAreaCode(String searchAreaCode) {
		this.searchAreaCode = searchAreaCode;
	}
	public String getFormTemplate() {
		return formTemplate;
	}
	public void setFormTemplate(String formTemplate) {
		this.formTemplate = formTemplate;
	}
	
}
