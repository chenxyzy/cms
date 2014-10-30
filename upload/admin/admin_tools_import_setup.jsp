<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<body>
<p><input type="radio" onclick="javascript:chkAccess();" value="V1" checked name="R1">导入Access数据库</p>
<p><input type="radio" onclick="javascript:chkMysql();" name="R1" value="V2">导入Mysql数据库</p>
<script type="text/javascript">
function chkAccess(){
	$("#mysql").hide();
	$("#access").show();
}
function chkMysql(){
	$("#mysql").show();
	$("#access").hide();
}
</script>

<div id="access">
<form name="f1" method="POST" action="tools_import_init.action">
	
	<p>服务器中(必须是Windows服务器)的Access数据库文件地址(绝对地址)：<input TYPE="text" id="file" name="con.dbFile" size="20"></p>
	<p>表名：<input TYPE="text" name="con.table" size="20"></p>
	<p>字符集：<select size="1" name="con.charSet">
	<option value="GBK" selected>GBK</option>
	<option value="GB2312">GB2312</option>
	<option value="UTF-8">UTF-8</option>
	</select></p>
	<input TYPE="hidden" name="con.dbType" value="0" size="20">
	<p><input type="submit" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>
</div>
<div id="mysql" style="display: none;">
<form name="f2" method="POST" action="tools_import_init.action">
	
	<p>Mysql服务器：<input TYPE="text" id="server" value="localhost" name="con.server" size="20"></p>
	<p>端口：<input TYPE="text" id="port" name="con.port" value="3306" size="20"></p>
	<p>数据库：<input TYPE="text" name="con.db" size="20"></p>
	<p>用户名：<input TYPE="text" id="servername" value="root" name="con.user" size="20"></p>
	<p>密码：<input TYPE="text" id="password" name="con.password" size="20"></p>
	
	<p>表名：<input TYPE="text" name="con.table" size="20"></p>
	<p>字符集：<select size="1" name="con.charSet">
	<option value="GBK" selected>GBK</option>
	<option value="GB2312">GB2312</option>
	<option value="UTF-8">UTF-8</option>
	</select></p>
	<input TYPE="hidden" name="con.dbType" value="1" size="20">
	<p><input type="submit" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>
</div>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>