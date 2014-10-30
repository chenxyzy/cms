package com.lerx.article.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.dao.imp.ArticleThreadDaoImp;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.util.vo.ArticleFilterUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.vo.Attachment;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.util.IntegralRuleUtil;
import com.lerx.integral.rule.vo.DoModel;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FileEl;
import com.lerx.sys.util.vo.StrFilterUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleThreadAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticleThread thread;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ICommentDao commentDaoImp;
	private IAttachmentDao attachmentDaoImp;
	private IUserArtsCountDao userArtsCountDaoImp;
	private IIntegralRuleDao integralRuleDaoImp;
	private String refererUrl;
	private String workingUrl;
	private SiteStyle curStyle;
	private SiteInfo site;
	private long tid;
	private int gid;
	private UserCookie uc;
	private String addTime;
	private String trueListStr;
	private String falseListStr;
	private boolean soul;
	private int soulState;
	private boolean topOne;
	private int mediaFormat;
	private CookieDoModel cdm;
	private String[] attaUrl;
	private String[] attaTitle;
	private int[] attaMediaFormat;
	private int[] attaOrder;
	private int defaultAttaMediaFormat;

	public int getSoulState() {
		return soulState;
	}

	public void setSoulState(int soulState) {
		this.soulState = soulState;
	}

	public int getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(int mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public boolean isSoul() {
		return soul;
	}

	public void setSoul(boolean soul) {
		this.soul = soul;
	}

	public boolean isTopOne() {
		return topOne;
	}

	public void setTopOne(boolean topOne) {
		this.topOne = topOne;
	}

	public String getTrueListStr() {
		return trueListStr;
	}

	public void setTrueListStr(String trueListStr) {
		this.trueListStr = trueListStr;
	}

	public String getFalseListStr() {
		return falseListStr;
	}

	public void setFalseListStr(String falseListStr) {
		this.falseListStr = falseListStr;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setUserArtsCountDaoImp(IUserArtsCountDao userArtsCountDaoImp) {
		this.userArtsCountDaoImp = userArtsCountDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setCommentDaoImp(ICommentDao commentDaoImp) {
		this.commentDaoImp = commentDaoImp;
	}

	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
	}

	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
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

	public ArticleThread getThread() {
		return thread;
	}

	public void setThread(ArticleThread thread) {
		this.thread = thread;
	}

	public void setArticleThreadDaoImp(ArticleThreadDaoImp articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public String[] getAttaUrl() {
		return attaUrl;
	}

	public void setAttaUrl(String[] attaUrl) {
		this.attaUrl = attaUrl;
	}

	public String[] getAttaTitle() {
		return attaTitle;
	}

	public void setAttaTitle(String[] attaTitle) {
		this.attaTitle = attaTitle;
	}

	public int[] getAttaMediaFormat() {
		return attaMediaFormat;
	}

	public void setAttaMediaFormat(int[] attaMediaFormat) {
		this.attaMediaFormat = attaMediaFormat;
	}

	public int[] getAttaOrder() {
		return attaOrder;
	}

	public void setAttaOrder(int[] attaOrder) {
		this.attaOrder = attaOrder;
	}

	public int getDefaultAttaMediaFormat() {
		return defaultAttaMediaFormat;
	}

	public void setDefaultAttaMediaFormat(int defaultAttaMediaFormat) {
		this.defaultAttaMediaFormat = defaultAttaMediaFormat;
	}

	/*
	 * 修改文章
	 */
	public String modify() throws Exception {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		String mes,reUrl=null;
		site = siteInfDaoImp.query();
		String filterWords = site.getFilterWords();
		if (filterWords == null || filterWords.trim().equals("")) {
			filterWords = getText("lerx.msg.filterWords");
		}
		boolean con = true;
		site = siteInfDaoImp.query();
	
		ArticleFilterUtil afu=threadFilter(thread, filterWords);
		if (afu.isCon()){
			thread = afu.getT();
		}else{
			con=false;
		}
		
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re = reInit(refererUrl, 0, resultPageCode);
		if (con){
			cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
			uc = CookieUtil.query(cdm);
			User u=null;
			
			int priceCeiling=Integer.valueOf(getText("lerx.articlePriceCeiling"));
			
			if (thread.getPrice()<0){
				thread.setPrice(0);
			}
			if (thread.getPrice()>priceCeiling){
				thread.setPrice(priceCeiling);
			}
			
			if (uc==null){
				con = false;
			}else{
				u = userDaoImp.findUserById(uc.getUserId());
				
				
				if (u==null || thread.getArticleGroup() == null
						|| thread.getArticleGroup().getId() < 1) {
					con = false;
				}
			}
			
			if (thread.getTitle()!=null && thread.getTitle().trim().equals("*")){
				thread.setTitle(null);
			}
			
			if (con && thread.getTitle() != null
					&& !thread.getTitle().trim().equals("")) {
				ArticleGroup g = articleGroupDaoImp.findById(thread
						.getArticleGroup().getId());
				ChkUtilVo cuv=CuvInit();
				cuv.setAg(g);
				cuv.setUc(uc);
				cuv.setMode(0);
				cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
				if (g.isState()) {
					ArticleThread db = articleThreadDaoImp.findById(thread
							.getId());
					if (g != null
							&& uc != null
							&& (userDaoImp.checkUserOnSite(cuv) == 2
									|| db.getUser().getId() == uc.getUserId() || db
									.getMember().trim()
									.equals(uc.getUsername().trim()))) {
						int power=userDaoImp.checkUserOnSite(cuv);
						long countToday = articleThreadDaoImp
						.findCountArticleInTodayByUserId(uc.getUserId(), g.getId());
						boolean changeArtGroup=false;
						if (g.getId() - db.getArticleGroup().getId() !=0){
							changeArtGroup=true;
						}
						
						if (power < 2 && changeArtGroup && u.getUserGroup().isNumberAppearRestrict()
								&& g.getNumberAppearRestrict() > 0
								&& countToday >= g.getNumberAppearRestrict()) {
							mes = getText("lerx.fail.art.add.numOverLimit");
							con = false;
						} else {
							mes = "";
							// con = true;
						}
						if (con){
							addTime = StringUtil.nullFilter(addTime);
							Timestamp a = TimeUtil.testCreateTimestamp(addTime.trim(),
									getText("lerx.default.format.datetime").trim(),
									Integer.valueOf(getText("lerx.dateParsePosition")
											.trim()));
							cuv.setAg(null);
							
							if (addTime == null || a == null) {
								thread.setAddTime(db.getAddTime());
								thread.setAddTimeLong(db.getAddTimeLong());
							} else {
								if (!getText("lerx.changeArticleAddTimeMustBeAdmin")
										.trim().equalsIgnoreCase("true")
										|| (getText(
												"lerx.changeArticleAddTimeMustBeAdmin")
												.trim().equalsIgnoreCase("true") && userDaoImp
												.checkUserOnSite(cuv) == 2)) {
									thread.setAddTime(a);
									thread.setAddTimeLong(a.getTime());
								} else {
									thread.setAddTime(db.getAddTime());
									thread.setAddTimeLong(db.getAddTimeLong());
								}

							}
							
							cuv.setMode(1);
							
							if (userDaoImp.checkUserOnSite(cuv) < 2) {
								thread.setNotice(db.isNotice());
								thread.setNoticeShowBody(db.isNoticeShowBody());
							}

							if (thread.getMember() == null) {
								thread.setMember(db.getMember());
							}
							thread.setUser(db.getUser());
							thread.setComment(db.isComment());
							if (getText("lerx.mode.article.byEditor").trim()
									.equalsIgnoreCase("true")) {
								thread.setByEditor(true);
							} else {
								thread.setByEditor(false);
							}
							
							thread.setHtmlCreated(db.isHtmlCreated());
							thread.setHtmlURLFile(db.getHtmlURLFile());
							thread.setHtmlUrlPath(db.getHtmlUrlPath());
							if (mediaFormat > 0 && thread.getLinkUrl()!=null && !thread.getLinkUrl().trim().equals("")) {
								thread.setLinkUrl(mediaFormat + ",h,"
										+ thread.getLinkUrl());
							}
							boolean threadState = thread.isState();
							thread.setState(db.isState());
							thread.setViews(db.getViews());
							thread.setLastViewIp(db.getLastViewIp());
							thread.setNeutrals(db.getNeutrals());
							thread.setOpponents(db.getOpponents());
							thread.setProponents(db.getProponents());
							thread.setPollAllow(db.isPollAllow());
							articleThreadDaoImp.modify(thread);
							
							if (attaUrl != null && attaUrl.length > 0) {
								for (int m = 0; m < attaUrl.length; m++) {
									if (attaUrl[m] != null
											&& !attaUrl[m].trim().equals("")) {
										Attachment att = new Attachment();
										att.setHostId(db.getId());
										att.setHostType(1);
										att.setUrl(attaUrl[m]);
										att.setTitle(attaTitle[m]);
										att.setMediaType(attaMediaFormat[m]);
										if (attaOrder != null) {
											att.setOrderNum(attaOrder[m]);
										}

										att.setAddTime(new Timestamp(System
												.currentTimeMillis()));
										
										att.setUser(u);

										attachmentDaoImp.add(att);
									}

								}
							}
							thread.setState(threadState);
							g.setChanged(true);
							articleGroupDaoImp.changed(g);
							LogWrite.logWrite(
									request,
									"用户：" + uc.getUsername() + "修改文章："
											+ thread.getTitle() + "<id号："
											+ thread.getId() + ">");
							boolean createHtml;
							if (site.getStaticHtmlMode() == 2) {
								createHtml = true;
							} else {
								createHtml = false;
							}
							db = articleThreadDaoImp.findById(thread.getId());
							
							boolean passerClear=false;
							if (getText("lerx.articlePasserClear").trim().equalsIgnoreCase("true")){
								passerClear=true;
							}
							
							if (power == 2){
								if (thread.isState() && !db.isState()) {
									if (db.getPasser()==null){
										db.setPasser(uc.getUsername());
									}
									passedArticle(db, createHtml, 0);
								} else if (!thread.isState() && db.isState()) {
									unPassedArticle(db,passerClear);
								} else if (thread.isState() && db.isState()) {
									unPassedArticle(db,passerClear);
									if (db.getPasser()==null){
										db.setPasser(uc.getUsername());
									}
									passedArticle(db, createHtml, 0);

								}
							}else{
								if (getText("lerx.mode.safe.article.modify").trim()
										.equalsIgnoreCase("true")) {
									unPassedArticle(db,passerClear);
								}else{
									if (thread.isState() && db.isState()){
										unPassedArticle(db,false);
//										db.setState(true);
										passedArticle(db, createHtml, 0);
									}
									
								}
							}
							reUrl = refererUrl;
							mes = getText("lerx.success.modify");
							
						}

						// 附件处理

					} else {
						con = false;
						mes = getText("lerx.fail.all");
						reUrl = workingUrl;
					}
				} else {
					con = false;
					mes = getText("lerx.fail.all");
					reUrl = workingUrl;
				}
			} else {
				con = false;
				mes = getText("lerx.err.art.add");
				reUrl = workingUrl;
			}
		}else{
			con = false;
			reUrl = workingUrl;
			mes = getText("lerx.fail.filter");
		}

		if (reUrl==null){
			reUrl = refererUrl;
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setRefererUrl(reUrl);
		re.setMes(mes);
		resultPageCode = ResultPage.init(re);
		// resultPageCode = resultPage(resultPageCode,refererUrl,mes, 0);
		request.setAttribute("lerxCmsBody", resultPageCode);

		return SUCCESS;
	}

	/*
	 * 删除文章
	 */

	public String del() throws Exception {
		boolean con=true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		String mes;
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		ArticleGroup g = t.getArticleGroup();
		refererInit();
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		boolean passerClear=false;
		if (getText("lerx.articlePasserClear").trim().equalsIgnoreCase("true")){
			passerClear=true;
		}
		if (g.isState()) {
			
			ChkUtilVo cuv=CuvInit();
			cuv.setAg(g);
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
			
			if (uc != null
					&& (userDaoImp.checkUserOnSite(cuv) == 2
							|| t.getUser().getId() == uc.getUserId() || t
							.getMember().trim().equals(uc.getUsername().trim()))) {
				unPassedArticle(t,passerClear);
				User u = userDaoImp.findUserById(uc.getUserId());
				if (u.getArticleThreadsCount() > 0) {
					u.setArticleThreadsCount(u.getArticleThreadsCount() - 1);
					userDaoImp.modifyUser(u);
					
					//积分处理
					DoModel dm=new DoModel();
					dm.setIntegralRuleDaoImp(integralRuleDaoImp);
					dm.setLocalPostion(1);
					int value=IntegralRuleUtil.value(dm, 4);
					if (t.isState()){
						value+=IntegralRuleUtil.value(dm, 3);
					}
					if (value>0){
						u.setAllScore(u.getAllScore()-value);
					}
					//积分处理结束
					
					UserUtil.uacModify(u, "yyyy", t.getAddTime(),userArtsCountDaoImp, 0, -1);
					UserUtil.uacModify(u, "yyyyMM", t.getAddTime(),userArtsCountDaoImp, 0, -1);
					Date weekStamp=TimeUtil.firstDayAtWeek(new Date(t.getAddTime().getTime()));
					UserUtil.uacModify(u, "yyyyMMdd", new java.sql.Timestamp(weekStamp.getTime()),userArtsCountDaoImp, 0, -1);
					
					
				}
				g.setChanged(true);
				articleGroupDaoImp.changed(g);
				commentDaoImp.delCommentByThreadId(tid);
				articleThreadDaoImp.delById(tid);
				LogWrite.logWrite(request, "用户：" + uc.getUsername() + "删除文章："
						+ t.getTitle() + "<id号：" + t.getId() + ">");
				mes = getText("lerx.success.del");
			} else {
				con=false;
				mes = getText("lerx.fail.auth");
			}
		} else {
			con=false;
			mes = getText("lerx.fail.all");
		}
		
		
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);

		return SUCCESS;
	}

	/*
	 * 增加文章
	 */
	public String add() throws Exception {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		String mes = null, reUrl;
		boolean con = true;
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		if (thread!=null){
			site = siteInfDaoImp.query();
			String filterWords = site.getFilterWords();
			if (filterWords == null) {
				filterWords = getText("lerx.msg.filterWords");
			}
			ArticleFilterUtil afu=threadFilter(thread, filterWords);
			if (afu.isCon()){
				thread = afu.getT();
			}else{
				con=false;
			}
			cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
			uc = CookieUtil.query(cdm);
		}else{
			con=false;
		}
		
		if (con){
			
			ArticleGroup g;
			if (thread.getArticleGroup() == null
					|| thread.getArticleGroup().getId() == 0) {
				g = null;
			} else {
				g = articleGroupDaoImp.findById(thread
						.getArticleGroup().getId());
			}
			
			ChkUtilVo cuv=CuvInit();
			cuv.setAg(g);
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
			
			if (g != null && g.isState()) {
				g = articleGroupDaoImp.findById(g.getId());

				long countToday = articleThreadDaoImp
						.findCountArticleInTodayByUserId(uc.getUserId(), g.getId());
				if (thread.getTitle()!=null && thread.getTitle().trim().equals("*")){
					thread.setTitle(null);
				}
				if (thread.getTitle() == null
						|| thread.getTitle().trim().equals("")) {
					mes = getText("lerx.articleAddNoTitleErr");
					con = false;
				}
				
				
				
				if (con
						&& uc != null
						&& userDaoImp.checkUserOnSite(cuv) > 0) {

					// 如果系统设定自动审核

					switch (g.getArticlePassMode()) {
					case 1:
						thread.setState(false);
						break;
					case 2:
						thread.setState(true);
						break;
					default:
						if (site.isArticleAutoPass()) {
							thread.setState(true);
						} else {
							thread.setState(false);
						}
						break;
					}

					if (mediaFormat > 0 && thread.getLinkUrl()!=null && !thread.getLinkUrl().trim().equals("")) {
						thread.setLinkUrl(mediaFormat + ",h," + thread.getLinkUrl());
					}
					thread.setMember(uc.getUsername());
					User u = userDaoImp.findUserById(uc.getUserId());
					u.setArticleThreadsCount(u.getArticleThreadsCount() + 1);
					if (thread.isState()) {
						u.setArticleThreadsPassed(u.getArticleThreadsPassed() + 1);
						thread.setPasser(u.getUserName());
					}
					
					if (getText("lerx.poll.article.allow.default").trim().equals("true")){
						thread.setPollAllow(true);
					}else{
						thread.setPollAllow(false);
					}
					thread.setUser(u);
					// thread.setAddTime(addTime)
					thread.setAddTimeLong(System.currentTimeMillis());
					thread.setAddTime(new Timestamp(thread.getAddTimeLong()));
					
					thread.setComment(true);
					int priceCeiling=Integer.valueOf(getText("lerx.articlePriceCeiling"));
					
					if (thread.getPrice()<0){
						thread.setPrice(0);
					}
					if (thread.getPrice()>priceCeiling){
						thread.setPrice(priceCeiling);
					}
					if (getText("lerx.mode.article.byEditor").trim().equalsIgnoreCase(
							"true")) {
						thread.setByEditor(true);
					} else {
						thread.setByEditor(false);
					}

					if (u.getUserGroup().isNumberAppearRestrict()
							&& g.getNumberAppearRestrict() > 0
							&& countToday >= g.getNumberAppearRestrict()) {
						mes = getText("lerx.fail.art.add.numOverLimit");
						con = false;
					} else {
						mes = "";
						// con = true;
					}

					if (con) {
						userDaoImp.modifyUser(u);
						
						UserUtil.uacModify(u, "yyyy", thread.getAddTime(),userArtsCountDaoImp, 0, 1);
						UserUtil.uacModify(u, "yyyyMM", thread.getAddTime(),userArtsCountDaoImp, 0, 1);
						Date weekStamp=TimeUtil.firstDayAtWeek(new Date(thread.getAddTime().getTime()));
						UserUtil.uacModify(u, "yyyyMMdd", new java.sql.Timestamp(weekStamp.getTime()),userArtsCountDaoImp, 0, 1);
						
						thread.setTopOne(false);
						long did = articleThreadDaoImp.add(thread);
						
						if (thread.isState()){
							g.setChanged(true);
							articleGroupDaoImp.changed(g);
						}
						
						thread = articleThreadDaoImp.findById(did);
						FileEl fe = ThreadUtil.fileNameFormat(this, thread, 0);
						thread.setHtmlURLFile(fe.getName());
						thread.setHtmlUrlPath(fe.getPath());
						thread.setTitle(thread.getTitle().trim());
						articleThreadDaoImp.modify(thread);
						
						
						if (did > 0) {

							LogWrite.logWrite(request, "用户：" + uc.getUsername()
									+ "增加文章：" + thread.getTitle());

							mes = getText("lerx.success.add");

							// 附件处理

							if (attaUrl != null && attaUrl.length > 0) {
								for (int m = 0; m < attaUrl.length; m++) {
									if (attaUrl[m] != null
											&& !attaUrl[m].trim().equals("")) {
										Attachment att = new Attachment();
										att.setHostId(did);
										att.setHostType(1);
										att.setUrl(attaUrl[m]);
										att.setTitle(attaTitle[m]);
										att.setMediaType(attaMediaFormat[m]);
										if (attaOrder != null) {
											att.setOrderNum(attaOrder[m]);
										}

										att.setAddTime(new Timestamp(System
												.currentTimeMillis()));
										att.setUser(u);
										attachmentDaoImp.add(att);
									}

								}
							}

							con = true;
						} else {
							mes = getText("lerx.err.db");

							con = false;
						}
					}

				} else {
					if (mes == null || mes.trim().equals("")) {
						mes = getText("lerx.fail.auth");
					}

					
					con = false;
				}
			} else {
				
				mes = getText("lerx.fail.null.articleGroup");
				con = false;
			}
			if (uc != null
					&& userDaoImp.checkUserOnSite(cuv) > 1) {
				if (con) {
					reUrl = getText("lerx.articleGroupPageFileName").trim()
							+ "?gid=" + g.getId();
				} else {
					reUrl = workingUrl;
				}
			} else {
				if (con) {
					reUrl = refererUrl;
				} else {
					reUrl = workingUrl;
				}
			}
			
		}else{
			
			reUrl = workingUrl;
			mes = getText("lerx.fail.filter");
		}
		
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		re.setRefererUrl(reUrl);
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);

		// TODO Auto-generated method stub
		if (con) {
			ActionContext.getContext().getSession().put("refererUrl", "");
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	/*
	 * 精华或推荐
	 */
	public String soul() throws IOException {
		boolean con=true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);

		curStyle = siteStyleDaoImp.findDef();
		refererInit();
		String resultPageCode = curStyle.getResultPageCode();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		// ArticleGroup g = t.getArticleGroup();
		ArticleGroup g = articleGroupDaoImp.findById(t
				.getArticleGroup().getId());
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		ChkUtilVo cuv=CuvInit();
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (uc != null && g.isState()
				&& userDaoImp.checkUserOnSite(cuv) == 2) {
			articleThreadDaoImp.changeSoul(tid, soul);
			g.setChanged(true);
			articleGroupDaoImp.changed(g);
			LogWrite.logWrite(request, "用户：" + uc.getUsername() + "设置文章精华或推荐："
					+ t.getTitle() + " id号：<" + t.getId() + ">");
			re.setMes(getText("lerx.success.all"));
		} else {
			con=false;
			re.setMes(getText("lerx.fail.power"));
		}
		
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	/*
	 * 头条
	 */
	public String topOne() throws IOException {
		boolean con=true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);

		curStyle = siteStyleDaoImp.findDef();
		refererInit();
		String resultPageCode = curStyle.getResultPageCode();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		ArticleGroup g = articleGroupDaoImp.findById(t
				.getArticleGroup().getId());
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		ChkUtilVo cuv=CuvInit();
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (uc != null && g.isState()
				&& userDaoImp.checkUserOnSite(cuv) == 2) {
			articleThreadDaoImp.topOne(tid, topOne);
			g.setChanged(true);
			articleGroupDaoImp.changed(g);
			LogWrite.logWrite(request,
					"用户：" + uc.getUsername() + "设置文章头条：" + t.getTitle()
							+ " id号：<" + t.getId() + ">");
			re.setMes(getText("lerx.success.all"));
		} else {
			con=false;
			re.setMes(getText("lerx.fail.power"));
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	/*
	 * 单个审核文章
	 */
	public String singlePassed() throws Exception {
		boolean con=true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		curStyle = siteStyleDaoImp.findDef();
		refererInit();
		String resultPageCode = curStyle.getResultPageCode();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		ArticleGroup g = articleGroupDaoImp.findById(t
				.getArticleGroup().getId());

		boolean state;
		if (t.isState()) {
			state = false;
		} else {
			state = true;
		}
		boolean passerClear=false;
		if (getText("lerx.articlePasserClear").trim().equalsIgnoreCase("true")){
			passerClear=true;
		}
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		
		ChkUtilVo cuv=CuvInit();
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (uc != null && g.isState()
				&& userDaoImp.checkUserOnSite(cuv) == 2) {
			if (state) {
				if (site.getStaticHtmlMode() == 2) {
					if (passedArticle(t, true, soulState) == 2) {
						
						LogWrite.logWrite(
								request,
								"用户：" + uc.getUsername() + "审核单个文章："
										+ t.getTitle() + " id号：<" + t.getId()
										+ ">");
						re.setMes(getText("lerx.success.pass.htmlCreateSuccess"));

					} else {
						re.setMes(getText("lerx.success.pass.htmlCreateFail"));
					}
				} else {
					passedArticle(t, false, 2);
					re.setMes(getText("lerx.success.all"));
				}
			} else {
				// 此处以后再设置返回页提示消息
				re.setMes("");
				unPassedArticle(t,passerClear);
			}
			
			g.setChanged(true);
			articleGroupDaoImp.changed(g);

		} else {
			con=false;
			re.setMes(getText("lerx.fail.power"));
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	/*
	 * 批量审核
	 */
	public String batchPassed() throws NumberFormatException,
			InterruptedException, IOException {
		boolean con=true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		curStyle = siteStyleDaoImp.findDef();
		refererInit();
		String resultPageCode = curStyle.getResultPageCode();
		ArticleGroup g = articleGroupDaoImp.findById(gid);
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		boolean passerClear=false;
		if (getText("lerx.articlePasserClear").trim().equalsIgnoreCase("true")){
			passerClear=true;
		}
		ChkUtilVo cuv=CuvInit();
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (uc != null && g.isState()
				&& userDaoImp.checkUserOnSite(cuv) == 2) {

			LogWrite.logWrite(request, "用户：" + uc.getUsername()
					+ "批量审核文章：falseListStr:" + falseListStr + " trueListStr："
					+ trueListStr);
			if (falseListStr != null && !falseListStr.trim().equals("")) {
				String[] sArray = falseListStr.split("_");
				for (int step = 0; step < sArray.length; step++) {
					unPassedArticle(articleThreadDaoImp.findById(Long
							.valueOf(sArray[step])),passerClear);
				}
			}

			if (trueListStr != null && !trueListStr.trim().equals("")) {
				String[] sArray = trueListStr.split("_");
				for (int step = 0; step < sArray.length; step++) {
					if (site.getStaticHtmlMode() == 2) {
						passedArticle(articleThreadDaoImp.findById(Long
								.valueOf(sArray[step])), true,0);
					} else {
						passedArticle(articleThreadDaoImp.findById(Long
								.valueOf(sArray[step])), false,2);
					}

				}
			}
			g.setChanged(true);
			articleGroupDaoImp.changed(g);
			re.setMes(getText("lerx.success.all"));
		} else {
			con=false;
			re.setMes(getText("lerx.fail.auth"));
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

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

	private ArticleFilterUtil threadFilter(ArticleThread thread, String filterStr) {
		boolean rep;
		if (getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")) {
			rep = true;
		} else {
			rep = false;
		}
		
		
		
		StrFilterUtil sfu;
		sfu=StringUtil.filterStr(thread.getBody(), filterStr, rep);
		
		if (sfu.isCon()){
			thread.setBody(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getTitle(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			thread.setTitle(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getAccessionalTitle(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setAccessionalTitle(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getPensileTitle(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setPensileTitle(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getAuthor(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setAuthor(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getAuthorDept(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setAuthorDept(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getAuthorEmail(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setAuthorEmail(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getJournal(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setJournal(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getLinkTitle(), filterStr, rep);
		}
		String tmp=thread.getLinkUrl();
		if (sfu.isCon()){
			thread.setLinkTitle(sfu.getStr());
			
			tmp=StringUtil.strReplace(tmp, "http://s", "____lerx__url____");
			thread.setLinkUrl(tmp);
			sfu=StringUtil.filterStrWithHtml(thread.getLinkUrl(), filterStr, rep);
		}
		if (sfu.isCon()){
			tmp=sfu.getStr();
			tmp=StringUtil.strReplace(tmp, "____lerx__url____", "http://s");
			thread.setLinkUrl(tmp);
			sfu=StringUtil.filterStrWithHtml(thread.getMainImg(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setMainImg(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getMainImgExplain(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setMainImgExplain(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getMentor(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setMentor(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getShortTitle(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setShortTitle(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getSynopsis(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setSynopsis(sfu.getStr());
			sfu=StringUtil.filterStrWithHtml(thread.getThumbnail(), filterStr, rep);
		}
		if (sfu.isCon()){
			thread.setThumbnail(sfu.getStr());
//			sfu=StringUtil.filterStrWithHtml(thread.getThumbnail(), filterStr, rep);
		}
//		thread.setBody(StringUtil.filterStr(thread.getBody(), filterStr, rep));

//		thread.setTitle(StringUtil.filterStrWithHtml(thread.getTitle(),
//				filterStr, rep));
//		thread.setAccessionalTitle(StringUtil.filterStrWithHtml(
//				thread.getAccessionalTitle(), filterStr, rep));
//		thread.setPensileTitle(StringUtil.filterStrWithHtml(
//				thread.getPensileTitle(), filterStr, rep));
//		thread.setAuthor(StringUtil.filterStrWithHtml(thread.getAuthor(),
//				filterStr, rep));
//		thread.setAuthorDept(StringUtil.filterStrWithHtml(
//				thread.getAuthorDept(), filterStr, rep));
//		thread.setAuthorEmail(StringUtil.filterStrWithHtml(
//				thread.getAuthorEmail(), filterStr, rep));
//		thread.setJournal(StringUtil.filterStrWithHtml(thread.getJournal(),
//				filterStr, rep));
//		thread.setLinkTitle(StringUtil.filterStrWithHtml(thread.getLinkTitle(),
//				filterStr, rep));
//		thread.setLinkUrl(StringUtil.filterStrWithHtml(thread.getLinkUrl(),
//				filterStr, rep));
//		thread.setMainImg(StringUtil.filterStrWithHtml(thread.getMainImg(),
//				filterStr, rep));
//		thread.setMainImgExplain(StringUtil.filterStrWithHtml(
//				thread.getMainImgExplain(), filterStr, rep));
//		thread.setMentor(StringUtil.filterStrWithHtml(thread.getMentor(),
//				filterStr, rep));
//		thread.setShortTitle(StringUtil.filterStrWithHtml(
//				thread.getShortTitle(), filterStr, rep));
//		thread.setSynopsis(StringUtil.filterStrWithHtml(thread.getSynopsis(),
//				filterStr, rep));
//		thread.setThumbnail(StringUtil.filterStrWithHtml(thread.getThumbnail(),
//				filterStr, rep));
		ArticleFilterUtil afu=new ArticleFilterUtil();
		afu.setCon(sfu.isCon());
		afu.setFound(sfu.isFound());
		afu.setT(thread);
		afu.setRep(rep);
		
		return afu;
	}

	private int unPassedArticle(ArticleThread at,boolean passerClear) throws InterruptedException {

		int r = 0;
		// System.out.println("新测试点unPassedArticle---");
		if (at.isState()) {
			String staticHTMLFile, folderUrl, trueFolder;
			if (at.isHtmlCreated()) {
				if (at.getHtmlURLFile() == null || at.getHtmlUrlPath() == null
						|| at.getHtmlURLFile().trim().equals("")
						|| at.getHtmlUrlPath().trim().equals("")) {
					FileEl fe = ThreadUtil.fileNameFormat(this, at, 0);
					// staticHTMLFile = HtmlFileFormatter.format(System
					// .currentTimeMillis()) + ".html";
					// folderUrl = getText("lerx.htmlPath")
					// + "/"
					// + HtmlFilePathUrlFormatter.format(System
					// .currentTimeMillis());
					// System.out.println("测试点点点");
					at.setHtmlURLFile(fe.getName());
					at.setHtmlUrlPath(fe.getPath());
					folderUrl = fe.getPath();
					staticHTMLFile = fe.getName();

				} else {
					staticHTMLFile = at.getHtmlURLFile();
					folderUrl = at.getHtmlUrlPath();
				}

				if (File.separator.equals("/")) {
					trueFolder = request.getContextPath() + File.separator
							+ folderUrl;
				} else {
					trueFolder = request.getContextPath() + File.separator
							+ folderUrl;
					trueFolder = StringUtil.strReplace(folderUrl, "/", "\\");
				}

				trueFolder = request.getSession().getServletContext()
						.getRealPath(trueFolder);
				if (FileUtil.deleteFile(trueFolder, staticHTMLFile)) {
					r = 1;
				} else {
					r = 0;
				}
				at.setHtmlCreated(false);
			}

			at.setState(false);
			if (passerClear){
				at.setPasser(null);
			}
			

			if (at.getUser() != null) {
				User u = userDaoImp.findUserById(at.getUser().getId());
				if (u != null && u.getArticleThreadsPassed() > 0) {
					u.setArticleThreadsPassed(u.getArticleThreadsPassed() - 1);
					
					//积分处理
					DoModel dm=new DoModel();
					dm.setIntegralRuleDaoImp(integralRuleDaoImp);
					dm.setLocalPostion(1);
					int value=IntegralRuleUtil.value(dm, 3);
					if (value>0){
						u.setAllScore(u.getAllScore()-value);
					}
					//积分处理结束
					
					userDaoImp.modifyUser(u);
					UserUtil.uacModify(u, "yyyy", at.getAddTime(),userArtsCountDaoImp, 1, -1);
					UserUtil.uacModify(u, "yyyyMM", at.getAddTime(),userArtsCountDaoImp, 1, -1);
					Date weekStamp=TimeUtil.firstDayAtWeek(new Date(at.getAddTime().getTime()));
					UserUtil.uacModify(u, "yyyyMMdd", new java.sql.Timestamp(weekStamp.getTime()),userArtsCountDaoImp, 1, -1);
					
					
				}
			} else {
			}

			articleThreadDaoImp.modify(at);
		} else {
		}

		return r;
	}

	private int passedArticle(ArticleThread at, boolean createHtml,
			int soulV) throws InterruptedException {
		// System.out.println("新测试点passedArticle---");
		ArticleGroup ag;
		ag = at.getArticleGroup();

		if (!at.isState()) {
			// 静态页处理
			String staticHTMLFile = null, folderUrl = null, trueFolder = null, baseUrl = null;
			boolean agIpCheck=false;
			if (ag.getHostsAllow()!=null && ag.getHostsAllow().trim().length()>7){
				agIpCheck=true;
			}
			if (createHtml && !at.isLinkJump() && ag.isShare() && !agIpCheck) {

				if (at.getHtmlURLFile() == null || at.getHtmlUrlPath() == null
						|| at.getHtmlURLFile().trim().equals("")
						|| at.getHtmlUrlPath().trim().equals("")) {
					int mod;
					if (getText("lerx.staticFileNameByAddTime").trim()
							.equalsIgnoreCase("true")) {
						mod = 1;
					} else {
						mod = 0;
					}
					FileEl fe = ThreadUtil.fileNameFormat(this, at, mod);
					staticHTMLFile = fe.getName();
					folderUrl = fe.getPath();

					at.setHtmlURLFile(staticHTMLFile);
					at.setHtmlUrlPath(folderUrl);

				} else {
					staticHTMLFile = at.getHtmlURLFile();
					folderUrl = at.getHtmlUrlPath();
				}

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
			}
			boolean soulS;
			switch (soulV) {
			case 1:
				soulS=true;
				break;
			case 2:
				soulS=false;
				break;
			default:
				ArticleThread atDb=articleThreadDaoImp.findById(at.getId());
				if (atDb==null){
					soulS=false;
				}else{
					soulS=atDb.isSoul();
				}
				break;
			}
			at.setSoul(soulS);
			at.setState(true);
			if (at.getPasser()==null){
				at.setPasser(uc.getUsername());
			}
			
			articleThreadDaoImp.modify(at);

			if (at.getUser() != null) {
				User u = userDaoImp.findUserById(at.getUser().getId());
				if (u != null) {
					u.setArticleThreadsPassed(u.getArticleThreadsPassed() + 1);
					
					//积分处理
					DoModel dm=new DoModel();
					dm.setIntegralRuleDaoImp(integralRuleDaoImp);
					dm.setLocalPostion(1);
					int value=IntegralRuleUtil.value(dm, 3);
					if (value>0){
						u.setAllScore(u.getAllScore()+value);
					}
					//积分处理结束
					userDaoImp.modifyUser(u);
					UserUtil.uacModify(u, "yyyy", at.getAddTime(),userArtsCountDaoImp, 1, 1);
					UserUtil.uacModify(u, "yyyyMM", at.getAddTime(),userArtsCountDaoImp, 1, 1);
					Date weekStamp=TimeUtil.firstDayAtWeek(new Date(at.getAddTime().getTime()));
					UserUtil.uacModify(u, "yyyyMMdd", new java.sql.Timestamp(weekStamp.getTime()),userArtsCountDaoImp, 1, 1);
					
//					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
//							"yyyy");
//					int timeKey=Integer.valueOf(formatter.format(new Timestamp(System.currentTimeMillis())));
//					UserArtsCount uac=userArtsCountDaoImp.findByUK(u, timeKey);
//					if (uac==null){
//						uac=new UserArtsCount();
//						uac.setUser(u);
//						uac.setTimeKey(timeKey);
//						uac.setArticleThreadsCount(0);
//						uac.setArticleThreadsPassed(0);
//						
//					}
//					uac.setArticleThreadsPassed(uac.getArticleThreadsPassed()+1);
//					userArtsCountDaoImp.modify(uac);
					
//					Calendar   calendar=Calendar.getInstance();
					
				}

			} else {
			}

			if (!at.isLinkJump() && createHtml && !at.getArticleGroup().isRefuseStaticHtml() &&  !agIpCheck) {


				if (FileUtil
						.createHtml(
								baseUrl
										+ "/"
										+ getText(
												"lerx.articleViewPageFileName")
												.trim() + "?tid="
										+ at.getId(), staticHTMLFile,
								trueFolder, getText("lerx.charset"))) {
					at.setHtmlCreated(true);
					articleThreadDaoImp.modify(at);
					return 2;
				} else {
					// System.out.println("创建文件失败："+folder+staticHTMLFile);
					return 1;
				}
			
			} else {
				return 0;
			}

		} else {
			return 0;
		}

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
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

	}
	
	
	private ChkUtilVo CuvInit(){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		
		return cuv;
	}
//
//	private void initCdm() {
//		cdm = new CookieDoModel();
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
		this.response = response;

	}
}
