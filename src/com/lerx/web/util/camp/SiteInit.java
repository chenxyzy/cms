package com.lerx.web.util.camp;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.draw.vo.Draw;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.draw.vo.DrawStyle;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.site.util.SiteStyleUtil;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.vote.vo.VoteSubject;
import com.lerx.web.vo.ConEl;
import com.lerx.web.vo.ResultEl;
import com.lerx.web.vo.WebElements;

public class SiteInit {

	public static WebElements styleInit(WebElements wel, int mod) {
		
		SiteStyle curSiteStyle = null;
		BbsStyle curBbsStyle = null;
		QaStyle curQaStyle = null;
		VoteStyle curVoteStyle = null;
		DrawStyle curDrawStyle = null;
		int gid;
		int sta = wel.getStation();
		int local = WebStation.check(sta);

		switch (local) {
		case 2: // bbs
			curBbsStyle = wel.getBbsStyleDaoImp().findDef();
			break;
		case 3: // qa
			curQaStyle = wel.getQaStyleDaoImp().findDefault();
			break;
		case 4: // vote
			gid = wel.getGid();
			VoteSubject vs = wel.getVoteSubjectDaoImp().findById(gid);
			if (vs != null) {
				curVoteStyle = vs.getStyle();
			}
			if (curVoteStyle == null) {
				curVoteStyle = wel.getVoteStyleDaoImp().findDefault();
			}
			break;
		case 5:
			gid = wel.getGid();
			Draw d = wel.getDrawDaoImp().findById(gid);
			if (d != null) {
				curDrawStyle = d.getDs();
			}
			if (curDrawStyle == null) {
				curDrawStyle = wel.getDrawStyleDaoImp().findDefault();
			}
			break;
		default: // 网站
			/*
			 * 在资源文件中，用 lerx.portalStyleId 强行指定一个模板id，
			 * 则当前web使用此模板，目的有些网站共享数据。
			 * 比如：门户与手机版可能是同一数据源
			 */
			String resStyleIdStr = wel.getAs().getText("lerx.portalStyleId");
			if (resStyleIdStr.trim().equals("0")
					|| resStyleIdStr.trim().equals("null")
					|| resStyleIdStr.trim().equals("lerx.portalStyleId")) {
				if (sta == 1) {
					gid = wel.getGid();

					if (gid > 0) {
						ArticleGroup g = wel.getArticleGroupDaoImp()
								.findById(wel.getGid());
						SiteStyle stmp=SiteStyleUtil.findForceStyle(g, wel.getArticleGroupDaoImp());
						if (g == null || stmp == null) {
							curSiteStyle = wel.getSiteStyleDaoImp().findDef();
						} else {
							curSiteStyle = wel.getSiteStyleDaoImp()
									.findStyleById(stmp.getId());
						}
					} else {
						curSiteStyle = wel.getSiteStyleDaoImp().findDef();
					}

				} else if (sta == 2) {
					long tid = wel.getTid();
					if (tid > 0) {
						ArticleThread t = wel.getArticleThreadDaoImp()
								.findById(tid);
						if (t != null) {
							ArticleGroup g = t.getArticleGroup();
							SiteStyle stmp=SiteStyleUtil.findForceStyle(g, wel.getArticleGroupDaoImp());
							if (g == null || stmp == null) {
								curSiteStyle = wel.getSiteStyleDaoImp().findDef();
							} else {
								curSiteStyle = wel.getSiteStyleDaoImp()
										.findStyleById(stmp.getId());
							}
						} else {
							curSiteStyle = wel.getSiteStyleDaoImp().findDef();
						}

					} else {
						curSiteStyle = wel.getSiteStyleDaoImp().findDef();
					}
				} else {
					curSiteStyle = wel.getSiteStyleDaoImp().findDef();
				}
			} else {
				int resStyleId = Integer.valueOf(resStyleIdStr);
				curSiteStyle = wel.getSiteStyleDaoImp().findStyleById(
						resStyleId);
				if (curSiteStyle==null){
					curSiteStyle = wel.getSiteStyleDaoImp().findDef();
				}
			}

			break;
		}
		wel.setCurSiteStyle(curSiteStyle);
		wel.setCurBbsStyle(curBbsStyle);
		wel.setCurQaStyle(curQaStyle);
		wel.setCurVoteStyle(curVoteStyle);
		wel.setCurDrawStyle(curDrawStyle);
		return wel;
	}

	public static ConEl check(WebElements wel) {

		ConEl ce = new ConEl();
		if (!wel.getAs().getText("lerx.appShow").trim().equalsIgnoreCase("true")) {
			ce.setCon(false);
			ce.setMes(wel.getAs().getText("lers.msg.app.close"));
			return ce;
		}

		// 检查网站状态
		if (wel.getSite().isState()) {
			ce.setCon(true);
		} else {
			ce.setCon(false);
			ce.setMes(wel.getSite().getCloseAnnounce());
		}
		if (ce.isCon()) {
			int sta = WebStation.check(wel.getStation());
			// System.out.println("sta:"+sta);
			switch (sta) {
			case 2:
				if (wel.getCurBbsStyle() == null) {
					ce.setCon(false);
					ce.setMes(wel.getAs().getText("lerx.err.style.notFount"));
				}
				break;
			case 3:
				if (wel.getCurQaStyle() == null) {
					ce.setCon(false);
					ce.setMes(wel.getAs().getText("lerx.err.style.notFount"));
				}
				break;
			case 4:
				if (wel.getCurVoteStyle() == null) {
					ce.setCon(false);
					ce.setMes(wel.getAs().getText("lerx.err.style.notFount"));
				}
				break;
			case 5:
				if (wel.getCurDrawStyle() == null) {
					ce.setCon(false);
					ce.setMes(wel.getAs().getText("lerx.err.style.notFount"));
				}
				break;
			default:
				if (wel.getCurSiteStyle() == null) {
					ce.setCon(false);
					ce.setMes(wel.getAs().getText("lerx.err.style.notFount"));
				}
				break;
			}

		}

		return ce;
	}


	

	

	
	public static WebElements reInit(WebElements wel) {
		ResultEl re = wel.getRe();
		int mod = 0, sta;
		sta = wel.getStation();
		mod = WebStation.check(sta);
		switch (mod) {
		case 1:
			re.setCodeStr(wel.getCurSiteStyle().getResultPageCode());
			break;
		case 2:
			re.setCodeStr(wel.getCurBbsStyle().getResultPageCode());
			break;

		}
		wel.setRe(re);
		return wel;
	}
	
}
