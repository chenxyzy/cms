<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核员管理中心</title>
<body>
<script type="text/javascript"
	src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
</head>




<s:action name="passer_findCur">
</s:action>

<s:if test="#request.passer.id>0">

	<p align="center"><strong>审核员信息</strong></p>
	<form method="post"
		action="passer_modifyByUser.action?passer.id=${passer.id}">
	<table align="center" width="600">
		<tr>
			<td colspan="2" align="left"><img src="../images/horn.jpg" border="0">为了方便别人迅速找到我，我提供下面两个信息</td>
		</tr>
		<tr>
			<td class="list_7">我的第一个信息：</td>
			<td class="list_8"><input type="text" name="passer.passerInf1"
				autocomplete="off" size="60" <s:if test="#request.passer.lockPasserInf1">readonly</s:if> value="${passer.passerInf1}" /></td>
		</tr>
		<tr>
			<td class="list_7">我的第二个信息：</td>
			<td class="list_8"><input type="text" name="passer.passerInf2"
				autocomplete="off" size="60" <s:if test="#request.passer.lockPasserInf2">readonly</s:if> value="${passer.passerInf2}" /></td>
		</tr>
		<tr>
			<td colspan="2" height="20" align="center" valign="middle"></td>
		</tr>
		<tr>
			<td colspan="2" align="left"><img src="../images/horn.jpg" border="0">为了减少我的工作量，我给两个问题让他们回答。回答正确就自动通过了。</td>
		</tr>
		<tr>
			<td class="list_7">我给出的第一个问题：</td>
			<td class="list_8"><input type="text" name="passer.question1"
				autocomplete="off" size="60" value="${passer.question1}" /></td>
		</tr>
		<tr>
			<td class="list_7">第一个问题的答案：</td>
			<td class="list_8"><input type="text" name="passer.answer1"
				autocomplete="off" size="60" value="${passer.answer1}" /></td>
		</tr>
		<tr>
			<td class="list_7">我给出的第二个问题：</td>
			<td class="list_8"><input type="text" name="passer.question2"
				autocomplete="off" size="60" value="${passer.question2}" /></td>
		</tr>
		<tr>
			<td class="list_7">第二个问题的答案：</td>
			<td class="list_8"><input type="text" name="passer.answer2"
				autocomplete="off" size="60" value="${passer.answer2}" /></td>
		</tr>
		<tr>
			<td colspan="2" height="20" align="center" valign="middle"></td>
		</tr>
		<tr>
			<td colspan="2" align="left"><img src="../images/horn.jpg" border="0">经过我（含通过问答通过的）审核的用户数据，将自动含有以下标签(注入信息1-2)。?将由用户自己填写。如果你发现不好编辑，那说明被管理员锁定了。</td>
		</tr>
		<tr>
			<td class="list_7" align="center">注入信息1：</td>
			<td class="list_8">提　　示：<input type="text"
				name="passer.questionForPriTag1"
				<s:if test="#request.passer.lockPriTag1">readonly</s:if>
				autocomplete="off" size="40" value="${passer.questionForPriTag1}" /><br />


			注入信息：<input type="text" name="passer.priTag1" autocomplete="off"
				<s:if test="#request.passer.lockPriTag1">readonly</s:if> size="40"
				value="${passer.priTag1}" /></td>
		</tr>

		<tr>
			<td class="list_7" align="center">注入信息2：</td>
			<td class="list_8">提　　示：<input type="text"
				name="passer.questionForPriTag2"
				<s:if test="#request.passer.lockPriTag2">readonly</s:if>
				autocomplete="off" size="40" value="${passer.questionForPriTag2}" /><br />
			注入信息：<input type="text" name="passer.priTag2" autocomplete="off"
				<s:if test="#request.passer.lockPriTag2">readonly</s:if> size="40"
				value="${passer.priTag2}" /></td>
		</tr>



	</table>
	<p align="center"><input type="submit" value="提交" name="B1">
	<input type="reset" value="复位" name="B2"></p>
	</form>


	<s:action name="user_findListByPasser">
		<s:param name="state">
			<s:property value="#parameters.state" />
		</s:param>
		<s:param name="page">
			<s:property value="#parameters.page" />
		</s:param>
		<s:param name="pageSize">
			<s:property value="#parameters.pageSize" />
		</s:param>
	</s:action>



	<table align="center">
		<tr>
			<td colspan="5" align="center"><s:if test="#parameters.state">已审核</s:if>
			<s:else>未审核</s:else> 人员列表 切换至 <a
				href='${pageContext.request.contextPath}/pass/passerCenter.jsp?state=<s:if test="#parameters.state">false</s:if><s:else>true</s:else>'><s:if
				test="#parameters.state">未通过</s:if><s:else>已通过</s:else>的用户列表</a></td>
		</tr>
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>注入信息1</td>
			<td>注入信息2</td>
			<td>操作</td>
		</tr>
		<s:iterator value="#request.rs.list" id="x" status="st">
			<s:if test='!#x.state'>

				<form method="post"
					action='${pageContext.request.contextPath}/pass/user_passManually.action?uid=${x.id}&pass=true&state=<s:property value="#parameters.state"/>&page=<s:property value="#parameters.page"/>&pageSize=<s:property value="#parameters.pageSize"/>'>
			</s:if>


			<tr>
				<td></td>
				<td>${x.userName}</td>
				
				<td><input type="text" name="pi.priTag1" <s:if test='!#request.passer.priTag1.equals("?") && !#x.priTag1.trim().equals("")'>readonly</s:if>
					size="12" value="${x.priTag1}" /></td>
				<td><input type="text" name="pi.priTag2" <s:if test='!#request.passer.priTag2.equals("?") && !#x.priTag2.trim().equals("")'>readonly</s:if>
					size="12" value="${x.priTag2}" /></td>
				<td><s:if test='#x.state'>
					<a
						href='${pageContext.request.contextPath}/pass/user_passManually.action?uid=${x.id}&pass=false&state=<s:property value="#parameters.state"/>&page=<s:property value="#parameters.page"/>&pageSize=<s:property value="#parameters.pageSize"/>'>取消审核</a>
				</s:if><s:else>
					<input type="submit" value="审 核" name="B1">

				</s:else></td>
			</tr>
			<s:if test='!#x.state'>
				</form>
			</s:if>

		</s:iterator>
		<tr>
			<td colspan="5" align="center"><s:if
				test="#request.rs.pageCount>0">
				<div class="list_oneline"><s:if test="#request.rs.pageCount>0">
					<s:if test="#request.rs.page>1">
						<a
							href='${pageContext.request.contextPath}/pass/passerCenter.jsp?state=<s:property value="#parameters.state"/>&page=${request.rs.page-1}&pageSize=${request.rs.pageSize}'>上一页</a>
					</s:if>
					<s:if test="#request.rs.page<#request.rs.pageCount">
						<a
							href='${pageContext.request.contextPath}/pass/passerCenter.jsp?state=<s:property value="#parameters.state"/>&page=${request.rs.page+1}&pageSize=${request.rs.pageSize}'>下一页</a>
					</s:if>
				</s:if> 共${request.rs.pageCount}页</div>
			</s:if></td>
		</tr>

	</table>

</s:if>
<s:else>您没有该权限！ 请<a
		href='${pageContext.request.contextPath}/login.action'>登录</a>
</s:else>

<script type="text/javascript">
	$("input[readOnly]").keydown(function(e) {
		e.preventDefault();
	});
</script>
</body>
</html>