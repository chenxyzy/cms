package com.lerx.web.ajax.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.comment.util.CommentUtil;
import com.lerx.comment.util.vo.CommentFilterUtil;
import com.lerx.comment.vo.Comment;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.util.SiteStyleUtil;
import com.lerx.style.site.vo.CommentStyleUtil;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.PageFormatShow;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommentUtilAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private ICommentDao commentDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private SiteInfo site;
	private SiteStyle curStyle;
	private long tid;
	private int page;
	private int pageSize;
	private String verifyCode;
	private String msg="";
	private long cid;
	private String url;
	private boolean state;
	private int l;
	private int n;
	private int gid;
	private CookieDoModel cdm;
	private FormatElements sfel;
	

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

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
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
	
	//列表
	@SuppressWarnings("unchecked")
	public void listAll() throws IOException{
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		elSiteInit();
		String formatStr=curStyle.getHrefLineFormatStrOverAll();
//		ArticleGroup g = articleGroupDaoImp.findArticleGroupById(gid);
		Rs rs = commentDaoImp.queryByGroupId(gid, 1, n);
		List<Comment> csl = (List<Comment>) rs.getList();
		String tmp,tmpAll="",titleTmp,alt;
		for (Comment c : csl) {
			tmp=formatStr;
			if (l>0){
				if (c.getBody().trim().length()>l){
					alt=c.getBody();
					alt=StringUtil.escape(alt);
					titleTmp=c.getBody().substring(0,l)+"…";
					alt=StringUtil.strReplace(alt,"\n","");
					alt=StringUtil.strReplace(alt,"\r","");
					
					alt=" Title=\""+alt+"\" ";
					
				}else{
					alt="";
					titleTmp=c.getBody();
				}
				
			}else{
				alt="";
				titleTmp=c.getBody();
			}
			titleTmp=StringUtil.strReplace(titleTmp,"\n","");
			titleTmp=StringUtil.strReplace(titleTmp,"\r","");
			titleTmp=StringUtil.escape(titleTmp);
//			ArticleThread a=articleThreadDaoImp.findThreadById(c.getThread().getId())
			tmp = StringUtil.strReplace(tmp, "{$$title$$}",titleTmp);
			tmp = StringUtil.strReplace(tmp, "{$$alt$$}",alt);
			sfel.setLf(tmp);
			if (articleThreadDaoImp.findById(c.getThread().getId())!=null){
				tmp = ThreadUtil.formatHref(sfel, articleThreadDaoImp.findById(c.getThread().getId()));
//				tmp = StringUtil.escape(tmp);
				tmpAll+=tmp;
			}
		}
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(tmpAll);
		
	}

	public void del() throws IOException  {
		if (checkPower()){
			commentDaoImp.delCommentById(cid);
		}
		writeAreaCode();
	}
	
	public void changeState() throws IOException {
		if (checkPower()){
			commentDaoImp.changeCommentStateById(cid);
		}
		writeAreaCode();
	}
	
	public void changeArticleState() throws IOException {
		if (checkPower()){
			ArticleThread t=articleThreadDaoImp.findById(tid);
			t.setComment(state);
			articleThreadDaoImp.modify(t);
		}
		writeAreaCode();
	}
	
	public void add() throws IOException {
		site = siteInfDaoImp.query();
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
			msg=getText("lerx.err.verifyCode");
		}else{
			String curIP = IpUtil.getRealRemotIP(request).trim();
			comment.setThread(articleThreadDaoImp.findById(tid));
			comment.setAddTime(new Timestamp(System.currentTimeMillis()));
			comment.setState(true);
			comment.setIp(curIP);
			CommentFilterUtil cfu=CommentUtil.filter(this,comment,site.getFilterWords());
			
			if (cfu.isCon()){
				Comment c;
				c=cfu.getC();
				if (c.getPublisher()==null || c.getPublisher().trim().equals("")){
					c.setPublisher(getText("lerx.anonymousStr"));
				}
				if (c.getPhone()==null){
					c.setPhone("");
				}
				
				if (c.getBody()==null || c.getBody().trim().equals("")){
					msg=getText("lerx.fail.comment.bodyNull");
				}else{
					commentDaoImp.addComment(c);
				}
			}else{
				msg = getText("lerx.fail.filter");
			}
		}
		writeAreaCode();
	}

	public void writeAreaCode() throws IOException {
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		
		curStyle = siteStyleDaoImp.findDef();
		WebElements wel=new WebElements();
		wel.setCurSiteStyle(curStyle);
		wel.setArticleThreadDaoImp(articleThreadDaoImp);
		wel.setArticleGroupDaoImp(articleGroupDaoImp);
		wel.setUserDaoImp(userDaoImp);
		wel.setCommentDaoImp(commentDaoImp);
		wel.setTid(tid);
		wel.setPage(page);
		wel.setPageSize(pageSize);
		wel.setSite(site);
		wel.setMsg(msg);
		wel.setAs(this);
		wel.setRequest(request);
		wel.setInterconnectionDaoImp(interconnectionDaoImp);
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		wel=SiteUtil.initSiteElement(wel, curStyle.getCommentStyle());
		CommentStyleUtil csu=new CommentStyleUtil();
		csu.setEditCode(wel.getSel().getEditAreaCode());
		csu.setFormCode(wel.getSel().getFormTemplate());
		csu.setHtml(wel.getSel().getHtmlCode());
		
		csu.setLineFormat(wel.getSel().getHrefLineFormat());
		
		String htmlTemplate = SiteStyleUtil.commentAreaInit(wel, csu,true);
		
		if (this!=null){
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset="+getText("lerx.charset"));
			response.getWriter().write(htmlTemplate);
		}
		
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
	
//	private Comment CommentFilter(Comment c,String filterStr){
//		boolean rep;
//		if (getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")){
//			rep=true;
//		}else{
//			rep=false;
//		}
//		c.setBody(StringUtil.filterStrWithHtml(c.getBody(), filterStr, rep));
//		c.setEmail(StringUtil.filterStrWithHtml(c.getEmail(), filterStr, rep));
//		c.setPhone(StringUtil.filterStrWithHtml(c.getPhone(), filterStr, rep));
//		if (c.getTitle()!=null){
//			c.setTitle(StringUtil.filterStrWithHtml(c.getTitle(), filterStr, rep));
//		}
//		
//		c.setPublisher(StringUtil.filterStrWithHtml(c.getPublisher(), filterStr, rep));
//		c.setPublisherFrom(StringUtil.filterStrWithHtml(c.getPublisherFrom(), filterStr, rep));
//		return c;		
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
	/*
	 * 初始化门户格式化输出元素
	 */
	// TODO elSiteInit
	private void elSiteInit() {
		sfel = new FormatElements();

		// FormatElementsForSiteThread el = new FormatElementsForSiteThread ();
		sfel.setLf(curStyle.getHrefLineFormatStrOverAll());
		sfel.setAs(this);
		sfel.setArticleThreadDaoImp(articleThreadDaoImp);
		sfel.setDateFormatOnLine(curStyle.getPublicStyle().getDateFormatOnLine());
//		sfel.setEditAreaCode(editAreaCode);
		sfel.setRequest(request);
	}
	private ChkUtilVo CuvInit(){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		
		return cuv;
	}

}
