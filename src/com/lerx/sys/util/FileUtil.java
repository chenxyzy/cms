package com.lerx.sys.util;

import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import com.lerx.sys.util.vo.ReadFileArg;
import com.lerx.web.vo.FileReadArgs;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class FileUtil {
	
	String strHTML = null;

	public FileUtil() {
	}

	
	public static String readTempFile(FileReadArgs fra,String subFolder,String fileName) {
		String rootFolder;
		rootFolder=fra.getRootFolder();
		ReadFileArg rfv = new ReadFileArg();
		rfv.setAs(fra.getAs());
		rfv.setRequest(fra.getRequest());
		rfv.setRootFolder(rootFolder);
		rfv.setFileName(fileName);
		rfv.setSubFolder(subFolder);
		String txt = FileUtil.readFile(rfv);
		return txt;
	}
	
	//判断文件是否图像文件
	public static boolean imgFileCheck(String fileName,String imgFileEtcStr){
		String extType;
		int p = fileName.lastIndexOf(".");
		if (p > 0) {
			extType = fileName.substring(p+1, fileName.length());
		} else {
			extType = "";
			return false;
		}
		extType=extType.toLowerCase().trim();
		String[] sArray = imgFileEtcStr.split(",");
		for (int step=0;step<sArray.length;step++){
			if (extType.trim().equalsIgnoreCase(sArray[step].trim())){
				return true;
			}
			
		}
		return false;
	}
	
	// 抓取URL生成静态页面
	// String fromurl - 源URL
	// String StaticHTML - 生成的静态文件(含路径)
	public static boolean deleteFile(String path,String file){
		
		String separator,fe;
		if (File.separator.equals("/")){
			separator=File.separator;
		}else{
			separator="\\";
		}
		fe=path+separator+file;
		
		//System.out.println("删除文件："+fe);
		File f=new File(fe);
		if (f.exists()) {
			f.delete();
			return true;
		}else{
			return false;
		}
		
	}
	
	public static boolean createHtml(String fromurl, String StaticHTML,
			String filePath,String charset) throws InterruptedException {
//		System.out.println("fromurl:"+fromurl);
//		System.out.println("FilePath:"+filePath);
//		System.out.println("StaticHTML:"+StaticHTML);
		try {

			
			
			String realPath = "",separator;
			if (File.separator.equals("/")){
				separator=File.separator;
			}else{
				separator="\\";
			}
			filePath=StringUtil.strReplace(filePath,separator,"_	_");
			String[] pathName = filePath.split("_	_");
			for (int i = 0; i < pathName.length; i++) {
				if (!pathName[i].trim().equals("")) {
					if (i==0){
						if (File.separator.equals("/")){
							realPath += separator + pathName[i];
						}else{
							realPath += pathName[i];
						}
					}else{
						realPath += separator + pathName[i];
					}
					
					
					//System.out.println("路径测试"+i+"："+realPath);
					File fp = new File(
							realPath.toString());
					if (!fp.exists()) {
						fp.mkdir();
					}
				}

			}

			String strCreateDateTime = "";
			File f = new File(filePath, StaticHTML);
			if (f.exists()) {
				f.delete();
			}

			Date pd_now = new Date();
			java.text.SimpleDateFormat IndexHtmlDate = new java.text.SimpleDateFormat(
					"yyyy年M月d日 HH时mm分ss秒");

			strCreateDateTime += "<!--静态生成时间";
			strCreateDateTime += IndexHtmlDate.format(pd_now);
			strCreateDateTime += "-->";
			URL url = new URL(fromurl);
			URLConnection urlCon = url.openConnection();// 获得连接
			if (urlCon!=null){
				urlCon.setRequestProperty("Accept-Language", "zh-CN");
				urlCon.setRequestProperty("Accept-Charset", charset);
				
//				urlCon.getDefaultRequestProperty("Accept-Charset");
				urlCon.setConnectTimeout(30000);
//				BufferedReader br = new BufferedReader(new InputStreamReader(
//						urlCon.getInputStream()));
				BufferedReader br = new BufferedReader(new InputStreamReader(
						urlCon.getInputStream(),charset));
				filePath=StringUtil.strReplace(filePath,"_	_",separator);
//				System.out.println("建立文件："+filePath+ separator + StaticHTML);
				//下行有时有错
				FileOutputStream fw = new FileOutputStream(filePath+ separator + StaticHTML);
				
				String str = null,strn="\n";
				
				while ((str = br.readLine()) != null) {
//					System.out.println("第一行"+charset+"："+str);
//					System.out.println("第二行"+charset+"："+str.getBytes(charset));
					fw.write(str.getBytes(charset));
					fw.write(strn.getBytes());
				}

				fw.write(strCreateDateTime.getBytes(charset));
				fw.close();
				br.close();
				return true;
			}else{
				return false;
			}
			
			
			// conn.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		return false;

	}

	public static String formatFileName(ActionSupport as,Timestamp argTime, String fileNameFormat,String timeKey,
			String arg1,String arg2) throws InterruptedException {
		
//		System.currentTimeMillis();
		
		
		if (argTime!=null && argTime.getNanos()==0){
			Timestamp n=new Timestamp(System.currentTimeMillis());
			argTime.setNanos(n.getNanos());
			
		}
		
		String staticArticleFileNameFormat= as.getText(timeKey).trim();
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(staticArticleFileNameFormat);
		
		String fn= as.getText(fileNameFormat);
		if (argTime==null){
			fn = StringUtil.strReplace(fn,"$time$", formatter.format(System.currentTimeMillis()));
		}else{
			fn = StringUtil.strReplace(fn,"$time$", formatter.format(argTime));
		}
		
		
		if (arg1!=null){
			fn = StringUtil.strReplace(fn,"$arg1$", arg1);
		}
		if (arg2!=null){
			fn = StringUtil.strReplace(fn,"$arg2$", arg2);
		}
		fn=fn.trim();
		return fn;
	}
	
	/*
	 * 2.3.4版本中exeAndWait无法使用action的getText。。。写此补救 
	 */
	public static String formatFileName(HttpServletRequest request,Timestamp argTime, String fileNameFormat,String timeKey,
			String arg1,String arg2) throws InterruptedException {
		
//		System.currentTimeMillis();
		
		
		if (argTime!=null && argTime.getNanos()==0){
			Timestamp n=new Timestamp(System.currentTimeMillis());
			argTime.setNanos(n.getNanos());
			
		}
		
		String staticArticleFileNameFormat= readRec(timeKey).trim();
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(staticArticleFileNameFormat);
		
		String fn= readRec(fileNameFormat);
		if (argTime==null){
			fn = StringUtil.strReplace(fn,"$time$", formatter.format(System.currentTimeMillis()));
		}else{
			fn = StringUtil.strReplace(fn,"$time$", formatter.format(argTime));
		}
		
		
//		String fn = formatter.format(System.currentTimeMillis());
		if (arg1!=null){
			fn = StringUtil.strReplace(fn,"$arg1$", arg1);
		}
		if (arg2!=null){
			fn = StringUtil.strReplace(fn,"$arg2$", arg2);
		}
		fn=fn.trim();
		return fn;
		
		
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
	
	
	public static String readConfigFile(ActionSupport as,HttpServletRequest request,String fileName,String subFolder){
//				System.out.println("断点2");
				try {
					String separator, fe, realPath;
					String tempString = null,str;
					
					realPath = request.getSession().getServletContext().getRealPath("/")
							+ "WEB-INF";
					if (File.separator.equals("/")) {
						separator = File.separator;
					} else {
						separator = "\\";
					}
					if (subFolder!=null){
						subFolder=separator+subFolder;
					}else{
						subFolder="";
					}
					fe = realPath + separator +"conf" +subFolder+ separator + fileName;
//					System.out.println("fe:"+fe);
					File f = new File(fe);
					if (f.exists()) {
						tempString="";
						String strn="\n";
//						BufferedReader reader = new BufferedReader(new FileReader(f));
						BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
			                    f), as.getText("lerx.charset")));

						while ((str = reader.readLine()) != null) {
							tempString+=str+strn;
						}
//						tempString = reader.readLine();
						reader.close();
					}else{
						return null;
					}

					return tempString;
				} catch (IOException e) {
					return null;
				}
	}

	public static String readFile(ReadFileArg rfa){
	//				System.out.println("断点2");
		ActionSupport as=rfa.getAs();
		HttpServletRequest request=rfa.getRequest();
		String fileName=rfa.getFileName();
		String subFolder=rfa.getSubFolder();
		String rootFolder=rfa.getRootFolder();
		rootFolder = StringUtil.strReplace(rootFolder, "{$$contextPath$$}/", "");
		if (rootFolder==null || rootFolder.trim().equals("")){
			rootFolder="res";
		}
					try {
						String separator, fe, realPath;
						String tempString = null,str;
						
						realPath = request.getSession().getServletContext().getRealPath(rootFolder);
						if (File.separator.equals("/")) {
							separator = File.separator;
						} else {
							separator = "\\";
						}
						if (subFolder!=null){
							subFolder=separator+subFolder;
						}else{
							subFolder="";
						}
						fe = realPath + subFolder+ separator + fileName;
//						System.out.println("fe:"+fe);
						File f = new File(fe);
						if (f.exists()) {
							tempString="";
							String strn="\n";
	//						BufferedReader reader = new BufferedReader(new FileReader(f));
							BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
				                    f), as.getText("lerx.charset")));
	
							while ((str = reader.readLine()) != null) {
								tempString+=str+strn;
							}
	//						tempString = reader.readLine();
							reader.close();
						}else{
							return null;
						}
//						System.out.println("tempString:"+tempString);
						return tempString;
					} catch (IOException e) {
						return null;
					}
		}
	
//	private static String readRec(HttpServletRequest request,String recFile,String key){
//		String curPath=
//			request.getSession().getServletContext().getRealPath("");
//			curPath+=File.separator+"WEB-INF"+File.separator+"classes"+File.separator;
//		String value=CfgFile.read(curPath+"resources"+recFile+"_zh_CN.properties", key).trim();
//			
//		return value;
//	}
//	
	
}
