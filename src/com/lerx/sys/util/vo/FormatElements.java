package com.lerx.sys.util.vo;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.opensymphony.xwork2.ActionSupport;


/*
 * 本类初始化后可应用于问答系统数据行初始化
 */

public class FormatElements {
	
	private HttpServletRequest request;
	private ActionSupport as;
	private String dateFormatOnLine;
	private String lf;
	private int titleLength;
	private boolean includeEditArea;
	private String editAreaCode;
	private String functionAreaCode;
	private String postion;
	private String shieldedShowCode;
	private String afterReplyShowCode;
	private UserCookie uc;
	//站点信息
	private ISiteInfoDao siteInfDaoImp;
	
	//风格
	private ISiteStyleDao siteStyleDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	
	//问答
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	//投票
	private IVoteSubjectDao voteSubjectDaoImp;
	private IVoteItemDao voteItemDaoImp;
	//论坛
	private IBbsForumDao bbsForumDaoImp;
	private IBbsThemeDao bbsThemeDaoImp;
	private String icoUrlOfHot;
	private String icoUrlOfNew;
	private String icoUrlOfGeneral;
	private String icoUrlOfStickedOnGlobal;
	private String icoUrlOfStickedOnClass;
	private String icoUrlOfStickedOnForum;
	private String bmShowFormat;
	//文章
	private IArticleGroupDao articleGroupDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private String eyeCatchingStrCode;
	
	//用户
	private IUserDao userDaoImp;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private IScoreGroupDao scoreGroupDaoImp;
	
	//附件
	private IAttachmentDao attachmentDaoImp;
	
	public IQaItemDao getQaItemDaoImp() {
		return qaItemDaoImp;
	}
	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getDateFormatOnLine() {
		return dateFormatOnLine;
	}
	public void setDateFormatOnLine(String dateFormatOnLine) {
		this.dateFormatOnLine = dateFormatOnLine;
	}
	public String getLf() {
		return lf;
	}
	public void setLf(String lf) {
		this.lf = lf;
	}
	public int getTitleLength() {
		return titleLength;
	}
	public void setTitleLength(int titleLength) {
		this.titleLength = titleLength;
	}
	public boolean isIncludeEditArea() {
		return includeEditArea;
	}
	public void setIncludeEditArea(boolean includeEditArea) {
		this.includeEditArea = includeEditArea;
	}
	public String getEditAreaCode() {
		return editAreaCode;
	}
	public void setEditAreaCode(String editAreaCode) {
		this.editAreaCode = editAreaCode;
	}
	public ActionSupport getAs() {
		return as;
	}
	public void setAs(ActionSupport as) {
		this.as = as;
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
	public IQaNavDao getQaNavDaoImp() {
		return qaNavDaoImp;
	}
	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
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
	public String getIcoUrlOfHot() {
		return icoUrlOfHot;
	}
	public void setIcoUrlOfHot(String icoUrlOfHot) {
		this.icoUrlOfHot = icoUrlOfHot;
	}
	public String getIcoUrlOfNew() {
		return icoUrlOfNew;
	}
	public void setIcoUrlOfNew(String icoUrlOfNew) {
		this.icoUrlOfNew = icoUrlOfNew;
	}
	public String getIcoUrlOfGeneral() {
		return icoUrlOfGeneral;
	}
	public void setIcoUrlOfGeneral(String icoUrlOfGeneral) {
		this.icoUrlOfGeneral = icoUrlOfGeneral;
	}
	public String getIcoUrlOfStickedOnGlobal() {
		return icoUrlOfStickedOnGlobal;
	}
	public void setIcoUrlOfStickedOnGlobal(String icoUrlOfStickedOnGlobal) {
		this.icoUrlOfStickedOnGlobal = icoUrlOfStickedOnGlobal;
	}
	public String getIcoUrlOfStickedOnClass() {
		return icoUrlOfStickedOnClass;
	}
	public void setIcoUrlOfStickedOnClass(String icoUrlOfStickedOnClass) {
		this.icoUrlOfStickedOnClass = icoUrlOfStickedOnClass;
	}
	public String getIcoUrlOfStickedOnForum() {
		return icoUrlOfStickedOnForum;
	}
	public void setIcoUrlOfStickedOnForum(String icoUrlOfStickedOnForum) {
		this.icoUrlOfStickedOnForum = icoUrlOfStickedOnForum;
	}
	public String getBmShowFormat() {
		return bmShowFormat;
	}
	public void setBmShowFormat(String bmShowFormat) {
		this.bmShowFormat = bmShowFormat;
	}
	public String getFunctionAreaCode() {
		return functionAreaCode;
	}
	public void setFunctionAreaCode(String functionAreaCode) {
		this.functionAreaCode = functionAreaCode;
	}
	public IArticleThreadDao getArticleThreadDaoImp() {
		return articleThreadDaoImp;
	}
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}
	public String getEyeCatchingStrCode() {
		return eyeCatchingStrCode;
	}
	public void setEyeCatchingStrCode(String eyeCatchingStrCode) {
		this.eyeCatchingStrCode = eyeCatchingStrCode;
	}
	public IUserDao getUserDaoImp() {
		return userDaoImp;
	}
	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
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
	public IArticleGroupDao getArticleGroupDaoImp() {
		return articleGroupDaoImp;
	}
	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
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
	public IQaStyleDao getQaStyleDaoImp() {
		return qaStyleDaoImp;
	}
	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}
	public IAttachmentDao getAttachmentDaoImp() {
		return attachmentDaoImp;
	}
	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
	}
	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		this.postion = postion;
	}
	public String getShieldedShowCode() {
		return shieldedShowCode;
	}
	public void setShieldedShowCode(String shieldedShowCode) {
		this.shieldedShowCode = shieldedShowCode;
	}
	public UserCookie getUc() {
		return uc;
	}
	public void setUc(UserCookie uc) {
		this.uc = uc;
	}
	public String getAfterReplyShowCode() {
		return afterReplyShowCode;
	}
	public void setAfterReplyShowCode(String afterReplyShowCode) {
		this.afterReplyShowCode = afterReplyShowCode;
	}
	
	
}
