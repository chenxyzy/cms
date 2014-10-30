package com.lerx.site.service;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.PropertiesUtil;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.vo.PropertiesFileInf;
import com.opensymphony.xwork2.ActionSupport;

public class SiteInfoAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private SiteInfo site,siteFromDb;
	private ISiteInfoDao siteInfDaoImp;
	public SiteInfo getSite() {
		return site;
	}

	public void setSite(SiteInfo site) {
		this.site = site;
	}


	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public String query() throws Exception {

		
		siteFromDb=siteInfDaoImp.query();
		
		if (siteFromDb==null){
			siteFromDb=new SiteInfo();
			siteFromDb.setId(1);
		}
		this.site=siteFromDb;
		return SUCCESS;
	}
	
	
	
	public String modifySiteInf() throws Exception{
		siteFromDb = siteInfDaoImp.query();
		if (checkAdmin()){
			SiteInfo siteInfoChanged=site;
//			System.out.println(site.getStyleIdForBbs());
			this.site=siteFromDb;
//			query();
			site.setFullSiteName(siteInfoChanged.getFullSiteName());
			site.setShortSiteName(siteInfoChanged.getShortSiteName());
			site.setSpaceName(siteInfoChanged.getSpaceName());
			site.setBbsName(siteInfoChanged.getBbsName());
			site.setSessionKey(siteInfoChanged.getSessionKey());
			site.setKeyWords(siteInfoChanged.getKeyWords());
			site.setDescription(siteInfoChanged.getDescription());
			site.setHost(siteInfoChanged.getHost());
			site.setState(siteInfoChanged.isState());
			site.setCloseAnnounce(siteInfoChanged.getCloseAnnounce());
			site.setHostVisitAllow(siteInfoChanged.getHostVisitAllow());
			site.setWelcomeStr(siteInfoChanged.getWelcomeStr());
			site.setBbsState(siteInfoChanged.isBbsState());
			site.setStaticHtmlMode(siteInfoChanged.getStaticHtmlMode());
			if (!siteInfoChanged.getHost().trim().equals(getText("lerx.host.current").trim())){
				String trueFolder=request.getSession().getServletContext().getRealPath("");
				PropertiesFileInf pf=new PropertiesFileInf();
				pf.setFile(4);
				pf.setLocal(this.getLocale().toString());
				pf.setPath(trueFolder);
				
			}
			
			
			
			
			String host=site.getHost(),hostSecFile;
			hostSecFile=getText("lerx.hostSecFile");
			if (hostSecFile==null || hostSecFile.trim().equals("lerx.hostSecFile")){
				hostSecFile="curLerxHost.jsp";
			}
//			System.out.println(host);
			boolean lock=false;
			if (getText("lerx.lockSecFile").trim().equalsIgnoreCase("true")){
				lock=true;
			}
			if (!lock){
				if (!siteInfoChanged.getHost().trim().equals(getText("lerx.host.current").trim())){
					String trueFolder=request.getSession().getServletContext().getRealPath("");
					PropertiesFileInf pf=new PropertiesFileInf();
					pf.setFile(4);
					pf.setLocal(this.getLocale().toString());
					pf.setPath(trueFolder);
					PropertiesUtil.updateProperties(pf, "lerx.host.current", siteInfoChanged.getHost());
				}
			}
			try {
				SrvInf.saveSecStr(request, host, hostSecFile,lock);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			siteInfDaoImp.modify(site);
			query();
			
			
			request.setAttribute("resultAltStr", getText("lerx.success.all"));
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	public String modifyUserOption() throws Exception{
		siteFromDb = siteInfDaoImp.query();
		if (checkAdmin()){
			SiteInfo siteInfoChanged=site;
			this.site=siteFromDb;
			site.setActAfterReg(siteInfoChanged.getActAfterReg());
			site.setUserRegAllow(siteInfoChanged.isUserRegAllow());
			site.setUserGroupOfAutoPassed(siteInfoChanged.getUserGroupOfAutoPassed());
			site.setOneMailForReg(siteInfoChanged.isOneMailForReg());
			site.setUserLoginAllow(siteInfoChanged.isUserLoginAllow());
			site.setUserGroupWhenNotLoginAllow(siteInfoChanged.getUserGroupWhenNotLoginAllow());
			site.setArticleAutoPass(siteInfoChanged.isArticleAutoPass());
			site.setModeOfComment(siteInfoChanged.getModeOfComment());
			site.setUserGroupsForStat(siteInfoChanged.getUserGroupsForStat());
			site.setCommentsOpen(siteInfoChanged.isCommentsOpen());
			siteInfDaoImp.modify(site);
			query();
			request.setAttribute("resultAltStr", getText("lerx.success.all"));
			
			return SUCCESS;

//			if (siteInfoChanged.getActAfterReg()<2 && siteInfoChanged.getUserGroupOfAutoPassed()<1){
//				this.addActionError(getText("lerx.err.null.userGroup"));
//				request.setAttribute("resultAltStr", getText("lerx.err.null.userGroup"));
//				return INPUT;
//			}else{
//				
//			}
			
			
		}else{
			return INPUT;
		}

		
	}
	public String modifyOtherOption() throws Exception{
		siteFromDb = siteInfDaoImp.query();
		if (checkAdmin()){
			SiteInfo siteInfoChanged=site;
			this.site=siteFromDb;
//			query();
			site.setMailTitleFromSite(siteInfoChanged.getMailTitleFromSite());
			site.setMailBodyForReg(siteInfoChanged.getMailBodyForReg());
			site.setMailBodyForQaAdd(siteInfoChanged.getMailBodyForQaAdd());
			site.setMailBodyForQaReply(siteInfoChanged.getMailBodyForQaReply());
			site.setMailSmtpServer(siteInfoChanged.getMailSmtpServer());
			site.setMailSmtpUser(siteInfoChanged.getMailSmtpUser());
			site.setMailSmtpPws(siteInfoChanged.getMailSmtpPws());
			site.setMailSenderAddr(siteInfoChanged.getMailSenderAddr());
			site.setFileUploadExtName(siteInfoChanged.getFileUploadExtName());
			site.setFilterWords(siteInfoChanged.getFilterWords());
			siteInfDaoImp.modify(site);
			request.setAttribute("resultAltStr", getText("lerx.success.all"));
			query();
			
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
	}
	
}
