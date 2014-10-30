package com.lerx.web.util;

import java.io.IOException;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.util.VoteRecUtil;
import com.lerx.vote.vo.VoteRec;
import com.lerx.web.util.camp.DrawUtil;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.VoteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.lerx.draw.dao.IDrawDao;
import com.lerx.draw.vo.Draw;
import com.lerx.style.draw.vo.DrawStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadDraw {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		IDrawDao drawDaoImp = wel.getDrawDaoImp();
		IVoteRecDao voteRecDaoImp=wel.getVoteRecDaoImp();
		
		int gid=wel.getGid();
		
		wel.setCurSiteStyle(wel.getSiteStyleDaoImp().findDef());
//		site.getStyleIdForSite()
		ActionSupport as=wel.getAs();
		Draw draw=drawDaoImp.findById(gid);
		DrawStyle curDrawStyle=wel.getCurDrawStyle();
//		System.out.println("curDrawStyle.getStyleName():"+curDrawStyle.getStyleName());
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		
		
		wel=DrawUtil.init(wel);
		String tileFormat=wel.getTitleFormat();
		tileFormat=StringUtil.strReplace(tileFormat, "{$$app$$}",
				as.getText("lerx.drawTitle"));
//		System.out.println("tileFormat:"+tileFormat);
//		System.out.println("draw.getTitle():"+draw.getTitle());
		tileFormat=StringUtil.strReplace(tileFormat, "{$$drawTitle$$}",
				draw.getTitle());
		wel.setTitleFormat(tileFormat);
		FormatElements fel=wel.getFel();
		fel.setAs(as);
		fel.setLf(wel.getHrefLineFormat());
		
		
		String result=draw.getResultRecStr();
		String coreCode;
		if (result!=null && !result.trim().equals("")){	//如果已有抽奖结果
			coreCode=curDrawStyle.getResultCode();
//			System.out.println("coreCode:"+coreCode);
			String[] resultArray = result.split(",");
			long recId;
			VoteRec vr;
			String tmp,tmpAll="";
			for (int step = 0; step < resultArray.length; step++) {
				try {
					recId=Long.parseLong(resultArray[step]);
					
					vr=voteRecDaoImp.findById(recId);
					if (vr!=null){
						vr = VoteRecUtil.prosTrim(vr);
						
						tmp=VoteRecUtil.formatHref(fel, vr);

						tmp=StringUtil.strReplace(tmp, "{$$sn$$}",
								""+(step+1));
						tmpAll+=tmp;
					}
					
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

			}
			coreCode=StringUtil.strReplace(coreCode, "{$$results$$}",
					tmpAll);
			
		}else{											//如果还没抽奖
			coreCode=curDrawStyle.getStartCode();
		}
		String html=wel.getHtml();
		html = StringUtil.strReplace(html,
				"{$$drawBody$$}", coreCode);
		wel.setHtml(html);
		
		wel=DrawUtil.endDrawService(wel);
		
		String htmlTemplate=wel.getHtmlTemplate();
		
		
		boolean check = VoteUtil.checkUserOnVote(wel);
		if (check){
			htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$clearCode$$}",
					curDrawStyle.getClearCode());
		}else{
			htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$clearCode$$}",
					"");
		}
		htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$id$$}",
				""+draw.getId());
		htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$gid$$}",
				""+draw.getId());
		htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$drawTitle$$}",
				draw.getTitle());
		htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$app$$}",
				as.getText("lerx.drawTitle"));
//		htmlTemplate=StringUtil.strReplace(htmlTemplate, "{$$siteTitle$$}",
//				tileFormat);
		return htmlTemplate;
	}
}
