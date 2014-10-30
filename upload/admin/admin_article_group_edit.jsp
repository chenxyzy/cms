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
	<title>栏目设置</title>
	</head>

	<body>
	<div class="aebox">
	<div class="aeheadbox">
	<div class="aeheadl"></div>
	<div class="aeheadm">> 栏目设置</div>
	<div class="aeheadr"></div>
	</div>
	<div class="aelist">
	<form method="post" action="admin_article_group_modify.action?articleGroup.id=${currentGroup.id}">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="list_1">栏目名称：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.groupName" size="50"
				value="${currentGroup.groupName}"></td>
		</tr>
		<tr>
			<td class="list_1">上级栏目：</td>
			<td class="list_2"><s:action name="admin_article_group_findAll"></s:action>
			<select size="1" name="parentGroupID">
				<option value="0">根栏目${articleGroup.parentGroup.id}</option>
				<s:iterator value="#request.groupAll" id="x" status="st">
					<option
						<s:if test="#x.articleGroup.id==currentGroup.id || (#x.articleGroup.footerLeft>currentGroup.footerLeft && #x.articleGroup.footerRight<currentGroup.footerRight)" >disabled="true" style="font-style:oblique;"</s:if>
						<s:if test="#x.articleGroup.id==currentGroup.parentGroup.id">selected</s:if>
						value="${x.articleGroup.id}">${x.prefixStr}
					${x.articleGroup.groupName}</option>
				</s:iterator>

			</select></td>
		</tr>
		<tr>
			<td class="list_1">常规属性：</td>
			<td class="list_2">
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="25%"><input type="checkbox"
						name="articleGroup.state" value="true" 
						<s:if test="currentGroup.state" >checked</s:if>>正常使用</td>
					<td width="25%"><input type="checkbox"
						name="articleGroup.share" value="true"
						<s:if test="currentGroup.share" >checked</s:if>>栏目公开</td>
					<td width="25%"><input type="checkbox"
						name="articleGroup.asClass" value="true"
						<s:if test="currentGroup.asClass" >checked</s:if>>定义为分类</td>
					<td width="25%"><input type="checkbox"
						name="articleGroup.showAll" value="true"
						<s:if test="currentGroup.showAll" >checked</s:if>>显示所有子类文章</td>
				</tr>
				<tr>
					<td width="25%">日最大发文数：<input type="text"
						name="articleGroup.numberAppearRestrict" size="5"
						value="${currentGroup.numberAppearRestrict}"></td>
					<td width="25%">文章价格：<input type="text"
						name="articleGroup.price" size="5"
						value="${currentGroup.price}"></td>
					<td width="25%">数据行数<input type="text"
						name="articleGroup.numberList" size="5"
						value="${currentGroup.numberList}"></td>
					<td width="25%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="list_1">属性@首 页：</td>
			<td class="list_2">
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="15%"><input type="checkbox"
						name="articleGroup.showOnIndex" value="true"
						<s:if test="currentGroup.showOnIndex" >checked</s:if>>首页列表</td>
					<td width="15%">显示个数<input type="text"
						name="articleGroup.numberShowOnIndex" size="5"
						value="${currentGroup.numberShowOnIndex}"></td>
					<td width="15%">标题长度<input type="text"
						name="articleGroup.lengthShowOnIndex" size="5"
						value="${currentGroup.lengthShowOnIndex}"></td>
					<td width="30%">列表格式
					<textarea rows="2" name="articleGroup.formatOnIndex" cols="30">${currentGroup.formatOnIndex}</textarea>
					</td>
					<td width="15%">精华靠前<input type="checkbox"
						name="articleGroup.soulOnIndex" value="true"
						<s:if test="currentGroup.soulOnIndex" >checked</s:if>></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="list_1">属性@父级页：</td>
			<td class="list_2">
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="15%"><input type="checkbox"
						name="articleGroup.showOnParent" value="true"
						<s:if test="currentGroup.showOnParent" >checked</s:if>>父级页列表</td>
					<td width="15%">显示个数<input type="text"
						name="articleGroup.numberShowOnParent" size="5"
						value="${currentGroup.numberShowOnParent}"></td>
					<td width="15%">标题长度<input type="text"
						name="articleGroup.lengthShowOnParent" size="5"
						value="${currentGroup.lengthShowOnParent}"></td>
					<td width="30%">列表格式
					<textarea rows="2" name="articleGroup.formatOnParent" cols="30">${currentGroup.formatOnParent}</textarea>
					</td>
					<td width="15%">精华靠前<input type="checkbox"
						name="articleGroup.soulOnParent" value="true"
						<s:if test="currentGroup.soulOnParent" >checked</s:if>></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="list_1">限制本栏目可访问IP：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.hostsAllow" size="50"
				value="${currentGroup.hostsAllow}"></td>
		</tr>
		<tr>
			<td class="list_1">跳转目标Url：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.jumpUrl" size="50"
				value="${currentGroup.jumpUrl}"></td>
		</tr>
		<tr>
			<td class="list_1">栏目图标Url：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.iconUrl" size="50"
				value="${currentGroup.iconUrl}"></td>
		</tr>
		<tr>
			<td class="list_1">静态文件所在文件夹名：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.staticHtmlFolder" size="50"
				value="${currentGroup.staticHtmlFolder}"> <input type="checkbox"
						name="articleGroup.refuseStaticHtml" value="true" 
						<s:if test="currentGroup.refuseStaticHtml" >checked</s:if>>强制不静态</td>
		</tr>
		<s:action name="admin_site_style_query"></s:action>
		<tr>
			<td class="list_1">栏目风格：</td>
			<td class="list_2"><select size="1" name="articleGroup.style.id">
				<option value="0">--系统默认--</option>
				<s:iterator value="#request.siteStyleAll" id="x" status="st">
					<option <s:if test="#x.id==currentGroup.style.id">selected</s:if>
						value=${x.id}>${x.styleName}</option>
				</s:iterator>

			</select> <input type="checkbox" name="articleGroup.compulsionDocStyle"
				value="true"
				<s:if test="currentGroup.compulsionDocStyle" >checked</s:if>>强制下属栏目及文章页面
			</td>
		</tr>
		<tr>
			<td class="list_1">发文审核方式：</td>
			<td class="list_2"><select size="1"
				name="articleGroup.articlePassMode">
				<option value="0"
					<s:if test="currentGroup.articlePassMode==0" >selected</s:if>>--系统默认--</option>
				<option value="1"
					<s:if test="currentGroup.articlePassMode==1" >selected</s:if>>默认不通过</option>
				<option value="2"
					<s:if test="currentGroup.articlePassMode==2" >selected</s:if>>默认通过</option>
			</select></td>
		</tr>
		<tr>
			<td class="list_1">评论控制：</td>
			<td class="list_2"><select size="1"
				name="articleGroup.commentMode">
				<option value="0"
					<s:if test="currentGroup.commentMode==0" >selected</s:if>>--系统默认--</option>
				<option value="1"
					<s:if test="currentGroup.commentMode==1" >selected</s:if>>禁止评论</option>
				<option value="2"
					<s:if test="currentGroup.commentMode==2" >selected</s:if>>仅允许注册用户发表评论</option>
				<option value="3"
					<s:if test="currentGroup.commentMode==3" >selected</s:if>>允许游客发表评论</option>
			</select></td>
		</tr>
		<tr>
			<td class="list_1">点赞控制码：</td>
			<td class="list_2"><input type="text"
				name="articleGroup.pollFmt" maxlength="3" size="5"
				value="${currentGroup.pollFmt}"> 注：三位数字 用0或1表示。0代表关闭，1代表开放。第1位：中立;第二位：支持;第3位：反对。</td>
		</tr>
		<tr>
			<td class="list_1">私有html：</td>
			<td class="list_2"><textarea rows="5" name="articleGroup.privateHtml" cols="60">${currentGroup.privateHtml}</textarea></td>
		</tr>
		

	</table>
	<div class="list_oneline"><input type="submit" value="提交"
		name="B1"> <input type="reset" value="复位" name="B2"></div>
	</form>
	</div>
	</div>
	注：作为分类后将无法在此分类下发表文章，栏目不公开将只对审核用户开放。
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>