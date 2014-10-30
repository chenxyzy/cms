package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.web.util.camp.IndexUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.IndexAgExeElement;
import com.lerx.web.vo.IndexAgExeReturn;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadIndex {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		//从wel中取值，以防用过多的get方法
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		ActionSupport as=wel.getAs();
		
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		int speedMod=Integer.valueOf(as.getText("lerx.data.query.mod"));
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getIndexStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.indexTitle")));
		wel=SiteUtil.endSiteService(wel);
		FormatElements fel=wel.getFel();
		fel.setAs(wel.getAs());
		String htmlTemplate = wel.getHtmlTemplate();
		
		//主体开始
		
		String lf, tmpA;
		// 取公告
		int afficheCountOnIndex = Integer
				.valueOf(as.getText("lerx.afficheCountOnIndex"));
		if (afficheCountOnIndex == 0) {
			afficheCountOnIndex = Integer
					.valueOf(as.getText("lerx.pageSize.result.default"));
		}
		int recNewMode=Integer.valueOf(as.getText("lerx.recNewMode"));
		
		
		tmpA = "";
		
		fel.setEditAreaCode(null);
		fel.setIncludeEditArea(false);
		fel.setTitleLength(0);
		fel.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
		
		fel.setEyeCatchingStrCode(curSiteStyle.getEyeCatchingCode());
		//查询公告
		if (htmlTemplate.indexOf("{$$staticAfficheCode$$}") != -1) {
			Rs ns = articleThreadDaoImp.findByGroupAndMod(0, 0,
					afficheCountOnIndex, recNewMode, 1, true, 0,0,false,speedMod);
			@SuppressWarnings("unchecked")
			List<Long> na = (List<Long>) ns.getList();
			ArticleThread at;
			if (!na.isEmpty()){
				
				for (Long atid : na) {
					fel.setLf(curSiteStyle.getHrefLineFormatStrOverAll());
					at=articleThreadDaoImp.findById(atid);
					lf = ThreadUtil.formatHref(fel, at);
					tmpA += lf;
				}
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$staticAfficheCode$$}", tmpA);
			}
			
		}
		
		// 取首页列表区域文章
		tmpA = "";
		List<ArticleGroup> l = articleGroupDaoImp.findShowOnIndex(0);
		
		if (!l.isEmpty()) {
			IndexAgExeElement iae=new IndexAgExeElement();
			iae.setArticleThreadDaoImp(articleThreadDaoImp);
			iae.setAs(as);
			iae.setCurSiteStyle(curSiteStyle);
			iae.setFel(fel);
			IndexAgExeReturn iaer;
			for (ArticleGroup ag : l) {
				iae.setAg(ag);
				iae.setHtmlTemplate(htmlTemplate);
				iaer=IndexUtil.htmlCreateByNav(iae);
				htmlTemplate = IndexUtil.rep(htmlTemplate,ag.getId(),iaer);
				String customTemp;
				for (int stepTmp = 1; stepTmp <= 8; stepTmp++) {
					fel.setLf(curSiteStyle.getHrefLineFormatStrOverAll());
					if (htmlTemplate.indexOf("{$$customFormatIndex,"
							+ ag.getId()+",") != -1) {
						customTemp=ThreadUtil.customFormat(fel, stepTmp,
								ag.getId(), curSiteStyle, 1,
								ag.getNumberShowOnIndex(),0);
						htmlTemplate = StringUtil.strReplace(
								htmlTemplate,
								"{$$customFormatIndex," + ag.getId() + ","
										+ stepTmp + "$$}",customTemp);
					}

				}
				

			}
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				as.getText("lerx.indexTitle"));
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$gid$$}", "0");
		return htmlTemplate;
	}
	
	
	
	
	
}
