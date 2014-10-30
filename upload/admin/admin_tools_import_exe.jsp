<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content=5;url=tools_import_exe.action>


<title>Insert title here</title>
</head>
<body>

<p>正在导入......(<s:property value="complete"/>)% </p>
<p>--总记录数：${con.recCount}</p>
<p>已转换<s:property value="currentRec"/>条记录</p>
<p>--字段数：${con.colCount}</p>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>