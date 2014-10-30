package com.lerx.web.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.site.vo.SiteStyleSubElementInCommon;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.UserGroup;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadNav {
	@SuppressWarnings("unchecked")
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
//		wel.setStation(0);
		String staticHtmlRoot = wel.getAs().getText("lerx.htmlPath");
		boolean c = true;
		boolean staticMod;
		
		//从wel中取值，以防用过多的get方法
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		
		ActionSupport as=wel.getAs();
		
		int speedMod=Integer.valueOf(as.getText("lerx.data.query.mod"));
		
		HttpServletRequest request=wel.getRequest();
		
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
		//取值结束
		
		ArticleThread at;
		ArticleGroup g = articleGroupDaoImp.findById(gid);
		if (wel.getSite().getStaticHtmlMode() == 2){
			staticMod=true;
		}else{
			staticMod=false;
		}
		
		int noOpenShowMod;
		if (as.getText("lerx.articleNoOpenShowTitle").trim().equals("true")){
			noOpenShowMod=1;
		}else{
			noOpenShowMod=0;
		}
		boolean show=false;
		if (g != null && (g.isShare() || (!g.isShare() && noOpenShowMod==1))){
			show=true;
		}
		
		if (g != null && g.isState() && show) {
			//下面五行顺序不能错
			wel=SiteUtil.initSiteElement(wel, curSiteStyle.getClassStyle());
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
					g.getGroupName()));
			wel=SiteUtil.endSiteService(wel);
			FormatElements fel=wel.getFel();
			fel.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			String htmlTemplate = wel.getHtmlTemplate();
			
			SiteStyleSubElementInCommon sel=wel.getSel();
			//取值结束
			
			String curIP = IpUtil.getRealRemotIP(wel.getRequest()).trim();
			
			/*
			 * 下面是计数，不适用，后台已增加统计功能，取消
			 */
//			String requestUri=""+wel.getRequest().getRequestURL();
//			String queryString=wel.getRequest().getQueryString();
//			String refererURL=wel.getRequest().getHeader("Referer");
			
