package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.style.vote.vo.VoteStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.vo.WebElements;

public class VoteUtil {

	public static WebElements init(WebElements wel,
			VoteStyleSubElementInCommon element) {
		try {

			VoteStyle curVoteStyle = wel.getCurVoteStyle();
			// if (wel.getGid()>0){
			//
			// }else{
			// curSiteStyle = wel.getCurSiteStyle();
			// }

			if (element == null || element.getHtmlTemplate() == null
					|| element.getHtmlTemplate().trim().equals("")) {
				wel.setHtmlTemplate(curVoteStyle.getPublicStyle()
						.getHtmlTemplate());
			} else {
				wel.setHtmlTemplate(StringUtil.nullFilter(element
						.getHtmlTemplate()));
			}
			if (element == null || element.getCssCode() == null
					|| element.getCssCode().trim().equals("")) {
				wel.setCss(StringUtil.nullFilter(curVoteStyle.getPublicStyle()
						.getCssCode()));
			} else {
				wel.setCss(StringUtil.nullFilter(element.getCssCode()));
			}
			if (element == null || element.getTopCode() == null
					|| element.getTopCode().trim().equals("")) {
				wel.setTopCode(StringUtil.nullFilter(curVoteStyle
						.getPublicStyle().getTopCode()));
			} else {
				wel.setTopCode(StringUtil.nullFilter(element.getTopCode()));
			}
			if (element == null || element.getFooterCode() == null
					|| element.getFooterCode().trim().equals("")) {
				wel.setFooterCode(StringUtil.nullFilter(curVoteStyle
						.getPublicStyle().getFooterCode()));
			} else {
				wel.setFooterCode(StringUtil.nullFilter(element.getFooterCode()));
			}

			if (element == null || element.getTitleFormat() == null
					|| element.getTitleFormat().trim().equals("")) {
				wel.setTitleFormat(StringUtil.nullFilter(curVoteStyle
						.getPublicStyle().getTitleFormat()));
			} else {
				wel.setTitleFormat(StringUtil.nullFilter(element
						.getTitleFormat()));
			}

			if (element == null || element.getHtmlCode() == null
					|| element.getHtmlCode().trim().equals("")) {
				wel.setHtml(StringUtil.nullFilter(curVoteStyle.getPublicStyle()
						.getHtmlCode()));
			} else {
				wel.setHtml(StringUtil.nullFilter(element.getHtmlCode()));
			}

			if (element == null || element.getHrefLineFormat() == null
					|| element.getHrefLineFormat().trim().equals("")) {
				wel.setHrefLineFormat(StringUtil.nullFilter(curVoteStyle
						.getPublicStyle().getHrefLineFormat()));
			} else {
				wel.setHrefLineFormat(StringUtil.nullFilter(element
						.getHrefLineFormat()));
			}

			wel.setVel(element);
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
	 * 检查投票及抽奖中用户权限
	 */
	public static boolean checkUserOnVote(WebElements wel) {
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
		// System.out.println("wel.getUc():"+wel.getUc().getUsername());
		return wel.getUserDaoImp().checkUserOnVote(cuv);

	}

	public static WebElements endVoteService(WebElements wel)
			throws IOException {
		// wel.setShortSiteName(shortName(wel.getSite()));
		wel = SiteUtil.siteNameInit(wel);
		wel.setTitleFormat(SiteUtil.webPageTitleInit(wel,
				wel.getShortSiteName()));
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
		VoteStyle curVoteStyle = wel.getCurVoteStyle();
		if (curVoteStyle.getPublicCode1() != null
				&& !curVoteStyle.getPublicCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", curVoteStyle.getPublicCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", "");
		}

		if (curVoteStyle.getPublicCode2() != null
				&& !curVoteStyle.getPublicCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", curVoteStyle.getPublicCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", "");
		}

		if (curVoteStyle.getPublicCode3() != null
				&& !curVoteStyle.getPublicCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", curVoteStyle.getPublicCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", "");
		}

		if (curVoteStyle.getPublicCode4() != null
				&& !curVoteStyle.getPublicCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", curVoteStyle.getPublicCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", "");
		}

		if (wel.getVel().getSpecialCode1() != null
				&& !wel.getVel().getSpecialCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", wel.getVel().getSpecialCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", "");
		}

		if (wel.getVel().getSpecialCode2() != null
				&& !wel.getVel().getSpecialCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", wel.getVel().getSpecialCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", "");
		}

		if (wel.getVel().getSpecialCode3() != null
				&& !wel.getVel().getSpecialCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", wel.getVel().getSpecialCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", "");
		}

		if (wel.getVel().getSpecialCode4() != null
				&& !wel.getVel().getSpecialCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", wel.getVel().getSpecialCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", "");
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

}
