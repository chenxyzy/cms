package com.lerx.web.vo;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.opensymphony.xwork2.ActionSupport;

public class SiteWebNavEl {
	private String htmlTemplate;
	private ActionSupport as;
	private IArticleGroupDao articleGroupDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private int gid;
	
	
	
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public IArticleGroupDao getArticleGroupDaoImp() {
		return articleGroupDaoImp;
	}
	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}
	public IArticleThreadDao getArticleThreadDaoImp() {
		return articleThreadDaoImp;
	}
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	
}
