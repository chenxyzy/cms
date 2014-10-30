package com.lerx.web.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsInfoDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.draw.dao.IDrawDao;
import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.style.draw.dao.IDrawStyleDao;
import com.lerx.style.draw.vo.DrawStyle;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.qa.vo.QaStyleSubElementInCommon;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.site.vo.SiteStyleSubElementInCommon;
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.style.vote.vo.VoteStyleSubElementInCommon;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.opensymphony.xwork2.ActionSupport;

public class WebElements {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionSupport as;
	
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
	private SiteInfo site;
	private BbsInfo bi;
	private SiteStyle curSiteStyle;
	private BbsStyle curBbsStyle;
	private QaStyle curQaStyle;
	private VoteStyle curVoteStyle;
	private DrawStyle curDrawStyle;
	private SiteStyleSubElementInCommon sel;
	private BbsStyleSubElementInCommon bel;
	private QaStyleSubElementInCommon qel;
	private VoteStyleSubElementInCommon vel;
	private FormatElements fel;
	private String htmlTemplate;
	private String css;
	private String html;
	private String topCode;
	private String footerCode;
	private String titleFormat;
	private String hrefLineFormat;
	private String dateFormatOnLine;
	private String editAreaCode;
	private String searchAreaCode;
	private String functionAreaCode;
	private boolean refererRec;
	private String locationSplitStr;
	private UserCookie uc;
	private String shortSiteName;
	private String siteName;
	private String bbsName;
	private CookieDoModel cdm;
	private long pageStart;
	private long pageEnd;
	
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
	private int offset;
	private int station;
	private boolean toEnd;
	private int scrollPos;
	
	private boolean userLogined;
	
	private ResultEl re;
	private String msg;
	private int de;
	private FileReadArgs fra;
	
