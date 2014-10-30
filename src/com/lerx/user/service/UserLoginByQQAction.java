package com.lerx.user.service;

import java.io.IOException;
import java.sql.Timestamp;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.util.IntegralRuleUtil;
import com.lerx.integral.rule.vo.DoModel;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.util.SiteUtil;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.SiteSecSetUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.QQUserInf;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserInf;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.RegFinishVo;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginByQQAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IIntegralRuleDao integralRuleDaoImp;
	private IUserGroupDao userGroupDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private String code;
	private String state;
	private UserInf user;
	private QQUserInf qui;
	private String openID;
	private String verifyCode;
	private String refererUrl;
	private String workingUrl;
	private String f;
	private long uid;
	
	
	
	public long getUid() {
		return uid;
	}


	public void setUid(long uid) {
		this.uid = uid;
	}


	public String getF() {
		return f;
	}


	public void setF(String f) {
		this.f = f;
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


	public String getVerifyCode() {
		return verifyCode;
	}


	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}


	public void setUserGroupDaoImp(IUserGroupDao userGroupDaoImp) {
		this.userGroupDaoImp = userGroupDaoImp;
	}


	public String getOpenID() {
		return openID;
	}


	public void setOpenID(String openID) {
		this.openID = openID;
	}


	public QQUserInf getQui() {
		return qui;
	}


	public void setQui(QQUserInf qui) {
		this.qui = qui;
	}


	public UserInf getUser() {
		return user;
	}


	public void setUser(UserInf user) {
		this.user = user;
	}


	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}


	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}


	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
	}


	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void send() throws IOException, QQConnectException {
		String refererUrl;
		refererUrl = (String) ActionContext.getContext().getSession()
		.get("refererUrl");
		if (refererUrl == null || refererUrl.trim().equals("")
				|| refererUrl.trim().equals("null")) {

			if (request.getHeader("Referer") == null) {
				refererUrl = ResultPage.defRURL(this, request);

			} else {
				refererUrl = request.getHeader("Referer");
			}

		}
		ActionContext.getContext().getSession().put("refererUrl", refererUrl);
		
		response.sendRedirect(new Oauth().getAuthorizeURL(request));
	}
	
	
	public String add() throws IOException{
		SiteStyle curStyle=siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		
//		refererInit();
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		refererUrl = (String) ActionContext.getContext().getSession()
		.get("refererUrl");
		
//		System.out.println("1--:"+refererUrl);
		
		String defaultUserState = getText("lerx.default.user.state").trim()
		.toLowerCase();
		boolean pwdMD5ToLowerCase,con=true;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		if (user==null || user.getUserName()==null || user.getUserName().trim().equals("")){
			user.setUserName(null);
			con=false;
		}else{
			user.setUserName(user.getUserName().trim().toLowerCase());
		}
		
		if (con){
			if (userDaoImp.findUserByName(user.getUserName())!=null){
				con=false;
			}
		}
		
		if (con){
			user.setRegIp(IpUtil.getRealRemotIP(request));
			user.setRegTimstamp(new Timestamp(System.currentTimeMillis()));
			user.setUuid(StringUtil.uuidStr());
			SiteInfo site = siteInfDaoImp.query();
			
			if (site.getActAfterReg()>0){
				user.setState(false);
			}else{
				if (defaultUserState.equals("enabled")) {
					user.setState(true);
				} else {
					user.setState(false);
				}
			}
			
			user.setPassword("");
			user.setUserName(user.getUserName().trim().toLowerCase());
			user=userDaoImp.add(user, pwdMD5ToLowerCase);
			user=userDaoImp.findUserInfById(user.getId());

			interconnectionDaoImp.create(user, 1, openID);
			UserCookie uc = new UserCookie();

			uc.setUsername(user.getUserName());
			uc.setRemName(user.getRemName());
			uc.setPasswd(user.getPassword());
			uc.setUserId(user.getId());
			
			uc.setOpenID(openID);
			uc.setTime(0);
			CookieDoModel cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
			CookieUtil.save(cdm, uc);
			
			RegFinishVo rfv=new RegFinishVo();
			rfv.setAs(this);
			rfv.setRe(re);
			rfv.setRefererUrl(ResultPage.defRURL(this, request));
			rfv.setSite(site);
			rfv.setUserGroupDaoImp(userGroupDaoImp);
			rfv.setUserInf(user);
			rfv.setRequest(request);
			rfv.setUserDaoImp(userDaoImp);
			rfv.setRootFolder(siteStyleDaoImp.findDef().getRootResFolder());
			re=ResultPage.regFinish(rfv);
			ActionContext.getContext().getSession().put("refererUrl", "");
			re.setRefererUrl(ResultPage.defRURL(this, request));
			re.setMod(0);
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			
			return SUCCESS;
			
		}else{
			re.setMod(2);
			re.setMes(getText("lerx.fail.exists.userName"));
		}
		
		
//		re.setRefererUrl(refererUrl);
		re.setRefererUrl(ResultPage.defRURL(this, request));
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
		
		
	}
	
	public String bind() throws IOException{
		SiteStyle curStyle=siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		
		refererUrl = (String) ActionContext.getContext().getSession()
		.get("refererUrl");
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		
		String from="user";
		String ip = IpUtil.getRealRemotIP(request);
		boolean con=true;
		String sessionStr = from + "_" + getText("lerx.host.current") + "_"
		+ ip + "_random";
		
		if (getText("lerx.verifyOnFront").trim().equals("true")) {
			String randStr = (String) ActionContext.getContext().getSession()
					.get(sessionStr);
			if (randStr == null || verifyCode == null
					|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
				this.addActionError(getText("lerx.err.verifyCode"));
				con=false;
				
			}
		}
		
		if (con){
			
			user.setUserName(user.getUserName().trim().toLowerCase());
			TransferUserUtil tuu = new TransferUserUtil();
			tuu.setAs(this);
			tuu.setRequest(request);
			tuu.setUser(user);
			tuu.setUserDaoImp(userDaoImp);
			tuu.setW(false);
			tuu.setPwsMode(2);
			tuu.setIgnorePws(false);
			User u = UserUtil.check(tuu);
			if (u!=null){
				interconnectionDaoImp.create(u, 1, openID);
				
				UserCookie uc = new UserCookie();

				uc.setUsername(u.getUserName());
				uc.setRemName(u.getRemName());
				uc.setPasswd(u.getPassword());
				uc.setUserId(u.getId());
				
				uc.setOpenID(openID);
				uc.setTime(0);
				CookieDoModel cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
				CookieUtil.save(cdm, uc);
				re.setMod(0);
				re.setMes(getText("lerx.success.auth"));
				
			}else{
				re.setMod(2);
				re.setMes(getText("lerx.fail.login.fail"));
				
			}
			
//			
//			String refererUrl;
//			refererUrl = (String) ActionContext.getContext().getSession()
//			.get("refererUrl");
//			response.sendRedirect(refererUrl);
			
		}else{
			re.setMod(2);
			this.addActionError(getText("lerx.err.verifyCode"));
			re.setMes(getText("lerx.err.verifyCode"));
			
		}
		
		
		re.setRefererUrl(refererUrl);
		
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
		
		
		
	}
	
	public String login() throws IOException {
		
		String refererUrl;
		SiteStyle curStyle=siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		
//		refererInit();
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		refererUrl = (String) ActionContext.getContext().getSession()
		.get("refererUrl");
		response.setContentType("text/html; charset=utf-8");
		qui = null;
		try {
			// MultiPartRequestWrapper req = (MultiPartRequestWrapper) request;
			AccessToken accessTokenObj = (new Oauth())
					.getAccessTokenByRequest(request);

			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;
			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				System.out.print("没有获取到响应参数");
			} else {
				qui = new QQUserInf();
				accessToken = accessTokenObj.getAccessToken();
				tokenExpireIn = accessTokenObj.getExpireIn();

				request.getSession().setAttribute("demo_access_token",
						accessToken);
				request.getSession().setAttribute("demo_token_expirein",
						String.valueOf(tokenExpireIn));

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				qui.setOpenID(openID);
				request.getSession().setAttribute("demo_openid", openID);

				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				if (userInfoBean.getRet() == 0) {
					qui.setNickname(userInfoBean.getNickname());
					qui.setGender(userInfoBean.getGender());
					qui.setLevel(userInfoBean.getLevel());
					qui.setVip(userInfoBean.isVip());
					qui.setYellowYearVip(userInfoBean.isYellowYearVip());
					qui.setAvatarURL30(userInfoBean.getAvatar()
							.getAvatarURL30());
					qui.setAvatarURL50(userInfoBean.getAvatar()
							.getAvatarURL50());
					qui.setAvatarURL100(userInfoBean.getAvatar()
							.getAvatarURL100());

				} else {
					qui.setMsg(userInfoBean.getMsg());
				}
				com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(
						accessToken, openID);
				com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo
						.getUserInfo();
				if (weiboUserInfoBean.getRet() == 0) {
					qui.setWeiboAvatarURL30(weiboUserInfoBean.getAvatar()
							.getAvatarURL30());
					qui.setWeiboAvatarURL50(weiboUserInfoBean.getAvatar()
							.getAvatarURL50());
					qui.setWeiboAvatarURL100(weiboUserInfoBean.getAvatar()
							.getAvatarURL100());
					qui.setWeiboBirthday(weiboUserInfoBean.getBirthday());

					qui.setWeiboCountryCode(weiboUserInfoBean.getCountryCode());
					qui.setWeiboProvinceCode(weiboUserInfoBean
							.getProvinceCode());
					qui.setWeiboCityCode(weiboUserInfoBean.getCityCode());
					qui.setWeiboLocation(weiboUserInfoBean.getLocation());


				} else {
					qui.setWeiboMsg(weiboUserInfoBean.getMsg());
					// out.println("很抱歉，我们没能正确获取到您的信息，原因是： " +
					// weiboUserInfoBean.getMsg());
				}
				
				/*
				 * 验证用户登录
				 */
				
				SiteSecSetUtil sssu=new SiteSecSetUtil();
				sssu.setAs(this);
				sssu.setRequest(request);
				sssu.setSite(siteInfDaoImp.query());
				SiteUtil.secAuto(sssu);
				
				User u=interconnectionDaoImp.findUserByOpenID(openID, 1);
				
				if (u!=null){				//如果发现互联记录
					u=userDaoImp.findUserById(u.getId());
					TransferUserUtil tuu = new TransferUserUtil();
					tuu.setAs(this);
					tuu.setRequest(request);
					tuu.setUser(u);
					tuu.setUserDaoImp(userDaoImp);
					tuu.setW(true);
					tuu.setPwsMode(2);
					tuu.setIgnorePws(true);
					u = UserUtil.check(tuu);
					if (u==null){
						return ERROR;
					}
					Timestamp ta=u.getLastLoginTimstamp();
					Timestamp tb=new Timestamp(System.currentTimeMillis());
					
					u.setLastLoginIp(IpUtil.getRealRemotIP(request));
					u.setLastLoginTimstamp(new Timestamp(System.currentTimeMillis()));
					
					//积分处理
					DoModel dm=new DoModel();
					dm.setIntegralRuleDaoImp(integralRuleDaoImp);
					dm.setLocalPostion(1);
					int value=IntegralRuleUtil.value(dm, 2);
					int b=TimeUtil.covTimeToDateInt(tb);
					int a=0;
					if (ta==null){
						a=0;
					}else{
						a=TimeUtil.covTimeToDateInt(ta);
					}
					
					if (a==0 || (b>a && value>0)){
						u.setAllScore(u.getAllScore()+value);
					}
					if (!u.isAvatarFileLock()){
						u.setAvatarFile(avatar(qui));
					}
					
					
					userDaoImp.modifyUser(u);
					
					UserCookie uc = new UserCookie();

					uc.setUsername(u.getUserName());
					uc.setRemName(u.getRemName());
					uc.setPasswd(u.getPassword());
					uc.setUserId(u.getId());
					
					uc.setOpenID(qui.getOpenID());
					uc.setTime(0);
					CookieDoModel cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
					CookieUtil.save(cdm, uc);
					re.setMod(0);
					re.setMes(getText("lerx.success.auth"));
					response.sendRedirect(refererUrl);
					return null;
					
					
				}else{			//如果没有互联记录
					
					
					request.setAttribute("qui", qui);
					ActionContext.getContext().getValueStack().set("qui", qui);
					return INPUT;
				}

			}
			re.setMod(2);
			re.setMes(getText("lerx.fail.all"));
			
			
			
			
		} catch (QQConnectException e) {
			re.setMod(2);
			re.setMes(getText("lerx.fail.all"));
		}
		
		
		re.setRefererUrl(refererUrl);
		
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
		
	}
	
	public String clear() throws IOException{
		CookieDoModel cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		UserCookie uc = CookieUtil.query(cdm);
		boolean self=false;
		if (uc!=null && uc.getUserId()==user.getId()){
			self=true;
		}
		if (checkAdmin() || self){
			interconnectionDaoImp.clear(user, 1);
		}
		
		if (checkAdmin() && !f.trim().equals("fore")){
			return INPUT;
		}else{
			SiteStyle curStyle=siteStyleDaoImp.findDef();
			String resultPageCode = curStyle.getResultPageCode();
			ResultEl re = reInit(workingUrl, 0, resultPageCode);
			response.setContentType("text/html; charset=utf-8");
			re.setRefererUrl(ResultPage.defRURL(this, request));
			re.setMes(getText("lerx.success.modify"));
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			return SUCCESS;
		}
		
		
	}
	
	public void chk(){
//		System.out.println("gggg:");
		if (interconnectionDaoImp.findUserByUid(uid, 1)!=null){
//			System.out.println("aaa");
			request.setAttribute("qq_ic", "yes");
			ActionContext.getContext().getValueStack().set("qq_ic", "yes");
		}else{
			request.setAttribute("qq_ic", "no");
			ActionContext.getContext().getValueStack().set("qq_ic", "no");
		}
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
				// System.out.println("workingUrl 1:"+workingUrl);
			} else {
				workingUrl = request.getHeader("Referer");
				// System.out.println("workingUrl 2:"+workingUrl);
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
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
	
	private String avatar(QQUserInf qui){
		String avF=qui.getAvatarURL100();
		if (avF==null || avF.trim().equals("")){
			avF=qui.getAvatarURL50();
			if (avF==null || avF.trim().equals("")){
				avF=qui.getAvatarURL30();
			}
		}
		return avF;
		
	}
	
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}

}
