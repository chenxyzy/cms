package com.lerx.sys.obj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

public class UASObj extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private User user;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	private IUserDao userDaoImp;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	

}
