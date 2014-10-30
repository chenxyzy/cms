package com.lerx.sys.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	public static String getRealRemotIP(HttpServletRequest request) {
		String strXRealIP, strRemoteAddr;
		strXRealIP = request.getHeader("X-Real-IP");
		strRemoteAddr = request.getRemoteAddr();

		String strReturnIP = "";
		if (strXRealIP == null) {
			strXRealIP = "";
		}

		if (strRemoteAddr == null) {
			strRemoteAddr = "";
		}

		strXRealIP = strXRealIP.toLowerCase().trim();
		strRemoteAddr = strRemoteAddr.toLowerCase().trim();

		if (strXRealIP.equals("localhost") || strXRealIP.equals("127.0.0.1")) {
			strXRealIP = "";
		}

		if (strRemoteAddr.equals("localhost")
				|| strRemoteAddr.equals("127.0.0.1")) {
			strRemoteAddr = "";
		}

		if (strXRealIP.equals("") && !strRemoteAddr.equals("")) {
			strReturnIP = strRemoteAddr;
		} else if (!strXRealIP.equals("") && strRemoteAddr.equals("")) {
			strReturnIP = strXRealIP;
		} else if (!strXRealIP.equals("") && !strRemoteAddr.equals("")
				&& strXRealIP.equals(strRemoteAddr)) {
			strReturnIP = strRemoteAddr;
		} else if (!strXRealIP.equals("") && !strRemoteAddr.equals("")
				&& !strXRealIP.equals(strRemoteAddr)) {
			if (isInnerIP(strXRealIP) && !isInnerIP(strRemoteAddr)) {
				strReturnIP = strRemoteAddr;
			} else if (!isInnerIP(strXRealIP) && isInnerIP(strRemoteAddr)) {
				strReturnIP = strXRealIP;
			} else if (isInnerIP(strXRealIP) && isInnerIP(strRemoteAddr)) {
				strReturnIP = strXRealIP;
			} else {
				strReturnIP = strXRealIP;
			}

		}
		
		if (strReturnIP.trim().equals("") || strReturnIP.trim().equals("0:0:0:0:0:0:0:1")) {
			strReturnIP = "127.0.0.1";
		}
		return strReturnIP;
	}

	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		/**
		 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
		 * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
		 **/
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = isInner(ipNum, aBegin, aEnd)
				|| isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
				|| ipAddress.equals("127.0.0.1");
		return isInnerIp;
	}

	private static long getIpNum(String ipAddress) {
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);

		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}

	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}

	public static String ipFilter(String ip, int n) {
		String fip = "", tmp = "";
		String[] sArray = ip.split("\\.");
		for (int step = 0; step < sArray.length; step++) {
			tmp = sArray[step];
			if (step > (n - 1)) {
				tmp = "*";
			}
			if (step == 0) {
				fip += tmp;
			} else {
				fip += "." + tmp;
			}

		}
		return fip;

	}

	public static boolean isInRange(String ip, String ipRange) {
		if (ip == null || ip.trim().equals("")) {
			return false;
		}
		// 如果ip范围为空，说明无限制
		if (ipRange == null || ipRange.trim().equals("")) {
			return true;
		}

		// 取当前IP的数值--curIpNum
		long curIpNum = getIpNum(ip);
		//
		String ipCol;
		long ipNumS, ipNumE;
		String[] ipRangeArray = ipRange.trim().split(",");
		for (int j = 0; j < ipRangeArray.length; j++) {
			ipCol = "";
			ipCol = ipRangeArray[j];
			if (ipCol.indexOf("-") == -1) {
				ipNumS = getIpNum(ipCol);
				if (curIpNum == ipNumS) {
					return true;
				}
				// ipNumS=
			} else {
				String[] ipRangeTmp = ipCol.trim().split("-");
				ipNumS = getIpNum(ipRangeTmp[0]);
				ipNumE = getIpNum(ipRangeTmp[1]);
				if (ipNumS < ipNumE) {
					if (curIpNum >= ipNumS && curIpNum <= ipNumE) {
						return true;
					}
				} else if (ipNumS > ipNumE) {
					if (curIpNum >= ipNumE && curIpNum <= ipNumS) {
						return true;
					}
				} else {
					if (curIpNum == ipNumE) {
						return true;
					}
				}

			}
		}

		return false;

	}
	
//	public static String getLocalHostMac(){
//		return null;
//	}

	public static String getLocalHostIp() throws UnknownHostException {
		InetAddress localhost = InetAddress.getLocalHost();
		return localhost.getHostAddress();
	}

	public static String getExternalIpAddress() {
		return ExternalIpAddressFetcher.getIP();
	}
}
