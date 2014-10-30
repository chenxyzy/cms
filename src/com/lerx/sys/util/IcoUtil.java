package com.lerx.sys.util;

import com.opensymphony.xwork2.ActionSupport;

public class IcoUtil {
	public static String read(ActionSupport as, String flag, int mod) {
		
		
		/*
		 * mod  
		 * 0 直接读
		 * 1取前部分
		 * 2取后部分
		 */
		
		String reStr;
		flag=flag.trim();
		String icoFile = as.getText("lerx.bbs.ico." + flag);
		
//		System.out.println("icoFile:"+icoFile);
		
		icoFile=icoFile.trim();
		String[] sArray;
		
		switch (mod) {
		case 1:
			sArray = icoFile.split("\\|");
			if (sArray.length>1){
				reStr=sArray[0];
			}else{
				reStr="";
			}
			
			break;
		case 2:
			sArray = icoFile.split("\\|");
			if (sArray.length>1){
				reStr=sArray[1];
			}else{
				reStr="";
			}
			break;
		default:
			reStr=icoFile;
			break;

		}
//		System.out.println("reStr:"+reStr);
		return reStr;
	}
}
