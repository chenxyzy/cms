<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<s:action name="admin_administrator_safetyCertification">
</s:action>
<s:if test="#session.LerxAdmin=='false'">
	<script>alert('对不起！您需要重新登录！');window.location='<%=path%>/<s:i18n name="ApplicationResources"><s:text name="lerx.default.admin.folder" /></s:i18n>/m_login.jsp';
	</Script>
</s:if>

<s:if test="#request.resultAltStr.trim().equals('')==false">
	<script LANGUAGE="JavaScript" type="text/javascript">
	<!--
		var temp = '<s:property value="#request.resultAltStr" />';
		//execScript("msgbox '测试1',0,'操作成功'","操作提示");
		alert(temp);
	//-->
	</script>
	<s:set name="resultAltStr" value="" scope="request" />
</s:if>
