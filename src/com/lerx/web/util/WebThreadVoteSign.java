package com.lerx.web.util;

import java.io.IOException;

import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.util.VoteSubjectUtil;
import com.lerx.vote.vo.VoteSubject;
import com.lerx.web.util.camp.PubUtil;
import com.lerx.web.util.camp.VoteUtil;
import com.lerx.web.vo.LoginCheckEl;
import com.lerx.web.vo.WebElements;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.UserCookie;
import com.opensymphony.xwork2.ActionSupport;

public class WebThreadVoteSign {
	public static String show(WebElements wel) throws IOException{
		wel.setRefererRec(false);
		IVoteSubjectDao voteSubjectDaoImp = wel.getVoteSubjectDaoImp();
//		IVoteStyleDao voteStyleDaoImp = wel.getVoteStyleDaoImp();
		IVoteItemDao voteItemDaoImp = wel.getVoteItemDaoImp();
		int gid=wel.getGid();
		ActionSupport as=wel.getAs();
		VoteSubject vs=voteSubjectDaoImp.findById(gid);
		VoteStyle curVoteStyle = wel.getCurVoteStyle();
		
		LoginCheckEl lcel=PubUtil.logincheck(wel);
		wel.setCdm(lcel.getCdm());
		wel.setUc(lcel.getUc());
		wel.setUserLogined(lcel.isLogined());
		UserCookie uc=wel.getUc();
//		int subId=wel.getGid();
		//下面顺序不能错
		wel=VoteUtil.init(wel, curVoteStyle.getJoinStyle());
		String tileFormat=wel.getTitleFormat();
		tileFormat=StringUtil.strReplace(tileFormat, "{$$app$$}",
				as.getText("lerx.voteSignTitle"));
		tileFormat=StringUtil.strReplace(tileFormat, "{$$voteSubject$$}",
				vs.getTitle());
		wel.setTitleFormat(tileFormat);
		
		wel=VoteUtil.endVoteService(wel);
//		FormatElements fel=wel.getFel();
		String htmlTemplate = wel.getHtmlTemplate();
		
		htmlTemplate=VoteSubjectUtil.init(htmlTemplate, as, vs);
		
		//统计
		Long allSign,allSingPassed,allSingNoPassed;
		allSign=voteItemDaoImp.count(vs, 0);
		allSingPassed=voteItemDaoImp.count(vs, 1);
		allSingNoPassed=voteItemDaoImp.count(vs, 2);
		
//		htmlTemplate = StringUtil.strReplace(htmlTemplate,
//				"{$$subId$$}", ""+subId);
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSign$$}", allSign.toString());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSingPassed$$}", allSingPassed.toString());
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$allSingNoPassed$$}", allSingNoPassed.toString());
		
		
		if (vs.isNetJoinMustBeMember() && uc==null){
			String rootFolder;
			rootFolder=wel.getSiteStyleDaoImp().findDef().getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(as);
			rfv.setRequest(wel.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("loginNeed.txt");
			rfv.setSubFolder("html");
			
			String txt = FileUtil.readFile(rfv);
			
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$loginNeedMessage$$}", txt);
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", " disabled =\"true\" ");
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$loginNeedMessage$$}", "");
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$submitDisabled$$}", "");
		}
		
		return htmlTemplate;
	}
}
