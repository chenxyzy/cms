package com.lerx.web.util;

import java.io.IOException;
import java.text.ParsePosition;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.util.AttaUtil;
import com.lerx.attachment.vo.Attachment;
import com.lerx.comment.dao.ICommentDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadArtshow {
	public static String show(WebElements wel) throws IOException{
		String htmlTemplate;
		wel.setRefererRec(false);
		boolean c = true;
		
		//从wel中取值，以防用过多的get方法
		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		IExternalHostCharsetDao externalHostCharsetDaoImp=wel.getExternalHostCharsetDaoImp();
		IAttachmentDao attachmentDaoImp=wel.getAttachmentDaoImp();
		ICommentDao commentDaoImp=wel.getCommentDaoImp();
		ActionSupport as=wel.getAs();
		HttpServletRequest request=wel.getRequest();
		
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		long tid=wel.getTid();
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		
		//取值结束
		
		String lastArticleForwardCode, nextArticleForwardCode;
		String curIP = IpUtil.getRealRemotIP(request).trim();
		String artTitle,eyeCatchingStrTmp;
		
		ArticleThread t = articleThreadDaoImp.findById(tid);
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getThreadStyle());
		if (t != null) {
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
					t.getTitle()));
		}else{
			wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
					as.getText("lerx.err.notFound")));
		}
		
		wel=SiteUtil.endSiteService(wel);
