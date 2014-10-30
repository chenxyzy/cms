package com.lerx.web.ajax.service;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.opensymphony.xwork2.ActionSupport;

public class ArticlePollAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sp; // standpoint
	private int sid;
	private long tid;
	private boolean st;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private IUserDao userDaoImp;

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public boolean isSt() {
		return st;
	}

	public void setSt(boolean st) {
		this.st = st;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(
			IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}


	public void w() throws IOException {
		
		ArticleThread t = articleThreadDaoImp.findById(tid);
		ArticleGroup g = articleGroupDaoImp.findLastPollParent(t.getArticleGroup());
		String code = "";
		
		if (t!=null && g!=null && ArticleGroupUtil.pollChk(g)){
//			System.out.println("测试点1");
			boolean pwdMD5ToLowerCase;
			if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
				pwdMD5ToLowerCase = true;
			} else {
				pwdMD5ToLowerCase = false;
			}
			UserCookie uc;
			CookieDoModel cdm;
			cdm = CdmUtil.init(this, request, response, userDaoImp,
					interconnectionDaoImp);
			uc = CookieUtil.query(cdm);
			
			ChkUtilVo cuv = CuvInit();
			cuv.setAg(g);
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);

			SiteStyle curStyle;
			if (sid > 0) {
				curStyle = siteStyleDaoImp.findStyleById(sid);
			} else {
				curStyle = siteStyleDaoImp.findDef();
			}
			String txt;
			String rootFolder,pollYes,pollNo,pollNeu;
			
			rootFolder = curStyle.getRootResFolder();
			ReadFileArg rfv = new ReadFileArg();
			rfv.setAs(this);
			rfv.setRequest(request);
			rfv.setRootFolder(rootFolder);
			rfv.setSubFolder("act");
			
			if (userDaoImp.checkUserOnSite(cuv) == 2) {
				rfv.setFileName("artPoll.txt");
				txt = FileUtil.readFile(rfv);
				txt = StringUtil.strReplace(txt, "{$$tid$$}", "" + t.getId());
			} else {
				txt = "";
			}

			if (t.isPollAllow()) {
//				System.out.println("测试点2");
				txt = StringUtil.strReplace(txt, "{$$title$$}",
						getText("lerx.title.close"));
				code = curStyle.getAjaxOfArtPoll();
				code = StringUtil.strReplace(code, "{$$pollFunction$$}", txt);
				code = StringUtil.strReplace(code, "{$$st$$}", "false");
				
				if (ArticleGroupUtil.pollFmt(g, 1)){
					pollYes =rfvRead(rfv,"pollYes.txt");
					code = StringUtil.strReplace(code, "{$$pollYes$$}", pollYes);
				}else{
					code = StringUtil.strReplace(code, "{$$pollYes$$}", "");
				}
				
				if (ArticleGroupUtil.pollFmt(g, 2)){
					pollNo=rfvRead(rfv,"pollNo.txt");
					code = StringUtil.strReplace(code, "{$$pollNo$$}", pollNo);
				}else{
					code = StringUtil.strReplace(code, "{$$pollNo$$}", "");
				}
				
				if (ArticleGroupUtil.pollFmt(g, 0)){
					pollNeu=rfvRead(rfv,"pollNeu.txt");
					code = StringUtil.strReplace(code, "{$$pollNeu$$}", pollNeu);
				}else{
					code = StringUtil.strReplace(code, "{$$pollNeu$$}", "");
				}
				
			} else {
				code = txt;
				code = StringUtil.strReplace(code, "{$$title$$}",
						getText("lerx.title.open"));
				code = StringUtil.strReplace(code, "{$$st$$}", "true");
			}
			code = StringUtil.strReplace(code, "{$$gid$$}", ""
					+ t.getArticleGroup().getId());
			code = StringUtil.strReplace(code, "{$$tid$$}", "" + t.getId());
			code = StringUtil.strReplace(code, "{$$proponents$$}",
					"" + t.getProponents());
			code = StringUtil.strReplace(code, "{$$opponents$$}",
					"" + t.getOpponents());
			code = StringUtil.strReplace(code, "{$$neutrals$$}",
					"" + t.getNeutrals());
			code = StringUtil.strReplace(code, "{$$contextPath$$}",
					request.getContextPath());
		
		}
		
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(code);
	}

	public void chg() throws IOException {
		UserCookie uc;
		CookieDoModel cdm;
		cdm = CdmUtil.init(this, request, response, userDaoImp,
				interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		ArticleThread t = articleThreadDaoImp.findById(tid);
		String msg;
		if (uc != null && t != null && t.isState()) {
			boolean pwdMD5ToLowerCase;
			if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
				pwdMD5ToLowerCase = true;
			} else {
				pwdMD5ToLowerCase = false;
			}
			
			
			// User u = userDaoImp.findUserById(uc.getUserId());
			ArticleGroup g = articleGroupDaoImp.findById(t
					.getArticleGroup().getId());
			ChkUtilVo cuv = CuvInit();
			cuv.setAg(g);
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
			if (userDaoImp.checkUserOnSite(cuv) == 2) {
				t.setPollAllow(st);
				articleThreadDaoImp.modify(t);
				msg = "success";
			} else {
				msg = "fail";
			}
		} else {
			msg = "fail";
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(msg);
	}

	public void v() throws IOException {
		boolean withLogin, con = true;
		String msg = null;
		int pcd;
		pcd = Integer.valueOf(getText("lerx.poll.article.days.intervals"));
		if (pcd == 0) {
			pcd = 1;
		}
		int time = 60 * 60 * 24 * pcd;
		if (getText("lerx.poll.article.login").trim().equalsIgnoreCase("true")) {
			withLogin = true;
		} else {
			withLogin = false;
		}
		UserCookie uc;
		CookieDoModel cdm;
		cdm = CdmUtil.init(this, request, response, userDaoImp,
				interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		String cv = CookieUtil.query(cdm, "lerx_poll_art_" + tid + "_");
		if (cv == null || cv.trim().equals("")) {
			con = true;
		} else {
			msg = "exists_"+cv;
			con = false;
		}

		if (con && withLogin) {

			if (uc != null) {
				con = true;
			} else {
				msg = "noLogin";
				con = false;
			}
		}
		ArticleThread t = articleThreadDaoImp.findById(tid);

		int dayAllow = Integer.valueOf(getText("lerx.poll.article.days.allow"));
		if (dayAllow > 0) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			int n = TimeUtil.daysSubByTimestamp(now, t.getAddTime());
			n = Math.abs(n);
			if (n > dayAllow) {
				con = false;
				msg = "timeOut";
			}
		}

		if (con) {

			String tmp="";
			if (t != null && t.isState()) {
				switch (sp) {
				case 1:
					tmp="true";
					t.setProponents(t.getProponents() + 1);
					break;
				case 2:
					tmp="false";
					t.setOpponents(t.getOpponents() + 1);
					break;
				default:
					tmp="neu";
					t.setNeutrals(t.getNeutrals() + 1);
					break;
				}
				articleThreadDaoImp.modify(t);
				CookieUtil.save(cdm, "lerx_poll_art_" + tid + "_", tmp, time);
				msg = "success";
			} else {
				msg = "fail";
			}
		}
		if (msg == null) {
			msg = "fail";
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		// System.out.println("msg::"+msg);
		response.getWriter().write(msg);

	}

	private ChkUtilVo CuvInit() {
		ChkUtilVo cuv = new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);

		return cuv;
	}
	
	private String rfvRead(ReadFileArg rfv,String fileName){
		rfv.setFileName(fileName);
		return FileUtil.readFile(rfv);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
