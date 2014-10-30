package com.lerx.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.sys.util.LogWrite;
import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.util.UserGroupUtil;
import com.lerx.user.vo.UserGroup;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport implements
		ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	private UserGroup userGroup;
	private int id;
	private String groupName;
	private List<UserGroup> list;
	private boolean state;
	private IUserGroupDao userGroupDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setUserGroupDaoImp(IUserGroupDao userGroupDaoImp) {
		this.userGroupDaoImp = userGroupDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String add() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()){
			if (userGroupDaoImp.findUserGroupByName(userGroup.getGroupName())){
				this.addActionError(getText("lerx.fail.exists.userGroup"));
				query();
				return INPUT;
			}else{
				userGroup.setState(true);
				userGroup.setUserCount(0);
				userGroupDaoImp.addUserGroup(userGroup);
				LogWrite.logWrite(request, "用户组："+userGroup.getGroupName()+"增加成功。");
				query();
				return SUCCESS;
			}
		}else{
			query();
			return INPUT;
		}

		
		
	}

	public String query() throws Exception{

		list=userGroupDaoImp.findAll();
		
		//ActionContext.getContext().getValueStack().set("userGroupAll",list);
		request.setAttribute("allGroup", list);
		return SUCCESS;
	}
	public String findById() throws Exception{

		this.userGroup=userGroupDaoImp.findUserGroupByID(id);
		query();
		return SUCCESS;
	}
	
	/*
	 * 修复
	 * 本功能主要是用于修复用户组发文选择栏目列表
	 * 资源文件lerx.userGroupNavsSelectStr的值如果为true才会进行。
	 * 目的是栏目过多时减少用户视图中的不需要的栏目列表
	 */
	public String repair() throws Exception {	
		list=userGroupDaoImp.findAll();
		for (UserGroup ag:list){
			ag=UserGroupUtil.repair(this, articleGroupDaoImp, ag);
			userGroupDaoImp.modifyUserGroup(ag);
		}
		query();
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		siteInfDaoImp.query();
		if (checkAdmin()){
//			UserGroup ug=userGroupDaoImp.findUserGroupByID(userGroup.getId());
			userGroup = UserGroupUtil.repair(this, articleGroupDaoImp, userGroup);
//			if (getText("lerx.userGroupNavsSelectStr").trim().equalsIgnoreCase("true")){
//				String powerMask=userGroup.getPowerMask();
//				boolean save=true;
//				String[] powerMaskArray = powerMask.split(",");
//				for (int step = 0; step < powerMaskArray.length; step++) {
//					if (powerMaskArray[step].equals("0") || powerMaskArray[step].equals("a0")) {	//如果是管理员或全部发表权就不处理
//						save=false;
//						break;
//
//					}
//				}
//				if (save){
//					String agSelectStr="",mask,curMask;
//					int agId;
//					for (int step = 0; step < powerMaskArray.length; step++) {
//						mask=StringUtil.filterUnNumber(powerMaskArray[step]);	//过滤非数字字符
//						if (mask!=null && !mask.trim().equals("")){
//							agId=Integer.valueOf(mask.trim());
//							//找到该组上所有上层
//							List<ArticleGroup> list=articleGroupDaoImp.findParentArticleGroupById(agId);
//							for (ArticleGroup ag:list){
//								curMask="{$$"+ag.getId()+"$$}";
//								if (agSelectStr.indexOf(curMask)== -1){
//									if (agSelectStr.trim().equals("")){
//										agSelectStr+=curMask;
//									}else{
//										agSelectStr+=","+curMask;
//									}
//								}
//								
//							}
//							curMask="{$$"+agId+"$$}";
//							if (agSelectStr.trim().equals("")){
//								agSelectStr+=curMask;
//							}else{
//								agSelectStr+=","+curMask;
//							}
//							
//							
//						}
//						
//						
//						
//					}
//					agSelectStr=StringUtil.strReplace(agSelectStr, "$$},{$$", ",");
//					agSelectStr=StringUtil.strReplace(agSelectStr, "{$$", "");
//					agSelectStr=StringUtil.strReplace(agSelectStr, "$$}", "");
//					userGroup.setSiteArticleGroupsSelectCustomStr(agSelectStr);
//					
//				}
//				
//				
//				
//			}
			userGroupDaoImp.modifyUserGroup(userGroup);
			query();
			LogWrite.logWrite(request, "用户组："+userGroup.getGroupName()+"修改成功。");
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String changeState() throws Exception{
		siteInfDaoImp.query();
		if (checkAdmin()){
			userGroupDaoImp.setStateById(id, state);
			userGroup=userGroupDaoImp.findUserGroupByID(id);
			query();
			LogWrite.logWrite(request, "用户组："+userGroup.getGroupName()+"修改状态"+state+"成功。");
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String del() throws Exception{
		siteInfDaoImp.query();
		if (checkAdmin()){
			userGroupDaoImp.delUserGroupById(id);
			query();
			LogWrite.logWrite(request, "用户组id："+id+"删除成功。");
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
		// TODO Auto-generated method stub

	}

}
