<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
%>
<s:if test="#request.resultAltStr.trim().equals('')==false">
	<script LANGUAGE="JavaScript" type="text/javascript">
	<!--
		var temp = '<s:property value="#request.resultAltStr" />';
		alert(temp);
	//-->
	</script>
	<s:set name="resultAltStr" value="" scope="request" />
</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>