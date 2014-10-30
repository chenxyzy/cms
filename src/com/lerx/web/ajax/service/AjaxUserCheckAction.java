package com.lerx.web.ajax.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONObject;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.util.IntegralRuleUtil;
import com.lerx.integral.rule.vo.DoModel;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.util.SiteUtil;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.site.vo.SiteStyleSubElementInCommon;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.SiteSecSetUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.util.PasserUtil;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserArtsCount;
import com.lerx.user.vo.UserGroup;
import com.lerx.user.vo.UserInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxUserCheckAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	private IUserArtsCountDao userArtsCountDaoImp;
	private IIntegralRuleDao integralRuleDaoImp;
	private IScoreGroupDao scoreGroupDaoImp;
	private IScoreSchemeDao scoreSchemeDaoImp;
	private IPasserDao passerDaoImp;
	private IBbsBMDao bbsBMDaoImp;
	private IBbsThemeDao bbsThemeDaoImp;
	private SiteInfo siteInfo;
	private SiteStyle curStyle;
	private String resultMsgStr;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String username;
	private String resultStr;
	private String email;
	private String location;
	private String gidStr;
	private int cookietime;
	private int gid;
	private int umode;
	private int smode;
	private int page;
	private int pageSize;
	private int offset;
	private CookieDoModel cdm;
	private int local;
	private String verifyCode;
	private String key;
	private boolean w;
	private int module;

	public void setBbsThemeDaoImp(IBbsThemeDao bbsThemeDaoImp) {
		this.bbsThemeDaoImp = bbsThemeDaoImp;
	}

	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}

	public void setScoreSchemeDaoImp(IScoreSchemeDao scoreSchemeDaoImp) {
		this.scoreSchemeDaoImp = scoreSchemeDaoImp;
	}

	public void setScoreGroupDaoImp(IScoreGroupDao scoreGroupDaoImp) {
		this.scoreGroupDaoImp = scoreGroupDaoImp;
	}

	public boolean isW() {
		return w;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCookietime() {
		return cookietime;
	}

	public void setCookietime(int cookietime) {
		this.cookietime = cookietime;
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

	public String getGidStr() {
		return gidStr;
	}

	public void setGidStr(String gidStr) {
		this.gidStr = gidStr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// @JSON(name="resultStr")
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
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

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}

	public void setUserArtsCountDaoImp(IUserArtsCountDao userArtsCountDaoImp) {
		this.userArtsCountDaoImp = userArtsCountDaoImp;
	}

	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
	}

	public void setPasserDaoImp(IPasserDao passerDaoImp) {
		this.passerDaoImp = passerDaoImp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public void area() throws IOException {
		boolean loged = false;
		siteInfo = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String areaCode;
		User u=null ;
		UserCookie uc;
		cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		

		if (uc != null) {
			TransferUserUtil tuu = new TransferUserUtil();
			User ut = new User();
			ut.setUserName(uc.getUsername());
			ut.setPassword(uc.getPasswd());
			ut.setId(uc.getUserId());
			tuu.setAs(this);
			tuu.setRequest(request);
			tuu.setUser(ut);
			tuu.setUserDaoImp(userDaoImp);
			tuu.setW(true);
			tuu.setPwsMode(2);
			tuu.setStaCheck(true);
			
			String openID=uc.getOpenID();
//			System.out.println("openIDopenIDopenID:"+openID);
			if (openID!=null && !openID.trim().equals("")){
				u=interconnectionDaoImp.findUserByOpenID(openID, 1);
				tuu.setUser(u);
				tuu.setIgnorePws(true);
				u = UserUtil.check(tuu);
				u = userDaoImp.findUserById(u.getId());
			}else{
				u = UserUtil.check(tuu);
				u = userDaoImp.findUserById(u.getId());
			}
			
//			if (u==null){
//				System.out.println("u为空了");
//			}
			Timestamp ta = u.getLastLoginTimstamp();
			Timestamp tb = new Timestamp(System.currentTimeMillis());
			// 积分处理
			DoModel dm = new DoModel();
			dm.setIntegralRuleDaoImp(integralRuleDaoImp);
			dm.setLocalPostion(1);
			int value = IntegralRuleUtil.value(dm, 2);
			int b = TimeUtil.covTimeToDateInt(tb);
			int a;
			if (ta == null) {
				a = 0;
			} else {
				a = TimeUtil.covTimeToDateInt(ta);
			}

			if (b > a && value > 0) {
				u.setAllScore(u.getAllScore() + value);
				u.setLastLoginTimstamp(tb);
				String ip = IpUtil.getRealRemotIP(request);
				u.setLastLoginIp(ip);
				userDaoImp.modifyUser(u);
			}

			loged = true;
		}

		SiteStyleSubElementInCommon element, elementPub;
		
		elementPub = curStyle.getPublicStyle();
		
		if (location != null) {
			location = location.trim().toLowerCase();
			if (location.equals("reg")) {
				element = curStyle.getRegStyle();
			} else if (location.equals("index")) {
				element = curStyle.getIndexStyle();
			} else if (location.equals("login")) {
				element = curStyle.getLoginStyle();
			} else if (location.equals("class")) {
				element = curStyle.getClassStyle();
			} else if (location.equals("nav")) {
				element = curStyle.getClassStyle();
			} else if (location.equals("thread")) {
				element = curStyle.getThreadStyle();
			} else {
				element = elementPub;
			}
		} else {
			element = elementPub;
		}
		switch (local) {
		case 1:
			areaCode = "";
			break;
		case 2:
			QaStyle qaStyle = qaStyleDaoImp.findDefault();
			if (loged) {
				if (qaStyle.getPublicStyle().getMemberPanelCodeForLogIn() != null
						&& !qaStyle.getPublicStyle()
								.getMemberPanelCodeForLogIn().trim().equals("")) {
					areaCode = qaStyle.getPublicStyle()
							.getMemberPanelCodeForLogIn();
				} else {
					if (element.getMemberPanelCodeForLogIn() != null
							&& !element.getMemberPanelCodeForLogIn().trim()
									.equals("")) {
						areaCode = element.getMemberPanelCodeForLogIn();
					} else {
						areaCode = elementPub.getMemberPanelCodeForLogIn();
					}
				}

			} else {
				if (qaStyle.getPublicStyle().getMemberPanelCodeForLogOut() != null
						&& !qaStyle.getPublicStyle()
								.getMemberPanelCodeForLogOut().trim()
								.equals("")) {
					areaCode = qaStyle.getPublicStyle()
							.getMemberPanelCodeForLogOut();
				} else {
					if (element.getMemberPanelCodeForLogIn() != null
							&& !element.getMemberPanelCodeForLogOut().trim()
									.equals("")) {
						areaCode = element.getMemberPanelCodeForLogOut();
					} else {
						areaCode = elementPub.getMemberPanelCodeForLogOut();
					}
				}
			}
			break;
		case 3:
			areaCode = "";
			break;
		default:
			if (loged) {
				if (element.getMemberPanelCodeForLogIn() != null
						&& !element.getMemberPanelCodeForLogIn().trim()
								.equals("")) {
					areaCode = element.getMemberPanelCodeForLogIn();
				} else {
					areaCode = elementPub.getMemberPanelCodeForLogIn();
				}
			} else {
				if (element.getMemberPanelCodeForLogOut() != null
						&& !element.getMemberPanelCodeForLogOut().trim()
								.equals("")) {
					areaCode = element.getMemberPanelCodeForLogOut();
				} else {
					areaCode = elementPub.getMemberPanelCodeForLogOut();
				}
			}
			break;
		}

		if (gid > 0) {
			areaCode = StringUtil.strReplace(areaCode, "{$$gid$$}", "" + gid);
		} else {
			if (gidStr == null || !StringUtil.isNumber(gidStr)) {
				areaCode = StringUtil.strReplace(areaCode, "{$$gid$$}", "0");
			} else {
				int d = Integer.valueOf(gidStr);
				areaCode = StringUtil.strReplace(areaCode, "{$$gid$$}", "" + d);
			}
		}

		if (loged) {
			areaCode = StringUtil.strReplace(areaCode, "{$$uid$$}",
					"" + uc.getUserId());
			UserGroup ug = userDaoImp.findUserById(uc.getUserId())
					.getUserGroup();
			if (ug != null && ug.getPrivateHtml() != null
					&& !ug.getPrivateHtml().trim().equals("")) {
				areaCode = StringUtil.strReplace(areaCode, "{$$privateHtml$}",
						ug.getPrivateHtml());
			} else {
				areaCode = StringUtil.strReplace(areaCode, "{$$privateHtml$}",
						"");
			}
			boolean task;
			if (u!=null){
				
				task=PasserUtil.chk(u, passerDaoImp, userDaoImp, 1, 5, false);
			}else{
				task=false;
			}
			
			if (task){
				String rootFolder;
				rootFolder=curStyle.getRootResFolder();
				ReadFileArg rfv=new ReadFileArg();
				rfv.setAs(this);
				rfv.setRequest(request);
				rfv.setRootFolder(rootFolder);
				rfv.setFileName("hrefPasser.txt");
				rfv.setSubFolder("act");
				
				String txt = FileUtil.readFile(rfv);
				txt = StringUtil.strReplace(txt, "{$$href$$}",
				request.getContextPath()+"/pass/passerCenter.jsp");
				areaCode = StringUtil.strReplace(areaCode, "{$$task$}",txt);
			}else{
				areaCode = StringUtil.strReplace(areaCode, "{$$task$}",
				"");
			}
		} else {
			areaCode = StringUtil.strReplace(areaCode, "{$$uid$$}", "0");
		}
		areaCode = StringUtil.strReplace(areaCode, "{$$contextPath$$}",
				request.getContextPath());
		if (!siteInfo.isState()) {
			areaCode = siteInfo.getCloseAnnounce();
		}
		// System.out.println("asdfasdf");
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(areaCode);

	}

	public void findByEmail() throws IOException {
		siteInfo = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		resultMsgStr = curStyle.getAjaxStrFormat();

		if (StringUtil.emailTest(email)) {
			if (siteInfo.isOneMailForReg()) {
				if (userDaoImp.findUserByEmail(email) == null) {
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultMsg$$}", getText("lerx.success.reg.allow"));
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultIcoUrl$$}", "{$$okIcoUrl$$}");
				} else {
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultMsg$$}", getText("lerx.fail.exists.email"));
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultIcoUrl$$}", "{$$failIcoUrl$$}");
				}
			} else {
				resultMsgStr = StringUtil.strReplace(resultMsgStr,
						"{$$resultMsg$$}", getText("lerx.success.reg.allow"));
				resultMsgStr = StringUtil.strReplace(resultMsgStr,
						"{$$resultIcoUrl$$}", "{$$okIcoUrl$$}");
			}
		} else {
			resultMsgStr = StringUtil.strReplace(resultMsgStr,
					"{$$resultMsg$$}", getText("lerx.err.format.email"));
			resultMsgStr = StringUtil.strReplace(resultMsgStr,
					"{$$resultIcoUrl$$}", "{$$failIcoUrl$$}");
		}
		if (!siteInfo.isState()) {
			resultMsgStr = siteInfo.getCloseAnnounce();
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(resultMsgStr);

	}

	public void findByName() throws IOException {
		try {
			siteInfo = siteInfDaoImp.query();
			curStyle = siteStyleDaoImp.findDef();
			resultMsgStr = curStyle.getAjaxStrFormat();
			username = username.trim().toLowerCase();
			int limitLength = Integer.valueOf(getText("lerx.rule.length.username"));
			String lengthErr = getText("lerx.fail.reg.nullOrLength");
			lengthErr += limitLength;
			if (username.length() < limitLength) {

				resultMsgStr = StringUtil.strReplace(resultMsgStr,
						"{$$resultMsg$$}", lengthErr);
				resultMsgStr = StringUtil.strReplace(resultMsgStr,
						"{$$resultIcoUrl$$}", "{$$failIcoUrl$$}");
			} else {
				if (userDaoImp.findUserByName(username) == null) {
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultMsg$$}", getText("lerx.success.reg.allow"));
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultIcoUrl$$}", "{$$okIcoUrl$$}");
				} else {
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultMsg$$}",
							getText("lerx.fail.exists.userName"));
					resultMsgStr = StringUtil.strReplace(resultMsgStr,
							"{$$resultIcoUrl$$}", "{$$failIcoUrl$$}");
				}
			}
			if (!siteInfo.isState()) {
				resultMsgStr = siteInfo.getCloseAnnounce();
			}
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset="
					+ getText("lerx.charset"));
			response.getWriter().write(resultMsgStr);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 登录
	 */
	public void login() throws IOException {
		String ip = IpUtil.getRealRemotIP(request);
		String from = "user";
		String outStr;
		boolean con = true;
		curStyle = siteStyleDaoImp.findDef();
		siteInfo=siteInfDaoImp.query();
		String sessionStr = from + "_" + getText("lerx.host.current") + "_"
				+ ip + "_random";
		if (getText("lerx.verifyOnFront").trim().equals("true")) {
			String randStr = (String) ActionContext.getContext().getSession()
					.get(sessionStr);
			if (randStr == null || verifyCode == null
					|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
				LogWrite.logWrite(request, "验证码错误 ！" + verifyCode);
				con = false;
			}
		}

		String url=null;
		// 可以用uid登录
		if (con) {
			
			SiteSecSetUtil sssu=new SiteSecSetUtil();
			sssu.setAs(this);
			sssu.setRequest(request);
			sssu.setSite(siteInfDaoImp.query());
			SiteUtil.secAuto(sssu);
			
			String failUrl=getText("lerx.url.fail.login").trim();
			boolean staCheck;
			if (failUrl.equalsIgnoreCase("null") || failUrl.equalsIgnoreCase("lerx.url.fail.login")){
				failUrl=null;
				staCheck=true;
			}else{
				staCheck=false;
			}
			
			TransferUserUtil tuu = new TransferUserUtil();
			tuu.setAs(this);
			tuu.setRequest(request);
			tuu.setUser(user);
			tuu.setUserDaoImp(userDaoImp);
			tuu.setW(true);
			tuu.setPwsMode(1);
			tuu.setStaCheck(staCheck);
			User u = UserUtil.check(tuu);

			if (u != null) {
				if (u.isState()){
					UserCookie uc = new UserCookie();

					uc.setUsername(user.getUserName());
					uc.setRemName(u.getRemName());
					uc.setPasswd(user.getPassword());
					uc.setUserId(u.getId());
					uc.setTime(cookietime);
					cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
					CookieUtil.save(cdm, uc);
					LogWrite.logWrite(request, "用户：" + u.getUserName() + "登录成功。");
					con = true;
				}else{
					url=UserUtil.passingUrl(request, siteInfo, this, u);
//					url = SrvInf.srvUrl(request,siteInfo.getHost(),
//							Integer.valueOf(getText("lerx.serverPort")))
//							+ "/passersList.action?nu.id=" + u.getId();
//					response.sendRedirect(url);
				}
				
			} else {
				LogWrite.logWrite(request, "找不到合法用户:" + user.getUserName()
						+ " 密码：" + user.getPassword() + "  ！");
				con = false;
			}
		}

		if (url==null){
			if (con) {
				outStr = "true";
			} else {
				outStr = "false";
			}
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset=" + getText("lerx.charset"));
			response.getWriter().write(outStr);
		}else{
			
			response.sendRedirect(url);
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public void userCheckUseJson() throws IOException {
		TransferUserUtil tuu = new TransferUserUtil();
		tuu.setAs(this);
		tuu.setRequest(request);
		tuu.setUser(user);
		tuu.setUserDaoImp(userDaoImp);
		tuu.setW(w);
		tuu.setPwsMode(1);
		User u = UserUtil.check(tuu);
		JSONObject obj = new JSONObject();
		if (u != null) {
			// u.setPassword(tuu.getUser().getPassword());
			obj.put("result", "success");
			obj.put("user", u);
			obj.put("userName", u.getUserName());
			obj.put("userGroup", u.getUserGroup().getGroupName());
			obj.put("userId", u.getId());
			obj.put("avatar", u.getAvatarFile());
			obj.put("remName", u.getRemName());
		} else {
			obj.put("result", "fail");
		}
		String outStr;
		outStr = obj.toJSONString();
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}

	public void loginChk() throws IOException {
		String outStr="";
		cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		UserCookie uc = CookieUtil.query(cdm);
		if (uc == null) {
			outStr = "false";
		} else {
			TransferUserUtil tuu = new TransferUserUtil();
			User ut=new User();
			ut.setUserName(uc.getUsername());
			ut.setPassword(uc.getPasswd());
			tuu.setAs(this);
			tuu.setRequest(request);
			tuu.setUser(ut);
			tuu.setUserDaoImp(userDaoImp);
			tuu.setW(w);
			tuu.setPwsMode(1);
			tuu.setStaCheck(true);
			
			String openID=uc.getOpenID();
			User u;
			if (openID!=null && !openID.trim().equals("")){
				u=interconnectionDaoImp.findUserByOpenID(openID, 1);
				tuu.setUser(u);
				tuu.setIgnorePws(true);
				u = UserUtil.check(tuu);
				u = userDaoImp.findUserById(u.getId());
			}else{
				u = UserUtil.check(tuu);
				u = userDaoImp.findUserById(u.getId());
			}
			
//			User u = UserUtil.check(tuu);
			if (u==null){
				outStr = "false";
			}else{
				outStr = "true";
			}
		}
		
//		
//		
//		boolean pwdMD5ToLowerCase;
//		
//		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
//			pwdMD5ToLowerCase = true;
//		} else {
//			pwdMD5ToLowerCase = false;
//		}
//		cdm = CdmUtil.init(this, request, response, userDaoImp);
//		UserCookie uc = CookieUtil.query(cdm);
//		if (uc == null) {
//			outStr = "false";
//		} else {
//			User u = userDaoImp.findUserByNameAndPassowrd(uc.getUsername(),
//					uc.getPasswd(), pwdMD5ToLowerCase);
//			if (!u.getUserGroup().isState() || u == null) {
//				outStr = "false";
//			} else {
//				outStr = "true";
//			}
//		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}
	
	public void find() throws IOException {
		
		String rootFolder;
		rootFolder=siteStyleDaoImp.findDef().getRootResFolder();
		ReadFileArg rfv=new ReadFileArg();
		rfv.setAs(this);
		rfv.setRequest(request);
		rfv.setRootFolder(rootFolder);
		rfv.setFileName("user.txt");
		rfv.setSubFolder("html");
		
		String txt = FileUtil.readFile(rfv);
//		
//		String txt = FileUtil.readConfigFile(this, request,
//				"user.txt","html");
		String outStr;
		FormatElements el=new FormatElements();
		el.setAs(this);
		el.setUserDaoImp(userDaoImp);
		el.setScoreGroupDaoImp(scoreGroupDaoImp);
		el.setScoreSchemeDaoImp(scoreSchemeDaoImp);
		el.setLf(txt);
		outStr=UserUtil.formatHref(el, user.getId());
		if (bbsBMDaoImp.chkIdcByUid(user.getId())){
			outStr = StringUtil.strReplace(outStr, "{$$isBm$$}", getText("lerx.bbs.bm.title"));
		}else{
			outStr = StringUtil.strReplace(outStr, "{$$isBm$$}", "");
		}
		
		cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		UserCookie uc = CookieUtil.query(cdm);
		User u=null;
		if (uc!=null){
			u = userDaoImp.findUserInfById(uc.getUserId());
		}
		
		if (u!=null && bbsBMDaoImp.chkIdcByUid(uc.getUserId())){
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					getText("lerx.default.format.date"));
			BbsTheme t=bbsThemeDaoImp.findLastByUid(uc.getUserId());
			if (t!=null){
				outStr = StringUtil.strReplace(outStr, "{$$lastPostTime$$}", formatter.format(t.getAddTime()));
			}else{
				outStr = StringUtil.strReplace(outStr, "{$$lastPostTime$$}", "");
			}
			
			outStr = StringUtil.strReplace(outStr, "{$$regDate$$}", formatter.format(u.getRegTimstamp()));
			outStr = StringUtil.strReplace(outStr, "{$$lastLoginTime$$}", formatter.format(u.getLastLoginTimstamp()));
			
			outStr = StringUtil.strReplace(outStr, "{$$regIP$$}", u.getRegIp());
			outStr = StringUtil.strReplace(outStr, "{$$lastIP$$}", u.getLastLoginIp());
		}else{
			outStr = StringUtil.strReplace(outStr, "{$$regDate$$}", "");
			outStr = StringUtil.strReplace(outStr, "{$$lastLoginTime$$}", "");
			outStr = StringUtil.strReplace(outStr, "{$$lastPostTime$$}", "");
			outStr = StringUtil.strReplace(outStr, "{$$regIP$$}", "");
			outStr = StringUtil.strReplace(outStr, "{$$lastIP$$}", "");
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}

	@SuppressWarnings("unchecked")
	public void findUsersArticlesNum() throws IOException {

		siteInfo = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		Rs rs;

		String strFormat = curStyle.getHrefLineFormatWithSnStrOverAll();
		String tmp, tmpAll = "";
		int step = 0;
		boolean byATime = false;
		int timeKey = TimeUtil.timeNum(key, offset);
		if (timeKey > 0 && key != null && !key.trim().equals("")
				&& key.trim().length() > 3) {
			byATime = true;
		}
		if (byATime) {
			rs = userArtsCountDaoImp.findTopByUKAndGroup(gid, timeKey, 0, page,
					pageSize);
			if (rs.getList().size() > 0) {
				UserInf u;
				List<UserArtsCount> l = (List<UserArtsCount>) rs.getList();
				for (UserArtsCount uac : l) {
					step++;
					tmp = strFormat;
					tmp = StringUtil.strReplace(tmp, "{$$alt$$}", "");

					tmp = StringUtil.strReplace(tmp, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmp = StringUtil.strReplace(tmp, "{$$sn0$$}", ""
							+ ((page - 1) * pageSize + (step - 1)));

					// tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
					// tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
					tmp = StringUtil.strReplace(tmp, "{$$href$$}",
							request.getContextPath()
									+ "/myArticles.action?uid="
									+ uac.getUser().getId());
					u = userDaoImp.findUserInfById(uac.getUser().getId());
					if (umode == 0) {
						tmp = StringUtil.strReplace(tmp, "{$$title$$}",
								u.getUserName());
					} else {
						if (u.getTrueName() == null
								|| u.getTrueName().trim().equals("")) {
							tmp = StringUtil.strReplace(tmp, "{$$title$$}",
									u.getUserName());
						} else {
							tmp = StringUtil.strReplace(tmp, "{$$title$$}",
									u.getTrueName());
						}
					}
					if (smode == 0) {
						tmp = StringUtil.strReplace(
								tmp,
								"{$$other$$}",
								"" + uac.getArticleThreadsPassed() + "	/	"
										+ u.getArticleThreadsPassed());
					} else {
						tmp = StringUtil.strReplace(
								tmp,
								"{$$other$$}",
								"" + uac.getArticleThreadsCount() + "	/	"
										+ u.getArticleThreadsCount());
					}

					tmpAll += tmp;

				}

			}
		} else {
			rs = userDaoImp.findUserInfByGroupOrderByThread(gid, page,
					pageSize, smode);
			if (rs.getList().size() > 0) {

				List<UserInf> l = (List<UserInf>) rs.getList();
				for (UserInf u : l) {
					step++;
					tmp = strFormat;
					tmp = StringUtil.strReplace(tmp, "{$$alt$$}", "");

					tmp = StringUtil.strReplace(tmp, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmp = StringUtil.strReplace(tmp, "{$$sn0$$}", ""
							+ ((page - 1) * pageSize + (step - 1)));

					// tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
					// tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
					tmp = StringUtil.strReplace(tmp, "{$$href$$}",
							request.getContextPath()
									+ "/myArticles.action?uid=" + u.getId());
					if (umode == 0) {
						tmp = StringUtil.strReplace(tmp, "{$$title$$}",
								u.getUserName());
					} else {
						if (u.getTrueName() == null
								|| u.getTrueName().trim().equals("")) {
							tmp = StringUtil.strReplace(tmp, "{$$title$$}",
									u.getUserName());
						} else {
							tmp = StringUtil.strReplace(tmp, "{$$title$$}",
									u.getTrueName());
						}
					}
					if (smode == 0) {
						tmp = StringUtil.strReplace(tmp, "{$$other$$}",
								"" + u.getArticleThreadsPassed());
					} else {
						tmp = StringUtil.strReplace(tmp, "{$$other$$}",
								"" + u.getArticleThreadsCount());
					}

					tmpAll += tmp;

				}

			}
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(tmpAll);

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	// private void initCdm() {
	// cdm = new CookieDoModel();
	// cdm.setActionSupport(this);
	// cdm.setEncodingCode(getText("lerx.charset").trim());
	// cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
	// cdm.setHost(getText("lerx.host.current"));
	// cdm.setHostSecFile(getText("lerx.hostSecFile"));
	// cdm.setRequest(request);
	// cdm.setResponse(response);
	// cdm.setUserDaoImp(userDaoImp);
	// }

}
