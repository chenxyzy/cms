package com.lerx.web.util.camp;

import javax.servlet.http.HttpServletRequest;

import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.MailSender;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.MaiCreateArg;
import com.lerx.sys.util.vo.Mail;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.UserGroup;
import com.lerx.user.vo.UserInf;
import com.lerx.web.vo.RegFinishVo;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionSupport;

public class ResultPage {
	public static String init(ResultEl re) {
		String waitResStr, codeStr;
		int waitSecs;
		codeStr = re.getCodeStr();

		String imgFile;
		
		switch (re.getMod()) {
		case 1:
			waitResStr=re.getAs().getText("lerx.time.wait.page.jump.mod1");
			imgFile=re.getAs().getText("lerx.prompt.img.file.mod1");
			break;
		case 2:
			waitResStr=re.getAs().getText("lerx.time.wait.page.jump.mod2");
			imgFile=re.getAs().getText("lerx.prompt.img.file.mod2");
			break;
		default:
			waitResStr=re.getAs().getText("lerx.time.wait.page.jump.mod0");
			imgFile=re.getAs().getText("lerx.prompt.img.file.mod0");
			break;
		}
		if (re.getWaitSecs()>0){
			waitSecs=re.getWaitSecs();
		}else{
			waitSecs = Integer
			.valueOf(waitResStr);
		}
		
		String rootFolder;
		SiteStyle style;
		if (re.getStyleID()>0){
			style=re.getSiteStyleDaoImp().findStyleById(re.getStyleID());
		}else{
			style=re.getSiteStyleDaoImp().findDef();
		}
		rootFolder=style.getRootResFolder();
		ReadFileArg rfv=new ReadFileArg();
		rfv.setAs(re.getAs());
		rfv.setRequest(re.getRequest());
		rfv.setRootFolder(rootFolder);
		rfv.setFileName("img.txt");
		rfv.setSubFolder("html");
		
		String txt = FileUtil.readFile(rfv);
		
		txt = StringUtil.strReplace(txt, "{$$url$$}",
				rootFolder+"/ico/"+imgFile);
		
		
		codeStr = StringUtil.strReplace(codeStr, "{$$img$$}",
				txt);

		codeStr = StringUtil.strReplace(codeStr, "{$$returnUrl$$}",
				re.getRefererUrl());
		codeStr = StringUtil.strReplace(codeStr, "{$$waitingTime$$}", ""
				+ waitSecs);
		codeStr = StringUtil
				.strReplace(codeStr, "{$$resultMsg$$}", re.getMes());
		codeStr = StringUtil.strReplace(codeStr, "{$$imgUrl$$}",
				StringUtil.nullFilter(re.getImgUrl()));
		codeStr = StringUtil.strReplace(codeStr, "{$$contextPath$$}", re
				.getRequest().getContextPath());
		return codeStr;
	}

	public static ResultEl regFinish(RegFinishVo rfv) {
		SiteInfo si = rfv.getSite();
		ActionSupport as = rfv.getAs();
		HttpServletRequest request = rfv.getRequest();
		UserInf userInf = rfv.getUserInf();
		ResultEl re = rfv.getRe();
		String refererUrl = rfv.getRefererUrl();
		IUserDao userDaoImp = rfv.getUserDaoImp();
		String rootFolder = rfv.getRootFolder();
		UserGroup g;
		switch (si.getActAfterReg()) {
		case 1:
			MaiCreateArg mca = new MaiCreateArg();
			mca.setAs(as);
			mca.setMod(1);
			mca.setQn(null);
			mca.setRequest(request);
			mca.setSite(rfv.getSite());
			mca.setRootFolder(rootFolder);
			mca.setFileName("reg.txt");
			mca.setSta(1);
			Mail mail = MailSender.mailInit(mca);
			// String body = mail.getBody();

			// Mail mail = MailSender.mailInit(si, as, request);
			if (MailSender.check(mail)) {
				String body = mail.getBody();
				String shortSiteName;
				if (si.getShortSiteName() == null
						|| si.getShortSiteName().trim().equals("")) {
					shortSiteName = si.getFullSiteName().trim();
				} else {
					shortSiteName = si.getShortSiteName().trim();
				}
				body = StringUtil.strReplace(body, "{$$site$$}", shortSiteName);
				body = StringUtil.strReplace(body, "{$$siteUrl$$}",
						StringUtil.nullFilter(si.getHost()));
				body = StringUtil.strReplace(
						body,
						"{$$userPassingUrl$$}",
						SrvInf.srvUrl(request, si.getHost(),
								Integer.valueOf(as.getText("lerx.serverPort")))
								+ "/user_pass.action?uid="
								+ userInf.getId()
								+ "&uuid=" + userInf.getUuid());
				body = StringUtil.strReplace(
						body,
						"{$$userPassingAdvUrl$$}",
						SrvInf.srvUrl(request, si.getHost(),
								Integer.valueOf(as.getText("lerx.serverPort")))
								+ "/passersList.action?nu.id="
								+ userInf.getId());
				mail.setBody(body);
				mail.setToMail(userInf.getEmail());
				if (Mail.send()) {
					re.setMod(0);

					re.setMes(as.getText("lerx.success.reg.mailSucess"));
				} else {
					re.setMod(1);
					re.setWaitSecs(4);
					re.setMes(as.getText("lerx.success.reg.mailErr"));
				}
			} else {
				re.setMod(1);
				re.setWaitSecs(4);
				re.setMes(as.getText("lerx.success.reg.mailErr"));
			}

			break;
		case 2:
			// 跳转到选择审核者页面
			refererUrl = SrvInf.srvUrl(request, si.getHost(),
					Integer.valueOf(as.getText("lerx.serverPort")))
					+ "/passersList.action?nu.id=" + userInf.getId();
			re.setRefererUrl(refererUrl);
			re.setMod(0);
			re.setMes(as.getText("lerx.success.reg.nor"));
			break;

		default:

			refererUrl = ResultPage.defRURL(as, request);
			g = rfv.getUserGroupDaoImp().findUserGroupByID(
					si.getUserGroupOfAutoPassed());
			if (g != null) {
				userInf.setUserGroup(g);
				userDaoImp.modifyUserInf(userInf);
				re.setMod(0);
				re.setMes(as.getText("lerx.success.reg.nor"));
			} else {
				re.setMod(2);
				re.setMes(as.getText("lerx.success.reg.waitFor"));
			}

			re.setRefererUrl(refererUrl);

			break;

		}

		if (re.getRefererUrl() == null || re.getRefererUrl().trim().equals("")) {

			re.setRefererUrl(defRURL(as, request));
		}
		return re;
	}

	public static String defRURL(ActionSupport as, HttpServletRequest request) {

		String dfr;
		dfr = request.getContextPath();
		String rS = as.getText("lerx.default.url.return.site");
		if (!rS.trim().equals("/")) {

			dfr = dfr.trim();
			if (rS.substring(0, 1).equals("/")) {
				rS = rS.substring(1, dfr.length());
			}
			if (dfr!=null && !dfr.trim().equals("") && dfr.substring(dfr.length() - 1, dfr.length()).equals("/")) {
				dfr += rS.trim();
			} else {
				dfr += "/" + rS.trim();
			}
		}

		if (dfr.trim().equals("")) {
			dfr = "/";
		}

		return dfr;
	}
}
