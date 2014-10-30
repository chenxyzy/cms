package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;

import com.lerx.site.vo.SiteInfo;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.vo.UserInf;
import com.opensymphony.xwork2.ActionSupport;

public class RegFinishVo {
	
	private ActionSupport as;
	private SiteInfo site;
	private IUserGroupDao userGroupDaoImp;
	private IUserDao userDaoImp;
	private HttpServletRequest request;
	private UserInf userInf;
	private ResultEl re;
	private String refererUrl;
	private String rootFolder;
	
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public SiteInfo getSite() {
		return site;
	}
	public void setSite(SiteInfo site) {
		this.site = site;
	}
	public IUserGroupDao getUserGroupDaoImp() {
		return userGroupDaoImp;
	}
	public void setUserGroupDaoImp(IUserGroupDao userGroupDaoImp) {
		this.userGroupDaoImp = userGroupDaoImp;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public UserInf getUserInf() {
		return userInf;
	}
	public void setUserInf(UserInf userInf) {
		this.userInf = userInf;
	}
	public ResultEl getRe() {
		return re;
	}
	public void setRe(ResultEl re) {
		this.re = re;
	}
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	public String getRootFolder() {
		return rootFolder;
	}
	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
}
