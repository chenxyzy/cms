package com.lerx.web.vo;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

public class BbsThemeCheckUtil {
	private User user;
	private BbsTheme theme;
	private IBbsForumDao bbsForumDaoImp;
	private IBbsBMDao bbsBMDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private String str;
	private String functionAreaCode;
	private String editAreaCode;
	private String quoteButtomCode;
	private BbsInfo bi;
	private ActionSupport as;
	private String icoFolder;
	
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public IBbsStyleDao getBbsStyleDaoImp() {
		return bbsStyleDaoImp;
	}
	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}
	public BbsInfo getBi() {
		return bi;
	}
	public void setBi(BbsInfo bi) {
		this.bi = bi;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BbsTheme getTheme() {
		return theme;
	}
	public void setTheme(BbsTheme theme) {
		this.theme = theme;
	}
	public IBbsForumDao getBbsForumDaoImp() {
		return bbsForumDaoImp;
	}
	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}
	public IBbsBMDao getBbsBMDaoImp() {
		return bbsBMDaoImp;
	}
	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getFunctionAreaCode() {
		return functionAreaCode;
	}
	public void setFunctionAreaCode(String functionAreaCode) {
		this.functionAreaCode = functionAreaCode;
	}
	public String getEditAreaCode() {
		return editAreaCode;
	}
	public void setEditAreaCode(String editAreaCode) {
		this.editAreaCode = editAreaCode;
	}
	public String getQuoteButtomCode() {
		return quoteButtomCode;
	}
	public void setQuoteButtomCode(String quoteButtomCode) {
		this.quoteButtomCode = quoteButtomCode;
	}
	public String getIcoFolder() {
		return icoFolder;
	}
	public void setIcoFolder(String icoFolder) {
		this.icoFolder = icoFolder;
	}
	
	
}
