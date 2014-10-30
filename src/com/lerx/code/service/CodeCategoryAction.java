package com.lerx.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.code.dao.ICodeCategoryDao;
import com.lerx.code.vo.CodeCategory;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CodeCategoryAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CodeCategory cc;
	private int id;
	private ICodeCategoryDao codeCategoryDaoImp;
	private HttpServletRequest request;
	
	public CodeCategory getCc() {
		return cc;
	}

	public void setCc(CodeCategory cc) {
		this.cc = cc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public void setCodeCategoryDaoImp(ICodeCategoryDao codeCategoryDaoImp) {
		this.codeCategoryDaoImp = codeCategoryDaoImp;
	}
	
	private void queryAll(){
		List<CodeCategory> list = codeCategoryDaoImp.queryAll();
		request.setAttribute("categoryAll", list);
		ActionContext.getContext().getValueStack().set("categoryAll", list);
	}
	
	public String query(){
		if (checkAdmin()){
			queryAll();
//			ActionContext.getContext().getValueStack().set("categoryAll", list);
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	public String findById(){
		if (checkAdmin()){
			CodeCategory cc=codeCategoryDaoImp.findById(id);
			request.setAttribute("category", cc);
			ActionContext.getContext().getValueStack().set("category", cc);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	
	public String add(){
		if (checkAdmin()){
			codeCategoryDaoImp.add(cc);
			queryAll();
			LogWrite.logWrite(request, "info:后台增加代码类别“"+cc.getName()+"”，id号："+cc.getId());
			return SUCCESS;
		}else {
			queryAll();
			return INPUT;
		}
		
		
	}
	
	public String  del(){
		if (checkAdmin()){
			codeCategoryDaoImp.delById(id);
			queryAll();
			LogWrite.logWrite(request, "info:后台删除代码类别，id号："+id);
			return SUCCESS;
		}else {
			queryAll();
			return INPUT;
		}
	}
	
	public String modify(){
		if (checkAdmin()){
			codeCategoryDaoImp.modify(cc);
			queryAll();
			LogWrite.logWrite(request, "info:后台修改代码类别，id号："+cc.getId());
			return SUCCESS;
		}else {
			queryAll();
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
