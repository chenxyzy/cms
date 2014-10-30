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
<p align="center"><img border="0" src="images/pwsChange.jpg"></p>
<p>  　</p>
<p align="center">请重新输入一个新的密码！</p>
<p>  　</p>
<form id="f" method="POST" action="user_retakePws.action?uid=<s:property value="#parameters.uid"/>&salt=<s:property value="#parameters.salt"/>&uuid=<s:property value="#parameters.uuid"/>">
	
	<p align="center">输入新密码：<input type="password" id="pw1" name="pw1" size="20"></p>
	<p align="center">核对新密码：<input type="password" id="pw2" name="pw2" size="20"></p>
	<p align="center"><input type="button" onclick="javascript:gook();" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>

<SCRIPT LANGUAGE=javascript>
<!--
function gook() {
	var pw1=$.trim($('#pw1').val());
	var pw2=$.trim($('#pw2').val());
	if (pw1!=pw2){
		alert("两次密码不相同！");
		return false;
	}else{
		//$('#password').value=pw1;
		
		$('#f').submit();
	}
}
</SCRIPT>
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