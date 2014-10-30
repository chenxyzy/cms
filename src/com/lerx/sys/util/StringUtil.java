package com.lerx.sys.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lerx.sys.util.vo.HostInf;
import com.lerx.sys.util.vo.StrFilterUtil;

public class StringUtil {

	//替换回车换行
	public static String replaceEnter(String oldString)
	 {
//	   System.out.println("old:"+oldString.length());
	  Pattern pattern=Pattern.compile("(\r\n|\r|\n|\n\r)");
	//正则表达式的匹配一定要是这样，单个替换\r|\n的时候会错误
	  Matcher matcher=pattern.matcher(oldString);
	  String newString=matcher.replaceAll("");
//	  System.out.println("new:"+newString.length());
	  return newString;
	 }
	
	public static String randomString(int size) {
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
				'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
				'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	public static String md5(String s) {
		MessageDigest md5Digest = null;
		StringBuffer digestBuffer = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		digestBuffer = new StringBuffer();
		digestBuffer.setLength(0);
		byte abyte0[] = md5Digest.digest(s.getBytes());
		for (int i = 0; i < abyte0.length; i++)
			digestBuffer.append(toHex(abyte0[i]));

		return digestBuffer.toString();
	}

	
	public static String toHex(byte one) {
		String HEX = "0123456789ABCDEF";
		char[] result = new char[2];
		result[0] = HEX.charAt((one & 0xf0) >> 4);
		result[1] = HEX.charAt(one & 0x0f);
		String mm = new String(result);
		return mm;
	}

	// 将null转为空字符串
	public static String nullFilter(String var) {
		if (var == null) {
			return "";
		} else {
			return var;
		}

	}

	// 将字符串相乘累加
	public static String countStr(int n, String str) {
		if (n <= 0) {
			return "";
		} else {
			String endStr = "";
			for (int i = 1; i <= n; i++) {
				endStr += str;
			}
			return endStr;
		}
	}

	// 字符串替换
	public static String strReplace(String strSource, String strFrom,
			String strTo) {
		// 如果要替换的子串为空，则直接返回源串
		if (strSource == null) {
			return null;
		}
		if (strFrom == null || strFrom.equals("")) {
			return strSource;
		}

		String strDest = "";
		// 要替换的子串长度
		int intFromLen = strFrom.length();
		int intPos;
		// 循环替换字符串
		// System.out.println("strSource:"+strSource);
		// System.out.println("strFrom:"+strFrom);
		if (strSource != null) {
			while ((intPos = strSource.indexOf(strFrom)) != -1) {
				// 获取匹配字符串的左边子串
				strDest = strDest + strSource.substring(0, intPos);
				// 加上替换后的子串
				strDest = strDest + strTo;
				// 修改源串为匹配子串后的子串
				strSource = strSource.substring(intPos + intFromLen);
			}
			// 加上没有匹配的子串
			strDest = strDest + strSource;
		}

		// 返回
		return strDest;
	}

	public static boolean emailTest(String email) {

		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		boolean b = m.matches();
		return b;
	}

	public static String escapeUrl(String url, int act) {
		if (act == 1) {
			url = strReplace(url, "?", "@");
			url = strReplace(url, "&", "^");
			url = strReplace(url, "=", "~");
		} else {
			url = strReplace(url, "@", "?");
			url = strReplace(url, "^", "&");
			url = strReplace(url, "~", "=");
		}
		return url;
	}

