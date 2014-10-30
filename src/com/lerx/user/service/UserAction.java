package com.lerx.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.util.IntegralRuleUtil;
import com.lerx.integral.rule.vo.DoModel;
//import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.util.SiteUtil;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.MailSender;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.MaiCreateArg;
import com.lerx.sys.util.vo.Mail;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.SiteSecSetUtil;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.Passer;
import com.lerx.user.vo.PassingInf;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.user.vo.UserInf;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.RegFinishVo;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author lzh
 * 
 */
public class UserAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int page;
	private int pageSize;
	private User user;
	private UserInf userInf;
	private int groupId;
	private Rs rs;
	private int id;
	private long uid;
	private boolean state;
	private boolean pass;
	private String password;
	private int birthdayYear;
	private int birthdayMonth;
	private int birthdayDay;
	private IUserDao userDaoImp;
	private IPasserDao passerDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private IUserGroupDao userGroupDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IIntegralRuleDao integralRuleDaoImp;
	private int sex;
	private String pw1;
	private String pw2;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private String refererUrl;
	private String workingUrl;
	private SiteStyle curStyle;
	private SiteInfo site;
	private int cookietime;
	private String userName;
	private String uuid;
	private String salt;
	private String verifyCode;
	private String secStr;
	private String randKey;
	private int order;
	private int orderMod;
	private String findStr;
	private CookieDoModel cdm;
	private boolean spaceAdminCheck;

	private PassingInf pi;

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public PassingInf getPi() {
		return pi;
	}

	public void setPi(PassingInf pi) {
		this.pi = pi;
	}

	// private String inputStr;

	public String getVerifyCode() {
		return verifyCode;
	}

	public String getFindStr() {
		return findStr;
	}

	public void setFindStr(String findStr) {
		this.findStr = findStr;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrderMod() {
		return orderMod;
	}

	public void setOrderMod(int orderMod) {
		this.orderMod = orderMod;
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

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getWorkingUrl() {
		return workingUrl;
	}

	public void setWorkingUrl(String workingUrl) {
		this.workingUrl = workingUrl;
	}

	public String getRefererUrl() {
		return refererUrl;
	}

	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setUserGroupDaoImp(IUserGroupDao userGroupDaoImp) {
		this.userGroupDaoImp = userGroupDaoImp;
	}

	public void setPasserDaoImp(IPasserDao passerDaoImp) {
		this.passerDaoImp = passerDaoImp;
	}

	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
	}

	public String getPw1() {
		return pw1;
	}

	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}

	public String getPw2() {
		return pw2;
	}

	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(
			IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public int getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(int birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public int getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(int birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public int getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(int birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rs getRs() {
		return rs;
	}

	public void setRs(Rs rs) {
		this.rs = rs;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInf getUserInf() {
		return userInf;
	}

	public void setUserInf(UserInf userInf) {
		this.userInf = userInf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getCookietime() {
		return cookietime;
	}

	public void setCookietime(int cookietime) {
		this.cookietime = cookietime;
	}

	public boolean isSpaceAdminCheck() {
		return spaceAdminCheck;
	}

	public void setSpaceAdminCheck(boolean spaceAdminCheck) {
		this.spaceAdminCheck = spaceAdminCheck;
	}

	/*
	 * 注销
	 */
	public String logout() {
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		cdm = CdmUtil.init(this, request, response, userDaoImp,
				interconnectionDaoImp);
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		CookieUtil.clear(cdm);
		re.setMes(getText("lerx.success.logout"));
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		ActionContext.getContext().getSession().put("refererUrl", "");
		return SUCCESS;
	}

	/*
	 * 登录 还有一个ajax登录
	 */
	public String login() throws IOException {
		String ip = IpUtil.getRealRemotIP(request);
		String from;
		from = "user";
		
		
		SiteSecSetUtil sssu=new SiteSecSetUtil();
		sssu.setAs(this);
		sssu.setRequest(request);
		sssu.setSite(siteInfDaoImp.query());
		SiteUtil.secAuto(sssu);
		
//		if (spaceAdminCheck) {
//			from = "spaceadmin";
//		} else {
//			from = "user";
//		}

		// boolean pwdMD5ToLowerCase;
		// if
		// (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
		// pwdMD5ToLowerCase = true;
		// } else {
		// pwdMD5ToLowerCase = false;
		// }
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		String sessionStr = from + "_" + getText("lerx.host.current") + "_"
				+ ip + "_random";
		refererInit();
		ResultEl re = reInit(workingUrl, 0, resultPageCode);
		if (getText("lerx.verifyOnFront").trim().equals("true")) {
			String randStr = (String) ActionContext.getContext().getSession()
					.get(sessionStr);
			if (randStr == null || verifyCode == null
					|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
				this.addActionError(getText("lerx.err.verifyCode"));
				re.setMod(2);
				re.setMes(getText("lerx.err.verifyCode"));
				resultPageCode = ResultPage.init(re);
				request.setAttribute("lerxCmsBody", resultPageCode);
				return INPUT;
			}
		}

		String failUrl=getText("lerx.url.fail.login").trim();
		boolean staCheck;
		if (failUrl.equalsIgnoreCase("no") ||failUrl.equalsIgnoreCase("false") || failUrl.equalsIgnoreCase("null") || failUrl.equalsIgnoreCase("lerx.url.fail.login")){
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
		tuu.setPwsMode(2);
		
		tuu.setStaCheck(staCheck);
		User u = UserUtil.check(tuu);

		boolean con = true;
		if (u != null) {
			Timestamp ta = u.getLastLoginTimstamp();
			Timestamp tb = new Timestamp(System.currentTimeMillis());

			u.setLastLoginIp(IpUtil.getRealRemotIP(request));
			u.setLastLoginTimstamp(new Timestamp(System.currentTimeMillis()));

			// 积分处理
			if (!spaceAdminCheck) {
				DoModel dm = new DoModel();
				dm.setIntegralRuleDaoImp(integralRuleDaoImp);
				dm.setLocalPostion(1);
				int value = IntegralRuleUtil.value(dm, 2);
				int b = TimeUtil.covTimeToDateInt(tb);
				int a = 0;
				if (ta == null) {
					a = 0;
				} else {
					a = TimeUtil.covTimeToDateInt(ta);
				}

				if (a == 0 || (b > a && value > 0)) {
					u.setAllScore(u.getAllScore() + value);
				}

				userDaoImp.modifyUser(u);
			}

			UserCookie uc = new UserCookie();

			uc.setUsername(user.getUserName());
			uc.setRemName(u.getRemName());
			uc.setPasswd(user.getPassword());
			uc.setUserId(u.getId());
			uc.setTime(cookietime);
			cdm = CdmUtil.init(this, request, response, userDaoImp,
					interconnectionDaoImp);
			CookieUtil.save(cdm, uc);
			re.setMes(getText("lerx.success.login"));
			// System.out.println("refererUrl:"+refererUrl);
			re.setRefererUrl(refererUrl);
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			ActionContext.getContext().getSession().put("refererUrl", "");
			// LogWrite.logWrite(request, "用户：" + u.getUserName() +
			// "登录成功。");
			con = true;
		} else {
			con = false;
		}

		if (con) {
			return SUCCESS;
		} else {

			re.setMod(2);
			if (spaceAdminCheck) {
				this.addActionError(getText("lerx.fail.auth"));
			} else {
				if (u!=null && !staCheck){
					
					refererUrl=UserUtil.passingUrl(request, site, this, u);
					re.setMes(getText("lerx.fail.login.failWithPassingUrl"));
				}else{
					re.setMes(getText("lerx.fail.login.fail"));
				}
				re.setRefererUrl(refererUrl);
				
				resultPageCode = ResultPage.init(re);
				request.setAttribute("lerxCmsBody", resultPageCode);

			}
			return INPUT;
		}
	}

	/*
	 * 检测是否空间管理员
	 */

//	public String spaceAdminCheck() throws IOException {
//		boolean pwdMD5ToLowerCase;
//		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
//			pwdMD5ToLowerCase = true;
//		} else {
//			pwdMD5ToLowerCase = false;
//		}
//		UserCookie uc;
//		cdm = CdmUtil.init(this, request, response, userDaoImp,
//				interconnectionDaoImp);
//		uc = CookieUtil.query(cdm);
//		// System.out.println("----test");
//		if (uc != null
//				&& userDaoImp.findUserByNameAndPassowrd(uc.getUsername(),
//						uc.getPasswd(), pwdMD5ToLowerCase,true) != null) {
//			User u = userDaoImp.findUserById(uc.getUserId());
//			System.out.println("----user:" + u.getUserName());
//			if (u.getSpaceState() == 2) {
//				request.getSession().setAttribute("LerxSpaceAdmin", "true");
//			} else {
//				request.getSession().setAttribute("LerxSpaceAdmin", "false");
//			}
//
//		}
//		return SUCCESS;
//	}

	/*
	 * 列表
	 */
	public String findList() {
//		ActionContext.getContext().getValueStack().pop();
		int defaultPageSize = Integer
				.valueOf(getText("lerx.pageSize.result.default"));

		if (page < 1) {
			page = 1;
		}
		if (pageSize < 1) {
			pageSize = defaultPageSize;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		rs = userDaoImp.findUserByGroup(groupId, page, pageSize, order,
				orderMod, findStr);

		if (rs.getList() != null && !rs.getList().isEmpty()) {

			ActionContext.getContext().getValueStack().set("rs", rs);

		} else {

		}

		return SUCCESS;
	}

	/*
	 * 等待审核或已审核列表
	 */
	public String findListByPasser() throws IOException {
		int defaultPageSize = Integer
				.valueOf(getText("lerx.pageSize.result.default"));

		if (page < 1) {
			page = 1;
		}
		if (pageSize < 1) {
			pageSize = defaultPageSize;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}

		UserCookie uc;
		cdm = CdmUtil.init(this, request, response, userDaoImp,
				interconnectionDaoImp);
		uc = CookieUtil.query(cdm);

		rs = userDaoImp.findUserByPasserUid(uc.getUserId(), state, page,
				pageSize);

		if (rs.getList() != null && !rs.getList().isEmpty()) {
			rs.setPage(page);
			rs.setPageSize(pageSize);
			request.setAttribute("rs", rs);

		}
		return SUCCESS;
	}

	/*
	 * 增加 注册
	 */
	public String reg() throws UnsupportedEncodingException {

		boolean con = true;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();

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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		refererInit();

		if (!con) {
			ResultEl re = reInit(workingUrl, 1, resultPageCode);
			re.setMes(getText("lerx.err.host"));
			resultPageCode = ResultPage.init(re);

			request.setAttribute("lerxCmsBody", resultPageCode);
			return INPUT;
		}

		// String refererUrl=(String) request.getAttribute("refererUrl");

		int limitLength = Integer.valueOf(getText("lerx.rule.length.username"));
		ResultEl re = reInit(workingUrl, 1, resultPageCode);
		if (con && site.isOneMailForReg()
				&& userDaoImp.findUserByEmail(userInf.getEmail()) != null) {
			re.setMes(getText("lerx.fail.exists.email"));
			con = false;

		}

		if (con && !StringUtil.emailTest(userInf.getEmail())) {
			re.setMes(getText("lerx.err.format.email"));
			con = false;

		}

		if (con && !pw1.equals(pw2)) {
			re.setMes(getText("lerx.err.password.different"));
			con = false;

		}

		if (con && userInf.getUserName().trim().equals("")
				|| userInf.getUserName().length() < limitLength) {
			re.setMes(getText("lerx.fail.reg.nullOrLength"));
			con = false;

		}

		if (con
				&& userDaoImp.findUserByIpAndSaltOnSameDay(
						IpUtil.getRealRemotIP(request), randKey)) {
			// 同一天之内出现在同一IP和同一salt，认定为注册机、机器人
			re.setMes(getText("lerx.err.secStr.illegalOperation"));
			con = false;
		}

		if (con) {
			userInf.setPassword(pw1);
			userInf.setUuid(StringUtil.uuidStr());
			userInf.setRegIp(IpUtil.getRealRemotIP(request));
			userInf.setRegTimstamp(new Timestamp(System.currentTimeMillis()));
			userInf.setUserName(userInf.getUserName().toLowerCase());
			userInf.setSalt(randKey);
			userInf.setRemName("");

			// 积分处理
			DoModel dm = new DoModel();
			dm.setIntegralRuleDaoImp(integralRuleDaoImp);
			dm.setLocalPostion(1);
			int value = IntegralRuleUtil.value(dm, 1);
			if (value > 0) {
				userInf.setAllScore(value);
			}
			userInf = userDaoImp.addUser(userInf, pwdMD5ToLowerCase);
			if (userInf != null) {
				LogWrite.logWrite(request, "用户：" + userInf.getUserName()
						+ "注册成功。");

				con = true;
			} else {
				con = false;
				re.setMes("database err!");
			}
		}
		if (con) {
			re.setMod(0);
			RegFinishVo rfv = new RegFinishVo();
			rfv.setAs(this);
			rfv.setRe(re);
			rfv.setRefererUrl(refererUrl);
			rfv.setSite(site);
			rfv.setUserGroupDaoImp(userGroupDaoImp);
			rfv.setUserInf(userInf);
			rfv.setRequest(request);
			rfv.setUserDaoImp(userDaoImp);
			rfv.setRootFolder(siteStyleDaoImp.findDef().getRootResFolder());
			re = ResultPage.regFinish(rfv);


			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			ActionContext.getContext().getSession().put("refererUrl", "");
			return SUCCESS;
		} else {
			re.setMod(2);
			// re.setMes(getText("lerx.fail.userAdd"));
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			return INPUT;
		}

	}

	public String passManually() throws IOException {
		UserCookie uc;
		cdm = CdmUtil.init(this, request, response, userDaoImp,
				interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		User user = userDaoImp.findUserById(uid);

		if (uc != null && uc.getUserId() == user.getPasser().getId()) {
			Passer passer = passerDaoImp.find(userDaoImp.findUserById(uc
					.getUserId()));
			UserInf u = userDaoImp.findUserInfById(user.getId());
			if (pass) {
				if (u.getPriTag1()==null || u.getPriTag1().trim().equals("")){
					if (passer.getPriTag1()==null || passer.getPriTag1().trim().equals("") || passer.getPriTag1().trim().equals("?")){
						u.setPriTag1(pi.getPriTag1());
					}else{
						u.setPriTag1(passer.getPriTag1());
					}
					
				}
				if (u.getPriTag2()==null || u.getPriTag2().trim().equals("")){
					if (passer.getPriTag2()==null || passer.getPriTag2().trim().equals("") || passer.getPriTag2().trim().equals("?")){
						u.setPriTag2(pi.getPriTag2());
					}else{
						u.setPriTag2(passer.getPriTag2());
					}
					
				}
//				u.setPriTag1(passer.getPriTag1());
//				u.setPriTag1(passer.getPriTag2());
				u.setState(true);
				u.setUserGroup(passer.getToUG());
				u.setPassingTime(new Timestamp(System.currentTimeMillis()));

			} else {
				u.setState(false);
				u.setUserGroup(null);
				u.setPassingTime(null);

			}
			userDaoImp.modifyUser(u);
			return SUCCESS;
		} else {
			return SUCCESS;
		}

	}

	/*
	 * 认认2
	 */
	public String passByAnswers() {
		boolean con = false, pass = false;
		String ip = IpUtil.getRealRemotIP(request);
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		String from = "pass";
		String sessionStr = from + "_" + getText("lerx.host.current") + "_"
				+ ip + "_random";
		refererInit();
		ResultEl re = reInit(workingUrl, 1, resultPageCode);
		re.setRefererUrl(workingUrl);
		if (getText("lerx.verifyOnFront").trim().equals("true")) {
			String randStr = (String) ActionContext.getContext().getSession()
					.get(sessionStr);
			if (randStr == null || verifyCode == null
					|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
				this.addActionError(getText("lerx.err.verifyCode"));

				refererUrl = SrvInf.srvUrl(request, site.getHost(),
						Integer.valueOf(getText("lerx.serverPort")))
						+ "/passersList.action?nu.id=" + pi.getUserId();
				re.setRefererUrl(refererUrl);
				re.setMes(getText("lerx.err.verifyCode"));

				re.setMod(2);

				resultPageCode = ResultPage.init(re);
				request.setAttribute("lerxCmsBody", resultPageCode);
				return INPUT;
			}
		}
		Passer passer = passerDaoImp.findById(pi.getPasserId());
		user = userDaoImp.findUserById(pi.getUserId());
		// System.out.println(user.getUserName());
		// System.out.println(passer.getAnswer1());
		UserInf u = userDaoImp.findUserInfById(user.getId());
		UserGroup ug = null;
		if (passer != null && user != null) {
			con = true;
			if (pi.getAnswer1() == null || pi.getAnswer2() == null) {
				pass = false;
			} else {

				if (pi.getAnswer1().trim()
						.equalsIgnoreCase(passer.getAnswer1().trim())
						&& (pi.getAnswer2().trim().equalsIgnoreCase(passer
								.getAnswer2()))) {
					pass = true;
				} else {
					pass = false;
				}
			}

		}
		if (pi.getAnswer1() != null && !pi.getAnswer1().trim().equals("")
				&& pi.getAnswer2() != null
				&& !pi.getAnswer2().trim().equals("") && !pass) {
			// 重新选择填
			con = false;
		}

		if (con && pass) {
			ug = passer.getToUG();
			if (ug == null) {
				// System.out.println("22222");
				con = false;
			}
		}

		if (passer.getPriTag1() != null
				&& !passer.getPriTag1().trim().equals("")
				&& !passer.getPriTag1().trim().equals("?")) {
			
			u.setPriTag1(passer.getPriTag1());
		} else {
			if (passer.getPriTag1() == null) {
				con = false;
			} else {
				if (passer.getPriTag1().trim().equals("?")) {
					u.setPriTag1(pi.getPriTag1());
				}
			}

		}

		if (passer.getPriTag2() != null
				&& !passer.getPriTag2().trim().equals("")
				&& !passer.getPriTag2().trim().equals("?")) {
			u.setPriTag2(passer.getPriTag2());
		} else {
			if (passer.getPriTag2() == null) {
				con = false;
			} else {
				if (passer.getPriTag2().trim().equals("?")) {
					u.setPriTag2(pi.getPriTag2());
				}
			}

		}
		
		if (con) {
			if (pass) {
				u.setState(true);
				u.setUserGroup(ug);
				u.setPassingTime(new Timestamp(System.currentTimeMillis()));
				
			}else{
			}
			
		}else{
		}
		
		if (con) {
			u.setPasser(userDaoImp.findUserById(passer.getUser().getId()));
			userDaoImp.modifyUser(u);

			if (pass) { // 已经审核了
				re.setMes(getText("lerx.success.user.passed"));
				re.setMod(2);
			} else { // 下一步审核
				re.setMes(getText("lerx.msg.pass.waitForManually"));
				re.setMod(1);
			}

			re.setRefererUrl(ResultPage.defRURL(this, request));

			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);

			return SUCCESS;
		} else {
			refererUrl = SrvInf.srvUrl(request, site.getHost(),
					Integer.valueOf(getText("lerx.serverPort")))
					+ "/passersList.action?nu.id=" + pi.getUserId();
			re.setRefererUrl(refererUrl);
			re.setMod(2);
			re.setMes(getText("lerx.fail.auth"));
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			return ERROR;
		}

	}

	/*
	 * 认证1
	 */
	public String pass() throws UnsupportedEncodingException {
		// userName = java.net.URLDecoder.decode(userName,
		// getText("lerx.charset"));
		User u = userDaoImp.findUserByUuid(uuid);
		User u2 = userDaoImp.findUserById(uid);
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();

		refererInit();

		ResultEl re = reInit(workingUrl, 1, resultPageCode);
		re.setRefererUrl(refererUrl);

		if (u == null) {
			re.setRefererUrl(ResultPage.defRURL(this, request));
			re.setMes(getText("lerx.fail.auth"));
			re.setMod(2);
			resultPageCode = ResultPage.init(re);
			request.setAttribute("lerxCmsBody", resultPageCode);
			return ERROR;
		} else {
			if (u.isState()) {
				re.setRefererUrl(ResultPage.defRURL(this, request));
				re.setMes(getText("lerx.err.auth.repeat"));
				resultPageCode = ResultPage.init(re);
				re.setMod(2);
				request.setAttribute("lerxCmsBody", resultPageCode);
				return ERROR;
			} else {

				if (u.getUuid().trim().equals(u2.getUuid().trim())) {
					UserGroup g = userGroupDaoImp.findUserGroupByID(site
							.getUserGroupOfAutoPassed());
					u.setUserGroup(g);
					u.setState(true);
					// if (u.getAllScore())
					userDaoImp.modifyUser(u);
					LogWrite.logWrite(request, "用户：" + u.getUserName()
							+ "认证成功。");
					re.setMod(0);
					re.setRefererUrl(ResultPage.defRURL(this, request));
					re.setMes(getText("lerx.success.auth"));
					resultPageCode = ResultPage.init(re);
					request.setAttribute("lerxCmsBody", resultPageCode);
					ActionContext.getContext().getSession()
							.put("refererUrl", "");

					return SUCCESS;
				} else {
					re.setRefererUrl(ResultPage.defRURL(this, request));
					re.setMod(2);
					re.setMes(getText("lerx.fail.auth"));
					resultPageCode = ResultPage.init(re);
					request.setAttribute("lerxCmsBody", resultPageCode);
					return ERROR;
				}
			}
		}
	}

	/*
	 * 快速增加
	 */
	public String addQuickly() {
		site = siteInfDaoImp.query();
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		if (checkAdmin()) {
			String defaultUserPassword = getText("lerx.default.user.password")
					.trim();

			String defaultUserState = getText("lerx.default.user.state").trim()
					.toLowerCase();
			String defaultUserEmail = getText("lerx.default.user.email").trim()
					.toLowerCase();
			userInf.setEmail(defaultUserEmail);
			userInf.setPassword(defaultUserPassword);
			userInf.setUserName(userInf.getUserName().toLowerCase());
			userInf.setRegIp(IpUtil.getRealRemotIP(request));
			userInf.setRegTimstamp(new Timestamp(System.currentTimeMillis()));
			userInf.setUuid(StringUtil.uuidStr());
			userInf.setRemName("");
			if (defaultUserState.equals("enabled")) {
				userInf.setState(true);
			} else {
				userInf.setState(false);
			}

			if (TimeUtil.testCreateDate(getText("lerx.default.format.date"),
					Integer.valueOf(getText("lerx.dateParsePosition").trim()),
					birthdayYear, birthdayMonth, birthdayDay)) {
				userInf.setBirthday(TimeUtil.createDate(birthdayYear,
						birthdayMonth, birthdayDay));
			} else {
			}
			userInf = UserUtil.nullFilter(userInf);
			// 积分处理
			DoModel dm = new DoModel();
			dm.setIntegralRuleDaoImp(integralRuleDaoImp);
			dm.setLocalPostion(1);
			int value = IntegralRuleUtil.value(dm, 1);
			if (value > 0) {
				userInf.setAllScore(value);
			}

			if (userDaoImp.addUser(userInf, pwdMD5ToLowerCase) != null) {
				LogWrite.logWrite(request, "后台快速增加用户：" + userInf.getUserName()
						+ "成功。");
				findList();
				return SUCCESS;
			} else {
				this.addActionError(getText("lerx.fail.userAdd"));
				findList();
				return INPUT;
			}
		} else {
			return INPUT;
		}

	}

	/*
	 * 删除
	 */
	public String del() {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			boolean userDeleteCascadeThread;
			if (getText("lerx.rule.cascade.thread.delete").trim()
					.equalsIgnoreCase("true")) {
				userDeleteCascadeThread = true;
			} else {
				userDeleteCascadeThread = false;
			}

			if (userDeleteCascadeThread) {
				articleThreadDaoImp.delByUserId(id);
			} else {
				articleThreadDaoImp.changThreadUserToNull(id);
			}

			user=userDaoImp.findUserById(id);
			if (user!=null){
				interconnectionDaoImp.clear(user, 1);
			}
			
			userDaoImp.delUserById(id);
			LogWrite.logWrite(request, "后台删除用户<id：" + id + ">成功。");
			findList();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	/*
	 * 改变状态
	 */
	public String changeState() {

		userDaoImp.setState(id, state);
		LogWrite.logWrite(request, "后台改变用户状态 " + state + " <id：" + id + ">成功。");
		findList();
		return SUCCESS;
	}

	/*
	 * 按id查找登录用户
	 */
	public String findById() {

		try {

			ActionContext.getContext().getValueStack()
					.set("user", userDaoImp.findUserById(id));
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/*
	 * 按id查找用户详情
	 */
	public String findInfById() {

		try {

			UserInf u = userDaoImp.findUserInfById(id);
			if (u.getBirthday() != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(u.getBirthday());
				this.setBirthdayYear(c.get(Calendar.YEAR));
				this.setBirthdayMonth(c.get(Calendar.MONTH) + 1);
				this.setBirthdayDay(c.get(Calendar.DAY_OF_MONTH));
			}
			ActionContext.getContext().getValueStack().set("user", u);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/*
	 * 用户中心
	 */
	public String userModify() {
		boolean pwChange = false;
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		refererInit();
		ResultEl re = reInit(refererUrl, 1, resultPageCode);
		UserInf userInfFromDb = userDaoImp.findUserInfById(userInf.getId());

		userInf.setSalt(userInfFromDb.getSalt());
		userInf.setUuid(userInfFromDb.getUuid());
		userInf.setArticleThreadsCount(userInfFromDb.getArticleThreadsCount());
		userInf.setArticleThreadsPassed(userInfFromDb.getArticleThreadsPassed());
		userInf.setRemName(userInfFromDb.getRemName());
		userInf.setState(userInfFromDb.isState());
		userInf.setRegIp(userInfFromDb.getRegIp());
		userInf.setAllScore(userInfFromDb.getAllScore());
		userInf.setBbsScore(userInfFromDb.getBbsScore());
		userInf.setPriTag1(userInfFromDb.getPriTag1());
		userInf.setPriTag2(userInfFromDb.getPriTag2());
		userInf.setPasser(userInfFromDb.getPasser());

		if (getText("lerx.userTrueNameModifyMustByAdmin").trim()
				.equalsIgnoreCase("true")
				&& userInfFromDb.getTrueName() != null
				&& !userInfFromDb.getTrueName().trim().equals("")) {
			userInf.setTrueName(userInfFromDb.getTrueName());
		}
		userInf.setRegTimstamp(userInfFromDb.getRegTimstamp());
		if (TimeUtil.testCreateDate(getText("lerx.default.format.date"),
				Integer.valueOf(getText("lerx.dateParsePosition").trim()),
				birthdayYear, birthdayMonth, birthdayDay)) {
			userInf.setBirthday(TimeUtil.createDate(birthdayYear,
					birthdayMonth, birthdayDay));
		} else {
			userInf.setBirthday(userInfFromDb.getBirthday());
		}
		if (pw1 != null) {
			password = pw1;
		}
		if (password == null || password.trim().equals("")) {
			userInf.setPassword(userInfFromDb.getPassword());
		} else {
			pwChange = true;
			String passwordMd5;
			if (pwdMD5ToLowerCase) {
				passwordMd5 = StringUtil.md5(
						StringUtil.md5(password).toLowerCase()
								.concat(userInf.getSalt())).toLowerCase();
			} else {
				passwordMd5 = StringUtil.md5(StringUtil.md5(password).concat(
						userInf.getSalt()));
			}

			userInf.setPassword(passwordMd5);

			// String safeUserSessionStr = getText(
			// "lerx.sessionPrefixOfUserAuthentication").trim();
			// safeUserSessionStr = safeUserSessionStr.replace("servername",
			// getText("lerx.host.current"));
			cdm = CdmUtil.init(this, request, response, userDaoImp,
					interconnectionDaoImp);
			CookieUtil.clear(cdm);
			// ActionContext.getContext().getSession().put(safeUserSessionStr,
			// "");
		}

		switch (sex) {
		case 0:
			userInf.setSex(null);
			break;
		case 1:
			userInf.setSex(true);
			break;
		case 2:
			userInf.setSex(false);
			break;
		}
		userInf.setUserGroup(userInfFromDb.getUserGroup());
		userInf = UserUtil.nullFilter(userInf);
		userDaoImp.modifyUserInf(userInf);
		LogWrite.logWrite(request, "用户 " + userInf.getUserName() + " 修改资料成功。 "
				+ " <id：" + userInf.getId() + ">");
		re.setMod(0);
		if (pwChange) {
			
			re.setMes(getText("lerx.success.modify.reLogin"));
			resultPageCode = ResultPage.init(re);
			// resultPageCode=resultPage(resultPageCode,refererUrl,getText("lerx.success.modify.reLogin"),0,1);
		} else {
			re.setMes(getText("lerx.success.modify"));
			resultPageCode = ResultPage.init(re);
			// resultPageCode=resultPage(resultPageCode,refererUrl,getText("lerx.success.modify"),0,1);
		}
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	/*
	 * 修改用户
	 */
	public String modify() {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		site = siteInfDaoImp.query();
		UserInf userInfFromDb = userDaoImp.findUserInfById(userInf.getId());

		userInf.setSalt(userInfFromDb.getSalt());
		userInf.setUuid(userInfFromDb.getUuid());
		userInf.setArticleThreadsCount(userInfFromDb.getArticleThreadsCount());
		userInf.setArticleThreadsPassed(userInfFromDb.getArticleThreadsPassed());
		userInf.setRemName(userInfFromDb.getRemName());
		userInf.setRegIp(userInfFromDb.getRegIp());
		userInf.setRegTimstamp(userInfFromDb.getRegTimstamp());
		if (userInf.getPriTag1()==null || userInf.getPriTag1().trim().equals("")){
			userInf.setPriTag1(userInfFromDb.getPriTag1());
		}
		if (userInf.getPriTag2()==null || userInf.getPriTag2().trim().equals("")){
			userInf.setPriTag2(userInfFromDb.getPriTag1());
		}
//		userInf.setPriTag2(userInfFromDb.getPriTag2());
		userInf.setPasser(userInfFromDb.getPasser());
		userInf.setBbsScore(userInfFromDb.getBbsScore());
		userInf.setAvatarFileLock(userInfFromDb.isAvatarFileLock());

		if (!checkAdmin()) {
			userInf.setState(userInfFromDb.isState());
		}

		if (TimeUtil.testCreateDate(getText("lerx.default.format.date"),
				Integer.valueOf(getText("lerx.dateParsePosition").trim()),
				birthdayYear, birthdayMonth, birthdayDay)) {
			userInf.setBirthday(TimeUtil.createDate(birthdayYear,
					birthdayMonth, birthdayDay));
		} else {
			userInf.setBirthday(userInfFromDb.getBirthday());
		}
		if (password.trim().equals("")) {
			userInf.setPassword(userInfFromDb.getPassword());
		} else {
			String passwordMd5;
			if (pwdMD5ToLowerCase) {
				passwordMd5 = StringUtil.md5(
						StringUtil.md5(password).toLowerCase()
								.concat(userInf.getSalt())).toLowerCase();
			} else {
				passwordMd5 = StringUtil.md5(StringUtil.md5(password).concat(
						userInf.getSalt()));
			}

			userInf.setPassword(passwordMd5);
		}

		switch (sex) {
		case 0:
			userInf.setSex(null);
			break;
		case 1:
			userInf.setSex(true);
			break;
		case 2:
			userInf.setSex(false);
			break;
		}

		if (userInf.getUserGroup().getId() == 0) {
			userInf.setUserGroup(null);
		}
		userInf = UserUtil.nullFilter(userInf);
		userDaoImp.modifyUserInf(userInf);
		LogWrite.logWrite(request, "后台修改用户 " + userInf.getUserName()
				+ " 资料成功。 " + " <id：" + userInf.getId() + ">");
		findList();
		return SUCCESS;
	}

	public String retakeCode() {
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		User u = userDaoImp.findUserByName(user.getUserName());
		if (u.getUuid() == null || u.getUuid().trim().equals("")) {
			u.setUuid(StringUtil.uuidStr());
			userDaoImp.modifyUser(u);
		}
		boolean con = false;
		refererInit();
		ResultEl re = reInit(workingUrl, 1, resultPageCode);
		if (u != null
				&& u.getEmail().trim().equalsIgnoreCase(user.getEmail().trim())) {
			con = true;
		} else {
			con = false;
		}
		if (con) {
			
			MaiCreateArg mca= new MaiCreateArg();
			String rootFolder=curStyle.getRootResFolder();
			mca.setAs(this);
			mca.setMod(1);
			mca.setQn(null);
			mca.setRequest(request);
			mca.setSite(site);
			mca.setRootFolder(rootFolder);
			mca.setSta(1);
			mca.setFileName("pwsRetake.txt");
			Mail mail = MailSender.mailInit(mca);
			String body = mail.getBody();
			
			String shortSiteName;
			if (site.getShortSiteName() == null
					|| site.getShortSiteName().trim().equals("")) {
				shortSiteName = site.getFullSiteName().trim();
			} else {
				shortSiteName = site.getShortSiteName().trim();
			}
			body = StringUtil.strReplace(body, "{$$site$$}", shortSiteName);
			body = StringUtil.strReplace(body, "{$$siteUrl$$}",
					StringUtil.nullFilter(site.getHost()));
			body = StringUtil.strReplace(
					body,
					"{$$pwsRetakeUrl$$}",
					SrvInf.srvUrl(request, site.getHost(),
							Integer.valueOf(getText("lerx.serverPort")))
							+ "/pwsRetake.jsp?uid="
							+ u.getId()
							+ "&uuid="
							+ u.getUuid() + "&salt=" + u.getSalt());

			mail.setBody(body);
			mail.setToMail(u.getEmail());
			if (Mail.send()) {
				re.setMod(0);
				re.setMes(getText("lerx.success.retake"));
			} else {
				re.setMod(2);
				re.setMes(getText("lerx.fail.mail.send"));
			}
		} else {
			re.setMod(2);
			re.setMes(getText("lerx.err.retake.password.input"));

		}

		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		ActionContext.getContext().getSession().put("refererUrl", "");
		return SUCCESS;
	}

	public String retakePws() {
		site = siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String resultPageCode = curStyle.getResultPageCode();
		User u = userDaoImp.findUserById(uid);
		boolean con = false;
		refererInit();
		String returnUrl = SrvInf.srvUrl(request, site.getHost(),
				Integer.valueOf(getText("lerx.serverPort")))
				+ "/login.action";
		ResultEl re = reInit(returnUrl, 1, resultPageCode);
		if (u != null && uuid != null && salt != null) {
			if (u.getUuid().trim().equals(uuid.trim())
					&& u.getSalt().trim().equals(salt.trim())) {
				con = true;
			} else {
				re.setMes(getText("lerx.err.secStr.illegalOperation"));
			}
		} else {
			re.setMes(getText("lerx.err.secStr.illegalOperation"));
		}
		if (con) {
			if (pw1.equals(pw2)) {
				re.setMod(0);
				con = true;
			} else {
				con = false;
				re.setMod(2);
				re.setMes(getText("lerx.err.password.different"));
			}
		}
		if (con) {
			boolean pwdMD5ToLowerCase;
			if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase(
					"true")) {
				pwdMD5ToLowerCase = true;
			} else {
				pwdMD5ToLowerCase = false;
			}
			String randKey = StringUtil.randomString(6).toLowerCase();
			u.setSalt(randKey);
			String passwordMd5;
			if (pwdMD5ToLowerCase) {
				passwordMd5 = StringUtil.md5(
						StringUtil.md5(pw1).toLowerCase().concat(u.getSalt()))
						.toLowerCase();
			} else {
				passwordMd5 = StringUtil.md5(StringUtil.md5(pw1).concat(
						u.getSalt()));
			}

			u.setPassword(passwordMd5);
			userDaoImp.modifyUser(u);
			re.setMes(getText("lerx.success.all"));
		}else{
			re.setMod(2);
		}
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		ActionContext.getContext().getSession().put("refererUrl", "");
		return SUCCESS;
	}

	/*
	 * 初始化来源页和工作页
	 */
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}

	// private void initCdm() {
	// cdm =CdmUtil.init(this, request, response, userDaoImp);
	// // cdm = new CookieDoModel();
	// // cdm.setActionSupport(this);
	// // cdm.setEncodingCode(getText("lerx.charset").trim());
	// // cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
	// // cdm.setHost(getText("lerx.host.current"));
	// // cdm.setHostSecFile(getText("lerx.hostSecFile"));
	// // cdm.setRequest(request);
	// // cdm.setResponse(response);
	// // cdm.setUserDaoImp(userDaoImp);
	// // cdm.setAc(ActionContext.getContext());
	// }

}
