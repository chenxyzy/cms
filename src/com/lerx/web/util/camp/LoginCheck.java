package com.lerx.web.util.camp;

import java.io.IOException;

import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.vo.User;
import com.lerx.web.vo.LoginCheckEl;
import com.opensymphony.xwork2.ActionContext;

public class LoginCheck {
	public static LoginCheckEl check(LoginCheckEl lcel) throws IOException{
		boolean pwdMD5ToLowerCase;
		if (lcel.getAs().getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		boolean loged = false;
		String safeUserSessionStr = lcel.getAs().getText(
				"lerx.sessionPrefixOfUserAuthentication").trim();
		safeUserSessionStr = safeUserSessionStr.replace("servername",
				lcel.getAs().getText("lerx.host.current"));

		if (ActionContext.getContext().getSession().get(safeUserSessionStr) != null
				&& ActionContext.getContext().getSession()
						.get(safeUserSessionStr).equals("passed")) {
			loged = true;
		}
		if (!loged) {
			
//			
//			UserCookie uc;
//			cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
//			uc = CookieUtil.query(cdm);
			
//			System.out.println("lcel.getAs():"+lcel.getAs());
//			System.out.println("lcel.getRequest():"+lcel.getRequest());
//			System.out.println("lcel.getResponse():"+lcel.getResponse());
//			System.out.println("lcel.getUserDaoImp():"+lcel.getUserDaoImp());
//			System.out.println("lcel.getInterconnectionDaoImp():"+lcel.getInterconnectionDaoImp());
			UserCookie uc;
			CookieDoModel cdm=CdmInit.init(lcel.getAs(), lcel.getRequest(), lcel.getResponse(), lcel.getUserDaoImp(),lcel.getInterconnectionDaoImp());
//			cdm.setHostSecFile(hostSecFile);
//			System.out.println("cdm.getHostSecFile():"+cdm.getHostSecFile());
//			System.out.println("cdm.getEncodingCode():"+cdm.getEncodingCode());
//			System.out.println("cdm.getHost():"+cdm.getHost());
//			System.out.println("cdm.getPrefix():"+cdm.getPrefix());
//			System.out.println("cdm.getActionSupport():"+cdm.getActionSupport());
//			System.out.println("cdm.getInterconnectionDaoImp():"+cdm.getInterconnectionDaoImp());
//			System.out.println("cdm.getRequest():"+cdm.getRequest());
//			System.out.println("cdm.getResponse():"+cdm.getResponse());
//			System.out.println("cdm.getUserDaoImp():"+cdm.getUserDaoImp());
			uc = CookieUtil.query(cdm);
//			if (uc==null){
//				System.out.println("找不到uc!!!");
//			}
			lcel.setUc(uc);
			lcel.setCdm(cdm);
			String openID;
			if (uc!=null){
				openID=uc.getOpenID();
			}else{
				openID=null;
			}
			
			if (openID!=null && !openID.trim().equals("")){
				User u=lcel.getInterconnectionDaoImp().findUserByOpenID(openID, 1);
				if (u!=null){
					loged = true;
				}
				
			}else{
				if (uc != null
						&& lcel.getUserDaoImp().findUserByNameAndPassowrd(uc.getUsername(),
								uc.getPasswd(),pwdMD5ToLowerCase,true) != null) {
					loged = true;
				}
			}
			
			

		}
		lcel.setLogined(loged);
		
//		System.out.println("----loged:"+loged);
		
		return lcel;
	}
}
