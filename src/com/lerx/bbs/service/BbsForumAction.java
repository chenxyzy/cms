package com.lerx.bbs.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class BbsForumAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List<BbsForum> list;
	private BbsForum forum;
	private int parentForumID;
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

	private IBbsForumDao bbsForumDaoImp;
	
	

	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
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


	public int getParentForumID() {
		return parentForumID;
	}

	public void setParentForumID(int parentForumID) {
		this.parentForumID = parentForumID;
	}

	public List<BbsForum> getList() {
		return list;
	}

	public void setList(List<BbsForum> list) {
		this.list = list;
	}


	public BbsForum getForum() {
		return forum;
	}

	public void setForum(BbsForum forum) {
		this.forum = forum;
	}

	public String add(){
		siteInfDaoImp.query();
		if (checkAdmin()){
			forum.setRootForum(bbsForumDaoImp.findBbsForumById(parentForumID));
			forum.setForumName(forum.getForumName().trim());
			bbsForumDaoImp.addForum(forum);
			LogWrite.logWrite(request, "后台增加论坛版块 "+forum.getForumName()+" 成功。 ");
			findAll();
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String del(){
		siteInfDaoImp.query();
		if (checkAdmin()){
			if (!bbsForumDaoImp.delForumById(id)){
				request.setAttribute("resultAltStr", getText("lerx.fail.del.exists.parent"));
				
			}else{
				LogWrite.logWrite(request, "后台删除论坛版块 id号：<"+id+"> 成功。 ");
			}
			findAll();
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String findAll(){
//		System.out.println("测试点");
		request.setAttribute("forumAll", bbsForumDaoImp.findAllBbsForumModel(getText("lerx.treePrefixHead"),getText("lerx.treePrefixBody")));
		return SUCCESS;
	}
	
	public String findAllByParent(){
		request.setAttribute("subForumAll", bbsForumDaoImp.findBbsForumByParentId(parentForumID));
		return SUCCESS;
		
	}

	public String swap(){
		BbsForum s=bbsForumDaoImp.findBbsForumById(sid);
		BbsForum t=bbsForumDaoImp.findBbsForumById(tid);
		bbsForumDaoImp.swapBbsForum(s, t);
		LogWrite.logWrite(request, "后台修改--交换文章组sid号：<"+s.getId()+"> tid号：<"+t.getId()+"> 成功。 ");
		return SUCCESS;
	}
	public String findById(){
		ActionContext.getContext().getValueStack().set("currentForum",bbsForumDaoImp.findBbsForumById(id));
//		request.setAttribute("currentGroup",bbsForumDaoImp.findBbsForumById(id));
		return SUCCESS;
	}
	
	public String modify(){
		siteInfDaoImp.query();
		if (checkAdmin()){
//			System.out.println("发文模式："+BbsForum.getArticlePassMode());
			BbsForum g = bbsForumDaoImp.findBbsForumById(forum.getId());
			if ((g.getRootForum()==null && parentForumID>0)  || (g.getRootForum()!=null && g.getRootForum().getId()!=parentForumID)){
				forum.setForumName(forum.getForumName().trim());
				bbsForumDaoImp.modifyAndMoveBbsForum(forum, parentForumID);
				LogWrite.logWrite(request, "后台修改--移动文章组 id号：<"+forum.getId()+"> 成功。 ");
			}else{
				forum.setForumName(forum.getForumName().trim());
				forum.setRootForum(bbsForumDaoImp.findBbsForumById(parentForumID));
				forum.setFooterLeft(g.getFooterLeft());
				forum.setFooterRight(g.getFooterRight());
				forum.setDisplayOrder(g.getDisplayOrder());
				
				if (forum.getPollFmt()!=null){
					forum.setPollFmt(forum.getPollFmt().trim());
				}
				
//				BbsForum.setRefuseStaticHtml(g.isRefuseStaticHtml());
//				System.out.println("测试外键："+BbsForum.getStyle().getId());
				if (bbsForumDaoImp.modifyForum(forum)){
					LogWrite.logWrite(request, "后台修改文章组 id号：<"+forum.getId()+"> 成功。 ");
//					request.setAttribute("resultAltStr", getText("lerx.success.all"));
				}
			}
			
			findAll();
			return SUCCESS;
		}else{
			findAll();
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

