package com.lerx.style.site.util;

import java.util.List;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.comment.util.CommentUtil;
import com.lerx.comment.vo.Comment;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.vo.CommentStyleUtil;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.PageFormatShow;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class SiteStyleUtil {
	
	public static String commentAreaInit(WebElements wel,CommentStyleUtil csu,boolean ajaxMode){
		String htmlTemplate;
		String msg=StringUtil.nullFilter(wel.getMsg());
		ActionSupport as = wel.getAs();
		long tid=wel.getTid();
		IArticleThreadDao articleThreadDaoImp = wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp = wel.getArticleGroupDaoImp();
		IUserDao userDaoImp = wel.getUserDaoImp();
		ICommentDao commentDaoImp = wel.getCommentDaoImp();
		SiteInfo site = wel.getSite();
		SiteStyle curStyle=wel.getCurSiteStyle();
		int page = wel.getPage();
		int pageSize = wel.getPageSize();
		
		UserCookie uc = wel.getUc();
//		System.out.println("ucasdfasdf:");
//		System.out.println("uc:"+uc.getUsername());
		String html=csu.getHtml();
		String lineFormat=csu.getLineFormat();
		String formCode=csu.getFormCode();
		String editCode=csu.getEditCode();
		
		if (ajaxMode){
			htmlTemplate=html;
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$top$$}", "");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$footer$$}", "");
		}else{
			htmlTemplate=wel.getHtmlTemplate();
		}
		
		
		int stateMod;
		boolean commentAllow = false;
		boolean isManager, pwdMD5ToLowerCase, open;
		if (as.getText("lerx.pwdMD5ToLowerCase").trim()
				.equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		
		

		ArticleThread t = articleThreadDaoImp.findById(tid);
		ArticleGroup g = articleGroupDaoImp.findById(t
				.getArticleGroup().getId());
		ChkUtilVo cuv=CuvInit(wel);
		cuv.setAg(g);
		cuv.setUc(uc);
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		if (uc != null
				&& (userDaoImp.checkUserOnSite(cuv) == 2
						|| t.getUser().getId() == uc.getUserId() || t
						.getMember().trim().equals(uc.getUsername().trim()))) {
			stateMod = 1;
			isManager = true;
			
		} else {
			html = "";
			editCode="";
			stateMod = 0;
			isManager = false;
			
		}
		
		if (site.isCommentsOpen()) {
			open = true;
		} else {
			open = false;
		}
		if (isManager) {
			open = true;
		}

		FormatElements fel = new FormatElements();
		fel.setAs(as);

		String tmp, tmpAll = "";
		Rs rs = commentDaoImp.queryByThreadId(tid, page, pageSize, 1);
		

			@SuppressWarnings("unchecked")
			List<Comment> csl = (List<Comment>) rs.getList();

			for (Comment c : csl) {
				tmp = lineFormat;

				tmp = StringUtil.strReplace(tmp, "{$$editCode$$}",
						editCode);
				if (isManager){
					tmp = StringUtil.strReplace(tmp, "{$$commentEditCode$$}",
							editCode);
				}else{
					tmp = StringUtil.strReplace(tmp, "{$$commentEditCode$$}",
							"");
				}
				
				tmp = StringUtil.strReplace(tmp, "{$$html$$}",
						html);
				if (stateMod == 0) {
					tmp = StringUtil.strReplace(tmp, "{$$ip$$}", IpUtil
							.ipFilter(StringUtil.nullFilter(c.getIp()), Integer
									.valueOf(as.getText("lerx.rule.length.ip.filter"))));
				} else {
					tmp = StringUtil.strReplace(tmp, "{$$ip$$}", c.getIp());
				}

				fel.setLf(tmp);
				tmp = CommentUtil.formatHref(fel, c);

				tmp = StringUtil.strReplace(tmp, "{$$tid$$}", "" + tid);

				tmp = StringUtil.strReplace(tmp, "{$$page$$}", "" + page);
				tmp = StringUtil.strReplace(tmp, "{$$pageSize$$}", ""
						+ pageSize);
				tmpAll += tmp;
			}
		
		
//		System.out.println("tmpAll:"+tmpAll);
//		System.out.println("g.getCommentMode():"+g.getCommentMode());
//		System.out.println("uc.getUsername():"+uc.getUsername());
		if (t.isComment()
				&& ((g.getCommentMode() == 2 && uc != null) || (g
						.getCommentMode() == 3 || (g.getCommentMode() == 0 && site
						.getModeOfComment() == 3)))) {
			commentAllow = true;
			
		}
//		System.out.println("commentAllow:"+commentAllow);
		if (commentAllow) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$commentFormCode$$}", formCode);
		} else {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$commentFormCode$$}", "");
		}
		
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$commentData$$}", tmpAll);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$tid$$}",
				"" + tid);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$contextPath$$}", wel.getRequest().getContextPath());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$articleId$$}", "" + tid);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$tid$$}", "" + tid);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$page$$}", "" + page);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
		"{$$msg$$}", msg);
//		cuv.setAg(g);
//		cuv.setUc(uc);
//		cuv.setMode(0);
//		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		
		if (userDaoImp.checkUserOnSite(cuv) == 2){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$closeCommentStr$$}", as.getText("lerx.msg.comment.close"));
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$closeCommentStr$$}", "");
		}
		
		if (rs.getCount()>0 && open){
			if (ajaxMode){
				htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}",
						RsInit.rsPageStrShowAtFun(rs, "", pageFormatShowInit(as,curStyle)));
			}else{
				htmlTemplate = StringUtil
				.strReplace(
						htmlTemplate,
						"{$$pageShowStr$$}",
						RsInit.rsPageStrShow(
								rs,
								wel.getRequest().getContextPath()
										+ "/comm.action?tid=" + tid,
								PubUtil.pageFormatShowInit(as, wel), false));
			}
			
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$pageShowStr$$}",
			"");
		}
//		System.out.println("htmlTemplate2:"+htmlTemplate);
//		System.out.println("-----------------------");
//		System.out.println("htmlTemplate2:"+htmlTemplate);
//		System.out.println(htmlTemplate);
//		htmlTemplate=StringUtil.strReplace(htmlTemplate,
//				"{$$closeCommentStr$$}", commentAreaCode);
		return htmlTemplate;
	}
	
	public static PageFormatShow pageFormatShowInit(ActionSupport as,SiteStyle curStyle) {
		PageFormatShow pfs = new PageFormatShow();
		pfs.setEnd(as.getText("lerx.pageEndPageStr"));
		pfs.setFirst(as.getText("lerx.pageFirstPageStr"));
		pfs.setLast(as.getText("lerx.pageLastPageStr"));
		pfs.setNext(as.getText("lerx.pageNextPageStr"));
		pfs.setPrefix(as.getText("lerx.pageStrPrefix"));
		pfs.setSuffix(as.getText("lerx.pageStrSuffix"));
		pfs.setCountPrefix(as.getText("lerx.pageStrCountPrefix"));
		pfs.setJumpPrefix(as.getText("lerx.pageStrJumpPrefix"));
		pfs.setEyeCatchingCode(curStyle.getEyeCatchingCode());
		return pfs;
	}
	
	public static SiteStyle findForceStyle(ArticleGroup g,IArticleGroupDao articleGroupDaoImp){
		SiteStyle s=null;
		if (g.getStyle()!=null){
			s=g.getStyle();
		}else{
			s=articleGroupDaoImp.findParentForceStyle(g);
		}
		return s;
	}
	
	private static ChkUtilVo CuvInit(WebElements wel){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(wel.getInterconnectionDaoImp());
		
		return cuv;
	}
}
