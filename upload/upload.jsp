<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<jsp:useBean id="sysUtil" scope="page"
	class="com.lerx.sys.service.SysUtilAction" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<title>上传文件</title>
</head>
<style>
.sub_file input{ border:#ccc solid 1px; height:26px;}
.sub_but input{ border:#775631 solid 1px; height:25px; background:#F93}
</style>
<body>
<%
String cookieDomain=sysUtil.findResource("lerx.cookieDomain");
if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
%>
<script type="text/javascript">
try
{
document.domain = "<%=cookieDomain%>";
}
catch(e)
{
}
</script>
<%	
}
%>
<form name="upload" action="upload.action?col=<s:property value="#parameters.col"/>" method="post" enctype="multipart/form-data">
	<span class="sub_file"><input type="file" name="f.file"></span><span class="sub_but"><input type="submit" value="上传" onclick="uping();" /></span>
</form>
<div id="upImg"><img src="images/loading.gif" />正在上传，请稍候……</div>
<script type="text/javascript">
$("#upImg").hide();
function uping(){
	$("#upImg").show();
 }
</script>
</body>
</html>