//		FormatElements fel=wel.getFel();
		
		boolean staticMod;
		if (wel.getSite().getStaticHtmlMode() == 2){
			staticMod=true;
		}else{
			staticMod=false;
		}
		if (t!=null){
			t=ThreadUtil.escape(t);
			htmlTemplate = wel.getHtmlTemplate();
			ArticleGroup gtmp = t.getArticleGroup();
			if (gtmp.isState()) {
				
				
				LoginCheckEl lcel=PubUtil.logincheck(wel);
				wel.setCdm(lcel.getCdm());
				wel.setUc(lcel.getUc());
				wel.setUserLogined(lcel.isLogined());
				
				
				// 栏目导航
				List<ArticleGroup> navList = articleGroupDaoImp
						.findByParentId(gtmp.getId());
				if (navList.size() == 0) {
					if (gtmp.getParentGroup() == null) {
						navList = articleGroupDaoImp
								.findByParentId(0);
					} else {
						navList = articleGroupDaoImp
								.findByParentId(gtmp.getParentGroup()
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
				
				//----------------
				UserGroup ug;
				UserCookie uc=wel.getUc();
				User u=null;
				if(uc!=null){
					u=userDaoImp.findUserById(uc.getUserId());
				}
				lastArticleForwardCode = curSiteStyle
						.getLastArticleForwardCode();
				nextArticleForwardCode = curSiteStyle
						.getNextArticleForwardCode();
				
				
					if (u == null) {
						ug = null;
					} else {
						ug = u.getUserGroup();
					}

				if (!articleGroupDaoImp.checkShare(gtmp)
						&& (ug == null || !ug.isState())) {
					c = false;
				}
				if (!IpUtil.isInRange(curIP, articleGroupDaoImp
						.findAllHostAllowStrByArticleGroup(gtmp))) {
					c = false;
				}

				if (!c) {
					request.setAttribute("lerxCmsBody",
							as.getText("lerx.access.denied"));
					htmlTemplate = as.getText("lerx.access.denied");
					
				} else {

					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
							as.getText("lerx.default.format.datetime"));
					ParsePosition pos = new ParsePosition(
							Integer.valueOf(as.getText("lerx.dateParsePosition")
									.trim()));

					String encryptedParmStr = StringUtil.md5(StringUtil
							.randomString(64));
					if (!as.getText("lerx.mode.realtime.byAjax").trim()
							.equalsIgnoreCase("true")) {
						t.setEncryptedParmStr(encryptedParmStr);
						articleThreadDaoImp.modify(t);
					}
					
					eyeCatchingStrTmp=curSiteStyle.getEyeCatchingCode();
					
					if (!t.isState()
							&& checkUserOnSite(wel,gtmp) < 2
							&& (uc == null || (uc.getUserId() != t.getUser()
									.getId() && !uc.getUsername().trim()
									.equals(t.getMember().trim())))) {
						htmlTemplate=curSiteStyle.getNoPassedAltStr();
					} else {

						// 判断页面访问并保存
						boolean jump=false;
						// 如果跳转
						if (t.isLinkJump() && t.getLinkUrl() != null
								&& !t.getLinkUrl().trim().equals("")) {
							jump=true;
						}
						
						String lastViewIP = t.getLastViewIp();

						if (jump || !as.getText("lerx.mode.realtime.byAjax").trim()
								.equalsIgnoreCase("true")) {
							boolean saveT = false;

							if (lastViewIP == null) {
								lastViewIP = "";
							}
							if (as.getText("lerx.viewsUpdateByIp").trim().equals("true")){
								if (!lastViewIP.trim().equals(curIP)) {
									t.setLastViewIp(curIP);
									t.setViews(t.getViews() + 1);
									saveT = true;
								}
							}else{
								t.setViews(t.getViews() + 1);
								saveT = true;
							}
							
							if (saveT) {
//								long gviews=gtmp.getViews();
//								gviews++;
//								gtmp.setViews(gviews);
//								articleGroupDaoImp.modifyArticleGroup(gtmp);
								articleThreadDaoImp.modify(t);
							}
						}
						
						// 如果跳转
						if (jump) {
							request.setAttribute("toUrl", t.getLinkUrl());
							return "jump";
						}

						// 上一条，下一条
						ArticleThread lastT, nextT;

						lastT = articleThreadDaoImp.findAdjacent(tid, 0, t
								.getArticleGroup().getId(), checkUserOnSite(wel,t
								.getArticleGroup()));
						lastT=ThreadUtil.escape(lastT);
						nextT = articleThreadDaoImp.findAdjacent(tid, 1, t
								.getArticleGroup().getId(), checkUserOnSite(wel,t
								.getArticleGroup()));
						nextT=ThreadUtil.escape(nextT);
						String location="",curLocation;
						if (t.getArticleGroup() != null) {
							ArticleGroup g = articleGroupDaoImp
									.findById(t.getArticleGroup()
											.getId());
							curLocation = ArticleGroupUtil.findFolder(g, staticMod, as, request);

							ArticleGroup tg = g, p;
							if ((tg.getParentGroup() != null)) {
								while (tg.getParentGroup() != null) {
									p = tg.getParentGroup();
//									location += ArticleGroupUtil.findFolder(p, staticMod, as, request)  + wel.getLocationSplitStr();
									location = ArticleGroupUtil.findFolder(p, staticMod, as, request)+ wel.getLocationSplitStr() + location;
									tg = p;
								}
							}
							location+=curLocation;
						} else {
							location = "";
						}

						if (checkUserOnSite(wel,t.getArticleGroup()) == 2) {
							if (t.isState()) {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$passStr$$}",
										StringUtil.nullFilter(curSiteStyle
												.getPassedStr()));
							} else {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$passStr$$}",
										StringUtil.nullFilter(curSiteStyle
												.getNoPassedStr()));
							}
							
							if (t.isSoul()) {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$soulStr$$}",
										as.getText("lerx.soulCancelStr"));
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$soul$$}", "false");
							} else {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$soulStr$$}",
										as.getText("lerx.soulStr"));
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$soul$$}", "true");
							}
							
							if (t.isTopOne()) {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$topOneStr$$}",
										as.getText("lerx.topOneCancelStr"));
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$topOne$$}", "false");
							} else {
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$topOneStr$$}",
										as.getText("lerx.topOneStr"));
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$topOne$$}", "true");
							}
							
						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$passStr$$}", "");
						}

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$gid$$}", ""
										+ t.getArticleGroup().getId());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$pensileTitle$$}",
								StringUtil.nullFilter(t.getPensileTitle()));
						artTitle=StringUtil.nullFilter(t.getTitle());
						if (t.isEyeCatching()){
							artTitle=StringUtil.strReplace(eyeCatchingStrTmp,"{$$title$$}",artTitle);
						}
						
						
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$articleTitle$$}",artTitle);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$accessionalTitle$$}",
								StringUtil.nullFilter(t.getAccessionalTitle()));
						if (t.getAddTime() == null) {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$addTime$$}", "");
						} else {
							formatter.parse(t.getAddTime().toString(), pos);
							// formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
							htmlTemplate = StringUtil
									.strReplace(htmlTemplate, "{$$addTime$$}",
											formatter.format(t.getAddTime())
													.toString());
						}
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$synopsis$$}",
								StringUtil.nullFilter(t.getSynopsis()));
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$authorDept$$}",
								StringUtil.nullFilter(t.getAuthorDept()));
						if (t.getAuthorEmail() != null
								&& !t.getAuthorEmail().trim().equals("")) {
							htmlTemplate = StringUtil.strReplace(
									htmlTemplate,
									"{$$authorWithMail$$}",
									"<a href=\"mailto:"
											+ t.getAuthorEmail()
											+ "\">"
											+ StringUtil.nullFilter(t
													.getAuthor()) + "</a>");
						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$authorWithMail$$}",
									StringUtil.nullFilter(t.getAuthor()));
						}
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$author$$}",
								StringUtil.nullFilter(t.getAuthor()));
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$authorEmail$$}",
								StringUtil.nullFilter(t.getAuthorEmail()));
						String rootFolder;
						rootFolder=curSiteStyle.getRootResFolder();
						ReadFileArg rfv=new ReadFileArg();
						rfv.setAs(as);
						rfv.setRequest(request);
						rfv.setRootFolder(rootFolder);
						rfv.setFileName("img.txt");
						rfv.setSubFolder("html");
						
						String txt = FileUtil.readFile(rfv);
						
