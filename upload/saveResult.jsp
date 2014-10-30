<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
<p class="upstate"><s:property  value="#request.UploadMsg"/></p>
<p class="upadd">上传后地址：<input id="fileUrl" size="20" name="fileUrl" value="<s:property  value="#request.FileUrl"/>"></p>
<p><a href="upload.jsp">重传</a> <a href="javascript:returnMsg();">确定</a> <a href="javascript:window.parent.closeUpLoadDiv();">取消</a></p>
<script type="text/javascript">
function returnMsg(){

$(window.parent.document).find("#<s:property value="#request.col"/>").val($("#fileUrl").val());
window.parent.closeUpLoadDiv();
}
</script>
</body>
</html>