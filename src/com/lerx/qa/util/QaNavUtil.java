package com.lerx.qa.util;

import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.user.vo.ChkUtilVo;

public class QaNavUtil {
	public static String formatHref(FormatElements el,QaNav qn,boolean staticHtmlMode,String staticHtmlRoot) {
		String lf=el.getLf();
		String gHtmlFolder,url,href;
		String qaFileFolder=el.getAs().getText("lerx.staticQaFileFolder");
		
		String staticSiteFileFolderOnNav=el.getAs().getText("lerx.staticSiteFileFolderOnNav");
		boolean OnNav;
		if (staticSiteFileFolderOnNav.trim().equals("true")){
			OnNav=true;
		}else{
			OnNav=false;
		}
		String staticFileFolderOnRoot=el.getAs().getText("lerx.staticFileFolderOnRoot");
		boolean staticOnRoot;
		if (staticFileFolderOnRoot.trim().equals("true")){
			staticOnRoot=true;
		}else{
			staticOnRoot=false;
		}
		if (staticOnRoot){
			staticHtmlRoot="";
		}else{
			staticHtmlRoot += "/";
		}
		if (qaFileFolder==null || qaFileFolder.trim().equalsIgnoreCase("null") || !OnNav){
			qaFileFolder="";
		}else{
			qaFileFolder+="/";
		}
		if (staticHtmlMode
				&& !qn.isRefuseStaticHtml()) {
			gHtmlFolder = qn.getStaticHtmlFolder();
			if (gHtmlFolder == null
					|| gHtmlFolder.trim().equals("")) {
				gHtmlFolder = staticHtmlRoot +qaFileFolder 
						+ "q" + qn.getId();
			} else {
				gHtmlFolder = staticHtmlRoot + qaFileFolder 
						+ gHtmlFolder;
			}
			url = "<a href=\"" + el.getRequest().getContextPath()
					+ "/" + gHtmlFolder + "\">"
					+ qn.getTitle() + "</a>";
			href=el.getRequest().getContextPath()
			+ "/" + gHtmlFolder;
		} else {
			url = "<a href=\""
					+ el.getRequest().getContextPath()
					+ "/qaNav.action?=" + qn.getId()
					+ "\">" + qn.getTitle() + "</a>";
			href="qaNav.action?gid=" + qn.getId();
		}
		lf = StringUtil.strReplace(lf, "{$$href$$}",href);
		lf = StringUtil.strReplace(lf, "{$$hrefline$$}",url);
		lf=StringUtil.strReplace(lf, "{$$title$$}", qn.getTitle());
		return lf;
	}
	
	
	public static String locationStr(FormatElements el,QaNav qn,boolean staticHtml){
		String location;
		String staticHtmlRoot=el.getAs().getText("lerx.htmlPath");
		String qaFileFolder=el.getAs().getText("lerx.staticQaFileFolder");
		String staticSiteFileFolderOnNav=el.getAs().getText("lerx.staticSiteFileFolderOnNav");
		boolean OnNav;
		if (staticSiteFileFolderOnNav.trim().equals("true")){
			OnNav=true;
		}else{
			OnNav=false;
		}
		
		if (qaFileFolder==null || qaFileFolder.trim().equalsIgnoreCase("null") || !OnNav){
			qaFileFolder="";
		}else{
			qaFileFolder+="/";
		}
		
		
		String staticFileFolderOnRoot=el.getAs().getText("lerx.staticFileFolderOnRoot");
		boolean staticOnRoot;
		if (staticFileFolderOnRoot.trim().equals("true")){
			staticOnRoot=true;
		}else{
			staticOnRoot=false;
		}
		if (staticOnRoot){
			staticHtmlRoot="";
		}else{
			staticHtmlRoot += "/";
		}
		
		if (!qn.isRefuseStaticHtml()){
//			String folder;
//			if (qn.getStaticHtmlFolder()!=null) {
//				folder=qn.getStaticHtmlFolder();
//			}else{
//				folder= "c" + qn.getId();
//			}
			if (staticHtml){
				String gHtmlFolder = qn.getStaticHtmlFolder();
				if (gHtmlFolder == null
						|| gHtmlFolder.trim().equals("")) {
					gHtmlFolder = staticHtmlRoot +qaFileFolder 
							+ "q" + qn.getId();
				} else {
					gHtmlFolder = staticHtmlRoot + qaFileFolder 
							+ gHtmlFolder;
				}
				location = "<a href=\"" + el.getRequest().getContextPath()
				+ "/" + gHtmlFolder + "\">"
				+ qn.getTitle() + "</a>";
			}else{
				location = "<a href=\""
					+ el.getRequest().getContextPath()
					+ "/qaNav.action?gid=" + qn.getId()
					+ "\">" + qn.getTitle() + "</a>";
			}
		}else{
			location = "<a href=\""
				+ el.getRequest().getContextPath()
				+ "/qaNav.action?gid=" + qn.getId()
				+ "\">" + qn.getTitle() + "</a>";
		}
		return location;
	}
	
	public static int checkUserOnQa(ChkUtilVo cuv){
		boolean pwdMD5ToLowerCase;
		if (cuv.getAs().getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
//		UserCookie uc;
//		CookieDoModel cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
//		uc = CookieUtil.query(cdm);
//		ChkUtilVo cuv = new ChkUtilVo();
//		cuv.setAs(this);
//		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
//		cuv.setRequest(request);
//		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
//		cuv.setUc(uc);
//		cuv.setUserDaoImp(userDaoImp);
//		cuv.setQn(qn);
		return cuv.getUserDaoImp().checkUserOnQa(cuv);
	}
	
}
