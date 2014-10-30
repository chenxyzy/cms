<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{ font-size:12px;}
p,div{text-align:center;}
div{ margin:5px 0;}
</style>
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="attachment_modify.action?tid=<s:property value="#parameters.tid" />">
<s:action name="attachment_findByParentId">
	<s:param name="tid">
		<s:property value="#parameters.tid" />
	</s:param>
</s:action>
<div>序号格式：<input type="text" name="attaLineOrderFormatStr" size="20"  value="${request.attaLineOrderFormatStr}"> 默认媒体文件：<select size="1" name="defaultAttaMediaFormat">
	<option value="0" <s:if test="#request.defaultAttaMediaFormat==0">selected</s:if>>不采用</option>
	<option value="1" <s:if test="#request.defaultAttaMediaFormat==1">selected</s:if>>flv</option>
	<option value="2" <s:if test="#request.defaultAttaMediaFormat==2">selected</s:if>>wmv</option>
	<option value="3" <s:if test="#request.defaultAttaMediaFormat==3">selected</s:if>>rm</option>
</select></div>
<p></p>

<s:iterator value="#request.attachments" id="x" status="st">
	<div><input
		type="text" name="at[${st.index}].id" size="5" value="${x.id}" style="display:none;" /><input
		type="text" name="at[${st.index}].hostId" size="5" value="${x.hostId}" style="display:none;" /> <input type="checkbox" name="at[${st.index}].delTag" value="true">删除&nbsp;&nbsp;
	标题：<input type="text" name="at[${st.index}].title" size="10"  value="${x.title}"> 址址：<input
		type="text" name="at[${st.index}].url" size="15" value="${x.url}"> 序号：<input type="text"
		name="at[${st.index}].orderNum" size="3"  value="${x.orderNum}"> 作为媒体：<select size="1"
		name="at[${st.index}].mediaType">
		<option value="0" <s:if test="#x.mediaType==0">selected</s:if>>默认</option>
		<option value="1" <s:if test="#x.mediaType==1">selected</s:if>>flv</option>
		<option value="2" <s:if test="#x.mediaType==2">selected</s:if>>wmv</option>
		<option value="3" <s:if test="#x.mediaType==3">selected</s:if>>rm</option>
	</select></div>
</s:iterator>
<div><input type="submit" value="提交" name="B1"><input
	type="reset" value="重置" name="B2"></div>
</form>
</body>
</html>