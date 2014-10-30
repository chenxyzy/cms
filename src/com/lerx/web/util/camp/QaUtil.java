package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.qa.vo.QaNav;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.qa.vo.QaStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.vo.WebElements;

public class QaUtil {

	/*
	 * 检查问答系统中用户权限
	 */
	public static int checkUserOnQa(WebElements wel, QaNav qn) {
		boolean pwdMD5ToLowerCase;
		if (wel.getAs().getText("lerx.pwdMD5ToLowerCase").trim()
				.equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		ChkUtilVo cuv = new ChkUtilVo();
		cuv.setAs(wel.getAs());
		cuv.setInterconnectionDaoImp(wel.getInterconnectionDaoImp());
		cuv.setRequest(wel.getRequest());
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		cuv.setUc(wel.getUc());
		cuv.setUserDaoImp(wel.getUserDaoImp());
		cuv.setQn(qn);
		return wel.getUserDaoImp().checkUserOnQa(cuv);

	}
	
	
	public static WebElements init(WebElements wel,
			QaStyleSubElementInCommon element) {
		QaStyle curQaStyle = wel.getCurQaStyle();

		try {
			if (element == null || element.getHtmlTemplate() == null
					|| element.getHtmlTemplate().trim().equals("")) {
				wel.setHtmlTemplate(curQaStyle.getPublicStyle()
						.getHtmlTemplate());
			} else {
				wel.setHtmlTemplate(StringUtil.nullFilter(element
						.getHtmlTemplate()));
			}

			if (element == null || element.getCssCode() == null
					|| element.getCssCode().trim().equals("")) {
				wel.setCss(curQaStyle.getPublicStyle().getCssCode());
			} else {
				wel.setCss(StringUtil.nullFilter(element.getCssCode()));
			}
			if (element == null || element.getTopCode() == null
					|| element.getTopCode().trim().equals("")) {
				wel.setTopCode(curQaStyle.getPublicStyle().getTopCode());
			} else {
				wel.setTopCode(StringUtil.nullFilter(element.getTopCode()));
			}
			if (element == null || element.getFooterCode() == null
					|| element.getFooterCode().trim().equals("")) {
				wel.setFooterCode(curQaStyle.getPublicStyle().getFooterCode());
			} else {
				wel.setFooterCode(StringUtil.nullFilter(element.getFooterCode()));
			}

			if (element == null || element.getTitleFormat() == null
					|| element.getTitleFormat().trim().equals("")) {
				wel.setTitleFormat(curQaStyle.getPublicStyle().getTitleFormat());
			} else {
				wel.setTitleFormat(StringUtil.nullFilter(element
						.getTitleFormat()));
			}

			if (element == null || element.getHtmlCode() == null
					|| element.getHtmlCode().trim().equals("")) {
				wel.setHtml(curQaStyle.getPublicStyle().getHtmlCode());
			} else {
				wel.setHtml(StringUtil.nullFilter(element.getHtmlCode()));
			}

			if (element == null || element.getHrefLineFormat() == null
					|| element.getHrefLineFormat().trim().equals("")) {
				wel.setHrefLineFormat(curQaStyle.getPublicStyle()
						.getHrefLineFormat());
			} else {
				wel.setHrefLineFormat(StringUtil.nullFilter(element
						.getHrefLineFormat()));
			}

			// 此处是取得了论坛的位置分隔符
			if (curQaStyle.getLocationSplitStr() == null
					|| curQaStyle.getLocationSplitStr().trim().equals("")) {
				wel.setLocationSplitStr("—");
			} else {
				wel.setLocationSplitStr(curQaStyle.getLocationSplitStr().trim());
			}

			wel.setQel(element);
			// System.out.println("-------0000----");
			wel.setFel(FelInit.elInit(wel));
			return wel;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}
	
	
	public static WebElements endQaService(WebElements wel) throws IOException {
		// wel.setShortSiteName(shortName(wel.getSite()));
		wel = SiteUtil.siteNameInit(wel);
		wel.setTitleFormat(SiteUtil.webPageTitleInit(wel, wel.getShortSiteName()));
		// System.out.println("wel.getTitleFormat():"+wel.getTitleFormat());
		wel = PubUtil.endGeneralService(wel);
		// wel.setTitleFormat(webPageTitleInit(wel, wel.getShortSiteName()));
		String htmlTemplate = wel.getHtmlTemplate();
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$webPageTitle$$}", wel.getTitleFormat());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$siteTitle$$}",
				wel.getTitleFormat());
		// splitStrOnSite();
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$keyWord$$}", wel
				.getSite().getKeyWords());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$description$$}",
				wel.getSite().getDescription());
		QaStyle qaStyle = wel.getCurQaStyle();
		if (qaStyle.getPublicCode1() != null
				&& !qaStyle.getPublicCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", qaStyle.getPublicCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", "");
		}
		if (qaStyle.getPublicCode2() != null
				&& !qaStyle.getPublicCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", qaStyle.getPublicCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", "");
		}
		if (qaStyle.getPublicCode3() != null
				&& !qaStyle.getPublicCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", qaStyle.getPublicCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", "");
		}
		if (qaStyle.getPublicCode4() != null
				&& !qaStyle.getPublicCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", qaStyle.getPublicCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", "");
		}
		if (wel.getQel().getSpecialCode1() != null
				&& !wel.getQel().getSpecialCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", wel.getQel().getSpecialCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", "");
		}

		if (wel.getQel().getSpecialCode2() != null
				&& !wel.getQel().getSpecialCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", wel.getQel().getSpecialCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", "");
		}

		if (wel.getQel().getSpecialCode3() != null
				&& !wel.getQel().getSpecialCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", wel.getQel().getSpecialCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", "");
		}

		if (wel.getQel().getSpecialCode4() != null
				&& !wel.getQel().getSpecialCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", wel.getQel().getSpecialCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", "");
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$locationSplitStr$$}", wel.getLocationSplitStr());
		// System.out.println(wel.getStation());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$memberPanel$$}",
				MemberPanel.init(wel));
		wel.setHtmlTemplate(htmlTemplate);

		PubUtil.referer(wel);
		wel = PubUtil.endService(wel);
		return wel;
	}
	
}
