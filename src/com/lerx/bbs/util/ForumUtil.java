package com.lerx.bbs.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.bbs.vo.BbsThreadInf;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;

public class ForumUtil {
	public static String formatHref(FormatElements fl, BbsForum forum) {
		String tmp = fl.getLf();
		tmp = StringUtil.strReplace(tmp, "{$$bm$$}", fl.getBmShowFormat());
		tmp = StringUtil.strReplace(tmp, "{$$title$$}", forum.getForumName());
		tmp = StringUtil.strReplace(tmp, "{$$fid$$}", "" + forum.getId());
		
		tmp = StringUtil.strReplace(
				tmp,
				"{$$threadsCount$$}",
				""
						+ fl.getBbsThemeDaoImp().findThreadsCountByForumId(
								forum.getId()));
		tmp = StringUtil.strReplace(
				tmp,
				"{$$themesCount$$}",
				""
						+ fl.getBbsThemeDaoImp().findThemesCountByForumId(
								forum.getId()));
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
		
		Date d=cal.getTime();
					
//		System.out.println("d:"+d.toString());

		tmp = StringUtil.strReplace(
				tmp,
				"{$$theradsCountOfToday$$}",
				""
						+ fl.getBbsThemeDaoImp().findThreadsCountByDate(forum.getId(),d));
		tmp = StringUtil.strReplace(
				tmp,
				"{$$themesCountOfToday$$}",
				""
						+ fl.getBbsThemeDaoImp().findThemesCountByDate(forum.getId(),d));
		
		if (forum.getIcoUrl() == null || forum.getIcoUrl().trim().equals("")) {
			tmp = StringUtil.strReplace(tmp, "{$$forumIcoUrl$$}", "");
			tmp = StringUtil.strReplace(tmp, "{$$forumIcoImage$$}", "");
		} else {
			tmp = StringUtil.strReplace(tmp, "{$$forumIcoUrl$$}",
					forum.getIcoUrl());
			tmp = StringUtil.strReplace(tmp, "{$$forumIcoImage$$}",
					"<img src=\"" + forum.getIcoUrl().trim() + "\">");
		}

		BbsTheme lastTheme = fl.getBbsThemeDaoImp().findLastThemeByForumId(
				forum.getId());
		BbsTheme lastReplyTheme = fl.getBbsThemeDaoImp()
				.findLastReplyThemeByForumId(forum.getId());

		BbsThreadInf lastThemeBfi = fl.getBbsThemeDaoImp()
				.findThreadInfByTheme(lastTheme);
//		BbsThreadInf lastReplyThemeBfi = fl.getBbsThemeDaoImp()
//				.findThreadInfByTheme(lastReplyTheme);

		if (lastThemeBfi.getThemeLeader() == null) {
			tmp = StringUtil.strReplace(tmp, "{$$lastThreadTitle$$}", "");
		} else {
			String url = fl.getRequest().getContextPath() + "/"
					+ fl.getAs().getText("lerx.bbsThreadPageFileName").trim()
					+ "?tid=" + lastTheme.getId();
			// 最新帖
			tmp = StringUtil.strReplace(tmp, "{$$lastThreadTitle$$}",
					lastTheme.getTitle());
			tmp = StringUtil.strReplace(tmp, "{$$lastThreadHref$$}", url);
			tmp = StringUtil.strReplace(tmp, "{$$lastThreadHrefLine$$}",
					"<a href=\"" + url + "\">" + lastTheme.getTitle() + "</a>");

			tmp = StringUtil.strReplace(tmp, "{$$lastThreadMember$$}",
					lastTheme.getUser().getUserName());

			// 最新回复帖

			url = fl.getRequest().getContextPath() + "/"
					+ fl.getAs().getText("lerx.bbsThreadPageFileName").trim()
					+ "?tid=" + lastReplyTheme.getId();
			tmp = StringUtil.strReplace(tmp, "{$$lastReplyThreadTitle$$}",
					lastReplyTheme.getTitle());
			tmp = StringUtil.strReplace(tmp, "{$$lastReplyThreadHref$$}", url);
			tmp = StringUtil.strReplace(tmp, "{$$lastReplyThreadHrefLine$$}",
					"<a href=\"" + url + "\">" + lastReplyTheme.getTitle()
							+ "</a>");
			tmp = StringUtil.strReplace(tmp, "{$$lastThreadReplier$$}",
					lastReplyTheme.getUser().getUserName());
		}

		// fl.getBbsThemeDaoImp().f;

		// tmp = StringUtil.strReplace(tmp, "{$$bm$$}",
		// bmShowFormat);

		return tmp;
	}

	public static boolean ipRangeCheck(String ip, List<BbsForum> list) {
		boolean c = true;
		for (BbsForum bf : list) {
			if (!IpUtil.isInRange(ip, bf.getHostsAllow())) {
				c = false;
				break;
			}
		}
		return c;
	}
	
	
	public static boolean pollFmt(BbsForum f,int pos){
		if (f==null){
			return false;
		}
		String fmtStr=f.getPollFmt();
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
	
	
	public static boolean pollChk(BbsForum f){
		if (f==null){
			return false;
		}
		String fmtStr=f.getPollFmt();
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
