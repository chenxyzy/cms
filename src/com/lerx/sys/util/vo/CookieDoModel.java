package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class CookieDoModel {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionSupport actionSupport;
	private String prefix;
	private String encodingCode;
	private String host;
	private String hostSecFile;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
//	private ActionContext ac;
	
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
	public ActionSupport getActionSupport() {
		return actionSupport;
	}
	public void setActionSupport(ActionSupport actionSupport) {
		this.actionSupport = actionSupport;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getEncodingCode() {
		return encodingCode;
	}
	public void setEncodingCode(String encodingCode) {
		this.encodingCode = encodingCode;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getHostSecFile() {
		return hostSecFile;
	}
	public void setHostSecFile(String hostSecFile) {
		this.hostSecFile = hostSecFile;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
//	public ActionContext getAc() {
//		return ac;
//	}
//	public void setAc(ActionContext ac) {
//		this.ac = ac;
//	}
	public IInterconnectionDao getInterconnectionDaoImp() {
		return interconnectionDaoImp;
	}
	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}
	
	
}
