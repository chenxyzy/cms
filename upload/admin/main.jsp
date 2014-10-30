<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lerx网站系统管理中心</title>
</head>
<frameset  border="0" framespacing="0" rows="*"> 
<frameset cols="180,*"  border="0" framespacing="0"> 
  <frame name="leftFrame" scrolling="NO" noresize src="admin_left.jsp?admin=${admin.name}" marginwidth="0" marginheight="0">
  <frame name="main" src="admin_main.jsp" scrolling="AUTO" NORESIZE frameborder="0" marginwidth="10" marginheight="10" >
  <noframes>
  <body>
</body>
  </noframes>
</frameset>
</frameset>
<frameset>
</frameset>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>