	public static String uuidStr() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
		return s;
	}

	public static String filterByHtmlLabel(String htmlStr,String label){
		if (htmlStr == null) {
			return htmlStr;
		}
		String regEx_style = "<[\\s]*?"+label+"[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?"+label+"[\\s]*?>";
		java.util.regex.Pattern pStyle;
		java.util.regex.Matcher mStyle;
		pStyle = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		mStyle = pStyle.matcher(htmlStr);
		htmlStr = mStyle.replaceAll(""); // 过滤style标签
		return htmlStr;
	}
	public static String htmlFilter(String htmlStr) {
		if (htmlStr == null) {
			return htmlStr;
		}
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		String regEx_html = "<[^>]+>";
		String regEx_space = "<.+?>|&nbsp;|//s";

		java.util.regex.Pattern pScript;
		java.util.regex.Matcher mScript;
		java.util.regex.Pattern pStyle;
		java.util.regex.Matcher mStyle;
		java.util.regex.Pattern pHtml;
		java.util.regex.Matcher mHtml;

		pScript = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		mScript = pScript.matcher(htmlStr);
		htmlStr = mScript.replaceAll(""); // 过滤script标签

		pStyle = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		mStyle = pStyle.matcher(htmlStr);
		htmlStr = mStyle.replaceAll(""); // 过滤style标签

		pHtml = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		mHtml = pHtml.matcher(htmlStr);
		htmlStr = mHtml.replaceAll(""); // 过滤html标签

		pHtml = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		mHtml = pHtml.matcher(htmlStr);
		htmlStr = mHtml.replaceAll(""); // 过滤网页空格及其它标签 本方法可能与上面重复。

		// htmlStr=strReplace(htmlStr,"&nbsp;","");

		return htmlStr;

	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static StrFilterUtil filterStr(String source, String filterStr, boolean rep) {
		StrFilterUtil sfu = new StrFilterUtil();
		sfu.setStr(source);
		sfu.setRep(rep);
		sfu.setFound(false);
		sfu.setCon(true);
		if (source == null || source.trim().equals("") || filterStr == null || filterStr.trim().equals("")) {
			return sfu;
		}
		String[] sArray = filterStr.split(",");
		if (sArray.length>0){
			for (int step = 0; step < sArray.length; step++) {
				if (source.indexOf(sArray[step]) != -1) {
					sfu.setFound(true);
					if (!rep) {
						source=null;
						sfu.setCon(false);
						break;
					} else {
						source = strReplace(source, sArray[step], "*");
					}
				}
			}
		}
		sfu.setStr(source);

		return sfu;
	}

	public static StrFilterUtil filterStrWithHtml(String source, String filterStr,
			boolean rep) {
		StrFilterUtil sfu=new StrFilterUtil();
		sfu.setStr(source);
		sfu.setRep(rep);
		sfu.setFound(false);
		sfu.setCon(true);
		if (source == null) {
			return sfu;
		}
		sfu = filterStr(source, filterStr, rep);
		source=sfu.getStr();
		if (source != null) {
			source=htmlFilter(source);
		} 
		sfu.setStr(source);
		return sfu;
	}

	public static String timeCustomReplace(String sourStr, String pre,
			Timestamp t) {
		if (sourStr == null || sourStr.trim().equals("")) {
			return null;
		}
		int poss = sourStr.indexOf("{$$" + pre + "@");
		if (poss == -1) {
			return sourStr;
		}
		int pose = sourStr.indexOf("$$}", poss);
		String forEndStr = sourStr.substring(poss, pose + 3);
		if (forEndStr.trim().equals("")) {
			forEndStr = strReplace(sourStr, forEndStr, "");
		} else {
			DateFormat f = new SimpleDateFormat(forEndStr.substring(
					4 + pre.length(), forEndStr.length() - 3));
			if (t == null) {
				forEndStr = strReplace(sourStr, forEndStr, "");
			} else {
				forEndStr = strReplace(sourStr, forEndStr, f.format(t));
			}
		}
		return forEndStr;
	}

	public static String htmlSpecialCharsForKE(String str) {
		str = nullFilter(str);
		if (!str.trim().equals("")) {
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\"", "&quot;");
		}

		return str;
	}

	public static String covIntToStr(int n, int l) {
		String tmp = "" + n;
		if (tmp.length() >= l) {
			return tmp;
		} else {
			int emp = l - tmp.length();
			String empStr = countStr(emp, "0");
			tmp = empStr + tmp;
			return tmp;
		}
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static int byteLen(String val) {
		int len = 0;
		for (int i = 0; i < val.length(); i++) {
			if (isChinese(val.charAt(i))) { // 全角
				len += 2;
			} else {
				len += 1;
			}
		}
		return len;
	}

	public static String cutByte(String val, int len) {
		if (byteLen(val) <= len) {
			return val;
		}
		char s;
		String str = "", str2 = "";
		int i = 0, end = 0;

		while (end < len) {
			s = val.charAt(i);
			str2 += s;
			if (byteLen(str2) <= len) {
				str += s;
			}

			i++;
			end++;
			if (isChinese(s)) {
				end++;
			}
		}

		return str;
	}

	/**
	 * 
	 * @param str
	 *            需要过滤的字符串
	 * @return
	 * @Description:过滤数字以外的字符
	 */
	public static String filterUnNumber(String str) {
		// 只允数字
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		// 替换与模式匹配的所有字符（即非数字的字符将被""替换）
		return m.replaceAll("").trim();

	}

	// 转义
	public static String escape(String str) {
		if (str == null) {
			return str;
		}
		// str=strReplace(str, "&", "&amp;");
		str = strReplace(str, "\"", "&quot;");
		str = strReplace(str, " ", "&nbsp;");
		str = strReplace(str, "×", "&times;");
		str = strReplace(str, "÷", "&divide;");
		str = strReplace(str, "<", "&lt;");
		str = strReplace(str, ">", "&gt;");
		str = strReplace(str, "©", "&copy;");
		str = strReplace(str, "®", "&reg;");
		// str=strReplace(str, "™", "™");
		return str;
	}

	// 判断字符串中字符数值相加后的奇偶性
	public static int bitForStrCharsAdd(String str) {
		int n = 0, nOfChar, len;
		len = str.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			nOfChar = c;
			n += nOfChar;
		}
		// System.out.println("奇偶数：" + n % 2);
		return n % 2;
	}

	// 只字符串的奇数位或偶数位字符相加成一字符串
	public static String strAtBit(String str, int bit) {
		char c;
		String s = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (bit == 0) {
				if ((i % 2) == 0) {
					s += String.valueOf(c);
				}
			} else {
				if ((i % 2) == 1) {
					s += String.valueOf(c);
				}
			}
		}
		return s;
	}

	public static String urlEncoder(String url, String charSet)
			throws UnsupportedEncodingException {
		// System.out.println("转换前mediaUrl:"+url);
		url = strReplace(url, "/", "_____a__a_____");
		url = strReplace(url, ":", "_____b__b_____");
		url = URLEncoder.encode(url, charSet);
		url = strReplace(url, "_____a__a_____", "/");
		url = strReplace(url, "_____b__b_____", ":");
		url = url.toLowerCase();
		// System.out.println("转换后mediaUrl:"+url);

		return url;
	}
	
