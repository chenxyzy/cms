<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发文统计</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/tools_countArtByUser.action">重新计算并记录</a>
<a href="admin_tools_countArtsByUserAndOther.jsp">指定条件统计</a>
</body>
</html>
</s:if>