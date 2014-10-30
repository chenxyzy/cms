package com.lerx.web.ajax.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleThread;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.opensymphony.xwork2.ActionSupport;

public class SiteStyleCustomFormatAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mod;
	private int fmod;
	private int gid;
	private boolean img;
	private String gidStr;
	private int page;
	private int pageSize;
	private int firstResult;
	private int n;
	private int l;
	private int soul;
	private int stateMode;
	private boolean  nt;
	private boolean fromjsp;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private int sid;
	

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getGid() {
		return gid;
	}

	public String getGidStr() {
		return gidStr;
	}

	public void setGidStr(String gidStr) {
		this.gidStr = gidStr;
	}

	public int getFmod() {
		return fmod;
	}

	public void setFmod(int fmod) {
		this.fmod = fmod;
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

	public boolean isFromjsp() {
		return fromjsp;
	}

	public void setFromjsp(boolean fromjsp) {
		this.fromjsp = fromjsp;
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

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
	}

	public boolean isImg() {
		return img;
	}

	public void setImg(boolean img) {
		this.img = img;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
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

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
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

	@SuppressWarnings("unchecked")
	public void custom() throws IOException {
		SiteInfo siteInfo;
		SiteStyle curStyle;
		siteInfo = siteInfDaoImp.query();
		if (sid>0){
			curStyle = siteStyleDaoImp.findStyleById(sid);
		}else{
			curStyle = siteStyleDaoImp.findDef();
		}
		if (curStyle!=null){
			String formatStr;
//			System.out.println("aaaaaaaaa");
			switch (fmod) {
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
				formatStr = curStyle.getCustomFormatCode1();
				break;
			}
			if (page==0){
				page=1;
			}
			if (n>0){
				pageSize=n;
			}
			if (pageSize<=0){
				pageSize=Integer.valueOf(getText("lerx.pageSize.result.default"));
			}
			
			int speedMod=Integer.valueOf(getText("lerx.data.query.mod"));
//			System.out.println("aaaa");
			Rs rs;
			ArticleThread at;
//			System.out.println("iiiii");
			if (gidStr==null || gidStr.trim().equals("")){
				rs=articleThreadDaoImp.findByGroupAndMod(gid, page, pageSize, mod, stateMode, nt,soul,firstResult,img,speedMod);
			}else{
				rs=articleThreadDaoImp.findByGroupAndMod(gidStr, page, pageSize, mod, stateMode, nt,soul,firstResult,img,speedMod);
			}
			String tmp="",tmpAll="";
			if (rs==null){
				response.setCharacterEncoding(getText("lerx.charset"));
				response.getWriter().write(tmpAll);
			}else{
				
				List<Long> lan = null;
				List<ArticleThread> lat = null;
				if (speedMod==1){
					lat=(List<ArticleThread>) rs.getList();
				}else{
					lan = (List<Long>) rs.getList();
				}
				
				String f=formatStr;
				
				int step=0;
				FormatElements el=elSiteInit();
				el.setIncludeEditArea(false);
				el.setTitleLength(l);
				el.setEditAreaCode(null);
				el.setDateFormatOnLine(null);
				el.setSiteStyleDaoImp(siteStyleDaoImp);
				if (fromjsp){
					String baseUrl = SrvInf.srvUrl(request, siteInfo.getHost(),
							Integer.valueOf(getText("lerx.serverPort")));
					el.setPostion(baseUrl);
				}
				if (speedMod==1){
					for (ArticleThread ati : lat) {
						el.setLf(f);
						at=ati;
						step++;
						tmp = ThreadUtil.formatHref(el,  at);
						
						tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
						tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
						tmpAll+=tmp;
					}
				}else{
					for (Long atid : lan) {
						el.setLf(f);
						at=articleThreadDaoImp.findById(atid);
						step++;
						tmp = ThreadUtil.formatHref(el,  at);
						
						tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
						tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
						tmpAll+=tmp;
					}
				}
				
				tmpAll=StringUtil.strReplace(tmpAll,"$$}{$$",",");
				tmpAll=StringUtil.strReplace(tmpAll,"$$}","");
				tmpAll=StringUtil.strReplace(tmpAll,"{$$","");
				
				if (fromjsp){
					tmpAll=StringUtil.strReplace(tmpAll,"'","\"");
//					tmpAll=StringUtil.strReplace(tmpAll,"\"","\\\\\"");
					tmpAll="document.write('"+tmpAll+"');";
				}
//				System.out.println("下面结果");
//				System.out.println(tmpAll);
//				 out.println("document.write(\""+ps_tmp+"\");");
				response.setCharacterEncoding(getText("lerx.charset"));
				response.setContentType("text/html;charset="+getText("lerx.charset"));
				response.getWriter().write(tmpAll);
			}
		}else{
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset="+getText("lerx.charset"));
			response.getWriter().write("style not found or err");
		}
		
		
		
		
	}
	
	private FormatElements elSiteInit(){
		FormatElements el = new FormatElements ();
		el.setAs(this);
		el.setArticleThreadDaoImp(articleThreadDaoImp);
		el.setRequest(request);
		return el;
	}

}
