package com.lerx.sys.util.vo;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> session;
    protected ServletContext servletContext;
    protected HttpServletRequest request;
    
    public BaseAction(){
        servletContext = ServletActionContext.getServletContext();
        request = ServletActionContext.getRequest();
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
        
    }


}
