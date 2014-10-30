package com.lerx.sys.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.SrvInf;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class SysUtilAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private HttpServletRequest request;
	private SiteInfo site;
	private ISiteInfoDao siteInfDaoImp;
	private static final long serialVersionUID = 1L;


	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void appPath(){
		request.setAttribute("appPath", request.getContextPath());
	}
	
	public String readResource(String key) {
		/*
		 * 此方法是否会产生安全问题？
		 * 是否可以通过此action获得配置文件的信息？
		 * 目前我没想到方法
		 */
		
		return getText(key);
	}
	
	
	public String findResource(String key) {
		/*
		 * 此方法是否会产生安全问题？
		 * 是否可以通过此action获得配置文件的信息？
		 * 目前我没想到方法
		 */
		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
		
	}
	
	
	public ActionSupport ac(){
		return this;
	}
	

	public String findHostName() {
		try {
			this.doDefault();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		site = siteInfDaoImp.query();
		// return SrvInf.obtainHostName(request, site.getHost());
		// System.out.println("测试"+SrvInf.obtainHostName(request,
		// site.getHost()));
		return SrvInf.obtainHostName(request, site.getHost());
	}

	public void siteHostName() {
		site = siteInfDaoImp.query();
		// return SrvInf.obtainHostName(request, site.getHost());
//		 System.out.println("测试"+SrvInf.obtainHostName(request,
//		 site.getHost()));
		request.setAttribute("hostName",
				SrvInf.obtainHostName(request, site.getHost()));
	}
	
	public String fileUploadExtName(){
		site = siteInfDaoImp.query();
		return site.getFileUploadExtName();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
