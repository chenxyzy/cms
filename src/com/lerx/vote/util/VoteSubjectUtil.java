package com.lerx.vote.util;

import com.lerx.sys.util.StringUtil;
import com.lerx.vote.vo.VoteSubject;
import com.opensymphony.xwork2.ActionSupport;

public class VoteSubjectUtil {
	public static VoteSubject prosTrim(VoteSubject vs){
		if (vs.getTitle()!=null){
			vs.setTitle(vs.getTitle().trim());
		}
		
		if (vs.getIpArea()!=null){
			vs.setIpArea(vs.getIpArea().trim());
		}
		
		if (vs.getVotePassword()!=null){
			vs.setVotePassword(vs.getVotePassword().trim());
		}
		return vs;
	}
	
	public static String init(String htmlTemplate,ActionSupport as,VoteSubject vs){
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$subId$$}", ""+vs.getId());
		
		java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat(
				as.getText("lerx.default.format.datetime"));;
				
		if (vs.getSignStartTime()!=null){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$signStartTime$$}", formatter.format(vs.getSignStartTime()));
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$signStartTime$$}", "");
		}
		if (vs.getSignEndTime()!=null){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$signEndTime$$}", formatter.format(vs.getSignEndTime()));	
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$signEndTime$$}", "");
		}
		
		if (vs.getVoteStartTime()!=null){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$voteStartTime$$}", formatter.format(vs.getVoteStartTime()));
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$voteStartTime$$}","");
		}
		
		if (vs.getVoteEndTime()!=null){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$voteEndTime$$}", formatter.format(vs.getVoteEndTime()));
		}else{
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$voteEndTime$$}","");
		}
		
		htmlTemplate = StringUtil.strReplace(htmlTemplate,
				"{$$subjectTitle$$}",vs.getTitle().trim());
		return htmlTemplate;
		
	}
}
