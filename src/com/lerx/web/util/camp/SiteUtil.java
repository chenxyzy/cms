package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.site.vo.SiteStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.vo.WebElements;

public class SiteUtil {
	public static WebElements endSiteService(WebElements wel)
			throws IOException {
		// wel.setShortSiteName(shortName(wel.getSite()));
		wel = siteNameInit(wel);
		wel.setTitleFormat(webPageTitleInit(wel, wel.getShortSiteName()));
		wel = PubUtil.endGeneralService(wel);

		String htmlTemplate = wel.getHtmlTemplate();
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$webPageTitle$$}", wel.getTitleFormat());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$siteTitle$$}",
				wel.getTitleFormat());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$keyWord$$}", wel
				.getSite().getKeyWords());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$description$$}",
				wel.getSite().getDescription());
		SiteStyle curSiteStyle = wel.getCurSiteStyle();
		if (curSiteStyle.getPublicCode1() != null
				&& !curSiteStyle.getPublicCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", curSiteStyle.getPublicCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", "");
		}

		if (curSiteStyle.getPublicCode2() != null
				&& !curSiteStyle.getPublicCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", curSiteStyle.getPublicCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", "");
		}

		if (curSiteStyle.getPublicCode3() != null
				&& !curSiteStyle.getPublicCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", curSiteStyle.getPublicCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", "");
		}

		if (curSiteStyle.getPublicCode4() != null
				&& !curSiteStyle.getPublicCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", curSiteStyle.getPublicCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", "");
		}
		if (wel.getSel() != null) {
			if (wel.getSel().getSpecialCode1() != null
					&& !wel.getSel().getSpecialCode1().trim().equals("")) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode1$$}", wel.getSel().getSpecialCode1());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode1$$}", "");
			}

			if (wel.getSel().getSpecialCode2() != null
					&& !wel.getSel().getSpecialCode2().trim().equals("")) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode2$$}", wel.getSel().getSpecialCode2());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode2$$}", "");
			}
			if (wel.getSel().getSpecialCode3() != null
					&& !wel.getSel().getSpecialCode3().trim().equals("")) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode3$$}", wel.getSel().getSpecialCode3());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode3$$}", "");
			}

			if (wel.getSel().getSpecialCode4() != null
					&& !wel.getSel().getSpecialCode4().trim().equals("")) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode4$$}", wel.getSel().getSpecialCode4());
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$specialCode4$$}", "");
			}
		}

		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$locationSplitStr$$}", wel.getLocationSplitStr());
		// 用户面板
		// memberPanelCode=MemberPanel.init(wel);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$memberPanel$$}",
				MemberPanel.init(wel));
		wel.setHtmlTemplate(htmlTemplate);
		PubUtil.referer(wel);
		wel = PubUtil.endService(wel);
		return wel;
	}

	public static WebElements initSiteElement(WebElements wel,
			SiteStyleSubElementInCommon element) {
		try {

			SiteStyle curSiteStyle = wel.getCurSiteStyle();
			if (wel.getGid() > 0) {

			} else {
				curSiteStyle = wel.getCurSiteStyle();
			}

			if (element == null || element.getHtmlTemplate() == null
					|| element.getHtmlTemplate().trim().equals("")) {
				wel.setHtmlTemplate(curSiteStyle.getPublicStyle()
						.getHtmlTemplate());
			} else {
				wel.setHtmlTemplate(StringUtil.nullFilter(element
						.getHtmlTemplate()));
			}
			if (element == null || element.getCssCode() == null
					|| element.getCssCode().trim().equals("")) {
				wel.setCss(StringUtil.nullFilter(curSiteStyle.getPublicStyle()
						.getCssCode()));
			} else {
				wel.setCss(StringUtil.nullFilter(element.getCssCode()));
			}
			if (element == null || element.getTopCode() == null
					|| element.getTopCode().trim().equals("")) {
				wel.setTopCode(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getTopCode()));
			} else {
				wel.setTopCode(StringUtil.nullFilter(element.getTopCode()));
			}
			if (element == null || element.getFooterCode() == null
					|| element.getFooterCode().trim().equals("")) {
				wel.setFooterCode(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getFooterCode()));
			} else {
				wel.setFooterCode(StringUtil.nullFilter(element.getFooterCode()));
			}

			if (element == null || element.getTitleFormat() == null
					|| element.getTitleFormat().trim().equals("")) {
				wel.setTitleFormat(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getTitleFormat()));
			} else {
				wel.setTitleFormat(StringUtil.nullFilter(element
						.getTitleFormat()));
			}

			if (element == null || element.getHtmlCode() == null
					|| element.getHtmlCode().trim().equals("")) {
				wel.setHtml(StringUtil.nullFilter(curSiteStyle.getPublicStyle()
						.getHtmlCode()));
			} else {
				wel.setHtml(StringUtil.nullFilter(element.getHtmlCode()));
			}

			if (element == null || element.getHrefLineFormat() == null
					|| element.getHrefLineFormat().trim().equals("")) {
				wel.setHrefLineFormat(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getHrefLineFormat()));
			} else {
				wel.setHrefLineFormat(StringUtil.nullFilter(element
						.getHrefLineFormat()));
			}

			if (element == null || element.getDateFormatOnLine() == null
					|| element.getDateFormatOnLine().trim().equals("")) {
				wel.setDateFormatOnLine(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getDateFormatOnLine()));
			} else {
				wel.setDateFormatOnLine(StringUtil.nullFilter(element
						.getDateFormatOnLine()));
			}

			if (element == null || element.getEditAreaCode() == null
					|| element.getEditAreaCode().trim().equals("")) {
				wel.setEditAreaCode(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getEditAreaCode()));
			} else {
				wel.setEditAreaCode(StringUtil.nullFilter(element
						.getEditAreaCode()));
			}
			if (element == null || element.getSearchAreaCode() == null
					|| element.getSearchAreaCode().trim().equals("")) {
				wel.setSearchAreaCode(StringUtil.nullFilter(curSiteStyle
						.getPublicStyle().getSearchAreaCode()));
			} else {
				wel.setSearchAreaCode(StringUtil.nullFilter(element
						.getSearchAreaCode()));
			}

			// 此处是取得了门户的位置分隔符
			if (curSiteStyle.getLocationSplitStr() == null
					|| curSiteStyle.getLocationSplitStr().trim().equals("")) {
				wel.setLocationSplitStr("—");
			} else {
				wel.setLocationSplitStr(curSiteStyle.getLocationSplitStr()
						.trim());
			}

			wel.setSel(element);
			// System.out.println("-------0000----");
			wel.setFel(FelInit.elInit(wel));
			return wel;
			// elInit(1);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	/*
	 * 
	 */
	public static WebElements siteNameInit(WebElements wel) {
		wel.setShortSiteName(shortName(wel.getSite()));
		wel.setSiteName(wel.getSite().getFullSiteName());
		if (wel.getBi() != null) {
			wel.setBbsName(wel.getBi().getName());
		}

		return wel;
	}

	/*
	 * 浏览器标题行初始化
	 */
	public static String webPageTitleInit(WebElements wel, String curAppName) {
		String titleFormat = wel.getTitleFormat();
		titleFormat = StringUtil.strReplace(titleFormat, "{$$siteName$$}",
				StringUtil.nullFilter(wel.getSiteName()));

		titleFormat = StringUtil.strReplace(titleFormat, "{$$fullSiteName$$}",
				StringUtil.nullFilter(wel.getSite().getFullSiteName()));
		titleFormat = StringUtil.strReplace(titleFormat, "{$$shortSiteName$$}",
				StringUtil.nullFilter(wel.getShortSiteName().trim()));
		titleFormat = StringUtil.strReplace(titleFormat, "{$$mainTitle$$}",
				StringUtil.nullFilter(curAppName));
		titleFormat = StringUtil.strReplace(titleFormat, "{$$welcomeStr$$}",
				StringUtil.nullFilter(wel.getSite().getWelcomeStr()));
		return titleFormat;
	}

	public static String shortName(SiteInfo site) {
		String shortSiteName;
		if (site.getShortSiteName() == null
				|| site.getShortSiteName().trim().equals("")) {
			shortSiteName = site.getFullSiteName().trim();
		} else {
			shortSiteName = site.getShortSiteName().trim();
		}
		// System.out.println("shortSiteName:"+shortSiteName);
		return shortSiteName;
	}
	
	
	/*
	 * 检查门户中用户权限
	 */
	public static int checkUserOnSite(WebElements wel, ArticleGroup ag) {
		boolean pwdMD5ToLowerCase;
		if (wel.getAs().getText("lerx.pwdMD5ToLowerCase").trim()
				.equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		ChkUtilVo cuv=PubUtil.CuvInit(wel);
		cuv.setAg(ag);
		cuv.setUc(wel.getUc());
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		return wel.getUserDaoImp().checkUserOnSite(cuv);

	}

}
