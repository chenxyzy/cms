package com.lerx.user.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class ChkUtilVo {
	
	private IInterconnectionDao interconnectionDaoImp;
	private IUserDao userDaoImp;
	private ArticleGroup ag;
	private UserCookie uc;
	private int mode;
	private boolean pwdMD5ToLowerCase;
	private ActionSupport as;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private QaNav qn;
	
	public QaNav getQn() {
		return qn;
	}
	public void setQn(QaNav qn) {
		this.qn = qn;
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
	public ArticleGroup getAg() {
		return ag;
	}
	public void setAg(ArticleGroup ag) {
		this.ag = ag;
	}
	public UserCookie getUc() {
		return uc;
	}
	public void setUc(UserCookie uc) {
		this.uc = uc;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public boolean isPwdMD5ToLowerCase() {
		return pwdMD5ToLowerCase;
	}
	public void setPwdMD5ToLowerCase(boolean pwdMD5ToLowerCase) {
		this.pwdMD5ToLowerCase = pwdMD5ToLowerCase;
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
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
