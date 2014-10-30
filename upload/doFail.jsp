<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="showTitle"/>失败!!!</title>
</head>
<body>
<p><s:property value="showTitle"/>失败!!!</p>
<s:if test="requestIsNull.equalsIgnoreCase('yes')">请稍候重试！</s:if>
<s:property value="requestIsNull"/>

</body>
</html>
