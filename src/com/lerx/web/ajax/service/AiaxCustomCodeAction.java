package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.code.dao.ICustomCodeDao;
import com.lerx.code.vo.CustomCode;
import com.opensymphony.xwork2.ActionSupport;

public class AiaxCustomCodeAction extends ActionSupport implements
 ServletResponseAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private int gid;
	private int mode;
	private long id;
	private ICustomCodeDao customCodeDaoImp;
	

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void findById() throws IOException{
		String html;
		CustomCode code = customCodeDaoImp.findById(id);
		html=code.getCode();
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(html);
	}
	
	public void find() throws IOException{
		String html;
		/*
		 * mode 0 按顺序
		 * 		1 随机
		 */
		CustomCode code = customCodeDaoImp.find(gid, mode);
		if (code==null){
			html="";
		}else{
			html=code.getCode();
		}
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(html);
	}

	public void setCustomCodeDaoImp(ICustomCodeDao customCodeDaoImp) {
		this.customCodeDaoImp = customCodeDaoImp;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

}
