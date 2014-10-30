package com.lerx.web.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.util.ForumUtil;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.util.BbsStyleUtil;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.IcoUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.BbsThemeCheckUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadThread {
	public static String show(WebElements wel) throws IOException{
		String htmlTemplate;
		wel.setRefererRec(false);
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		IBbsForumDao bbsForumDaoImp = wel.getBbsForumDaoImp();
		IBbsThemeDao bbsThemeDaoImp = wel.getBbsThemeDaoImp();
		IBbsBMDao bbsBMDaoImp=wel.getBbsBMDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		ActionSupport as=wel.getAs();
		
		boolean toEnd =  wel.isToEnd();
		int scrollPos=wel.getScrollPos();
		
		BbsThemeCheckUtil btcu=new BbsThemeCheckUtil();
		
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
//		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		BbsStyle curBbsStyle=wel.getCurBbsStyle();
		btcu.setBbsStyleDaoImp(wel.getBbsStyleDaoImp());
		String quoteButtomCode=curBbsStyle.getQuoteButtomCode();
		quoteButtomCode=StringUtil.strReplace(quoteButtomCode, "{$$quoteText$$}", as.getText("lerx.quoteText"));
		long tid=wel.getTid();
		int pageSize=wel.getPageSize();
		if (pageSize==0 && wel.getBi().getPageSizeOfTheme()>0){
			pageSize=wel.getBi().getPageSizeOfTheme();
		}
		int page=wel.getPage();
		BbsTheme theme = bbsThemeDaoImp.findThemeById(tid);
		if (theme == null || theme.getRootTheme() != null) { // 如果不是主帖返回参数错误
			htmlTemplate=PubUtil.errKey(wel.getFra(), "lerx.err.parameter");
			return htmlTemplate;
		}else{
			if (toEnd){
				if (pageSize==0){
					int pz=wel.getBi().getPageSizeOfTheme();
					if (pz>0){
						pageSize=pz;
					}else{
						pageSize=Integer.valueOf(as.getText("lerx.pageSize.result.default"));
					}
				}
				
				page=bbsThemeDaoImp.pageCountByRootThemeId(tid, pageSize);
			}
		}
		BbsForum f = bbsForumDaoImp.findBbsForumById(theme.getForum().getId());
		
		boolean show=true;
		String curIP = IpUtil.getRealRemotIP(wel.getRequest()).trim();
		
		if (f != null) {
			List<BbsForum> ltmp = bbsForumDaoImp.findIPRange(f);
			if (ltmp != null) {
				if (!ForumUtil.ipRangeCheck(curIP, ltmp)) {
					show = false;
				}
			}

		}
		
//		wel=SiteInit.endBbsService(wel);
		//下面五行顺序不能错
		wel=BbsUtil.initBbsElement(wel, curBbsStyle.getThemeStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				theme.getTitle()));
		
		wel=SiteInit.reInit(wel);
		
		
		String location = BbsUtil.curBbsLocation(wel,f); // 当前位置
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		wel=BbsUtil.endBbsService(wel);
		
		FormatElements fel=wel.getFel();
		fel.setBbsStyleDaoImp(wel.getBbsStyleDaoImp());
		fel.setUserDaoImp(wel.getUserDaoImp());
		fel.setShieldedShowCode(curBbsStyle.getShieldedShowCode());
		fel.setAfterReplyShowCode(curBbsStyle.getAfterReplyShowCode());
//		System.out.println("fel.getAfterReplyShowCode():"+fel.getAfterReplyShowCode());
		String icoFolder=BbsStyleUtil.icoFolder(curBbsStyle, 0);
		String icoActFolder=icoFolder+"act/";
		String icoStaFolder=icoFolder+"sta/";
		htmlTemplate = wel.getHtmlTemplate();
		if (!show) {
			
			htmlTemplate = as.getText("lerx.access.denied");
		} else {
			wel=SiteInit.reInit(wel);
			UserCookie uc=wel.getUc();
			BbsStyleSubElementInCommon bel=wel.getBel();
			String tmpBracket, tmpBracketAll = "";
			String loofBasiclf = StringUtil.nullFilter(bel.getMinorLoopCodeInLump());
			String loopBracketLf = StringUtil.nullFilter(bel
					.getMajorLoopCodeInLump());
			
			
			
			String lastViewIP = theme.getLastViewIp();
			if (lastViewIP==null || !curIP.trim().equals(lastViewIP.trim())){
				theme.setViews(theme.getViews()+1);
				theme.setLastViewIp(curIP);
				theme.setLastViewTime(new Timestamp(System.currentTimeMillis()));
				bbsThemeDaoImp.modifyBbsTheme(theme);
			}
			
			
			User user=null;
			
			if (uc!=null){
				user=userDaoImp.findUserById(uc.getUserId());
			}
			btcu.setUser(user);
			btcu.setBbsForumDaoImp(bbsForumDaoImp);
			btcu.setBbsBMDaoImp(bbsBMDaoImp);
			btcu.setEditAreaCode(fel.getEditAreaCode());
			btcu.setFunctionAreaCode(fel.getFunctionAreaCode());
			btcu.setQuoteButtomCode(quoteButtomCode);
			btcu.setBi(wel.getBi());
			btcu.setAs(wel.getAs());
			btcu.setIcoFolder(icoFolder);
			
			if (loofBasiclf == null || loofBasiclf.trim().equals("")) {
				loofBasiclf = loopBracketLf;
			}
			
			
			
		
			boolean sortMod;
			if (Integer.valueOf(as.getText("lerx.bbsThemeSortMod")) == 0) {
				sortMod = true;
			} else {
				sortMod = false;
			}
			Rs rs = bbsThemeDaoImp.findThemesAndOwnByParentThemeId(tid, page, pageSize,
					sortMod);
			
			
			@SuppressWarnings("unchecked")
			List<Long> nl = (List<Long>) rs.getList();
			int step = 0;
			if (page>1){
				step=((page - 1) * pageSize);
			}
			
			if (!nl.isEmpty()) {
				// TODO this

				fel.setIncludeEditArea(true);
				fel.setTitleLength(0);
				for (Long themesId : nl) {
					step++;
					BbsTheme t=bbsThemeDaoImp.findThemeById(themesId);
					
					btcu.setTheme(t);
					
					
					if ((themesId-tid) ==0){
						tmpBracket = loofBasiclf;
						btcu.setStr(tmpBracket);
						tmpBracket=ThemeUtil.codeRepByUser(btcu);
						fel.setLf(tmpBracket);
						fel.setUc(uc);
						tmpBracket = ThemeUtil.formatHref(fel, t);
						fel.setLf(tmpBracket);
						//如果是第一页第一行，就是主题帖
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$floorNum$$}", "1");
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$topActCode$$}", curBbsStyle.getTopActCode());
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$topAct$$}", ""+!t.isTop());
						if (t.isTop()){
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$icoTopAct$$}", icoActFolder+IcoUtil.read(as, "top", 2));
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$topActText$$}", wel.getAs().getText("lerx.bbs.untopAct"));
						}else{
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$icoTopAct$$}", icoActFolder+IcoUtil.read(as, "top", 1));
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$topActText$$}", wel.getAs().getText("lerx.bbs.topAct"));
						}
						if (t.isSink()){
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$sinkActTxt$$}", btcu.getAs().getText("lerx.bbs.unsinkAct"));
							
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$icoSink$$}", icoActFolder+IcoUtil.read(as, "sink", 1));
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$sinkAct$$}", "false");
							
						}else{
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$sinkActTxt$$}", btcu.getAs().getText("lerx.bbs.sinkAct"));
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$icoSink$$}", icoActFolder+IcoUtil.read(as, "sink", 2));
							tmpBracket = StringUtil.strReplace(tmpBracket, "{$$sinkAct$$}", "true");
						}
						
						String rootFolder;
						rootFolder=wel.getSiteStyleDaoImp().findDef().getRootResFolder();
						ReadFileArg rfv=new ReadFileArg();
						rfv.setAs(as);
						rfv.setRequest(wel.getRequest());
						rfv.setRootFolder(rootFolder);
						rfv.setFileName("priTheme.txt");
						rfv.setSubFolder("html");
						
						String txt = FileUtil.readFile(rfv);
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$priSpecificCode$$}", txt);
						
					}else{
						
						tmpBracket = loopBracketLf;
						btcu.setStr(tmpBracket);
						tmpBracket=ThemeUtil.codeRepByUser(btcu);
						fel.setLf(tmpBracket);
						fel.setUc(uc);
						tmpBracket = ThemeUtil.formatHref(fel, t);
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$topActCode$$}", "");
						tmpBracket = StringUtil.strReplace(tmpBracket, "{$$priSpecificCode$$}", "");
					}
					
					
					
					fel.setLf(tmpBracket);
					
					tmpBracket = UserUtil.formatHref(fel, t.getUser().getId());

					tmpBracket = StringUtil.strReplace(tmpBracket,
							"{$$floorNum$$}", "" + step);
					tmpBracket = StringUtil.strReplace(tmpBracket,
							"{$$floorNum-1$$}", "" + (step-1));
					
					tmpBracket = StringUtil.strReplace(tmpBracket,
							"{$$posId$$}", "pos_theme_"+t.getId());
					
					
					
					String pollTxt = pollFmt(wel,btcu,t);
					tmpBracket = StringUtil.strReplace(tmpBracket,
							"{$$pollArea$$}", pollTxt);
					
					tmpBracketAll += tmpBracket;
				}
			}
			//Rs rs=new Rs();
			
			String pageShow=RsInit.rsPageStrShow(rs, wel.getRequest().getContextPath()
					+ "/"
					+ as.getText(
							"lerx.bbsThreadPageFileName")
							.trim()
					+ "?tid=" + tid, PubUtil.pageFormatShowInit(as, wel), false);

			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$themesLoop$$}",
					tmpBracketAll);
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
				String replieThreadAreaCode = curBbsStyle.getReplieThreadAreaCode();
				fel.setLf(replieThreadAreaCode);
				replieThreadAreaCode = UserUtil.formatHref(fel, uc.getUserId());
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$replieThreadAreaCode$$}", replieThreadAreaCode);
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$replieThreadAreaCode$$}", "");
			}
			
			if (step>1){
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}", pageShow);

			}else{
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}", "");

			}
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$tid$$}", ""
					+ theme.getId());
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$page$$}", ""
					+ page);
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageSize$$}", ""
					+ pageSize);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$fid$$}", ""
					+ theme.getForum().getId());
			fel.setLf(htmlTemplate);
			
			
			
			
			
			
