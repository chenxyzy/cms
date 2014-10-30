package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.vo.ArticleGroup;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleGroupAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private IArticleGroupDao articleGroupDaoImp;
	
	
	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	private int gid;
	
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public void lenOnIndex() throws IOException{
		ArticleGroup g=articleGroupDaoImp.findById(gid);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(""+g.getLengthShowOnIndex());
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
	}

}
