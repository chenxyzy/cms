package com.lerx.user.vo;

import javax.servlet.http.HttpServletRequest;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class TransferUserUtil {
	private User user;
	private IUserDao userDaoImp;
	private ActionSupport as;
	private boolean w;
	private int pwsMode;
	private boolean ignorePws;
	private boolean staCheck;
	
	private HttpServletRequest request;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public boolean isW() {
		return w;
	}
	public void setW(boolean w) {
		this.w = w;
	}
	public int getPwsMode() {
		return pwsMode;
	}
	public void setPwsMode(int pwsMode) {
		this.pwsMode = pwsMode;
	}
	public boolean isIgnorePws() {
		return ignorePws;
	}
	public void setIgnorePws(boolean ignorePws) {
		this.ignorePws = ignorePws;
	}
	public boolean isStaCheck() {
		return staCheck;
	}
	public void setStaCheck(boolean staCheck) {
		this.staCheck = staCheck;
	}
}
