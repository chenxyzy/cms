package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadArticleAdd {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(true);
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		int gid=wel.getGid();
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getArticleAddStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.articleAddPageTitle")));
		wel=SiteUtil.endSiteService(wel);
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		ResultEl re=wel.getRe();
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		UserCookie uc=wel.getUc();
		boolean con=true;
		User u=null;
		if (uc!=null){
			u = userDaoImp.findUserById(uc.getUserId());
			if (!u.isState()){
				con=false;
			}
			if (u==null || u.getUserGroup()==null){
				con=false;
			}
		}else{
			con=false;
		}
		if (con && u!=null) {
			UserGroup ug=u.getUserGroup();
			String agSelectStr=ug.getSiteArticleGroupsSelectCustomStr();
			if (agSelectStr!=null && !agSelectStr.trim().equals("")){
				agSelectStr=StringUtil.strReplace(agSelectStr, ",", "$$},{$$");
				agSelectStr="{$$"+agSelectStr+"$$}";
			}else{
				agSelectStr=null;
			}
			String selectOptionFormat, soAllStr = "";
			
			String rootFolder;
			rootFolder=curSiteStyle.getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(as);
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("selectLoop.txt");
			rfv.setSubFolder("html");
			
			selectOptionFormat = FileUtil.readFile(rfv);
			
			List<ArticleGroupShowModel> it = articleGroupDaoImp
					.findAllModel(as.getText("lerx.treePrefixHead"),
							as.getText("lerx.treePrefixBody"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", as.getText("lerx.articleAddPageTitle"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$loginUser$$}", uc.getUsername());
			if (!it.isEmpty()) {

				
				soAllStr = ArticleGroupUtil.createSelectStr(wel, as, it, agSelectStr, selectOptionFormat, gid);
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$articleGroupSelect$$}", soAllStr);
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$mediaCodeFormat1$$}", as.getText("lerx.mediaCodeFormat1"));
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$mediaCodeFormat2$$}", as.getText("lerx.mediaCodeFormat2"));
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$mediaCodeFormat3$$}", as.getText("lerx.mediaCodeFormat3"));

			}
			
		}else{
			
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			re.setRefererUrl(wel.getRequest().getContextPath()+"/login.action");
//			re.setRefererUrl(SiteInit.refCheck(wel,0));
			htmlTemplate=ResultPage.init(re);
		}
		
		return htmlTemplate;
	}
}
