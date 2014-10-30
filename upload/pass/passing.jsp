<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
.passAll {height:500px; width:500px; margin:0 auto;}

.tit,.sub {
	margin-top: 20px;
	text-align: center;
}
</style>
<title>审核</title>
</head>
<body>

<div class="passAll">
<form name="f1" method="post"
		action="user_passByAnswers.action?pi.passerId=${passer.id}&pi.userId=${user.id}">
	
	<div class="tit">审核验证</div>

	<div class="tit" >
	
	<s:if
		test='passer.answer1!=null and !passer.answer1.trim().equals("") and passer.answer2!=null and !passer.answer2.trim().equals("")'>
		<p>您选择的审核员提供了两个问题请您回答。</p>
		<p>${passer.question1} <input type="text" name="pi.answer1"
			size="5"></p>
	
		<p>${passer.question2} <input type="text" name="pi.answer2"
			size="5"></p>
	</s:if> <s:if test='passer.priTag1.trim().equals("?")'>
		<p>${passer.questionForPriTag1} <input type="text"
			name="pi.priTag1" size="20"></p>
	</s:if> <s:if test='passer.priTag2.trim().equals("?")'>
		<p>${passer.questionForPriTag2} <input type="text"
			name="pi.priTag2" size="20"></p>
	</s:if>
	</div>

	<div class="tit"><b>验证码:</b> <label> <input type="text"
		onfocus="showLoginChooseDiv()" maxlength="4" name="verifyCode"
		id="textfield3" size="2" /> <img width="60" height="18" name="imgLoginValid"
		id="imgLoginValid" style="visibility: hidden; cursor: pointer;"
		onclick="javascript:changeLoginChooseDiv();" /> </label>
	</div>
	<div class="tit">
	<input type="submit" value="下一步">
	</div>
</form>
</div>
<script type="text/javascript">
	function showLoginChooseDiv() {
		var objimg = document.getElementById("imgLoginValid");
		if (objimg.style.visibility != "visible") {
			var timeNow = new Date().getTime();
			objimg.src = "randomNum.action?time=" + timeNow
					+ "&from=pass&mode=2";
			objimg.style.visibility = "visible";
		}
	}
	function changeLoginChooseDiv() {
		var obj = document.getElementById("imgLoginValid");
		var timeNow = new Date().getTime();
		obj.src = "randomNum.action?time=" + timeNow + "&from=pass&mode=2";
		document.login.verifyCode.focus();
	}
</script>
</body>
</html>