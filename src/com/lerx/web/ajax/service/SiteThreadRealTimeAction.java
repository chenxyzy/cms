package com.lerx.web.ajax.service;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.util.AttaUtil;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IIntegralDao;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.Integral;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

public class SiteThreadRealTimeAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long tid;
	private int gid;
	private int l;
	private int format;
	private boolean htmlFilter;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ICommentDao commentDaoImp;
	private IExternalHostCharsetDao externalHostCharsetDaoImp;
	private IIntegralDao integralDaoImp;
	private SiteStyle curStyle;
	private UserCookie uc;
	private String encryptedParmStr;
	private CookieDoModel cdm;
	private int direction;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getEncryptedParmStr() {
		return encryptedParmStr;
	}

	public void setEncryptedParmStr(String encryptedParmStr) {
		this.encryptedParmStr = encryptedParmStr;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public boolean isHtmlFilter() {
		return htmlFilter;
	}

	public void setHtmlFilter(boolean htmlFilter) {
		this.htmlFilter = htmlFilter;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
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

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setCommentDaoImp(ICommentDao commentDaoImp) {
		this.commentDaoImp = commentDaoImp;
	}

	public void setExternalHostCharsetDaoImp(
			IExternalHostCharsetDao externalHostCharsetDaoImp) {
		this.externalHostCharsetDaoImp = externalHostCharsetDaoImp;
	}

	public void setIntegralDaoImp(IIntegralDao integralDaoImp) {
		this.integralDaoImp = integralDaoImp;
	}

	public void encryptedParmStr() throws IOException {
		ArticleThread t = articleThreadDaoImp.findById(tid);
		String encryptedParmStr = StringUtil.md5(StringUtil.randomString(64));
		t.setEncryptedParmStr(encryptedParmStr);
		articleThreadDaoImp.modify(t);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(encryptedParmStr);
	}

	// 上一条下一条
	public void adjacent() throws IOException {
		String lastArticleForwardCode, nextArticleForwardCode, outStr;
		siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		curStyle = siteStyleDaoImp.findDef();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		lastArticleForwardCode = curStyle.getLastArticleForwardCode();
		nextArticleForwardCode = curStyle.getNextArticleForwardCode();
		// 上一条，下一条
		ArticleThread adjacentT;

		if (direction == 0) {
			outStr = lastArticleForwardCode;
			adjacentT = articleThreadDaoImp.findAdjacent(tid, 0, t
					.getArticleGroup().getId(), checkUser(t.getArticleGroup()));
		} else {
			outStr = nextArticleForwardCode;
			adjacentT = articleThreadDaoImp.findAdjacent(tid, 1, t
					.getArticleGroup().getId(), checkUser(t.getArticleGroup()));
		}
		outStr = ThreadUtil.formatLastOrNext(outStr, request, this, adjacentT,
				direction);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);

	}

	// 媒体
	public void mediaUrl() throws IOException {
		boolean con;
		String mediaUrl = null;
		ArticleThread t = articleThreadDaoImp.findById(tid);
		if (t.getEncryptedParmStr() != null
				&& t.getEncryptedParmStr().trim().equals(encryptedParmStr)) {
			String linkUrl = t.getLinkUrl();

			if (linkUrl == null || linkUrl.trim().equals("")
					|| t.getLinkUrl().indexOf(",") == -1) {
				con = false;
			} else {
				String[] linkUrlTmp = linkUrl.trim().split(",");
				mediaUrl = linkUrlTmp[2];
				if (mediaUrl != null && !mediaUrl.trim().equals("")) {
					con = true;
				} else {
					con = false;
				}
			}
		} else {
			con = false;
		}

		t.setEncryptedParmStr(null);
		articleThreadDaoImp.modify(t);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));

		if (con && mediaUrl != null) {
			mediaUrl = AttaUtil.encoder(externalHostCharsetDaoImp, this,
					mediaUrl, 1);
			// mediaUrl=StringUtil.urlEncoder(mediaUrl,
			// getText("lerx.attaURLEncoderCharset"));
			response.getWriter().write(mediaUrl);
		} else {
			response.getWriter().write("null");
		}
	}

	public void extraBody() throws IOException {
		siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		String ellipsisChar = SysUtil.ellipsis(this);
		ArticleThread t = articleThreadDaoImp.findById(tid);

		String body, out;
		if (t.getBody() == null) {
			body = "";
		} else {
			if (htmlFilter) {
				body = StringUtil.htmlFilter(t.getBody().trim());
			} else {
				body = t.getBody().trim();
			}

		}
		String formatStr;
		switch (format) {
		case 1:
			formatStr = curStyle.getCustomFormatCode1();
			break;
		case 2:
			formatStr = curStyle.getCustomFormatCode2();
			break;
		case 3:
			formatStr = curStyle.getCustomFormatCode3();
			break;
		case 4:
			formatStr = curStyle.getCustomFormatCode4();
			break;
		case 5:
			formatStr = curStyle.getCustomFormatCode5();
			break;
		case 6:
			formatStr = curStyle.getCustomFormatCode6();
			break;
		case 7:
			formatStr = curStyle.getCustomFormatCode7();
			break;
		case 8:
			formatStr = curStyle.getCustomFormatCode8();
			break;
		default:
			formatStr = null;
			break;
		}

		if (l > 0 && body.length() > l) {
			body = body.substring(0, l) + ellipsisChar;
		}

		if (formatStr != null) {
			out = StringUtil.strReplace(formatStr, "{$$body$$}", body);
		} else {
			out = body;
		}

		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(out);

	}

	// 检查ip，栏目是否限制ip
	public void ipCheck() throws IOException {
		String result;
		ArticleGroup ag = articleGroupDaoImp.findById(gid);
		if (ag.getHostsAllow() == null || ag.getHostsAllow().trim().equals("")) {
			result = "true";

		} else {
			String curIP = IpUtil.getRealRemotIP(request).trim();
			if (!IpUtil.isInRange(curIP,
					articleGroupDaoImp.findAllHostAllowStrByArticleGroup(ag))) {
				result = "false";
			} else {
				result = "true";
			}
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(result);

	}

	public void someInf() throws IOException {

		siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		String tmp;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				getText("lerx.default.format.datetime").trim());

		curStyle = siteStyleDaoImp.findDef();
		tmp = curStyle.getThreadAjaxShowStr();
		ArticleThread t = articleThreadDaoImp.findById(tid);
		// 判断页面访问并保存
		boolean saveT = false;
		String lastViewIP = t.getLastViewIp();
		String curIP = IpUtil.getRealRemotIP(request).trim();
		if (lastViewIP == null) {
			lastViewIP = "";
		}
		if (getText("lerx.viewsUpdateByIp").trim().equals("true")) {
			//必须ip不同才计数
			if (!lastViewIP.trim().equals(curIP)) {
				t.setLastViewIp(curIP);
				t.setViews(t.getViews() + 1);
				saveT = true;
			}
		} else {
			//不管ip，只要浏览就计数
			t.setViews(t.getViews() + 1);
			saveT = true;
		}

		if (saveT) {
			articleThreadDaoImp.modify(t);
		}else{
		}

		if (t.getAddTime() == null) {
			tmp = StringUtil.strReplace(tmp, "{$$addTime$$}", "");
		} else {
			tmp = StringUtil.strReplace(tmp, "{$$addTime$$}",
					formatter.format(t.getAddTime()).toString());
		}
		if (checkUser(t.getArticleGroup()) == 2) {
			if (t.isState()) {
				tmp = StringUtil.strReplace(tmp, "{$$passStr$$}",
						StringUtil.nullFilter(curStyle.getPassedStr()));
			} else {
				tmp = StringUtil.strReplace(tmp, "{$$passStr$$}",
						StringUtil.nullFilter(curStyle.getNoPassedStr()));
			}
			if (t.isSoul()) {
				tmp = StringUtil.strReplace(tmp, "{$$soulStr$$}",
						getText("lerx.soulCancelStr"));
				tmp = StringUtil.strReplace(tmp, "{$$soul$$}", "false");
			} else {
				tmp = StringUtil.strReplace(tmp, "{$$soulStr$$}",
						getText("lerx.soulStr"));
				tmp = StringUtil.strReplace(tmp, "{$$soul$$}", "true");
			}
			if (t.isTopOne()) {
				tmp = StringUtil.strReplace(tmp, "{$$topOneStr$$}",
						getText("lerx.topOneCancelStr"));
				tmp = StringUtil.strReplace(tmp, "{$$topOne$$}", "false");
			} else {
				tmp = StringUtil.strReplace(tmp, "{$$topOneStr$$}",
						getText("lerx.topOneStr"));
				tmp = StringUtil.strReplace(tmp, "{$$topOne$$}", "true");
			}

		} else {
			tmp = StringUtil.strReplace(tmp, "{$$passStr$$}", "");
		}
		tmp = StringUtil.strReplace(tmp, "{$$member$$}",
				StringUtil.nullFilter(t.getMember()));
		tmp = StringUtil.strReplace(tmp, "{$$views$$}", "" + t.getViews());
//		System.out.println("---lastViewIP:"+lastViewIP);
		tmp = StringUtil.strReplace(
				tmp,
				"{$$lastIP$$}",
				IpUtil.ipFilter(StringUtil.nullFilter(lastViewIP),
						Integer.valueOf(getText("lerx.rule.length.ip.filter"))));

		if (t.getPasser() != null && !t.getPasser().trim().equals("")) {

			tmp = StringUtil.strReplace(tmp, "{$$passer$$}", t.getPasser());
		} else {
			tmp = StringUtil.strReplace(tmp, "{$$passer$$}", "");
		}
		tmp = StringUtil.strReplace(tmp, "{$$contextPath$$}",
				request.getContextPath());
		tmp = StringUtil.strReplace(tmp, "{$$tid$$}", "" + t.getId());

		tmp = StringUtil.strReplace(tmp, "{$$gid$$}", ""
				+ t.getArticleGroup().getId());
		// 评论总数
		long commentsPassedCount = commentDaoImp.count(tid, 0);
		long commentsNoPassedCount = commentDaoImp.count(tid, 1);
		long commentsAllCount = commentDaoImp.count(tid, 2);
		tmp = StringUtil.strReplace(tmp, "{$$commentsPassedCount$$}", ""
				+ commentsPassedCount);
		tmp = StringUtil.strReplace(tmp, "{$$commentsNoPassedCount$$}", ""
				+ commentsNoPassedCount);
		tmp = StringUtil.strReplace(tmp, "{$$commentsAllCount$$}", ""
				+ commentsAllCount);

		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(tmp);
	}

	// 输出显示正文状态

	public void showBodyByPrice() throws IOException {
		siteInfDaoImp.query();
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		// System.out.println("uid:"+uc.getUserId());
		User u = null;
		if (uc != null) {
			u = userDaoImp.findUserById(uc.getUserId());
			// System.out.println("id:"+u.getId());
		}
		ArticleThread t = articleThreadDaoImp.findById(tid);
		String tmp = null;
		int price = ThreadUtil.price(t);
		if (price > 0) {
			if (u != null &&  t.getUser()!=null && u.getId() == t.getUser().getId()) {
				price = 0;
			}

		}
		if (price > 0) {
			ChkUtilVo cuv=CuvInit();
			cuv.setAg(t.getArticleGroup());
			cuv.setUc(uc);
			cuv.setMode(0);
			cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
			if (u != null
					&& userDaoImp.checkUserOnSite(cuv) == 2) {
				price = 0;
			}
		}
		if (price > 0) {
			if (u != null) {
				Integral integral = new Integral();
				integral.setTagId(t.getId());
				integral.setValue(price);
				integral.setType(1);
				integral.setRecTime(new Timestamp(System.currentTimeMillis()));
				integral.setUser(u);
				if (integralDaoImp.find(integral) > 0) {
					// System.out.println("找到了已经扣分");
					price = 0;
				}
			} else {
				tmp = getText("lerx.err.view.nologin");
			}
		}
		if (price == 0) {
			tmp = t.getBody();
		} else { // 提示扣点
			if (tmp == null) {
				curStyle = siteStyleDaoImp.findDef();

				tmp = curStyle.getAjaxOfBeforeView();
				tmp = StringUtil.strReplace(tmp, "{$$price$$}", "" + price);
				tmp = StringUtil.strReplace(tmp, "{$$remainder$$}", "" + u.getAllScore());
//				tmp=tmp.replaceAll("\'", "\\\\\'");
				
			}

		}

		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(tmp);
	}

	// 显示文章所需点数
	public void price() throws IOException {
		ArticleThread t = articleThreadDaoImp.findById(tid);
		int price = t.getPrice();
		if (price == 0) {
			price = t.getArticleGroup().getPrice();
		}
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write("" + price);
	}

	// 扣点数显示正文
	public void priceView() throws IOException {
		siteInfDaoImp.query();
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		User u = userDaoImp.findUserById(uc.getUserId());
		String tmp;
		ArticleThread t = articleThreadDaoImp.findById(tid);
		Integral integral = new Integral();
		int price = t.getPrice();
		if (price == 0) {
			price = t.getArticleGroup().getPrice();
		}

		if (u.getAllScore() > price) {
			if (price > 0) {

				integral.setTagId(t.getId());
				integral.setValue(price);
				integral.setType(1);
				integral.setRecTime(new Timestamp(System.currentTimeMillis()));
				integral.setUser(u);
				integralDaoImp.add(integral);
				u.setAllScore(u.getAllScore() - price);
				userDaoImp.modifyUser(u);

				int priceAddMode = Integer
						.valueOf(getText("lerx.articlePriceAddMode"));
				/*
				 * 积分增加模式 
				 * 0 阅读后不给发者布加分
				 * 1 阅读后根据所属栏目定义的扣分，将阅读者被扣除的分加给发布者
				 * 2 阅读后根据所属栏目定义的扣分和发布的文章定义的分取最高值，将阅读者被扣除的分加给发布者
				 * 3 阅读后根据所属栏目定义的扣分和发布的文章定义的分取最低值，将阅读者被扣除的分加给发布者
				 */
				u=t.getUser();
				if (u!=null){
					int addValue;
					int vg,va;
					switch (priceAddMode) {
					case 1:
						addValue=t.getArticleGroup().getPrice();
						break;
					case 2:
						
						vg=t.getArticleGroup().getPrice();
						va=t.getPrice();
						if (va>vg){
							addValue=va;
						}else{
							addValue=vg;
						}
						break;
					case 3:
						vg=t.getArticleGroup().getPrice();
						va=t.getPrice();
						if (va<vg){
							addValue=va;
						}else{
							addValue=vg;
						}
						break;
					default:
						addValue=0;
						break;
					}
					
					if (addValue>0){
						
						u.setAllScore(u.getAllScore()+addValue);
						userDaoImp.modifyUser(u);
					}
				}
				

			}
			tmp = t.getBody();
		} else {
			tmp = getText("lerx.fail.notEnough");
		}

		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(tmp);
	}

	public int checkUser(ArticleGroup ag) {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}

		ChkUtilVo cuv=CuvInit();
		cuv.setAg(ag);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		return userDaoImp.checkUserOnSite(cuv);

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	private ChkUtilVo CuvInit(){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		
		return cuv;
	}

}
