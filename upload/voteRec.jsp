<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:action name="admin_vote_subject_findById">
	<s:param name="id"><s:property value="#parameters.subId"/></s:param>
</s:action>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body,html{ height:100%; font-size:12px; font-family:"宋体",Arial, Helvetica, sans-serif;}
table,table td,table th{
border-collapse:collapse;margin-top: 10px;}

td{
background:#ffc;
border:solid 1px #f90;
height:22px;
}
.pageStr {margin-top: 10px;}
.title {margin-top: 5px;text-align: center}
.count {margin-top: 5px;text-align: center}
</style>
<title><s:property value="#request.curVs.title"/>投票详细情况</title>
</head>

<body>
<br>
<br>
<s:action name="voteRec">
		<s:param name="subId">
			<s:property value="#parameters.subId" />
		</s:param>
		<s:param name="page">
			<s:property value="#parameters.page" />
		</s:param>
		<s:param name="pageSize">
			<s:property value="#parameters.pageSize" />
		</s:param>
	</s:action>
	
<div class="title"><big><b>“<s:property value="#request.curVs.title"/>”投票详细情况</b></big> </div>
<div class="count">目前已有 <s:property value="#request.voteRecRs.count"/> 人参与投票</div>


<table  width="100%"  id="table1"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>序号</td>
		<td>投票人</td>
		<td>身份证号</td>
		<td>家庭住址或联系信息</td>
		<td>来源或职业</td>
		<td>联系电话</td>
		<td>来源IP地址</td>
		<td>投票情况</td>
		<td>投票时间</td>
	</tr>

	
	
	<s:iterator value="#request.voteRecRs.list" id="x" status="st">
	<tr>
   		<td>${(voteRecRs.page-1)*voteRecRs.pageSize+st.index+1}</td>
		<td style="white-space:nowrap"> ${x.name}</td>
		<td style="white-space:nowrap"> ${x.identity}</td>
		
		<td><s:if test="#x.address==null" >-</s:if><s:else>${x.address}</s:else> </td>
		<td> ${x.occ}</td>
		<td> ${x.phone}</td>
		<td>${x.addIp}</td>
		<td>${x.itemsRec}</td>
		<td>${x.addTime}</td>
		</tr>
	</s:iterator>
</table>
<div class="pageStr">第<s:property value="#parameters.page" />页  <s:if test="#request.voteRecRs.pageCount>0"><s:if test="#request.voteRecRs.page>1"><a href="?subId=<s:property value="#parameters.subId"/>&page=${voteRecRs.page-1}&pageSize=${voteRecRs.pageSize}">上一页</a></s:if> <s:if test="#request.voteRecRs.page<#request.voteRecRs.pageCount"><a href="?subId=<s:property value="#parameters.subId"/>&page=${voteRecRs.page+1}&pageSize=${voteRecRs.pageSize}">下一页</a></s:if></s:if> 共${voteRecRs.pageCount}页</div>

</body>
</html>