//			System.out.println("location:"+location);
			
			htmlTemplate = UserUtil.formatHref(fel, theme.getUser().getId());
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
					location);
			
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoActFloder$$}",
					icoActFolder);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoStaFloder$$}",
					icoStaFolder);
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoFloder$$}",
					icoFolder);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$toEnd$$}",
					toEnd+"");
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$scrollPos$$}",
					scrollPos+"");
			
			
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
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoFolderUrl$$}",
				curBbsStyle.getIcoFolderUrl());
		return htmlTemplate;
	}
	
	private static String pollFmt(WebElements wel,BbsThemeCheckUtil btcu,BbsTheme t){
		
		String code = "";
		BbsForum f=wel.getBbsForumDaoImp().findLastPollParent(wel.getBbsForumDaoImp().findBbsForumById(t.getForum().getId()));
		if (ForumUtil.pollChk(f)){
			String txt="";
			String rootFolder,pollYes,pollNo,pollNeu;
			rootFolder = wel.getSiteStyleDaoImp().findDef().getRootResFolder();
			ReadFileArg rfv = new ReadFileArg();
			rfv.setAs(wel.getAs());
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setSubFolder("act");
			if (ThemeUtil.powerCheck(btcu)) {
				
				rfv.setFileName("bbsPoll.txt");
				txt = FileUtil.readFile(rfv);
			}
			SiteStyle curStyle = wel.getSiteStyleDaoImp().findDef();
			
			if (t.isPollAllow()) {
				txt = StringUtil.strReplace(txt, "{$$title$$}",
						wel.getAs().getText("lerx.title.close"));
				code = curStyle.getAjaxOfArtPoll();
				code = StringUtil.strReplace(code, "{$$pollFunction$$}", txt);
				code = StringUtil.strReplace(code, "{$$st$$}", "false");
				if (ForumUtil.pollFmt(f, 1)){
					pollYes =rfvRead(rfv,"pollYes.txt");
					code = StringUtil.strReplace(code, "{$$pollYes$$}", pollYes);
				}else{
					code = StringUtil.strReplace(code, "{$$pollYes$$}", "");
				}
				
				if (ForumUtil.pollFmt(f, 2)){
					pollNo=rfvRead(rfv,"pollNo.txt");
					code = StringUtil.strReplace(code, "{$$pollNo$$}", pollNo);
				}else{
					code = StringUtil.strReplace(code, "{$$pollNo$$}", "");
				}
				
				if (ForumUtil.pollFmt(f, 0)){
					pollNeu=rfvRead(rfv,"pollNeu.txt");
					code = StringUtil.strReplace(code, "{$$pollNeu$$}", pollNeu);
				}else{
					code = StringUtil.strReplace(code, "{$$pollNeu$$}", "");
				}
			} else {
				code = txt;
				code = StringUtil.strReplace(code, "{$$title$$}",
						wel.getAs().getText("lerx.title.open"));
				code = StringUtil.strReplace(code, "{$$st$$}", "true");
			}
			code = StringUtil.strReplace(code, "{$$fid$$}", ""
					+ t.getForum().getId());
			code = StringUtil.strReplace(code, "{$$tid$$}", "" + t.getId());
			code = StringUtil.strReplace(code, "{$$proponents$$}",
					"" + t.getProponents());
			code = StringUtil.strReplace(code, "{$$opponents$$}",
					"" + t.getOpponents());
			code = StringUtil.strReplace(code, "{$$neutrals$$}",
					"" + t.getNeutrals());
			code = StringUtil.strReplace(code, "{$$contextPath$$}",
					wel.getRequest().getContextPath());
			code = StringUtil.strReplace(code, "{$$tid$$}", "" + t.getId());
					
		}
		
		//code = StringUtil.strReplace(code, "{$$pos$$}","poll_"+t.getId());
		
		return code;
	}
	
	private static String rfvRead(ReadFileArg rfv,String fileName){
		rfv.setFileName(fileName);
		return FileUtil.readFile(rfv);
	}
	
}
