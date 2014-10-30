package com.lerx.web.ajax.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.sys.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BbsThreadCreateCodeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISiteInfoDao siteInfDaoImp;
	private IBbsForumDao bbsForumDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private int fid;
	private long tid;
	
	

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}

	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}
	
	public void edit() throws IOException{
		String outStr;
		siteInfDaoImp.query();
		int bbsStyleId=bbsStyleDaoImp.findDef().getId();
		BbsStyle bbsStyle = bbsStyleDaoImp.findById(bbsStyleId);
		outStr=bbsStyle.getEditThemeAreaCode();
		outStr = StringUtil.strReplace(outStr,
				"{$$contextPath$$}", request.getContextPath());
		outStr = StringUtil.strReplace(outStr,
				"{$$currentForumId$$}", ""+fid);
		outStr = StringUtil.strReplace(outStr,
				"{$$parentThreadId$$}", ""+tid);
		outStr = StringUtil.strReplace(outStr,
				"{$$forumName$$}", bbsForumDaoImp.findBbsForumById(fid).getForumName());
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(outStr);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
}
