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
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.QaUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadQaNav {
	@SuppressWarnings("unchecked")
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		String staticHtmlRoot = wel.getAs().getText("lerx.htmlPath");
		
		//从wel中取值，以防用过多的get方法
		IQaNavDao qaNavDaoImp = wel.getQaNavDaoImp();
		IQaItemDao qaItemDaoImp = wel.getQaItemDaoImp();
		ActionSupport as=wel.getAs();
		HttpServletRequest request=wel.getRequest();
		QaItem qi;
		SiteInfo site=wel.getSite();
		QaStyle curQaStyle=wel.getCurQaStyle();
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
		int mod = wel.getMod();
//		User u;
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
//		if (lcel.isLogined()==null){
//			
//		}
//		System.out.println("222211111---lcel.isLogined():"+lcel.isLogined());
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		UserCookie uc=wel.getUc();
		
		
		
		String location;
		QaNav qn=qaNavDaoImp.findById(gid);
		if (qn==null){
			return as.getText("lerx.err.parameter");
		}
//		String css,html;
		boolean qnNull;
		if (qn.getParentNav()==null){
			qnNull=true;
		}else{
			qnNull=false;
		}

		//下面五行顺序不能错
		QaStyleSubElementInCommon ec;
		if (qnNull){
			ec=curQaStyle.getIndexStyle();
		}else{
			ec=curQaStyle.getNavStyle();
		}
		wel=QaUtil.init(wel,ec);
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				qn.getTitle()));
		wel=QaUtil.endQaService(wel);
		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
		String locationSplitStr = wel.getLocationSplitStr();
		
		
		int stateMod,openMode;

		if (QaUtil.checkUserOnQa(wel,qn)==1){
			stateMod=0;
			openMode=0;
		}else{
			stateMod=1;
			openMode=1;
		}
		
		List<QaNav> qnl;
		if (qn.getParentNav()==null){
			qnl=qaNavDaoImp.findByParent(qn.getId(),1);
		}else{
			qnl=qaNavDaoImp.findByParent(qn.getParentNav().getId(),1); //与上同
		}
		String lf,tmpAll="",tmpItemsAll;
		String navsLoopFormat,itemsLoopFormat;
		if (ec.getNavsLoopFormat()==null || ec.getNavsLoopFormat().trim().equals("")){
			navsLoopFormat=curQaStyle.getPublicStyle().getNavsLoopFormat();
		}else{
			navsLoopFormat=ec.getNavsLoopFormat();
		}
		
		if (ec.getItemsLoopFormat()==null || ec.getItemsLoopFormat().trim().equals("")){
			itemsLoopFormat=curQaStyle.getPublicStyle().getItemsLoopFormat();
		}else{
			itemsLoopFormat=ec.getItemsLoopFormat();
		}
		
		List<Long> qil;
		Rs rs;
		boolean staticHtmlMode;
		if (site.getStaticHtmlMode() == 2){
			staticHtmlMode=true;
		}else{
			staticHtmlMode=false;
		}
		if (qnl.size()>0){

			for (QaNav n : qnl) {
				fel.setLf(navsLoopFormat);
				lf=QaNavUtil.formatHref(fel, n,staticHtmlMode,staticHtmlRoot);
				tmpAll+=lf;
//				System.out.println("tmpAll:"+tmpAll);
				fel.setLf(itemsLoopFormat);
				//如果是分类，获得栏目相关的items
				if (qn.getParentNav()==null){ //如果是分类
					rs=qaItemDaoImp.findQaItemsByGroupAndMod(n.getId(), page, pageSize, mod, 1,1, 0);
					if (rs.getList().size()>0){
						qil=(List<Long>) rs.getList();
						tmpItemsAll="";
						for (Long qiid : qil) {
							qi=qaItemDaoImp.findById(qiid);
							lf=QaItemUtil.formatHref(fel, qi);
							if (stateMod==1){
								lf = StringUtil.strReplace(lf, "{$$openStr$$}", "");
								if (qi.isState()){
									lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getProcessedStr());
								}else{
									lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getNoProcessedStr());
								}
							}else{
								if (qi.isOpen()){
									lf = StringUtil.strReplace(lf, "{$$openStr$$}", curQaStyle.getOpenStr());
								}else{
									lf = StringUtil.strReplace(lf, "{$$openStr$$}", curQaStyle.getNoOpenStr());
								}
								if (qi.isState()){
									lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getProcessedStr());
								}else{
									lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getNoProcessedStr());
								}
							}
							
							
							tmpItemsAll+=lf;
						}
						htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$navItemsList,"+n.getId()+"$$}",tmpItemsAll);
						
						
						
					}
				}
				
			}
		
		}
		
		//当前列表
		int numberShowOn;
		if (qn.getNumberShowOn()==0){
			numberShowOn=pageSize;
		}else{
			numberShowOn=qn.getNumberShowOn();
		}
		
		
		
		
		fel.setLf(ec.getNavsLoopFormat());
		if (qn.getParentNav()==null){
			location=QaNavUtil.locationStr(fel, qn, staticHtmlMode);
			rs=qaItemDaoImp.findQaItemsByParentAndMod(qn.getId(), page, numberShowOn, mod, stateMod, 0);
			
			htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$addArea$$}","");
		}else{

			String l1,l2;
			
			l1=QaNavUtil.locationStr(fel, qn.getParentNav(), staticHtmlMode);
			l2=QaNavUtil.locationStr(fel, qn, staticHtmlMode);
			location=l1+locationSplitStr+l2;
			rs=qaItemDaoImp.findQaItemsByGroupAndMod(qn.getId(), page, numberShowOn, mod, stateMod,openMode, 0);
			
			
			
			//增加区域item显示
			System.currentTimeMillis();
			String secStr = null;
			String randKey = StringUtil.randomString(10).toLowerCase();
			try {
				secStr = SrvInf.readSecStr(request, as.getText("lerx.hostSecFile"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			secStr = StringUtil.md5(secStr.concat(randKey)).toLowerCase();
			htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$addArea$$}",curQaStyle.getAddAreaCode());
			if (secStr != null) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$secStr$$}", secStr);
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$randKey$$}", randKey);
			}else{
			}
			
		
		}
		if (rs.getList().size()>0){

			qil=(List<Long>) rs.getList();
			tmpItemsAll="";
			fel.setLf(ec.getItemsLoopFormat());
			for (Long qiid : qil) {
				qi=qaItemDaoImp.findById(qiid);
				lf=QaItemUtil.formatHref(fel, qi);
				if (stateMod==1){
					lf = StringUtil.strReplace(lf, "{$$openStr$$}", "");
					if (qi.isState()){
						lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getProcessedStr());
					}else{
						lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getNoProcessedStr());
					}
				}else{
					if (qi.isOpen()){
						lf = StringUtil.strReplace(lf, "{$$openStr$$}", curQaStyle.getOpenStr());
					}else{
						lf = StringUtil.strReplace(lf, "{$$openStr$$}", curQaStyle.getNoOpenStr());
					}
					if (qi.isState()){
						lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getProcessedStr());
					}else{
						lf = StringUtil.strReplace(lf, "{$$processedStr$$}", curQaStyle.getNoProcessedStr());
					}
				}
				tmpItemsAll+=lf;
			}
			

			htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$curItemsList$$}",tmpItemsAll);
			htmlTemplate = StringUtil
			.strReplace(
					htmlTemplate,
					"{$$pageShowStr$$}",
					RsInit.rsPageStrShow(
							rs,
							request.getContextPath()
									+ "/qaNav.action?gid=" + gid,
									PubUtil.pageFormatShowInit(as, wel), false));
			
		
		}else{

			htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$pageShowStr$$}","");
			htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$curItemsList$$}","");
		
		}
		htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$navList$$}",tmpAll);
		htmlTemplate=StringUtil.strReplace(htmlTemplate,"{$$gid$$}",""+gid);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$app$$}", qn.getTitle());
		
		if (qn.isLoginNeed() && uc==null){
			
			String rootFolder;
			rootFolder=wel.getSiteStyleDaoImp().findDef().getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(as);
			rfv.setRequest(request);
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("loginNeed.txt");
			rfv.setSubFolder("html");
			
			String txt = FileUtil.readFile(rfv);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$loginNeedMessage$$}", txt);
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", " disabled =\"true\" ");
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$loginNeedMessage$$}", "");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", "");
		}
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$location$$}",
				location);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageSize$$}",
				""+numberShowOn);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$page$$}",
				""+page);
		
		
		return htmlTemplate;
	}
}
