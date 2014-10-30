package com.lerx.sys.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

public class SrvInf {

	public static String srvUrl(HttpServletRequest request, String hostName,
			int port) {
		String portStr, rStr;
		
		if (hostName==null || hostName.trim().equals("")) {
			hostName = request.getServerName();
		}
		
		hostName = hostName.trim();
		// System.out.println("---ContextPath:"+request.getContextPath());
		switch (port) {
		case 0:
			if (request.getServerPort() == 80) {
				portStr = "";
			} else {
				portStr = ":" + request.getServerPort();
			}

			break;
		case 80:
			portStr = "";
			break;
		default:
			portStr = ":" + port;
			break;
		}

//		if (!request.getServerName().equals(hostName)) {
//			if (hostName==null || hostName.trim().equals("")) {
//				hostName = request.getServerName();
//			}
//		}
		rStr = request.getScheme() + "://" + hostName + portStr
				+ request.getContextPath();
		return rStr;
	}
	
	public static String srvUrlNoPath(HttpServletRequest request, String hostName,
			int port) {
		String portStr, rStr;
		hostName = hostName.trim();
		// System.out.println("---ContextPath:"+request.getContextPath());
		switch (port) {
		case 0:
			if (request.getServerPort() == 80) {
				portStr = "";
			} else {
				portStr = ":" + request.getServerPort();
			}

			break;
		case 80:
			portStr = "";
			break;
		default:
			portStr = ":" + port;
			break;
		}

		if (!request.getServerName().equals(hostName)) {
			if (hostName==null || hostName.trim().equals("")) {
				hostName = request.getServerName();
			}
		}
		rStr = request.getScheme() + "://" + hostName + portStr
				+ request.getContextPath();
		return rStr;
	}
	
	public static String obtainHostName(HttpServletRequest request,String hostName){
		if (hostName!=null){
			hostName = hostName.trim();
		}
		
		if (!request.getServerName().equals(hostName)) {
			if (hostName==null || hostName.trim().equals("")) {
				hostName = request.getServerName();
			}
		}
		return hostName;
	}

	public static String readSecStr(HttpServletRequest request,
			String secFileName) throws IOException {
		String separator, fe, realPath;
		String tempString = null;
		realPath = request.getSession().getServletContext().getRealPath("/")
				+ "WEB-INF";
		if (File.separator.equals("/")) {
			separator = File.separator;
		} else {
			separator = "\\";
		}
		fe = realPath + separator + secFileName;
		File f = new File(fe);
		if (f.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(f));

			tempString = reader.readLine();
			reader.close();
		}else{
			return null;
		}

		return tempString;

	}

	public static boolean saveSecStr(HttpServletRequest request,String hostName,
			String secFileName,boolean lock) throws IOException {
		String r=readSecStr(request,secFileName);
		if (r==null || r.trim().equals("")){
			String separator, fe, realPath,secStr;
			
			secStr=StringUtil.uuidStr();
			
			realPath = request.getSession().getServletContext().getRealPath("/")
					+ "WEB-INF";
			if (File.separator.equals("/")) {
				separator = File.separator;
			} else {
				separator = "\\";
			}
			fe = realPath + separator + secFileName;
			File f = new File(fe);
			boolean con;
			if (f.exists()){
				if (lock){
					con=false;
				}else{
					con=true;
					f.delete();
				}
				
			}else{
				con=true;
			}
			if (con){
				FileOutputStream o;
				try {
					o = new FileOutputStream(f);
//					o.write(secStr.getBytes("GBK"));
					o.write(secStr.getBytes());
				  	o.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

			}
			
		  	
			
			return true;
		}else{
			return false;
		}
		
	}

//	// 获取mac
//	private static String getMac(String sip, int mod) {
//		NetworkInterface ni;
//		String curMac = null;
//		try {
//			Enumeration<NetworkInterface> el = NetworkInterface
//					.getNetworkInterfaces();
//			while (el.hasMoreElements()) {
//				ni = el.nextElement();
//				byte[] mac = ni.getHardwareAddress();
//				if (mac == null)
//					continue;
//
//				StringBuilder builder = new StringBuilder();
//				StringBuilder builderWithoutSplit = new StringBuilder();
//				for (byte b : mac) {
//					builder.append(hexByte(b));
//					builderWithoutSplit.append(hexByte(b));
//					builder.append("-");
//				}
//				builder.deleteCharAt(builder.length() - 1);
//				if (ni.getInterfaceAddresses().size() > 0) {
//
//					List<InterfaceAddress> ifal = ni.getInterfaceAddresses();
//					for (InterfaceAddress ifa : ifal) {
//						if (ifa.getAddress().getHostAddress().trim()
//								.equals(sip)) {
//							// System.out.println("mac:"+builder);
//							// System.out.println("mac:"+builderWithoutSplit);
//							// curMac=builder;
//							if (mod == 1) {
//								curMac = builder.toString();
//							} else {
//								curMac = builderWithoutSplit.toString();
//							}
//						}
//					}
//				}
//
//			}
//		} catch (Exception exception) {
//			return null;
//		}
//
//		return curMac.trim().toLowerCase();
//	}
//
//	private static String hexByte(byte b) {
//		String s = "000000" + Integer.toHexString(b);
//		return s.substring(s.length() - 2);
//	}

}