//						String txt=FileUtil.readTempFile(fra, "html", "err.txt");
//						String txt = FileUtil.readConfigFile(as, request,
//								"img.txt","html");
						
						
						
						//第一种标题图片，只取主图片
						if (t.getMainImg() != null
								&& !t.getMainImg().trim().equals("")) {
							
							txt = StringUtil.strReplace(txt, "{$$url$$}", StringUtil.nullFilter(t
									.getMainImg()));
							if (txt==null){
								txt="";
							}
							htmlTemplate = StringUtil.strReplace(
									htmlTemplate,
									"{$$image$$}",
									txt);
						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$image$$}", "");
						}
						
						//第二种标题图片，缩略图优先
						if (t.getMainImg() != null || t.getThumbnail() != null) {
							String img;
							if (t.getThumbnail() != null
									&& !t.getThumbnail().trim().equals("")){
								img = t.getThumbnail();
								
							}else{
								img=t.getMainImg();
							}
							if (img.trim().equals("")){
								htmlTemplate = StringUtil.strReplace(htmlTemplate,
										"{$$titleImg$$}", "");
							}else{
								txt = StringUtil.strReplace(txt, "{$$url$$}", StringUtil.nullFilter(img));
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate,
										"{$$titleImg$$}",
										txt);
							}
							
						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$titleImg$$}", "");
						}
						

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$mainImage$$}",
								StringUtil.nullFilter(t.getMainImg()));

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$smallImage$$}",
								StringUtil.nullFilter(t.getThumbnail()));
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$imageExplain$$}",
								StringUtil.nullFilter(t.getMainImgExplain()));

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$mentor$$}",
								StringUtil.nullFilter(t.getMentor()));
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$journal$$}",
								StringUtil.nullFilter(t.getJournal()));

						String linkUrl = t.getLinkUrl(), linkUrlEnd = "";
						
						//正文
						
						
						String atBody = t.getBody();
						String bodyNoHtml=StringUtil.nullFilter(atBody);
						bodyNoHtml=StringUtil.htmlFilter(bodyNoHtml);
						/*
						 * 兼容1.0
						 */
						if (!t.isByEditor() && atBody != null
								&& atBody.trim().length() > 4) {
							atBody = StringUtil.strReplace(atBody, "\n",
									"</p><p>");
							atBody = "<p>" + atBody;
							atBody += "</p>";
						}
						
						bodyNoHtml = StringUtil.strReplace(bodyNoHtml, "\n",
						"</p><p>");
						bodyNoHtml = "<p>" + bodyNoHtml;
						bodyNoHtml += "</p>";
						int price = ThreadUtil.price(t);
						boolean showLink = true;
						
						if (linkUrl == null) {
							linkUrl = "";
						}
						if (linkUrl.indexOf(",") == -1 || linkUrl.trim().length()<7) {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$linkUrl$$}",
									StringUtil.nullFilter(t.getLinkUrl()));
							linkUrlEnd = t.getLinkUrl();
						} else {
							String[] linkUrlTmp = linkUrl.trim().split(",");
							if (linkUrlTmp[1].trim().equalsIgnoreCase("h")) {
								showLink = false;
							}
							String mediaPlayOuterLayerCode = curSiteStyle
									.getMediaPlayOuterLayerCodeForArtPage();
							switch (Integer.valueOf(linkUrlTmp[0])) {
							case 2:
								mediaPlayOuterLayerCode = StringUtil
										.strReplace(mediaPlayOuterLayerCode,
												"{$$playerMainBody$$}",
												curSiteStyle
														.getMediaPlayCode2());
								break;
							case 3:
								mediaPlayOuterLayerCode = StringUtil
										.strReplace(mediaPlayOuterLayerCode,
												"{$$playerMainBody$$}",
												curSiteStyle
														.getMediaPlayCode3());
								break;
							default:
								mediaPlayOuterLayerCode = StringUtil
										.strReplace(mediaPlayOuterLayerCode,
												"{$$playerMainBody$$}",
												curSiteStyle
														.getMediaPlayCode1());
								break;
							}
							String bodyTmp=t.getBody();
							
							
							if (price==0){
								if (bodyTmp == null
										|| bodyTmp.trim().equals("") || StringUtil.htmlFilter(bodyTmp).trim().equals("")) {
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate, "{$$body$$}",
											mediaPlayOuterLayerCode);
								} else {
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate, "{$$body$$}",
											StringUtil.nullFilter(atBody));
									
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate, "{$$bodyNoHtml$$}",
											bodyNoHtml);
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate, "{$$mediaPlayCode$$}",
											mediaPlayOuterLayerCode);
								}
								linkUrlEnd = linkUrlTmp[2];
								htmlTemplate = StringUtil.strReplace(htmlTemplate,
										"{$$mediaUrl$$}", linkUrlTmp[2]);
							}else{
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$body$$}",
										curSiteStyle.getAjaxOfArticlePrice());
								htmlTemplate = StringUtil.strReplace(
										htmlTemplate, "{$$bodyNoHtml$$}",
										curSiteStyle.getAjaxOfArticlePrice());
								htmlTemplate = StringUtil.strReplace(htmlTemplate,
										"{$$mediaUrl$$}", "");
							}
							
							
						}
						
						
						if (price==0){
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$body$$}", StringUtil.nullFilter(atBody));
							htmlTemplate = StringUtil.strReplace(
									htmlTemplate, "{$$bodyNoHtml$$}",
									bodyNoHtml);
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$linkTitle$$}",
									StringUtil.nullFilter(t.getLinkTitle()));
							if (showLink && t.getLinkUrl() != null
									&& !t.getLinkUrl().trim().equals("")) {
								if (t.getLinkTitle() != null
										&& !t.getLinkTitle().trim().equals("")) {
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate,
											"{$$link$$}",
											"<a href=\""
													+ StringUtil
															.nullFilter(linkUrlEnd)
													+ "\">"
													+ StringUtil.nullFilter(t
															.getLinkTitle())
													+ "</a>");
								} else {
									htmlTemplate = StringUtil.strReplace(
											htmlTemplate,
											"{$$link$$}",
											"<a href=\""
													+ StringUtil
															.nullFilter(linkUrlEnd)
													+ "\">"
													+ StringUtil
															.nullFilter(linkUrlEnd)
													+ "</a>");
								}
							} else {
								htmlTemplate = StringUtil.strReplace(htmlTemplate,
										"{$$link$$}", "");
							}
						}else{
//							System.out.println("curSiteStyle:"+curSiteStyle.getStyleName());
//							
//							System.out.println("bbbb:"+curSiteStyle.getAjaxOfArticlePrice());
							
							htmlTemplate = StringUtil.strReplace(
									htmlTemplate, "{$$body$$}",
									curSiteStyle.getAjaxOfArticlePrice());
							htmlTemplate = StringUtil.strReplace(
									htmlTemplate, "{$$bodyNoHtml$$}",
									curSiteStyle.getAjaxOfArticlePrice());
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$linkTitle$$}", "");
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$link$$}", "");
							
						}
						
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$member$$}",
								StringUtil.nullFilter(t.getMember()));
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$views$$}", "" + t.getViews());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$lastIP$$}", IpUtil.ipFilter(StringUtil
										.nullFilter(lastViewIP), Integer
										.valueOf(as.getText("lerx.rule.length.ip.filter"))));

						if (t.getPasser() != null
								&& !t.getPasser().trim().equals("")) {

							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$passer$$}", t.getPasser());
						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$passer$$}", "");
						}

						if (lastT != null) {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$lastArticle$$}", ThreadUtil
											.formatLastOrNext(
													lastArticleForwardCode,
													request, as, lastT, 0));

						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$lastArticle$$}", "");

						}
						if (nextT != null) {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$nextArticle$$}", ThreadUtil
											.formatLastOrNext(
													nextArticleForwardCode,
													request, as, nextT, 1));

						} else {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$nextArticle$$}", "");
						}

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$gid$$}", ""
										+ t.getArticleGroup().getId());

						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$location$$}",
								StringUtil.nullFilter(location));
						if (!as.getText("lerx.mode.realtime.byAjax").trim()
								.equalsIgnoreCase("true")) {
							htmlTemplate = StringUtil.strReplace(htmlTemplate,
									"{$$encryptedParmStr$$}", encryptedParmStr);
						}
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$tid$$}", "" + t.getId());
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$aid$$}", "" + t.getId());

						// 附件显示
						List<Attachment> al = attachmentDaoImp.findByHostId(t
								.getId());
						String attachmentLoopStr, attachmentLoopStrAll = "";
						String imgFileEtcStr = as.getText("lerx.imgFileEtcStr");
						int attaMediaType,attaOrderStep=0;
						String attaLineOrderFormatStr,attaTitle,attaUrl;
						for (Attachment atta : al) {
							atta.setTitle(StringUtil.escape(atta.getTitle()));
							attaOrderStep++;//行号增长
							attaMediaType=atta.getMediaType();
							if (attaMediaType==0){
								attaMediaType=t.getDefaultAttaMediaFormat();
							}
							if (attaMediaType == 0) {
								if (FileUtil.imgFileCheck(atta.getUrl(),
										imgFileEtcStr)) {
									attachmentLoopStr = curSiteStyle
											.getAttachmentLineShowForImg();
								} else {
									attachmentLoopStr = curSiteStyle
											.getAttachmentLineShowForDownload();
								}
							} else {
								attachmentLoopStr = curSiteStyle
										.getAttachmentLineShowForPlay();

							}

							attachmentLoopStr = StringUtil.strReplace(
									attachmentLoopStr, "{$$attaId$$}", ""
											+ atta.getId());
							
							if (atta.getTitle() != null
									&& !atta.getTitle().trim().equals("")) {
								attaTitle=atta.getTitle();
							} else {
								attaTitle=as.getText("lerx.msg.noTitle");
							}
							
							attachmentLoopStr = StringUtil.strReplace(
									attachmentLoopStr,
									"{$$attaTitle$$}",attaTitle);// attaTitle为附件原始标题
							
							if (t.getAttaLineOrderFormatStr()!=null && !t.getAttaLineOrderFormatStr().trim().equals("")){
								attaLineOrderFormatStr=t.getAttaLineOrderFormatStr();
							}else{
								attaLineOrderFormatStr="";
							}
							
							if (attaLineOrderFormatStr.equals("")){
								attaLineOrderFormatStr=atta.getUrl();
							}else{
								attaLineOrderFormatStr=StringUtil.strReplace(
										attaLineOrderFormatStr,
										"$title$",attaTitle);
								attaLineOrderFormatStr = StringUtil.strReplace(
										attaLineOrderFormatStr, "$order$",""+attaOrderStep);
								attaLineOrderFormatStr = StringUtil.strReplace(
										attaLineOrderFormatStr, "$sn$",""+attaOrderStep);
								
							}
							
							attachmentLoopStr = StringUtil.strReplace(
									attachmentLoopStr, "{$$title$$}",
									attaLineOrderFormatStr);//title定义为附件序号格式字符串 
							
							attaUrl=atta.getUrl();
							attaUrl=AttaUtil.encoder(externalHostCharsetDaoImp,as, attaUrl,attaMediaType);
							attachmentLoopStr = StringUtil.strReplace(
									attachmentLoopStr, "{$$attaURL$$}",
									atta.getUrl());
							attachmentLoopStr = StringUtil.strReplace(
									attachmentLoopStr, "{$$editCode$$}", "");

							attachmentLoopStrAll += attachmentLoopStr;

						}
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$attachments$$}", attachmentLoopStrAll);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$page$$}", ""+page);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$pageSize$$}", ""+pageSize);

