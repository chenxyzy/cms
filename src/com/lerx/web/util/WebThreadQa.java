package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.util.QaItemUtil;
import com.lerx.qa.util.QaNavUtil;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.qa.vo.QaStyleSubElementInCommon;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.QaUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadQa {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		String staticHtmlRoot = wel.getAs().getText("lerx.htmlPath");
		
		//从wel中取值，以防用过多的get方法
		IQaNavDao qaNavDaoImp = wel.getQaNavDaoImp();
		IQaItemDao qaItemDaoImp = wel.getQaItemDaoImp();
		ActionSupport as=wel.getAs();
		HttpServletRequest request=wel.getRequest();
		
		SiteInfo site=wel.getSite();
		QaStyle curQaStyle;
		long tid = wel.getTid();
		long id = wel.getId();
		String pwd = wel.getPwd();
		if (tid == 0){
			tid=id;
		}
		
		String location;
		QaItem qi=qaItemDaoImp.findById(tid);
		QaNav qn=qi.getQn();
		if (qn!=null && qn.getStyle()!=null){
			curQaStyle=qn.getStyle();
		}else{
			curQaStyle=wel.getCurQaStyle();
		}
		if (qn==null){
			return as.getText("lerx.err.parameter");
		}
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				qn.getTitle()));
		//下面五行顺序不能错
		QaStyleSubElementInCommon ec=curQaStyle.getItemStyle();
		wel=QaUtil.init(wel,ec);
		
		FormatElements fel=wel.getFel();
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		String html;
		html=ec.getHtmlCode();
		
		UserCookie uc=wel.getUc();
		if (uc != null && QaUtil.checkUserOnQa(wel,qn)==1){
			html=StringUtil.strReplace(html,"{$$replyArea$$}",curQaStyle.getReplyAreaCode());
		}else{
			html=StringUtil.strReplace(html,"{$$replyArea$$}","");
		}
		
		if (uc!=null){
			html=StringUtil.strReplace(html,"{$$remName$$}",StringUtil.nullFilter(uc.getRemName()));
		}else{
			html=StringUtil.strReplace(html,"{$$remName$$}","");
		}
		fel.setLf(html);
		html=QaItemUtil.formatHref(fel, qi);
		wel.setHtml(html);
		
		
		wel=QaUtil.endQaService(wel);
		
		
		
		
		
		String htmlTemplate = wel.getHtmlTemplate();
		String locationSplitStr = wel.getLocationSplitStr();
		boolean staticHtmlMode;
//		int stateMod;
		if (site.getStaticHtmlMode() == 2){
			staticHtmlMode=true;
		}else{
			staticHtmlMode=false;
		}
		
		List<QaNav> qnl;
		if (qn.getParentNav()==null){
			qnl=qaNavDaoImp.findByParent(qn.getId(),1);
		}else{
			qnl=qaNavDaoImp.findByParent(qn.getParentNav().getId(),1); //与上同
		}
		String tmpAll="",lf;
		String navsLoopFormat;
		if (ec.getNavsLoopFormat()==null || ec.getNavsLoopFormat().trim().equals("")){
			navsLoopFormat=curQaStyle.getPublicStyle().getNavsLoopFormat();
//			fel.setLf(curQaStyle.getPublicStyle().getNavsLoopFormat());
		}else{
			navsLoopFormat=ec.getNavsLoopFormat();
//			fel.setLf(ec.getNavsLoopFormat());
		}
		fel.setLf(navsLoopFormat);
		if (qnl.size()>0){
			for (QaNav n : qnl) {
				
				lf=QaNavUtil.formatHref(fel, n,staticHtmlMode,staticHtmlRoot);
				tmpAll+=lf;
			}
		}
		
		// 判断页面访问并保存
		String curIP = IpUtil.getRealRemotIP(request).trim();
		String lastViewIP = qi.getLastViewIp();

		if (!as.getText("lerx.mode.realtime.byAjax").trim()
				.equalsIgnoreCase("true")) {

			boolean saveT = false;

			if (lastViewIP == null) {
				lastViewIP = "";
			}
			if (as.getText("lerx.viewsUpdateByIp").trim().equals("true")){
				if (!lastViewIP.trim().equals(curIP)) {
					qi.setLastViewIp(curIP);
					qi.setViews(qi.getViews() + 1);
					saveT = true;
				}
			}else{
				qi.setViews(qi.getViews() + 1);
				saveT = true;
			}
			
			if (saveT) {
				qaItemDaoImp.modify(qi);
			}
		
		}
		boolean pwdRead;
		String pwdAtQi;
		pwdAtQi=qi.getPassword();
		if (pwdAtQi==null){
			pwdAtQi="";
		}
		
		if (pwd==null){
			pwd="";
		}
		
		if (pwd.trim().equals("") || pwdAtQi.trim().equals("")){
			pwdRead=false;
		}else{
			if (pwd.trim().equalsIgnoreCase(pwdAtQi.trim())){
				pwdRead=true;
			}else{
				pwdRead=false;
			}
		}
		if (!qi.isOpen() && QaUtil.checkUserOnQa(wel,qn)!=1 && !pwdRead){
			return as.getText("lerx.fail.power");
		}
		
		
		if (qn.getParentNav()==null){
			location=QaNavUtil.locationStr(fel, qn, staticHtmlMode);
		}else{
			String l1,l2;
			l1=QaNavUtil.locationStr(fel, qn.getParentNav(), staticHtmlMode);
			l2=QaNavUtil.locationStr(fel, qn, staticHtmlMode);
			location=l1+locationSplitStr+l2;
		}
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$htmlBody$$}",
				html);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				location);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$app$$}", qi.getTitle());
		htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$navList$$}",tmpAll);
		return htmlTemplate;
	}
}
