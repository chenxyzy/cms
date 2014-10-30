package com.lerx.style.bbs.vo;

import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.style.site.vo.SiteStyle;

public class BbsStyle implements Cloneable {
	private Integer id;
	private String styleName;
	private String author;
	private String description;
	private boolean state;
	private boolean def;
	private BbsStyleSubElementInCommon publicStyle;
	private BbsStyleSubElementInCommon indexStyle;
	private BbsStyleSubElementInCommon forumStyle;
	private BbsStyleSubElementInCommon themeStyle;
	private BbsStyleSubElementInCommon searchStyle;
	private BbsStyleSubElementInCommon genericStyle;
	private BbsStyleSubElementInCommon editThreadStyle;
	
	private String locationSplitStr; 				//位置分隔符号
	private String hrefLineFormatStrOverAll; 		//通用数据行格式
	private String hrefLineFormatWithSnStrOverAll;	//带行号及其它信息的数据行格式
	private String bmShowFormat;						//版主或管理员显示格式
	private String lastEditorShowFormat;
	private String publicCode1;
	private String publicCode2;
	private String publicCode3;
	private String publicCode4;
	private String customFormatCode1;
	private String customFormatCode2;
	private String customFormatCode3;
	private String customFormatCode4;
	private String customFormatCode5;
	private String customFormatCode6;
	private String customFormatCode7;
	private String customFormatCode8;
	
	private String addThemeButtomCode;				//发帖按钮（有些页面或未登录将不显示）
	private String editThemeAreaCode;				//发贴代码
	private String replieThreadAreaCode;			//快速回复区代码
	
	private String icoFolderUrl;
	
	private String topActCode;
	private String quoteAreaCode;					//引用区域代码
	private String quoteButtomCode;
	private String indexShowLastThreadFormatStr;	//最后发表信息区域
	private String resultPageCode;					//错误页面代码
	private String shieldedShowCode;
	private String afterReplyShowCode;
	
	public String getTopActCode() {
		return topActCode;
	}
	public void setTopActCode(String topActCode) {
		this.topActCode = topActCode;
	}
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
	public String getHrefLineFormatStrOverAll() {
		return hrefLineFormatStrOverAll;
	}
	public void setHrefLineFormatStrOverAll(String hrefLineFormatStrOverAll) {
		this.hrefLineFormatStrOverAll = hrefLineFormatStrOverAll;
	}
	public String getHrefLineFormatWithSnStrOverAll() {
		return hrefLineFormatWithSnStrOverAll;
	}
	public void setHrefLineFormatWithSnStrOverAll(
			String hrefLineFormatWithSnStrOverAll) {
		this.hrefLineFormatWithSnStrOverAll = hrefLineFormatWithSnStrOverAll;
	}
	
	public String getIcoFolderUrl() {
		return icoFolderUrl;
	}
	public void setIcoFolderUrl(String icoFolderUrl) {
		this.icoFolderUrl = icoFolderUrl;
	}
	public String getBmShowFormat() {
		return bmShowFormat;
	}
	public void setBmShowFormat(String bmShowFormat) {
		this.bmShowFormat = bmShowFormat;
	}
	public BbsStyleSubElementInCommon getPublicStyle() {
		return publicStyle;
	}
	public void setPublicStyle(BbsStyleSubElementInCommon publicStyle) {
		this.publicStyle = publicStyle;
	}
	public BbsStyleSubElementInCommon getIndexStyle() {
		return indexStyle;
	}
	public void setIndexStyle(BbsStyleSubElementInCommon indexStyle) {
		this.indexStyle = indexStyle;
	}
	public BbsStyleSubElementInCommon getForumStyle() {
		return forumStyle;
	}
	public void setForumStyle(BbsStyleSubElementInCommon forumStyle) {
		this.forumStyle = forumStyle;
	}
	public BbsStyleSubElementInCommon getEditThreadStyle() {
		return editThreadStyle;
	}
	public void setEditThreadStyle(BbsStyleSubElementInCommon editThreadStyle) {
		this.editThreadStyle = editThreadStyle;
	}
	public BbsStyleSubElementInCommon getThemeStyle() {
		return themeStyle;
	}
	public void setThemeStyle(BbsStyleSubElementInCommon themeStyle) {
		this.themeStyle = themeStyle;
	}
	public BbsStyleSubElementInCommon getSearchStyle() {
		return searchStyle;
	}
	public void setSearchStyle(BbsStyleSubElementInCommon searchStyle) {
		this.searchStyle = searchStyle;
	}
	
