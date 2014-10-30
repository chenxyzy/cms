package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadMyArticles {
	@SuppressWarnings("unchecked")
	public static String show(WebElements wel) throws IOException{
		
		wel.setRefererRec(false);
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
		int speedMod=Integer.valueOf(as.getText("lerx.data.query.mod"));
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		long uid=wel.getUid();
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
		
		User u;
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		UserCookie uc=wel.getUc();
		int mod = 0;
		boolean admin=false;
		if (SiteUtil.checkUserOnSite(wel,null) == 2) {
			admin=true;
		}
		if (uid == 0) {
			if (uc != null) {
				uid = uc.getUserId();
				mod = 1;
			}

		} 
		u = userDaoImp.findUserById(uid);
		String userName="";
		if (u!=null){
			userName=u.getUserName();
		}else{
			userName="null";
		}
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getGenericStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				userName + as.getText("lerx.myArticlesPageTitle")));
		wel=SiteUtil.endSiteService(wel);
		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		fel.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
		ResultEl re=wel.getRe();
		
		String hrefLineFormat=wel.getHrefLineFormat();
		
		
		ArticleThread at;
		
		if (uid > 0) {
//			System.out.println("aaaa");
			String lf, tmpA;
//			u = userDaoImp.findUserById(uid);

			Rs rs = articleThreadDaoImp.findByUserId(uid,gid, page, pageSize,
					mod,speedMod);
			
			List<Long> lan = null;
			List<ArticleThread> lat = null;
			if (speedMod==1){
				lat=(List<ArticleThread>) rs.getList();
			}else{
				lan = (List<Long>) rs.getList();
			}
			
			tmpA = "";
			int step = 0;
			fel.setTitleLength(0);
			fel.setEyeCatchingStrCode(curSiteStyle.getEyeCatchingCode());
			if (speedMod==1){
				for (ArticleThread ati : lat) {
					step++;
					at=ati;
					if (admin || ((uc != null) && (uc.getUserId()-at.getUser().getId())==0)){
						mod = 1;
					}else{
						mod = 0;
					}
					fel.setLf(hrefLineFormat);
					if (mod == 1) {
						fel.setIncludeEditArea(true);
					} else {
						fel.setIncludeEditArea(false);
					}
					lf = ThreadUtil.formatHref(fel, at);
					lf = StringUtil.strReplace(lf, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmpA += lf;
				}
			}else{
				for (Long aid : lan) {
					step++;
					at=articleThreadDaoImp.findById(aid);
					if (admin || ((uc != null) && (uc.getUserId()-at.getUser().getId())==0)){
						mod = 1;
					}else{
						mod = 0;
					}
					fel.setLf(hrefLineFormat);
					if (mod == 1) {
						fel.setIncludeEditArea(true);
					} else {
						fel.setIncludeEditArea(false);
					}
					lf = ThreadUtil.formatHref(fel, at);
					lf = StringUtil.strReplace(lf, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmpA += lf;
				}
			}
			

			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$genericDataBody$$}", tmpA);
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
					"myArticlesMain");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$userName$$}", userName);
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", u.getUserName()
							+ as.getText("lerx.myArticlesPageTitle"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pageShowStr$$}", RsInit.rsPageStrShow(rs,
							"?uid=" + uid, PubUtil.pageFormatShowInit(as, wel), false));
		
		}else{
			re.setMes(as.getText("lerx.fail.all"));
			re.setMod(2);
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			re.setRefererUrl(wel.getRequest().getContextPath()+"/login.action");
//			re.setRefererUrl(SiteInit.refCheck(wel,0));
			htmlTemplate=ResultPage.init(re);
		}
		return htmlTemplate;
	}
}
