<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<style type="text/css">
body {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0 0 0 5px;
}
.list_1 {
	width: 20%;
	border-left: 0px;
	text-align: right;
}

.list_2 {
	width: 80%;
	text-align: left;
}
.list_oneline {
	
	border-right: #999 1px solid;
	
	border-left: 0;
	border-top: #fff 1px solid;
	height: 30px;
	text-align: center;
	padding-top: 10px;
}
</style>
	<title>选择栏目</title>
	</head>

	<body>
	<br /><br />
	<s:action name="admin_article_group_findAll"></s:action>
	<form method="post" action="admin_article_group_move.action?sid=<s:property value="#parameters.sid[0]" />">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
		<tr>
			<td class="list_1">选择目标栏目：</td>
			<td class="list_2"><s:action name="admin_article_group_findAll"></s:action>
			<select size="1" name="tid">
				
				<s:iterator value="#request.groupAll" id="x" status="st">
					<option
						<s:if test="#x.articleGroup.id == #parameters.sid[0] || (#x.articleGroup.footerLeft>currentGroup.footerLeft && #x.articleGroup.footerRight<currentGroup.footerRight)" >disabled="true" style="font-style:oblique;"</s:if>
						
						value="${x.articleGroup.id}">${x.prefixStr}
					${x.articleGroup.groupName}</option>
				</s:iterator>

			</select></td>
		</tr>
		
		</table>
		<p><br /><br /></p>
		<p>说明：本功能将移动源栏目的所有文章到目标栏目。</p>
		<p>注意：移动后数据将无法还原到原先栏目！</p>
		<p><br /><br /></p>
		<div class="list_oneline"><input type="submit" value="移动"
		name="B1"></div>
		</form>
		
		<script type="text/javascript">
function returnMsg(){
//$(window.parent.document).find("#<s:property value="#request.col"/>").val($("#fileUrl").val());
window.parent.closeUpLoadDiv();
}
</script>
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>