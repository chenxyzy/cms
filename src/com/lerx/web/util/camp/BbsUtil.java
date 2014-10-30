package com.lerx.web.util.camp;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.lerx.bbs.vo.BMInfo;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.vo.WebElements;

public class BbsUtil {

	public static String findBms(WebElements wel,int fid,String fstr){
		List<BMInfo> bmlist=wel.getBbsBMDaoImp().queryByFid(fid);
		String out="",tmp;
		Iterator<BMInfo> itr = bmlist.iterator();
		while (itr.hasNext()) {
			tmp=fstr;
			
			BMInfo bm=(BMInfo) itr.next();
			tmp = StringUtil.strReplace(tmp, "{$$data$$}",
					bm.getName());
			tmp = StringUtil.strReplace(tmp, "{$$username$$}",
					bm.getName());
			tmp = StringUtil.strReplace(tmp, "{$$uid$$}",
					""+bm.getUid());
			out+=tmp;
		}
		return out;
	}
	
	public static WebElements initBbsElement(WebElements wel,
			BbsStyleSubElementInCommon element) {
		BbsStyle curBbsStyle = wel.getCurBbsStyle();

		try {
			if (element == null || element.getHtmlTemplate() == null
					|| element.getHtmlTemplate().trim().equals("")) {
				wel.setHtmlTemplate(curBbsStyle.getPublicStyle()
						.getHtmlTemplate());
			} else {
				wel.setHtmlTemplate(StringUtil.nullFilter(element
						.getHtmlTemplate()));
			}
			if (element == null || element.getCssCode() == null
					|| element.getCssCode().trim().equals("")) {
				wel.setCss(curBbsStyle.getPublicStyle().getCssCode());
			} else {
				wel.setCss(StringUtil.nullFilter(element.getCssCode()));
			}
			if (element == null || element.getTopCode() == null
					|| element.getTopCode().trim().equals("")) {
				wel.setTopCode(curBbsStyle.getPublicStyle().getTopCode());
			} else {
				wel.setTopCode(StringUtil.nullFilter(element.getTopCode()));
			}
			if (element == null || element.getFooterCode() == null
					|| element.getFooterCode().trim().equals("")) {
				wel.setFooterCode(curBbsStyle.getPublicStyle().getFooterCode());
			} else {
				wel.setFooterCode(StringUtil.nullFilter(element.getFooterCode()));
			}

			if (element == null || element.getTitleFormat() == null
					|| element.getTitleFormat().trim().equals("")) {
				wel.setTitleFormat(curBbsStyle.getPublicStyle()
						.getTitleFormat());
			} else {
				wel.setTitleFormat(StringUtil.nullFilter(element
						.getTitleFormat()));
			}

			if (element == null || element.getHtmlCode() == null
					|| element.getHtmlCode().trim().equals("")) {
				wel.setHtml(curBbsStyle.getPublicStyle().getHtmlCode());
			} else {
				wel.setHtml(StringUtil.nullFilter(element.getHtmlCode()));
			}

			if (element == null || element.getHrefLineFormat() == null
					|| element.getHrefLineFormat().trim().equals("")) {
				wel.setHrefLineFormat(curBbsStyle.getPublicStyle()
						.getHrefLineFormat());
			} else {
				wel.setHrefLineFormat(StringUtil.nullFilter(element
						.getHrefLineFormat()));
			}

			if (element == null || element.getDateFormatOnLine() == null
					|| element.getDateFormatOnLine().trim().equals("")) {
				wel.setDateFormatOnLine(curBbsStyle.getPublicStyle()
						.getDateFormatOnLine());
			} else {
				wel.setDateFormatOnLine(StringUtil.nullFilter(element
						.getDateFormatOnLine()));
			}

			if (element == null || element.getEditAreaCode() == null
					|| element.getEditAreaCode().trim().equals("")) {
				wel.setEditAreaCode(curBbsStyle.getPublicStyle()
						.getEditAreaCode());
			} else {
				wel.setEditAreaCode(StringUtil.nullFilter(element
						.getEditAreaCode()));
			}
			if (element == null || element.getSearchAreaCode() == null
					|| element.getSearchAreaCode().trim().equals("")) {
				wel.setSearchAreaCode(curBbsStyle.getPublicStyle()
						.getSearchAreaCode());
			} else {
				wel.setSearchAreaCode(StringUtil.nullFilter(element
						.getSearchAreaCode()));
			}
			
			if (element == null || element.getFunctionAreaCode() == null
					|| element.getFunctionAreaCode().trim().equals("")) {
				wel.setFunctionAreaCode(curBbsStyle.getPublicStyle()
						.getFunctionAreaCode());
			} else {
				wel.setFunctionAreaCode(StringUtil.nullFilter(element
						.getFunctionAreaCode()));
			}
			
			

			// 此处是取得了论坛的位置分隔符
//			System.out.println("asdfasdfasdf");
			if (curBbsStyle.getLocationSplitStr() == null
					|| curBbsStyle.getLocationSplitStr().trim().equals("")) {
				wel.setLocationSplitStr("—");
			} else {
				wel.setLocationSplitStr(curBbsStyle.getLocationSplitStr()
						.trim());
			}

			wel.setBel(element);
			// System.out.println("-------0000----");
			wel.setFel(FelInit.elInit(wel));
			return wel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static WebElements endBbsService(WebElements wel) throws IOException {
		wel = SiteUtil.siteNameInit(wel);
		// wel.setShortSiteName(shortName(wel.getSite()));
		wel.setTitleFormat(SiteUtil.webPageTitleInit(wel, wel.getShortSiteName()));
		wel = PubUtil.endGeneralService(wel);
		String bbsName = wel.getBi().getName();
//		System.out.println("----bsname:"+bbsName);
		if (bbsName == null || bbsName.trim().equals("")) {
			
			bbsName = wel.getShortSiteName()
					+ wel.getAs().getText("lerx.bbsTitle").trim();
		}
		BbsStyle curBbsStyle = wel.getCurBbsStyle();

		wel.setTitleFormat(SiteUtil.webPageTitleInit(wel, bbsName));
		
		

		String htmlTemplate = wel.getHtmlTemplate();
		
		

		if (curBbsStyle.getPublicCode1() != null
				&& !curBbsStyle.getPublicCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", curBbsStyle.getPublicCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode1$$}", "");
		}
		if (curBbsStyle.getPublicCode2() != null
				&& !curBbsStyle.getPublicCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", curBbsStyle.getPublicCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode2$$}", "");
		}

		if (curBbsStyle.getPublicCode3() != null
				&& !curBbsStyle.getPublicCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", curBbsStyle.getPublicCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode3$$}", "");
		}

