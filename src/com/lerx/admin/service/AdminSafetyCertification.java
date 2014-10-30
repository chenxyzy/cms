package com.lerx.admin.service;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.opensymphony.xwork2.ActionSupport;

public class AdminSafetyCertification extends ActionSupport implements
		ServletRequestAware {

	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()) {
			request.getSession().setAttribute("LerxAdmin", "true");
			return SUCCESS;
		} else {
			request.getSession().setAttribute("LerxAdmin", "false");
			return LOGIN;
		}

	}

	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

	}

}
