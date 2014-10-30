package com.lerx.admin.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.lerx.sys.util.IpUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class AdminUtil {
	public static boolean checkAdmin(ActionSupport actionSupport,String hostName,
			HttpServletRequest request) {

		String safeAdminSessionStr = actionSupport.getText(
				"lerx.sessionPrefixOfAdminAuthentication").trim();
		safeAdminSessionStr = safeAdminSessionStr.replace("servername",
				hostName);
		boolean con = true;
		String safeIp = actionSupport.getText("lerx.safe.admin.ip");
		if (safeIp != null
				&& !safeIp.trim().equals("")
				&& !safeIp.trim().equalsIgnoreCase("all")
				&& !safeIp.trim().equals("lerx.safe.admin.ip")
				&& !IpUtil.isInRange(IpUtil.getRealRemotIP(request).trim(),
						safeIp)) {
			con = false;
		}
		if (request.getSession().getAttribute(safeAdminSessionStr) == null
				|| !request.getSession().getAttribute(safeAdminSessionStr).equals("passed") || !con) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean checkAdmin(String hostName,
			HttpServletRequest request) {

		String safeAdminSessionStr = readRec(
				"lerx.sessionPrefixOfAdminAuthentication").trim();
		safeAdminSessionStr = safeAdminSessionStr.replace("servername",
				hostName);
		boolean con = true;
		String safeIp = readRec("lerx.safe.admin.ip");
		if (safeIp != null
				&& !safeIp.trim().equals("")
				&& !safeIp.trim().equalsIgnoreCase("all")
				&& !safeIp.trim().equals("lerx.safe.admin.ip")
				&& !IpUtil.isInRange(IpUtil.getRealRemotIP(request).trim(),
						safeIp)) {
			con = false;
		}
		if (request.getSession().getAttribute(safeAdminSessionStr) == null
				|| !request.getSession().getAttribute(safeAdminSessionStr).equals("passed") || !con) {
			return false;
		} else {
			return true;
		}
	}

	public static String findCurAdmin(ActionSupport actionSupport,String hostName,
			HttpServletRequest request) {

		String safeAdminSessionStr = actionSupport.getText(
				"lerx.sessionPrefixOfAdminAuthentication").trim();
		safeAdminSessionStr = safeAdminSessionStr.replace("servername",
				hostName);
		String admin = (String) request.getSession().getAttribute(safeAdminSessionStr + "_adminName");
		return admin;
	}
	
	private static String readRec(String key) {
		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
	}
}
