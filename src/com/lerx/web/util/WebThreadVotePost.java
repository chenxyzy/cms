package com.lerx.web.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadVotePost {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		IVoteSubjectDao voteSubjectDaoImp = wel.getVoteSubjectDaoImp();
//		IVoteStyleDao voteStyleDaoImp = wel.getVoteStyleDaoImp();
		IVoteItemDao voteItemDaoImp = wel.getVoteItemDaoImp();
		IVoteRecDao voteRecDaoImp=wel.getVoteRecDaoImp();
		int gid=wel.getGid();
		int mod=wel.getMod();
		HttpServletRequest request=wel.getRequest();
		ActionSupport as=wel.getAs();
		VoteSubject vs=voteSubjectDaoImp.findById(gid);
		VoteStyle curVoteStyle = wel.getCurVoteStyle();
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
//		int subId=wel.getGid();
		//下面五行顺序不能错
		wel=VoteUtil.init(wel,curVoteStyle.getVoteStyle());
		String tileFormat=wel.getTitleFormat();
		
		tileFormat=StringUtil.strReplace(tileFormat, "{$$app$$}",
				as.getText("lerx.votePostTitle"));
		tileFormat=StringUtil.strReplace(tileFormat, "{$$voteSubject$$}",
				vs.getTitle());
		wel.setTitleFormat(tileFormat);
		
		wel=VoteUtil.endVoteService(wel);
		FormatElements fel=wel.getFel();
		fel.setLf(wel.getHrefLineFormat());
		String htmlTemplate = wel.getHtmlTemplate();
		
		htmlTemplate=VoteSubjectUtil.init(htmlTemplate, as, vs);
		//报名起始时间
		
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
		
		//增加区域item显示
		if (vs.isUseVerifyCode()){
			
		}
		
		//安全
		System.currentTimeMillis();
		String key,fileSec="",secStr = null;
		String randKey = StringUtil.randomString(10).toLowerCase();
		
		try {
			fileSec = SrvInf.readSecStr(request, as.getText("lerx.hostSecFile"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		secStr = StringUtil.md5(fileSec.concat(randKey)).toLowerCase();
		
		key=StringUtil.md5(StringUtil.strAtBit(secStr, StringUtil.bitForStrCharsAdd(fileSec)));
		if (mod>0){
			mod--;
		}else{
			mod = vs.getOrderType();
		}
		List<Long> lv=voteItemDaoImp.queryAll(mod, vs.getId(), 0);
//		System.out.println("lv.size():"+lv.size());
		VoteItem vi;
		String lf,tmpA="";
		VoteStyleSubElementInCommon el = wel.getVel();
		int step=0;
		for (Long vid : lv) {
			
			step++;
			fel.setLf(el.getItemsLoopFormat());
			
			vi=voteItemDaoImp.findById(vid);
			lf = VoteItemUtil.formatHref(fel, vi,key);
			lf = StringUtil.strReplace(lf,
					"{$$sn$$}", ""+step);
			tmpA += lf;
		}
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allItems$$}", tmpA);
		
		//检查当前的时间是否在投票规定的时间以内
		boolean con = true;
		Long curTime = System.currentTimeMillis();
		Long startTime;
		Long endTime;
		if (vs.getVoteStartTime() == null) {
			startTime = null;
		} else {
			startTime = vs.getVoteStartTime().getTime();
		}
		if (vs.getVoteEndTime() == null) {
			endTime = null;
		} else {
			endTime = vs.getVoteEndTime().getTime();
		}

		if (startTime != null && curTime < startTime) {
			con = false;
		}

		if (endTime != null && curTime > endTime) {
			con = false;
		}
		if (!con){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", " disabled =\"true\" ");
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", "");
		}
		
		if (secStr != null) {
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$secStr$$}", secStr);
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$randKey$$}", randKey);
		}
//		System.out.println("key2:"+key);
		if (key !=null){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$postKey$$}", key);
		}
		
		//查询当前投票数
		Long count=voteRecDaoImp.findRecCountBySub(vs);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$currentRec$$}", ""+count);
		
		//最大投票数
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$maxSelect$$}", ""+vs.getUpperLimit());
		//投票时间
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$voteStartTime$$}", ""+startTime);
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$voteEndTime$$}", ""+endTime);
		
//		String rebotJs=RobotAway.createJS();
//		String rebotJsFunction=RobotAway.getCheckFunction();
//		System.out.println("函数：rebotJsFunction:"+rebotJsFunction);
//		htmlTemplate = StringUtil.strReplace(htmlTemplate,
//				"{$$rebotJs$$}", rebotJs);
//		htmlTemplate = StringUtil.strReplace(htmlTemplate,
//				"{$$rebotJsFunction$$}", rebotJsFunction);
		return htmlTemplate;
	}
}
