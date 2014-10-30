package com.lerx.article.vo;

import com.lerx.style.site.vo.SiteStyle;

public class ArticleGroup {
	
	private Integer id;
	private String groupName;
	private ArticleGroup parentGroup;
	private String displayOrder;
	private int footerLeft;
	private int footerRight;
	private boolean share;
	private boolean asClass;
	private boolean state;
	private boolean changed;
	private String pollFmt;
	private boolean showAll; //是否使用精简标题
	private int price;		//是否使用价格
	private String iconUrl;
	private String jumpUrl;
	private String hostsAllow;
	private String staticHtmlFolder;
	private boolean refuseStaticHtml;

	private boolean showOnIndex;
	private int lengthShowOnIndex;
	private int numberShowOnIndex;
	private boolean soulOnIndex;
	private String formatOnIndex;
	
	private boolean showOnParent;
	private int lengthShowOnParent;
	private int numberShowOnParent;
	private boolean soulOnParent;
	private String formatOnParent;
	
	private SiteStyle style;
	private boolean compulsionDocStyle;
	private int articlePassMode;
	private int numberAppearRestrict;
	private int numberList;
	private int commentMode;
	
	private String privateHtml;
	private long views;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public ArticleGroup getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(ArticleGroup parentGroup) {
		this.parentGroup = parentGroup;
	}
	public int getFooterLeft() {
		return footerLeft;
	}
	public void setFooterLeft(int footerLeft) {
		this.footerLeft = footerLeft;
	}
	public int getFooterRight() {
		return footerRight;
	}
	public void setFooterRight(int footerRight) {
		this.footerRight = footerRight;
	}
	public boolean isShare() {
		return share;
	}
	public void setShare(boolean share) {
		this.share = share;
	}
	public boolean isAsClass() {
		return asClass;
	}
	public void setAsClass(boolean asClass) {
		this.asClass = asClass;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isShowAll() {
		return showAll;
	}
	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getJumpUrl() {
		return jumpUrl;
	}
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	public String getStaticHtmlFolder() {
		return staticHtmlFolder;
	}
	public void setStaticHtmlFolder(String staticHtmlFolder) {
		this.staticHtmlFolder = staticHtmlFolder;
	}
	public String getHostsAllow() {
		return hostsAllow;
	}
	public void setHostsAllow(String hostsAllow) {
		this.hostsAllow = hostsAllow;
	}
	public boolean isShowOnIndex() {
		return showOnIndex;
	}
	public void setShowOnIndex(boolean showOnIndex) {
		this.showOnIndex = showOnIndex;
	}
	public int getLengthShowOnIndex() {
		return lengthShowOnIndex;
	}
	public void setLengthShowOnIndex(int lengthShowOnIndex) {
		this.lengthShowOnIndex = lengthShowOnIndex;
	}
	public int getNumberShowOnIndex() {
		return numberShowOnIndex;
	}
	public void setNumberShowOnIndex(int numberShowOnIndex) {
		this.numberShowOnIndex = numberShowOnIndex;
	}
	public String getFormatOnIndex() {
		return formatOnIndex;
	}
	public void setFormatOnIndex(String formatOnIndex) {
		this.formatOnIndex = formatOnIndex;
	}
	public boolean isShowOnParent() {
		return showOnParent;
	}
	public void setShowOnParent(boolean showOnParent) {
		this.showOnParent = showOnParent;
	}
	public int getLengthShowOnParent() {
		return lengthShowOnParent;
	}
	public void setLengthShowOnParent(int lengthShowOnParent) {
		this.lengthShowOnParent = lengthShowOnParent;
	}
	public int getNumberShowOnParent() {
		return numberShowOnParent;
	}
	public void setNumberShowOnParent(int numberShowOnParent) {
		this.numberShowOnParent = numberShowOnParent;
	}
	public String getFormatOnParent() {
		return formatOnParent;
	}
	public void setFormatOnParent(String formatOnParent) {
		this.formatOnParent = formatOnParent;
	}
	public SiteStyle getStyle() {
		return style;
	}
	public void setStyle(SiteStyle style) {
		this.style = style;
	}
	public boolean isCompulsionDocStyle() {
		return compulsionDocStyle;
	}
	public void setCompulsionDocStyle(boolean compulsionDocStyle) {
		this.compulsionDocStyle = compulsionDocStyle;
	}
	public int getArticlePassMode() {
		return articlePassMode;
	}
	public void setArticlePassMode(int articlePassMode) {
		this.articlePassMode = articlePassMode;
	}
	public int getNumberAppearRestrict() {
		return numberAppearRestrict;
	}
	public void setNumberAppearRestrict(int numberAppearRestrict) {
		this.numberAppearRestrict = numberAppearRestrict;
	}
	public int getNumberList() {
		return numberList;
	}
	public void setNumberList(int numberList) {
		this.numberList = numberList;
	}
	public int getCommentMode() {
		return commentMode;
	}
	public void setCommentMode(int commentMode) {
		this.commentMode = commentMode;
	}
	public String getPrivateHtml() {
		return privateHtml;
	}
	public void setPrivateHtml(String privateHtml) {
		this.privateHtml = privateHtml;
	}
	public boolean isRefuseStaticHtml() {
		return refuseStaticHtml;
	}
	public void setRefuseStaticHtml(boolean refuseStaticHtml) {
		this.refuseStaticHtml = refuseStaticHtml;
	}
	public boolean isSoulOnIndex() {
		return soulOnIndex;
	}
	public void setSoulOnIndex(boolean soulOnIndex) {
		this.soulOnIndex = soulOnIndex;
	}
	public boolean isSoulOnParent() {
		return soulOnParent;
	}
	public void setSoulOnParent(boolean soulOnParent) {
		this.soulOnParent = soulOnParent;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public String getPollFmt() {
		return pollFmt;
	}
	public void setPollFmt(String pollFmt) {
		this.pollFmt = pollFmt;
	}
	
	
}