//						htmlTemplate = endPage(htmlTemplate, 0);
						//评论总数
						long commentsPassedCount=commentDaoImp.count(tid, 0);
						long commentsNoPassedCount=commentDaoImp.count(tid, 1);
						long commentsAllCount=commentDaoImp.count(tid, 2);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$commentsPassedCount$$}", ""+commentsPassedCount);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$commentsNoPassedCount$$}", ""+commentsNoPassedCount);
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$commentsAllCount$$}", ""+commentsAllCount);
						//显示分类
						htmlTemplate = StringUtil.strReplace(htmlTemplate,
								"{$$navList$$}", navListStrAll);
						request.setAttribute("lerxCmsBody", htmlTemplate);

					}
				}

			} else {
				htmlTemplate = as.getText("lerx.err.notFound");
				request.setAttribute("lerxCmsBody", htmlTemplate);
			}
			return htmlTemplate;
		}else{
			htmlTemplate=PubUtil.errKey(wel.getFra(), "lerx.err.notFound");
			return htmlTemplate;
//			return as.getText("lerx.err.notFound");
		}
		
	}
	
	/*
	 * 检查门户中用户权限
	 */
	private static int checkUserOnSite(WebElements wel,ArticleGroup ag) {
		boolean pwdMD5ToLowerCase;
		if (wel.getAs().getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		ChkUtilVo cuv=CuvInit(wel);
		cuv.setAg(ag);
		cuv.setUc(wel.getUc());
		cuv.setMode(0);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		return wel.getUserDaoImp().checkUserOnSite(cuv);

	}
	
	private static ChkUtilVo CuvInit(WebElements wel){
		ChkUtilVo cuv=new ChkUtilVo();
		cuv.setInterconnectionDaoImp(wel.getInterconnectionDaoImp());
		
		return cuv;
	}
}
