package com.lerx.vote.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.util.ActionPageUtil;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.vo.ActionPage;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.vo.VoteRec;
import com.lerx.vote.vo.VoteSubject;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VoteRecAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subId;
	private int page;
	private int pageSize;
	private IVoteRecDao voteRecDaoImp;
	private IVoteSubjectDao voteSubjectDaoImp;
	private IVoteStyleDao voteStyleDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CookieDoModel cdm;
	private UserCookie uc;
	private long rid;
	private ActionPage ap;
	private String workingUrl;
	
	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}


	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}

	public void setVoteSubjectDaoImp(IVoteSubjectDao voteSubjectDaoImp) {
		this.voteSubjectDaoImp = voteSubjectDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void query() throws IOException {

		if (checkVDAdmin()) {
			if (page < 1) {
				page = 1;
			}
			if (pageSize < 1) {
				try {
					pageSize = Integer
							.valueOf(getText("lerx.pageSize.result.default"));
				} catch (NumberFormatException e) {
					pageSize = 10;
				}
			}
			VoteSubject vs = voteSubjectDaoImp.findById(subId);
			Rs rs = voteRecDaoImp.findBySub(vs, page, pageSize);
			request.setAttribute("voteRecRs", rs);
			ActionContext.getContext().getValueStack().set("voteRecRs", rs);
		} else {
		}

	}

	public void findMes() throws IOException {

		if (page < 1) {
			page = 1;
		}
		if (pageSize < 1) {
			try {
				pageSize = Integer
						.valueOf(getText("lerx.pageSize.result.default"));
			} catch (NumberFormatException e) {
				pageSize = 10;
			}
		}
		VoteSubject vs = voteSubjectDaoImp.findById(subId);
		Rs rs;
		
		if (checkVDAdmin()) {
			rs = voteRecDaoImp.findMesBySub(vs, page, pageSize, true);
		} else {
			rs = voteRecDaoImp.findMesBySub(vs, page, pageSize, false);
		}
		request.setAttribute("voteMesRs", rs);
		ActionContext.getContext().getValueStack().set("voteMesRs", rs);
	}

	public String passMes() throws IOException{
		boolean findSiteRP=false;
		String resultPageCode;
		VoteSubject vs = null;
		VoteStyle curVoteStyle=null;
		refererInit();
		
		VoteRec vr=voteRecDaoImp.findById(rid);
		vs=vr.getSub();
		if (vs ==null) {
			vs = voteSubjectDaoImp.findById(subId);
			curVoteStyle=vs.getStyle();
			if (curVoteStyle==null){
				curVoteStyle=voteStyleDaoImp.findDefault();
			}
			if (curVoteStyle==null || curVoteStyle.getResultPageCode()==null || curVoteStyle.getResultPageCode().trim().equals("")){
				findSiteRP=true;
			}
		}else{
			findSiteRP=true;
		}
		if (findSiteRP){
			SiteStyle curStyle = siteStyleDaoImp.findDef();
			resultPageCode = curStyle.getResultPageCode();
		}else{
			resultPageCode=curVoteStyle.getResultPageCode();
		}
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		if (checkVDAdmin()){
			vr.setMesState(true);
			voteRecDaoImp.modify(vr);
			re.setMes(getText("lerx.success.all"));
			re.setMod(0);
		}else{
			re.setMes(getText("lerx.fail.auth"));
			re.setMod(2);
		}
		
		re.setRefererUrl(ap.getWorkingUrl());
		// re.setRefererUrl(workingUrl);
		
		resultPageCode = ResultPage.init(re);
		// resultPageCode = resultPage(resultPageCode,refererUrl,mes, 0);
		request.setAttribute("lerxCmsBody", resultPageCode);

		return SUCCESS;
	}
	
	
	private boolean checkVDAdmin() throws IOException {
		if (checkAdmin()){
			return true;
		}
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		boolean pwdMD5ToLowerCase;
		if (this.getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		ChkUtilVo cuv = new ChkUtilVo();
		cuv.setAs(this);
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		cuv.setRequest(request);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		cuv.setUc(uc);
		cuv.setUserDaoImp(userDaoImp);
		return userDaoImp.checkUserOnVote(cuv);
	}
	
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
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
	private ResultEl reInit(String refererUrl, int mod, String codeStr) {
		ResultEl re = new ResultEl();
		re.setAs(this);
		re.setRequest(request);
		re.setRefererUrl(refererUrl);
		re.setMod(mod);
		re.setCodeStr(codeStr);
		re.setSiteStyleDaoImp(siteStyleDaoImp);
		return re;
	}
	public void refererInit() {

		ap = new ActionPage();
		ap.setActionSupport(this);
		ap.setRequest(request);
		ap.setAc(ActionContext.getContext());
		ap = ActionPageUtil.refererInit(ap);

	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		// TODO Auto-generated method stub
		
	}

}
