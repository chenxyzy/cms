package com.lerx.web.util;

import java.io.IOException;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadPost {
	public static String show(WebElements wel) throws IOException{
//		boolean titleAreaShow = false;
		wel.setRefererRec(false);
		IBbsForumDao bbsForumDaoImp = wel.getBbsForumDaoImp();
		IBbsThemeDao bbsThemeDaoImp = wel.getBbsThemeDaoImp();
		ActionSupport as=wel.getAs();
		BbsStyle curBbsStyle=wel.getCurBbsStyle();
		int fid=wel.getFid();
		long tid = wel.getTid();
		int scrollPos=wel.getScrollPos();
//		System.out.println("asdfasdfa");
//		int pageSize=wel.getPageSize();
//		int page=wel.getPage();
		
		BbsForum f;
		
		//下面五行顺序不能错
		wel=BbsUtil.initBbsElement(wel, curBbsStyle.getEditThreadStyle());
		if (tid < 1) {
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
					as.getText("lerx.bbsThreadAddTitle")));
		} else {
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
					as.getText("lerx.bbsThreadEditTitle")));
		}
		wel=SiteInit.reInit(wel);
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		wel=BbsUtil.endBbsService(wel);
		
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		ResultEl re=wel.getRe();
		
		int pageSize=wel.getPageSize();
		if (pageSize==0 && wel.getBi().getPageSizeOfTheme()>0){
			pageSize=wel.getBi().getPageSizeOfTheme();
		}
		int page=wel.getPage();
//		BbsStyleSubElementInCommon bel=wel.getBel();
		UserCookie uc=wel.getUc();
		User u=null;
		if (uc!=null){
			u = wel.getUserDaoImp().findUserById(uc.getUserId());
		}
		
		boolean postAllow=true;
		BbsInfo bi = wel.getBbsInfoDaoImp().query();
		if (u==null || !u.isState() || (bi.isPostMustInGroup() && u.getUserGroup()==null)){
			postAllow=false;
		}
		
		
		
		
		if (postAllow) {
			
			
			String rootFolder;
			rootFolder=wel.getSiteStyleDaoImp().findDef().getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(as);
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("replyMust.txt");
			rfv.setSubFolder("html");
			
			String replyMustTxt = FileUtil.readFile(rfv);
			
			

			if (tid < 1 && fid < 1) {
				re.setMes(as.getText("lerx.err.forum.notSelected"));
				re.setMod(2);
				re.setRefererUrl(PubUtil.refCheck(wel,1));
				htmlTemplate=ResultPage.init(re);
				
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$editThreadCode$$}",
						curBbsStyle.getEditThemeAreaCode());
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$parentId$$}", "" + tid);
				

				if (tid > 0) {
//					System.out.println("rrr");
					
					BbsTheme t = bbsThemeDaoImp.findThemeById(tid);
					f = bbsForumDaoImp.findBbsForumById(t.getForum().getId());
					
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$otid$$}", "" + tid);
					
					if (t.getRootTheme() == null) {
						if (t.isSeeAfterReply()){
//							System.out.println("111replyMustTxt:"+replyMustTxt);
							replyMustTxt = StringUtil.strReplace(replyMustTxt,
									"{$$seeAfterReply$$}"," checked ");
//							System.out.println("222replyMustTxt:"+replyMustTxt);
						}else{
							replyMustTxt = StringUtil.strReplace(replyMustTxt,
									"{$$seeAfterReply$$}","");
						}
						
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$replyMustTxt$$}",replyMustTxt);
						
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$tid$$}", "0");
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$title$$}", t.getTitle());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$disabled$$}", "");
					} else {
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$replyMustTxt$$}","");
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$tid$$}", ""+t.getRootTheme().getId());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$title$$}", t.getRootTheme().getTitle());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$disabled$$}", "disabled");

					}

					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$body$$}", t.getBody());
				} else {
					f = bbsForumDaoImp.findBbsForumById(fid);
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$replyMustTxt$$}",replyMustTxt);
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$tid$$}", "0");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$otid$$}", "0");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$title$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$body$$}", "");
				}
				
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$page$$}", ""
						+ page);
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageSize$$}", ""
						+ pageSize);
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$scrollPos$$}", ""
						+ scrollPos);
				
				
				//安全
				
				System.currentTimeMillis();
				String secStr = null;
				String randKey = StringUtil.randomString(6).toLowerCase();
				try {
					secStr = SrvInf.readSecStr(wel.getRequest(), as.getText("lerx.hostSecFile"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (secStr != null) {
					secStr = StringUtil.md5(secStr.concat(randKey)).toLowerCase();
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$secStr$$}", secStr);
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$randKey$$}", randKey);
				}
				
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$fid$$}", ""+f.getId());
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$forumName$$}", f.getForumName());

			}
			
			

		
		}else{
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
			re.setRefererUrl(PubUtil.refCheck(wel,1));
			htmlTemplate=ResultPage.init(re);

		
		}
		return htmlTemplate;
	}
}
