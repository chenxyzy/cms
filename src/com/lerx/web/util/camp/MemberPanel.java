package com.lerx.web.util.camp;

import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.user.util.PasserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.web.vo.WebElements;

public class MemberPanel {
	
	
	public static String init(WebElements wel) {
		boolean logined=wel.isUserLogined();
		int mod=0,sta;
		sta=wel.getStation();
		mod = WebStation.check(sta);
//		System.out.println("mod:"+mod);
		String memberPanelCode = null, memberLoginCode, memberLogoutCode;
		switch (mod) {
		
		case 2:
			BbsStyle curBbsStyle=wel.getCurBbsStyle();
			if (wel.getBel()!=null && wel.getBel().getMemberPanelCodeForLogIn() != null
					&& !wel.getBel().getMemberPanelCodeForLogIn().equals("")) {
				memberLoginCode = wel.getBel().getMemberPanelCodeForLogIn();
			} else {
				memberLoginCode = curBbsStyle.getPublicStyle()
						.getMemberPanelCodeForLogIn();
			}

			if (wel.getBel()!=null && wel.getBel().getMemberPanelCodeForLogOut() != null
					&& !wel.getBel().getMemberPanelCodeForLogOut().equals("")) {
				memberLogoutCode = wel.getBel().getMemberPanelCodeForLogIn();
			} else {
				memberLogoutCode = curBbsStyle.getPublicStyle()
						.getMemberPanelCodeForLogOut();
			}
			break;
		case 3:
			QaStyle curQaStyle=wel.getCurQaStyle();
			if (wel.getQel()!=null && wel.getQel().getMemberPanelCodeForLogIn() != null
					&& !wel.getQel().getMemberPanelCodeForLogIn().equals("")) {
				memberLoginCode = wel.getQel().getMemberPanelCodeForLogIn();
			} else {
				memberLoginCode = curQaStyle.getPublicStyle()
						.getMemberPanelCodeForLogIn();
			}

			if (wel.getQel()!=null && wel.getQel().getMemberPanelCodeForLogOut() != null
					&& !wel.getQel().getMemberPanelCodeForLogOut().equals("")) {
				memberLogoutCode = wel.getQel().getMemberPanelCodeForLogIn();
			} else {
				memberLogoutCode = curQaStyle.getPublicStyle()
						.getMemberPanelCodeForLogOut();
			}
			break;
		case 4:
			VoteStyle curVoteStyle=wel.getCurVoteStyle();
			if (wel.getVel()!=null && wel.getVel().getMemberPanelCodeForLogIn() != null
					&& !wel.getVel().getMemberPanelCodeForLogIn().equals("")) {
				memberLoginCode = wel.getVel().getMemberPanelCodeForLogIn();
			} else {
				memberLoginCode = curVoteStyle.getPublicStyle()
						.getMemberPanelCodeForLogIn();
			}

			if (wel.getVel()!=null && wel.getVel().getMemberPanelCodeForLogOut() != null
					&& !wel.getVel().getMemberPanelCodeForLogOut().equals("")) {
				memberLogoutCode = wel.getVel().getMemberPanelCodeForLogIn();
			} else {
				memberLogoutCode = curVoteStyle.getPublicStyle()
						.getMemberPanelCodeForLogOut();
			}
			break;
		default:
			SiteStyle curSiteStyle = wel.getCurSiteStyle();
			if (wel.getSel()!=null && wel.getSel().getMemberPanelCodeForLogIn() != null
					&& !wel.getSel().getMemberPanelCodeForLogIn().equals("")) {
				memberLoginCode = wel.getSel().getMemberPanelCodeForLogIn();
			} else {
				memberLoginCode = curSiteStyle.getPublicStyle()
						.getMemberPanelCodeForLogIn();
			}

			if (wel.getSel()!=null && wel.getSel().getMemberPanelCodeForLogOut() != null
					&& !wel.getSel().getMemberPanelCodeForLogOut().equals("")) {
				memberLogoutCode = wel.getSel().getMemberPanelCodeForLogOut();
			} else {
				memberLogoutCode = curSiteStyle.getPublicStyle()
						.getMemberPanelCodeForLogOut();
			}
			break;

		}
		User u=null;
		
//		
//		if (u==null){
//			logined=false;
//		}
		if (logined) {
			memberPanelCode = memberLoginCode;
			u = wel.getUserDaoImp().findUserById(wel.getUc().getUserId());
			UserGroup ug=u.getUserGroup();
			if (ug!=null && ug.getPrivateHtml()!=null && !ug.getPrivateHtml().trim().equals("")){
				memberPanelCode=StringUtil.strReplace(memberPanelCode,"{$$privateHtml$}",ug.getPrivateHtml());
			}else{
				memberPanelCode=StringUtil.strReplace(memberPanelCode,"{$$privateHtml$}","");
			}

		} else {
			memberPanelCode = memberLogoutCode;
		}
		
		boolean task;
		if (u!=null){
			
			task=PasserUtil.chk(u, wel.getPasserDaoImp(), wel.getUserDaoImp(), 1, 5, false);
		}else{
			task=false;
		}
		
		if (task){
			String rootFolder;
			rootFolder=wel.getCurSiteStyle().getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(wel.getAs());
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("hrefPasser.txt");
			rfv.setSubFolder("act");
			
			String txt = FileUtil.readFile(rfv);
			txt = StringUtil.strReplace(txt, "{$$href$$}",
					wel.getRequest().getContextPath()+"/pass/passerCenter.jsp");
			memberPanelCode = StringUtil.strReplace(memberPanelCode, "{$$task$}",txt);
		}else{
			memberPanelCode = StringUtil.strReplace(memberPanelCode, "{$$task$}",
			"");
		}
		return memberPanelCode;
	}
}
