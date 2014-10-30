package com.lerx.sys.util;

import java.util.Locale;

import com.lerx.sys.util.vo.HotScore;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class SysUtil {
	
	public static String readRec(ActionSupport as,String key){
		if (as==null || as.getLocale()==null){
			return readRec(key);
		}else{
			return as.getText(key);
		}
	}
	public static String readRec(String key) {
		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
	}
	
	public static String ellipsis(ActionSupport as){
		String ellipsisChar=as.getText("lerx.charOfEllipsis");
		if (ellipsisChar==null || ellipsisChar.trim().equals("") || ellipsisChar.trim().equals("lerx.charOfEllipsis") || ellipsisChar.trim().equals("null")){
			return "";
		}else{
			return ellipsisChar.trim();
		}
	}
	
	
	//数字加密
	public static Long covValue(Long value,String key){
//		System.out.println("加密前："+value);
		int p=covStrToPos(key);
		if (p==0){
			p=6;
		}
//		System.out.println("加密位移值："+p);
		String valueStr=value.toString();
		int len = valueStr.length();
		String c,endC="6";
		int v;
		for (int i=0;i<len;i++){
			c=""+valueStr.charAt(i);
			v=Integer.valueOf(c);
			v+=p;
			v=v%10;
			endC+=v;
		}
//		System.out.println("加密后："+endC);
		return Long.valueOf(endC);
	}
	
	//数字解密
	public static Long reCovValue(Long value,String key){
//		System.out.println("解密前："+value);
		int p=covStrToPos(key);
		if (p==0){
			p=6;
		}
//		System.out.println("解密位移值："+p);
		String endC="",c,valueStr=value.toString();
		
		int len = valueStr.length();
		valueStr=valueStr.substring(1, len);
		len = valueStr.length();
		int v;
		for (int i=0;i<len;i++){
			c=""+valueStr.charAt(i);
			v=Integer.valueOf(c);
			v+=100;
			v-=p;
			v=v%10;
			endC+=v;
		}
//		System.out.println("解密后："+endC);
		return Long.valueOf(endC);
	}
	
	
	public static Long[] covValue(Long[] val,String key){
		for(int i=0;i<val.length;i++){
			val[i]=covValue(val[i],key);
		}

		return val;
	}
	
	public static Long[] reCovValue(Long[] val,String key){
		for(int i=0;i<val.length;i++){
			val[i]=reCovValue(val[i],key);
		}

		return val;
	}
	
	private static int covStrToPos(String key){
		
		int l=key.length();
		int c,v=0,m;
		for (int i=0;i<l;i++){
			c=(int)key.charAt(i);
			v+=c;
		}
		m=v%11;
		if (m==10){
			m=0;
		}
		return m;
	}
	
	public static HotScore hsValue(ActionSupport as){
		String str=as.getText("lerx.hotScore").trim();
		String[] sArray=null;
		HotScore hs=new HotScore();
		boolean err=false;
		if (str.length()>0){
			sArray = str.split("\\|");
			if (sArray.length<3){
				err=true;
			}else{
				int[] s ;
				s = new int[3] ;
				s[0]= Integer.valueOf(sArray[0]);
				s[1]= Integer.valueOf(sArray[1]);
				s[2]= Integer.valueOf(sArray[2]);
				hs.setValueE(s[0]);
				hs.setValueP(s[1]);
				hs.setValueV(s[2]);
			}
			
			
		}else{
			err=true;
		}
		
		if (err){
			hs.setValueE(60);
			hs.setValueP(20);
			hs.setValueV(20);
		}
		return hs;
		
	}
}
