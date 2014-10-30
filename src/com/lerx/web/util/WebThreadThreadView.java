package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadThreadView {
	public static String show(WebElements wel) throws IOException {
		boolean titleAreaShow = false;
		wel.setRefererRec(false);

//		IBbsForumDao bbsForumDaoImp = wel.getBbsForumDaoImp();
		IBbsThemeDao bbsThemeDaoImp = wel.getBbsThemeDaoImp();
		IBbsStyleDao bbsStyleDaoImp = wel.getBbsStyleDaoImp();
		ActionSupport as = wel.getAs();
		BbsStyle curBbsStyle = wel.getCurBbsStyle();
		int mod = wel.getMod();
		int page = wel.getPage();
		int pageSize = wel.getPageSize();
		
//		String curIP = IpUtil.getRealRemotIP(wel.getRequest()).trim();

		if (pageSize == 0 && wel.getBi().getPageSizeOfTitle() > 0) {
			pageSize = wel.getBi().getPageSizeOfTitle();
		}

		// 下面五行顺序不能错
		wel = BbsUtil.initBbsElement(wel, curBbsStyle.getGenericStyle());
		/*
		 * 下行以后再说
		 */
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
				"{$$app$$}", "views"));

		wel = SiteInit.reInit(wel);
		LoginCheckEl lcel = PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());

		wel = BbsUtil.endBbsService(wel);

		FormatElements fel = wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();

		BbsStyleSubElementInCommon bel = wel.getBel();
//		UserCookie uc = wel.getUc();
		boolean th=false;
		String location;
		long uid=0;
		switch (mod) {
		case 1:				//热贴
			location = as.getText("lerx.bbs.pageTitleOfHot");
			break;
		case 2:				//新帖
			location = as.getText("lerx.bbs.pageTitleOfNewThread");
			break;
		case 3:				//新回复帖
			location = as.getText("lerx.bbs.pageTitleOfNewRip");
			break;
		case 4:				//我的主题
			location = as.getText("lerx.bbs.pageTitleOfMyThread");
			th=true;
			uid=wel.getUc().getUserId();
			break;
		case 5:				//我参与的
			location = as.getText("lerx.bbs.pageTitleOfMyPartIn");
			uid=wel.getUc().getUserId();
			break;
		default:
			location = "";
			break;
		}
		// String location = SiteInit.curBbsLocation(wel, null); // 当前位置
//		String tmpBasicAll = "";
//		String loofBasiclf = StringUtil
//				.nullFilter(bel.getMinorLoopCodeInLump());
		String loopBracketLf = StringUtil.nullFilter(bel
				.getMajorLoopCodeInLump());
		// 子版块显示
		fel.setBbsStyleDaoImp(wel.getBbsStyleDaoImp());

//		if (titleAreaShow && bel.getTitleOfMinorLoopCodeInLump() != null) {
//			htmlTemplate = StringUtil.strReplace(htmlTemplate,
//					"{$$titleOfLoopBasicLevelNavInLump$$}", bel
//							.getTitleOfMinorLoopCodeInLump().trim());
//		} else {
//			htmlTemplate = StringUtil.strReplace(htmlTemplate,
//					"{$$titleOfLoopBasicLevelNavInLump$$}", "");
//		}
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$titleOfLoopBasicLevelNavInLump$$}", "");
		titleAreaShow = false;
		Rs rs;
		
		if (uid==0){
			rs = bbsThemeDaoImp.findThemesByRule(0, 0, mod, page, pageSize,SysUtil.hsValue(as));

		}else{
			rs = bbsThemeDaoImp.findThemesByUserId(uid, page, pageSize, true, th);

		}
		
		String pageShow = RsInit.rsPageStrShow(rs, wel.getRequest()
				.getContextPath() + "/thview.action?mod=" + mod,
				PubUtil.pageFormatShowInit(as, wel), false);

		@SuppressWarnings("unchecked")
		List<Long> ls = (List<Long>) rs.getList();

		String tmp, tmpAll = "";
		BbsTheme bt;
		FormatElements el = new FormatElements();
		el.setAs(wel.getAs());
		el.setUserDaoImp(wel.getUserDaoImp());
		el.setRequest(wel.getRequest());
		el.setBbsThemeDaoImp(bbsThemeDaoImp);
		el.setBbsStyleDaoImp(bbsStyleDaoImp);
		for (Long tid : ls) {
			el.setLf(loopBracketLf);
			bt = bbsThemeDaoImp.findThemeById(tid);

			tmp = ThemeUtil.formatHref(el, bt);
			tmpAll += tmp;
		}

		if (titleAreaShow && bel.getTitleOfMajorLoopCodeInLump() != null) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$titleOfLoopBracketCodeInLump$$}", bel
							.getTitleOfMajorLoopCodeInLump().trim());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$titleOfLoopBracketCodeInLump$$}", "");
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$threadsLoop$$}",
				tmpAll);

		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}",
				pageShow);

		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				location);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$editThemeButtomTxt$$}",
				as.getText("lerx.txtOfBbsAddThemeButtom").trim());

		return htmlTemplate;
	}
}
