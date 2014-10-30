package com.lerx.bbs.vo;

public class BbsForum {
	private Integer id;
	private String forumName;
	private BbsForum rootForum;
	private String description;
	private String displayOrder;
	private String pollFmt;
	private int footerLeft;
	private int footerRight;
	private boolean share;
	private boolean asClass;
	private boolean state;
	private String icoUrl;
	
	private String hostsAllow;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public BbsForum getRootForum() {
		return rootForum;
	}
	public void setRootForum(BbsForum rootForum) {
		this.rootForum = rootForum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
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
	public String getHostsAllow() {
		return hostsAllow;
	}
	public void setHostsAllow(String hostsAllow) {
		this.hostsAllow = hostsAllow;
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
	public String getIcoUrl() {
		return icoUrl;
	}
	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}
	public String getPollFmt() {
		return pollFmt;
	}
	public void setPollFmt(String pollFmt) {
		this.pollFmt = pollFmt;
	}
}
