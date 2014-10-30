package com.lerx.web.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.util.ThemeUtil;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.web.util.camp.BbsUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadThreadSearch {
	public static String show(WebElements wel) throws IOException {
		wel.setRefererRec(false);
		IBbsThemeDao bbsThemeDaoImp = wel.getBbsThemeDaoImp();
		IBbsStyleDao bbsStyleDaoImp = wel.getBbsStyleDaoImp();
		ActionSupport as = wel.getAs();
		// HttpServletRequest request=wel.getRequest();

		// SiteInfo site=wel.getSite();
		BbsStyle curBbsStyle = wel.getCurBbsStyle();

		int pageSize = wel.getPageSize();
		int page = wel.getPage();
		int fid = wel.getFid();
		if (pageSize == 0 && wel.getBi().getPageSizeOfTitle() > 0) {
			pageSize = wel.getBi().getPageSizeOfTitle();
		}
		String key = wel.getKey();
		String keyCon = wel.getKeyCon();
		// 取值结束

		// 下面五行顺序不能错
		wel = BbsUtil.initBbsElement(wel, curBbsStyle.getSearchStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
				"{$$app$$}", as.getText("lerx.searchPageTitle")));

		wel = SiteInit.reInit(wel);
		LoginCheckEl lcel = PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());

		wel = BbsUtil.endBbsService(wel);
		ResultEl re=wel.getRe();
		String htmlTemplate = wel.getHtmlTemplate();

		String tmpStr = "";
		if ((key == null || key.trim().equals("")) && keyCon != null
				&& !keyCon.trim().equals("")) {

			key = URLDecoder.decode(keyCon, as.getText("lerx.charset"));
			key = StringUtil.htmlFilter(key);
		}

		if (key != null && !key.trim().equals("")) {
			keyCon = URLEncoder.encode(key, as.getText("lerx.charset"));
			keyCon = StringUtil.htmlFilter(keyCon);
		}

		tmpStr = "?keyCon=" + keyCon;

		boolean searchMustBeMember;
		if (as.getText("lerx.searchMustBeMember").trim()
				.equalsIgnoreCase("true")) {
			searchMustBeMember = true;
		} else {
			searchMustBeMember = false;
		}
		
		String pageShowStr;
		
		BbsStyleSubElementInCommon bel = wel.getBel();

		String loopBracketLf = StringUtil.nullFilter(bel
				.getMajorLoopCodeInLump());
		boolean al = false;

		if (wel.isUserLogined()  || !searchMustBeMember) {
			
			if (wel.isUserLogined() && as.getText("lerx.searchInAllText").trim()
					.equalsIgnoreCase("true")) {
				al = true;
			} else {
				al = false;
			}

			if (key == null || key.trim().equals("")) {
				pageShowStr = "";
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$threadsLoop$$}", "");
			} else {
				
//				System.out.println("keyCon:"+keyCon);
//				System.out.println("key:"+key);
				Rs rs = bbsThemeDaoImp.search(key, fid, al, page, pageSize);

				pageShowStr = RsInit.rsPageStrShow(rs, tmpStr,
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
//				el.setLf(wel.getBel().getMajorLoopCodeInLump());
				for (Long tid : ls) {
					el.setLf(loopBracketLf);
					bt = bbsThemeDaoImp.findThemeById(tid);

					tmp = ThemeUtil.formatHref(el, bt);
//					System.out.println("tmp:888");
//					System.out.println("tmp:"+tmp);
					tmpAll += tmp;
				}
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$threadsLoop$$}", tmpAll);

				
				
				
			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", as.getText("lerx.searchPageTitle"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
			"searchMain");
			
			
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pageShowStr$$}", pageShowStr);

			

		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pageShowStr$$}", "");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$searchDataBody$$}", "");
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
			re.setRefererUrl(PubUtil.refCheck(wel, 0));
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			htmlTemplate = ResultPage.init(re);

		}
		return htmlTemplate;
	}
}
