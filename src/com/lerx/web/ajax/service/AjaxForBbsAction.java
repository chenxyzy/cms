package com.lerx.web.ajax.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.util.BbsStyleUtil;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxForBbsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private long tid;
	private long fid;
	private int fmt;
	private int smod;
	private int hours;
	private IBbsThemeDao bbsThemeDaoImp;;
	private IBbsStyleDao bbsStyleDaoImp;
	private IUserDao userDaoImp;
	private int page;
	private int pageSize;
	private int n;
	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getSmod() {
		return smod;
	}

	public void setSmod(int smod) {
		this.smod = smod;
	}

	public int getFmt() {
		return fmt;
	}

	public void setFmt(int fmt) {
		this.fmt = fmt;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
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

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public void setBbsThemeDaoImp(IBbsThemeDao bbsThemeDaoImp) {
		this.bbsThemeDaoImp = bbsThemeDaoImp;
	}

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void themeQuote() throws IOException{
//		System.out.println("引用开始");
		String themeBody="";
		int length=Integer.valueOf(getText("lerx.quoteLength"));
		if (length==0){
			length=100;
		}
		BbsTheme theme=bbsThemeDaoImp.findThemeById(tid);
		if (theme!=null){
			themeBody=theme.getBody();
			themeBody=StringUtil.filterByHtmlLabel(themeBody,"blockquote");
			themeBody=StringUtil.htmlFilter(themeBody);
			if (themeBody.length()>length){
				themeBody=themeBody.substring(0, length);
				themeBody+="...";
			}
		}
		String quoteAreaCode;
		BbsStyle curBbsStyle=bbsStyleDaoImp.findDef();
		quoteAreaCode=curBbsStyle.getQuoteAreaCode();
//		System.out.println(quoteAreaCode);
		
		quoteAreaCode=StringUtil.strReplace(quoteAreaCode, "{$$quoteText$$}", themeBody);
		if (theme!=null){
			if (theme.getUser()!=null){
				User user=userDaoImp.findUserById(theme.getUser().getId());
				quoteAreaCode=StringUtil.strReplace(quoteAreaCode, "{$$user$$}", user.getUserName());
			}else{
				quoteAreaCode=StringUtil.strReplace(quoteAreaCode, "{$$user$$}", "");
			}
			
			quoteAreaCode=StringUtil.strReplace(quoteAreaCode, "{$$time$$}", ""+theme.getAddTime());
		}else{
			quoteAreaCode=StringUtil.strReplace(quoteAreaCode, "{$$time$$}", "");
		}
				
		
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		
		try {
//			response.getWriter().write("asdfadsf");
			response.getWriter().write(quoteAreaCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void findThread(){
		
		/*
		 * smod 0 按id号递减
		 * 		1 按热度			他人参与率 60% 回贴数及浏览数均20%
		 * 		2 按浏览数
		 * 
		 * n、pageSize  每页数
		 * page 第几页
		 * fmt 自定义格式  0采用默认格式
		 * hours 到当前时间的小时数 0 不采用
		 * 
		 */
		if (page==0){
			page=1;
		}
		if (n>0){
			pageSize=n;
		}
		
		if (pageSize<=0){
			pageSize=Integer.valueOf(getText("lerx.pageSize.result.default"));
		}
//		System.out.println("hours:"+hours);
		Rs rs=bbsThemeDaoImp.findThemesByRule(fid,hours, smod,page, pageSize,SysUtil.hsValue(this));
		
		@SuppressWarnings("unchecked")
		List<Long> ls = (List<Long>) rs.getList();
		
		BbsStyle bs=bbsStyleDaoImp.findDef();
		if (bs!=null){
			String formatStr;
			if (fmt<1 || fmt >8){
				formatStr=bs.getHrefLineFormatStrOverAll();
			}else{
				formatStr=BbsStyleUtil.customFormat(bs, fmt);
			}
			String tmp,tmpAll="";
			BbsTheme bt;
			FormatElements el=new FormatElements();
			el.setAs(this);
			el.setUserDaoImp(userDaoImp);
			el.setRequest(request);
			el.setBbsThemeDaoImp(bbsThemeDaoImp);
			el.setBbsStyleDaoImp(bbsStyleDaoImp);
			for (Long tid : ls) {
				el.setLf(formatStr);
				bt = bbsThemeDaoImp.findThemeById(tid);
				
				tmp = ThemeUtil.formatHref(el, bt);
				tmpAll += tmp;
			}
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset=" + getText("lerx.charset"));
			
			try {
//				response.getWriter().write("asdfadsf");
				response.getWriter().write(tmpAll);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		// TODO Auto-generated method stub
		
	}

}
