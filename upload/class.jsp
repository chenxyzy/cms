<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:action name="nav">
	<s:param name="gid"><s:property value="#parameters.id"/></s:param>
	<s:param name="gid"><s:property value="#parameters.gid"/></s:param>
</s:action>
<s:property value="#request.lerxCmsBody" escape="false" />