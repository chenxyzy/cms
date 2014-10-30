package com.lerx.web.util;

import java.io.IOException;

import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadUcenter {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		//从wel中取值，以防用过多的get方法
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
//		int pageSize=wel.getPageSize();
//		int page=wel.getPage();
//		int gid=wel.getGid();
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getUserCenterStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.ucenterTitle")));
		wel=SiteUtil.endSiteService(wel);
		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		IInterconnectionDao interconnectionDaoImp=wel.getInterconnectionDaoImp();
		wel=SiteInit.reInit(wel);
		ResultEl re=wel.getRe();
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$location$$}", as.getText("lerx.ucenterTitle"));
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		UserCookie uc=wel.getUc();
		
		System.currentTimeMillis();
		if (uc != null) {
			fel.setLf(htmlTemplate);
			htmlTemplate = UserUtil.formatHref(fel, uc.getUserId());
			
			User u = wel.getUserDaoImp().findUserById(uc.getUserId());
			if (u.isAvatarFileLock()){
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$avatarFileLock$$}", "checked");
			}else{
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$avatarFileLock$$}", "");
			}
			
			if (interconnectionDaoImp.findUserByUid(uc.getUserId(), 1)!=null){
//				System.out.println("aaa");
				String rootFolder;
				rootFolder=curSiteStyle.getRootResFolder();
				ReadFileArg rfv=new ReadFileArg();
				rfv.setAs(as);
				rfv.setRequest(wel.getRequest());
				rfv.setRootFolder(rootFolder);
				rfv.setFileName("icClear.txt");
				rfv.setSubFolder("act");
				String txt = FileUtil.readFile(rfv);
//				System.out.println("txt:"+txt);
				txt = StringUtil.strReplace(txt,
						"{$$href$$}", wel.getRequest().getContextPath()+"/qq_clear.action?user.id="+uc.getUserId()+"&f=fore");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$icClear$$}", txt);
			}else{
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$icClear$$}", "");
			}
		}else{
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
//			re.setRefererUrl(SiteInit.refCheck(wel,0));
			re.setRefererUrl(wel.getRequest().getContextPath()+"/login.action");
//			ResultEl re=reInit(refCheck(0),"loginForbidden",0,0);
			htmlTemplate=ResultPage.init(re);
		}
		
		return htmlTemplate;
	}
}
