<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:action name="ajax_style_siteCustom">
	<s:param name="gidStr"><s:property value="#parameters.class"/></s:param>
	<s:param name="gid"><s:property value="#parameters.gid"/></s:param>
	<s:param name="mod"><s:property value="#parameters.o"/></s:param>
	<s:param name="n"><s:property value="#parameters.n"/></s:param>
	<s:param name="firstResult"><s:property value="#parameters.s"/></s:param>
	<s:param name="l"><s:property value="#parameters.l"/></s:param>
	<s:param name="fmod"><s:property value="#parameters.fmod"/></s:param>
	<s:param name="soul"><s:property value="#parameters.soul"/></s:param>
	<s:param name="stateMode">1</s:param>
	<s:param name="fromjsp">true</s:param>
</s:action>