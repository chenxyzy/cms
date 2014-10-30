package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONObject;

import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SiteInfAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	@SuppressWarnings("unchecked")
	public void inf() throws IOException {
		JSONObject obj = new JSONObject();
		String outStr,shortSiteName,fullSiteName,host,portStr;
		int port;
		SiteInfo site = siteInfDaoImp.query();
		host=site.getHost();
		shortSiteName=site.getShortSiteName();
		fullSiteName=site.getFullSiteName();
		port=Integer.valueOf(getText("lerx.serverPort"));
		switch (port) {
		case 0:
			if (request.getServerPort() == 80) {
				portStr = "";
			} else {
				portStr = ":" + request.getServerPort();
			}

			break;
		case 80:
			portStr = "";
			break;
		default:
			portStr = ":" + port;
			break;
		}
		obj.put("host", host);
		obj.put("fullSiteName", fullSiteName);
		obj.put("shortSiteName", shortSiteName);
		obj.put("port", ""+port);
		obj.put("portStr", portStr);
		outStr=obj.toJSONString();
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}
	
	@SuppressWarnings("unchecked")
	public void views() throws IOException {
		SiteInfo site = siteInfDaoImp.query();
		JSONObject obj = new JSONObject();
		obj.put("views", site.getViews());
		obj.put("nviews", site.getNviews());
		String outStr;
		outStr=obj.toJSONString();
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}


}
