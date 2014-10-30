package com.lerx.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.vo.ScoreScheme;
import com.lerx.site.dao.ISiteInfoDao;
import com.opensymphony.xwork2.ActionSupport;

public class ScoreSchemeAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ScoreScheme scheme;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private HttpServletRequest request;
	private List<ScoreScheme> list;
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
	}
	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ScoreScheme getScheme() {
		return scheme;
	}
	public void setScheme(ScoreScheme scheme) {
		this.scheme = scheme;
	}
	
	public String use(){
		if (checkAdmin()){
			scoreSchemeDaoImp.setState(id);
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String add(){
//		siteInfDaoImp.query();
		if (checkAdmin()){
			
			scoreSchemeDaoImp.addScoreScheme(scheme);
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String del(){
		if (checkAdmin()){
			scoreSchemeDaoImp.delScoreSchemeById(id);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String queryAll() {
		
		if (checkAdmin()){
			list=scoreSchemeDaoImp.findAll();
			request.setAttribute("schemeAll", list);
//			ActionContext.getContext().getValueStack().set("schemeAll", list);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String find(){
		this.scheme=scoreSchemeDaoImp.findScoreSchemeById(id);
//		ActionContext.getContext().getValueStack()
//		.set("scheme", scheme);
		request.setAttribute("scheme", scheme);
//		System.out.println(scheme.getSchemeName());
		return SUCCESS;
	}
	
	public String modify(){
		if (checkAdmin()){
			ScoreScheme schemeDb=scoreSchemeDaoImp.findScoreSchemeById(id);
			schemeDb.setValueOfDownload(scheme.getValueOfDownload());
			schemeDb.setValueOfMin(scheme.getValueOfMin());
			schemeDb.setValueOfNewTheme(scheme.getValueOfNewTheme());
			schemeDb.setValueOfReply(scheme.getValueOfReply());
			schemeDb.setValueOfSoul(scheme.getValueOfSoul());
			schemeDb.setValueOfUpload(scheme.getValueOfUpload());
			scoreSchemeDaoImp.modifyScoreScheme(schemeDb);
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
}
