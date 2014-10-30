package com.lerx.web.vo;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.vo.FormatElements;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAgExeElement {
	
	private String htmlTemplate;
	private ArticleGroup ag;
	private boolean show;
	private FormatElements fel;
	private IArticleThreadDao articleThreadDaoImp;
	private SiteStyle curSiteStyle;
	private ActionSupport as;
	private int speedMod;
	
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	public ArticleGroup getAg() {
		return ag;
	}
	public void setAg(ArticleGroup ag) {
		this.ag = ag;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public FormatElements getFel() {
		return fel;
	}
	public void setFel(FormatElements fel) {
		this.fel = fel;
	}
	public IArticleThreadDao getArticleThreadDaoImp() {
		return articleThreadDaoImp;
	}
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}
	public SiteStyle getCurSiteStyle() {
		return curSiteStyle;
	}
	public void setCurSiteStyle(SiteStyle curSiteStyle) {
		this.curSiteStyle = curSiteStyle;
	}
	public int getSpeedMod() {
		return speedMod;
	}
	public void setSpeedMod(int speedMod) {
		this.speedMod = speedMod;
	}
	
}
