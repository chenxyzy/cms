package com.lerx.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.code.dao.ICustomCodeDao;
import com.lerx.code.vo.CustomCode;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomCodeAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private int gid;
	private long id;
	private ICustomCodeDao customCodeDaoImp;
	private CustomCode code;
	
	
	public CustomCode getCode() {
		return code;
	}

	public void setCode(CustomCode code) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCustomCodeDaoImp(ICustomCodeDao customCodeDaoImp) {
		this.customCodeDaoImp = customCodeDaoImp;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}
	
	
//	public String queryById(){
//		System.out.println("45678");
//		if (checkAdmin()){
//			System.out.println("4567668");
//			CustomCode code=customCodeDaoImp.findById(id);
//			if (code!=null){
//				System.out.println("11111");
//				System.out.println("code.getName():"+code.getName());
//				request.setAttribute("code", code);
//				ActionContext.getContext().getValueStack().set("code", code);
//			}else{
//				System.out.println("code.getName():null");
//				return INPUT;
//			}
//			
//		}else{
//			System.out.println("code.getName():null");
//			return INPUT;
//		}
//		
//		return SUCCESS;
//	}
	
	public String modify(){
		if (checkAdmin()){
			customCodeDaoImp.modify(code);
			queryAll();
			ActionContext.getContext().getValueStack().set("gid", code.getCc().getId());
			LogWrite.logWrite(request, "info:后台修改用户代码，id号："+code.getId());
			return SUCCESS;
		}else{
			queryAll();
			ActionContext.getContext().getValueStack().set("gid", code.getCc().getId());
			return INPUT;
		}
		
	}
	
	public String findById(){
		if (checkAdmin()){
			CustomCode code=customCodeDaoImp.findById(id);
			if (code!=null){
				request.setAttribute("code", code);
				ActionContext.getContext().getValueStack().set("code", code);
			}else{
				return INPUT;
			}
			
		}else{
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	public String del(){
		if (checkAdmin()){
			
			customCodeDaoImp.delById(id);
			LogWrite.logWrite(request, "info:后台删除用户代码，id号："+id);
		}
		queryAll();
		return SUCCESS;
	}
	
	public String add(){
		if (checkAdmin()){
			code.setState(true);
			code.setCurrent(false);
			Long codeId=customCodeDaoImp.add(code);
			queryAll();
			request.setAttribute("gid", code.getCc().getId());
			ActionContext.getContext().getValueStack().set("gid", code.getCc().getId());
			if (codeId>0){
				LogWrite.logWrite(request, "info:后台增加用户代码“"+code.getName()+"”，id号："+codeId);
				return SUCCESS;
			}else{
				return INPUT;
			}
			
		}else{
			queryAll();
			request.setAttribute("gid", code.getCc().getId());
			ActionContext.getContext().getValueStack().set("gid", code.getCc().getId());
			return INPUT;
		}
		
	}

	
	public String query(){
		if (checkAdmin()){
			queryAll();
//			System.out.println("gid:"+gid);
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	private void queryAll(){
		List<CustomCode> list=customCodeDaoImp.findByType(gid);
		request.setAttribute("customCodeAll", list);
		request.setAttribute("gid", gid);
		ActionContext.getContext().getValueStack().set("gid", gid);
		ActionContext.getContext().getValueStack().set("customCodeAll", list);
		
	}
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

}
