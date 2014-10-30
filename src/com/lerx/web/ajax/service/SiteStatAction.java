package com.lerx.web.ajax.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.imp.ArticleThreadDaoImp;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class SiteStatAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ArticleThreadDaoImp articleThreadDaoImp;
	private IUserDao userDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private SiteStyle curStyle;

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setArticleThreadDaoImp(ArticleThreadDaoImp articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void stat() throws IOException {
		long a0 = articleThreadDaoImp.count(0);
		long a1 = articleThreadDaoImp.count(1);
		long a2 = articleThreadDaoImp.count(2);

		long u0 = userDaoImp.count(0);
		long u1 = userDaoImp.count(1);
		long u2 = userDaoImp.count(2);

		siteInfDaoImp.query();
		curStyle = siteStyleDaoImp.findDef();
		String statCode = curStyle.getStatCode();
		statCode = StringUtil.strReplace(statCode, "{$$articlesPassed$$}", ""
				+ a1);
		statCode = StringUtil.strReplace(statCode, "{$$articlesNoPassed$$}", ""
				+ a2);
		statCode = StringUtil
				.strReplace(statCode, "{$$articlesAll$$}", "" + a0);
		statCode = StringUtil
				.strReplace(statCode, "{$$usersPassed$$}", "" + u1);
		statCode = StringUtil.strReplace(statCode, "{$$usersNoPassed$$}", ""
				+ u2);
		statCode = StringUtil.strReplace(statCode, "{$$usersAll$$}", "" + u0);

		statCode = StringUtil.strReplace(statCode, "{$$contextPath$$}",
				request.getContextPath());

		statCode = StringUtil.strReplace(
				statCode,
				"{$$contextHost$$}",
				SrvInf.srvUrlNoPath(request, "",
						Integer.valueOf(getText("lerx.serverPort"))).trim());

//		java.text.SimpleDateFormat formatterYear = new java.text.SimpleDateFormat(
//				"yyyy");
//		java.text.SimpleDateFormat formatterMonth = new java.text.SimpleDateFormat(
//				"yyyyMM");
//		String curYear,curMonth,lastMonth;
//		curYear=formatterYear.format(new Timestamp(System.currentTimeMillis()));
//		curMonth=formatterMonth.format(new Timestamp(System.currentTimeMillis()));
//		int year=Integer.valueOf(curYear);
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.MONDAY,cal.get(Calendar.MONDAY)-1);
//		lastMonth=formatterMonth.format(cal.getTime());
//		statCode = StringUtil.strReplace(statCode, "{$$lastYear$$}",
//				""+(year-1));
//		statCode = StringUtil.strReplace(statCode, "{$$lastMonth$$}",
//				lastMonth);
//		statCode = StringUtil.strReplace(statCode, "{$$curYear$$}",
//				curYear);
//		statCode = StringUtil.strReplace(statCode, "{$$curMonth$$}",
//				curMonth);
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(statCode);

		// response.getWriter().write("<p>c0结果："+c0+"</p>");
		// response.getWriter().write("<p>c1结果："+c1+"</p>");
		// response.getWriter().write("<p>c2结果："+c2+"</p>");
		// response.getWriter().write("<p>---------</p>");
		// response.getWriter().write("<p>u0结果："+u0+"</p>");
		// response.getWriter().write("<p>u1结果："+u1+"</p>");
		// response.getWriter().write("<p>u2结果："+u2+"</p>");
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
