package com.lerx.sys.util;

import java.io.IOException;

import com.lerx.sys.util.vo.SecFilePosition;

/*
 * 加密解密字符串
 * java本身有一套算法，但导入开发环境时问题多多，自己写一个，能用就行
 * 读取站点密钥文件后进行处理，保证每个站点的算法都不会相同，采用对等位移方式
 * @author lzh
 */
 
public class DesUtils {
	
	
	/*
	 * 加密
	 */
	public static String encrypt(String source,SecFilePosition sfp) throws IOException{
		return encryptStr(source,byteNum(sfp));
	}
	
	/*
	 * 解密
	 */
	public static String decrypt(String source,SecFilePosition sfp) throws IOException{
		return decryptStr(source,byteNum(sfp));
	}
	
	private static String encryptStr(String source,Integer[] in){
		int c,l;
		l=source.length();
		int[] ic=new int[l];
		for (int i=0;i<l;i++){
			c=(int)source.charAt(i)+in[i%10];
			ic[i]=c;
		}
		String r="";
		for (int m=0;m<ic.length;m++){
			r += (char) ic[m];
		}
		return r;
	}
	
	private static String decryptStr(String source,Integer[] in){
		int c,l;
		l=source.length();
		int[] ic=new int[l];
		for (int i=0;i<l;i++){
			c=(int)source.charAt(i)-in[i%10];
			ic[i]=c;
		}
		String r="";
		for (int m=0;m<ic.length;m++){
			r += (char) ic[m];
		}
		return r;
	}
	
	//返回一个长度为10的数组
	private static Integer[] byteNum(SecFilePosition sfp) throws IOException{
		String secWords = SrvInf.readSecStr(sfp.getRequest(), sfp.getSecFileName());
		if (secWords==null){
			secWords="";
		}
		secWords=secWords.trim();
		if (secWords.length()<32){
			return null;
		}
		
		Integer[] in=new Integer[10];
		
		for (int i=0;i<=9;i++){
			int a = (int)secWords.charAt(i);
			int b = (int)secWords.charAt(i+10);
			int c = (int)secWords.charAt(i+20);
			int d = 0 ;
			if (i==0){
				d=(int)secWords.charAt(30);
			}
			if (i==9){
				d=(int)secWords.charAt(31);
			}
			int e = a + b +c + d;
			e = e%10;
			in[i]=e;
		}
		
		return in;
	}
}
