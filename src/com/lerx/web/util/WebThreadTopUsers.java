package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.Rs;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.UserArtsCount;
import com.lerx.user.vo.UserInf;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.SiteInit;
import com.lerx.web.util.camp.SiteUtil;
import com.lerx.web.vo.WebElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadTopUsers {
	
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		//从wel中取值，以防用过多的get方法
//		IArticleThreadDao articleThreadDaoImp=wel.getArticleThreadDaoImp();
//		IArticleGroupDao articleGroupDaoImp=wel.getArticleGroupDaoImp();
		IUserDao userDaoImp=wel.getUserDaoImp();
		IUserArtsCountDao userArtsCountDaoImp=wel.getUserArtsCountDaoImp();
		ActionSupport as=wel.getAs();
		HttpServletRequest request=wel.getRequest();
		
//		SiteInfo site=wel.getSite();
		SiteStyle curSiteStyle=wel.getCurSiteStyle();
		
		
		int pageSize=wel.getPageSize();
		int page=wel.getPage();
		int gid=wel.getGid();
		int smode=wel.getSmode();
		int umode=wel.getUmode();
		int offset=wel.getOffset();
		//如果有key就表明是按某个时段
		String key=wel.getKey();
		boolean byATime=false;
		int timeKey=TimeUtil.timeNum(key, offset);
		if (timeKey>0 && key!=null && !key.trim().equals("") && key.trim().length()>3){
			byATime=true;
		}
		//取值结束
		
		//下面五行顺序不能错
		wel=SiteUtil.initSiteElement(wel, curSiteStyle.getGenericStyle());
		wel.setTitleFormat(StringUtil.strReplace(wel.getTitleFormat(), "{$$app$$}",
				as.getText("lerx.topUserTitle")));
		wel=SiteUtil.endSiteService(wel);
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		wel=SiteInit.reInit(wel);
//		ResultEl re=wel.getRe();
		
//		String hrefLineFormat=wel.getHrefLineFormat();
		String strFormat=curSiteStyle.getHrefLineFormatWithSnStrOverAll();
		if (byATime){
			if (StringUtil.nullFilter(key).equals("curWeek")){
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$location$$}", as.getText("lerx.topUserTitle") + " " + timeKey + " - "+TimeUtil.weekTimeNum(offset, 7));
			}else{
				htmlTemplate = StringUtil.strReplace(htmlTemplate,
						"{$$location$$}", as.getText("lerx.topUserTitle") + " " + timeKey);
			}
			
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$location$$}", as.getText("lerx.topUserTitle"));
		}
		
		Rs rs;
		String tmp,tmpAll="";
		int step=0;
		if (byATime){
			rs=userArtsCountDaoImp.findTopByUKAndGroup(gid, timeKey, 0, page, pageSize);
//			UserInf ui;
//			User user;
//			UserArtsCount uac;
//			@SuppressWarnings("unchecked")
//			List<UserInf> list=(List<UserInf>) rs.getList();
//			int size=list.size();
//			for (int m = 0; m < size; m++) {
//				ui=list.get(m);
//				user=userDaoImp.findUserById(ui.getId());
//				uac=userArtsCountDaoImp.findByUK(user, timeKey);
//				if (uac!=null){
//					ui.setArticleThreadsCount(uac.getArticleThreadsCount());
//					ui.setArticleThreadsPassed(uac.getArticleThreadsPassed());
//				}else{
//					ui.setArticleThreadsCount(0);
//					ui.setArticleThreadsPassed(0);
//				}
//				
//			}
//			
//			
//			
//			
//			
//			Comparator<UserInf> comparator = new Comparator<UserInf>() {
//				public int compare(UserInf s1, UserInf s2) {
//					// 先排通过数
//					if (s1.getArticleThreadsPassed() != s2.getArticleThreadsPassed()) {
//						return (int) (s2.getArticleThreadsPassed() - s1.getArticleThreadsPassed());
//					} else {
//						// 再排全部
//						if (s1.getArticleThreadsCount() != s2.getArticleThreadsCount()) {
//							return (int) (s2.getArticleThreadsCount() - s1.getArticleThreadsCount());
//						} else {
//							// 最后按id
//							return (int) (s1.getId() - s2.getId());
//						}
//					}
//				}
//			};
//			Collections.sort(list, comparator);
			if (rs.getList().size()>0){
				UserInf u;
				@SuppressWarnings("unchecked")
				List<UserArtsCount> l=(List<UserArtsCount>) rs.getList();
				for (UserArtsCount uac : l) {
					step++;
					tmp=strFormat;
					tmp=StringUtil.strReplace(tmp, "{$$alt$$}", "");
					
					tmp = StringUtil.strReplace(tmp, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmp = StringUtil.strReplace(tmp, "{$$sn0$$}", ""
							+ ((page - 1) * pageSize + (step-1)));
					
//					tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
//					tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
					tmp=StringUtil.strReplace(tmp, "{$$href$$}", request.getContextPath()+"/myArticles.action?uid="+uac.getUser().getId());
					u = userDaoImp.findUserInfById(uac.getUser().getId());
					if (umode==0){
						tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getUserName());
					}else{
						if (u.getTrueName()==null || u.getTrueName().trim().equals("")){
							tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getUserName());
						}else{
							tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getTrueName());
						}
					}
					if (smode==0){
						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+uac.getArticleThreadsPassed()+"	/	"+u.getArticleThreadsPassed());
					}else{
						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+uac.getArticleThreadsCount()+"	/	"+u.getArticleThreadsCount());
					}
//					if (smode==0){
//						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+uac.getArticleThreadsPassed());
//					}else{
//						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+uac.getArticleThreadsCount());
//					}
					
					tmpAll+=tmp;
					
				}
			
			}
		}else{
			rs=userDaoImp.findUserInfByGroupOrderByThread(gid, page, pageSize,smode);
			if (rs.getList().size()>0){

				@SuppressWarnings("unchecked")
				List<UserInf> l=(List<UserInf>) rs.getList();
				for (UserInf u : l) {
					step++;
					tmp=strFormat;
					tmp=StringUtil.strReplace(tmp, "{$$alt$$}", "");
					
					tmp = StringUtil.strReplace(tmp, "{$$sn$$}", ""
							+ ((page - 1) * pageSize + step));
					tmp = StringUtil.strReplace(tmp, "{$$sn0$$}", ""
							+ ((page - 1) * pageSize + (step-1)));
					
//					tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
//					tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
					tmp=StringUtil.strReplace(tmp, "{$$href$$}", request.getContextPath()+"/myArticles.action?uid="+u.getId());
					if (umode==0){
						tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getUserName());
					}else{
						if (u.getTrueName()==null || u.getTrueName().trim().equals("")){
							tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getUserName());
						}else{
							tmp=StringUtil.strReplace(tmp, "{$$title$$}", u.getTrueName());
						}
					}
					if (smode==0){
						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+u.getArticleThreadsPassed());
					}else{
						tmp=StringUtil.strReplace(tmp, "{$$other$$}", ""+u.getArticleThreadsCount());
					}
					
					tmpAll+=tmp;
					
				}
			
			}
		}
		
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$genericDataBody$$}", tmpAll);
		htmlTemplate = StringUtil.strReplace(htmlTemplate, "{$$mainDiv$$}",
				"topUsersMain");
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$pageShowStr$$}", RsInit.rsPageStrShow(rs,
						"?gid="+gid+"&umode="+umode+"&smode="+smode+"&key="+key+"&offset="+offset, PubUtil.pageFormatShowInit(as, wel), false));
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$location$$}", as.getText("lerx.topUserTitle"));
		return htmlTemplate;
	}
}
