package com.lerx.web.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.util.VoteItemUtil;
import com.lerx.vote.util.VoteSubjectUtil;
import com.lerx.vote.vo.VoteItem;
import com.lerx.vote.vo.VoteSubject;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.VoteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.style.vote.vo.VoteStyleSubElementInCommon;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadVoteRank {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		IVoteSubjectDao voteSubjectDaoImp = wel.getVoteSubjectDaoImp();
//		IVoteStyleDao voteStyleDaoImp = wel.getVoteStyleDaoImp();
		IVoteItemDao voteItemDaoImp = wel.getVoteItemDaoImp();
		IVoteRecDao voteRecDaoImp=wel.getVoteRecDaoImp();
		int gid=wel.getGid();
		int mod=wel.getMod();
		int de=wel.getDe();
		ActionSupport as=wel.getAs();
		VoteSubject vs=voteSubjectDaoImp.findById(gid);
		VoteStyle curVoteStyle = wel.getCurVoteStyle();
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
//		int subId=wel.getGid();
		//下面顺序不能错
		wel=VoteUtil.init(wel,curVoteStyle.getResultStyle());
		String tileFormat=wel.getTitleFormat();
		tileFormat=StringUtil.strReplace(tileFormat, "{$$app$$}",
				as.getText("lerx.voteResultTitle"));
		tileFormat=StringUtil.strReplace(tileFormat, "{$$voteSubject$$}",
				vs.getTitle());
		wel.setTitleFormat(tileFormat);
		
	
		wel=VoteUtil.endVoteService(wel);
		FormatElements fel=wel.getFel();
		fel.setLf(wel.getHrefLineFormat());
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		htmlTemplate=VoteSubjectUtil.init(htmlTemplate, as, vs);		
		//统计
		Long allSign,allSingPassed,allSingNoPassed;
		allSign=voteItemDaoImp.count(vs, 0);
		allSingPassed=voteItemDaoImp.count(vs, 1);
		allSingNoPassed=voteItemDaoImp.count(vs, 2);
		
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSign$$}", allSign.toString());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSingPassed$$}", allSingPassed.toString());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSingNoPassed$$}", allSingNoPassed.toString());
		
		//查询当前投票数
		Long count=voteRecDaoImp.findRecCountBySub(vs);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$currentRec$$}", ""+count);
		
		//增加区域item显示
		if (mod>0 && mod<9){
			mod--;
		}else{
			mod = 9;
		}
		List<Long> lv=voteItemDaoImp.queryAll(mod, vs.getId(), 0);
		VoteItem vi;
		String lf,tmpA="";
		VoteStyleSubElementInCommon el = wel.getVel();
		Long maxRecNum=voteItemDaoImp.findMaxRecNum(vs.getId());
		int maxBarValue=Integer.valueOf(curVoteStyle.getBarMaxValue());
		if (maxBarValue==0){
			maxBarValue=500;
		}
		if (maxRecNum>0){
			
		}
		int step=0;
		float valu;
		String decimalStr;
		if (de>0){
			decimalStr=".";
			decimalStr+=StringUtil.countStr(de, "0");
			
		}else{
			decimalStr=".0000";
		}
		DecimalFormat df = new DecimalFormat(decimalStr);
		for (Long vid : lv) {
			step++;
			
			fel.setLf(el.getItemsLoopFormat());
			
			vi=voteItemDaoImp.findById(vid);
			lf = VoteItemUtil.formatHref(fel, vi,null);
			
			valu=(float)(((float)vi.getRecNum()/(float)maxRecNum) * vs.getPercent());
//			df.format(valu);
			
			if (maxRecNum>0){
				lf = StringUtil.strReplace(lf,
						"{$$barWidth$$}", ""+(vi.getRecNum()*maxBarValue)/maxRecNum);
				lf = StringUtil.strReplace(lf,
						"{$$barHeight$$}", ""+(vi.getRecNum()*maxBarValue)/maxRecNum);
			}else{
				lf = StringUtil.strReplace(lf,
						"{$$barWidth$$}", "1");
				lf = StringUtil.strReplace(lf,
						"{$$barHeight$$}", "1");
			}
			lf = StringUtil.strReplace(lf,
					"{$$value$$}", ""+df.format(valu));
			lf = StringUtil.strReplace(lf,
					"{$$sn$$}", ""+step);
			lf = StringUtil.strReplace(lf,
					"{$$rec$$}", ""+vi.getRecNum());
			
			tmpA += lf;
		}
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allItems$$}", tmpA);
		
		return htmlTemplate;
	}
}
