package com.lerx.style.draw.vo;

public class DrawStyle implements Cloneable{
	private Integer id;
	private String author;
	private String description;
	private boolean state;
	private String styleName;
	private String htmlTemplate;
	private String cssCode;
	private String htmlCode;
	private String titleFormat;
	private String resultLineFormat;
	private String startCode;
	private String resultCode;
	private String clearCode;
	private String maskFormat;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
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
	public String getTitleFormat() {
		return titleFormat;
	}
	public void setTitleFormat(String titleFormat) {
		this.titleFormat = titleFormat;
	}
	public String getResultLineFormat() {
		return resultLineFormat;
	}
	public void setResultLineFormat(String resultLineFormat) {
		this.resultLineFormat = resultLineFormat;
	}
	public String getStartCode() {
		return startCode;
	}
	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getClearCode() {
		return clearCode;
	}
	public void setClearCode(String clearCode) {
		this.clearCode = clearCode;
	}
	public String getMaskFormat() {
		return maskFormat;
	}
	public void setMaskFormat(String maskFormat) {
		this.maskFormat = maskFormat;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object o = null;
		try {
			o = (DrawStyle) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	}
}
