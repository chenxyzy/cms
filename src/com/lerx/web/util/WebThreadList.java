package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadList {
	@SuppressWarnings("unchecked")
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(true);
		//从wel中取值，以防用过多的get方法
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
		int speedMod=Integer.valueOf(as.getText("lerx.data.query.mod"));
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
//		int smode=wel.getSmode();
//		int umode=wel.getUmode();
		int stateMode=wel.getStateMode();
		int mod=wel.getMod();
		boolean notice=wel.isNotice();
		int soul =wel.getSoul();
		ArticleGroup g = articleGroupDaoImp.findById(gid);
		
		String gStr;
		if (gid == 0) {
			if (stateMode==2){
				gStr = as.getText("lerx.articlesNoPassedListTitle");
			}else{
				gStr = as.getText("lerx.articlesAllListTitle");
			}
		} else {
			gStr = g.getGroupName() + " " + as.getText("lerx.articlesListTitle");
		}
		
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getGenericStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				gStr));
		wel=SiteUtil.endSiteService(wel);
		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		fel.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
//		ResultEl re=wel.getRe();
		
		String hrefLineFormat=wel.getHrefLineFormat();
//		String strFormat=curSiteStyle.getHrefLineFormatWithSnStrOverAll();
		
		
		
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		String lf, tmpA = "",pageShowStr;
		boolean editCodeShow = false;
		int ck=SiteUtil.checkUserOnSite(wel,g);
		if (stateMode==2 && ck<2){
			pageShowStr="";
			tmpA=as.getText("lerx.fail.auth");
		}else{
			if (ck < 2) {
				if (stateMode != 1) {
					stateMode = 1;
				}
			} else {
				editCodeShow = true;
			}
			
			Rs rs = articleThreadDaoImp.findByGroupAndMod(gid, page,
					pageSize, mod, stateMode, notice, soul,0,false,speedMod);
			
			List<Long> lan = null;
			List<ArticleThread> lat = null;
			if (speedMod==1){
				lat=(List<ArticleThread>) rs.getList();
			}else{
				lan = (List<Long>) rs.getList();
			}
			
			
			int step = 0;
			fel.setTitleLength(0);
			fel.setIncludeEditArea(editCodeShow);
			fel.setEyeCatchingStrCode(curSiteStyle.getEyeCatchingCode());
			ArticleThread at;
			if (speedMod==1){
				for (ArticleThread ati : lat) {
					step++;
					at=ati;
					fel.setLf(hrefLineFormat);
					lf = ThreadUtil.formatHref(fel, at);
					lf = StringUtil.strReplace(lf, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmpA += lf;
				}
			}else{
				for (Long atid : lan) {
					step++;
					at=articleThreadDaoImp.findById(atid);
					fel.setLf(hrefLineFormat);
					lf = ThreadUtil.formatHref(fel, at);
					lf = StringUtil.strReplace(lf, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmpA += lf;
				}
			}
			
			
			pageShowStr=RsInit.rsPageStrShow(rs, "?gid=" + gid, PubUtil.pageFormatShowInit(as, wel),
					false);
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$location$$}", gStr);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$genericDataBody$$}", tmpA);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
				"listMain");
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				gStr);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}",
				pageShowStr);
		
		return htmlTemplate;
	}
}
