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
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
</head>
<body>
<script type="text/javascript">
	function gook() {
		var name = $.trim($('#newStyleName').val());

		if (name == "") {
			alert("请输入风格名称！");
			return false;
		} else {
			 
			$("copy").submit();
			parent.location.reload(true);
			window.parent.closeStyleCopyDiv();
			alert("复制成功！请点击下方的刷新更新当前列表！");
			
			
			
		}

	}
</script>
<s:action name="admin_bbs_style_findNameById">
	<s:param name="styleID">
		<s:property value="#parameters.id" />
	</s:param>
</s:action>
<s:actionerror />
<form method="POST" id="copy"
	action="admin_bbs_style_copy.action?id=<s:property value="#parameters.id"/>">
<input type="text" id="newStyleName" name="newStyleName"> <input
	type="submit" value="提交" onclick="javascript:gook()" name="B1">
</form>
<a href='admin_bbs_style_copyToFile.action?oid=<s:property value="#parameters.id"/>'>导出为文件</a>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>