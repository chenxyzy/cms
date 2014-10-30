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
	text-decoration: none;
	color: #333;
}  /*链接*/
a:visited {
	text-decoration: none;
	color: #333;
}  /*已访问链接*/
a:hover {
	text-decoration: none;
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

.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7,.list_8 {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: center;
	padding: 5px;
}

.list_1 {
	width: 20%;
	border-left: 0px;
	text-align: right;
}

.list_2 {
	width: 80%;
	text-align: left;
}

.list_3 {
	width: 20%;
}

.list_4 {
	width: 20%;
}

.list_5 {
	width: 10%;
}

.list_6 {
	width: 10%;
}

.list_7 {
	
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

.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8
	{
	border-right: #999;
	border-bottom: #999;
	text-align: center;
}
</style>
	<title>版块设置</title>
	</head>

	<body>
	<div class="aebox">
	<div class="aeheadbox">
	<div class="aeheadl"></div>
	<div class="aeheadm">> 版块设置</div>
	<div class="aeheadr"></div>
	</div>
	<div class="aelist">
	<form method="post" action="admin_bbs_forum_modify.action?forum.id=${currentForum.id}">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="list_1">版块名称：</td>
			<td class="list_2"><input type="text"
				name="forum.forumName" size="50"
				value="${currentForum.forumName}"></td>
		</tr>
		<tr>
			<td class="list_1">上级版块：</td>
			<td class="list_2"><s:action name="admin_bbs_forum_findAll"></s:action>
			<select size="1" name="parentForumID">
				<option value="0">根版块${forum.rootForum.id}</option>
				<s:iterator value="#request.forumAll" id="x" status="st">
					<option
						<s:if test="#x.forum.id==currentForum.id || (#x.forum.footerLeft>currentForum.footerLeft && #x.forum.footerRight<currentForum.footerRight)" >disabled="true" style="font-style:oblique;"</s:if>
						<s:if test="#x.forum.id==currentForum.rootForum.id">selected</s:if>
						value="${x.forum.id}">${x.prefixStr}
					${x.forum.forumName}</option>
				</s:iterator>

			</select></td>
		</tr>
		<tr>
			<td class="list_1">常规属性：</td>
			<td class="list_2">
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="25%"><input type="checkbox"
						name="forum.state" value="true" 
						<s:if test="currentForum.state" >checked</s:if>>正常使用</td>
					<td width="25%"><input type="checkbox"
						name="forum.share" value="true"
						<s:if test="currentForum.share" >checked</s:if>>版块公开</td>
					<td width="25%"><input type="checkbox"
						name="forum.asClass" value="true"
						<s:if test="currentForum.asClass" >checked</s:if>>定义为分类</td>
					<td width="25%"></td>
				</tr>
				
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td class="list_1">限制本版块可访问IP：</td>
			<td class="list_2"><input type="text"
				name="forum.hostsAllow" size="50"
				value="${currentForum.hostsAllow}"></td>
		</tr>
		
		<tr>
			<td class="list_1">点赞控制码：</td>
			<td class="list_2"><input type="text"
				name="forum.pollFmt" maxlength="3" size="5"
				value="${currentForum.pollFmt}"></td>
		</tr>
		
		
		<tr>
			<td class="list_1">版块图标Url：</td>
			<td class="list_2"><input type="text"
				name="forum.icoUrl" size="50"
				value="${currentForum.icoUrl}"></td>
		</tr>
		
		
		

	</table>
	<div class="list_oneline"><input type="submit" value="提交"
		name="B1"> <input type="reset" value="复位" name="B2"></div>
	</form>
	</div>
	</div>
	注：作为分类后将无法在此分类下发表文章，版块不公开将只对审核用户开放。
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>