package com.lerx.article.util;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.FileEl;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.sys.util.vo.Rs;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class ThreadUtil {
	
	/*
	 * 取单个文章内容
	 */
	public static String bodyShow(FormatElements el,ArticleThread at,int length){
		String body=at.getBody();
		String ellipsisChar=SysUtil.ellipsis(el.getAs());
		if (body.length()>length){
			body=body.substring(0, length)+ellipsisChar;
		}
		return body;
	}
	
	/*
	 * 格式化href
	 * 
	 */
	public static String formatHref(FormatElements el,ArticleThread at) {
		String lf=el.getLf();
		String postion;
		boolean noArtStatic;
		if (el.getPostion()==null){
			postion=el.getRequest().getContextPath();
		}else{
			postion=el.getPostion().trim();
		}
		if (at.isState()) {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}",
					el.getAs().getText("lerx.passedTips"));
		} else {
			lf = StringUtil.strReplace(lf, "{$$passStateStr$$}",
					el.getAs().getText("lerx.noPassedTips"));
		}

		
		
		if (el.getAs().getText("lerx.artStaticMod").trim().equalsIgnoreCase("true")){
			noArtStatic=false;
		}else{
			noArtStatic=true;
		}

		String shortTitle = at.getShortTitle();
		String title = at.getTitle();
		
		
		
		
		
		String synopsis=at.getSynopsis();
		String ellipsisChar=SysUtil.ellipsis(el.getAs());
		
		int maxLengthOfSynopsis;
		
		try {
			maxLengthOfSynopsis=Integer.valueOf(el.getAs().getText("lerx.threadSynopsisLength"));
		} catch (NumberFormatException e) {
			maxLengthOfSynopsis=0;
		}
		
		if (synopsis==null || synopsis.trim().length()<2){
			synopsis=at.getBody();
		}
		synopsis=StringUtil.nullFilter(synopsis);
		if (el.getAs().getText("lerx.threadSynopsisHtmlFilter").trim().equalsIgnoreCase("yes")){
			synopsis=StringUtil.htmlFilter(synopsis);
		}
		
		if (maxLengthOfSynopsis>0 && synopsis.length()>maxLengthOfSynopsis){
			synopsis=synopsis.substring(0, maxLengthOfSynopsis)+ellipsisChar;
		}
		
		if (shortTitle == null || shortTitle.trim().equals("")) {
			shortTitle = title;
		}
		shortTitle=StringUtil.nullFilter(shortTitle);
		int len = StringUtil.byteLen(shortTitle);
		
		if (!noArtStatic && len > el.getTitleLength() && el.getTitleLength() > 0) {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", " Title=\""
					+ title + "\" ");
			shortTitle=StringUtil.cutByte(shortTitle, el.getTitleLength());
