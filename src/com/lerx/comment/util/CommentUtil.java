package com.lerx.comment.util;

import com.lerx.comment.util.vo.CommentFilterUtil;
import com.lerx.comment.vo.Comment;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.StrFilterUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CommentUtil {
	public static String formatHref(FormatElements el,Comment c) {
		String lf=el.getLf();
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(el.getAs().getText("lerx.default.format.datetime"));
		lf = StringUtil.strReplace(lf, "{$$body$$}", c.getBody());
		lf = StringUtil.strReplace(lf, "{$$addTime$$}", "" + formatter.format(c.getAddTime()));
		lf = StringUtil.strReplace(lf, "{$$cid$$}", "" + c.getId());
		lf = StringUtil.strReplace(lf, "{$$phone$$}",StringUtil.nullFilter(c.getPhone()));
		lf = StringUtil
		.strReplace(lf, "{$$from$$}", c.getPublisherFrom());
		lf = StringUtil.strReplace(lf, "{$$name$$}", c.getPublisher());
		if (c.isState()){
			lf = StringUtil.strReplace(lf, "{$$state$$}", "");
		}else{
			lf = StringUtil.strReplace(lf, "{$$state$$}", el.getAs().getText("lerx.msg.shield"));
		}
		return lf;
	}
	
	public static  CommentFilterUtil filter(ActionSupport as,Comment c,String filterStr){

		boolean rep;
		if (as.getText("lerx.mode.filter.replace").trim().equalsIgnoreCase("true")){
			rep=true;
		}else{
			rep=false;
		}
		
		StrFilterUtil sfu;
		sfu=StringUtil.filterStr(c.getBody(), filterStr, rep);
		if (sfu.isCon()){
			c.setBody(sfu.getStr());
			sfu=StringUtil.filterStr(c.getEmail(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			c.setEmail(sfu.getStr());
			sfu=StringUtil.filterStr(c.getPhone(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			c.setPhone(sfu.getStr());
			sfu=StringUtil.filterStr(c.getTitle(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			c.setTitle(sfu.getStr());
			sfu=StringUtil.filterStr(c.getPublisher(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			c.setPublisher(sfu.getStr());
			sfu=StringUtil.filterStr(c.getPublisherFrom(), filterStr, rep);
		}
		
		if (sfu.isCon()){
			c.setPublisherFrom(sfu.getStr());
		}
		CommentFilterUtil cfu=new CommentFilterUtil();
		cfu.setFound(sfu.isFound());
		cfu.setCon(sfu.isCon());
		cfu.setRep(rep);
		cfu.setC(c);
		return cfu;		
	
	}
}
