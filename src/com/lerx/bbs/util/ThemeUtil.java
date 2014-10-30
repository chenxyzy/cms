package com.lerx.bbs.util;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsInfo;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.bbs.vo.ScoreGroup;
import com.lerx.bbs.vo.ScoreScheme;
import com.lerx.style.bbs.util.BbsStyleUtil;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.sys.util.IcoUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.user.vo.User;
import com.lerx.web.vo.BbsThemeCheckUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ThemeUtil {

	public static String formatHref(FormatElements el, BbsTheme at) {
		BbsStyle bs=el.getBbsStyleDaoImp().findDef();
		ActionSupport as=el.getAs();
		String lf = el.getLf();
		
		if (at.isState()) {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}", el.getAs()
					.getText("lerx.passedTips"));
		} else {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}", el.getAs()
					.getText("lerx.noPassedTips"));
		}

		String title = StringUtil.nullFilter(at.getTitle());
		String synopsis = at.getBody();
		String ellipsisChar = SysUtil.ellipsis(el.getAs());
		if (ellipsisChar == null || ellipsisChar.trim().equals("")
				|| ellipsisChar.trim().equals("lerx.ellipsisChar")) {
			ellipsisChar = "...";
		} else {
			ellipsisChar = ellipsisChar.trim();
		}
		int maxLengthOfSynopsis = Integer.valueOf(el.getAs().getText(
				"lerx.threadSynopsisLength"));
		synopsis = StringUtil.nullFilter(synopsis);
		synopsis = StringUtil.htmlFilter(synopsis);

		if (maxLengthOfSynopsis > 0 && synopsis.length() > maxLengthOfSynopsis) {
			synopsis = synopsis.substring(0, maxLengthOfSynopsis)
					+ ellipsisChar;
		}

		if (title.length() > el.getTitleLength() && el.getTitleLength() > 0) {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", " Title=\"" + title
					+ "\" ");
			title = title.substring(0, el.getTitleLength());

			title = title + ellipsisChar;
		} else {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", "");
		}

		java.text.SimpleDateFormat formatter;
		if (el.getDateFormatOnLine() != null
				&& !el.getDateFormatOnLine().trim().equals("")) {
			formatter = new java.text.SimpleDateFormat(el.getDateFormatOnLine());
		} else {
			formatter = new java.text.SimpleDateFormat(el.getAs().getText(
					"lerx.default.format.datetime"));
		}

		//发贴距现在时间
		if (at.getAddTime() == null) {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}", "");
			lf = StringUtil.timeCustomReplace(lf, "addTime", null);
			lf = StringUtil.strReplace(lf, "{$$addTimesAgo$$}", "");
		} else {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}",
					"" + formatter.format(at.getAddTime()));
			lf = StringUtil.timeCustomReplace(lf, "addTime", at.getAddTime());
			String addTimesAgoStr = TimeUtil.timeSpan(el.getAs(), at
					.getAddTime().getTime());
			lf = StringUtil.strReplace(lf, "{$$addTimesAgo$$}", addTimesAgoStr);
		}
		
		//最后编辑者

		if (at.getLastEditTime() == null) {
			lf = StringUtil.strReplace(lf, "{$$lastEdit$$}", "");
		} else {
			String lastEditorFormat=el.getBbsStyleDaoImp().findDef().getLastEditorShowFormat();
						
			
			lastEditorFormat= StringUtil.strReplace(
					lastEditorFormat, "{$$username$$}", el.getUserDaoImp().findUserById(at.getLastEditUser().getId())
							.getUserName());
			
			
			formatter = new java.text.SimpleDateFormat(el.getAs().getText(
			"lerx.bbsLastEditTimeFormat"));
			
			
			lastEditorFormat = StringUtil.strReplace(
					lastEditorFormat, "{$$dateTime$$}",
					formatter.format(at.getLastEditTime()));
			lf = StringUtil.strReplace(lf, "{$$lastEdit$$}",
					lastEditorFormat);
		}

		if (at.getQuote() == null || at.getQuote().trim().equals("")) {
			lf = StringUtil.strReplace(lf, "{$$quote$$}", "");
		} else {
			lf = StringUtil.strReplace(lf, "{$$quote$$}", at.getQuote());
		}

		if (el.isIncludeEditArea() && el.getEditAreaCode() != null
				&& !el.getEditAreaCode().trim().equals("")) {
			lf = StringUtil.strReplace(lf, "{$$editCode$$}",
					el.getEditAreaCode());
			lf = StringUtil.strReplace(lf, "{$$editFileURL$$}",
					"post.action?tid=" + at.getId());
			lf = StringUtil.strReplace(lf, "{$$deleteFileURL$$}",
					"theme_del.action?tid=" + at.getId());
		} else {
			lf = StringUtil.strReplace(lf, "{$$editCode$$}", "");
		}

		if (at.isState()) {
			lf = StringUtil.strReplace(lf, "{$$state$$}", " checked ");
		} else {
			lf = StringUtil.strReplace(lf, "{$$state$$}", " ");
		}
		if (at.getRootTheme()==null){
			lf = StringUtil.strReplace(lf, "{$$tid$$}", "" + at.getId());
		}else{
			lf = StringUtil.strReplace(lf, "{$$tid$$}", "" + at.getRootTheme().getId());
		}
		lf = StringUtil.strReplace(lf, "{$$otid$$}", "" + at.getId());
		lf = StringUtil.strReplace(lf, "{$$title$$}", title);
		User user=el.getUserDaoImp()
		.findUserById(at.getUser().getId());
		
		lf = StringUtil.strReplace(lf, "{$$uid$$}", ""+user.getId());
		lf = StringUtil.strReplace(lf, "{$$member$$}", user.getUserName());
		lf = StringUtil.strReplace(lf, "{$$username$$}", user.getUserName());
		if (user.getAvatarFile() == null || user.getAvatarFile().trim().equals("")) {
			lf = StringUtil.strReplace(lf, "{$$avatarFile$$}", el.getAs().getText("lerx.noAvatarSmall"));
		} else {
			lf = StringUtil.strReplace(lf, "{$$avatarFile$$}", user.getAvatarFile());
		}
		
		lf = StringUtil.strReplace(lf, "{$$email$$}", user.getEmail());
		lf = StringUtil.strReplace(lf, "{$$remName$$}", user.getRemName());
		lf = StringUtil.strReplace(lf, "{$$allScore$$}", ""+user.getAllScore());
		lf = StringUtil.strReplace(lf, "{$$bbsScore$$}", ""+user.getBbsScore());
		// 论坛用户组
		if (el.getScoreGroupDaoImp() != null
				&& el.getScoreSchemeDaoImp() != null) {
			ScoreScheme sc = el.getScoreSchemeDaoImp().findCurScoreScheme();
			ScoreGroup sg = null;
			if (sc != null) {
				sg = el.getScoreGroupDaoImp().findScoreGroupByIdAndValue(
						sc.getId(), user.getBbsScore());
			}
			if (sg != null) {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}",
						sg.getGroupName());
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}",
						"" + user.getBbsScore());
			} else {
				lf = StringUtil.strReplace(lf, "{$$scoreGroup$$}", el.getAs()
						.getText("lerx.err.unknown"));
				lf = StringUtil.strReplace(lf, "{$$scoreValue$$}", el.getAs()
						.getText("lerx.err.unknown"));
			}

		}
		
		
		lf = StringUtil.strReplace(lf, "{$$views$$}", "" + at.getViews());
		lf = StringUtil.strReplace(lf, "{$$synopsis$$}",
				StringUtil.nullFilter(synopsis));
		String url = el.getRequest().getContextPath() + "/"
				+ el.getAs().getText("lerx.bbsThreadPageFileName").trim()
				+ "?tid=" + at.getId();
		lf = StringUtil.strReplace(lf, "{$$href$$}", url);
		lf = StringUtil.strReplace(lf, "{$$hrefLine$$}", "<a href=\"" + url
				+ "\">" + at.getTitle() + "</a>");
		boolean c=true;
		boolean isAuthor;
		if (el.getUc()==null || el.getUc().getUserId()<1){
			isAuthor=false;
		}else{
			if (at.getUser().getId() - el.getUc().getUserId() == 0){
				isAuthor=true;
			}else{
				isAuthor=false;
			}
		}
		if (!isAuthor && at.getRootTheme()==null && at.isSeeAfterReply()){
			if (el.getUc()!=null &&
					el.getBbsThemeDaoImp().findReplyer(at.getId(), el.getUc().getUserId())){
				c=true;
			}else{
				c=false;
			}
		}
		
		if (c){
			if (at.isShield()){
				lf = StringUtil.strReplace(lf, "{$$body$$}", el.getShieldedShowCode());
			}else{
				lf = StringUtil.strReplace(lf, "{$$body$$}", "" + at.getBody());
			}
		}else{
			lf = StringUtil.strReplace(lf, "{$$body$$}", el.getAfterReplyShowCode());
		}
		
