package com.lerx.style.site.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="site_style")
@org.hibernate.annotations.Table(appliesTo = "site_style")

public class SiteStyle implements Cloneable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=50)
	private String styleName;
	
	@Column(length=50)
	private String author;
	
	
	private String description;

	private boolean state;
	private boolean def;

	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="pid")
	private SiteStyleSubElementInCommon publicStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="iid")
	private SiteStyleSubElementInCommon indexStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="cid")
	private SiteStyleSubElementInCommon classStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="tid")
	private SiteStyleSubElementInCommon threadStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="rid")
	private SiteStyleSubElementInCommon regStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="lid")
	private SiteStyleSubElementInCommon loginStyle;
	
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true,  fetch = FetchType.EAGER)
	@JoinColumn(name="aid")
	private SiteStyleSubElementInCommon articleAddStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="eid")
	private SiteStyleSubElementInCommon articleEditStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="sid")
	private SiteStyleSubElementInCommon searchStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="gid")
	private SiteStyleSubElementInCommon genericStyle;
	
	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="uid")
	private SiteStyleSubElementInCommon userCenterStyle;


	@ManyToOne(cascade={CascadeType.ALL},optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name="mid")
	private SiteStyleSubElementInCommon commentStyle;
	
	private String locationSplitStr; // 位置分隔符号
	private String rootResFolder;
	
	@Column(columnDefinition="text")
	private String hrefLineFormatStrOverAll; // 通用数据行格式
	
	@Column(columnDefinition="text")
	private String hrefLineFormatWithSnStrOverAll; //带行号及其它信息的数据行格式

	@Column(columnDefinition="longtext")
	private String mediaPlayCode1;
	
	@Column(columnDefinition="longtext")
	private String mediaPlayCode2;
	
	@Column(columnDefinition="longtext")
	private String mediaPlayCode3;
	
	@Column(columnDefinition="text")
	private String mediaPlayOuterLayerCodeForArtPage;
	
	@Column(columnDefinition="text")
	private String mediaPlayOuterLayerCodeForAttaPage;
	
	@Column(columnDefinition="longtext")
	private String publicCode1;
	
	@Column(columnDefinition="longtext")
	private String publicCode2;
	
	@Column(columnDefinition="longtext")
	private String publicCode3;
	
	@Column(columnDefinition="longtext")
	private String publicCode4;
	
	private String ajaxStrFormat;
	
	@Column(columnDefinition="longtext")
	private String resultPageCode;
	
	
	@Column(columnDefinition="text")
	private String attachmentLineShowForImg; 		//附件显示 图像
	
	@Column(columnDefinition="text")
	private String attachmentLineShowForDownload; 	//附件显示 下载
	
	@Column(columnDefinition="text")
	private String attachmentLineShowForPlay;		//附件显示 播放

	// 新增
//	private String lineBreakStrS; 	// 行标志符头 如<div> 用于下级栏目显示 （下同）
//	private String lineBreakStrE; 	// 行标志符尾 如</div>
//	private String columnSplitStrS; // 列分隔符头 如<span>
//	private String columnSplitStrE;
	private String lineBlockFormat;
	private String columnBlockFormat;
	@Column(columnDefinition="text")
	private String customFormatCode1;
	
	@Column(columnDefinition="text")
	private String customFormatCode2;
	
	@Column(columnDefinition="text")
	private String customFormatCode3;
	
	@Column(columnDefinition="text")
	private String customFormatCode4;
	
	@Column(columnDefinition="text")
	private String customFormatCode5;
	
	@Column(columnDefinition="text")
	private String customFormatCode6;
	
	@Column(columnDefinition="text")
	private String customFormatCode7;
	
	@Column(columnDefinition="text")
	private String customFormatCode8;