//	public static char findCharAtPos(String str,int intPos){
//		String s=str.substring(intPos-1, intPos);
//		return s.charAt(intPos);
//	}

	// 从字符串中提取主机名
	public static HostInf findHostInStr(String url) {
		if (url == null || url.trim().equals("")) {
			return null;
		} else {
			url=url.trim();
			if (url.indexOf(":")==-1){
				return null;
			}else{
				String[] filedArray=url.split(":");
				if (filedArray.length<2 || filedArray.length>3){ //分隔:后应有两段以上
					return null;
				}else{
					HostInf hi=new HostInf();
					String host=null,protocol=null;
					int port=0;
					protocol=filedArray[0];
					
					
					if (filedArray.length==2){
						url=filedArray[1];
						if (url!=null && !url.trim().equals("")){
							url=StringUtil.strReplace(url, "//", "");
						}
						port=80;
						filedArray = url.split("/");
						if (filedArray.length > 0) {
							host = filedArray[0];
							if (host.length()>0){
								host=host.trim();
							}
						}
					}else{
						host = filedArray[1];
						if (host!=null && !host.trim().equals("")){
							host=StringUtil.strReplace(host, "//", "");
						}
						url=filedArray[2];
						filedArray = url.split("/");
						if (filedArray.length > 0) {
							port = Integer.valueOf(filedArray[0]);
							
						}
					}
					if (port==0){
						port=80;
					}
					hi.setHost(host);
					hi.setPort(port);
					hi.setProtocol(protocol);
					if (hi.getHost()==null || hi.getHost().trim().equals("") || hi.getProtocol()==null){
						return null;
					}else{
						return hi;
					}
					
					
					
					
				}
				
			}
			
		}
	}

}
