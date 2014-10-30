package com.lerx.web.ajax.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.opensymphony.xwork2.ActionSupport;

public class SiteThreadListAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	HttpServletRequest request;
	private int firstResult;
	private boolean img;
	private SiteStyle curStyle;
	private int gid;
	private int mod;
	private int n;
	private int l;
	private int soul;
	private int page;
	private int pageSize;
	private int stateMode;
	private boolean nt;
//	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IArticleThreadDao articleThreadDaoImp;

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
	}

//	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
//		this.siteInfDaoImp = siteInfDaoImp;
//	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public boolean isImg() {
		return img;
	}

	public void setImg(boolean img) {
		this.img = img;
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

	public int getStateMode() {
		return stateMode;
	}

	public void setStateMode(int stateMode) {
		this.stateMode = stateMode;
	}

	public boolean isNt() {
		return nt;
	}

	public void setNt(boolean nt) {
		this.nt = nt;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	@SuppressWarnings("unchecked")
	public void list() throws IOException {

		if (page == 0) {
			page = 1;
		}
		if (n > 0) {
			pageSize = n;
		}
		if (pageSize <= 0) {
			pageSize = Integer.valueOf(getText("lerx.pageSize.result.default"));
		}
//		siteInfDaoImp.query();
		int speedMod=Integer.valueOf(getText("lerx.data.query.mod"));
		curStyle = siteStyleDaoImp.findDef();
		Rs rs = articleThreadDaoImp.findByGroupAndMod(gid, page,
				pageSize, mod, stateMode, nt, soul, firstResult, img,speedMod);
		List<Long> lan = null;
		List<ArticleThread> lat = null;
		if (speedMod==1){
			lat=(List<ArticleThread>) rs.getList();
		}else{
			lan = (List<Long>) rs.getList();
		}
		
		ArticleThread at;
		String f = curStyle.getHrefLineFormatStrOverAll();
		String tmp = "", tmpAll = "";
		FormatElements el = new FormatElements();
		el.setEditAreaCode(null);
		el.setDateFormatOnLine(null);
		el.setIncludeEditArea(false);
		el.setTitleLength(0);
		el.setAs(this);
		el.setRequest(request);
		el.setArticleThreadDaoImp(articleThreadDaoImp);
		el.setSiteStyleDaoImp(siteStyleDaoImp);
		if (speedMod==1){
			for (ArticleThread ati : lat) {
				el.setLf(f);
				at = ati;
				at = ThreadUtil.escape(at);
				tmp = ThreadUtil.formatHref(el, at);
				tmpAll += tmp;
			}
		}else{
			for (Long atid : lan) {
				el.setLf(f);
				at = articleThreadDaoImp.findById(atid);
				at = ThreadUtil.escape(at);
				tmp = ThreadUtil.formatHref(el, at);
				tmpAll += tmp;
			}
		}
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(tmpAll);

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// TODO Auto-generated method stub

	}

}
