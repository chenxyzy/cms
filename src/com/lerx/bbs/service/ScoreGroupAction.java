package com.lerx.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.vo.ScoreGroup;
import com.lerx.bbs.vo.ScoreScheme;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScoreGroupAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int sid;
	private int cid;
	private ScoreGroup group;
	private IScoreGroupDao scoreGroupDaoImp;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private HttpServletRequest request;
	private List<ScoreGroup> list;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public ScoreGroup getGroup() {
		return group;
	}

	public void setGroup(ScoreGroup group) {
		this.group = group;
	}

	public void setScoreGroupDaoImp(IScoreGroupDao scoreGroupDaoImp) {
		this.scoreGroupDaoImp = scoreGroupDaoImp;
	}

	
	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}

	public String add(){
		
		if (checkAdmin()){
			ScoreScheme scheme=scoreSchemeDaoImp.findScoreSchemeById(sid);
			group.setScheme(scheme);
			scoreGroupDaoImp.addScoreGroup(group);
			request.setAttribute("id", sid);
			request.setAttribute("sid", sid);
			ActionContext.getContext().getValueStack().set("sid", sid);
			ActionContext.getContext().getValueStack().set("gid", group.getId());
//			ServletActionContext.getRequest().setAttribute("gid", group.getId());
			return SUCCESS;
		}else{
			return INPUT;
		}
		
		
	}
	
	public String del(){
		if (checkAdmin()){
			ScoreGroup g=scoreGroupDaoImp.findScoreGroupByID(id);
			sid=g.getScheme().getId();
			scoreGroupDaoImp.delScoreGroupById(id);
//			System.out.println("sid:"+sid);
			request.setAttribute("sid",sid);
			request.setAttribute("id", sid);
			ActionContext.getContext().getValueStack().set("sid", sid);
			ActionContext.getContext().getValueStack().set("gid", g.getId());
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String modify(){
		return SUCCESS;
	}
	
	public String queryAll(){
		if (checkAdmin()){
			if (id==0){
				id=cid;
			}
			list=scoreGroupDaoImp.findGroupBySchemeId(id);
			request.setAttribute("id", id);
			request.setAttribute("sid", id);
			request.setAttribute("groupAll", list);
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
