package com.lerx.web.util;

import java.io.IOException;

import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.util.SiteStyleUtil;
import com.lerx.style.site.vo.CommentStyleUtil;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadComment {
	public static String show(WebElements wel) throws IOException {
		wel.setRefererRec(true);
		ActionSupport as = wel.getAs();
		String htmlTemplate;
		long tid=wel.getTid();
		ArticleThread a = wel.getArticleThreadDaoImp().findById(tid);
		if (a!=null){
			SiteStyle curSiteStyle = wel.getCurSiteStyle();
			wel=SiteUtil.initSiteElement(wel, curSiteStyle.getCommentStyle());
			
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
					"{$$app$$}", as.getText("lerx.commentTitle")));
			wel = SiteUtil.endSiteService(wel);
			
			wel = SiteInit.reInit(wel);
			LoginCheckEl lcel = PubUtil.logincheck(wel);
			wel.setCdm(lcel.getCdm());
			wel.setUc(lcel.getUc());
			wel.setUserLogined(lcel.isLogined());
			
			CommentStyleUtil csu=new CommentStyleUtil();
			csu.setEditCode(wel.getSel().getEditAreaCode());
			csu.setFormCode(wel.getSel().getFormTemplate());
			csu.setHtml(wel.getSel().getHtmlCode());
			csu.setLineFormat(wel.getSel().getHrefLineFormat());
			
			htmlTemplate = SiteStyleUtil.commentAreaInit(wel, csu,false);
			String synopsis=a.getSynopsis();
			if (synopsis==null || synopsis.trim().equals("")){
				synopsis=StringUtil.nullFilter(a.getBody());
				synopsis=synopsis.trim();
				synopsis=StringUtil.htmlFilter(synopsis);
				if (synopsis.length()>255){
					synopsis=synopsis.substring(0, 255);
				}
			}else{
				synopsis=synopsis.trim();
			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$synopsis$$}", synopsis);
			
		}else{
			htmlTemplate=as.getText("lerx.err.parameter");
		}
		return htmlTemplate;
	}
	
}
