package com.lerx.web.util;

import java.io.IOException;

import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadLogin {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(true);
		//从wel中取值，以防用过多的get方法
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
//		HttpServletRequest request=wel.getRequest();
		
		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
//		int pageSize=wel.getPageSize();
//		int page=wel.getPage();
//		int gid=wel.getGid();
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getLoginStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.loginTitle")));
		wel=SiteUtil.endSiteService(wel);
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		ResultEl re=wel.getRe();
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$location$$}", as.getText("lerx.loginTitle"));
		if (site.isUserLoginAllow()) {
			
		}else{
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			re.setMes(as.getText("lerx.fail.login.forbidden"));
			re.setMod(2);
			re.setRefererUrl(PubUtil.refCheck(wel,0));
//			ResultEl re=reInit(refCheck(0),"loginForbidden",0,0);
			htmlTemplate=ResultPage.init(re);
		}
		return htmlTemplate;
	}
}
