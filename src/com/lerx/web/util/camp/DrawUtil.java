package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.style.draw.vo.DrawStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.vo.WebElements;

public class DrawUtil {

	public static WebElements init(WebElements wel) {
		DrawStyle curDrawStyle = wel.getCurDrawStyle();
		wel.setHtmlTemplate(StringUtil.nullFilter(curDrawStyle
				.getHtmlTemplate()));
		wel.setCss(StringUtil.nullFilter(curDrawStyle.getCssCode()));
		wel.setHtml(StringUtil.nullFilter(curDrawStyle.getHtmlCode()));
		wel.setHrefLineFormat(curDrawStyle.getResultLineFormat());
		wel.setTitleFormat(curDrawStyle.getTitleFormat());
		wel.setFel(FelInit.elInit(wel));

		return wel;
	}

	public static WebElements endDrawService(WebElements wel)
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
