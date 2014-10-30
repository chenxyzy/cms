<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
.passAll {
	position:absolute;
	top:50%;
	left:50%;
	margin:-150px 0 0 -200px;
	width:500px;
	height:300px;
	border:1px solid #008800;
	}
.tit,.sub {margin-top:20px; text-align: center;}
</style>
<title>审核列表</title>
 
</head>
<body>

<div class="passAll">
<form name="f1" method="post" action="passer_send.action?nu.id=<s:property value="#request.nu.id"/>" >
		
	<div class="tit">请从下面的审核员列表中正确选择一位审核员</div>
	<div class="tit">
		 
		<select size="1" name="passer.id">
		<s:iterator value="passerAll" id="x" status="st"> 
		<option value="${x.id}">${x.passerInf1} ${x.passerInf2} ${x.user.userName} </option>
		</s:iterator>
		</select>
		
	</div>
	<div class="sub"><input type="submit" value="下一步"></div>
</form>
</div>





</body>
</html>