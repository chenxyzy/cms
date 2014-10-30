<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery/1.7.2/jquery-1.7.2.min.js"></script>

<title>QQ登录设置</title>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

table.imagetable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.imagetable th {
	background: #b5cfd2 url('images/cell-blue.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}

table.imagetable td {
	background: #dcddc0 url('images/cell-grey.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}
</style>

</head>
<body>
<div align="center">
<p>&nbsp;</p>
<table width="300" border="0">
	<tr>
		<td align="center" valign="middle" nowrap><strong><img
			src="images/qq_con.png" width="92" height="120"></strong></td>
		<td align="center" valign="middle" nowrap><strong>只需一步，轻松登录
		</strong></td>
	</tr>
</table>
<p>&nbsp;</p>
</div>
<form id="f1" method="POST"
	action="qq_userAdd.action?ic=true&openID=${qui.openID}">
	<table width="600" align="center" class="gridtable">
	<tr>
		<td align="center">我 是 新 用 户！</td>
		<td align="center">我 已 有 帐 号！</td>
	</tr>
	<tr>
		<td width="50%">
		<table width="100%" cellpadding="0" cellspacing="0" class="imagetable">
			<tr>
				<td align="right">用户名：</td>
				<td align="left"><input size="12" name="user.userName" value="${qui.nickname}" /></td>
			</tr>
			<tr>
				<td align="right">呢　称：</td>
				<td align="left"><input size="12" name="user.remName" value="${qui.nickname}" /></td>
			</tr>
			<tr>
				<td align="right">邮　箱：</td>
				<td align="left"><input size="12" name="user.email" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提    交"
					name="B1"></td>
			</tr>
		</table>
</form>
		</td>
		<td width="50%">
<form id="f2" method="POST"
			action="qq_userBind.action?ic=true&openID=${qui.openID}">
		<table width="600" cellpadding="0" cellspacing="0" class="imagetable">
			<tr>
				<td align="right">已存在的用户名：</td>
				<td align="left"><input size="12" name="user.userName" value="${qui.nickname}" /></td>
			</tr>
			<tr>
				<td align="right">密　　　　　码：</td>
				<td align="left"><input size="12" name="user.password" type="password" value="" /></td>
			</tr>
			<tr>
				<td align="right">验　　证　　码：</td>
				<td align="left"><input size="4" type="text" onfocus="showLoginChooseDiv()"
					maxlength="4" name="verifyCode" id="textfield3" /> <img width="60"
					height="18" name="imgLoginValid" id="imgLoginValid"
					style="visibility: hidden; cursor: pointer;"
					onclick="javascript:changeLoginChooseDiv();" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提    交"
					name="B2"></td>
			</tr>
		</table>
</form>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p align="center"> <span id="site"></span></p>
<script type="text/javascript">
siteInf();
function siteInf(){
	$("#site").html("正在查询...");
	$.ajax({
		url:'${pageContext.request.contextPath}/ajax_site_inf.action',
		type: 'post',
		success:function(data){
		var tmp=data;
		obj = JSON.parse(tmp);
		var host,port,url,siteName;
		host=obj.host;
		if (host==""){
			host="${pageContext.request.contextPath}";
		}else{
			host="http://"+host;
		}
		port=obj.port;
		if (port!=0 && port!=80){
			host+=":"+port;
		}
		siteName=obj.fullSiteName;
		if (siteName==""){
			siteName="未知网站名称";
		}
		var html="<a href=\""+host+"\">"+siteName+"</a>";
		$("#site").html(html);
		}
	 });
 
    }

	function showLoginChooseDiv() {
		var objimg = document.getElementById("imgLoginValid");
		if (objimg.style.visibility != "visible") {
			var timeNow = new Date().getTime();
			objimg.src = "randomNum.action?time=" + timeNow
					+ "&from=user&mode=2";
			objimg.style.visibility = "visible";
		}
	}
	function changeLoginChooseDiv() {
		var obj = document.getElementById("imgLoginValid");
		var timeNow = new Date().getTime();
		obj.src = "randomNum.action?time=" + timeNow + "&from=user&mode=2";
		document.login.verifyCode.focus();
	}
</script>
</body>
</html>