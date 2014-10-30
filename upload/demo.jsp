<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
request.setCharacterEncoding("UTF-8");

String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";

%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>

	<script charset="utf-8" TYPE="text/javascript" language="javascript" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
<script>
KE.show({
id : 'content1',
imageUploadJson : '<%=request.getContextPath()%>/kindeditor/upload_json.jsp',
fileManagerJson : '<%=request.getContextPath()%>/kindeditor/file_manager_json.jsp',
cssPath : '<%=request.getContextPath()%>/kindeditor/index.css',
afterCreate : function(id) {
KE.event.ctrl(document, 13, function() {
KE.util.setData(id);
document.forms['example'].submit();
});
KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
KE.util.setData(id);
document.forms['example'].submit();
});
}
});
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" method="post" action="demo.jsp">
		<textarea name="content1" id="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>