package com.lerx.user.util;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.lerx.bbs.vo.ScoreGroup;
import com.lerx.bbs.vo.ScoreScheme;
import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserArtsCount;
import com.lerx.user.vo.UserInf;
import com.opensymphony.xwork2.ActionSupport;

public class UserUtil {
	
	
	public static String simpFormatUserDataLine(FormatElements el,long uid){
		String lf = el.getLf();
		User user=el.getUserDaoImp().findUserById(uid);
		lf = StringUtil.strReplace(lf, "{$$uid$$}", ""+user.getId());
		lf = StringUtil.strReplace(lf, "{$$username$$}", user.getUserName());
		lf = StringUtil.strReplace(lf, "{$$avatarFile$$}", user.getAvatarFile());
		lf = StringUtil.strReplace(lf, "{$$email$$}", user.getEmail());
		lf = StringUtil.strReplace(lf, "{$$remName$$}", user.getRemName());
		lf = StringUtil.strReplace(lf, "{$$allScore$$}", ""+user.getAllScore());
		lf = StringUtil.strReplace(lf, "{$$bbsScore$$}", ""+user.getBbsScore());
		// 论坛用户组
		if (el.getScoreGroupDaoImp() != null
				&& el.getScoreSchemeDaoImp() != null) {
			ScoreScheme sc = el.getScoreSchemeDaoImp().findCurScoreScheme();
			ScoreGroup sg = null;
			if (sc != null) {
				sg = el.getScoreGroupDaoImp().findScoreGroupByIdAndValue(
						sc.getId(), user.getBbsScore());
			}
			if (sg != null) {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}",
						sg.getGroupName());
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}",
						"" + user.getBbsScore());
			} else {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}", el.getAs()
						.getText("lerx.err.unknown"));
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}", el.getAs()
						.getText("lerx.err.unknown"));
			}

		}
		