		if (curBbsStyle.getPublicCode4() != null
				&& !curBbsStyle.getPublicCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", curBbsStyle.getPublicCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$publicCode4$$}", "");
		}
		BbsStyleSubElementInCommon bel = wel.getBel();
		if (bel.getSpecialCode1() != null
				&& !bel.getSpecialCode1().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", bel.getSpecialCode1());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode1$$}", "");
		}

		if (bel.getSpecialCode2() != null
				&& !bel.getSpecialCode2().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", bel.getSpecialCode2());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode2$$}", "");
		}

		if (bel.getSpecialCode3() != null
				&& !bel.getSpecialCode3().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", bel.getSpecialCode3());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode3$$}", "");
		}

		if (bel.getSpecialCode4() != null
				&& !bel.getSpecialCode4().trim().equals("")) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", bel.getSpecialCode4());
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$specialCode4$$}", "");
		}

		String url=wel.getBi().getUrl();
		
		if (url==null || url.trim().equals("")){
			url=wel.getRequest().getContextPath()+"/bindex.action";
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$locationSplitStr$$}", wel.getLocationSplitStr());

		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$memberPanel$$}",
				MemberPanel.init(wel));
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$webPageTitle$$}", wel.getTitleFormat());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$siteTitle$$}",
				wel.getTitleFormat());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$bbsName$$}",
				bbsName);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$bbsUrl$$}",
				url);
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$icoFolderUrl$$}",
				curBbsStyle.getIcoFolderUrl());
				
		// splitStrOnSite();
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$keyWord$$}", wel
				.getSite().getKeyWords());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$description$$}",
				wel.getSite().getDescription());
		
		wel.setHtmlTemplate(htmlTemplate);

		// 用户面板
		// el.getSpecialCode1();
		PubUtil.referer(wel);
		wel = PubUtil.endService(wel);
		return wel;
	}
	
	/*
	 * 论坛当前位置
	 */
	public static String curBbsLocation(WebElements wel, BbsForum f) {
		String location = "";
		boolean c=false;
		if (wel.getAs().getText("lerx.bbs.nav.asclass.show").trim().equalsIgnoreCase("true")){
			c=true;
		}
		if (f!=null){
			location = "<a href=\"" + wel.getRequest().getContextPath() + "/"
			+ wel.getAs().getText("lerx.bbsForumPageFileName").trim()
			+ "?fid=" + f.getId() + "\">" + f.getForumName() + "</a>";
			BbsForum ft = f, p;

			if ((ft.getRootForum() != null)) {
				while (ft.getRootForum() != null) {
					
					p = ft.getRootForum();

					if (c || !p.isAsClass()){
						location = "<a href=\""
							+ wel.getRequest().getContextPath()
							+ "/"
							+ wel.getAs().getText("lerx.bbsForumPageFileName")
									.trim() + "?fid=" + p.getId() + "\">"
							+ p.getForumName() + "</a>" + wel.getLocationSplitStr()
							+ location;
					}

					
					ft = p;
				}
			}
		}else{
			location = "";
		}
		
		
		return location;
	}
	
}
