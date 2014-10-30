package com.lerx.qa.util;

import java.io.File;
import java.sql.Timestamp;

import com.lerx.qa.vo.QaItem;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.FileEl;
import com.lerx.sys.util.vo.FormatElements;
import com.opensymphony.xwork2.ActionSupport;

public class QaItemUtil {
	public static String formatHref(FormatElements el,QaItem qi) {
		String lf=el.getLf(),title;
		title=qi.getTitle();
		title=StringUtil.escape(title);
		if (qi.isOpen()) {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}",
					el.getAs().getText("lerx.openTips"));
		} else {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}",
					el.getAs().getText("lerx.noOpenTips"));
		}
		
		String ellipsisChar=SysUtil.ellipsis(el.getAs());
		if (ellipsisChar==null || ellipsisChar.trim().equals("") || ellipsisChar.trim().equals("lerx.ellipsisChar")){
			ellipsisChar="...";
		}else{
			ellipsisChar=ellipsisChar.trim();
		}
		
		if (title.length() > el.getTitleLength() && el.getTitleLength() > 0) {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", " Title=\""
					+ title + "\" ");
			title = title.substring(0, el.getTitleLength());
			
			title = title + ellipsisChar;
		} else {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", "");
		}
		
		java.text.SimpleDateFormat formatter;
		if (el.getDateFormatOnLine() != null && !el.getDateFormatOnLine().trim().equals("")) {
			formatter = new java.text.SimpleDateFormat(el.getDateFormatOnLine());
		} else {
			formatter = new java.text.SimpleDateFormat(
					el.getAs().getText("lerx.default.format.datetime"));
		}
		
		if (qi.getAddTime() == null) {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}", "");
			lf = StringUtil.timeCustomReplace(lf, "addTime", null);
		} else {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}",
					"" + formatter.format(qi.getAddTime()));
			lf = StringUtil.timeCustomReplace(lf, "addTime", qi.getAddTime());
		}
		lf = StringUtil.strReplace(lf, "{$$id$$}", "" + qi.getId());
		lf = StringUtil.strReplace(lf, "{$$title$$}", title);
		lf = StringUtil.strReplace(lf, "{$$author$$}", StringUtil.nullFilter(qi.getAuthor()));
		lf = StringUtil.strReplace(lf, "{$$question$$}", StringUtil.nullFilter(qi.getQuestion()));
		
		if (qi.isOpen()){
			lf = StringUtil.strReplace(lf, "{$$openChecked$$}", "checked");
		}else{
			lf = StringUtil.strReplace(lf, "{$$openChecked$$}", "");
		}
		
		if (qi.getAnswer()==null || qi.getAnswer().trim().equals("")){
			lf = StringUtil.strReplace(lf, "{$$answer$$}", el.getAs().getText("lerx.msg.waitForReply"));
			lf = StringUtil.strReplace(lf, "{$$answerArea$$}", "");
		}else{
			lf = StringUtil.strReplace(lf, "{$$answer$$}", StringUtil.nullFilter(qi.getAnswer()));
			lf = StringUtil.strReplace(lf, "{$$answerArea$$}", StringUtil.nullFilter(qi.getAnswer()));
		}
		
		lf = StringUtil.strReplace(lf, "{$$qq$$}", qi.getQq());
		lf = StringUtil.strReplace(lf, "{$$email$$}", qi.getEmail());
		lf = StringUtil.strReplace(lf, "{$$phone$$}", qi.getPhone());
		lf = StringUtil.strReplace(lf, "{$$addr$$}", qi.getAddr());
		lf = StringUtil.strReplace(lf, "{$$replier$$}", StringUtil.nullFilter(qi.getReplier()));
		if (qi.getReplyTime() == null) {
			lf = StringUtil.strReplace(lf, "{$$replyTime$$}", "");
			lf = StringUtil.timeCustomReplace(lf, "replyTime", null);
		} else {
			lf = StringUtil.strReplace(lf, "{$$replyTime$$}",
					"" + formatter.format(qi.getAddTime()));
			lf = StringUtil.timeCustomReplace(lf, "replyTime", qi.getReplyTime());
		}
		if (qi.getReplyUser()==null){
			lf = StringUtil.strReplace(lf, "{$$replyUser$$}", "");
		}else{
			lf = StringUtil.strReplace(lf, "{$$replyUser$$}", qi.getReplyUser().getUserName());
		}
		
		lf = StringUtil.strReplace(lf, "{$$views$$}", "" + qi.getViews());
		
		if (qi.isHtmlCreated()) {
			lf = StringUtil.strReplace(lf, "{$$href$$}",
					el.getRequest().getContextPath() + "/" + qi.getHtmlUrlPath() + "/"
							+ qi.getHtmlURLFile());
			lf = StringUtil.strReplace(lf, "{$$hrefLine$$}","<a target=\"_blank\" href=\""+
					el.getRequest().getContextPath() + "/" + qi.getHtmlUrlPath() + "/"
							+ qi.getHtmlURLFile()+"\">"+qi.getTitle()+"</a>");

		} else {
			lf = StringUtil.strReplace(lf, "{$$href$$}",
					el.getRequest().getContextPath() + "/qa.action?tid=" + qi.getId());
			lf = StringUtil.strReplace(lf,"{$$hrefLine$$}","<a target=\"_blank\" href=\""+el.getRequest().getContextPath() + "/qa.action?tid=" + qi.getId()+"\">"+qi.getTitle()+"</a>");
		}
		
		lf = StringUtil.strReplace(lf,
				"{$$views$$}", "" + qi.getViews());
		lf = StringUtil.strReplace(lf,
				"{$$lastIP$$}", IpUtil.ipFilter(StringUtil
						.nullFilter(qi.getLastViewIp()), Integer
						.valueOf(el.getAs().getText("lerx.rule.length.ip.filter"))));
		return lf;
	}
	
	
	//按配置返回文件名
	public static FileEl fileNameFormat(ActionSupport as,QaItem qi,int mod) throws InterruptedException{
		Timestamp t;
		boolean newFile=false;
		FileEl fe = new FileEl();
		if (qi.getHtmlURLFile()==null || qi.getHtmlURLFile().trim().equals("") || qi.getHtmlUrlPath()==null || qi.getHtmlUrlPath().trim().equals("")){
			newFile=true;
			if (as.getText("lerx.staticFileNameByAddTime").trim().equalsIgnoreCase("true")){
				
				if (qi.getAddTime()==null){
					
					t=new Timestamp(System.currentTimeMillis());
				}else{
					t=qi.getAddTime();
					
				}
			}else{
				
				t=new Timestamp(System.currentTimeMillis());
			}
		}else{
			
			if (mod==1){ //如果强制重新生成
				newFile=true;
				if (as.getText("lerx.staticFileNameByAddTime").trim().equalsIgnoreCase("true")){
					t=qi.getAddTime();
				}else{
					t=new Timestamp(System.currentTimeMillis());
				}
				
			}else{
				t=new Timestamp(System.currentTimeMillis());
				fe.setPath(qi.getHtmlUrlPath());
				fe.setName(qi.getHtmlURLFile());
			}
			
			
//			t=new Timestamp(System.currentTimeMillis());
		}
		
		String folder;
		if (newFile){
			java.text.SimpleDateFormat HtmlFilePathFormatter = new java.text.SimpleDateFormat(
					as.getText("lerx.htmlFilePathFormat"));
			String qaFileFolder=as.getText("lerx.staticQaFileFolder");
			if (qaFileFolder==null || qaFileFolder.trim().equalsIgnoreCase("null")){
				qaFileFolder="";
			}else{
				qaFileFolder+="/";
			}
			if (as.getText("lerx.staticFileByNav").trim().equals("yes")){
				int qnId=qi.getQn().getId();
				String qnFolder;
				if (qi.getQn().getStaticHtmlFolder()==null || qi.getQn().getStaticHtmlFolder().trim().equals("")){
					qnFolder="q"+qnId;
				}else{
					qnFolder=qi.getQn().getStaticHtmlFolder().trim();
				}
				qnFolder+="/";
				qaFileFolder+=qnFolder;
			}
			folder = as.getText("lerx.htmlPath") + "/"+qaFileFolder
			+ HtmlFilePathFormatter.format(t);
			fe.setPath(folder);
			folder=StringUtil.strReplace(folder, "/", File.separator);
			fe.setRealPath(folder);
			String fn = FileUtil.formatFileName(as, t,"lerx.staticQaFileNameFormat","lerx.staticFileNameTimeFormat", ""+qi.getId(), null);
			fn = StringUtil.strReplace(fn,"tid", ""+qi.getId());
			if (qi.getReplyUser()!=null){
				fn = StringUtil.strReplace(fn,"uid", ""+qi.getReplyUser().getId());
			}
			fe.setName(fn);
		}else{
			folder=StringUtil.strReplace(fe.getPath(), "/", File.separator);
			fe.setRealPath(folder);
		}
		return fe;
	}
}