	public int getDe() {
		return de;
	}
	public void setDe(int de) {
		this.de = de;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
	}
	public ISiteInfoDao getSiteInfDaoImp() {
		return siteInfDaoImp;
	}
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}
	public ISiteStyleDao getSiteStyleDaoImp() {
		return siteStyleDaoImp;
	}
	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}
	public IBbsStyleDao getBbsStyleDaoImp() {
		return bbsStyleDaoImp;
	}
	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}
	public IArticleGroupDao getArticleGroupDaoImp() {
		return articleGroupDaoImp;
	}
	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}
	public IArticleThreadDao getArticleThreadDaoImp() {
		return articleThreadDaoImp;
	}
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}
	public IBbsInfoDao getBbsInfoDaoImp() {
		return bbsInfoDaoImp;
	}
	public void setBbsInfoDaoImp(IBbsInfoDao bbsInfoDaoImp) {
		this.bbsInfoDaoImp = bbsInfoDaoImp;
	}
	public IBbsForumDao getBbsForumDaoImp() {
		return bbsForumDaoImp;
	}
	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}
	public IBbsThemeDao getBbsThemeDaoImp() {
		return bbsThemeDaoImp;
	}
	public void setBbsThemeDaoImp(IBbsThemeDao bbsThemeDaoImp) {
		this.bbsThemeDaoImp = bbsThemeDaoImp;
	}
	public IBbsBMDao getBbsBMDaoImp() {
		return bbsBMDaoImp;
	}
	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	public IPasserDao getPasserDaoImp() {
		return passerDaoImp;
	}
	public void setPasserDaoImp(IPasserDao passerDaoImp) {
		this.passerDaoImp = passerDaoImp;
	}
	public IInterconnectionDao getInterconnectionDaoImp() {
		return interconnectionDaoImp;
	}
	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}
	public IScoreSchemeDao getScoreSchemeDaoImp() {
		return scoreSchemeDaoImp;
	}
	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}
	public IScoreGroupDao getScoreGroupDaoImp() {
		return scoreGroupDaoImp;
	}
	public void setScoreGroupDaoImp(IScoreGroupDao scoreGroupDaoImp) {
		this.scoreGroupDaoImp = scoreGroupDaoImp;
	}
	public IAttachmentDao getAttachmentDaoImp() {
		return attachmentDaoImp;
	}
	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
	}
	public IQaStyleDao getQaStyleDaoImp() {
		return qaStyleDaoImp;
	}
	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}
	public IVoteStyleDao getVoteStyleDaoImp() {
		return voteStyleDaoImp;
	}
	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}
	public IQaItemDao getQaItemDaoImp() {
		return qaItemDaoImp;
	}
	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}
	public IQaNavDao getQaNavDaoImp() {
		return qaNavDaoImp;
	}
	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}
	public IVoteSubjectDao getVoteSubjectDaoImp() {
		return voteSubjectDaoImp;
	}
	public void setVoteSubjectDaoImp(IVoteSubjectDao voteSubjectDaoImp) {
		this.voteSubjectDaoImp = voteSubjectDaoImp;
	}
	public IVoteItemDao getVoteItemDaoImp() {
		return voteItemDaoImp;
	}
	public void setVoteItemDaoImp(IVoteItemDao voteItemDaoImp) {
		this.voteItemDaoImp = voteItemDaoImp;
	}
	public IVoteRecDao getVoteRecDaoImp() {
		return voteRecDaoImp;
	}
	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}
	public IDrawDao getDrawDaoImp() {
		return drawDaoImp;
	}
	public void setDrawDaoImp(IDrawDao drawDaoImp) {
		this.drawDaoImp = drawDaoImp;
	}
	public IDrawStyleDao getDrawStyleDaoImp() {
		return drawStyleDaoImp;
	}
	public void setDrawStyleDaoImp(IDrawStyleDao drawStyleDaoImp) {
		this.drawStyleDaoImp = drawStyleDaoImp;
	}
	public ICommentDao getCommentDaoImp() {
		return commentDaoImp;
	}
	public void setCommentDaoImp(ICommentDao commentDaoImp) {
		this.commentDaoImp = commentDaoImp;
	}
	public void setUserArtsCountDaoImp(IUserArtsCountDao userArtsCountDaoImp) {
		this.userArtsCountDaoImp = userArtsCountDaoImp;
	}
	public IUserArtsCountDao getUserArtsCountDaoImp() {
		return userArtsCountDaoImp;
	}
	public IExternalHostCharsetDao getExternalHostCharsetDaoImp() {
		return externalHostCharsetDaoImp;
	}
	public void setExternalHostCharsetDaoImp(
			IExternalHostCharsetDao externalHostCharsetDaoImp) {
		this.externalHostCharsetDaoImp = externalHostCharsetDaoImp;
	}
	public SiteInfo getSite() {
		return site;
	}
	public void setSite(SiteInfo site) {
		this.site = site;
	}
	public SiteStyle getCurSiteStyle() {
		return curSiteStyle;
	}
	public void setCurSiteStyle(SiteStyle curSiteStyle) {
		this.curSiteStyle = curSiteStyle;
	}
	public BbsStyle getCurBbsStyle() {
		return curBbsStyle;
	}
	public void setCurBbsStyle(BbsStyle curBbsStyle) {
		this.curBbsStyle = curBbsStyle;
	}
	public QaStyle getCurQaStyle() {
		return curQaStyle;
	}
	public void setCurQaStyle(QaStyle curQaStyle) {
		this.curQaStyle = curQaStyle;
	}
	public VoteStyle getCurVoteStyle() {
		return curVoteStyle;
	}
	public void setCurVoteStyle(VoteStyle curVoteStyle) {
		this.curVoteStyle = curVoteStyle;
	}
	public DrawStyle getCurDrawStyle() {
		return curDrawStyle;
	}
	public void setCurDrawStyle(DrawStyle curDrawStyle) {
		this.curDrawStyle = curDrawStyle;
	}
	public SiteStyleSubElementInCommon getSel() {
		return sel;
	}
	public void setSel(SiteStyleSubElementInCommon sel) {
		this.sel = sel;
	}
	public BbsStyleSubElementInCommon getBel() {
		return bel;
	}
	public void setBel(BbsStyleSubElementInCommon bel) {
		this.bel = bel;
	}
	public QaStyleSubElementInCommon getQel() {
		return qel;
	}
	public void setQel(QaStyleSubElementInCommon qel) {
		this.qel = qel;
	}
	public VoteStyleSubElementInCommon getVel() {
		return vel;
	}
	public void setVel(VoteStyleSubElementInCommon vel) {
		this.vel = vel;
	}
	public FormatElements getFel() {
		return fel;
	}
	public void setFel(FormatElements fel) {
		this.fel = fel;
	}
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getTopCode() {
		return topCode;
	}
	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}
	public String getFooterCode() {
		return footerCode;
	}
	public void setFooterCode(String footerCode) {
		this.footerCode = footerCode;
	}
	public String getTitleFormat() {
		return titleFormat;
	}
	public void setTitleFormat(String titleFormat) {
		this.titleFormat = titleFormat;
	}
	public String getHrefLineFormat() {
		return hrefLineFormat;
	}
	public void setHrefLineFormat(String hrefLineFormat) {
		this.hrefLineFormat = hrefLineFormat;
	}
	public String getSearchAreaCode() {
		return searchAreaCode;
	}
	public void setSearchAreaCode(String searchAreaCode) {
		this.searchAreaCode = searchAreaCode;
	}
	
	public boolean isRefererRec() {
		return refererRec;
	}
	public void setRefererRec(boolean refererRec) {
		this.refererRec = refererRec;
	}
	public String getLocationSplitStr() {
		return locationSplitStr;
	}
	public void setLocationSplitStr(String locationSplitStr) {
		this.locationSplitStr = locationSplitStr;
	}
	public UserCookie getUc() {
		return uc;
	}
	public void setUc(UserCookie uc) {
		this.uc = uc;
	}
	public String getShortSiteName() {
		return shortSiteName;
	}
	public void setShortSiteName(String shortSiteName) {
		this.shortSiteName = shortSiteName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public CookieDoModel getCdm() {
		return cdm;
	}
	public void setCdm(CookieDoModel cdm) {
		this.cdm = cdm;
	}
	public long getPageStart() {
		return pageStart;
	}
	public void setPageStart(long pageStart) {
		this.pageStart = pageStart;
	}
	public long getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(long pageEnd) {
		this.pageEnd = pageEnd;
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
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}
	public String getDateFormatOnLine() {
		return dateFormatOnLine;
	}
	public void setDateFormatOnLine(String dateFormatOnLine) {
		this.dateFormatOnLine = dateFormatOnLine;
	}
	public String getEditAreaCode() {
		return editAreaCode;
	}
	public void setEditAreaCode(String editAreaCode) {
		this.editAreaCode = editAreaCode;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public boolean isUserLogined() {
		return userLogined;
	}
	public void setUserLogined(boolean userLogined) {
		this.userLogined = userLogined;
	}
	public ResultEl getRe() {
		return re;
	}
	public void setRe(ResultEl re) {
		this.re = re;
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
	public String getBbsName() {
		return bbsName;
	}
	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}
	public BbsInfo getBi() {
		return bi;
	}
	public void setBi(BbsInfo bi) {
		this.bi = bi;
	}
	public String getFunctionAreaCode() {
		return functionAreaCode;
	}
	public void setFunctionAreaCode(String functionAreaCode) {
		this.functionAreaCode = functionAreaCode;
	}
	public boolean isToEnd() {
		return toEnd;
	}
	public void setToEnd(boolean toEnd) {
		this.toEnd = toEnd;
	}
	public int getScrollPos() {
		return scrollPos;
	}
	public void setScrollPos(int scrollPos) {
		this.scrollPos = scrollPos;
	}
	public FileReadArgs getFra() {
		return fra;
	}
	public void setFra(FileReadArgs fra) {
		this.fra = fra;
	}
}
