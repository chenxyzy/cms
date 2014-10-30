<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery/1.7.2/jquery-1.7.2.min.js"></script>

<title>重置密码</title>
</head>
<body>
<p align="center"><img border="0" src="images/pwsGet.jpg"></p>
<p>  　</p>
<p align="center">请输入相关信息以便找回密码！</p>
<p>  　</p>
<form method="POST" action="user_retakeCode.action">

	<p align="center">请输入您的 <b>用户名</b>：<input type="text" name="user.userName" size="20"></p>
	<p align="center">请输入您的 <b>邮　箱</b>：<input type="text" name="user.email" size="20"></p>
	<p align="center"><input type="submit" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>
<p>  　</p>
<p>  　</p>
<p>  　</p>
<p>  　</p>
<p align="center"> <span id="site"><a href="http://www.lerx.com">Lerx网络科技</a></span></p>
<script type="text/javascript">
siteInf();
function siteInf(){
	$("#site").html("正在查询...");
	$.ajax({
		url:'/ajax_site_inf.action',
		type: 'post',
		success:function(data){
		var tmp=data;
		obj = JSON.parse(tmp);
		var html="<a href=\"http://"+obj.host+"\">"+obj.fullSiteName+"</a>";
		$("#site").html(html);
		}
	 });
 
    }
</script>
</body>
</html>