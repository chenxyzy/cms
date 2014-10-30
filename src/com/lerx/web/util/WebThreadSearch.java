package com.lerx.web.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadSearch {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		//从wel中取值，以防用过多的get方法
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
//		int smode=wel.getSmode();
//		int umode=wel.getUmode();
		long uid=wel.getUid();
		String key=wel.getKey();
		String keyCon=wel.getKeyCon();
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getSearchStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.searchPageTitle")));
		wel=SiteUtil.endSiteService(wel);
		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		String hrefLineFormat= wel.getHrefLineFormat();
		wel=SiteInit.reInit(wel);
		fel.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
		ResultEl re=wel.getRe();
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		UserCookie uc=wel.getUc();
		
		if (uid == 0) {
			if (uc != null) {
				uid = uc.getUserId();
			}

		}
		String tmpStr = "";
		if ((key == null || key.trim().equals("")) && keyCon != null
				&& !keyCon.trim().equals("")) {
			
			key = URLDecoder.decode(keyCon, as.getText("lerx.charset"));
			key=StringUtil.htmlFilter(key);
		}
		
		if (key != null && !key.trim().equals("")) {
			keyCon = URLEncoder.encode(key, as.getText("lerx.charset"));
			keyCon=StringUtil.htmlFilter(keyCon);
		}

		tmpStr = "?keyCon=" + keyCon;

		boolean searchMustBeMember;
		if (as.getText("lerx.searchMustBeMember").trim().equalsIgnoreCase("true")) {
			searchMustBeMember = true;
		} else {
			searchMustBeMember = false;
		}
		String lf, tmpA, pageShowStr;
		ArticleThread at;
		if ((uid > 0 && searchMustBeMember) || !searchMustBeMember) {

			if (key == null || key.trim().equals("")) {
				tmpA = "";
				pageShowStr = "";
			} else {

				Rs rs = articleThreadDaoImp.search(key, gid, page,
						pageSize, false);
				pageShowStr = RsInit.rsPageStrShow(rs, tmpStr,
						PubUtil.pageFormatShowInit(as, wel), false);
				@SuppressWarnings("unchecked")
				List<Long> na = (List<Long>) rs.getList();
				tmpA = "";
				int step = 0;
				fel.setTitleLength(0);
				fel.setIncludeEditArea(false);
				fel.setEyeCatchingStrCode(curSiteStyle.getEyeCatchingCode());
				for (Long aid : na) {
					step++;
					at=articleThreadDaoImp.findById(aid);
					at=ThreadUtil.escape(at);
					fel.setLf(hrefLineFormat);
					lf = ThreadUtil.formatHref(fel, at);
					lf = StringUtil.strReplace(lf, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmpA += lf;
				}
			}


			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$searchDataBody$$}", tmpA);

			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
					"searchMain");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", as.getText("lerx.searchPageTitle"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pageShowStr$$}", pageShowStr);

		} else {
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
			re.setRefererUrl(PubUtil.refCheck(wel,0));
			htmlTemplate=ResultPage.init(re);
			
		}
		return htmlTemplate;
	}
}
