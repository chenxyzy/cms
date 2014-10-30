package com.lerx.sys.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

public class SecCheck {
	public static boolean check(ActionSupport as,HttpServletRequest request,String secStr,String randKey){
		boolean con = true;
		if (secStr==null || secStr.trim().length()!=32 || randKey==null || randKey.trim().length()!=10){
			con=false;
		}else{
			try {
				String hostSecComStr;
				String secStrFromFile=SrvInf.readSecStr(request, as.getText("lerx.hostSecFile"));
				hostSecComStr=StringUtil.md5(secStrFromFile.concat(randKey)).toLowerCase();
				if (!hostSecComStr.trim().toLowerCase().equals(secStr)){
					con=false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
