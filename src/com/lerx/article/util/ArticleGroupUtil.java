package com.lerx.article.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.sys.util.StringUtil;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleGroupUtil {
	public static String findFolder(ArticleGroup ag,boolean staticMod,ActionSupport as,HttpServletRequest request){
		String glinkStr;
		boolean jump;
		if (ag.getJumpUrl()==null || ag.getJumpUrl().trim().equals("")){
			jump=false;
		}else{
			jump=true;
		}
		if (jump){
			glinkStr=ag.getJumpUrl().trim();
		}else{
			if (staticMod && !ag.isRefuseStaticHtml()){
				String staticHtmlRoot = as.getText("lerx.htmlPath");
				String staticFileFolderOnRoot=as.getText("lerx.staticFileFolderOnRoot");
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
				String gHtmlFolder = ag.getStaticHtmlFolder();
				if (gHtmlFolder == null
						|| gHtmlFolder.trim().equals("")) {
					gHtmlFolder = staticHtmlRoot
							+ "c"
							+ ag.getId();
				} else {
					gHtmlFolder = staticHtmlRoot
							+ "/" + gHtmlFolder;
				}
				glinkStr = "<a href=\""
						+ request.getContextPath() + "/"
						+ gHtmlFolder + "\">"
						+ ag.getGroupName() + "</a>";
			}else{
				glinkStr = "<a href=\""
					+ request.getContextPath()
					+ "/"
					+ as.getText(
							"lerx.articleGroupPageFileName")
							.trim() + "?gid="
					+ ag.getId() + "\">"
					+ ag.getGroupName() + "</a>";
			}
		}
		
		return glinkStr;
	}
	
	
	public static String createSelectStr(WebElements wel,ActionSupport as,List<ArticleGroupShowModel> it,String agSelectStr,String selectOptionFormat,int gid){
		ArticleGroup agtmp;
		boolean agJump;
		String soStr, soAllStr = "",curMask;
		for (ArticleGroupShowModel m : it) {
			agtmp=m.getArticleGroup();
			if (agtmp.getJumpUrl()==null || agtmp.getJumpUrl().trim().equals("")){
				agJump=false;
			}else{
				agJump=true;
			}
			if (agSelectStr!=null && as.getText("lerx.userGroupNavsSelectStr").trim().equalsIgnoreCase("true")){
				
				if (m.getArticleGroup().isState() && !agJump) {
					curMask="{$$"+m.getArticleGroup().getId()+"$$}";
					if (agSelectStr.indexOf(curMask) != -1){
						soStr = selectOptionFormat;
						if (m.getArticleGroup().getId() == gid
								&& !m.getArticleGroup().isAsClass()) {
							soStr = StringUtil.strReplace(soStr,
									"{$$selectState$$}", "selected");
						} else {
							soStr = StringUtil.strReplace(soStr,
									"{$$selectState$$}", "");
						}
						if (SiteUtil.checkUserOnSite(wel,m.getArticleGroup()) == 0
								|| m.getArticleGroup().isAsClass()) {
							soStr = StringUtil
									.strReplace(soStr, "{$$disabledStr$$}",
											" disabled=\"true\" style=\"font-style:oblique;\" ");

						} else {
							soStr = StringUtil.strReplace(soStr,
									"{$$disabledStr$$}", "");
						}
						soStr = StringUtil.strReplace(soStr, "{$$value$$}", ""
								+ m.getArticleGroup().getId());
						soStr = StringUtil.strReplace(soStr, "{$$display$$}",
								m.getPrefixStr()
										+ m.getArticleGroup().getGroupName());
						soAllStr += soStr;
						
					}
				}
				
				
			}else{
				if (m.getArticleGroup().isState() && !agJump) {
					soStr = selectOptionFormat;
					if (m.getArticleGroup().getId() == gid
							&& !m.getArticleGroup().isAsClass()) {
						soStr = StringUtil.strReplace(soStr,
								"{$$selectState$$}", "selected");
					} else {
						soStr = StringUtil.strReplace(soStr,
								"{$$selectState$$}", "");
					}
					if (SiteUtil.checkUserOnSite(wel,m.getArticleGroup()) == 0
							|| m.getArticleGroup().isAsClass()) {
						soStr = StringUtil
								.strReplace(soStr, "{$$disabledStr$$}",
										" disabled=\"true\" style=\"font-style:oblique;\" ");

					} else {
						soStr = StringUtil.strReplace(soStr,
								"{$$disabledStr$$}", "");
					}
					soStr = StringUtil.strReplace(soStr, "{$$value$$}", ""
							+ m.getArticleGroup().getId());
					soStr = StringUtil.strReplace(soStr, "{$$display$$}",
							m.getPrefixStr()
									+ m.getArticleGroup().getGroupName());
					soAllStr += soStr;
				}
			}
			

		}
		return soAllStr;
	}
	
	
	public static boolean pollFmt(ArticleGroup g,int pos){
		if (g==null){
			return false;
		}
		String fmtStr=g.getPollFmt();
		if (fmtStr==null || fmtStr.trim().equals("") || !StringUtil.isNumber(fmtStr)){
//			System.out.println("posnulkl");
			return false;
		}else{
			fmtStr=fmtStr.trim();
//			System.out.println("pos "+pos+" :"+fmtStr.charAt(pos));
			if (fmtStr.charAt(pos)=='1'){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public static boolean pollChk(ArticleGroup g){
		if (g==null){
			return false;
		}
		String fmtStr=g.getPollFmt();
		if (fmtStr==null || fmtStr.trim().equals("")){
			return false;
		}else{
			fmtStr=fmtStr.trim();
			int tmp=0;
			for (int i=0;i<=2;i++){
				if ((fmtStr.charAt(i))=='1'){
					tmp++;
				}
//				System.out.println("i--"+i + "--:" + fmtStr.charAt(i));
//				tmp=tmp+Integer.valueOf(fmtStr.charAt(i));
			}
			
//			System.out.println("tmp--:" + tmp);
			if (tmp==0){
				return false;
			}else{
				return true;
			}
		}
		
	}
	
}
