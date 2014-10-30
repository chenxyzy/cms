package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.PageFormatShow;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.vo.FileReadArgs;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PubUtil {
	
	public static String errKey(FileReadArgs fra,String key){
		String txt=FileUtil.readTempFile(fra, "html", "err.txt");
		txt = StringUtil.strReplace(txt, "{$$htmlBody$$}",fra.getAs().getText(key));
		txt = StringUtil.strReplace(txt, "{$$charset$$}",fra.getAs().getText("lerx.charset"));
		return txt;
	}
	
	public static WebElements welInit(WebElements wel) { // wel初始化检查
		if (wel.getPage() < 1) {
			wel.setPage(1);
		}
		//this postion  check bbs,if yes not init
		if (wel.getPageSize() < 1 && (WebStation.check(wel.getStation())!=2)) {
			int pageSize;
			try {
				pageSize = Integer.valueOf(wel.getAs().getText(
						"lerx.pageSize.result.default"));
			} catch (NumberFormatException e) {
				pageSize = 10;
			}
			wel.setPageSize(pageSize);
		}
		return wel;
	}
	
	public static WebElements endService(WebElements wel) {
		String htmlTemplate = wel.getHtmlTemplate();
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$charset$$}", wel
				.getAs().getText("lerx.charset"));
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$fullSiteName$$}", wel.getSite().getFullSiteName());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$shortSiteName$$}", wel.getShortSiteName());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$welcomeStr$$}",
				wel.getSite().getWelcomeStr());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$siteName$$}",
				wel.getSiteName());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$siteUrl$$}",
				StringUtil.nullFilter(wel.getSite().getHost()));
		String systemMsg;
		if (wel.getRequest().getAttribute("lerxSystemMsg") == null) {
			systemMsg = "";
		} else {
			systemMsg = (String) wel.getRequest().getAttribute("lerxSystemMsg");
			if (systemMsg.trim().equals("null")) {
				systemMsg = "";
			}
		}

		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$actionErrors$$}", systemMsg);
		wel.setHtmlTemplate(htmlTemplate);
		return wel;

	}
	
	public static void  referer(WebElements wel){
		if (wel.isRefererRec()) {
			if (wel.getRequest().getHeader("Referer") != null
					&& !wel.getRequest().getHeader("Referer").trim().equals("")) {
				ActionContext
						.getContext()
						.getSession()
						.put("refererUrl",
								wel.getRequest().getHeader("Referer"));
			} else {
				ActionContext
						.getContext()
						.getSession()
						.put("refererUrl",
								ResultPage.defRURL(wel.getAs(), wel.getRequest()));

			}
		} else {
			ActionContext.getContext().getSession().put("refererUrl", "");
		}
	}
	
	public static ChkUtilVo CuvInit(WebElements wel){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(wel.getInterconnectionDaoImp());
		
		return cuv;
	}
	
	
	/*
	 * 检查来源页
	 */
	public static String refCheck(WebElements wel, int mod) {
		String mes;
		switch (mod) {
		case 1:
			mes = "lerx.default.url.return.bbs";
			break;
		case 2:
			mes = "lerx.default.url.return.album";
			break;
		default:
			mes = "lerx.default.url.return.site";
			break;
		}
		String ref = wel.getRequest().getHeader("Referer");
		if (ref == null || ref.trim().equals("")) {
			ref = wel.getAs().getText(mes);

		}
		return ref;
	}
	
	/*
	 * 分页字符串格式化
	 */
	public static PageFormatShow pageFormatShowInit(ActionSupport as,
			WebElements wel) {
		PageFormatShow pfs = new PageFormatShow();
		pfs.setEnd(as.getText("lerx.pageEndPageStr"));
		pfs.setFirst(as.getText("lerx.pageFirstPageStr"));
		pfs.setLast(as.getText("lerx.pageLastPageStr"));
		pfs.setNext(as.getText("lerx.pageNextPageStr"));
		pfs.setPrefix(as.getText("lerx.pageStrPrefix"));
		pfs.setSuffix(as.getText("lerx.pageStrSuffix"));
		pfs.setCountPrefix(as.getText("lerx.pageStrCountPrefix"));
		pfs.setJumpPrefix(as.getText("lerx.pageStrJumpPrefix"));
		if (wel.getCurSiteStyle() != null) {
			pfs.setEyeCatchingCode(wel.getCurSiteStyle().getEyeCatchingCode());
		} else {
			pfs.setEyeCatchingCode("");
		}

		return pfs;
	}
	
	/*
	 * loginCheck
	 */
	public static LoginCheckEl logincheck(WebElements wel) throws IOException {
		LoginCheckEl lcel = new LoginCheckEl();
		lcel.setAs(wel.getAs());
		lcel.setRequest(wel.getRequest());
		lcel.setResponse(wel.getResponse());
		lcel.setUserDaoImp(wel.getUserDaoImp());
		lcel.setInterconnectionDaoImp(wel.getInterconnectionDaoImp());
		lcel = LoginCheck.check(lcel);
		// cdm=lcel.getCdm();
		// uc=lcel.getUc();
		return lcel;
	}
	
	/*
	 * 通用页面结束处理
	 */

	public static WebElements endGeneralService(WebElements wel) {
		String htmlTemplate = wel.getHtmlTemplate();

		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$cssStyle$$}",
				wel.getCss());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$htmlBody$$}",
				wel.getHtml());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$top$$}",
				wel.getTopCode());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$footer$$}",
				wel.getFooterCode());

		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$searchAreaCode$$}", wel.getSearchAreaCode());
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$key$$}",
				StringUtil.nullFilter(wel.getKey()));
		wel.setHtmlTemplate(htmlTemplate);
		return wel;

	}
	
	
}
