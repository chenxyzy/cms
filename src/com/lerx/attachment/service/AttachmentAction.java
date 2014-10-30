package com.lerx.attachment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.vo.Attachment;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AttachmentAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long tid;
	private SiteStyle curStyle;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IAttachmentDao attachmentDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private CookieDoModel cdm;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserCookie uc;
	private String refererUrl;
	private String workingUrl;
	private String attaLineOrderFormatStr;
	private int defaultAttaMediaFormat;
	private List<Attachment> at;
//	private Attachment[] at;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	
	public String getAttaLineOrderFormatStr() {
		return attaLineOrderFormatStr;
	}

	public void setAttaLineOrderFormatStr(String attaLineOrderFormatStr) {
		this.attaLineOrderFormatStr = attaLineOrderFormatStr;
	}

	public int getDefaultAttaMediaFormat() {
		return defaultAttaMediaFormat;
	}

	public void setDefaultAttaMediaFormat(int defaultAttaMediaFormat) {
		this.defaultAttaMediaFormat = defaultAttaMediaFormat;
	}


	public List<Attachment> getAt() {
		return at;
	}

	public void setAt(List<Attachment> at) {
		this.at = at;
	}

	public String findByParentId(){
		ArticleThread thread = articleThreadDaoImp.findById(tid);
		List<Attachment> list=attachmentDaoImp.findByHostId(tid);
		request.setAttribute("defaultAttaMediaFormat", thread.getDefaultAttaMediaFormat());
		request.setAttribute("attaLineOrderFormatStr", thread.getAttaLineOrderFormatStr());
		request.setAttribute("attachments", list);
		return SUCCESS;
	}
	
	public String modify(){
		ArticleThread thread = articleThreadDaoImp.findById(tid);
		thread.setAttaLineOrderFormatStr(attaLineOrderFormatStr);
		thread.setDefaultAttaMediaFormat(defaultAttaMediaFormat);
		articleThreadDaoImp.modify(thread);
		if (at!=null){
			for (int m=0;m<at.size();m++){
				
//				System.out.println(at.get(m).getUrl());
//				System.out.println(at.get(m).isDelTag());
				if (at.get(m).isDelTag()){
					attachmentDaoImp.del(at.get(m).getId());
				}else{
					attachmentDaoImp.modify(at.get(m));
				}
				
			}
		}else{
//			System.out.println("没有记录");
		}
		return SUCCESS;
	}
	
	public String del()  throws Exception {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		String mes;
		boolean con=false;
		siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		Attachment atta=attachmentDaoImp.find(id);
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		if (atta.getHostType()==1){
			ArticleThread thread = articleThreadDaoImp.findById(atta.getHostId());
			ArticleGroup g = articleGroupDaoImp.findById(thread.getArticleGroup().getId());
			ChkUtilVo cuv=CuvInit();
			cuv.setAg(g);
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
			if (g!=null && uc != null
					&& (userDaoImp.checkUserOnSite(cuv) == 2
							|| thread.getUser().getId() == uc.getUserId() || thread
							.getMember().trim().equals(uc.getUsername().trim()))) {
				con=true;
			}
		}
		if (con){
			re.setMod(0);
			attachmentDaoImp.del(id);
			mes=getText("lerx.success.all");
		}else{
			re.setMod(2);
			mes=getText("lerx.fail.all");
		}
		
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
//	private String resultPage(String codeStr,String refererUrl,String mes, int waitTimeMod) {
//		String waitResStr;
//		switch (waitTimeMod) {
//		case 1:
//			waitResStr = "waitingTimeForPageJumpLong";
//			break;
//		default:
//			waitResStr = "waitingTimeForPageJumpShort";
//			break;
//		}
//		codeStr = StringUtil.strReplace(codeStr,
//				"{$$returnUrl$$}", refererUrl);
//		codeStr = StringUtil
//				.strReplace(
//						codeStr,
//						"{$$waitingTime$$}",
//						""
//								+ Integer
//										.valueOf(getText("lerx."+waitResStr)));
//		codeStr = StringUtil.strReplace(codeStr,
//				"{$$resultMsg$$}", mes);
//		codeStr = StringUtil.strReplace(codeStr, "{$$contextPath$$}",
//				request.getContextPath());
//		return codeStr;
//	}
	
	private ResultEl reInit(String refererUrl,int mod,String codeStr){
		ResultEl re=new ResultEl();
		re.setAs(this);
		re.setRequest(request);
		re.setRefererUrl(refererUrl);
		re.setMod(mod);
		re.setCodeStr(codeStr);
		re.setSiteStyleDaoImp(siteStyleDaoImp);
		return re;
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
//	
	public void refererInit() {

		refererUrl = (String) ActionContext.getContext().getSession()
				.get("refererUrl");
		workingUrl = (String) ActionContext.getContext().getSession()
				.get("workingUrl");
		if (workingUrl == null || workingUrl.trim().equals("")
				|| workingUrl.trim().equals("null")) {
			if (request.getHeader("Referer") == null) {
				workingUrl = ResultPage.defRURL(this, request);

			} else {
				workingUrl = request.getHeader("Referer");
			}

		}

		if (refererUrl == null || refererUrl.trim().equals("")
				|| refererUrl.trim().equals("null")) {

			if (request.getHeader("Referer") == null) {
				refererUrl = ResultPage.defRURL(this, request);

			} else {
				refererUrl = request.getHeader("Referer");
			}

		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
	private ChkUtilVo CuvInit(){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		
		return cuv;
	}

}