	public BbsStyleSubElementInCommon getGenericStyle() {
		return genericStyle;
	}
	public void setGenericStyle(BbsStyleSubElementInCommon genericStyle) {
		this.genericStyle = genericStyle;
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
	
	public String getCustomFormatCode1() {
		return customFormatCode1;
	}
	public void setCustomFormatCode1(String customFormatCode1) {
		this.customFormatCode1 = customFormatCode1;
	}
	public String getCustomFormatCode2() {
		return customFormatCode2;
	}
	public void setCustomFormatCode2(String customFormatCode2) {
		this.customFormatCode2 = customFormatCode2;
	}
	public String getCustomFormatCode3() {
		return customFormatCode3;
	}
	public void setCustomFormatCode3(String customFormatCode3) {
		this.customFormatCode3 = customFormatCode3;
	}
	public String getCustomFormatCode4() {
		return customFormatCode4;
	}
	public void setCustomFormatCode4(String customFormatCode4) {
		this.customFormatCode4 = customFormatCode4;
	}
	public String getCustomFormatCode5() {
		return customFormatCode5;
	}
	public void setCustomFormatCode5(String customFormatCode5) {
		this.customFormatCode5 = customFormatCode5;
	}
	public String getCustomFormatCode6() {
		return customFormatCode6;
	}
	public void setCustomFormatCode6(String customFormatCode6) {
		this.customFormatCode6 = customFormatCode6;
	}
	public String getCustomFormatCode7() {
		return customFormatCode7;
	}
	public void setCustomFormatCode7(String customFormatCode7) {
		this.customFormatCode7 = customFormatCode7;
	}
	public String getCustomFormatCode8() {
		return customFormatCode8;
	}
	public void setCustomFormatCode8(String customFormatCode8) {
		this.customFormatCode8 = customFormatCode8;
	}
	

	public String getEditThemeAreaCode() {
		return editThemeAreaCode;
	}
	public void setEditThemeAreaCode(String editThemeAreaCode) {
		this.editThemeAreaCode = editThemeAreaCode;
	}
	public String getReplieThreadAreaCode() {
		return replieThreadAreaCode;
	}
	public void setReplieThreadAreaCode(String replieThreadAreaCode) {
		this.replieThreadAreaCode = replieThreadAreaCode;
	}

	public String getQuoteAreaCode() {
		return quoteAreaCode;
	}
	public void setQuoteAreaCode(String quoteAreaCode) {
		this.quoteAreaCode = quoteAreaCode;
	}
	public String getIndexShowLastThreadFormatStr() {
		return indexShowLastThreadFormatStr;
	}
	public void setIndexShowLastThreadFormatStr(String indexShowLastThreadFormatStr) {
		this.indexShowLastThreadFormatStr = indexShowLastThreadFormatStr;
	}
	public String getLocationSplitStr() {
		return locationSplitStr;
	}
	public void setLocationSplitStr(String locationSplitStr) {
		this.locationSplitStr = locationSplitStr;
	}
	
	public String getShieldedShowCode() {
		return shieldedShowCode;
	}
	public void setShieldedShowCode(String shieldedShowCode) {
		this.shieldedShowCode = shieldedShowCode;
	}
	public String getAfterReplyShowCode() {
		return afterReplyShowCode;
	}
	public void setAfterReplyShowCode(String afterReplyShowCode) {
		this.afterReplyShowCode = afterReplyShowCode;
	}
	public String getResultPageCode() {
		return resultPageCode;
	}
	public void setResultPageCode(String resultPageCode) {
		this.resultPageCode = resultPageCode;
	}
	
	public String getAddThemeButtomCode() {
		return addThemeButtomCode;
	}
	public void setAddThemeButtomCode(String addThemeButtomCode) {
		this.addThemeButtomCode = addThemeButtomCode;
	}
	
	public String getQuoteButtomCode() {
		return quoteButtomCode;
	}
	public void setQuoteButtomCode(String quoteButtomCode) {
		this.quoteButtomCode = quoteButtomCode;
	}
	public boolean isDef() {
		return def;
	}
	public void setDef(boolean def) {
		this.def = def;
	}
	public String getLastEditorShowFormat() {
		return lastEditorShowFormat;
	}
	public void setLastEditorShowFormat(String lastEditorShowFormat) {
		this.lastEditorShowFormat = lastEditorShowFormat;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object o = null;
		try {
			o = (SiteStyle) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	}
	
}
