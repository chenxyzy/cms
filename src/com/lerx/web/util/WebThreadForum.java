package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.util.ForumUtil;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadForum {
	public static String show(WebElements wel) throws IOException {
		boolean titleAreaShow = false, show = true;
		wel.setRefererRec(false);
		IBbsForumDao bbsForumDaoImp = wel.getBbsForumDaoImp();
		IBbsThemeDao bbsThemeDaoImp = wel.getBbsThemeDaoImp();
		ActionSupport as = wel.getAs();
		BbsStyle curBbsStyle = wel.getCurBbsStyle();
		int fid = wel.getFid();
		String curIP = IpUtil.getRealRemotIP(wel.getRequest()).trim();
		
		int pageSize = wel.getPageSize();
		if (pageSize == 0 && wel.getBi().getPageSizeOfTitle() > 0) {
			pageSize = wel.getBi().getPageSizeOfTitle();
		}
		int page = wel.getPage();
		BbsForum f = bbsForumDaoImp.findBbsForumById(fid);
		if (f != null) {
			List<BbsForum> ltmp = bbsForumDaoImp.findIPRange(f);
			if (ltmp != null) {
				if (!ForumUtil.ipRangeCheck(curIP, ltmp)) {
					show = false;
				}
			}

		}

		// 下面五行顺序不能错
		wel = BbsUtil.initBbsElement(wel, curBbsStyle.getForumStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
				"{$$app$$}", f.getForumName()));

		wel = SiteInit.reInit(wel);
		LoginCheckEl lcel = PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());

		wel = BbsUtil.endBbsService(wel);

		FormatElements fel = wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		if (!show) {
			htmlTemplate = as.getText("lerx.access.denied");
		} else {

			BbsStyleSubElementInCommon bel = wel.getBel();
			UserCookie uc = wel.getUc();
			String location = BbsUtil.curBbsLocation(wel, f); // 当前位置
			String tmpBasic, tmpBasicAll = "", tmpBracket, tmpBracketAll = "";
			String bmShowFormat = curBbsStyle.getBmShowFormat();
			String loofBasiclf = StringUtil.nullFilter(bel
					.getMinorLoopCodeInLump());
			String loopBracketLf = StringUtil.nullFilter(bel
					.getMajorLoopCodeInLump());
			// 子版块显示
			fel.setBmShowFormat(bmShowFormat);
			fel.setBbsStyleDaoImp(wel.getBbsStyleDaoImp());
			List<BbsForum> l = bbsForumDaoImp.findBbsForumByParentId(fid);

			if (!l.isEmpty()) {

				titleAreaShow = true;
				for (BbsForum bg : l) {
					tmpBasic = loofBasiclf;
					fel.setLf(tmpBasic);
					tmpBasic = ForumUtil.formatHref(fel, bg);
					tmpBasicAll += tmpBasic;
				}

			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$forumsLoop$$}", tmpBasicAll);
			if (titleAreaShow && bel.getTitleOfMinorLoopCodeInLump() != null) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$titleOfSubForumsLoops$$}", bel
								.getTitleOfMinorLoopCodeInLump().trim());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$titleOfSubForumsLoops$$}", "");
			}
			titleAreaShow = false;
			boolean sort;
			if (Integer.valueOf(as.getText("lerx.bbsThreadSortMod")) == 0) {
				sort = true;
			} else {
				sort = false;
			}
			Rs rs = bbsThemeDaoImp.findAllThemesByForumId(fid, page, pageSize,
					sort);

			@SuppressWarnings("unchecked")
			List<Long> nl = (List<Long>) rs.getList();
			if (!nl.isEmpty()) {

				// TODO this
				fel.setIncludeEditArea(true);
				fel.setTitleLength(0);

				titleAreaShow = true;
				for (Long tid : nl) {
					BbsTheme t = bbsThemeDaoImp.findThemeById(tid);
					tmpBracket = loopBracketLf;
					fel.setLf(tmpBracket);

					tmpBracket = ThemeUtil.formatHref(fel, t);
					tmpBracketAll += tmpBracket;
				}

			}

			String pageShow = RsInit.rsPageStrShow(
					rs,
					wel.getRequest().getContextPath() + "/"
							+ as.getText("lerx.bbsForumPageFileName").trim()
							+ "?fid=" + fid,
							PubUtil.pageFormatShowInit(as, wel), false);

			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$threadsLoop$$}", tmpBracketAll);
			if (titleAreaShow && bel.getTitleOfMajorLoopCodeInLump() != null) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$titleOfThreadsLoops$$}", bel
								.getTitleOfMajorLoopCodeInLump().trim());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$titleOfThreadsLoops$$}", "");
			}
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
				if (f.isAsClass()) {
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$editThemeButtomCode$$}", "");
				} else {
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$editThemeButtomCode$$}",
							curBbsStyle.getAddThemeButtomCode());
				}

			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$editThemeButtomCode$$}", "");
			}

			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pageShowStr$$}", pageShow);

			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", location);
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$fid$$}", ""
					+ f.getId());
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$editThemeButtomTxt$$}",
					as.getText("lerx.txtOfBbsAddThemeButtom").trim());
			String bmFormat=bel.getBmDataFormat();
			if (bmFormat==null || bmFormat.trim().equals("")){
				bmFormat=wel.getCurBbsStyle().getPublicStyle().getBmDataFormat();
			}
			htmlTemplate = StringUtil
			.strReplace(
					htmlTemplate,
					"{$$bms$$}",
					BbsUtil.findBms(wel, f.getId(),
							bmFormat));
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoFolderUrl$$}",
					curBbsStyle.getIcoFolderUrl());
		}
		return htmlTemplate;
	}
}
