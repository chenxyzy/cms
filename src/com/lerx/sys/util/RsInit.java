package com.lerx.sys.util;

import com.lerx.sys.util.vo.PageFormatShow;
import com.lerx.sys.util.vo.Rs;

public class RsInit {
	public static Rs rsInit(int page, int pageSize, long count) {

		int pageCount;
		Rs rs = new Rs();
		if (pageSize<=0){
			pageSize=10;
		}
		if (count % pageSize > 0) {
			pageCount = Integer.parseInt(String.valueOf(count / pageSize)) + 1;

		} else {
			pageCount = Integer.parseInt(String.valueOf(count / pageSize));
		}

		if (page > pageCount) {
			page = pageCount;
		}

		if (page < 1) {
			page = 1;
		}

		rs.setCount(count);
		rs.setPageCount(pageCount);
		rs.setPage(page);
		rs.setPageSize(pageSize);
		return rs;
	}

	public static String rsPageStrShow(Rs rs, String url,PageFormatShow fs,boolean rep) {
		String joinStr,tmp,tmpAll="";
		int s, e;
		if (url.indexOf("?") == -1) {
			joinStr = "?";
		} else {
			joinStr = "&";
		}
		joinStr=url+joinStr;
		s = rs.getPage() - 5;
		e = rs.getPage() + 4;
		if (s < 1) {
			s = 1;
			if (rs.getPageCount() >= 10) {
				e = 10;
			} else {
				e = rs.getPageCount();
			}
		}
		if (e > rs.getPageCount()) {
			e = rs.getPageCount();
			s = rs.getPageCount() - 10;
			if (s < 1) {
				s = 1;
			}
		}
		
		for (int i=s;i<=e;i++){
			if (i!=rs.getPage()){
				if (rep){
					tmp=StringUtil.strReplace(url,"{$$page$$}",""+i);
					tmp=StringUtil.strReplace(tmp,"{$$pageTitle$$}",""+i);
				}else{
					tmp="&nbsp;<a href="+joinStr+"page="+i+"&pageSize="+rs.getPageSize()+">"+i+"</a>";
				}
				
			}else{
				if (fs.getEyeCatchingCode()!=null && !fs.getEyeCatchingCode().trim().equals("")){
					tmp="&nbsp;"+fs.getEyeCatchingCode();
					tmp=StringUtil.strReplace(tmp,"{$$title$$}",""+i);
				}else{
					tmp="&nbsp;<b>"+i+"</b>";
				}
			}
			tmpAll+=tmp;
		}
			
		tmpAll=fs.getPrefix()+"&nbsp;"+tmpAll+"&nbsp;"+fs.getSuffix();
		if (rs.getPageCount()>1){
			if (rs.getPage()>1){
				if (rep){
					String tmpF=StringUtil.strReplace(url,"{$$pageTitle$$}",""+fs.getFirst());
					tmpF=StringUtil.strReplace(tmpF,"{$$page$$}",""+1);
					String tmpL=StringUtil.strReplace(url,"{$$pageTitle$$}",""+fs.getLast());
					tmpL=StringUtil.strReplace(tmpL,"{$$page$$}",""+(rs.getPage()-1));
					tmpAll=tmpF+"&nbsp;"+tmpL+"&nbsp;&nbsp;&nbsp;&nbsp;"+tmpAll;
				}else{
					tmpAll="<a href="+joinStr+"page=1&pageSize="+rs.getPageSize()+">"+fs.getFirst()+"</a>&nbsp;"+"<a href="+joinStr+"page="+(rs.getPage()-1)+"&pageSize="+rs.getPageSize()+">"+fs.getLast()+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"+tmpAll;
				}
				
			}
			if (rs.getPage()<rs.getPageCount()){
				if (rep){
					String tmpN=StringUtil.strReplace(url,"{$$pageTitle$$}",""+fs.getNext());
					 tmpN=StringUtil.strReplace(tmpN,"{$$page$$}",""+(rs.getPage()+1));
					String tmpE=StringUtil.strReplace(url,"{$$pageTitle$$}",""+fs.getEnd());
					 tmpE=StringUtil.strReplace(tmpE,"{$$page$$}",""+rs.getPageCount());
					tmpAll+="&nbsp;&nbsp;&nbsp;&nbsp;"+tmpN+"&nbsp;"+tmpE;
					
				}else{
					tmpAll+="&nbsp;&nbsp;&nbsp;&nbsp;<a href="+joinStr+"page="+(rs.getPage()+1)+"&pageSize="+rs.getPageSize()+">"+fs.getNext()+"</a>"+"&nbsp;<a href="+joinStr+"page="+rs.getPageCount()+"&pageSize="+rs.getPageSize()+">"+fs.getEnd()+"</a>";
					
				}
				
			}
			tmpAll+="&nbsp;&nbsp;&nbsp;&nbsp;"+fs.getCountPrefix()+"&nbsp;"+rs.getPageCount()+"&nbsp;"+fs.getSuffix();
			if (rep){
				//String tmpj=StringUtil.strReplace(url,"{$$page$$}",""+(rs.getPage()+1));
				//tmpAll+="&nbsp;&nbsp;"+fs.getJumpPrefix()+fs.getPrefix()+"<input type=\"text\" name=\"page\" size=\"2\" maxlength=\"5\" style=\"text-align: center\" value=\"" + rs.getPage() + "\" onKeyPress=\"if (event.keyCode==13) window.location='" + joinStr + "pageSize="+rs.getPageSize()+"&page='" + "+this.value;\">"+fs.getSuffix();
			}else{
				tmpAll+="&nbsp;&nbsp;"+fs.getJumpPrefix()+fs.getPrefix()+"<input type=\"text\" name=\"page\" size=\"2\" maxlength=\"5\" style=\"text-align: center\" value=\"" + rs.getPage() + "\" onKeyPress=\"if (event.keyCode==13) window.location='" + joinStr + "pageSize="+rs.getPageSize()+"&page='" + "+this.value;\">"+fs.getSuffix();
			}
			
		}
		
		
		return tmpAll;
	}
	
