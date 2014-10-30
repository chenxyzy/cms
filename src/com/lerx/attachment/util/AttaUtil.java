package com.lerx.attachment.util;

import java.io.UnsupportedEncodingException;

import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.HostInf;
import com.lerx.sys.vo.ExternalHostCharset;
import com.opensymphony.xwork2.ActionSupport;

public class AttaUtil {
	public static String encoder(IExternalHostCharsetDao externalHostCharsetDaoImp, ActionSupport as,String url,int type) throws UnsupportedEncodingException{
		HostInf host=StringUtil.findHostInStr(url);
		if (as.getText("lerx.attaURLEncoder").trim().equalsIgnoreCase("true") && host!=null){
			ExternalHostCharset ehc=externalHostCharsetDaoImp.findByHostAndPortAndType(host.getHost(),host.getPort(), type);
			if (ehc==null){
				return url;
			}else{
				String charset=ehc.getCharset();
				if (charset==null || charset.trim().equals("")){
					return url;
				}else{
					url=StringUtil.urlEncoder(url, charset);
				}
				
			}
		}
		return url;
	}
}
