package com.lerx.web.util.camp;

import javax.servlet.http.HttpServletRequest;

import com.lerx.web.vo.RefererUrlVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RefererUtil {
	public static RefererUrlVo init(RefererUrlVo ruv) {
		String refererUrl = ruv.getRefererUrl();
		String workingUrl = ruv.getWorkingUrl();
		HttpServletRequest request = ruv.getRequest();
		ActionSupport as = ruv.getAs();
		refererUrl = (String) ActionContext.getContext().getSession()
				.get("refererUrl");
		workingUrl = (String) ActionContext.getContext().getSession()
				.get("workingUrl");
		if (workingUrl == null || workingUrl.trim().equals("")
				|| workingUrl.trim().equals("null")) {
			if (request.getHeader("Referer") == null) {
				workingUrl = ResultPage.defRURL(as, request);

			} else {
				workingUrl = request.getHeader("Referer");
			}

		}

		if (refererUrl == null || refererUrl.trim().equals("")
				|| refererUrl.trim().equals("null")) {

			if (request.getHeader("Referer") == null) {
				refererUrl = ResultPage.defRURL(as, request);

			} else {
				refererUrl = request.getHeader("Referer");
			}

		}

		ruv.setRefererUrl(refererUrl);
		ruv.setWorkingUrl(workingUrl);
		return ruv;
	}
}
