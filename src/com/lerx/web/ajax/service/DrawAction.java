package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.draw.dao.IDrawDao;
import com.lerx.draw.vo.Draw;
import com.lerx.sys.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DrawAction extends ActionSupport implements
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private int gid;
	private String password;
	private IDrawDao drawDaoImp;
	
	public int getGid() {
		return gid;
	}


	public void setGid(int gid) {
		this.gid = gid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setDrawDaoImp(IDrawDao drawDaoImp) {
		this.drawDaoImp = drawDaoImp;
	}


	public void checkPws() throws IOException  {
//		System.out.println(password);
	
		boolean con=true;
		Draw draw=drawDaoImp.findById(gid);
		if (draw.getPassword()!=null && !draw.getPassword().trim().equals("")){
			if (password==null || !StringUtil.md5(password).equals(draw.getPassword())){
				con=false;
				
			}
		}
		String returnStr;
		if (con){
			returnStr="true";
		}else{
			returnStr="false";
		}
//		returnStr="true";
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(returnStr);
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}


}
