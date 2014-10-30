package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadNavAll {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(true);
		//从wel中取值，以防用过多的get方法
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
		HttpServletRequest request=wel.getRequest();
		
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getGenericStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.navAllTitle")));
		wel=SiteUtil.endSiteService(wel);
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$location$$}", as.getText("lerx.navAllTitle"));
		
		String href;
		String soStr, soAllStr = "";
		boolean staticMod;
		if (wel.getSite().getStaticHtmlMode() == 2){
			staticMod=true;
		}else{
			staticMod=false;
		}
		List<ArticleGroupShowModel> it = articleGroupDaoImp
				.findAllModel(as.getText("lerx.treePrefixHead"),
						as.getText("lerx.treePrefixBody"));
		if (!it.isEmpty()) {

			for (ArticleGroupShowModel m : it) {
				if (m.getArticleGroup().isState()) {
					href = ArticleGroupUtil.findFolder(m.getArticleGroup(), staticMod, as, request);
//					if (site.getStaticHtmlMode() == 2
//							&& !m.getArticleGroup().isRefuseStaticHtml()) {
//						gHtmlFolder = m.getArticleGroup().getStaticHtmlFolder();
//						if (gHtmlFolder == null
//								|| gHtmlFolder.trim().equals("")) {
//							gHtmlFolder = staticHtmlRoot + File.separator + "c"
//									+ m.getArticleGroup().getId();
//						} else {
//							gHtmlFolder = staticHtmlRoot + File.separator
//									+ gHtmlFolder;
//						}
//						href = request.getContextPath() + "/" + gHtmlFolder;
//					} else {
//						href = request.getContextPath()
//								+ "/"
//								+ as.getText("lerx.articleGroupPageFileName")
//										.trim() + "?gid="
//								+ m.getArticleGroup().getId();
//					}

					soStr = "<p>{$$display$$}</p>";
					soStr = StringUtil.strReplace(soStr, "{$$value$$}", ""
							+ m.getArticleGroup().getId());
					soStr = StringUtil.strReplace(soStr, "{$$display$$}",
							m.getPrefixStr() + href);
					soAllStr += soStr;
				}

			}
		
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$genericDataBody$$}", soAllStr);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}",
				"");
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
				"navAllMain");
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				as.getText("lerx.navAllTitle"));
		return htmlTemplate;
		
	}
}
