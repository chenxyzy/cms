<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%response.setHeader("P3P","CP=CAO PSA OUR");%>
<jsp:useBean id="sysUtil" scope="page"
	class="com.lerx.sys.service.SysUtilAction" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
p{ text-align:center;}
.upstate{ font-size:16px; color:#c00;}
{font-size:16px; color:#666; }
.upadd input{ border:1px solid #ccc; height:24px; width:260px;}
p a{ display:inline-block; background:#FFC; padding:2px 12px; border:1px solid #E8B659; margin:0 5px; font-size:14px;}
p a:link{text-decoration:none;color:#333;} 
p a:visited{text-decoration:none;color:#333;} 
p a:hover{text-decoration:none;color:#f00;} 
p a:active{text-decoration:none;color:#333;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery/1.7.2/jquery-1.7.2.min.js"></script>

</head>
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

<s:if test='#request.UploadMsg==null || #request.UploadMsg.trim().equals("")'><p class="upstate">上传被拒绝，可能文件大小超过限制！</p></s:if><s:else></s:else>
<p class="upstate"><s:property  value="#request.UploadMsg"/></p>
<p class="upadd">上传后地址：<input id="fileUrl" size="20" name="fileUrl" value="<s:property  value="#request.FileUrl"/>"></p>

<p><a href='upload.jsp?col=<s:property value="#parameters.col"/>'>重传</a> <a href="javascript:returnMsg();">确定</a> <a href="javascript:window.parent.closeUpLoadDiv();">取消</a></p>
<script type="text/javascript">
function returnMsg(){
var cols="<s:property value="#request.col"/>";
var sArray = cols.split(",");
for (step=0;step<sArray.length;step++){
		$(window.parent.document).find("#"+sArray[step]).val($("#fileUrl").val());
		$(window.parent.document).find("#"+sArray[step]).attr("src", $("#fileUrl").val());
}
//$(window.parent.document).find("#<s:property value="#request.col"/>").val($("#fileUrl").val());
window.parent.closeUpLoadDiv();
}
</script>
</body>
</html>