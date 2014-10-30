<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<style type="text/css">
body {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0 0 0 5px;
}

a:link {
	text-decoration: underline;
	color: #333;
}  /*链接*/
a:visited {
	text-decoration: underline;
	color: #333;
}  /*已访问链接*/
a:hover {
	text-decoration: underline;
	color: #f00;
}  /*鼠标经过链接*/
a:active {
	text-decoration: none;
	color: #333;
}  /*鼠标按下链接*/
.aebox {
	width: 100%;
}

.aeheadbox {
	background: url(images/btbgm.jpg);
	height: 34px;
}

.aeheadl {
	height: 34px;
	width: 5px;
	background: url(images/btbgl.jpg) no-repeat;
	float: left;
}

.aeheadm {
	height: 25px;
	padding: 9px 0 0 15px;
	color: #FFF;
	font-size: 14px;
	font-weight: bold;
	float: left;
}

.aeheadr {
	height: 34px;
	width: 3px;
	background: url(images/btbgr.jpg) no-repeat;
	float: right;
}

.aelist {
	width: 99%;
	margin: auto;
	margin-bottom: 5px;
}

.aelist tr {
	background: #f5f5f5;
}

.trlist td {
	background: #aaa;
	height: 24px;
	font-weight: bold;
	color: #FFF;
}

.aelist tr:hover {
	background: #e6e6e6;
}

.list_1,.list_2,.list_3,.list_4,.list_5,.list_6 {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: center;
	padding: 5px;
}

.list_7 {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: right;
	padding: 5px;
}

.list_8 {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: left;
	padding: 5px;
}

.list_1 {
	width: 5%;
	border-left: 0px;
	text-align: right;
}

.list_2 {
	width: 20%;
	text-align: left;
}

.list_3 {
	width: 20%;
}

.list_4 {
	width: 20%;
}

.list_5 {
	width: 15%;
}

.list_6 {
	width: 20%;
}

.list_7 {
	width: 35%;
}

.list_8 {
	width: 65%;
}

.list_oneline {
	background: #CCC;
	border-right: #999 1px solid;
	border-bottom: #999 1px solid;
	border-left: 0;
	border-top: #fff 1px solid;
	height: 30px;
	text-align: center;
	padding-top: 10px;
}

.list_oneline2 {
	background: #eee;
	border-right: #e4e4e4 1px solid;
	border-bottom: #e4e4e4 1px solid;
	border-left: 0;
	border-top: #fff 1px solid;
	height: 20px;
	text-align: center;
	padding-top: 5px;
}

.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8
	{
	border-right: #999;
	border-bottom: #999;
	text-align: center;
}
</style>
	<title>审核员修改</title>
	</head>

	<body>
	<div class="aebox">
	<div class="aeheadbox">
	<div class="aeheadl"></div>
	<div class="aeheadm">> 用户审核员修改</div>
	<div class="aeheadr"></div>
	</div>



	<div class="aebox">
	<div class="aeheadbox">
	<div class="aeheadl"></div>
	<div class="aeheadm">> 修改用户审核员 ${passer.user.userName} 信息</div>
	<div class="aeheadr"></div>
	</div>
	<div class="aelist"><s:property value="actionErrors[0]" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<form method="POST"
			action="admin_passer_modify.action?passer.id=${passer.id}&passer.user.id=${passer.user.id}">

		<s:action name="admin_userGroup_query"></s:action>
		<tr>
			<td class="list_7">授权用户组：</td>
			<td class="list_8"><select size="1" name="passer.toUG.id">

				<s:iterator value="#request.allGroup" id="x" status="st">


					<option value="${x.id}"
						<s:if test="#x.id==passer.toUG.id">selected</s:if>>${x.groupName}</option>
				</s:iterator>
			</select></td>
		</tr>
		<tr>
			<td class="list_7">审核员信息1：</td>
			<td class="list_8"><input type="text" name="passer.passerInf1"
				autocomplete="off" size="60" value="${passer.passerInf1}" /> <input
				type="checkbox" name="passer.lockPasserInf1" value="true" 
				<s:if test="passer.lockPasserInf1" >checked</s:if> >锁定 </td>
		</tr>
		<tr>
			<td class="list_7">审核员信息2：</td>
			<td class="list_8"><input type="text" name="passer.passerInf2"
				autocomplete="off" size="60" value="${passer.passerInf2}" /> <input
				type="checkbox" name="passer.lockPasserInf2" value="true" 
				<s:if test="passer.lockPasserInf2" >checked</s:if> >锁定 </td>
		</tr>
		<tr>
			<td class="list_7">问题1：</td>
			<td class="list_8"><input type="text" name="passer.question1"
				autocomplete="off" size="60" value="${passer.question1}" /></td>
		</tr>
		<tr>
			<td class="list_7">答案1：</td>
			<td class="list_8"><input type="text" name="passer.answer1"
				autocomplete="off" size="60" value="${passer.answer1}" /></td>
		</tr>
		<tr>
			<td class="list_7">问题2：</td>
			<td class="list_8"><input type="text" name="passer.question2"
				autocomplete="off" size="60" value="${passer.question2}" /></td>
		</tr>
		<tr>
			<td class="list_7">答案2：</td>
			<td class="list_8"><input type="text" name="passer.answer2"
				autocomplete="off" size="60" value="${passer.answer2}" /></td>
		</tr>




		<tr>
			<td class="list_7">注入信息1：</td>
			<td class="list_8">信息：<input type="text" name="passer.priTag1"
				autocomplete="off" size="40" value="${passer.priTag1}" /><input
				type="checkbox" name="passer.lockPriTag1" value="true" 
				<s:if test="passer.lockPriTag1" >checked</s:if> >锁定 <br />
			提示：<input type="text" name="passer.questionForPriTag1"
				autocomplete="off" size="40" value="${passer.questionForPriTag1}" />如果需要根据提示输入信息，请在<b>信息</b>内输入?(注：英文小写)</td>
		</tr>

		<tr>
			<td class="list_7">注入信息2：</td>
			<td class="list_8">信息：<input type="text" name="passer.priTag2"
				autocomplete="off" size="40" value="${passer.priTag2}" /><input
				type="checkbox" name="passer.lockPriTag2" value="true" 
				<s:if test="passer.lockPriTag2" >checked</s:if> >锁定 <br />
			提示：<input type="text" name="passer.questionForPriTag2"
				autocomplete="off" size="40" value="${passer.questionForPriTag2}" />同上</td>
		</tr>
		
		<tr>
			<td class="list_7">状态：</td>
			<td class="list_8">
			<p align="left"><input type="radio" value="true"
				<s:if test="passer.state">checked</s:if> name="passer.state">正常<input
				type="radio" value="false" 
				<s:if test="passer.state==false">checked</s:if> name="passer.state">禁用</p>
			</td>
		</tr>

	</table>
	</div>
	<div class="list_oneline"><input type="submit" value="提交"
		name="B1"> <input type="reset" value="复位" name="B2"></div>
	</form>

	</div>
	</div>

	</body>
	</html>
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>