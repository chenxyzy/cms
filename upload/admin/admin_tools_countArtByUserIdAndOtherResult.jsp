<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
统计报表</title>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

table.imagetable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.imagetable th {
	background: #b5cfd2 url('images/cell-blue.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}

table.imagetable td {
	background: #dcddc0 url('images/cell-grey.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}
</style>
</head>

<body>


  <table width="90%" border="0" cellpadding="0" cellspacing="0" class="gridtable">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">会员Id</td>
    <td class="list_3">会员名称</td>
    <td class="list_4">真实名称</td>
    <td class="list_5">总发表数</td>
    <td class="list_6">通过数</td>
    
   </tr>

   <s:iterator value="#request.allCountList" id="x" status="st">      
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.uid} </td>
    <td class="list_3">${x.username}</td>
    <td class="list_4">${x.trueName}</td>
    <td class="list_5">${x.all}</td>
    <td class="list_6">${x.passed}</td>
   
   </tr>
   
</s:iterator>
  </table>
  

</body>
</html>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>