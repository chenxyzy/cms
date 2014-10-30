<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content='2;url=<s:property value="actionName"/>?arg1=<s:property value="arg1"/>&arg2=<s:property value="arg2"/>&arg3=<s:property value="arg3"/>'>
<title>正在<s:property value="showTitle"/>......</title>
</head>
<body>
<p>正在<s:property value="showTitle"/>......(<s:property value="complete"/>)% </p>
<p>当前处理信息(<s:property value="curStep"/>/<s:property value="stepAll"/>)：<s:property value="message"/></p>
</body>
</html>
