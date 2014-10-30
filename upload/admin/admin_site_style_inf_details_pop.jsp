<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
body{ font-size:12px; font-family:Arial, Helvetica, sans-serif; margin:0; padding:0 0 0 5px;}

a:link{text-decoration : none ;color : #333 ;} /*链接*/
a:visited {text-decoration : none ;color : #333 ;} /*已访问链接*/
a:hover {text-decoration : none ;color : #f00 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #333 ;}  /*鼠标按下链接*/

</style>
<title>变量详情</title>
</head>

<body>
<s:action name="admin_site_style_findNameById">
	<s:param name="id"><s:property  value="#request.styleID"/></s:param>
</s:action>
<%
//ps_KeywordForNextPage=new String(request.getParameter("KeywordForNextPage").getBytes("ISO8859-1"),"GBK");
%>
<p>“<s:property value="#request.currentStyleName"/>”—— <b>门户</b> 风格模板变量详情修改 <b><font color="#FF0000"><s:property escape="" value="#request.currentModelName"/></font></b></p> 
<form method="POST" action="admin_site_style_var_modify.action?styleID=${styleID}&id=${id}&col=${col}&table=${table}">
  <table width="100%" height="60%" border="0" cellpadding="0" cellspacing="0">
   <tr class="trlist">
    <td class="list_1">变量名称：<b><font color="#FF0000">${col}</font></b></td>
   </tr>
   <tr>
    <td class="list_1"><textarea rows="10" name="colValue" cols="68">${returnStrFromSql}</textarea></td>
   </tr>
  </table>
 <div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
  </form>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>