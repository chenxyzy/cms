package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoginCheckEl {
	
	private ActionSupport as;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private UserCookie uc;
	private CookieDoModel cdm;
	private boolean logined;
	
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
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	public IInterconnectionDao getInterconnectionDaoImp() {
		return interconnectionDaoImp;
	}
	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}
	public UserCookie getUc() {
		return uc;
	}
	public void setUc(UserCookie uc) {
		this.uc = uc;
	}
	public CookieDoModel getCdm() {
		return cdm;
	}
	public void setCdm(CookieDoModel cdm) {
		this.cdm = cdm;
	}
	public boolean isLogined() {
		return logined;
	}
	public void setLogined(boolean logined) {
		this.logined = logined;
	}
	
	
}
