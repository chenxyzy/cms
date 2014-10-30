<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script>
	if (top.location != self.location) {
		top.location = self.location;
	}
</script>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  
    $("#name").focus();
 
});
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style type="text/css"> 
body{ margin:0; padding:0; font-size:12px; color:#333; font-family:Arial, Helvetica, sans-serif}
a:link{text-decoration : none ;color :#2366A8 ;} /*链接*/
a:visited {text-decoration : none ;color :#2366A8;} /*已访问链接*/
a:hover {text-decoration : none ;color : #2366A8 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #2366A8 ;}  /*鼠标按下链接*/
.adminbigbox{ width:600px; height:150px; margin:150px auto auto auto;}
.mboxleft,.mboxright{ margin-top:5px;  width:299px; height:140px;}
.mboxleft{ float:left;}
.mboxright{ float:right; border-left: #CCC dotted 1px;}
.adminlogo{ margin:8px auto 20px; width:263px; height:36px; background:url(./images/adminlogo.jpg);}
.admintext{ margin:auto 15px;}
.adminid,.adminpw,.adminnum,.adminbot{margin:10px auto 10px 30px; height:20px;}
.adminid input,.adminpw input{width:140px; height:16px; border:#999 solid 1px; background:#f7f7f7;}
.adminnum input{width:60px; height:16px; border:#999 solid 1px; background:#f7f7f7;}
.adminbot{ padding-left:45px;}
.adminbot input{ width:52px; height:23px; border:#333 solid 1px; background:#999;}
.msg { text-align:center }
.admincopyright{margin:150px auto auto auto;width:600px; height:30px; text-align:center;}
</style>

</head>
<title>管理员登录——Lerx网站管理系统</title>

<body>

<div class="adminbigbox">
<div class="mboxleft">
<div class="adminlogo"></div>
<div class="admintext">Lerx 2是一个采用 SSH2框架构建的跨平台的高效专业级WEB应用集群解决方案(基于Java技术)。</div>
</div>
<div class="mboxright">
<form method="post" name="login" action="admin_administrator_login.action" >
<div class="msg">
<s:property value="actionErrors[0]"/>
</div>

	<div class="adminid"><b>用户名:</b> <label> <input
		type="text" name="admin.name" id="name" /> </label></div>
	<div class="adminpw"><b>密　码:</b> <label> <input
		type="password" name="admin.password" id="textfield2" /> </label></div>
	<div class="adminnum"><b>验证码:</b> <label> <input
		type="text" onfocus="showLoginChooseDiv()" maxlength="4" name="verifyCode"
		id="textfield3" /> <img width="60" height="18" name="imgLoginValid" id="imgLoginValid"
		style="visibility: hidden; cursor: pointer;"
		onclick="javascript:changeLoginChooseDiv();" /> </label></div>
	<div class="adminbot"><label> <input type="submit"
		name="button" id="button" value="提　交" /> </label> &nbsp; <label> <input
		type="submit" name="button2" id="button2" value="重　置" /> </label></div>
</form>
<script type="text/javascript">
	function showLoginChooseDiv() {
		var objimg = document.getElementById("imgLoginValid");
		if (objimg.style.visibility != "visible") {
			var timeNow = new Date().getTime();
			objimg.src = "randomNum.action?time=" + timeNow + "&from=admin&mode=2";
			objimg.style.visibility = "visible";
		}
	}
	function changeLoginChooseDiv() {
		var obj = document.getElementById("imgLoginValid");
		var timeNow = new Date().getTime();
		obj.src = "randomNum.action?time=" + timeNow + "&from=admin&mode=2";
		document.login.verifyCode.focus();
	}
</script></div>
</div>

<div class="admincopyright">Powered by <a
	href="http://www.lerx.com">Lerx</a> &copy; 2005-2008, <a
	href="http://www.lerx.com">www.lerx.com</a> Inc.</div>
</body>
</html>