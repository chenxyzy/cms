package com.lerx.bbs.util.vo;

import com.lerx.bbs.vo.BbsForum;

public class BbsForumShowModel {
	private BbsForum forum;
	private String prefixStr;
	public BbsForum getForum() {
		return forum;
	}
	public void setForum(BbsForum forum) {
		this.forum = forum;
	}
	public String getPrefixStr() {
		return prefixStr;
	}
	public void setPrefixStr(String prefixStr) {
		this.prefixStr = prefixStr;
	}
	
}
