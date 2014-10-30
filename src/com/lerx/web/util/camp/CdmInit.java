package com.lerx.web.util.camp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

public class CdmInit {

	public static CookieDoModel init(ActionSupport as,HttpServletRequest request,HttpServletResponse response,IUserDao userDaoImp,IInterconnectionDao interconnectionDaoImp) {
		CookieDoModel cdm = new CookieDoModel();
		cdm.setActionSupport(as);
		cdm.setEncodingCode(as.getText("lerx.charset").trim());
		cdm.setPrefix(as.getText("lerx.prefixOfCookieForLogin"));
		cdm.setHost(as.getText("lerx.host.current"));
		cdm.setHostSecFile(as.getText("lerx.hostSecFile"));
		cdm.setRequest(request);
		cdm.setResponse(response);
		cdm.setUserDaoImp(userDaoImp);
		cdm.setInterconnectionDaoImp(interconnectionDaoImp);
		return cdm;
	}
}