//	@Embedded
//	private CommentStyle commentStyle;
	
	private String lastArticleForwardCode;
	private String nextArticleForwardCode;
	private String eyeCatchingCode;
	
	@Column(columnDefinition="text")
	private String passedStr;
	
	@Column(columnDefinition="text")
	private String noPassedStr;
	
	private String noPassedAltStr;
	private String threadAjaxShowStr;
	private String ajaxOfArticlePrice;
	private String ajaxOfBeforeView;
	private String ajaxOfArtPoll;
	
	@Column(columnDefinition="text")
	private String statCode;
	
	
	public String getStatCode() {
		return statCode;
	}

	public void setStatCode(String statCode) {
		this.statCode = statCode;
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

	public SiteStyleSubElementInCommon getPublicStyle() {
		return publicStyle;
	}

	public void setPublicStyle(SiteStyleSubElementInCommon publicStyle) {
		this.publicStyle = publicStyle;
	}

	public SiteStyleSubElementInCommon getIndexStyle() {
		return indexStyle;
	}

	public void setIndexStyle(SiteStyleSubElementInCommon indexStyle) {
		this.indexStyle = indexStyle;
	}

	public SiteStyleSubElementInCommon getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(SiteStyleSubElementInCommon classStyle) {
		this.classStyle = classStyle;
	}

	public SiteStyleSubElementInCommon getThreadStyle() {
		return threadStyle;
	}

	public void setThreadStyle(SiteStyleSubElementInCommon threadStyle) {
		this.threadStyle = threadStyle;
	}

	public SiteStyleSubElementInCommon getRegStyle() {
		return regStyle;
	}

	public void setRegStyle(SiteStyleSubElementInCommon regStyle) {
		this.regStyle = regStyle;
	}

	public SiteStyleSubElementInCommon getLoginStyle() {
		return loginStyle;
	}

	public void setLoginStyle(SiteStyleSubElementInCommon loginStyle) {
		this.loginStyle = loginStyle;
	}

	public SiteStyleSubElementInCommon getArticleAddStyle() {
		return articleAddStyle;
	}

	public void setArticleAddStyle(SiteStyleSubElementInCommon articleAddStyle) {
		this.articleAddStyle = articleAddStyle;
	}

	public SiteStyleSubElementInCommon getGenericStyle() {
		return genericStyle;
	}

	public void setGenericStyle(SiteStyleSubElementInCommon genericStyle) {
		this.genericStyle = genericStyle;
	}

	public SiteStyleSubElementInCommon getCommentStyle() {
		return commentStyle;
	}

	public void setCommentStyle(SiteStyleSubElementInCommon commentStyle) {
		this.commentStyle = commentStyle;
	}

	public String getLocationSplitStr() {
		return locationSplitStr;
	}

	public void setLocationSplitStr(String locationSplitStr) {
		this.locationSplitStr = locationSplitStr;
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

	public String getMediaPlayCode1() {
		return mediaPlayCode1;
	}

	public void setMediaPlayCode1(String mediaPlayCode1) {
		this.mediaPlayCode1 = mediaPlayCode1;
	}

	public String getMediaPlayCode2() {
		return mediaPlayCode2;
	}

	public void setMediaPlayCode2(String mediaPlayCode2) {
		this.mediaPlayCode2 = mediaPlayCode2;
	}

	public String getMediaPlayCode3() {
		return mediaPlayCode3;
	}

	public void setMediaPlayCode3(String mediaPlayCode3) {
		this.mediaPlayCode3 = mediaPlayCode3;
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

	public String getAjaxStrFormat() {
		return ajaxStrFormat;
	}

	public void setAjaxStrFormat(String ajaxStrFormat) {
		this.ajaxStrFormat = ajaxStrFormat;
	}

	public String getResultPageCode() {
		return resultPageCode;
	}

	public void setResultPageCode(String resultPageCode) {
		this.resultPageCode = resultPageCode;
	}


	public SiteStyleSubElementInCommon getArticleEditStyle() {
		return articleEditStyle;
	}

	public void setArticleEditStyle(SiteStyleSubElementInCommon articleEditStyle) {
		this.articleEditStyle = articleEditStyle;
	}


	public SiteStyleSubElementInCommon getSearchStyle() {
		return searchStyle;
	}

	public void setSearchStyle(SiteStyleSubElementInCommon searchStyle) {
		this.searchStyle = searchStyle;
	}

	public String getLineBlockFormat() {
		return lineBlockFormat;
	}

	public void setLineBlockFormat(String lineBlockFormat) {
		this.lineBlockFormat = lineBlockFormat;
	}

	public String getColumnBlockFormat() {
		return columnBlockFormat;
	}

	public void setColumnBlockFormat(String columnBlockFormat) {
		this.columnBlockFormat = columnBlockFormat;
	}

	
	@Column(length = 65535)
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

//	public CommentStyle getCommentStyle() {
//		return commentStyle;
//	}
//
//	public void setCommentStyle(CommentStyle commentStyle) {
//		this.commentStyle = commentStyle;
//	}

	public String getLastArticleForwardCode() {
		return lastArticleForwardCode;
	}

	public void setLastArticleForwardCode(String lastArticleForwardCode) {
		this.lastArticleForwardCode = lastArticleForwardCode;
	}

	public String getNextArticleForwardCode() {
		return nextArticleForwardCode;
	}

	public void setNextArticleForwardCode(String nextArticleForwardCode) {
		this.nextArticleForwardCode = nextArticleForwardCode;
	}

	public String getEyeCatchingCode() {
		return eyeCatchingCode;
	}

	public void setEyeCatchingCode(String eyeCatchingCode) {
		this.eyeCatchingCode = eyeCatchingCode;
	}

	public String getPassedStr() {
		return passedStr;
	}

	public void setPassedStr(String passedStr) {
		this.passedStr = passedStr;
	}

	public String getNoPassedStr() {
		return noPassedStr;
	}

	public void setNoPassedStr(String noPassedStr) {
		this.noPassedStr = noPassedStr;
	}

	public String getNoPassedAltStr() {
		return noPassedAltStr;
	}

	public void setNoPassedAltStr(String noPassedAltStr) {
		this.noPassedAltStr = noPassedAltStr;
	}
	

	public String getThreadAjaxShowStr() {
		return threadAjaxShowStr;
	}

	public void setThreadAjaxShowStr(String threadAjaxShowStr) {
		this.threadAjaxShowStr = threadAjaxShowStr;
	}

	public SiteStyleSubElementInCommon getUserCenterStyle() {
		return userCenterStyle;
	}

	public void setUserCenterStyle(SiteStyleSubElementInCommon userCenterStyle) {
		this.userCenterStyle = userCenterStyle;
	}

	public String getMediaPlayOuterLayerCodeForArtPage() {
		return mediaPlayOuterLayerCodeForArtPage;
	}

	public void setMediaPlayOuterLayerCodeForArtPage(
			String mediaPlayOuterLayerCodeForArtPage) {
		this.mediaPlayOuterLayerCodeForArtPage = mediaPlayOuterLayerCodeForArtPage;
	}

	public String getMediaPlayOuterLayerCodeForAttaPage() {
		return mediaPlayOuterLayerCodeForAttaPage;
	}

	public void setMediaPlayOuterLayerCodeForAttaPage(
			String mediaPlayOuterLayerCodeForAttaPage) {
		this.mediaPlayOuterLayerCodeForAttaPage = mediaPlayOuterLayerCodeForAttaPage;
	}

	public String getAttachmentLineShowForImg() {
		return attachmentLineShowForImg;
	}

	public void setAttachmentLineShowForImg(String attachmentLineShowForImg) {
		this.attachmentLineShowForImg = attachmentLineShowForImg;
	}

	public String getAttachmentLineShowForDownload() {
		return attachmentLineShowForDownload;
	}

	public void setAttachmentLineShowForDownload(
			String attachmentLineShowForDownload) {
		this.attachmentLineShowForDownload = attachmentLineShowForDownload;
	}

	public String getAttachmentLineShowForPlay() {
		return attachmentLineShowForPlay;
	}

	public void setAttachmentLineShowForPlay(String attachmentLineShowForPlay) {
		this.attachmentLineShowForPlay = attachmentLineShowForPlay;
	}

	public String getAjaxOfArticlePrice() {
		return ajaxOfArticlePrice;
	}

	public void setAjaxOfArticlePrice(String ajaxOfArticlePrice) {
		this.ajaxOfArticlePrice = ajaxOfArticlePrice;
	}

	public String getAjaxOfBeforeView() {
		return ajaxOfBeforeView;
	}

	public void setAjaxOfBeforeView(String ajaxOfBeforeView) {
		this.ajaxOfBeforeView = ajaxOfBeforeView;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public String getRootResFolder() {
		return rootResFolder;
	}

	public String getAjaxOfArtPoll() {
		return ajaxOfArtPoll;
	}

	public void setAjaxOfArtPoll(String ajaxOfArtPoll) {
		this.ajaxOfArtPoll = ajaxOfArtPoll;
	}

	public void setRootResFolder(String rootResFolder) {
		this.rootResFolder = rootResFolder;
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