//			shortTitle = shortTitle.substring(0, el.getTitleLength());
			
			shortTitle = shortTitle + ellipsisChar;
		} else {
			lf = StringUtil.strReplace(lf, "{$$alt$$}", "");
		}
		
		title=StringUtil.escape(title);
		shortTitle=StringUtil.escape(shortTitle);
		String originalTitle=title;
		String originalShortTitle=shortTitle;
		
		if (at.isEyeCatching() && el.getEyeCatchingStrCode()!=null && !el.getEyeCatchingStrCode().trim().equals("")){
			shortTitle=StringUtil.strReplace(el.getEyeCatchingStrCode(),"{$$title$$}",shortTitle);
		}
		if (at.isLinkJump() && el.getAs().getText("lerx.pageJumpMode").trim().equals("0")){
			lf = StringUtil.strReplace(lf, "{$$href$$}",
					at.getLinkUrl());
			lf = StringUtil.strReplace(lf, "{$$hrefLine$$}","<a target=\"_blank\" href=\""+
					at.getLinkUrl()+"\">"+shortTitle+"</a>");
		}else{
			if (!noArtStatic && at.isHtmlCreated()) {
				lf = StringUtil.strReplace(lf, "{$$href$$}",
						postion + "/" + at.getHtmlUrlPath() + "/"
								+ at.getHtmlURLFile());
				lf = StringUtil.strReplace(lf, "{$$hrefLine$$}","<a target=\"_blank\" href=\""+
						postion + "/" + at.getHtmlUrlPath() + "/"
								+ at.getHtmlURLFile()+"\">"+shortTitle+"</a>");

			} else {
				lf = StringUtil.strReplace(lf, "{$$href$$}",
						postion + "/" + el.getAs().getText("lerx.articleViewPageFileName").trim()+"?tid=" + at.getId());
				lf = StringUtil.strReplace(lf,"{$$hrefLine$$}","<a target=\"_blank\" href=\""+postion + "/" +el.getAs().getText("lerx.articleViewPageFileName").trim()+"?tid=" + at.getId()+"\">"+shortTitle+"</a>");
			}
		}
		
		java.text.SimpleDateFormat formatter;
		if (el.getDateFormatOnLine() != null && !el.getDateFormatOnLine().trim().equals("")) {
			formatter = new java.text.SimpleDateFormat(el.getDateFormatOnLine());
		} else {
			formatter = new java.text.SimpleDateFormat(
					el.getAs().getText("lerx.default.format.datetime"));
		}
		if (at.getAddTime() == null) {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}", "");
			lf = StringUtil.timeCustomReplace(lf, "addTime", null);
		} else {
			lf = StringUtil.strReplace(lf, "{$$addTime$$}",
					"" + formatter.format(at.getAddTime()));
			lf = StringUtil.timeCustomReplace(lf, "addTime", at.getAddTime());
		}

		if (el.isIncludeEditArea() && el.getEditAreaCode()!=null && !el.getEditAreaCode().trim().equals("")) {
			lf = StringUtil.strReplace(lf, "{$$editCode$$}", el.getEditAreaCode());
			lf = StringUtil.strReplace(lf, "{$$editFileURL$$}",
					"articleEdit.action?tid=" + at.getId());
			lf = StringUtil.strReplace(lf, "{$$deleteFileURL$$}",
					"article_del.action?tid=" + at.getId());
		} else {
			lf = StringUtil.strReplace(lf, "{$$editCode$$}", "");
		}
		String imageUrl;
		if (at.getThumbnail() != null && !at.getThumbnail().trim().equals("")) {
			imageUrl = at.getThumbnail().trim();
		} else {
			if (at.getMainImg() != null && !at.getMainImg().trim().equals("")) {
				imageUrl = at.getMainImg().trim();
			} else {
				imageUrl = "";
			}

		}
		
		if (imageUrl==null || imageUrl.trim().equals("")){
			imageUrl=el.getAs().getText("lerx.noImageShow");
			lf = StringUtil.strReplace(lf, "{$$titleImg$$}", "");
		}else{
			
			String rootFolder;
			rootFolder=el.getSiteStyleDaoImp().findDef().getRootResFolder();
			ReadFileArg rfv=new ReadFileArg();
			rfv.setAs(el.getAs());
			rfv.setRequest(el.getRequest());
			rfv.setRootFolder(rootFolder);
			rfv.setFileName("img.txt");
			rfv.setSubFolder("html");
			
			String txt = FileUtil.readFile(rfv);
			
//			
//			
//			
//			String txt = FileUtil.readConfigFile(el.getAs(), el.getRequest(),
//					"img.txt","html");
			
			txt = StringUtil.strReplace(txt, "{$$url$$}", StringUtil.nullFilter(imageUrl));
			
			lf = StringUtil.strReplace(lf, "{$$titleImg$$}", txt);
			
			
		}
		
		
		
		if (at.isState()) {
			lf = StringUtil.strReplace(lf, "{$$state$$}",
					" checked ");
		} else {
			lf = StringUtil.strReplace(lf, "{$$state$$}",
					" ");
		}
		
		
		lf = StringUtil.strReplace(lf, "{$$id$$}", "" + at.getId());
		lf = StringUtil.strReplace(lf, "{$$title$$}", shortTitle);
		lf = StringUtil.strReplace(lf, "{$$originalShortTitle$$}", originalShortTitle);
		lf = StringUtil.strReplace(lf, "{$$originalTitle$$}", originalTitle);
		lf = StringUtil.strReplace(lf, "{$$member$$}", StringUtil.nullFilter(at.getMember()));
		lf = StringUtil.strReplace(lf, "{$$author$$}", StringUtil.nullFilter(at.getAuthor()));
		lf = StringUtil.strReplace(lf, "{$$mainImg$$}", StringUtil.nullFilter(at.getMainImg()));
		lf = StringUtil.strReplace(lf, "{$$thumbnail$$}", StringUtil.nullFilter(at.getThumbnail()));
		lf = StringUtil.strReplace(lf, "{$$imageUrl$$}", imageUrl);
		
		
		lf = StringUtil.strReplace(lf, "{$$mainImgExplain$$}", StringUtil.nullFilter(at.getMainImgExplain()));
		lf = StringUtil.strReplace(lf, "{$$authorUrl$$}", StringUtil.nullFilter(at.getAuthorUrl()));
		lf = StringUtil.strReplace(lf, "{$$authorEmail$$}", StringUtil.nullFilter(at.getAuthorEmail()));
		lf = StringUtil.strReplace(lf, "{$$authorDept$$}", StringUtil.nullFilter(at.getAuthorDept()));
		lf = StringUtil.strReplace(lf, "{$$views$$}", "" + at.getViews());
		lf = StringUtil.strReplace(lf, "{$$synopsis$$}", StringUtil.nullFilter(synopsis));
		return lf;
	}
	
	
	public static ArticleThread escape(ArticleThread at){
		if (at==null){
			return null;
		}
//		at.setTitle(StringUtil.escape(at.getTitle()));
		at.setAccessionalTitle(StringUtil.escape(at.getAccessionalTitle()));
		at.setAuthor(StringUtil.escape(at.getAuthor()));
		at.setAuthorDept(StringUtil.escape(at.getAuthorDept()));
		at.setAuthorEmail(StringUtil.escape(at.getAuthorEmail()));
		at.setAuthorUrl(StringUtil.escape(at.getAuthorUrl()));
//		at.setBody(StringUtil.escape(at.getBody()));
		at.setJournal(StringUtil.escape(at.getJournal()));
		at.setPensileTitle(StringUtil.escape(at.getPensileTitle()));
		at.setLinkTitle(StringUtil.escape(at.getLinkTitle()));
		at.setMember(StringUtil.escape(at.getMember()));
		at.setPasser(StringUtil.escape(at.getPasser()));
//		at.setShortTitle(StringUtil.escape(at.getShortTitle()));
		at.setSynopsis(StringUtil.escape(at.getSynopsis()));
		return at;
	}
	
	public static String formatLastOrNext(String lf,HttpServletRequest request,ActionSupport actionSupport,ArticleThread at,int mod){
		if (at==null){
			return "";
		}
		String pre,url;
		String shortTitle = at.getShortTitle();
		String title = at.getTitle();
		if (shortTitle == null || shortTitle.trim().equals("")) {
			shortTitle = title;
		}
		
		
		if (mod==0){
			pre=actionSupport.getText("lerx.lastArticlePreStr");
		}else{
			pre=actionSupport.getText("lerx.nextArticlePreStr");
		}
		if (at.isHtmlCreated()) {
			url = request.getContextPath() + "/" + at.getHtmlUrlPath() + "/"
							+ at.getHtmlURLFile();
		} else {
			url = request.getContextPath() + "/" + actionSupport.getText("lerx.articleViewPageFileName").trim()+"?tid=" + at.getId();
		}
		
		if (lf==null || lf.trim().equals("")){
			lf=pre+ " <a href=\""
			+ url + "\">" + at.getTitle() + "</a>";
		}else{
			lf = StringUtil.strReplace(lf, "{$$pre$$}", pre);
			lf = StringUtil.strReplace(lf, "{$$id$$}", "" + at.getId());
			lf = StringUtil.strReplace(lf, "{$$title$$}", shortTitle);
			if (at.isLinkJump()){
				lf = StringUtil.strReplace(lf, "{$$href$$}",
						at.getLinkUrl());
				lf = StringUtil.strReplace(lf, "{$$hrefLine$$}","<a target=\"_blank\" href=\""+
						at.getLinkUrl()+"\">"+shortTitle+"</a>");
			}else{
				lf = StringUtil.strReplace(lf, "{$$href$$}", url);
				lf = StringUtil.strReplace(lf, "{$$hrefLine$$}", "<a href=\""
						+ url + "\">" + at.getTitle() + "</a>");	
			}
			
			
			
		}
		return lf;
	}
	
	@SuppressWarnings("unchecked")
	public static String customFormat(FormatElements el,int fmod,int gid,SiteStyle curStyle,int page,int pageSize,int firstResult){
		
//		System.out.println("fmod:"+fmod);
		int speedMod=Integer.valueOf(el.getAs().getText("lerx.data.query.mod"));
		int recNewMode=Integer.valueOf(el.getAs().getText("lerx.recNewMode"));
		String formatStr;
		switch (fmod) {
		case 2:
			formatStr = curStyle.getCustomFormatCode2();
			break;
		case 3:
			formatStr = curStyle.getCustomFormatCode3();
			break;
		case 4:
			formatStr = curStyle.getCustomFormatCode4();
			break;
		case 5:
			formatStr = curStyle.getCustomFormatCode5();
			break;
		case 6:
			formatStr = curStyle.getCustomFormatCode6();
			break;
		case 7:
			formatStr = curStyle.getCustomFormatCode7();
			break;
		case 8:
			formatStr = curStyle.getCustomFormatCode8();
			break;
		default:
			formatStr = curStyle.getCustomFormatCode1();
			break;
		}
		int soul;
		ArticleGroup ag=el.getArticleGroupDaoImp().findById(gid);
		if (ag.isSoulOnIndex()){
			soul=1;
		}else{
			soul=0;
		}
		Rs rs=el.getArticleThreadDaoImp().findByGroupAndMod(gid, page, pageSize, recNewMode, 1, false,soul,firstResult,false,speedMod);
//		List<Long> ls = (List<Long>) rs.getList();
		List<Long> lan = null;
		List<ArticleThread> lat = null;
		if (speedMod==1){
			lat=(List<ArticleThread>) rs.getList();
		}else{
			lan = (List<Long>) rs.getList();
		}
		
//		String f=formatStr;
		el.setLf(formatStr);
		String tmp="",tmpAll="";
		int step=0;
		ArticleThread at;
		if (speedMod==1){
			for (ArticleThread ati : lat) {
//				tmp=f;
				step++;
				at=ati;
				at=escape(at);
				tmp = formatHref(el, at);
				
				tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
				tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
				tmpAll+=tmp;
			}
		}else{
			for (Long atid : lan) {
//				tmp=f;
				step++;
				at=el.getArticleThreadDaoImp().findById(atid);
				at=escape(at);
				tmp = formatHref(el, at);
				
				tmp=StringUtil.strReplace(tmp, "{$$sn$$}", ""+step);
				tmp=StringUtil.strReplace(tmp, "{$$sn0$$}", ""+(step-1));
				tmpAll+=tmp;
			}
		}
		
		tmpAll=StringUtil.strReplace(tmpAll,"$$}{$$",",");
		tmpAll=StringUtil.strReplace(tmpAll,"$$}","");
		tmpAll=StringUtil.strReplace(tmpAll,"{$$","");
		return tmpAll;
		
		
	}
	
	//按配置返回文件名
	public static FileEl fileNameFormat(ActionSupport as,ArticleThread at,int mod) throws InterruptedException{
		Timestamp t;
		boolean newFile=false;
		FileEl fe = new FileEl();
		if (at.getHtmlURLFile()==null || at.getHtmlURLFile().trim().equals("") || at.getHtmlUrlPath()==null || at.getHtmlUrlPath().trim().equals("")){
			newFile=true;
			if (as.getText("lerx.staticFileNameByAddTime").trim().equalsIgnoreCase("true")){
				
				if (at.getAddTime()==null){
					
					t=new Timestamp(System.currentTimeMillis());
				}else{
					t=at.getAddTime();
					
				}
			}else{
				
				t=new Timestamp(System.currentTimeMillis());
			}
		}else{
			
			if (mod==1){ //如果强制重新生成
				newFile=true;
				if (as.getText("lerx.staticFileNameByAddTime").trim().equalsIgnoreCase("true")){
					t=at.getAddTime();
				}else{
					t=new Timestamp(System.currentTimeMillis());
				}
				
			}else{
				t=new Timestamp(System.currentTimeMillis());
				fe.setPath(at.getHtmlUrlPath());
				fe.setName(at.getHtmlURLFile());
			}
			
			
//			t=new Timestamp(System.currentTimeMillis());
		}
		
		String folder;
		if (newFile){
			java.text.SimpleDateFormat HtmlFilePathFormatter = new java.text.SimpleDateFormat(
					as.getText("lerx.htmlFilePathFormat"));
			String agFileFolder=as.getText("lerx.staticSiteFileFolder");
			if (agFileFolder==null || agFileFolder.trim().equalsIgnoreCase("null")){
				agFileFolder="";
			}else{
				agFileFolder+="/";
			}
			if (as.getText("lerx.staticFileByNav").trim().equals("yes")){
				ArticleGroup ag=at.getArticleGroup();
				int agId=ag.getId();
				String agFolder;
				if (ag.getStaticHtmlFolder()==null || ag.getStaticHtmlFolder().trim().equals("")){
					agFolder="c"+agId;
				}else{
					agFolder=ag.getStaticHtmlFolder();
				}
				
				agFolder+="/";
				agFileFolder+=agFolder;
			}
			if (t==null){
				t=new Timestamp(System.currentTimeMillis());
			}
			folder = as.getText("lerx.htmlPath") + "/" + agFileFolder
			+ HtmlFilePathFormatter.format(t);
			fe.setPath(folder);
			folder=StringUtil.strReplace(folder, "/", File.separator);
			fe.setRealPath(folder);
			String fn = FileUtil.formatFileName(as, t,"lerx.staticArticleFileNameFormat","lerx.staticFileNameTimeFormat", ""+at.getId(), null);
			fn = StringUtil.strReplace(fn,"tid", ""+at.getId());
			if (at.getUser()!=null){
				fn = StringUtil.strReplace(fn,"uid", ""+at.getUser().getId());
			}
			fe.setName(fn);
		}else{
			folder=StringUtil.strReplace(fe.getPath(), "/", File.separator);
			fe.setRealPath(folder);
		}
		return fe;
	}
	
	//按配置返回文件名
	/*
	 * 
	 * 2.3.4版本中exeAndWait无法使用action的getText。。。写此补救 
	 */
	 
	public static FileEl fileNameFormat(HttpServletRequest request,ArticleThread at,int mod) throws InterruptedException{
		Timestamp t;
		boolean newFile=false;
		FileEl fe = new FileEl();
		String staticArticleFileNameByAddTime=readRec("lerx.staticFileNameByAddTime");
		
		
		if (at.getHtmlURLFile()==null || at.getHtmlURLFile().trim().equals("") || at.getHtmlUrlPath()==null || at.getHtmlUrlPath().trim().equals("")){
			newFile=true;
			if (staticArticleFileNameByAddTime.trim().equalsIgnoreCase("true")){
				
				if (at.getAddTime()==null){
					
					t=new Timestamp(System.currentTimeMillis());
				}else{
					t=at.getAddTime();
					
				}
			}else{
				
				t=new Timestamp(System.currentTimeMillis());
			}
		}else{
			
			if (mod==1){ //如果强制重新生成
				newFile=true;
				if (staticArticleFileNameByAddTime.trim().equalsIgnoreCase("true")){
					t=at.getAddTime();
				}else{
					t=new Timestamp(System.currentTimeMillis());
				}
				
			}else{
				t=new Timestamp(System.currentTimeMillis());
				fe.setPath(at.getHtmlUrlPath());
				fe.setName(at.getHtmlURLFile());
			}
			
			
//			t=new Timestamp(System.currentTimeMillis());
		}
		
		String folder;
		if (newFile){
			java.text.SimpleDateFormat HtmlFilePathFormatter = new java.text.SimpleDateFormat(readRec("lerx.htmlFilePathFormat"));
			
			String agFileFolder=readRec("lerx.staticSiteFileFolder");
			if (agFileFolder==null || agFileFolder.trim().equalsIgnoreCase("null")){
				agFileFolder="";
			}else{
				agFileFolder+="/";
			}
			if (readRec("lerx.staticFileByNav").trim().equals("yes")){
				ArticleGroup ag=at.getArticleGroup();
				int agId=ag.getId();
				String agFolder;
				if (ag.getStaticHtmlFolder()==null || ag.getStaticHtmlFolder().trim().equals("")){
					agFolder="c"+agId;
				}else{
					agFolder=ag.getStaticHtmlFolder();
				}
				
				agFolder+="/";
				agFileFolder+=agFolder;
			}
			if (t==null){
				t=new Timestamp(System.currentTimeMillis());
			}
			
			folder = readRec("lerx.htmlPath") + "/"+agFileFolder
			+ HtmlFilePathFormatter.format(t);
			fe.setPath(folder);
			folder=StringUtil.strReplace(folder, "/", File.separator);
			fe.setRealPath(folder);
			String fn = FileUtil.formatFileName(request, t,"lerx.staticArticleFileNameFormat","lerx.staticFileNameTimeFormat", ""+at.getId(), null);
			fn = StringUtil.strReplace(fn,"tid", ""+at.getId());
			if (at.getUser()!=null){
				fn = StringUtil.strReplace(fn,"uid", ""+at.getUser().getId());
			}
			fe.setName(fn);
		}else{
			folder=StringUtil.strReplace(fe.getPath(), "/", File.separator);
			fe.setRealPath(folder);
		}
		return fe;
	}

	public static int price(ArticleThread at){
		
		int price=at.getPrice();
		if (price==0){
			price=at.getArticleGroup().getPrice();
		}
				
		return price;
	}
	
	private static String readRec(String key){
		return LocalizedTextUtil.findDefaultText(key,new Locale("zh_CN")); 
//		String curPath=
//			request.getSession().getServletContext().getRealPath("");
//			curPath+=File.separator+"WEB-INF"+File.separator+"classes"+File.separator;
//		String value=CfgFile.read(curPath+"resources"+recFile+"_zh_CN.properties", key).trim();
//			
//		return value;
	}
	
//	private static String readRec(HttpServletRequest request,String recFile,String key){
//		String curPath=
//			request.getSession().getServletContext().getRealPath("");
//			curPath+=File.separator+"WEB-INF"+File.separator+"classes"+File.separator;
//		String value=CfgFile.read(curPath+"resources"+recFile+"_zh_CN.properties", key).trim();
//			
//		return value;
//	}
	
}
