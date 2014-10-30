package com.lerx.bbs.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.bbs.dao.IBbsInfoDao;
import com.lerx.bbs.vo.BbsInfo;
import com.opensymphony.xwork2.ActionSupport;

public class BbsInfoAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private BbsInfo bi;
	private IBbsInfoDao bbsInfoDaoImp;
	
	
	public BbsInfo getBi() {
		return bi;
	}

	public void setBi(BbsInfo bi) {
		this.bi = bi;
	}

	public void setBbsInfoDaoImp(IBbsInfoDao bbsInfoDaoImp) {
		this.bbsInfoDaoImp = bbsInfoDaoImp;
	}
	
	public String query(){
		this.bi=bbsInfoDaoImp.query();
		return SUCCESS;
	}
	
	public String modify(){
		if (checkAdmin()){
			bi.setId(1);
			bbsInfoDaoImp.modify(bi);
		}
		
		return SUCCESS;
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
