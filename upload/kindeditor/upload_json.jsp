<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@page
	import="java.io.*,org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper,java.util.concurrent.locks.*"%>
<%@ page import="com.lerx.sys.util.TimeUtil"%>
<%@ page import="com.lerx.sys.util.StringUtil"%>
<%@ page import="com.lerx.sys.util.CookieUtil"%>
<%@ page import="com.lerx.sys.util.vo.UserCookie"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="com.lerx.sys.service.SysUtilAction"%>
<%@ page import="com.lerx.sys.util.vo.CookieDoModel"%>
<jsp:useBean id="sysUtil" scope="page"
	class="com.lerx.sys.service.SysUtilAction" />
<%
CookieDoModel cdm=new CookieDoModel();
cdm.setPrefix(sysUtil.readResource("lerx.prefixOfCookieForLogin"));
cdm.setEncodingCode(sysUtil.readResource("lerx.charset").trim());
cdm.setHost(sysUtil.readResource("lerx.host.current").trim());
cdm.setHostSecFile(sysUtil.readResource("lerx.hostSecFile").trim());
cdm.setRequest(request);
cdm.setActionSupport(sysUtil.ac());


	//String hostName = sysUtil.findHostName();
	//String hostName = (String) request.getAttribute("hostName");
	//out.println("sdfasdf");
	UserCookie uc = CookieUtil.query(cdm);
	if (uc != null && uc.getUsername() != null
			&& !uc.getUsername().trim().equals("")) {
		String uploadPath = sysUtil.readResource("lerx.uploadPath");
		String url = uploadPath;

		String path = request.getSession().getServletContext()
				.getRealPath(uploadPath);

		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdir();
		}

		filePath = new File(filePath.toString()
				+ File.separator
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 2));
		url += "/"
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 2);
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		filePath = new File(filePath.toString()
				+ File.separator
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 1));
		url += "/"
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 1);
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		filePath = new File(filePath.toString()
				+ File.separator
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 0));
		url += "/"
				+ TimeUtil.getDateVar(
						(java.sql.Date) new java.sql.Date(System
								.currentTimeMillis()), 0);
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		//Struts2  请求 包装过滤器    
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名     
		String fileName = wrapper.getFileNames("imgFile")[0];
		//获得未见过滤器     

		File file = wrapper.getFiles("imgFile")[0];
		//out.println(getError("测试："+fileName+"||"+file.getName()+"||"+file.length()));
		//检查文件大小
		String extType;
		int p = fileName.lastIndexOf(".");
		if (p > 0) {
			extType = fileName.substring(p + 1, fileName.length());
		} else {
			extType = "";
		}
		extType = extType.toLowerCase();
		String pre = "lerx.uploadMaxKbSize." + extType;
		String returnText = sysUtil.readResource(pre);
		if (returnText.trim().equals(pre)
				|| returnText.trim().equals("")) {
			returnText = sysUtil
					.readResource("lerx.uploadMaxKbSize.default");
		}
		long maxSize = Integer.valueOf(returnText);
		if (maxSize != 0 && file.length() > maxSize * 1000) {
			out.println(getError(sysUtil
					.readResource("lerx.uploadFileTooBig")
					+ maxSize
					+ "KB"));
		} else {
			

			//----------- 重新构建上传文件名----------------------  
			
			final Lock lock = new ReentrantLock();
			String newName = null;
			lock.lock();
			try {
				//加锁为防止文件名重复   
				String timeMillis = TimeUtil
						.createCurrTimestampStr("HHmmssSSS");

				timeMillis += "_"+uc.getUserId();
				//timeMillis += uc.getUsername();
				newName = timeMillis
						+ fileName.substring(fileName.lastIndexOf("."),
								fileName.length());
			} finally {
				lock.unlock();
			}
			//------------ 锁结束 -------------    
			//获取文件输出流     
			uploadPath = filePath.toString();
			url += "/" + newName;
			FileOutputStream fos = new FileOutputStream(uploadPath
					+ "/" + newName);

			String newFileName = request.getContextPath() + "/"
					+ uploadPath + "/" + newName;
			byte[] buffer = new byte[1024];
			//获取内存中当前文件输入流     下一行发生错误
			InputStream in = new FileInputStream(file);
			try {
				int num = 0;
				while ((num = in.read(buffer)) > 0) {
					fos.write(buffer, 0, num);
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
			} finally {
				in.close();
				fos.close();
			}
			
			newFileName = request.getContextPath() + "/"
			+ url;
			
			//发送给KE     
			out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");
			out.println("<script type='text/javascript'>");
			out.println("parent.parent.KE.plugin['image'].insert('"
					+ wrapper.getParameter("id") + "','" + newFileName
					+ "','" + wrapper.getParameter("imgTitle") + "','"
					+ wrapper.getParameter("imgWidth") + "','"
					+ wrapper.getParameter("imgHeight") + "','"
					+ wrapper.getParameter("imgBorder") + "','"
					+ wrapper.getParameter("align") + "');</script>");
			out.println("</body></html>");

			//out.println(getError("测试："));

		}

	} else {
		out.println(getError(sysUtil
				.readResource("lerx.authenticationFailedAndToLogin")));
		return;
	}
%>
<%!private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}%>
