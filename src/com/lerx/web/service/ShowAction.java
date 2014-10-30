package com.lerx.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsInfoDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.draw.dao.IDrawDao;
import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.draw.dao.IDrawStyleDao;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.web.util.WebThreadArticleAdd;
import com.lerx.web.util.WebThreadArticleEdit;
import com.lerx.web.util.WebThreadArtshow;
import com.lerx.web.util.WebThreadBindex;
import com.lerx.web.util.WebThreadComment;
import com.lerx.web.util.WebThreadDraw;
import com.lerx.web.util.WebThreadForum;
import com.lerx.web.util.WebThreadIndex;
import com.lerx.web.util.WebThreadList;
import com.lerx.web.util.WebThreadLogin;
import com.lerx.web.util.WebThreadMyArticles;
import com.lerx.web.util.WebThreadNav;
import com.lerx.web.util.WebThreadNavAll;
import com.lerx.web.util.WebThreadPlay;
import com.lerx.web.util.WebThreadPost;
import com.lerx.web.util.WebThreadQa;
import com.lerx.web.util.WebThreadQaNav;
import com.lerx.web.util.WebThreadReg;
import com.lerx.web.util.WebThreadSearch;
import com.lerx.web.util.WebThreadThread;
import com.lerx.web.util.WebThreadThreadSearch;
import com.lerx.web.util.WebThreadThreadView;
import com.lerx.web.util.WebThreadTopUsers;
import com.lerx.web.util.WebThreadUcenter;
import com.lerx.web.util.WebThreadVotePost;
import com.lerx.web.util.WebThreadVoteRank;
import com.lerx.web.util.WebThreadVoteSign;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.WebUtil;
import com.lerx.web.vo.ConEl;
import com.lerx.web.vo.FileReadArgs;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String htmlTemplate;
	
	private WebElements wel;
	private ConEl ce;
	
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IBbsInfoDao bbsInfoDaoImp;
	private IBbsForumDao bbsForumDaoImp;
	private IBbsThemeDao bbsThemeDaoImp;
	private IBbsBMDao bbsBMDaoImp;
	private IUserDao userDaoImp;
	private IPasserDao passerDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private IScoreGroupDao scoreGroupDaoImp;
	private IAttachmentDao attachmentDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	private IVoteSubjectDao voteSubjectDaoImp;
	private IVoteItemDao voteItemDaoImp;
	private IVoteStyleDao voteStyleDaoImp;
	private IVoteRecDao voteRecDaoImp;
	private IDrawDao drawDaoImp;
	private IDrawStyleDao drawStyleDaoImp;
	private ICommentDao commentDaoImp;
	private IUserArtsCountDao userArtsCountDaoImp;
	private IExternalHostCharsetDao externalHostCharsetDaoImp;
	private long id;
	private int gid;
	private long tid;
	private long uid;
	private String pwd;
	private int mod;
	private int umode;
	private int smode;
	private int fid;
	private int stateMode;
	private boolean notice;
	private int soul;
	private int page;
	private int pageSize;
	private String key;
	private String keyCon;
	private String msg;
	private int offset;
	private int de;
	private boolean toEnd;
	private int scrollPos;
	
	
	public int getDe() {
		return de;
	}

	public void setDe(int de) {
		this.de = de;
	}

	public int getScrollPos() {
		return scrollPos;
	}

	public void setScrollPos(int scrollPos) {
		this.scrollPos = scrollPos;
	}

	public boolean isToEnd() {
		return toEnd;
	}

	public void setToEnd(boolean toEnd) {
		this.toEnd = toEnd;
	}

	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setBbsInfoDaoImp(IBbsInfoDao bbsInfoDaoImp) {
		this.bbsInfoDaoImp = bbsInfoDaoImp;
	}

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}

	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}

	public void setBbsThemeDaoImp(IBbsThemeDao bbsThemeDaoImp) {
		this.bbsThemeDaoImp = bbsThemeDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setPasserDaoImp(IPasserDao passerDaoImp) {
		this.passerDaoImp = passerDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}

	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}

	public void setScoreGroupDaoImp(IScoreGroupDao scoreGroupDaoImp) {
		this.scoreGroupDaoImp = scoreGroupDaoImp;
	}

	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
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

	public void setDrawDaoImp(IDrawDao drawDaoImp) {
		this.drawDaoImp = drawDaoImp;
	}

	public void setDrawStyleDaoImp(IDrawStyleDao drawStyleDaoImp) {
		this.drawStyleDaoImp = drawStyleDaoImp;
	}

	public void setVoteSubjectDaoImp(IVoteSubjectDao voteSubjectDaoImp) {
		this.voteSubjectDaoImp = voteSubjectDaoImp;
	}

	public void setVoteItemDaoImp(IVoteItemDao voteItemDaoImp) {
		this.voteItemDaoImp = voteItemDaoImp;
	}

	public void setCommentDaoImp(ICommentDao commentDaoImp) {
		this.commentDaoImp = commentDaoImp;
	}

	public void setUserArtsCountDaoImp(IUserArtsCountDao userArtsCountDaoImp) {
		this.userArtsCountDaoImp = userArtsCountDaoImp;
	}

	public void setExternalHostCharsetDaoImp(
			IExternalHostCharsetDao externalHostCharsetDaoImp) {
		this.externalHostCharsetDaoImp = externalHostCharsetDaoImp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public int getUmode() {
		return umode;
	}

	public void setUmode(int umode) {
		this.umode = umode;
	}

	public int getSmode() {
		return smode;
	}

	public void setSmode(int smode) {
		this.smode = smode;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getStateMode() {
		return stateMode;
	}

	public void setStateMode(int stateMode) {
		this.stateMode = stateMode;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyCon() {
		return keyCon;
	}

	public void setKeyCon(String keyCon) {
		this.keyCon = keyCon;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	private void welInit(int appMod){
		wel = new WebElements();
		wel.setPageStart(System.currentTimeMillis());
		wel.setAs(this);
		
		
		switch (appMod){
		case 1:
			wel.setArticleThreadDaoImp(articleThreadDaoImp);
			wel.setArticleGroupDaoImp(articleGroupDaoImp);
			wel.setCommentDaoImp(commentDaoImp);
			
			break;
		case 2:
			
			wel.setBbsStyleDaoImp(bbsStyleDaoImp);
			wel.setBbsThemeDaoImp(bbsThemeDaoImp);
			wel.setBbsForumDaoImp(bbsForumDaoImp);
			wel.setBbsThemeDaoImp(bbsThemeDaoImp);
			wel.setScoreGroupDaoImp(scoreGroupDaoImp);
			wel.setScoreSchemeDaoImp(scoreSchemeDaoImp);
			wel.setBbsBMDaoImp(bbsBMDaoImp);
			break;
			
		case 3:
			wel.setQaStyleDaoImp(qaStyleDaoImp);
			wel.setQaItemDaoImp(qaItemDaoImp);
			wel.setQaNavDaoImp(qaNavDaoImp);
			break;
			
		case 4:
			wel.setVoteStyleDaoImp(voteStyleDaoImp);
			wel.setVoteItemDaoImp(voteItemDaoImp);
			wel.setVoteRecDaoImp(voteRecDaoImp);
			wel.setVoteSubjectDaoImp(voteSubjectDaoImp);
			
			break;
		case 5:
			wel.setDrawDaoImp(drawDaoImp);
			wel.setDrawStyleDaoImp(drawStyleDaoImp);
			wel.setVoteRecDaoImp(voteRecDaoImp);
		}
		
		
		wel.setSiteInfDaoImp(siteInfDaoImp);
		wel.setBbsInfoDaoImp(bbsInfoDaoImp);
		wel.setSiteStyleDaoImp(siteStyleDaoImp);
		
		
		
		wel.setAttachmentDaoImp(attachmentDaoImp);
		wel.setPasserDaoImp(passerDaoImp);
		wel.setUserDaoImp(userDaoImp);
		wel.setInterconnectionDaoImp(interconnectionDaoImp);	//互联登录
		wel.setUserArtsCountDaoImp(userArtsCountDaoImp);
		
		wel.setExternalHostCharsetDaoImp(externalHostCharsetDaoImp);
		wel.setSite(wel.getSiteInfDaoImp().query());
		if (wel.getBbsInfoDaoImp()!=null){
			wel.setBi(wel.getBbsInfoDaoImp().query());
		}
		
		//结果页模块初始化
		wel.setRe(new ResultEl());
		wel.getRe().setAs(this);
		wel.getRe().setRequest(request);
		
		wel.setId(id);
		wel.setFid(fid);
		wel.setGid(gid);
		wel.setKey(key);
		wel.setKeyCon(keyCon);
		wel.setMod(mod);
		wel.setNotice(notice);
		wel.setPage(page);
		wel.setPageSize(pageSize);
		wel.setPwd(pwd);
		wel.setSmode(smode);
		wel.setSoul(soul);
		wel.setStateMode(stateMode);
		wel.setTid(tid);
		wel.setUid(uid);
		wel.setUmode(umode);
		wel.setRequest(request);
		wel.setOffset(offset);
		wel.setMsg(msg);
		wel.setToEnd(toEnd);
		wel.setScrollPos(scrollPos);
		wel.setDe(de);
//		String cookieDomain= getText("lerx.cookieDomain");
//		
//		if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
//			response.setHeader("P3P","CP=CAO PSA OUR");
//		}
		wel.setResponse(response);
		
		FileReadArgs fra = new FileReadArgs();
		fra.setAs(this);
		fra.setRequest(request);
		fra.setResponse(response);
		fra.setRootFolder(siteStyleDaoImp.findDef().getRootResFolder());
		wel.setFra(fra);
	}
	
	private String welEnd(String html){
		html = StringUtil.strReplace(html, "{$$contextPath$$}",
				request.getContextPath());
		
		html = StringUtil.strReplace(html, "{$$contextHost$$}",
				SrvInf.srvUrlNoPath(request, "", Integer.valueOf(getText("lerx.serverPort"))).trim());
		html = StringUtil.strReplace(html, "{$$year$$}", TimeUtil
				.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 2));
		html = StringUtil.strReplace(html,
				"{$$lerxCmsCurrentVersionNumber$$}",
				getText("lerx.currentVersionNumber"));
		html = StringUtil.strReplace(html,
				"{$$lerxCmsCurrentVersionBuild$$}",
				getText("lerx.currentVersionBuild"));
		html = StringUtil.strReplace(html,
				"{$$gid$$}",""+0);
		String staticHtmlRoot = getText("lerx.htmlPath");
		if (staticHtmlRoot == null
				|| staticHtmlRoot.trim().equals("lerx.htmlPath")) {
			staticHtmlRoot = "";
		} else {
			staticHtmlRoot = staticHtmlRoot.trim();
		}

		html = StringUtil.strReplace(html, "{$$htmlRoot$$}",
				staticHtmlRoot);
		
		html = StringUtil.strReplace(html, "{$$executeTime$$}", ""
				+ (System.currentTimeMillis() - wel.getPageStart()));
		WebUtil.viewsCheck(wel);
		return html;
	}
	
	
	public String index() throws Exception {
		welInit(1);
		wel.setStation(0);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadIndex.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
		
	}
	
	public String nav() throws Exception {
		welInit(1);
		wel.setStation(1);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadNav.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
//			System.out.println("----");
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String artshow() throws Exception {
		welInit(1);
		wel.setStation(2);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadArtshow.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		if (htmlTemplate.trim().equals("jump")){
			return "jump";
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String login() throws Exception {
		welInit(1);
		wel.setStation(3);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadLogin.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String reg() throws Exception {
		welInit(1);
		wel.setStation(4);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadReg.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String ucenter() throws Exception {
		welInit(1);
		wel.setStation(5);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadUcenter.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String articleAdd() throws Exception {
		welInit(1);
		wel.setStation(6);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadArticleAdd.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String articleEdit() throws Exception {
		welInit(1);
		wel.setStation(7);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadArticleEdit.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	
	
	public String myArticles() throws Exception {
		welInit(1);
		wel.setStation(8);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadMyArticles.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String topUsers() throws Exception {
		welInit(1);
		wel.setStation(9);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadTopUsers.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String list() throws Exception {
		welInit(1);
		wel.setStation(10);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadList.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String navAll() throws Exception {
		welInit(1);
		wel.setStation(11);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadNavAll.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String search() throws Exception {
		welInit(1);
		wel.setStation(12);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadSearch.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	
	
	public String play() throws Exception {
		welInit(1);
		wel.setStation(13);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadPlay.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String comm() throws Exception {
		
//		System.out.println("asdfasdf");
		
		welInit(1);
		
		wel.setStation(14);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadComment.show(wel);	//处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String bindex() throws Exception {
		welInit(2);
		wel.setStation(101);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadBindex.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String forum() throws Exception {
		welInit(2);
		wel.setStation(102);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadForum.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String thread() throws Exception {
		welInit(2);
		wel.setStation(103);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadThread.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String post() throws Exception {
		welInit(2);
		wel.setStation(104);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadPost.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String thview() throws Exception {
		welInit(2);
		wel.setStation(105);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadThreadView.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String thsearch() throws Exception {
		welInit(2);
		wel.setStation(106);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadThreadSearch.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}

	public String qaNav() throws Exception {
		welInit(3);
		wel.setStation(201);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadQaNav.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String qa() throws Exception {
		welInit(3);
		wel.setStation(202);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadQa.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String voteSign() throws Exception {
		welInit(4);
		wel.setStation(301);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadVoteSign.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String votePost() throws Exception {
		welInit(4);
		wel.setStation(302);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadVotePost.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String voteRank() throws Exception {
		welInit(4);
		wel.setStation(303);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadVoteRank.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}
	
	public String draw() throws Exception {
		welInit(5);
		wel.setStation(401);
		wel=SiteInit.styleInit(wel, wel.getStation());
		ce=SiteInit.check(wel);
		if (ce.isCon()){
			wel=PubUtil.welInit(wel); 			//对一些参数进行校正
			htmlTemplate=WebThreadDraw.show(wel);	//首页处理
			htmlTemplate=welEnd(htmlTemplate);
		}else{
			htmlTemplate = ce.getMes();
		}
		request.setAttribute("lerxCmsBody", htmlTemplate);
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("asdfasdf");
		
		// TODO Auto-generated method stub
		return super.execute();
	}

	

		
}
