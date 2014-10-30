package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.opensymphony.xwork2.ActionSupport;

public class QaItemRealTimeAction extends ActionSupport implements
ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	private CookieDoModel cdm;
	private long tid;
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}

	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}

	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	public void someInf() throws IOException{
		QaStyle qaStyle=qaStyleDaoImp.findDefault();
		String ajaxRealTimeCode=qaStyle.getAjaxRealTimeCode();
		QaItem qi=qaItemDaoImp.findById(tid);
		QaNav qn=qaNavDaoImp.findById(qi.getQn().getId());
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		CookieUtil.query(cdm);
		String lastViewIP = qi.getLastViewIp();
		if (checkUserOnQa(qn)==1){
			if (qi.isHtmlCreated()){
				ajaxRealTimeCode=StringUtil.strReplace(ajaxRealTimeCode, "{$$reply$$}", "<a href=\""+request.getContextPath() + "/qa.action?tid=" + qi.getId()+"\">"+getText("lerx.qaItemReplyTxtOfAjax")+"</a>");
			}else{
				ajaxRealTimeCode=StringUtil.strReplace(ajaxRealTimeCode, "{$$reply$$}", "");
			}
			
		}else{
			ajaxRealTimeCode=StringUtil.strReplace(ajaxRealTimeCode, "{$$reply$$}", "");
			boolean saveT = false;
			
			String curIP = IpUtil.getRealRemotIP(request).trim();
			if (lastViewIP == null) {
				lastViewIP = "";
			}
			if (getText("lerx.viewsUpdateByIp").trim().equals("true")){
				if (!lastViewIP.trim().equals(curIP)) {
					qi.setLastViewIp(curIP);
					qi.setViews(qi.getViews() + 1);
					saveT = true;
				}
			}else{
				qi.setViews(qi.getViews() + 1);
				saveT = true;
			}
			
			if (saveT) {
				qaItemDaoImp.modify(qi);
			}
			
		}
		
		
		ajaxRealTimeCode = StringUtil.strReplace(ajaxRealTimeCode, "{$$views$$}",
				"" + qi.getViews());
		ajaxRealTimeCode = StringUtil.strReplace(
				ajaxRealTimeCode,
				"{$$lastIP$$}",
				IpUtil.ipFilter(StringUtil.nullFilter(lastViewIP),
						Integer.valueOf(getText("lerx.rule.length.ip.filter"))));
		ajaxRealTimeCode = StringUtil.strReplace(ajaxRealTimeCode, "{$$replier$$}", StringUtil.nullFilter(qi.getReplier()));
		ajaxRealTimeCode = StringUtil.strReplace(ajaxRealTimeCode,
				"{$$contextPath$$}", request.getContextPath());
		ajaxRealTimeCode = StringUtil.strReplace(ajaxRealTimeCode,
				"{$$id$$}", "" + qi.getId());
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(ajaxRealTimeCode);
		
		
	}
	
	/*
	 * 检查问答系统中用户权限
	 */
	private int checkUserOnQa(QaNav qn) throws IOException {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		UserCookie uc;
		cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		ChkUtilVo cuv = new ChkUtilVo();
		cuv.setAs(this);
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		cuv.setRequest(request);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		cuv.setUc(uc);
		cuv.setUserDaoImp(userDaoImp);
		cuv.setQn(qn);
		return userDaoImp.checkUserOnQa(cuv);

	}
	
//	private void initCdm(){
//		cdm=new CookieDoModel();
//		cdm.setActionSupport(this);
//		cdm.setEncodingCode(getText("lerx.charset").trim());
//		cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
//		cdm.setHost(getText("lerx.host.current"));
//		cdm.setHostSecFile(getText("lerx.hostSecFile"));
//		cdm.setRequest(request);
//		cdm.setResponse(response);
//		cdm.setUserDaoImp(userDaoImp);
//	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

}