	public static String rsPageStrShowAtFun(Rs rs, String url,PageFormatShow fs) {
		String joinStr,tmp,tmpAll="";
		int s, e;
//		if (url.indexOf("?") == -1) {
//			joinStr = "?";
//		} else {
//			joinStr = "&";
//		}
		joinStr=url;
		s = rs.getPage() - 5;
		e = rs.getPage() + 4;
		if (s < 1) {
			s = 1;
			if (rs.getPageCount() >= 10) {
				e = 10;
			} else {
				e = rs.getPageCount();
			}
		}
		if (e > rs.getPageCount()) {
			e = rs.getPageCount();
			s = rs.getPageCount() - 10;
			if (s < 1) {
				s = 1;
			}
		}
		String tmpJionStr;
		for (int i=s;i<=e;i++){
			tmpJionStr=joinStr;
			tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageStr$$}",""+i);
			tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageSizeStr$$}",""+rs.getPageSize());
			tmp="&nbsp;<a href="+tmpJionStr+">"+i+"</a>";
			if (i!=rs.getPage()){
				tmp="&nbsp;<a href="+tmpJionStr+">"+i+"</a>";
			}else{
				if (fs.getEyeCatchingCode()!=null && !fs.getEyeCatchingCode().trim().equals("")){
					tmp="&nbsp;"+fs.getEyeCatchingCode();
					tmp=StringUtil.strReplace(tmp,"{$$title$$}",""+i);
				}else{
					tmp="&nbsp;<b>"+i+"</b>";
				}
				
			}
			tmpAll+=tmp;
		}
		String firstStr,lastStr;
		tmpAll=fs.getPrefix()+"&nbsp;"+tmpAll+"&nbsp;"+fs.getSuffix();
		if (rs.getPageCount()>1){
			if (rs.getPage()>1){
				tmpJionStr=joinStr;
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageStr$$}","1");
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageSizeStr$$}",""+rs.getPageSize());
				firstStr="<a href="+tmpJionStr+">"+fs.getFirst()+"</a>&nbsp;";
				tmpJionStr=joinStr;
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageStr$$}",""+(rs.getPage()-1));
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageSizeStr$$}",""+rs.getPageSize());
				lastStr="<a href="+tmpJionStr+">"+fs.getLast()+"</a>&nbsp;";
				tmpAll=firstStr+lastStr+"&nbsp;&nbsp;&nbsp;&nbsp;"+tmpAll;
				
				
				
			}
			String nextStr,endStr;
			if (rs.getPage()<rs.getPageCount()){
				tmpJionStr=joinStr;
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageStr$$}",""+(rs.getPage()+1));
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageSizeStr$$}",""+rs.getPageSize());
				nextStr="<a href="+tmpJionStr+">"+fs.getNext()+"</a>&nbsp;";
				tmpJionStr=joinStr;
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageStr$$}",""+rs.getPageCount());
				tmpJionStr=StringUtil.strReplace(tmpJionStr,"{$$pageSizeStr$$}",""+rs.getPageSize());
				endStr="<a href="+tmpJionStr+">"+fs.getEnd()+"</a>&nbsp;";
				tmpAll+="&nbsp;&nbsp;&nbsp;&nbsp;"+nextStr+endStr;
				
				
				
			}
			
			tmpAll+="&nbsp;&nbsp;&nbsp;&nbsp;"+fs.getCountPrefix()+"&nbsp;"+rs.getPageCount()+"&nbsp;"+fs.getSuffix();
				//String tmpj=StringUtil.strReplace(url,"{$$page$$}",""+(rs.getPage()+1));
				//tmpAll+="&nbsp;&nbsp;"+fs.getJumpPrefix()+fs.getPrefix()+"<input type=\"text\" name=\"page\" size=\"2\" maxlength=\"5\" style=\"text-align: center\" value=\"" + rs.getPage() + "\" onKeyPress=\"if (event.keyCode==13) window.location='" + joinStr + "pageSize="+rs.getPageSize()+"&page='" + "+this.value;\">"+fs.getSuffix();
			
		}
		
		
		return tmpAll;
	}

}