//		lf = StringUtil.strReplace(lf, "{$$bbsScore$$}", ""+user.get);
		return lf;
	}
	
	
	public static String formatHref(FormatElements el, long uid) {

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				el.getAs().getText("lerx.default.format.date"));
		java.text.SimpleDateFormat formatterYear = new java.text.SimpleDateFormat(
				"yyyy");
		java.text.SimpleDateFormat formatterMonth = new java.text.SimpleDateFormat(
				"MM");
		java.text.SimpleDateFormat formatterDay = new java.text.SimpleDateFormat(
				"dd");

		String lf = el.getLf();
		String avatarFile;
		lf = StringUtil.strReplace(lf, "{$$uid$$}", "" + uid);
		UserInf u = el.getUserDaoImp().findUserInfById(uid);
		if (u.getAvatarFile() == null || u.getAvatarFile().trim().equals("")) {
			avatarFile = el.getAs().getText("lerx.noAvatarSmall");
		} else {
			avatarFile = u.getAvatarFile();
		}

		lf = StringUtil.strReplace(lf, "{$$username$$}",
				StringUtil.nullFilter(u.getUserName()));
		lf = StringUtil.strReplace(lf, "{$$avatarFile$$}",
				StringUtil.nullFilter(avatarFile));
		lf = StringUtil.strReplace(lf, "{$$petName$$}",
				StringUtil.nullFilter(u.getPetName()));
		lf = StringUtil.strReplace(lf, "{$$email$$}",
				StringUtil.nullFilter(u.getEmail()));
		lf = StringUtil.strReplace(lf, "{$$city$$}",
				StringUtil.nullFilter(u.getCity()));
		lf = StringUtil.strReplace(lf, "{$$country$$}",
				StringUtil.nullFilter(u.getCountry()));
		lf = StringUtil.strReplace(lf, "{$$province$$}",
				StringUtil.nullFilter(u.getProvince()));
		lf = StringUtil.strReplace(lf, "{$$dept$$}",
				StringUtil.nullFilter(u.getDept()));
		lf = StringUtil.strReplace(lf, "{$$address$$}",
				StringUtil.nullFilter(u.getAddress()));
		lf = StringUtil.strReplace(lf, "{$$mobile$$}",
				StringUtil.nullFilter(u.getMobile()));
		lf = StringUtil.strReplace(lf, "{$$msn$$}",
				StringUtil.nullFilter(u.getMsn()));
		lf = StringUtil.strReplace(lf, "{$$phone$$}",
				StringUtil.nullFilter(u.getPhone()));
		lf = StringUtil.strReplace(lf, "{$$qq$$}",
				StringUtil.nullFilter(u.getQq()));
		lf = StringUtil.strReplace(lf, "{$$question1$$}",
				StringUtil.nullFilter(u.getQuestion1()));
		lf = StringUtil.strReplace(lf, "{$$question2$$}",
				StringUtil.nullFilter(u.getQuestion2()));
		lf = StringUtil.strReplace(lf, "{$$answer1$$}",
				StringUtil.nullFilter(u.getAnswer1()));
		lf = StringUtil.strReplace(lf, "{$$answer2$$}",
				StringUtil.nullFilter(u.getAnswer2()));
		lf = StringUtil.strReplace(lf, "{$$trueName$$}",
				StringUtil.nullFilter(u.getTrueName()));

		if (u.getBirthday() != null) {
			lf = StringUtil.strReplace(lf, "{$$birthday$$}",
					formatter.format(u.getBirthday()));

			lf = StringUtil.strReplace(lf, "{$$birthdayYear$$}",
					formatterYear.format(u.getBirthday()));
			lf = StringUtil.strReplace(lf, "{$$birthdayMonth$$}",
					formatterMonth.format(u.getBirthday()));
			lf = StringUtil.strReplace(lf, "{$$birthdayDay$$}",
					formatterDay.format(u.getBirthday()));
		} else {
			lf = StringUtil.strReplace(lf, "{$$birthday$$}", "");

			lf = StringUtil.strReplace(lf, "{$$birthdayYear$$}", "");
			lf = StringUtil.strReplace(lf, "{$$birthdayMonth$$}", "");
			lf = StringUtil.strReplace(lf, "{$$birthdayDay$$}", "");
		}

		if (u.getSex() == null) {
			lf = StringUtil.strReplace(lf, "{$$male$$}", "");
			lf = StringUtil.strReplace(lf, "{$$female$$}", "");
			lf = StringUtil.strReplace(lf, "{$$secret$$}", "checked");
		} else {
			if (u.getSex()) {
				lf = StringUtil.strReplace(lf, "{$$male$$}", "checked");
				lf = StringUtil.strReplace(lf, "{$$female$$}", "");
				lf = StringUtil.strReplace(lf, "{$$secret$$}", "");
			} else {
				lf = StringUtil.strReplace(lf, "{$$male$$}", "");
				lf = StringUtil.strReplace(lf, "{$$female$$}", "checked");
				lf = StringUtil.strReplace(lf, "{$$secret$$}", "");
			}
		}

		// 论坛用户组
		if (el.getScoreGroupDaoImp() != null
				&& el.getScoreSchemeDaoImp() != null) {
			ScoreScheme sc = el.getScoreSchemeDaoImp().findCurScoreScheme();
			ScoreGroup sg = null;
			if (sc != null) {
				sg = el.getScoreGroupDaoImp().findScoreGroupByIdAndValue(
						sc.getId(), u.getBbsScore());
			}
			if (sg != null) {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}",
						sg.getGroupName());
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}",
						"" + u.getBbsScore());
			} else {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}", el.getAs()
						.getText("lerx.err.unknown"));
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}", el.getAs()
						.getText("lerx.err.unknown"));
			}

		}

		return lf;
	}

	public static UserInf nullFilter(UserInf u) {
		u.setAddress(StringUtil.nullFilter(u.getUserName()));
		u.setAnswer1(StringUtil.nullFilter(u.getAnswer1()));
		u.setAnswer2(StringUtil.nullFilter(u.getAnswer2()));
		u.setAvatarFile(StringUtil.nullFilter(u.getAvatarFile()));
		u.setCity(StringUtil.nullFilter(u.getCity()));
		u.setCountry(StringUtil.nullFilter(u.getCountry()));
		u.setDept(StringUtil.nullFilter(u.getDept()));
		u.setEmail(StringUtil.nullFilter(u.getEmail()));
		u.setMobile(StringUtil.nullFilter(u.getMobile()));
		u.setMsn(StringUtil.nullFilter(u.getMsn()));
		u.setPersonalSignature(StringUtil.nullFilter(u.getPersonalSignature()));
		u.setPetName(StringUtil.nullFilter(u.getPetName()));
		u.setPhone(StringUtil.nullFilter(u.getPhone()));
		u.setProvince(StringUtil.nullFilter(u.getProvince()));
		u.setQq(StringUtil.nullFilter(u.getQq()));
		u.setQuestion1(StringUtil.nullFilter(u.getQuestion1()));
		u.setQuestion2(StringUtil.nullFilter(u.getQuestion2()));
		u.setRemName(StringUtil.nullFilter(u.getRemName()));
		u.setTrueName(StringUtil.nullFilter(u.getTrueName()));
		u.setUserName(StringUtil.nullFilter(u.getUserName()));
		return u;
	}

	public static void uacModify(User u, String timeKeyStr, Timestamp addTime,
			IUserArtsCountDao userArtsCountDaoImp, int col, int v) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				timeKeyStr);
		int timeKey = Integer.valueOf(formatter.format(addTime));
		UserArtsCount uac = userArtsCountDaoImp.findByUK(u, timeKey);
		if (uac == null) {
			uac = new UserArtsCount();
			uac.setUser(u);
			uac.setTimeKey(timeKey);
			uac.setArticleThreadsCount(0);
			uac.setArticleThreadsPassed(0);

		}
		if (col == 1) {
			if (v == 1) {
				uac.setArticleThreadsPassed(uac.getArticleThreadsPassed() + 1);
			} else if (v == -1) {
				if (uac.getArticleThreadsPassed() > 0) {
					uac.setArticleThreadsPassed(uac.getArticleThreadsPassed() - 1);
				}
			}
		} else if (col == 0) {
			if (v == 1) {
				uac.setArticleThreadsCount(uac.getArticleThreadsCount() + 1);
			} else if (v == -1) {
				if (uac.getArticleThreadsCount() > 0) {
					uac.setArticleThreadsCount(uac.getArticleThreadsCount() - 1);
				}
			}
		}

		userArtsCountDaoImp.modify(uac);
	}

	public static boolean spaceAdminCheck(CookieDoModel cdm) throws IOException {
		boolean pwdMD5ToLowerCase;
		ActionSupport as = cdm.getActionSupport();
		if (as.getText("lerx.pwdMD5ToLowerCase").trim()
				.equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		IUserDao userDaoImp = cdm.getUserDaoImp();
		UserCookie uc;
		// cdm =CdmUtil.init(this, request, response, userDaoImp);
		uc = CookieUtil.query(cdm);
		if (uc != null
				&& userDaoImp.findUserByNameAndPassowrd(uc.getUsername(),
						uc.getPasswd(), pwdMD5ToLowerCase,true) != null) {
			User u = userDaoImp.findUserById(uc.getUserId());
			if (u.getSpaceState() == 2) {
				return true;
				// request.getSession().setAttribute("LerxSpaceAdmin", "true");
			} else {
				return false;
				// request.getSession().setAttribute("LerxSpaceAdmin", "false");
			}

		} else {
			return false;
		}
	}

	public static User check(TransferUserUtil tuu) {
		boolean pwdMD5ToLowerCase, con = true;
		ActionSupport as = tuu.getAs();
		if (as.getText("lerx.pwdMD5ToLowerCase").trim()
				.equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		IUserDao userDaoImp = tuu.getUserDaoImp();
		User user = tuu.getUser();
		User u, utmp;
		if (user.getUserName() != null && !user.getUserName().trim().equals("")) {
			user.setUserName(user.getUserName().toLowerCase());
		} else if (user.getId() != null) {
			utmp = userDaoImp.findUserById(user.getId());
			if (utmp != null) {
				user.setUserName(utmp.getUserName());
			} else {
				user.setUserName(null);
			}

		} else {
			user.setUserName(null);
			con = false;

		}
		if (user.getUserName() == null) {
			con = false;
		}
		boolean staCkeck=tuu.isStaCheck();
		
		if (con) {
			
			if (!tuu.isIgnorePws()){
				u = userDaoImp.findUserByNameAndPassowrd(user.getUserName(),
						user.getPassword(), pwdMD5ToLowerCase,staCkeck);
			}else{
				u = userDaoImp.findUserByName(user.getUserName());
			}
			
			if (u != null) {
				if (tuu.isW()) {
					u.setLastLoginIp(IpUtil.getRealRemotIP(tuu.getRequest()));
					u.setLastLoginTimstamp(new Timestamp(System
							.currentTimeMillis()));
					userDaoImp.modifyUser(u);
					// System.out.println("正在写入...");
					LogWrite.logWrite(tuu.getRequest(), "用户：" + u.getUserName()
							+ "登录成功。");
				}
				// System.out.println("猎取到...");
				con = true;
				
				/*
				 * 密码返回
				 * 0：置空白字符串
				 * 1: 原密码
				 * 2: 数据库密码
				 */
				switch (tuu.getPwsMode()) {
				case 1:
					u.setPassword(user.getPassword());
					break;
				case 2:
					break;
				default:
					u.setPassword("");
					break;

				}
				return u;

			} else {
//				System.out.println("9---------");
				con = false;
				return null;
			}
		} else {
//			System.out.println("7---------");
			return null;
		}

	}
	
	public static boolean check(String openID,IInterconnectionDao interconnectionDaoImp,IUserDao userDaoImp){
		User u = interconnectionDaoImp.findUserByOpenID(openID, 1);
//		if (u==null){
//			System.out.println("没找到互联记录"+openID);
//		}
		if (u!=null && userDaoImp.findUserById(u.getId())!=null){
			return true;
		}else{
//			System.out.println("没找到与互联记录相对应的记录");
			return false;
		}
	}
	
	public static String passingUrl(HttpServletRequest request,SiteInfo site,ActionSupport as,User u){
		String url = SrvInf.srvUrl(request,site.getHost(),
				Integer.valueOf(as.getText("lerx.serverPort")))
				+ "/passersList.action?nu.id=" + u.getId();
		return url;
	}

}
