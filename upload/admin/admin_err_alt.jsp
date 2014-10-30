<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<script>alert('对不起！您需要重新登录！');window.location='<%=path%>/<s:i18n name="ApplicationResources"><s:text name="lerx.default.admin.folder" /></s:i18n>/m_login.jsp';
	</Script>