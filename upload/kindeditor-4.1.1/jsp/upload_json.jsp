<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*" %>
<%@page	import="java.io.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="com.lerx.sys.util.TimeUtil"%>
<%@ page import="com.lerx.sys.util.StringUtil"%>
<%@ page import="com.lerx.sys.util.CookieUtil"%>
<%@ page import="com.lerx.sys.util.vo.UserCookie"%>
<%@ page import="com.lerx.sys.service.SysUtilAction"%>
<%@ page import="com.lerx.sys.util.vo.CookieDoModel"%>
<jsp:useBean id="sysUtil" scope="page"
	class="com.lerx.sys.service.SysUtilAction" />
<%

/**
 * KindEditor JSP
 * 
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 * 
 */

 CookieDoModel cdm=new CookieDoModel();
 cdm.setPrefix(sysUtil.findResource("lerx.prefixOfCookieForLogin"));
 cdm.setEncodingCode(sysUtil.findResource("lerx.charset").trim());
 cdm.setHost(sysUtil.findResource("lerx.host.current").trim());
 cdm.setHostSecFile(sysUtil.findResource("lerx.hostSecFile").trim());
 cdm.setRequest(request);
 cdm.setActionSupport(sysUtil.ac());
 UserCookie uc = CookieUtil.queryNoAc(cdm);
 
 if (uc != null && uc.getUsername() != null
			&& !uc.getUsername().trim().equals("")) {
	 String uploadPath = sysUtil.findResource("lerx.uploadPath");
		String url = request.getContextPath()+"/"+uploadPath;

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
		
		
		
	//文件保存目录路径
	//String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";
	 String savePath = filePath.toString();

	 //文件保存目录URL
	 
	// String saveUrl  = request.getContextPath() + "/attached/";
	 String saveUrl  = url;
	 //定义允许上传的文件扩展名
	 HashMap<String, String> extMap = new HashMap<String, String>();
	 extMap.put("image", "gif,jpg,jpeg,png,bmp");
	 extMap.put("flash", "swf,flv");
	 extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	 extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

	 //最大文件大小
	

	 response.setContentType("text/html; charset=UTF-8");

	 if(!ServletFileUpload.isMultipartContent(request)){
	 	out.println(getError("请选择文件。"));
	 	return;
	 }
	 //检查目录
	 //File uploadDir = new File(savePath);
	 if(!filePath.isDirectory()){
	 	out.println(getError("上传目录不存在。"+savePath));
	 	return;
	 }
	 //检查目录写权限
	 if(!filePath.canWrite()){
	 	out.println(getError("上传目录没有写权限。"));
	 	return;
	 }

	 String dirName = request.getParameter("dir");
	 if (dirName == null) {
	 	dirName = "image";
	 }
	

	 FileItemFactory factory = new DiskFileItemFactory();
	 ServletFileUpload upload = new ServletFileUpload(factory);
	 upload.setHeaderEncoding("UTF-8");
	 List items = upload.parseRequest(request);
	 Iterator itr = items.iterator();
	 
	 
	 while (itr.hasNext()) {
	 	FileItem item = (FileItem) itr.next();
	 	String fileName = item.getName();
	 	long fileSize = item.getSize();
	 	
	 	if (!item.isFormField()) {
	 		
	 		String newName = null;
	 		//检查扩展名
			String extType;
			int p = fileName.lastIndexOf(".");
			if (p > 0) {
				extType = fileName.substring(p + 1, fileName.length());
			} else {
				extType = "";
			}
			extType = extType.toLowerCase();
			
			String pre = "lerx.uploadMaxKbSize." + extType;
			String returnText = sysUtil.findResource(pre);
			if (returnText.trim().equals(pre)
					|| returnText.trim().equals("")) {
				returnText = sysUtil
						.findResource("lerx.uploadMaxKbSize.default");
			}
			long maxSize = Integer.valueOf(returnText);
			if (maxSize != 0 && item.getSize() > maxSize * 1000) {
				out.println(getError(sysUtil
						.findResource("lerx.fail.upload.tooBig")
						+ maxSize
						+ "KB"));
				return;
			}
			
	 		String timeMillis = TimeUtil
			.createCurrTimestampStr("HHmmssSSS");

			timeMillis += "_"+uc.getUserId();
			
			newName = timeMillis
			+ fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
		 
			url += "/" + newName;
	 		//检查文件大小
	 		
	 		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	 		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
	 			out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
	 			return;
	 		}

	 		
	 		String newFileName = newName;
	 		
	 		
	 		try{
	 			File uploadedFile = new File(savePath, newFileName);
	 			item.write(uploadedFile);
	 		}catch(Exception e){
	 			out.println(getError("上传文件失败。"));
	 			return;
	 		}

	 		JSONObject obj = new JSONObject();
	 		obj.put("error", 0);
	 		//obj.put("url", saveUrl + newFileName);
	 		obj.put("url", url);
	 		out.println(obj.toJSONString());
	 	}
	 }
 }else{
	 out.println(getError("请先登录。"));
		
 }


%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>