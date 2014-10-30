package com.lerx.bbs.service;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsInfoDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.bbs.vo.ScoreScheme;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.StrFilterUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.RefererUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.BbsThemeCheckUtil;
import com.lerx.web.vo.RefererUrlVo;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionSupport;

public class BbsThemeAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserCookie uc;
	private long tid;
	private long otid;
	private int fid;
	private String secStr;
	private String randKey;
//	private String refererUrl;
	
	private BbsStyle curBbsStyle;
	private ISiteInfoDao siteInfDaoImp;
	private IBbsThemeDao bbsThemeDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private IBbsBMDao bbsBMDaoImp;
	private IBbsInfoDao bbsInfoDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IBbsForumDao bbsForumDaoImp;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private CookieDoModel cdm;
	private boolean state;
	private BbsTheme theme;
	private int page;
	private int pageSize;
	private int scrollPos;
	
	

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public int getScrollPos() {
		return scrollPos;
	}

	public void setScrollPos(int scrollPos) {
		this.scrollPos = scrollPos;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setBbsInfoDaoImp(IBbsInfoDao bbsInfoDaoImp) {
		this.bbsInfoDaoImp = bbsInfoDaoImp;
	}

	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}

	public long getOtid() {
		return otid;
	}

	public void setOtid(long otid) {
		this.otid = otid;
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

	public BbsTheme getTheme() {
		return theme;
	}

	public void setTheme(BbsTheme theme) {
		this.theme = theme;
	}

	
	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}

	
	public void setBbsThemeDaoImp(IBbsThemeDao bbsThemeDaoImp) {
		this.bbsThemeDaoImp = bbsThemeDaoImp;
	}

	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}
	
	
	
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
	
	public String top() throws IOException {
		curBbsStyle = bbsStyleDaoImp.findDef();
		String resultPageCode = curBbsStyle.getResultPageCode();
		String reUrl;
		RefererUrlVo ruv = new RefererUrlVo();
		ruv.setAs(this);
		ruv.setRequest(request);
		ruv=RefererUtil.init(ruv);
		
		ResultEl re=reInit(ruv.getRefererUrl(),1,resultPageCode);
		
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		BbsTheme theme=bbsThemeDaoImp.findThemeById(tid);
		uc = CookieUtil.query(cdm);
		String mes="";
		
		boolean con=true;
		if (uc==null){
			con=false;
			mes=getText("lerx.fail.auth");
			
		}else{
			User u = userDaoImp.findUserById(uc.getUserId());
			BbsThemeCheckUtil btcu=new BbsThemeCheckUtil();
			btcu.setBbsBMDaoImp(bbsBMDaoImp);
			btcu.setBbsForumDaoImp(bbsForumDaoImp);
			btcu.setBi(bbsInfoDaoImp.query());
			btcu.setTheme(theme);
			btcu.setUser(u);
			if (ThemeUtil.powerCheck(btcu)){
				con=true;
			}else{
				con=false;
			}
			
		}
		if (con){
			String actUrl;
			actUrl=this.getText("lerx.bbsThreadPageFileName").trim();
			reUrl=actUrl+"?tid="+tid;
			reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
			
			bbsThemeDaoImp.top(tid, state);
//			if (state){
//				bbsThemeDaoImp.sink(theme, false);
//			}
			
			
			mes=getText("lerx.success.post");
		}else{
			reUrl = ruv.getWorkingUrl();
			mes=getText("lerx.fail.power");
		}
		
		
		re.setRefererUrl(reUrl);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String shield() throws IOException {
		curBbsStyle = bbsStyleDaoImp.findDef();
		String resultPageCode = curBbsStyle.getResultPageCode();
		String reUrl;
		RefererUrlVo ruv = new RefererUrlVo();
		ruv.setAs(this);
		ruv.setRequest(request);
		ruv=RefererUtil.init(ruv);
		
		ResultEl re=reInit(ruv.getRefererUrl(),1,resultPageCode);
		
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		BbsTheme theme=bbsThemeDaoImp.findThemeById(otid);
		uc = CookieUtil.query(cdm);
		String mes="";
		
		boolean con=true;
		if (uc==null){
			con=false;
			mes=getText("lerx.fail.auth");
			
		}else{
			User u = userDaoImp.findUserById(uc.getUserId());
			BbsThemeCheckUtil btcu=new BbsThemeCheckUtil();
			btcu.setBbsBMDaoImp(bbsBMDaoImp);
			btcu.setBbsForumDaoImp(bbsForumDaoImp);
			btcu.setBi(bbsInfoDaoImp.query());
			btcu.setTheme(theme);
			btcu.setUser(u);
			if (ThemeUtil.powerCheck(btcu)){
				con=true;
			}else{
				con=false;
			}
			
		}
		if (con){
			String actUrl;
			actUrl=this.getText("lerx.bbsThreadPageFileName").trim();
			reUrl=actUrl+"?tid="+tid;
			reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
			bbsThemeDaoImp.shield(otid, state);
			mes=getText("lerx.success.post");
		}else{
			reUrl = ruv.getWorkingUrl();
			mes=getText("lerx.fail.power");
		}
		re.setRefererUrl(reUrl);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String sink() throws IOException {
		curBbsStyle = bbsStyleDaoImp.findDef();
		String resultPageCode = curBbsStyle.getResultPageCode();
		String reUrl;
		RefererUrlVo ruv = new RefererUrlVo();
		ruv.setAs(this);
		ruv.setRequest(request);
		ruv=RefererUtil.init(ruv);
		
		ResultEl re=reInit(ruv.getRefererUrl(),1,resultPageCode);
		
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		BbsTheme theme=bbsThemeDaoImp.findThemeById(tid);
		uc = CookieUtil.query(cdm);
		String mes="";
		
		boolean con=true;
		if (uc==null){
			con=false;
			mes=getText("lerx.fail.auth");
			
		}else{
			User u = userDaoImp.findUserById(uc.getUserId());
			BbsThemeCheckUtil btcu=new BbsThemeCheckUtil();
			btcu.setBbsBMDaoImp(bbsBMDaoImp);
			btcu.setBbsForumDaoImp(bbsForumDaoImp);
			btcu.setBi(bbsInfoDaoImp.query());
			btcu.setTheme(theme);
			btcu.setUser(u);
			if (ThemeUtil.powerCheck(btcu)){
				con=true;
			}else{
				con=false;
			}
			
		}
		if (con){
			String actUrl;
			actUrl=this.getText("lerx.bbsThreadPageFileName").trim();
			reUrl=actUrl+"?tid="+tid;
			reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
			
			bbsThemeDaoImp.sink(theme, state);
			
			
			mes=getText("lerx.success.post");
		}else{
			reUrl = ruv.getWorkingUrl();
			mes=getText("lerx.fail.power");
		}
		
		
		re.setRefererUrl(reUrl);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}
	
	public String del() throws IOException{
		
		curBbsStyle = bbsStyleDaoImp.findDef();
		String resultPageCode = curBbsStyle.getResultPageCode();
		String reUrl;
		RefererUrlVo ruv = new RefererUrlVo();
		ruv.setAs(this);
		ruv.setRequest(request);
		ruv=RefererUtil.init(ruv);
		
		ResultEl re=reInit(ruv.getRefererUrl(),1,resultPageCode);
		
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		BbsTheme theme=bbsThemeDaoImp.findThemeById(otid);
		uc = CookieUtil.query(cdm);
		String mes="";
		
//		bbsInfoDaoImp.query();
		
		boolean con=true;
		if (uc==null){
			con=false;
			mes=getText("lerx.fail.auth");
			
		}else{
			User u = userDaoImp.findUserById(uc.getUserId());
			BbsThemeCheckUtil btcu=new BbsThemeCheckUtil();
			btcu.setBbsBMDaoImp(bbsBMDaoImp);
			btcu.setBbsForumDaoImp(bbsForumDaoImp);
			btcu.setBi(bbsInfoDaoImp.query());
			btcu.setTheme(theme);
			btcu.setUser(u);
			if (ThemeUtil.powerCheck(btcu)){
				con=true;
			}else{
				con=false;
			}
			
		}
		if (con){
			String actUrl;
			if (otid==tid){
				actUrl=this.getText("lerx.bbsForumPageFileName").trim();
				reUrl=actUrl+"?fid="+theme.getForum().getId();
				bbsThemeDaoImp.delBbsThemeByRootId(tid);
				bbsThemeDaoImp.delBbsThemeById(otid);
				
			}else{
				actUrl=this.getText("lerx.bbsThreadPageFileName").trim();
				reUrl=actUrl+"?tid="+tid;
				reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
				
				bbsThemeDaoImp.delBbsThemeById(otid);
			}
			
			mes=getText("lerx.success.post");
		}else{
			reUrl = ruv.getWorkingUrl();
			mes=getText("lerx.fail.power");
		}
		
		
		re.setRefererUrl(reUrl);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	public String post() throws IOException{
		String mes,reUrl=null,actUrl;
		long tarId=0;
//		System.out.println("--page:"+page+" --pageSize:"+pageSize);
		boolean con=true;
		boolean chg=false;
//		siteInfDaoImp.query();
		curBbsStyle = bbsStyleDaoImp.findDef();
		BbsInfo bi = bbsInfoDaoImp.query();
		String resultPageCode = curBbsStyle.getResultPageCode();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		String curIP = IpUtil.getRealRemotIP(request).trim();
		
		RefererUrlVo ruv = new RefererUrlVo();
		ruv.setAs(this);
		ruv.setRequest(request);
		ruv=RefererUtil.init(ruv);
		
		ResultEl re=reInit(ruv.getRefererUrl(),1,resultPageCode);
		
		boolean rep;
		if (getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")) {
			rep = true;
		} else {
			rep = false;
		}
		SiteInfo site = siteInfDaoImp.query();
		String filterWords = site.getFilterWords();
		if (filterWords == null) {
			filterWords = getText("lerx.msg.filterWords");
		}
		StrFilterUtil sfu;
		if (theme!=null){
			
			sfu=StringUtil.filterStr(theme.getBody(), filterWords, rep);
			
			if (theme.getTitle()!=null && theme.getTitle().trim().equals("*")){
				theme.setTitle(null);
			}
			
			if (sfu.isCon()){
				theme.setBody(sfu.getStr());
				sfu=StringUtil.filterStrWithHtml(theme.getTitle(), filterWords, rep);
			}
			
			if (sfu.isCon()){
				theme.setTitle(sfu.getStr());
			}
		}else{
			con=false;
			sfu=new StrFilterUtil();
			sfu.setCon(false);
		}
		
		
		if (con && sfu.isCon()){
			if (uc==null){
				con=false;
				mes=getText("lerx.fail.auth");
				
			}else{
				
				
				User u = userDaoImp.findUserById(uc.getUserId());
				boolean postAllow=true;
				if (!u.isState() || (bi.isPostMustInGroup() && u.getUserGroup()==null)){
					postAllow=false;
				}
				if (postAllow){
					if (theme.getId()==null || theme.getId()==0){	//新帖
						chg=false;
						if (secStr == null || secStr.trim().length() != 32 || randKey == null
								|| randKey.trim().length() != 6) {
							con = false;
						} else {

							try {
								String hostSecComStr;
								String secStrFromFile = SrvInf.readSecStr(request,
										getText("lerx.hostSecFile"));
								hostSecComStr = StringUtil.md5(secStrFromFile.concat(randKey))
										.toLowerCase();
								if (!hostSecComStr.trim().toLowerCase().equals(secStr)) {
									con = false;
								}
								if (bbsThemeDaoImp.findBySecCode(secStr)!=null){
									con=false;
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						BbsForum f = bbsForumDaoImp.findBbsForumById(fid);
						if (f.isAsClass()){
							mes=getText("lerx.fail.forum.err");
						}else{
							if (con){
//								System.out.println("测试点111");
								String bodyTmp=theme.getBody();
								bodyTmp=StringUtil.htmlFilter(bodyTmp);
								BbsTheme pbt=bbsThemeDaoImp.findThemeById(tid);
								
								
								
								boolean bodyNull=false;
								if (bodyTmp==null || bodyTmp.trim().equals("")){
									bodyNull=true;
								}
								if (!bodyNull){
//									System.out.println("测试点222");
									theme.setUser(u);
									Timestamp tnow=new Timestamp(System.currentTimeMillis());
									theme.setAddTimeUnix(tnow.getTime());
									theme.setAddTime(tnow);
									theme.setChgTime(tnow);
									if (fid>0){
										theme.setForum(bbsForumDaoImp.findBbsForumById(fid));
									}
									if (tid>0){
										theme.setRootTheme(pbt);
									}
									
									theme.setState(true);
									theme.setSink(false);
									theme.setShield(false);
									theme.setSecCode(secStr);
									theme.setLastEditIp(curIP);
									theme.setAddIp(curIP);
									if (theme.getRootTheme()!=null){
										theme.setTitle(null);
									}
									if (getText("lerx.poll.bbs.allow.default").trim().equalsIgnoreCase("true")){
										theme.setPollAllow(true);
									}else{
										theme.setPollAllow(false);
									}
									
									
									if (pbt!=null){			//如果是回复贴
//										System.out.println("测试点333");
										pbt=bbsThemeDaoImp.findThemeById(pbt.getId());
										pbt.setReps(pbt.getReps()+1);
										pbt.setChgTime(new Timestamp(System.currentTimeMillis()));
										if ((pbt.getUser().getId() - u.getId())!=0 && !bbsThemeDaoImp.findReplyer(tid, u.getId())){
//											System.out.println("测试点444");
											pbt.setExoticActors(pbt.getExoticActors()+1);	//记录外部参与人数
											
										}
										bbsThemeDaoImp.modifyBbsTheme(pbt);
									}
									
									tarId=bbsThemeDaoImp.addBbsTheme(theme);
									ScoreScheme sc=scoreSchemeDaoImp.findCurScoreScheme();
									int value;
									
									if (sc!=null){
										if (pbt==null){//主题帖
											value=sc.getValueOfNewTheme();
										}else{//回复帖
											
											value=sc.getValueOfReply();
										}
										u.setBbsScore(u.getBbsScore()+value);
										userDaoImp.modifyUser(u);
									}
									mes=getText("lerx.success.post");
								}else{
									con=false;
									mes=getText("lerx.fail.null.body");
								}
								
							}else{
								con=false;
								mes=getText("lerx.err.secStr.illegalOperation");
							}
						}
					}else{
						chg=true;		//修改
						BbsTheme tdb=bbsThemeDaoImp.findThemeById(theme.getId());
						tdb.setTitle(theme.getTitle());
						
						
						String bodyTmp=theme.getBody();
						bodyTmp=StringUtil.htmlFilter(bodyTmp);
						boolean bodyNull=false;
						if (bodyTmp==null || bodyTmp.trim().equals("")){
							bodyNull=true;
						}
						if (tdb.getRootTheme()==null){
							tarId=tdb.getId();
						}else{
							tarId=tdb.getRootTheme().getId();
						}
						if (!bodyNull){
							boolean chge=false;
							if (!tdb.getBody().equals(theme.getBody()) || tdb.isSeeAfterReply()!=theme.isSeeAfterReply()){
								chge=true;
							}
							if (tdb.getRootTheme()==null){
								tdb.setSeeAfterReply(theme.isSeeAfterReply());
								tarId=tdb.getId();
							}else{
								tdb.setSeeAfterReply(false);
								tarId=tdb.getRootTheme().getId();
							}
							if (chge){
								tdb.setBody(theme.getBody());
								
								tdb.setLastEditIp(curIP);
								tdb.setLastEditTime(new Timestamp(System.currentTimeMillis()));
								tdb.setLastEditUser(u);
								bbsThemeDaoImp.modifyBbsTheme(tdb);
							}
							
							mes=getText("lerx.success.post");
						}else{
							con=false;
							mes=getText("lerx.fail.null.body");
						}
						
					}
					
				}else{
					con=false;
					reUrl = ruv.getWorkingUrl();
					mes=getText("lerx.fail.auth");
				}
				
				if (con && tarId>0) {
					
					actUrl=this.getText("lerx.bbsThreadPageFileName").trim();
					if (theme.getRootTheme()==null){
						reUrl=actUrl+"?tid="+tarId;
						reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
					}else{
						
						reUrl=actUrl+"?tid="+theme.getRootTheme().getId();
						if (chg){
							reUrl+="&page="+page+"&pageSize="+pageSize+"&scrollPos="+scrollPos;
						}else{
							int pz=bi.getPageSizeOfTheme();
							if (pz==0){
								pz=10;
							}
							int p=bbsThemeDaoImp.pageCountByRootThemeId(tid, pz);
							reUrl+="&page="+p+"&pageSize="+pz+"&toEnd=true";
						}
						
						
					}
				} else {
					reUrl = ruv.getWorkingUrl();
				}
				
				
			}
		}else{
			reUrl = ruv.getWorkingUrl();
			con=false;
			mes = getText("lerx.fail.filter");
		}
		
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		
		re.setMes(mes);
		resultPageCode=ResultPage.init(re);
		if (reUrl==null){
			reUrl = ruv.getWorkingUrl();
		}
		re.setRefererUrl(reUrl);
		resultPageCode=ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
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
	 * 结果页面处理
	 */
//	private String resultPage(String mes,  int waitTimeMod) {
//		String waitResStr;
//		switch (waitTimeMod) {
//		case 1:
//			waitResStr = "waitingTimeForPageJumpLong";
//			break;
//		default:
//			waitResStr = "waitingTimeForPageJumpShort";
//			break;
//		}
//		String resultPageCode = curBbsStyle.getResultPageCode();
//		resultPageCode = StringUtil.strReplace(resultPageCode,
//				"{$$resultMsg$$}",  mes);
//
//		resultPageCode = StringUtil.strReplace(resultPageCode,
//				"{$$waitingTime$$}",
//				"" + Integer.valueOf(getText("lerx." + waitResStr)));
//		resultPageCode = StringUtil.strReplace(resultPageCode,
//				"{$$returnUrl$$}", refCheck());
//		return resultPageCode;
//	}
	
	/*
	 * 检查来源页
	 */
//	private String refCheck() {
//		String mes="lerx.default.url.return.bbs";
//		String ref = request.getHeader("Referer");
//		if (ref == null || ref.trim().equals("")) {
//			ref = getText(mes);
//
//		}
//		// System.out.println("ref:"+ref);
//		return ref;
//	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;

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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	

}
