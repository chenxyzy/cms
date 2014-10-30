package com.lerx.sys.service;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.lerx.admin.util.AdminUtil;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.vo.ExternalHostCharset;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExternalHostCharsetAction extends ActionSupport implements
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private ExternalHostCharset ehc;
	private HttpServletRequest request;
	private IExternalHostCharsetDao externalHostCharsetDaoImp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ExternalHostCharset getEhc() {
		return ehc;
	}
	public void setEhc(ExternalHostCharset ehc) {
		this.ehc = ehc;
	}
	
	public void setExternalHostCharsetDaoImp(
			IExternalHostCharsetDao externalHostCharsetDaoImp) {
		this.externalHostCharsetDaoImp = externalHostCharsetDaoImp;
	}
	
	public String add(){
		int port=ehc.getPort();
		if ( port==0){
			port=80;
		}
		ehc.setPort(port);
		if (checkAdmin() && externalHostCharsetDaoImp.findByHostAndPortAndType(ehc.getHost(),ehc.getPort(), ehc.getType())==null){
			if (externalHostCharsetDaoImp.add(ehc)>0){
				queryAll();
				return SUCCESS;
			}else{
				queryAll();
				return INPUT;
			}
			
		}else{
			queryAll();
			return INPUT;
		}
		
	}
	
	public String modify(){
		int port=ehc.getPort();
		if ( port==0){
			port=80;
		}
		ehc.setPort(port);
		if (checkAdmin()){
			ExternalHostCharset ehcdb=externalHostCharsetDaoImp.findByHostAndPortAndTypeAndOtherId(ehc.getHost(),ehc.getPort(), ehc.getType(), ehc.getId());
			if (ehcdb==null){
				externalHostCharsetDaoImp.modify(ehc);
					queryAll();
					return SUCCESS;
			}else{
				queryAll();
				return INPUT;
			}
			
			
		}else{
			queryAll();
			return INPUT;
		}
	}
	
	public String del(){
		if (checkAdmin()){
			externalHostCharsetDaoImp.delById(id);
			queryAll();
			return SUCCESS;
		}else{
			queryAll();
			return INPUT;
		}
	}
	
	public String findById(){
		
		ExternalHostCharset ehc=externalHostCharsetDaoImp.findById(id);
		request.setAttribute("ehc", ehc);
		ActionContext.getContext().getValueStack().set("ehc", ehc);
		return SUCCESS;
	}
	
	public String query(){
		queryAll();
		return SUCCESS;
	}
	
	private void queryAll(){
		request.setAttribute("ehcAll", externalHostCharsetDaoImp.query());
		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}
	

}
