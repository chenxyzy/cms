package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.util.ForumUtil;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadBindex {
	public static String show(WebElements wel) throws IOException {
		wel.setRefererRec(false);
		IBbsForumDao bbsForumDaoImp = wel.getBbsForumDaoImp();
		ActionSupport as = wel.getAs();
		BbsStyle curBbsStyle = wel.getCurBbsStyle();
		// int gid=wel.getGid();

		// 下面五行顺序不能错
		wel = BbsUtil.initBbsElement(wel, curBbsStyle.getIndexStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
				"{$$app$$}", as.getText("lerx.indexTitle")));

		wel = SiteInit.reInit(wel);
		LoginCheckEl lcel = PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());

		wel = BbsUtil.endBbsService(wel);
		FormatElements fel = wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();

		BbsStyleSubElementInCommon bel = wel.getBel();
		
		String loofBasiclf = StringUtil
				.nullFilter(bel.getMinorLoopCodeInLump());
		String loopBracketLf = StringUtil.nullFilter(bel
				.getMajorLoopCodeInLump());
		String bmShowFormat = curBbsStyle.getBmShowFormat();
		String tmpBasicAll = "", tmpBasic = "", tmpBracketAll = "", tmpBracket = "";
		String bmFormat;
		bmFormat=bel.getBmDataFormat();
		if (bmFormat==null || bmFormat.trim().equals("")){
			bmFormat=wel.getCurBbsStyle().getPublicStyle().getBmDataFormat();
		}
		
		List<BbsForum> l = bbsForumDaoImp.findBbsForumShowOnIndex();
		fel.setBmShowFormat(bmShowFormat);
		if (!l.isEmpty()) {

			for (BbsForum bg : l) {

				tmpBasic = loofBasiclf;

				fel.setLf(loofBasiclf);
				tmpBasic = ForumUtil.formatHref(fel, bg);

				tmpBracketAll = "";
				List<BbsForum> b = bbsForumDaoImp.findBbsForumByParent(bg);

				for (BbsForum bc : b) {
					fel.setLf(loopBracketLf);
					tmpBracket = ForumUtil.formatHref(fel, bc);
//					tmpBracket = StringUtil.strReplace(tmpBracket, "{$$bms$$}",
//							findBms(wel, bc.getId()));
					tmpBracket = StringUtil
					.strReplace(
							tmpBracket,
							"{$$bms$$}",
							BbsUtil.findBms(wel, bc.getId(),
									bmFormat));
					tmpBracketAll += tmpBracket;
				}
				tmpBasic = StringUtil.strReplace(tmpBasic, "{$$forumsLoop$$}",
						tmpBracketAll);

				// String bms=findBms(wel,bg.getId());
				// tmpBasic = StringUtil.strReplace(tmpBasic, "{$$bms$$}",
				// findBms(wel,bg.getId()));

				tmpBasic = StringUtil
						.strReplace(
								tmpBasic,
								"{$$bms$$}",
								BbsUtil.findBms(wel, bg.getId(),
										bmFormat));

				tmpBasicAll += tmpBasic;

			}

		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$rootClassesLoop$$}", tmpBasicAll);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				as.getText("lerx.indexTitle"));
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$fid$$}", "0");
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoFolderUrl$$}",
				curBbsStyle.getIcoFolderUrl());
		return htmlTemplate;
	}

//	private static String findBms(WebElements wel, int fid) {
//		List<BMInfo> bmlist = wel.getBbsBMDaoImp().queryByFid(fid);
//		String out = "";
//		Iterator itr = bmlist.iterator();
//		while (itr.hasNext()) {
//			BMInfo bm = (BMInfo) itr.next();
//			out += bm.getName();
//		}
//		return out;
//	}
}