//		BbsStyle bs=el.getBbsStyleDaoImp().findDef();
		
		
		
		
		
		
		lf = StringUtil.strReplace(lf, "class@lerx", "class");
//		lf = StringUtil.strReplace(lf, "[[/quote]]", "</div>");
		BbsTheme lt = el.getBbsThemeDaoImp().findLastThemeByRoot(at);
		String lastReplier, lastReplieTime, lastReplierTimesAgoStr;
		long lastReplierUid, lastReplieTimeNum;

		if (lt == null) {
			lastReplier = el.getUserDaoImp().findUserById(at.getUser().getId())
					.getUserName();
			lastReplierUid = at.getUser().getId();
			lastReplieTime = "" + at.getAddTime();
			lastReplieTimeNum = at.getAddTime().getTime();
			lastReplierTimesAgoStr = TimeUtil.timeSpan(el.getAs(), at
					.getAddTime().getTime());
		} else {
			lastReplier = el.getUserDaoImp().findUserById(lt.getUser().getId())
					.getUserName();
			lastReplieTime = "" + lt.getAddTime();
			lastReplieTimeNum = lt.getAddTime().getTime();
			lastReplierUid = lt.getUser().getId();
			lastReplierTimesAgoStr = TimeUtil.timeSpan(el.getAs(), lt
					.getAddTime().getTime());
		}
		lf = StringUtil.strReplace(lf, "{$$lastReplier$$}", lastReplier);
		lf = StringUtil.strReplace(lf, "{$$lastReplierUid$$}", ""
				+ lastReplierUid);
		lf = StringUtil.strReplace(lf, "{$$lastReplieTime$$}", lastReplieTime);
		lf = StringUtil.strReplace(lf, "{$$lastReplierTimesAgo$$}",
				lastReplierTimesAgoStr);
		long childThemesCount = el.getBbsThemeDaoImp()
				.findThemesCountByRoot(at);
		lf = StringUtil.strReplace(lf, "{$$replies$$}", "" + childThemesCount);
		String icoUrl, icoAlt;
		String icoStaFolder=BbsStyleUtil.icoFolder(bs, 2);
		switch (at.getTopMod()) {
		case 3:
			icoUrl = icoStaFolder+IcoUtil.read(as, "stickedOnGlobal", 0);
			icoAlt = el.getAs().getText(
					"lerx.bbsThemeStateTxtOfStickedOnGlobal");
			break;
		case 2:
			icoUrl = icoStaFolder+IcoUtil.read(as, "stickedOnClass", 0);
			icoAlt = el.getAs()
					.getText("lerx.bbsThemeStateTxtOfStickedOnClass");
			break;
		case 1:
			icoUrl = icoStaFolder+IcoUtil.read(as, "stickedOnForum", 0);
			icoAlt = el.getAs()
					.getText("lerx.bbsThemeStateTxtOfStickedOnForum");
			break;
		default:
			long timeSpan,
			nowTimeNum = System.currentTimeMillis();
			timeSpan = nowTimeNum - lastReplieTimeNum;
			// long ltmp=Long.parseLong("3600000");
			// int hoursSpan=(int)(timeSpan/ltmp);
			int hotThreadAtTime = Integer.valueOf(el.getAs().getText(
					"lerx.bbsNewThreadAtTime"));// 取新帖判定时间（小时，距当前时间）

			int hotThreadAtPercent = Integer.valueOf(el.getAs().getText(
					"lerx.bbsHotThreadAtPercent"));// 取热帖判定百分比
			int hotMinnum = Integer.valueOf(el.getAs().getText(
			"lerx.bbs.thread.hot.minnum"));// 取热帖判定百分比
			
			if (at.isTop()){
				icoUrl = icoStaFolder+IcoUtil.read(as, "topsta", 0);
				icoAlt = el.getAs().getText("lerx.bbs.act.msg.top");
			}else{
				if (at.getViews() > 0 && childThemesCount>hotMinnum
						&& (childThemesCount / at.getViews()) * 100 >= hotThreadAtPercent) {
					icoUrl = icoStaFolder+IcoUtil.read(as, "hot", 0);
					icoAlt = el.getAs().getText("lerx.bbsThemeStateTxtOfHot");

				} else if (timeSpan < (hotThreadAtTime * 3600000)) {
					icoUrl = icoStaFolder+IcoUtil.read(as, "new", 0);
//					System.out.println("icoUrl:"+icoUrl);
					icoAlt = el.getAs().getText("lerx.bbsThemeStateTxtOfNew");
				} else {
					icoUrl = icoStaFolder+IcoUtil.read(as, "genera", 0);
					icoAlt = el.getAs().getText("lerx.bbsThemeStateTxtOfGeneral");
				}
			}
			
			if (icoUrl==null || icoUrl.trim().equals("")){
				icoUrl = icoStaFolder+IcoUtil.read(as, "genera", 0);
				
			}
			
			
			break;
		}
		
		
		
		
