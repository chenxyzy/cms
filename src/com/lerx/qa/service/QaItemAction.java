package com.lerx.qa.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.util.QaItemUtil;
import com.lerx.qa.util.vo.QaItemFilterUtil;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.ActionPageUtil;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.MailSender;
import com.lerx.sys.util.SecCheck;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ActionPage;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FileEl;
import com.lerx.sys.util.vo.MaiCreateArg;
import com.lerx.sys.util.vo.Mail;
import com.lerx.sys.util.vo.StrFilterUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QaItemAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QaItem qi;
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private long qid;
	private UserCookie uc;
	private String refererUrl;
	private String workingUrl;
	private CookieDoModel cdm;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ActionPage ap;
	private SiteInfo site;
	private SiteStyle curStyle;
	private String secStr;
	private String randKey;
	private String verifyCode;
	private boolean rem;
	
	
	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}
	
	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public QaItem getQi() {
		return qi;
	}
	public void setQi(QaItem qi) {
		this.qi = qi;
	}
	
	public long getQid() {
		return qid;
	}
	public void setQid(long qid) {
		this.qid = qid;
	}
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	public String getWorkingUrl() {
		return workingUrl;
	}
	public void setWorkingUrl(String workingUrl) {
		this.workingUrl = workingUrl;
	}
	
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	
	
	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public String getSecStr() {
		return secStr;
	}

	public void setSecStr(String secStr) {
		this.secStr = secStr;
	}

	public String getRandKey() {
		return randKey;
	}

	public void setRandKey(String randKey) {
		this.randKey = randKey;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isRem() {
		return rem;
	}

	public void setRem(boolean rem) {
		this.rem = rem;
	}

	//增加
	public String add() throws InterruptedException, IOException{
		String ip = IpUtil.getRealRemotIP(request);
		String from = "qa";
		String sessionStr = from + "_" +getText("lerx.host.current")+ "_" + ip
		+ "_random";
		refererInit();
		site = siteInfDaoImp.query();
		String filterWords=site.getFilterWords();
		if (filterWords==null){
			filterWords=getText("lerx.msg.filterWords");
		}
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		re.setRefererUrl(ap.getWorkingUrl());
		if (getText("lerx.verifyOnFront").trim().equals("true")){
			String randStr = (String) ActionContext.getContext().getSession()
			.get(sessionStr);
			if (randStr == null || verifyCode == null
					|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
				this.addActionError(getText("lerx.err.verifyCode"));
				re.setMes(getText("lerx.err.verifyCode"));
				resultPageCode=ResultPage.init(re);
				request.setAttribute("lerxCmsBody", resultPageCode);
				return SUCCESS;
			}
		}
		boolean con = SecCheck.check(this, request, secStr, randKey);
		String mes;
		if (con && !qaItemDaoImp.findQaItemByIpAndSaltOnSameDay(IpUtil.getRealRemotIP(request), randKey)){
			QaItemFilterUtil qifu=qiFilter(qi,filterWords);
			if (qifu.isCon()){
				qi=qifu.getQi();
				qi.setAddTime(new Timestamp(System.currentTimeMillis()));
				qi.setAddIp(IpUtil.getRealRemotIP(request));
				qi.setOpen(false);
				qi.setSalt(randKey);
				QaNav qn=qaNavDaoImp.findById(qi.getQn().getId());
				cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
				uc = CookieUtil.query(cdm);
				if (uc==null && qn.isLoginNeed()){
					re.setMes(getText("lerx.fail.auth.loginNeed"));
					resultPageCode=ResultPage.init(re);
					request.setAttribute("lerxCmsBody", resultPageCode);
					return LOGIN;
				}
				qi.setQn(qn);
				qi.setTitle(StringUtil.nullFilter(qi.getTitle()).trim());
				long ql=qaItemDaoImp.add(qi);
				FileEl fe =QaItemUtil.fileNameFormat(this,qi,0);
				qi.setHtmlURLFile(fe.getName());
				qi.setHtmlUrlPath(fe.getPath());
				qi.setHtmlCreated(false);
				qaItemDaoImp.modify(qi);
				//邮件发送
				
				if (StringUtil.emailTest(StringUtil.nullFilter(qi.getEmail())) && qi.getQn().isSendMail()){
					
					MaiCreateArg mca= new MaiCreateArg();
					String rootFolder=curStyle.getRootResFolder();
					mca.setAs(this);
					mca.setMod(0);
					mca.setQn(qi.getQn());
					mca.setRequest(request);
					mca.setSite(site);
					mca.setRootFolder(rootFolder);
					mca.setSta(0);
					mca.setFileName("qaAdd.txt");
					Mail mail = MailSender.mailInit(mca);
					
//					Mail mail = MailSender.mailInit(site,request,qi.getQn(), this,0);
					String body = mail.getBody();
					body = StringUtil.strReplace(
							body,
							"{$$lookUrl$$}",
							SrvInf.srvUrl(request, site.getHost(),
									Integer.valueOf(getText("lerx.serverPort")))
									+ "/qa.action?tid="
									+ qi.getId()
									+ "&pwd="
									+ qi.getPassword());
					body = StringUtil.strReplace(
							body,
							"{$$title$$}",
							qi.getTitle());
					body = StringUtil.strReplace(
							body,
							"{$$author$$}",
							qi.getAuthor());
					body = StringUtil.strReplace(
							body,
							"{$$id$$}",""+qi.getId());
					
					body = StringUtil.strReplace(
							body,
							"{$$email$$}",StringUtil.nullFilter(qi.getEmail()));
					body = StringUtil.strReplace(
							body,
							"{$$adminEmail$$}",StringUtil.nullFilter(qn.getAdminEmail()));
					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(getText("lerx.default.format.datetime"));
					body = StringUtil.strReplace(
							body,
							"{$$addTime$$}",formatter.format(qi.getAddTime()));
					
					String shortSiteName;
					if (site.getShortSiteName() == null
							|| site.getShortSiteName().trim().equals("")) {
						shortSiteName = site.getFullSiteName().trim();
					} else {
						shortSiteName = site.getShortSiteName().trim();
					}
					body = StringUtil.strReplace(
							body,"{$$site$$}",shortSiteName);
					body = StringUtil.strReplace(body, "{$$siteUrl$$}",
							StringUtil.nullFilter(site.getHost()));
					
					mail.setBody(body);
					mail.setToMail(qi.getEmail());
					
					if (Mail.send()) {
						re.setMod(0);
						mes=getText("lerx.success.qa.addWithMail");
					} else {
						re.setMod(1);
						mes=getText("lerx.success.qa.addFailMail");
					}
				}else{
					if (ql>0){
						re.setMod(0);
						mes=getText("lerx.success.qa.add");
					}else{
						re.setMod(2);
						mes=getText("lerx.fail.all");
						
					}
				}
			}else{
				re.setMod(2);
				mes = getText("lerx.fail.filter");
			}
			
		}else{
			re.setMod(2);
			mes=getText("lerx.err.secStr.illegalOperation");
		}
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String del() throws IOException{
		boolean con=true;
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		refererInit();
		QaItem qi=qaItemDaoImp.findById(qid);
		if (qi==null){
			re.setMes(getText("lerx.err.parameter"));
			
		}else{
			QaNav qn=qaNavDaoImp.findById(qi.getQn().getId());
			
			if (uc != null && checkUserOnQa(qn)==1){
				qaItemDaoImp.delById(qid);
				re.setMes(getText("lerx.success.all"));
			}else{
				con=false;
				re.setMes(getText("lerx.fail.auth"));
			}
		}
		
		re.setRefererUrl(ap.getWorkingUrl());
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String modify(){
		return SUCCESS;
	}
	
	public String reply() throws IOException, InterruptedException{
		
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		String mes;
		if (uc!=null){
			User u = userDaoImp.findUserById(uc.getUserId());
			QaItem qidb=qaItemDaoImp.findById(qi.getId());
			
			
			
			
//			QaNav qn=qidb.getQn();
			QaNav qn=qaNavDaoImp.findById(qidb.getQn().getId());
			
			if (qi.getAnswer()==null || qi.getAnswer().trim().equals("")){
				re.setMod(2);
				re.setMes(getText("lerx.fail.null.body"));
			}else{
				if (uc != null && checkUserOnQa(qn)==1){
					qidb.setAnswer(qi.getAnswer());
					qidb.setOpen(qi.isOpen());
					qidb.setReplier(qi.getReplier());
					qidb.setReplyTime(new Timestamp(System.currentTimeMillis()));
					
					qidb.setReplyUser(u);
					qidb.setState(true);
					
					qaItemDaoImp.modify(qidb);
					if (qidb.isOpen() && site.getStaticHtmlMode() == 2){
						if (qidb.getHtmlURLFile()==null || qidb.getHtmlURLFile().trim().equals("") || qidb.getHtmlUrlPath()==null || qidb.getHtmlUrlPath().trim().equals("")){
							FileEl fe =QaItemUtil.fileNameFormat(this,qidb,0);
							qidb.setHtmlURLFile(fe.getName());
							qidb.setHtmlUrlPath(fe.getPath());
//							qidb.setHtmlCreated(false);
						}
						String baseUrl,trueFolder;
						String staticHTMLFile = qidb.getHtmlURLFile();
						String folderUrl = qidb.getHtmlUrlPath();
						if (File.separator.equals("/")) {
							trueFolder = request.getContextPath() + File.separator
									+ folderUrl;
						} else {
							trueFolder = request.getContextPath() + File.separator
									+ folderUrl;
							trueFolder = StringUtil.strReplace(folderUrl, "/", "\\");
						}
						baseUrl = SrvInf.srvUrl(request, site.getHost(),
								Integer.valueOf(getText("lerx.serverPort")));
						trueFolder = request.getSession().getServletContext()
								.getRealPath(trueFolder);
						if (FileUtil.createHtml(baseUrl + "/qa.action?tid="
								+ qidb.getId(), staticHTMLFile, trueFolder,
								getText("lerx.charset"))) {
//							System.out.println("生成静态成功");
							qidb.setHtmlCreated(true);
							qaItemDaoImp.modify(qidb);
							
						} 
					}
					
					//发送邮件
					if (StringUtil.emailTest(StringUtil.nullFilter(qidb.getEmail())) && qn.isSendMail()){

//						System.out.println("正在发送邮件");
						MaiCreateArg mca= new MaiCreateArg();
						String rootFolder=curStyle.getRootResFolder();
						mca.setAs(this);
						mca.setMod(1);
						mca.setQn(qn);
						mca.setRequest(request);
						mca.setSite(site);
						mca.setRootFolder(rootFolder);
						mca.setSta(0);
						mca.setFileName("qaReply.txt");
						Mail mail = MailSender.mailInit(mca);
						String body = mail.getBody();
						body = StringUtil.strReplace(
								body,
								"{$$lookUrl$$}",
								SrvInf.srvUrl(request, site.getHost(),
										Integer.valueOf(getText("lerx.serverPort")))
										+ "/qa.action?tid="
										+ qi.getId()
										+ "&pwd="
										+ qidb.getPassword());
						body = StringUtil.strReplace(
								body,
								"{$$title$$}",
								qidb.getQuestion());
						body = StringUtil.strReplace(
								body,
								"{$$author$$}",
								qidb.getAuthor());
						body = StringUtil.strReplace(
								body,
								"{$$id$$}",""+qidb.getId());
						
						body = StringUtil.strReplace(
								body,
								"{$$email$$}",StringUtil.nullFilter(qidb.getEmail()));
						body = StringUtil.strReplace(
								body,
								"{$$adminEmail$$}",StringUtil.nullFilter(qn.getAdminEmail()));
						java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(getText("lerx.default.format.datetime"));
						body = StringUtil.strReplace(
								body,
								"{$$addTime$$}",formatter.format(qidb.getAddTime()));
						body = StringUtil.strReplace(
								body,
								"{$$replyTime$$}",formatter.format(qidb.getReplyTime()));
						body = StringUtil.strReplace(
								body,
								"{$$answer$$}",
								qidb.getAnswer());
						body = StringUtil.strReplace(
								body,
								"{$$replier$$}",
								qidb.getReplier());
						
						String shortSiteName;
						if (site.getShortSiteName() == null
								|| site.getShortSiteName().trim().equals("")) {
							shortSiteName = site.getFullSiteName().trim();
						} else {
							shortSiteName = site.getShortSiteName().trim();
						}
						body = StringUtil.strReplace(
								body,"{$$site$$}",shortSiteName);
						body = StringUtil.strReplace(body, "{$$siteUrl$$}",
								StringUtil.nullFilter(site.getHost()));
						
						mail.setBody(body);
						mail.setToMail(qidb.getEmail());
						if (Mail.send()) {
							re.setMod(0);
							mes=getText("lerx.success.qa.reply");
//							re.setMes(getText("lerx.success.qa.addWithMail"));
						} else {
							re.setMod(1);
							mes=getText("lerx.success.qa.reply.failMail");
//							re.setMes(getText("lerx.success.qa.addFailMail"));
						}
					
					}else{
//						System.out.println("没有发送邮件");
						re.setMod(0);
						mes=getText("lerx.success.qa.reply");
					}
					//
					String remNameAtUc;
					remNameAtUc=StringUtil.nullFilter(uc.getRemName());
					if (rem && !remNameAtUc.trim().equals(qi.getReplier())){
						u.setRemName(qi.getReplier());
						userDaoImp.modifyUser(u);
						uc.setRemName(qi.getReplier());
						CookieUtil.save(cdm, uc);
						
					}
					
					re.setMes(mes);
				}else{
					
				}
			}
		}else{
			re.setMod(2);
			re.setMes(getText("lerx.fail.power"));
		}
		
//		System.out.println(qn.getTitle());
//		System.out.println("checkUserOnQa(qn):"+checkUserOnQa(qn));
		
//		System.out.println(rem);
		re.setRefererUrl(ap.getWorkingUrl());
//		re.setRefererUrl(workingUrl);
		resultPageCode=ResultPage.init(re);
//		resultPageCode = resultPage(resultPageCode,refererUrl,mes, 0);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
		
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
	
	public void refererInit() {
		
		ap=new ActionPage();
		ap.setActionSupport(this);
		ap.setRequest(request);
		ap.setAc(ActionContext.getContext());
		ap=ActionPageUtil.refererInit(ap);
		
	}
	
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
		CookieDoModel cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
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
	
	
	private QaItemFilterUtil qiFilter(QaItem qi,String filterStr){
		boolean rep;
		if (getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")) {
			rep = true;
		} else {
			rep = false;
		}
		
		StrFilterUtil sfu;
		sfu=StringUtil.filterStrWithHtml(qi.getTitle(), filterStr, rep);
		
		if (sfu.isCon()){
			qi.setTitle(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(qi.getAuthor(), filterStr, rep);
			
		}
		
		if (sfu.isCon()){
			qi.setAuthor(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(qi.getQuestion(), filterStr, rep);
			
		}
		
		if (sfu.isCon()){
			qi.setQuestion(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(qi.getEmail(), filterStr, rep);
			
		}
		
		if (sfu.isCon()){
			qi.setEmail(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(qi.getPhone(), filterStr, rep);
			
		}
		
		if (sfu.isCon()){
			qi.setPhone(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(qi.getPassword(), filterStr, rep);
			
		}
		
		if (sfu.isCon()){
			qi.setPassword(sfu.getStr());
			
		}
		
		QaItemFilterUtil qifu=new QaItemFilterUtil();
		qifu.setCon(sfu.isCon());
		qifu.setFound(sfu.isFound());
		qifu.setRep(rep);
		qifu.setQi(qi);
		
//		qi.setTitle(StringUtil.filterStrWithHtml(qi.getTitle(),
//				filterStr, rep));
//		qi.setAuthor(StringUtil.filterStrWithHtml(qi.getAuthor(),
//				filterStr, rep));
//		qi.setQuestion(StringUtil.filterStrWithHtml(qi.getQuestion(),
//				filterStr, rep));
//		qi.setEmail(StringUtil.filterStrWithHtml(StringUtil.nullFilter(qi.getEmail()).trim(),
//				filterStr, rep));
//		qi.setPhone(StringUtil.filterStrWithHtml(qi.getPhone(),
//				filterStr, rep));
//		qi.setPassword(StringUtil.filterStrWithHtml(qi.getPassword(),
//				filterStr, rep));
		return qifu;
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
