package com.lerx.site.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.PropertiesUtil;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.vo.PropertiesFileInf;
import com.lerx.sys.util.vo.SiteSecSetUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SiteUtil {
	public static boolean secAuto(SiteSecSetUtil sssu) throws FileNotFoundException, IOException{
		SiteInfo site=sssu.getSite();
		HttpServletRequest request = sssu.getRequest();
		ActionSupport as = sssu.getAs();
		boolean fin=true;
		
		String host=site.getHost(),hostSecFile;
		hostSecFile=as.getText("lerx.hostSecFile");
		if (hostSecFile==null || hostSecFile.trim().equals("lerx.hostSecFile")){
			hostSecFile="curLerxHost.jsp";
		}
		String secStr=SrvInf.readSecStr(request, hostSecFile);
		if (secStr==null || secStr.trim().equals("")){
			if (!site.getHost().trim().equals(as.getText("lerx.host.current").trim())){
				String trueFolder=request.getSession().getServletContext().getRealPath("");
				PropertiesFileInf pf=new PropertiesFileInf();
				pf.setFile(4);
				pf.setLocal(as.getLocale().toString());
				pf.setPath(trueFolder);
				
			}
			
			
			
			
			
//			System.out.println(host);
			boolean lock=false;
			
			if (as.getText("lerx.lockSecFile").trim().equalsIgnoreCase("true")){
				lock=true;
			}
			if (!lock){
				if (!site.getHost().trim().equals(as.getText("lerx.host.current").trim())){
					String trueFolder=request.getSession().getServletContext().getRealPath("");
					PropertiesFileInf pf=new PropertiesFileInf();
					pf.setFile(4);
					pf.setLocal(as.getLocale().toString());
					pf.setPath(trueFolder);
					PropertiesUtil.updateProperties(pf, "lerx.host.current", site.getHost());
				}
			}
			try {
				SrvInf.saveSecStr(request, host, hostSecFile,lock);
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fin=false;
			}
		}
		
		
		return fin;
	}
}