//		String icoStaFolder=icoFolder+"sta/";
		
		
		lf = StringUtil.strReplace(lf, "{$$icoUrl$$}", icoUrl);
		lf = StringUtil.strReplace(lf, "{$$icoAlt$$}", icoAlt);
		return lf;
	}


	public static String formatLastOrNext(String lf,
			HttpServletRequest request, ActionSupport as, BbsTheme at, int mod) {
		String pre, url;
		String title = at.getTitle();

		if (mod == 0) {
			pre = as.getText("lerx.lastArticlePreStr");
		} else {
			pre = as.getText("lerx.nextArticlePreStr");
		}
		url = request.getContextPath() + "/"
				+ as.getText("lerx.bbsThreadPageFileName").trim() + "?tid="
				+ at.getId();

		if (lf == null || lf.trim().equals("")) {
			lf = pre + " <a href=\"" + url + "\">" + at.getTitle() + "</a>";
		} else {
			lf = StringUtil.strReplace(lf, "{$$pre$$}", pre);
			lf = StringUtil.strReplace(lf, "{$$id$$}", "" + at.getId());
			lf = StringUtil.strReplace(lf, "{$$title$$}", title);
			lf = StringUtil.strReplace(lf, "{$$href$$}", url);
			lf = StringUtil.strReplace(lf, "{$$hrefLine$$}", "<a href=\"" + url
					+ "\">" + at.getTitle() + "</a>");

		}
		return lf;
	}

	@SuppressWarnings("unchecked")
	public static String customFormat(FormatElements el, int fmod, int gid,
			BbsStyle curStyle, int page, int pageSize) {

		String formatStr;
		switch (fmod) {
		case 2:
			formatStr = curStyle.getCustomFormatCode2();
			break;
		case 3:
			formatStr = curStyle.getCustomFormatCode3();
			break;
		case 4:
			formatStr = curStyle.getCustomFormatCode4();
			break;
		case 5:
			formatStr = curStyle.getCustomFormatCode5();
			break;
		case 6:
			formatStr = curStyle.getCustomFormatCode6();
			break;
		case 7:
			formatStr = curStyle.getCustomFormatCode7();
			break;
		case 8:
			formatStr = curStyle.getCustomFormatCode8();
			break;
		default:
			formatStr = curStyle.getCustomFormatCode1();
			break;
		}
		int sortMod = Integer.valueOf(el.getAs().getText(
				"lerx.bbsThreadSortMod"));
		boolean sortM;
		if (sortMod == 0) {
			sortM = true;
		} else {
			sortM = false;
		}
		Rs rs = el.getBbsThemeDaoImp().findThemesByForumId(gid, page, pageSize,
				sortM);
		List<BbsTheme> ls = (List<BbsTheme>) rs.getList();
		String f = formatStr;
		String tmp = "", tmpAll = "";
		int step = 0;
		for (BbsTheme at : ls) {
			tmp = f;
			step++;
			tmp = formatHref(el, at);
			// tmp = formatHref(tmp,el.getBbsThemeDaoImp(),el.getRequest(),
			// el.getAs(), at, false, null, null,0);
			tmp = StringUtil.strReplace(tmp, "{$$sn$$}", "" + step);
			tmp = StringUtil.strReplace(tmp, "{$$sn0$$}", "" + (step - 1));
			tmpAll += tmp;
		}
		tmpAll = StringUtil.strReplace(tmpAll, "$$}{$$", ",");
		tmpAll = StringUtil.strReplace(tmpAll, "$$}", "");
		tmpAll = StringUtil.strReplace(tmpAll, "{$$", "");
		return tmpAll;

	}

	public static boolean lockCheck(BbsInfo bi, BbsTheme bt) {
		long addTimes, now;
		long lockTimes;
		lockTimes = bi.getTimesOfLockTheme() * 60 * 60 * 1000; // 锁定时间，小时为单位
		if (lockTimes > 0) {
			addTimes = bt.getAddTime().getTime();
			now = System.currentTimeMillis();

			if ((now - addTimes) > lockTimes) {
				return true;
			}

		}

		return false;
	}
	
	public static boolean powerCheck(BbsThemeCheckUtil btcu){
		boolean powered=false;
		
		BbsForum bf=btcu.getBbsForumDaoImp().findBbsForumById(
				btcu.getTheme().getForum().getId());
		User user = btcu.getUser();
		if (user != null && btcu.getBbsBMDaoImp().checkPower(user, bf)) {
			powered = true;

		}
		
		return powered;
	}

	public static String codeRepByUser(BbsThemeCheckUtil btcu) {
		boolean powered = false;
		User user = btcu.getUser();
		BbsTheme theme=btcu.getTheme();
		
		BbsForum bf = btcu.getBbsForumDaoImp().findBbsForumById(
				btcu.getTheme().getForum().getId());

		if (user != null && btcu.getBbsBMDaoImp().checkPower(user, bf)) {
			powered = true;

		}
		String str=btcu.getStr();
		String icoFolder=btcu.getIcoFolder();
		String icoActFolder=icoFolder+"act/";
		if (powered) {
			str = StringUtil.strReplace(str,
					"{$$functionAreaCode$$}", btcu.getFunctionAreaCode());
			
			
			if (!ThemeUtil.lockCheck(btcu.getBi(), theme)) { // 如果超过锁定期
				str = StringUtil.strReplace(str,
						"{$$editAreaCode$$}", btcu.getEditAreaCode());
			} else {
				str = StringUtil.strReplace(str,
						"{$$editAreaCode$$}", "");
			}
			
			//修改图标
			str = StringUtil.strReplace(str, "{$$icoEdit$$}", icoActFolder+IcoUtil.read(btcu.getAs(), "edit", 0));
			
			//删除图标
			
			str = StringUtil.strReplace(str, "{$$icoDel$$}", icoActFolder+IcoUtil.read(btcu.getAs(), "del", 0));
			
			//屏蔽
			str = StringUtil.strReplace(str, "{$$shieldAct$$}", ""+!theme.isShield());
			if (theme.isShield()){
				str = StringUtil.strReplace(str, "{$$shieldActTxt$$}", btcu.getAs().getText("lerx.bbs.unshieldAct"));
				
				str = StringUtil.strReplace(str, "{$$icoShield$$}", icoActFolder+IcoUtil.read(btcu.getAs(), "shield", 2));
				
			}else{
				str = StringUtil.strReplace(str, "{$$shieldActTxt$$}", btcu.getAs().getText("lerx.bbs.shieldAct"));
				str = StringUtil.strReplace(str, "{$$icoShield$$}", icoActFolder+IcoUtil.read(btcu.getAs(), "shield", 1));
			}
			
			

		} else {
			str = StringUtil.strReplace(str,
					"{$$functionAreaCode$$}", "");
			// 如果是本帖的主人
//			System.out.println("user1:"+user.getId());
//			System.out.println("user2:"+theme.getUser().getId());
//			System.out.println("lock:"+ThemeUtil.lockCheck(btcu.getBi(), theme));
//			
//			if (theme.getUser().getId() == user.getId()){
//				System.out.println("1是同一个人");
//			}else{
//				System.out.println("0不是同一个人");
//			}
//			
//			if ( !ThemeUtil.lockCheck(btcu.getBi(), theme)){
//				System.out.println("不锁定");
//			}else{
//				System.out.println("锁定");
//			}
			
			
			
			if (user != null && (theme.getUser().getId() - user.getId()==0)
					&& !ThemeUtil.lockCheck(btcu.getBi(), theme)) {
				if (btcu.getEditAreaCode()==null){
//					System.out.println("没有，空的");
				}
//				System.out.println("code:"+btcu.getEditAreaCode());
				
				str = StringUtil.strReplace(str,
						"{$$editAreaCode$$}", btcu.getEditAreaCode());
				//删除图标
				
				str = StringUtil.strReplace(str, "{$$icoEdit$$}", icoActFolder+IcoUtil.read(btcu.getAs(), "edit", 0));
				
			} else {
//				if (btcu.getEditAreaCode()==null){
//					System.out.println("222-----没有，空的");
//				}
				str = StringUtil.strReplace(str,
						"{$$editAreaCode$$}", "");
			}

			

		}
		
		if (user != null) {
			String quoteButtomCodeTmp = btcu.getQuoteButtomCode();
			quoteButtomCodeTmp = StringUtil.strReplace(quoteButtomCodeTmp,
					"{$$tid$$}", "" + theme.getId());
			str = StringUtil.strReplace(str,
					"{$$quoteButtom$$}", quoteButtomCodeTmp);
		} else {
			str = StringUtil.strReplace(str,
					"{$$quoteButtom$$}", "");
		}

		return str;
	}

}
