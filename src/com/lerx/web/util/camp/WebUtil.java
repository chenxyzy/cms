package com.lerx.web.util.camp;

import com.lerx.site.vo.SiteInfo;
import com.lerx.web.vo.WebElements;

public class WebUtil {
	public static boolean check(String u1,String u2){
		boolean r;
		if (u1!=null && u1.trim().equals("")){
			u1=null;
		}
		
		if (u2!=null && u2.trim().equals("")){
			u2=null;
		}
		
		if (u1==null && u2==null){
			r=true;
		}else if ((u1==null && u2!=null) || (u1!=null && u2==null)){
			r=false;
		}else{
			if (u1.trim().equalsIgnoreCase(u2)){
				r=true;
			}else{
				r=false;
			}
		}
		return r;
	}
	
	public static void viewsCheck(WebElements wel){
		SiteInfo s=wel.getSite();
		s.setViews(s.getViews()+1);
		if (wel.getRequest().getSession().isNew()){
			s.setNviews(s.getNviews()+1);
//			String requestUri=""+wel.getRequest().getRequestURL();
//			String queryString=wel.getRequest().getQueryString();
//			String refererURL=wel.getRequest().getHeader("Referer");
//			if (queryString!=null && !queryString.trim().equals("")){
//				requestUri+="?"+queryString;
//			}
//			System.out.println("refererURL:"+refererURL);
//			System.out.println("requestUri:"+requestUri);
//			if (!WebUtil.check(refererURL, requestUri)){
//				
//				s.setNviews(s.getNviews()+1);
//				
//				
//			}
		}
		
		wel.getSiteInfDaoImp().modify(s);
		
	}
}
