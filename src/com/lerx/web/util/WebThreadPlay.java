package com.lerx.web.util;

import java.io.IOException;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.util.AttaUtil;
import com.lerx.attachment.vo.Attachment;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadPlay {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(true);
		//从wel中取值，以防用过多的get方法
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
//		IUserDao userDaoImp=wel.getUserDaoImp();
		IAttachmentDao attachmentDaoImp=wel.getAttachmentDaoImp();
		IExternalHostCharsetDao externalHostCharsetDaoImp=wel.getExternalHostCharsetDaoImp();
		ActionSupport as=wel.getAs();
//		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
//		int pageSize=wel.getPageSize();
//		int page=wel.getPage();
//		int gid=wel.getGid();
//		int smode=wel.getSmode();
//		int umode=wel.getUmode();
//		int stateMode=wel.getStateMode();
//		int mod=wel.getMod();
//		boolean notice=wel.isNotice();
//		int soul =wel.getSoul();
		long id = wel.getId();
//		ArticleGroup g = articleGroupDaoImp.findArticleGroupById(gid);
		
		//取值结束
		Attachment atta = attachmentDaoImp.find(id);
		atta.setTitle(StringUtil.escape(atta.getTitle()));
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getGenericStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				atta.getTitle()));
		wel=SiteUtil.endSiteService(wel);
//		FormatElements fel=wel.getFel();
//		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
//		ResultEl re=wel.getRe();
		
		String mediaPlayOuterLayerCode = curSiteStyle
		.getMediaPlayOuterLayerCodeForAttaPage();
		switch (atta.getMediaType()) {

		case 2:
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$playerMainBody$$}",
					curSiteStyle.getMediaPlayCode2());
			break;
		case 3:
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$playerMainBody$$}",
					curSiteStyle.getMediaPlayCode3());
			break;
		default:
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$playerMainBody$$}",
					curSiteStyle.getMediaPlayCode1());
			break;
		}
		
		mediaPlayOuterLayerCode = StringUtil.strReplace(
				mediaPlayOuterLayerCode, "{$$attaId$$}", "" + id);
		switch (atta.getHostType()) {
		case 2:
			break;
		case 3:
			break;
		default:
			ArticleThread art = articleThreadDaoImp.findById(atta
					.getHostId());
			art=ThreadUtil.escape(art);
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$parentTitle$$}",
					art.getTitle());
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$attaTitle$$}",
					atta.getTitle());
			String attaUrl=atta.getUrl();
			attaUrl=AttaUtil.encoder(externalHostCharsetDaoImp,as, attaUrl,1);
			mediaPlayOuterLayerCode = StringUtil.strReplace(
					mediaPlayOuterLayerCode, "{$$attaUrl$$}",
					atta.getUrl());
			break;
		}
		return mediaPlayOuterLayerCode;
	}
}
