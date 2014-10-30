package com.lerx.sys.util;

import com.lerx.sys.obj.UASObj;
import com.lerx.sys.util.vo.ActionPage;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.web.util.camp.ResultPage;

public class ActionPageUtil {
	
	public static User auth(UASObj uo){
		TransferUserUtil tuu = new TransferUserUtil();
		tuu.setAs(uo);
		tuu.setRequest(uo.getRequest());
		tuu.setUser(uo.getUser());
		tuu.setUserDaoImp(uo.getUserDaoImp());
		tuu.setW(true);
		tuu.setPwsMode(2);
		User u = UserUtil.check(tuu);
		return u;
	}
	
	public static ActionPage refererInit(ActionPage ap) {
		String refererUrl,workingUrl;
		refererUrl = (String) ap.getAc().getSession()
				.get("refererUrl");
		workingUrl = (String) ap.getAc().getSession()
				.get("workingUrl");
		if (workingUrl == null || workingUrl.trim().equals("")
				|| workingUrl.trim().equals("null")) {
			if (ap.getRequest().getHeader("Referer") == null) {
				workingUrl = ResultPage.defRURL(ap.getActionSupport(), ap.getRequest());

			} else {
				workingUrl = ap.getRequest().getHeader("Referer");
			}

		}

		if (refererUrl == null || refererUrl.trim().equals("")
				|| refererUrl.trim().equals("null")) {

			if (ap.getRequest().getHeader("Referer") == null) {
				refererUrl = ResultPage.defRURL(ap.getActionSupport(), ap.getRequest());

			} else {
				refererUrl = ap.getRequest().getHeader("Referer");
			}

		}
		
		ap.setRefererUrl(refererUrl);
		ap.setWorkingUrl(workingUrl);
//		System.out.println("refererUrl---:"+refererUrl);
		return ap;
		
	}
}
