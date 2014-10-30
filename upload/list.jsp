<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:action name="artshow">
	<s:param name="tid"><s:property value="#parameters.id"/></s:param>
	<s:param name="tid"><s:property value="#parameters.tid"/></s:param>
</s:action>
<s:property value="#request.lerxCmsBody" escape="false" />