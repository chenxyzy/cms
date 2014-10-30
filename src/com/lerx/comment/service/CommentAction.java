package com.lerx.comment.service;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.comment.util.CommentUtil;
import com.lerx.comment.util.vo.CommentFilterUtil;
import com.lerx.comment.vo.Comment;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.ActionPageUtil;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.vo.ActionPage;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.PageFormatShow;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	private ICommentDao commentDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private SiteInfo site;
	private SiteStyle curStyle;
	private long tid;
	private int page;
	private int pageSize;
	private String verifyCode;
	private long cid;
	private String url;
	private boolean state;
	private int l;
	private int n;
	private int gid;
	private CookieDoModel cdm;
	private String refererUrl;
	private String workingUrl;
	private ActionPage ap;
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	private Comment comment;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
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

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}


	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setCommentDaoImp(ICommentDao commentDaoImp) {
		this.commentDaoImp = commentDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}
	

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public String del() throws IOException  {
		boolean con=true;
		site = siteInfDaoImp.query();
		String mes;
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		if (checkPower()){
			
			commentDaoImp.delCommentById(cid);
			mes=getText("lerx.success.all");
		}else{
			con=false;
			mes=getText("lerx.fail.auth");
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		re.setRefererUrl(ap.getWorkingUrl());
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String changeState() throws IOException {
		boolean con=true;
		site = siteInfDaoImp.query();
		String mes;
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		if (checkPower()){
			commentDaoImp.changeCommentStateById(cid);
			mes=getText("lerx.success.all");
		}else{
			con=false;
			mes=getText("lerx.fail.auth");
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		re.setRefererUrl(ap.getWorkingUrl());
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String changeArticleState() throws IOException {
		boolean con=true;
		site = siteInfDaoImp.query();
		String mes;
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		if (checkPower()){
			ArticleThread t=articleThreadDaoImp.findById(tid);
			t.setComment(state);
			articleThreadDaoImp.modify(t);
			mes=getText("lerx.success.all");
		}else{
			con=false;
			mes=getText("lerx.fail.auth");
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		re.setRefererUrl(ap.getWorkingUrl());
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String add() throws IOException {
		boolean con=true;
		site = siteInfDaoImp.query();
		String mes;
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		
		ResultEl re=reInit(refererUrl,0,resultPageCode);
		String from = "comment";
		String ip = IpUtil.getRealRemotIP(request);
		String sessionStr = from + "_" +getText("lerx.host.current")+ "_" + ip
				+ "_random";
		String randStr = (String) ActionContext.getContext().getSession()
				.get(sessionStr);
		String safeUserSessionStr = getText(
				"lerx.sessionPrefixOfUserAuthentication").trim();
		safeUserSessionStr = safeUserSessionStr.replace("servername",
				getText("lerx.host.current"));
		
		boolean verifyOnFront,verifyCodeRrr;
		
		if (getText("lerx.verifyOnFront").trim().equals("true")){
			verifyOnFront=true;
		}else{
			verifyOnFront=false;
		}
		
		if (verifyCode == null || randStr==null
				|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
			verifyCodeRrr=true;
		}else{
			verifyCodeRrr=false;
		}
		
		
		if (verifyOnFront && verifyCodeRrr) {
			con=false;
			mes=getText("lerx.err.verifyCode");
		}else{
			String curIP = IpUtil.getRealRemotIP(request).trim();
			comment.setThread(articleThreadDaoImp.findById(tid));
			comment.setAddTime(new Timestamp(System.currentTimeMillis()));
			comment.setState(true);
			comment.setIp(curIP);
//			System.out.println("测试评论标题");
//			System.out.println(comment.getTitle());
			CommentFilterUtil cfu=CommentUtil.filter(this,comment,site.getFilterWords());
			Comment c;
			if (cfu.isCon()){
				c=cfu.getC();
				if (c.getPublisher()==null || c.getPublisher().trim().equals("")){
					c.setPublisher(getText("lerx.msg.anonymous"));
				}
				if (c.getPhone()==null){
					c.setPhone("");
				}
				
				if (c.getBody()==null || c.getBody().trim().equals("")){
					con=false;
					mes=getText("lerx.fail.comment.bodyNull");
				}else{
					commentDaoImp.addComment(c);
					mes=getText("lerx.success.all");
				}
			}else{
				con=false;
				mes = getText("lerx.fail.filter");
			}
			
			
			
		}
		re.setMes(mes);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setRefererUrl(ap.getWorkingUrl());
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}


	public PageFormatShow pageFormatShowInit() {
		PageFormatShow pfs = new PageFormatShow();
		pfs.setEnd(getText("lerx.pageEndPageStr"));
		pfs.setFirst(getText("lerx.pageFirstPageStr"));
		pfs.setLast(getText("lerx.pageLastPageStr"));
		pfs.setNext(getText("lerx.pageNextPageStr"));
		pfs.setPrefix(getText("lerx.pageStrPrefix"));
		pfs.setSuffix(getText("lerx.pageStrSuffix"));
		pfs.setCountPrefix(getText("lerx.pageStrCountPrefix"));
		pfs.setJumpPrefix(getText("lerx.pageStrJumpPrefix"));
		pfs.setEyeCatchingCode(curStyle.getEyeCatchingCode());
		return pfs;
	}
	
//	private CommentFilterUtil CommentFilter(Comment c,String filterStr){
//		boolean rep;
//		if (getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")){
//			rep=true;
//		}else{
//			rep=false;
//		}
//		
//		StrFilterUtil sfu;
//		sfu=StringUtil.filterStr(c.getBody(), filterStr, rep);
//		if (sfu.isCon()){
//			c.setBody(sfu.getStr());
//			sfu=StringUtil.filterStr(c.getEmail(), filterStr, rep);
//		}
//		
//		if (sfu.isCon()){
//			c.setEmail(sfu.getStr());
//			sfu=StringUtil.filterStr(c.getPhone(), filterStr, rep);
//		}
//		
//		if (sfu.isCon()){
//			c.setPhone(sfu.getStr());
//			sfu=StringUtil.filterStr(c.getTitle(), filterStr, rep);
//		}
//		
//		if (sfu.isCon()){
//			c.setTitle(sfu.getStr());
//			sfu=StringUtil.filterStr(c.getPublisher(), filterStr, rep);
//		}
//		
//		if (sfu.isCon()){
//			c.setPublisher(sfu.getStr());
//			sfu=StringUtil.filterStr(c.getPublisherFrom(), filterStr, rep);
//		}
//		
//		if (sfu.isCon()){
//			c.setPublisherFrom(sfu.getStr());
//		}
//		CommentFilterUtil cfu=new CommentFilterUtil();
//		cfu.setFound(sfu.isFound());
//		cfu.setCon(sfu.isCon());
//		cfu.setRep(rep);
//		cfu.setC(c);
//		return cfu;		
//	}
	
	private boolean checkPower() throws IOException{
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		UserCookie uc = CookieUtil.query(cdm);
		ArticleThread t;
		ArticleGroup g;
		if (tid==0) {
			g=null;
		}else{
			t=articleThreadDaoImp.findById(tid);
			g = articleGroupDaoImp.findById(t
					.getArticleGroup().getId());
		}
		
		ChkUtilVo cuv=CuvInit();
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (userDaoImp.checkUserOnSite(cuv) == 2){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

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
	
	private void refererInit() {

		ap = new ActionPage();
		ap.setActionSupport(this);
		ap.setRequest(request);
		ap.setAc(ActionContext.getContext());
		ap = ActionPageUtil.refererInit(ap);

	}
	
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
	
	private ChkUtilVo CuvInit(){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		
		return cuv;
	}

}
