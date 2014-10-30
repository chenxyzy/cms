package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.opensymphony.xwork2.ActionSupport;

public class ArticlePostAction extends ActionSupport implements ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String len;
	private ArticleThread thread;
	private IArticleGroupDao articleGroupDaoImp;
	private HttpServletResponse response;
	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public ArticleThread getThread() {
		return thread;
	}

	public void setThread(ArticleThread thread) {
		this.thread = thread;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void chk() throws IOException  {
		int cuteMod;
		int length;
		
		ArticleGroup g=articleGroupDaoImp.findById(thread.getArticleGroup().getId());
		if (len.trim().equalsIgnoreCase("index")) {
			cuteMod = 1;
		} else if (len.trim().equalsIgnoreCase("nav")) {
			cuteMod = 2;
		} else {
			cuteMod = 0;
		}

		switch (cuteMod) {
		case 1:
			if (g!=null){
				length=g.getLengthShowOnIndex();
			}else{
				length=0;
			}
			break;
		case 2:
			if (g!=null){
				length=g.getLengthShowOnParent();
			}else{
				length=0;
			}
			break;
		default:
			length=Integer.valueOf(len);
			break;

		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		//System.out.println("length:"+length);
		response.getWriter().write(""+length);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

}
