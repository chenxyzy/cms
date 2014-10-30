<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<%
 response.setHeader("Cache-Control","no-store");           
 response.setHeader("Pragrma","no-cache");           
 response.setDateHeader("Expires",0);           
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">         
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">         
<META HTTP-EQUIV="expires" CONTENT="0"> 
<title>复制</title>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
</head>
<body>

<s:action name="admin_vote_subject_findSubById">
	<s:param name="id">
		<s:property value="#parameters.id" />
	</s:param>
</s:action>
<s:actionerror />
源名称：<s:property value="#request.curVs.title"/></br>
<form method="POST" id="copy"
	action="admin_vote_subject_copy.action?sid=<s:property value="#parameters.id"/>">
<div>
新的名称：<input type="text" id="newTitle" name="newTitle">
</div>
<div><input type="checkbox" name="itemsCopy" value="true" checked />同时复制items（内容）
</div>
<div>
 <input
	type="submit" value="提交" name="B1">
</div>
</form>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>
