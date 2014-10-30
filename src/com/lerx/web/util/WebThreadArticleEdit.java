package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.article.vo.ArticleThread;
import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.util.AttaUtil;
import com.lerx.attachment.vo.Attachment;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadArticleEdit {
	public static String show(WebElements wel) throws IOException {
		wel.setRefererRec(true);

		IArticleThreadDao articleThreadDaoImp = wel.getArticleThreadDaoImp();
		IArticleGroupDao articleGroupDaoImp = wel.getArticleGroupDaoImp();
		IUserDao userDaoImp = wel.getUserDaoImp();
		IAttachmentDao attachmentDaoImp = wel.getAttachmentDaoImp();
		IExternalHostCharsetDao externalHostCharsetDaoImp = wel
				.getExternalHostCharsetDaoImp();
		ActionSupport as = wel.getAs();
		HttpServletRequest request = wel.getRequest();

		// SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle = wel.getCurSiteStyle();
		// int gid=wel.getGid();
		long tid = wel.getTid();

		// 下面五行顺序不能错
		wel = SiteUtil.initSiteElement(wel, curSiteStyle.getArticleEditStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(),
				"{$$app$$}", as.getText("lerx.articleEditPageTitle")));
		wel = SiteUtil.endSiteService(wel);
		// FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();

		wel = SiteInit.reInit(wel);
		ResultEl re = wel.getRe();

		LoginCheckEl lcel = PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				as.getText("lerx.default.format.datetime").trim());

		UserCookie uc = wel.getUc();
		boolean con = true;
		User u = null;
		if (uc == null) {
			con = false;
		} else {
			u = userDaoImp.findUserById(uc.getUserId());
			if (u == null || !u.isState()) {
				con = false;
			}
		}

		if (uc != null && con) {
			UserGroup ug = u.getUserGroup();
			String agSelectStr = ug.getSiteArticleGroupsSelectCustomStr();
			if (agSelectStr != null && !agSelectStr.trim().equals("")) {
				agSelectStr = StringUtil
						.strReplace(agSelectStr, ",", "$$},{$$");
				agSelectStr = "{$$" + agSelectStr + "$$}";
			} else {
				agSelectStr = null;
			}
			ArticleThread t = articleThreadDaoImp.findById(tid);
			t = ThreadUtil.escape(t);
			String selectOptionFormat, soAllStr = "";
			
			String rootFolder;
			rootFolder=curSiteStyle.getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(as);
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("selectLoop.txt");
			rfv.setSubFolder("html");
			
			selectOptionFormat = FileUtil.readFile(rfv);
			
			// System.out.println("selectOptionFormat:"+selectOptionFormat);
			List<ArticleGroupShowModel> it = articleGroupDaoImp
					.findAllModel(
							as.getText("lerx.treePrefixHead"),
							as.getText("lerx.treePrefixBody"));
			if (!it.isEmpty()) {
				// ArticleGroup agtmp;
				// boolean agJump;
				// for (ArticleGroupShowModel m : it) {
				// agtmp=m.getArticleGroup();
				// if (agtmp.getJumpUrl()==null ||
				// agtmp.getJumpUrl().trim().equals("")){
				// agJump=false;
				// }else{
				// agJump=true;
				// }
				// if (agSelectStr!=null &&
				// as.getText("lerx.userGroupNavsSelectStr").trim().equalsIgnoreCase("true")){
				// if (m.getArticleGroup().isState() && !agJump) {
				// curMask="{$$"+m.getArticleGroup().getId()+"$$}";
				// if (agSelectStr.indexOf(curMask) != -1){
				// soStr = selectOptionFormat;
				// if ((m.getArticleGroup().getId() - t.getArticleGroup()
				// .getId()==0) && !m.getArticleGroup().isAsClass()) {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$selectState$$}", "selected");
				//
				// } else {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$selectState$$}", "");
				// }
				//
				// if (SiteInit.checkUserOnSite(wel,m.getArticleGroup()) == 0
				// || m.getArticleGroup().isAsClass()) {
				// soStr = StringUtil
				// .strReplace(soStr, "{$$disabledStr$$}",
				// " disabled=\"true\" style=\"font-style:oblique;\" ");
				//
				// } else {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$disabledStr$$}", "");
				// }
				// soStr = StringUtil.strReplace(soStr, "{$$value$$}", ""
				// + m.getArticleGroup().getId());
				// soStr = StringUtil.strReplace(soStr, "{$$display$$}",
				// m.getPrefixStr()
				// + m.getArticleGroup().getGroupName());
				// soAllStr += soStr;
				// }
				//
				// }
				// }else{
				// if (m.getArticleGroup().isState() && !agJump) {
				// soStr = selectOptionFormat;
				// /*
				// * tmd,测试这点，1会不等于1。以前好好的。
				// * m.getArticleGroup().getId()现在不等于t.getArticleGroup()
				// .getId()
				// 如果换成int v=m.getArticleGroup().getId();
				// int v2=t.getArticleGroup()
				// .getId()呢？会不会v不等于v2? 没试。但应该不会。
				// */
				// //
				//
				// // if (t.getId()==35626 ){
				// //
				// // System.out.println("-----------------");
				// // System.out.println("cg:"+m.getArticleGroup().getId());
				// // System.out.println("tg:"+t.getArticleGroup().getId());
				// //
				// System.out.println("asclass:"+m.getArticleGroup().isAsClass());
				// // System.out.println("soStr:"+soStr);
				// // System.out.println("相减的值 :"+(m.getArticleGroup().getId() -
				// t.getArticleGroup()
				// // .getId()));
				// // if (m.getArticleGroup().getId() == t.getArticleGroup()
				// // .getId()){
				// // System.out.println("相同");
				// // }else{
				// // System.out.println("不相同");
				// // }
				// //
				// // }
				// if ((m.getArticleGroup().getId() - t.getArticleGroup()
				// .getId()==0) && !m.getArticleGroup().isAsClass()) {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$selectState$$}", "selected");
				// // System.out.println("已改变soStr:"+soStr);
				//
				// } else {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$selectState$$}", "");
				// // System.out.println("不改变soStr:"+soStr);
				// }
				//
				// if (SiteInit.checkUserOnSite(wel,m.getArticleGroup()) == 0
				// || m.getArticleGroup().isAsClass()) {
				// soStr = StringUtil
				// .strReplace(soStr, "{$$disabledStr$$}",
				// " disabled=\"true\" style=\"font-style:oblique;\" ");
				//
				// } else {
				// soStr = StringUtil.strReplace(soStr,
				// "{$$disabledStr$$}", "");
				// }
				// soStr = StringUtil.strReplace(soStr, "{$$value$$}", ""
				// + m.getArticleGroup().getId());
				// soStr = StringUtil.strReplace(soStr, "{$$display$$}",
				// m.getPrefixStr()
				// + m.getArticleGroup().getGroupName());
				// soAllStr += soStr;
				// }
				// }
				//
				//
				// }

				soAllStr = ArticleGroupUtil.createSelectStr(wel, as, it,
						agSelectStr, selectOptionFormat, t.getArticleGroup()
								.getId());

			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$shortTitle$$}",
					StringUtil.nullFilter(t.getShortTitle()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$articleGroupSelect$$}", soAllStr);
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$articleID$$}", "" + t.getId());
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$tid$$}", ""
					+ t.getId());
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$pensileTitle$$}",
					StringUtil.nullFilter(t.getPensileTitle()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$price$$}",
					""+t.getPrice());
			
			int priceCeiling=Integer.valueOf(as.getText("lerx.articlePriceCeiling"));
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$priceCeiling$$}",
					""+priceCeiling);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$articleTitle$$}", StringUtil.nullFilter(t.getTitle()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$accessionalTitle$$}",
					StringUtil.nullFilter(t.getAccessionalTitle()));
			if (t.getAddTime() == null) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$addTime$$}", "");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$addTime$$}", formatter.format(t.getAddTime())
								.toString());
			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$synopsis$$}", StringUtil.nullFilter(t.getSynopsis()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$authorDept$$}",
					StringUtil.nullFilter(t.getAuthorDept()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$author$$}",
					StringUtil.nullFilter(t.getAuthor()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$authorEmail$$}",
					StringUtil.nullFilter(t.getAuthorEmail()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$image$$}",
					StringUtil.nullFilter(t.getMainImg()));
			htmlTemplate = StringUtil
					.strReplace(htmlTemplate, "{$$smallImage$$}",
							StringUtil.nullFilter(t.getThumbnail()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$imageExplain$$}",
					StringUtil.nullFilter(t.getMainImgExplain()));
			if (t.isByEditor()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$body$$}",
						StringUtil.htmlSpecialCharsForKE(t.getBody()));
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$body$$}", StringUtil.nullFilter(t.getBody()));
			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mentor$$}",
					StringUtil.nullFilter(t.getMentor()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$journal$$}",
					StringUtil.nullFilter(t.getJournal()));

			String linkUrl = t.getLinkUrl();
			if (linkUrl == null) {
				linkUrl = "";
			}
			if (linkUrl.indexOf(",") == -1 || linkUrl.trim().length() < 7) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$linkUrl$$}", StringUtil.nullFilter(t.getLinkUrl()));
			} else {
				String[] linkUrlTmp = linkUrl.trim().split(",");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$linkUrl$$}", linkUrlTmp[2]);
				switch (Integer.valueOf(linkUrlTmp[0])) {
				case 1:
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat0Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat1Select$$}", "selected");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat2Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat3Select$$}", "");
					break;
				case 2:
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat0Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat1Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat2Select$$}", "selected");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat3Select$$}", "");
					break;
				case 3:
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat0Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat1Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat2Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat3Select$$}", "selected");
					break;
				default:
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat0Select$$}", "selected");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat1Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat2Select$$}", "");
					htmlTemplate = StringUtil.strReplace(htmlTemplate,
							"{$$mediaFormat3Select$$}", "");
					break;
				}

			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$linkTitle$$}", StringUtil.nullFilter(t.getLinkTitle()));
			htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$member$$}",
					StringUtil.nullFilter(t.getMember()));
			// 醒目标题
			if (t.isEyeCatching()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$eyeCatching$$}", "checked");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$eyeCatching$$}", "");
			}
			// 状态
			if (t.isState()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$statePassed$$}", "checked");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$stateNoPassed$$}", "");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$statePassed$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$stateNoPassed$$}", "checked");
			}
			// 是否跳转
			if (t.isLinkJump()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$linkJumpTurning$$}", "checked");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$linkJumpTurning$$}", "");
			}
			// 公告noticeShowBody
			if (t.isNotice()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$notice$$}", "checked");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$notice$$}", "");
			}
			if (t.isNoticeShowBody()) {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$noticeShowBody$$}", "checked");
			} else {
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$noticeShowBody$$}", "");
			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", as.getText("lerx.articleEditPageTitle"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$loginUser$$}", uc.getUsername());
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$mediaCodeFormat1$$}",
					as.getText("lerx.mediaCodeFormat1"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$mediaCodeFormat2$$}",
					as.getText("lerx.mediaCodeFormat2"));
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$mediaCodeFormat3$$}",
					as.getText("lerx.mediaCodeFormat3"));

			// 附件显示
			if (t.getAttaLineOrderFormatStr()!=null){
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$attaLineOrderFormatStr$$}",
						t.getAttaLineOrderFormatStr());
			}else{
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$attaLineOrderFormatStr$$}",
						"");
			}
			
			switch (t.getDefaultAttaMediaFormat()) {
			case 1:
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat0$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat1$$}", " selected ");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat2$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat3$$}", "");

				break;
			case 2:
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat0$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat1$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat2$$}", " selected ");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat3$$}", "");
				break;
			case 3:
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat0$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat1$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat2$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat3$$}", " selected ");
				break;
			default:
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat0$$}", " selected ");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat1$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat2$$}", "");
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$defaultAttaMediaFormat3$$}", "");
				break;

			}
			List<Attachment> al = attachmentDaoImp.findByHostId(t.getId());
			String attachmentLoopStr, attachmentLoopStrAll = "";
			String imgFileEtcStr = as.getText("lerx.imgFileEtcStr");
			String attaLineOrderFormatStr, attaTitle, attaUrl;
			int attaMediaType, attaOrderStep = 0;
			for (Attachment atta : al) {
				attaOrderStep++;
				attaMediaType = atta.getMediaType();
				if (attaMediaType == 0) {
					attaMediaType = t.getDefaultAttaMediaFormat();
				}
				if (attaMediaType == 0) {
					if (FileUtil.imgFileCheck(atta.getUrl(), imgFileEtcStr)) {
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

				attachmentLoopStr = StringUtil.strReplace(attachmentLoopStr,
						"{$$attaId$$}", "" + atta.getId());
				if (atta.getTitle() != null
						&& !atta.getTitle().trim().equals("")) {
					attachmentLoopStr = StringUtil.strReplace(
							attachmentLoopStr, "{$$attaTitle$$}",
							atta.getTitle());
				} else {
					attachmentLoopStr = StringUtil.strReplace(
							attachmentLoopStr, "{$$attaTitle$$}",
							as.getText("lerx.msg.noTitle"));
				}

				if (atta.getTitle() != null
						&& !atta.getTitle().trim().equals("")) {
					attaTitle = atta.getTitle();
				} else {
					attaTitle = as.getText("lerx.msg.noTitle");
				}

				if (t.getAttaLineOrderFormatStr() != null
						&& !t.getAttaLineOrderFormatStr().trim().equals("")) {
					attaLineOrderFormatStr = t.getAttaLineOrderFormatStr();
				} else {
					attaLineOrderFormatStr = "";
				}

				if (attaLineOrderFormatStr.equals("")) {
					attaLineOrderFormatStr = atta.getUrl();
				} else {
					attaLineOrderFormatStr = StringUtil.strReplace(
							attaLineOrderFormatStr, "$title$", attaTitle);
					attaLineOrderFormatStr = StringUtil.strReplace(
							attaLineOrderFormatStr, "$order$", ""
									+ attaOrderStep);
					attaLineOrderFormatStr = StringUtil.strReplace(
							attaLineOrderFormatStr, "$sn$", "" + attaOrderStep);

				}
				if (attaLineOrderFormatStr!=null){
					attachmentLoopStr = StringUtil.strReplace(attachmentLoopStr,
							"{$$title$$}", attaLineOrderFormatStr);
				}else{
					attachmentLoopStr = StringUtil.strReplace(attachmentLoopStr,
							"{$$title$$}", "");
				}
				
				attaUrl = atta.getUrl();
				attaUrl = AttaUtil.encoder(externalHostCharsetDaoImp, as,
						attaUrl, attaMediaType);
				attachmentLoopStr = StringUtil.strReplace(attachmentLoopStr,
						"{$$attaURL$$}", atta.getUrl());

				attachmentLoopStr = StringUtil.strReplace(
						attachmentLoopStr,
						"{$$editCode$$}",
						"<a href=\"" + request.getContextPath()
								+ "/ajax_attachment_del.action?id="
								+ atta.getId() + "\">"
								+ as.getText("lerx.title.show.delete") + "</a>");

				attachmentLoopStrAll += attachmentLoopStr;

			}
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$attachments$$}", attachmentLoopStrAll);

		} else {
			re.setSiteStyleDaoImp(wel.getSiteStyleDaoImp());
			re.setMes(as.getText("lerx.fail.auth"));
			re.setMod(2);
//			re.setRefererUrl(SiteInit.refCheck(wel, 0));
			re.setRefererUrl(wel.getRequest().getContextPath()+"/login.action");
			htmlTemplate = ResultPage.init(re);
		}

		return htmlTemplate;
	}
}