//			if (queryString!=null && !queryString.trim().equals("")){
//				requestUri+="?"+queryString;
//			}
//			if (!WebUtil.check(refererURL, requestUri)){
//				g.setViews(g.getViews()+1);
//				articleGroupDaoImp.modifyArticleGroup(g);
//				
//			}
			
			LoginCheckEl lcel=PubUtil.logincheck(wel);
			wel.setCdm(lcel.getCdm());
			wel.setUc(lcel.getUc());
			wel.setUserLogined(lcel.isLogined());
			
			UserGroup ug;
			UserCookie uc=wel.getUc();
			
			
			if (uc == null) {
				ug = null;
			} else {
				if (userDaoImp.findUserById(uc.getUserId()) == null) {
					ug = null;
				} else {
					ug = userDaoImp.findUserById(uc.getUserId()).getUserGroup();
				}

			}
			if (!articleGroupDaoImp.checkShare(g)
					&& (ug == null || !ug.isState())) {
				c = false;
			}
			if (!IpUtil.isInRange(curIP,
					articleGroupDaoImp.findAllHostAllowStrByArticleGroup(g))) {
				c = false;
			}
			
			String staticFileFolderOnRoot=wel.getAs().getText("lerx.staticFileFolderOnRoot");
			boolean staticOnRoot;
			if (staticFileFolderOnRoot.trim().equals("true")){
				staticOnRoot=true;
			}else{
				staticOnRoot=false;
			}
			if (staticOnRoot){
				staticHtmlRoot="";
			}else{
				staticHtmlRoot += File.separator;
			}
			if (!c && !show) {
				htmlTemplate = as.getText("lerx.access.denied");
			} else {

				wel=SiteUtil.endSiteService(wel);
				String locationNoStatic = "";
				locationNoStatic = "<a href=\"" + request.getContextPath() + "/"
						+ as.getText("lerx.articleGroupPageFileName").trim()
						+ "?gid=" + g.getId() + "\">" + g.getGroupName()
						+ "</a>";

				ArticleGroup t = g, p;
				String location="";
				if ((t.getParentGroup() != null)) {
					while (t.getParentGroup() != null) {
						p = t.getParentGroup();
						location = ArticleGroupUtil.findFolder(p, staticMod, as, request)+ wel.getLocationSplitStr() + location;
						t = p;
					}
				}
				location += locationNoStatic;
				// 栏目导航
				List<ArticleGroup> navList = articleGroupDaoImp
						.findByParentId(gid);
				if (navList.size() == 0) {
					if (g.getParentGroup() == null) {
						navList = articleGroupDaoImp
								.findByParentId(0);
					} else {
						navList = articleGroupDaoImp
								.findByParentId(g.getParentGroup()
										.getId());
					}
				}
				String lineBreakStrS,lineBreakStrE,columnSplitStrS,columnSplitStrE;
				String lineBlockFormat=StringUtil.nullFilter(curSiteStyle.getLineBlockFormat());
				String columnBlockFormat=StringUtil.nullFilter(curSiteStyle.getColumnBlockFormat());
				lineBlockFormat=StringUtil.strReplace(lineBlockFormat, "{$$data$$}", ",");
				columnBlockFormat=StringUtil.strReplace(columnBlockFormat, "{$$data$$}", ",");
				String[] sArray = lineBlockFormat.split(",");
				if (sArray.length==2){
					lineBreakStrS=sArray[0];
					lineBreakStrE=sArray[1];
				}else{
					lineBreakStrS="<li>";
					lineBreakStrE="</li>";
				}
				
				sArray = columnBlockFormat.split(",");
				if (sArray.length==2){
					columnSplitStrS=sArray[0];
					columnSplitStrE=sArray[1];
				}else{
					columnSplitStrS="<span>";
					columnSplitStrE="</span>";
				}
				
				String navListStrAll = "", navListStrLine, glinkStr;
				if (navList.size() > 0) {
					for (ArticleGroup sgtmp : navList) {
						if (sgtmp.isState()) {
							navListStrLine = "";
							navListStrLine += lineBreakStrS
									+ columnSplitStrS;
							glinkStr=ArticleGroupUtil.findFolder(sgtmp, staticMod, as, request);
							navListStrLine += glinkStr;
							navListStrLine += columnSplitStrE
									+ lineBreakStrE;
							navListStrAll += navListStrLine;
						}

					}
				}
				String lf, tmpA;
				int recNewMode=Integer.valueOf(as.getText("lerx.recNewMode"));
				int titleLength = g.getLengthShowOnParent(); // 标题长度
				if (g.isShowAll()
						|| articleThreadDaoImp.articlesExistAtGroup(g)) {
					
					/*
					 * 当前栏目的文章列表
					 */
					// 要修改以下。state，如果有权限 state为0
					int numList=Integer.valueOf(as.getText("lerx.pageSize.result.nav"));
					if (numList>0){
						pageSize=numList;
					}
					
					if (g.getNumberList() != 0) {

						pageSize = g.getNumberList();
					}
					
					int check = SiteUtil.checkUserOnSite(wel,g);
					int stateMode = 0; // 0显示所有 1显示已通过审核列表 2显示未审核列表
					if (check == 2) {
						stateMode = 0;
					} else {
						stateMode = 1;
					}
					int soul;	//是否精华靠前
					if (g.isSoulOnIndex()){
						soul=1;
					}else{
						soul=0;
					}
					// 按发表时间或id倒序 0
					Rs ns = articleThreadDaoImp.findByGroupAndMod(gid,
							page, pageSize, recNewMode, stateMode, false, soul,0,false,speedMod);
					if (page > ns.getPageCount()) {
						page = ns.getPageCount();
						ns = articleThreadDaoImp.findByGroupAndMod(gid,
								page, pageSize, recNewMode, stateMode, false, soul,0,false,speedMod);
					}
					
					List<Long> lan = null;
					List<ArticleThread> lat = null;
					int rsSize=0;
					if (speedMod==1){
						lat=(List<ArticleThread>) ns.getList();
						rsSize=lat.size();
					}else{
						lan = (List<Long>) ns.getList();
						rsSize=lan.size();
					}
					
//					@SuppressWarnings("unchecked")
//					List<Long> na = (List<Long>) ns.getList();
					tmpA = "";
					if (rsSize == 0) {
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$curClassDataCode$$}", "");

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$pageShowStr$$}", "");
					} else {
						fel.setTitleLength(0);
						fel.setEyeCatchingStrCode(curSiteStyle.getEyeCatchingCode());
						if (speedMod==1){
							for (ArticleThread ati : lat) {
								fel.setLf(wel.getHrefLineFormat());
								if (check == 2) {
									fel.setIncludeEditArea(true);
								} else {
									fel.setIncludeEditArea(false);
								}
								at=ati;
								at=ThreadUtil.escape(at);
								lf = ThreadUtil.formatHref(fel, at);

								tmpA += lf;
							}
						}else{
							for (Long atid : lan) {
								fel.setLf(wel.getHrefLineFormat());
								if (check == 2) {
									fel.setIncludeEditArea(true);
								} else {
									fel.setIncludeEditArea(false);
								}
								at=articleThreadDaoImp.findById(atid);
								at=ThreadUtil.escape(at);
								lf = ThreadUtil.formatHref(fel, at);

								tmpA += lf;
							}
						}
						
						
						if (check == 2) {
							String formTemplate=sel.getFormTemplate();
							formTemplate=StringUtil.strReplace(formTemplate,
									"{$$data$$}", tmpA);
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$curClassDataCode$$}", formTemplate);
							
						}else{
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$curClassDataCode$$}", tmpA);
						}
						
						htmlTemplate = StringUtil
								.strReplace(
										htmlTemplate,
										"{$$pageShowStr$$}",
										RsInit.rsPageStrShow(
												ns,
												request.getContextPath()
														+ "/"
														+ as.getText(
																"lerx.articleGroupPageFileName")
																.trim()
														+ "?gid=" + gid,
														PubUtil.pageFormatShowInit(as, wel), false));
					}

				} else {
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$pageShowStr$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$curClassDataCode$$}", "");
				}
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$classTitle$$}", g.getGroupName());
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$privateHtml$$}", g.getPrivateHtml());
				
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$location$$}", location);

				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$gid$$}", "" + gid);
				// 子类文章列表
				String navLoopFormat = sel.getMajorLoopCodeInLump();
				if (navLoopFormat==null || navLoopFormat.trim().equals("")){
					navLoopFormat=sel.getMinorLoopCodeInLump();
				}
				String tmpLoopStr = "";
				tmpA = "";
				int numOnParent;
				List<ArticleGroup> sgl = articleGroupDaoImp
						.findByParentId(gid);
				for (ArticleGroup sg : sgl) {
					if (sg.isShowOnParent()) {
						titleLength=sg.getLengthShowOnParent();
						tmpLoopStr = navLoopFormat;
						tmpLoopStr = StringUtil.strReplace(tmpLoopStr,
								"{$$gid$$}", "" + sg.getId());
						tmpLoopStr = StringUtil.strReplace(tmpLoopStr,
								"{$$groupTitle$$}", sg.getGroupName());
						numOnParent = sg.getNumberShowOnParent();
						if (numOnParent < 1) {
							numOnParent = Integer
									.valueOf(as.getText("lerx.pageSize.result.default"));
						}
						int soul;
						if (g.isSoulOnIndex()){
							soul=1;
						}else{
							soul=0;
						}
						Rs rs = articleThreadDaoImp.findByGroupAndMod(
								sg.getId(), 1, numOnParent, recNewMode, 1, false, soul,0,false,speedMod);
//						
						List<Long> lan = null;
						List<ArticleThread> lat = null;
						if (speedMod==1){
							lat=(List<ArticleThread>) rs.getList();
						}else{
							lan = (List<Long>) rs.getList();
						}
						
						
						String tmp = "";
						fel.setTitleLength(titleLength);
						fel.setIncludeEditArea(false);
						if (speedMod==1){
							for (ArticleThread ati : lat) {
								at=ati;
								at=ThreadUtil.escape(at);
								fel.setLf(wel.getHrefLineFormat());
								lf = ThreadUtil.formatHref(fel, at);
								tmp += lf;
							}
						}else{
							for (Long aid : lan) {
								at=articleThreadDaoImp.findById(aid);
								at=ThreadUtil.escape(at);
								fel.setLf(wel.getHrefLineFormat());
								lf = ThreadUtil.formatHref(fel, at);
								tmp += lf;
							}
						}
						
						
						//
						glinkStr=ArticleGroupUtil.findFolder(sg, staticMod, as, request);
						tmpLoopStr = StringUtil.strReplace(tmpLoopStr,
								"{$$classTitleWithHref$$}", glinkStr);
						/*
						 * 下一行按自定义格式输出 如果设计了分类模板，下行将产生效果
						 */
						
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$subList," + sg.getId() + "$$}", tmp);
						//
						tmpLoopStr = StringUtil.strReplace(tmpLoopStr,
								"{$$subGroupThreadList$$}", tmp);

						tmpA += tmpLoopStr;
					}

				}
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$allSubList$$}", tmpA);
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$navList$$}", navListStrAll);
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$navIco$$}", StringUtil.nullFilter(g.getIconUrl()));

//				htmlTemplate = endPage(htmlTemplate, 0);

			}
			
			return htmlTemplate;	
		}else{
			return as.getText("lerx.err.notFound");
		}
		
	}
	
	
}
