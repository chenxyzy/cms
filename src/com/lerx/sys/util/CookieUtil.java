package com.lerx.sys.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.SecFilePosition;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.sys.util.vo.UserInfPrefix;
import com.lerx.user.util.UserUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class CookieUtil {
	
	public static String query(CookieDoModel cdm,String k){
		String n, v=null;
		if (cdm.getRequest().getSession().getAttribute(k)!=null){
			v=(String) cdm.getRequest().getSession().getAttribute(k);
//			System.out.println("测试点0");
			if (v!=null){
				if (!v.trim().equals("")){
//					System.out.println("测试点1");
					return v;
				}
				
			}
		}
		
		String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
		Cookie[] cookies = cdm.getRequest().getCookies();
		Cookie c;
		
		if (cookies != null && cookies.length > 0) {

			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
				if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
					c.setPath("/");
					c.setDomain("."+cookieDomain);
				}
//				System.out.println("0--c.getName():"+c.getName()+"  c.getDomain():"+c.getDomain());
				try {
					n = c.getName();
					
					if (n.trim().equals(k)) {
						v = java.net.URLDecoder.decode(c.getValue(),
								cdm.getEncodingCode());
						if (v!=null && !v.trim().equals("")){
//							System.out.println("测试点2");
							return v;
						}
						
					}
				} catch (UnsupportedEncodingException e) {
					continue;
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			
		
		}else{
			return null;
		}
		if (v!=null && v.trim().equals("")){
			v=null;
		}
//		System.out.println("测试点3");
		return v;
	}

	public static UserCookie query(CookieDoModel cdm) throws IOException {
		boolean pwdMD5ToLowerCase;
		if (cdm.getActionSupport().getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		
		String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
		
		UserInfPrefix uip= prefixInit(cdm);
		SecFilePosition sfp = new SecFilePosition();
		sfp.setRequest(cdm.getRequest());
		sfp.setSecFileName(cdm.getHostSecFile());
		
		Cookie[] cookies = cdm.getRequest().getCookies();
		
		Cookie c;
		String n, v;
		UserCookie uc1 = new UserCookie();
		UserCookie uc2 = new UserCookie();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
				if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
					c.setPath("/");
					c.setDomain("."+cookieDomain);
				}
//				System.out.println("1--c.getName():"+c.getName()+"  c.getDomain():"+c.getDomain());
				try {
					n = c.getName();
					v = java.net.URLDecoder.decode(c.getValue(),
							cdm.getEncodingCode());
					if (n.trim().equals(uip.getUsernamePre())) {
						uc1.setUsername(v);
					}
					if (n.trim().equals(uip.getRemNamePre())) {
						uc1.setRemName(v);
					}
					if (n.trim().equals(uip.getPasswordPre())) {
						uc1.setPasswd(v);
					}
					if (n.trim().equals(uip.getOpenIDPre())) {
						uc1.setOpenID(v);
					}
					
					if (n.trim().equals(uip.getUidPre())) {
						uc1.setUserId(Long.valueOf(v));
						// uc.setPasswd(v);
					}
				} catch (UnsupportedEncodingException e) {
					uc1 = null;
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}

		}
		
		if (uc1 != null) {
			if ((uc1.getOpenID()==null || uc1.getOpenID().trim().equals("")) && (uc1.getUserId() == 0 || uc1.getUsername() == null
					|| uc1.getUsername().trim().equals(""))) {
				uc1 = null;
			}
		}
		
		UserCookie uc;
		//从session中取值
		uc2 = findUCFromSession(cdm,uip);
//		if (uc2==null){
//			System.out.println("为空了");
//		}
		int tag=0;
		
		if (uc2!=null){
			tag=1;
		}
		
		if (uc2==null && uc1!=null){
			tag=2;
		}
		
		if (tag==1){
			uc=uc2;
		}else{
			uc=uc1;
		}
		
		if (uc==null){
//			System.out.println("uc空的--"+cdm.getRequest().getQueryString());
			clear(cdm);
			return null;
		}else{
//			System.out.println("uc啊是有的");
			/*
			 * 这个地方放过了在线编辑器，因为那个编辑器利用struts2标签会产生问题
			 * 当然也可以写一个action进行认证，利用ajax认证返回一个结果，再在编辑器中进行认证
			 */
			//
			
			if (uc.getOpenID()==null){
				if (cdm.getUserDaoImp()!=null && cdm.getUserDaoImp().findUserByNameAndPassowrd(DesUtils.decrypt(uc.getUsername(), sfp), DesUtils.decrypt(uc.getPasswd(), sfp),pwdMD5ToLowerCase,true)==null){
//					System.out.println("准备清除aaaaa");
					clear(cdm);
					return null;
				}
			}else{
//				System.out.println("---888uc.getOpenID():"+DesUtils.decrypt(uc.getOpenID(),sfp));
				if (!UserUtil.check(DesUtils.decrypt(uc.getOpenID(),sfp), cdm.getInterconnectionDaoImp(), cdm.getUserDaoImp())){
//					System.out.println("准备清除bbbbb");
					clear(cdm);
					return null;
				}
			}
			
			
			String username,password,remName,openID;
			username=uc.getUsername();
			remName=uc.getRemName();
			password=uc.getPasswd();
			openID=uc.getOpenID();
			
			uc.setUsername(DesUtils.decrypt(username, sfp));
			uc.setRemName(DesUtils.decrypt(remName, sfp));
			if (password!=null && !password.trim().equals("")){
				uc.setPasswd(DesUtils.decrypt(password, sfp));
			}
			if (openID!=null && !openID.trim().equals("")){
				uc.setOpenID(DesUtils.decrypt(openID, sfp));
			}
			
			return uc;
		}
		
	}
	
	public static UserCookie queryNoAc(CookieDoModel cdm) throws IOException {
		boolean pwdMD5ToLowerCase;
		if (findResource("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		
		
		UserInfPrefix uip= prefixInit(cdm);
		SecFilePosition sfp = new SecFilePosition();
		sfp.setRequest(cdm.getRequest());
		sfp.setSecFileName(cdm.getHostSecFile());
		
		Cookie[] cookies = cdm.getRequest().getCookies();
		Cookie c;
		String n, v;
		UserCookie uc1 = new UserCookie();
		UserCookie uc2 = new UserCookie();
		String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
		
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
//				System.out.println("2--c.getName():"+c.getName()+"  c.getDomain():"+c.getDomain());
				
				if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
					c.setPath("/");
					c.setDomain("."+cookieDomain);
				}
				try {
					n = c.getName();
					v = java.net.URLDecoder.decode(c.getValue(),
							cdm.getEncodingCode());
					if (n.trim().equals(uip.getUsernamePre())) {
						uc1.setUsername(v);
					}
					if (n.trim().equals(uip.getRemNamePre())) {
						uc1.setRemName(v);
					}
					if (n.trim().equals(uip.getPasswordPre())) {
						uc1.setPasswd(v);
					}
					if (n.trim().equals(uip.getUidPre())) {
						uc1.setUserId(Long.valueOf(v));
						// uc.setPasswd(v);
					}
					if (n.trim().equals(uip.getOpenIDPre())) {
						uc1.setOpenID(v);
					
					}
				} catch (UnsupportedEncodingException e) {
					uc1 = null;
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}

		}
		
		if (uc1 != null) {
			if (uc1.getUserId() == 0 || uc1.getUsername() == null
					|| uc1.getUsername().trim().equals("")) {
				uc1 = null;
			}
		}
		
		UserCookie uc;
		//从session中取值
		uc2 = findUCFromSession(cdm,uip);
		
		int tag=0;
		
		if (uc2!=null){
			tag=1;
		}
		
		if (uc2==null && uc1!=null){
			tag=2;
		}
		
		if (tag==1){
			uc=uc2;
		}else{
			uc=uc1;
		}
		
		if (uc==null){
//			System.out.println("准备清除000");
			clear(cdm);
			return null;
		}else{
			/*
			 * 这个地方放过了在线编辑器，因为那个编辑器利用struts2标签会产生问题
			 * 当然也可以写一个action进行认证，利用ajax认证返回一个结果，再在编辑器中进行认证
			 */
			//
			
			
			
			if (uc.getOpenID()==null){
				if (cdm.getUserDaoImp()!=null && cdm.getUserDaoImp().findUserByNameAndPassowrd(DesUtils.decrypt(uc.getUsername(), sfp), DesUtils.decrypt(uc.getPasswd(), sfp),pwdMD5ToLowerCase,true)==null){
//					System.out.println("准备清除111");
					clear(cdm);
					return null;
				}
			}else{
				if (!UserUtil.check(uc.getOpenID(), cdm.getInterconnectionDaoImp(), cdm.getUserDaoImp())){
//					System.out.println("准备清除222");
					clear(cdm);
					return null;
				}
			}
			String username,password,remName,openID;
			username=uc.getUsername();
			remName=uc.getRemName();
			password=uc.getPasswd();
			openID=uc.getOpenID();
			uc.setUsername(DesUtils.decrypt(username, sfp));
			uc.setRemName(DesUtils.decrypt(remName, sfp));
			if (password!=null && !password.trim().equals("")){
				uc.setPasswd(DesUtils.decrypt(password, sfp));
			}
			
			uc.setOpenID(DesUtils.decrypt(openID, sfp));
			return uc;
		}
		
	}
	
	public static boolean save(CookieDoModel cdm, String k,String v,int time)
	throws IOException {
		v = java.net.URLEncoder.encode(
				v, cdm.getEncodingCode());
		try {
			if (ActionContext.getContext()==null){
				cdm.getRequest().getSession().setAttribute(k, v);
			}else{
				ActionContext.getContext().getSession()
				.put(k, v);	
			}
			Cookie c = new Cookie(k, v);
//			String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
			String cookieDomain=SysUtil.readRec(cdm.getActionSupport(),"lerx.cookieDomain");
			if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
				c.setPath("/");
				c.setDomain("."+cookieDomain);
				
			}
			c.setMaxAge(time);
			cdm.getResponse().addCookie(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println("aaaa");
			e.printStackTrace();
		}
		return false;
	}
	
	

	public static boolean save(CookieDoModel cdm, UserCookie uc)
			throws IOException {
		
		UserInfPrefix uip= prefixInit(cdm);
		
		Cookie cu, cp, ci,co,cr;
		int saveTime;
		String username,password,remName,openID;
		username=uc.getUsername();
		password=uc.getPasswd();
		remName=uc.getRemName();
		openID=uc.getOpenID();
		
		if (remName==null){
			remName=username;
		}
		SecFilePosition sfp = new SecFilePosition();
		sfp.setRequest(cdm.getRequest());
		sfp.setSecFileName(cdm.getActionSupport().getText("lerx.hostSecFile"));
		
		uc.setUsername(DesUtils.encrypt(username, sfp));
		uc.setRemName(DesUtils.encrypt(remName, sfp));
		if (password!=null && !password.trim().equals("")){
			uc.setPasswd(DesUtils.encrypt(password, sfp));
		}
		if (openID!=null && !openID.trim().equals("")){
			uc.setOpenID(DesUtils.encrypt(openID, sfp));
		}
		
		
		String encodeUserName,encodePassword,encodeRemName,encodeOpenID;
		
		//写入session
		try {
			ActionContext.getContext().getSession()
			.put(uip.getUsernamePre(), uc.getUsername());
			ActionContext.getContext().getSession()
			.put(uip.getRemNamePre(), uc.getRemName());
			if (password!=null && !password.trim().equals("")){
				ActionContext.getContext().getSession()
				.put(uip.getPasswordPre(), uc.getPasswd());
			}
			
			ActionContext.getContext().getSession()
			.put(uip.getUidPre(), String.valueOf(uc.getUserId()));
			
			if (openID!=null && !openID.trim().equals("")){
				ActionContext.getContext().getSession()
				.put(uip.getOpenIDPre(), uc.getOpenID());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println("aaaa");
			e.printStackTrace();
		}
		
		//写入cookie
		encodeUserName = java.net.URLEncoder.encode(
				uc.getUsername(), cdm.getEncodingCode());
		encodeRemName = java.net.URLEncoder.encode(
				uc.getRemName(), cdm.getEncodingCode());
		if (uc.getPasswd()!=null && !uc.getPasswd().trim().equals("")){
			encodePassword = java.net.URLEncoder.encode(
					uc.getPasswd(), cdm.getEncodingCode());
		}else{
			encodePassword="";
		}
		cu = new Cookie(uip.getUsernamePre(), encodeUserName);
		cp = new Cookie(uip.getPasswordPre(), encodePassword);
		cr = new Cookie(uip.getRemNamePre(), encodeRemName);
		ci = new Cookie(uip.getUidPre(), String.valueOf(uc.getUserId()));
		
		if (uc.getOpenID()!=null && !uc.getOpenID().trim().equals("")){
			encodeOpenID = java.net.URLEncoder.encode(
					uc.getOpenID(), cdm.getEncodingCode());
			co = new Cookie(uip.getOpenIDPre(), encodeOpenID);
		}else{
			co=null;
		}
		
		
		
		
		switch (uc.getTime()){
			case 0:
				saveTime = -1;
				break;
			case 1:
				saveTime=60*60;
				break;
			case 2:
				saveTime=60*60*24;
				break;
			case 3:
				saveTime=60*60*24*7;
				break;
			case 4:
				saveTime=60*60*24*31;
				break;
			case 5:
				saveTime=60*60*24*365;
				break;
			default:
				saveTime=uc.getTime();
				break;
			
		}
		if (uc.getTime() != 0) {
			cu.setMaxAge(saveTime);
			cp.setMaxAge(saveTime);
			ci.setMaxAge(saveTime);
			cr.setMaxAge(saveTime);
			if (co!=null){
				co.setMaxAge(saveTime);
			}
			
		} else {
			cu.setMaxAge(-1);
			cp.setMaxAge(-1);
			ci.setMaxAge(-1);
			cr.setMaxAge(-1);
			if (co!=null){
				co.setMaxAge(-1);
			}
			
		}
		
		String cookieDomain= SysUtil.readRec(cdm.getActionSupport(), "lerx.cookieDomain");
		
		if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
			cu.setPath("/");
			cp.setPath("/");
			ci.setPath("/");
			cr.setPath("/");
			
			cu.setDomain("."+cookieDomain);
			cp.setDomain("."+cookieDomain);
			ci.setDomain("."+cookieDomain);
			cr.setDomain("."+cookieDomain);
			
			if (co!=null){
				co.setPath("/");
				co.setDomain("."+cookieDomain);
			}
			
		}
		cdm.getResponse().addCookie(cu);
		cdm.getResponse().addCookie(cp);
		cdm.getResponse().addCookie(ci);
		if (co!=null){
			cdm.getResponse().addCookie(co);
		}
		
		cdm.getResponse().addCookie(cr);

		return true;

	}

	public static boolean clear(CookieDoModel cdm) {
//		System.out.println();
//		System.out.println("现在清除");
		UserInfPrefix uip= prefixInit(cdm);
		Cookie[] cookies = cdm.getRequest().getCookies();
		Cookie c;
		String n;
//		String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
		String cookieDomain= SysUtil.readRec(cdm.getActionSupport(), "lerx.cookieDomain");
		
		
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
				if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
					c.setDomain("."+cookieDomain);
				}
				n = c.getName();
				if (n.trim().equals(uip.getUsernamePre())) {
					c.setMaxAge(0);
					cdm.getResponse().addCookie(c);
				}
				if (n.trim().equals(uip.getRemNamePre())) {
					c.setMaxAge(0);
					cdm.getResponse().addCookie(c);
				}
				if (n.trim().equals(uip.getPasswordPre())) {
					c.setMaxAge(0);
					cdm.getResponse().addCookie(c);
				}
				if (n.trim().equals(uip.getUidPre())) {
					c.setMaxAge(0);
					cdm.getResponse().addCookie(c);
				}
				if (n.trim().equals(uip.getOpenIDPre())) {
					c.setMaxAge(0);
					cdm.getResponse().addCookie(c);
				}
				
			}

		}
		//清除session
		ActionContext.getContext().getSession().remove(uip.getUsernamePre());
		ActionContext.getContext().getSession().remove(uip.getRemNamePre());
		ActionContext.getContext().getSession().remove(uip.getPasswordPre());
		ActionContext.getContext().getSession().remove(uip.getUidPre());
		ActionContext.getContext().getSession().remove(uip.getOpenIDPre());
		return true;

	}
	
	private static UserInfPrefix prefixInit(CookieDoModel cdm){
		UserInfPrefix uip=new UserInfPrefix ();
		String prefix = cdm.getPrefix();
		prefix = StringUtil.strReplace(prefix, "servername", cdm.getHost());
		uip.setUsernamePre("username_" + prefix);
		uip.setPasswordPre("password_" + prefix);
		uip.setRemNamePre("remname_" + prefix);
		uip.setUidPre("uid_" + prefix);
		uip.setOpenIDPre("openID_" + prefix);
		return uip;
		
	}
	
	private static UserCookie findUCFromSession(CookieDoModel cdm,UserInfPrefix uip){
		UserCookie uc=new UserCookie ();
		if (cdm.getRequest().getSession().getAttribute(uip.getUsernamePre())!=null){
			uc.setUsername((String) cdm.getRequest().getSession().getAttribute(uip.getUsernamePre()));
		}else{
//			System.out.println("--- 位置  1");
			return null;
		}
		if (cdm.getRequest().getSession().getAttribute(uip.getRemNamePre())!=null){
			uc.setRemName((String) cdm.getRequest().getSession().getAttribute(uip.getRemNamePre()));
		}else{
//			System.out.println("--- 位置  2");
			return null;
		}
		if (cdm.getRequest().getSession().getAttribute(uip.getPasswordPre())!=null){
			uc.setPasswd((String) cdm.getRequest().getSession().getAttribute(uip.getPasswordPre()));
		}
		if (cdm.getRequest().getSession().getAttribute(uip.getUidPre())!=null){
			uc.setUserId(Long.valueOf((String) cdm.getRequest().getSession().getAttribute(uip.getUidPre())));
		}else{
//			System.out.println("--- 位置 3");
			return null;
		}
		if (cdm.getRequest().getSession().getAttribute(uip.getOpenIDPre())!=null){
			uc.setOpenID((String) cdm.getRequest().getSession().getAttribute(uip.getOpenIDPre()));
		}
//		System.out.println("--- 位置  4");
		return uc;
	}
	
	
	private static  String findResource(String key) {
		/*
		 * 此方法是否会产生安全问题？
		 * 是否可以通过此action获得配置文件的信息？
		 * 目前我没想到方法
		 */
		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
		
	}
	
}
