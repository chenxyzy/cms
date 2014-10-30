package com.lerx.article.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ArticleGroupAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	private List<ArticleGroup> list;
	private ArticleGroup articleGroup;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private int parentGroupID;
	private int id;
	private int styleID;
	private int sid;
	private int tid;
	private ISiteInfoDao siteInfDaoImp;
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentGroupID() {
		return parentGroupID;
	}

	public void setParentGroupID(int parentGroupID) {
		this.parentGroupID = parentGroupID;
	}

	public List<ArticleGroup> getList() {
		return list;
	}

	public void setList(List<ArticleGroup> list) {
		this.list = list;
	}

	public ArticleGroup getArticleGroup() {
		return articleGroup;
	}

	public void setArticleGroup(ArticleGroup articleGroup) {
		this.articleGroup = articleGroup;
	}

	public String add(){
		siteInfDaoImp.query();
//		BeanFactory bf = new ClassPathXmlApplicationContext(
//		"applicationContext.xml");
//		IArticleGroupDao articleGroupDao = (IArticleGroupDao) bf.getBean("articleGroupDaoImp");
		if (checkAdmin() && articleGroup.getGroupName()!=null && !articleGroup.getGroupName().trim().equals("")){
			articleGroup.setParentGroup(articleGroupDaoImp.findById(parentGroupID));
			articleGroup.setGroupName(articleGroup.getGroupName().trim());
			articleGroup.setChanged(true);
			articleGroupDaoImp.add(articleGroup);
			LogWrite.logWrite(request, "后台增加文章组 "+articleGroup.getGroupName()+" 成功。 ");
			findAll();
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String del(){
		siteInfDaoImp.query();
		if (checkAdmin()){
			if (!articleGroupDaoImp.delById(id)){
				request.setAttribute("resultAltStr", getText("lerx.fail.del.exists.parent"));
				
			}else{
				LogWrite.logWrite(request, "后台删除文章组 id号：<"+id+"> 成功。 ");
			}
			findAll();
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String findAll(){
		request.setAttribute("groupAll", articleGroupDaoImp.findAllModel(getText("lerx.treePrefixHead"),getText("lerx.treePrefixBody")));
		return SUCCESS;
	}
	
	public String findAllByParent(){
		request.setAttribute("subGroupAll", articleGroupDaoImp.findByParentId(parentGroupID));
		return SUCCESS;
		
	}

	public String swap(){
		ArticleGroup s=articleGroupDaoImp.findById(sid);
		ArticleGroup t=articleGroupDaoImp.findById(tid);
		articleGroupDaoImp.swap(s, t);
		LogWrite.logWrite(request, "后台修改--交换文章组显示序号sid号：<"+s.getId()+"> tid号：<"+t.getId()+"> 成功。 ");
		return SUCCESS;
	}
	public String findById(){
		ActionContext.getContext().getValueStack().set("currentGroup",articleGroupDaoImp.findById(id));
//		request.setAttribute("currentGroup",articleGroupDaoImp.findById(id));
		return SUCCESS;
	}
	public String move(){
		siteInfDaoImp.query();
		if (checkAdmin() && sid>0 && tid>0 && sid!=tid){
			long count=articleThreadDaoImp.move(sid, tid);
			if (count>0){
				ArticleGroup ag=articleGroupDaoImp.findById(sid);
				ag.setChanged(true);
				articleGroupDaoImp.changed(ag);
				ag=articleGroupDaoImp.findById(tid);
				ag.setChanged(true);
				articleGroupDaoImp.changed(ag);
				request.setAttribute("mes",getText("lerx.msg.data.move.num.success")+count);
				
				LogWrite.logWrite(request, "后台修改--在文章组中移动文章sid号：<"+sid+"> tid号：<"+tid+"> 成功。 ");
			}else{
				request.setAttribute("mes",getText("lerx.fail.data.move"));
				return ERROR;
			}
//			request.setAttribute("mesdd","asdfasgasdg");
//			ActionContext.getContext().getValueStack().set("mesdd","asdfasgasdg");
			return SUCCESS;
		}else{
			request.setAttribute("mes",getText("lerx.fail.data.move"));
//			request.setAttribute("mesdd","asdfasgasdg");
//			ActionContext.getContext().getValueStack().set("mesdd","asdfasgasdg");
			return ERROR;
		}
//		findAll();
		
	}
	public String modify(){
		siteInfDaoImp.query();
		if (checkAdmin()){
//			System.out.println("发文模式："+articleGroup.getArticlePassMode());
			ArticleGroup g = articleGroupDaoImp.findById(articleGroup.getId());
			if ((g.getParentGroup()==null && parentGroupID>0)  || (g.getParentGroup()!=null && g.getParentGroup().getId()!=parentGroupID)){
				articleGroup.setGroupName(articleGroup.getGroupName().trim());
				articleGroup.setChanged(true);
				articleGroupDaoImp.modifyAndMove(articleGroup, parentGroupID);
				LogWrite.logWrite(request, "后台修改--移动文章组 id号：<"+articleGroup.getId()+"> 成功。 ");
			}else{
				articleGroup.setChanged(true);
				articleGroup.setGroupName(articleGroup.getGroupName().trim());
				articleGroup.setParentGroup(articleGroupDaoImp.findById(parentGroupID));
				articleGroup.setFooterLeft(g.getFooterLeft());
				articleGroup.setFooterRight(g.getFooterRight());
				articleGroup.setDisplayOrder(g.getDisplayOrder());
				if (articleGroup.getPollFmt()!=null){
					articleGroup.setPollFmt(articleGroup.getPollFmt().trim());
				}
//				articleGroup.setRefuseStaticHtml(g.isRefuseStaticHtml());
//				System.out.println("测试外键："+articleGroup.getStyle().getId());
				if (articleGroup.getStyle().getId()==0){
					articleGroup.setStyle(null);
				}
				if (articleGroupDaoImp.modify(articleGroup)){
					articleGroupDaoImp.changed(articleGroup);
					LogWrite.logWrite(request, "后台修改文章组 id号：<"+articleGroup.getId()+"> 成功。 ");
					request.setAttribute("resultAltStr", getText("lerx.success.all"));
				}
			}
			
			if (styleID==0){
				articleGroup.setStyle(null);
			}else{
				articleGroup.setStyle(siteStyleDaoImp.findStyleById(styleID));
			}
			
			findAll();
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;

	}

}
