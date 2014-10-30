<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<style type=text/css>
body { background:#CAD7F7; font-size: 12px; margin-top:0px;
SCROLLBAR-FACE-COLOR: #799AE1; SCROLLBAR-HIGHLIGHT-COLOR: #799AE1; 
SCROLLBAR-SHADOW-COLOR: #799AE1; SCROLLBAR-DARKSHADOW-COLOR: #799AE1; 
SCROLLBAR-3DLIGHT-COLOR: #799AE1; SCROLLBAR-ARROW-COLOR: #FFFFFF;
SCROLLBAR-TRACK-COLOR: #AABFEC;
}
TD {
	FONT-SIZE: 12px;
}
INPUT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
TEXTAREA {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SELECT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SPAN {
	FONT-SIZE: 12px; POSITION: static
}
A:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
a:hover  { color:#428EFF;text-decoration:underline; }
A.highlight:link {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:visited {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:hover {
	COLOR: red
}
A.thisclass:link {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:visited {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:hover {
	FONT-WEIGHT: bold
}
A.navlink:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:hover {
	COLOR: #003399; TEXT-DECORATION: none
}
.twidth {
	WIDTH: 760px
}
.content {
	FONT-SIZE: 14px; MARGIN: 5px 20px; LINE-HEIGHT: 140%; FONT-FAMILY: Tahoma,宋体
}
.aTitle {
	FONT-WEIGHT: bold; FONT-SIZE: 15px
}
TD.forumHeaderBackgroundAlternate {
	BACKGROUND-IMAGE: url(./images/admin_top_bg.gif); COLOR: #000000; BACKGROUND-COLOR: #799ae1
}
#TableTitleLink A:link {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:visited {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:active {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:hover {
	COLOR: #ffffff; TEXT-DECORATION: underline
}
TD.forumRow {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #F1F3F5; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TH {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(./images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR: #4455aa
}
TD.bodytitle {
	BACKGROUND-IMAGE: url(./images/admin_bg_2.gif)
}
TD.bodytitle1 {
	BACKGROUND-IMAGE: url(./images/admin_bg_3.gif)
}
TD.tablebody1 {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #bebbdb; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TD.forumRowHighlight {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #E4EDF9; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
.tableBorder {
	BORDER-RIGHT: #183789 1px solid; BORDER-TOP: #183789 1px solid; BORDER-LEFT: #183789 1px solid; WIDTH: 98%; BORDER-BOTTOM: #183789 1px solid; BACKGROUND-COLOR: #ffffff
}
.tableBorder1 {WIDTH: 98%; }
.helplink {
	FONT: 10px verdana,arial,helvetica,sans-serif; CURSOR: help; TEXT-DECORATION: none
}
.copyright {
	PADDING-RIGHT: 1px; BORDER-TOP: #6595d6 1px dashed; PADDING-LEFT: 1px; PADDING-BOTTOM: 1px; FONT: 11px verdana,arial,helvetica,sans-serif; COLOR: #4455aa; PADDING-TOP: 1px; TEXT-DECORATION: none
}
</style>
<title>用户资料修改</title>
</head>

<body>
<div align="center">

 <table  width="100%" border="0" class="tableBorder" align=center>
<tr><th class="tableHeaderText" height=25>用户资料修改</th>
  
  </center>
<tr><td class="bodytitle" height=23  align="center">
 
    
<div align="center">
<form name="f1" method="post" action="admin_user_modify.action?userInf.id=${user.id}&group=&page=<s:property value="page" />&pageSize=<s:property value="pageSize" />" >
 <table cellspacing="0" cellpadding="0" border="0" width="99%" align="center">
<tr><td bgcolor="#DDE3EC">
<table border="0" cellspacing="1" cellpadding="4" width="100%">
<tr>
<td colspan="2" class="header">用户注册 - 必填内容</td>
</tr>

<tr>
<td bgcolor="#F8F9FC" width="21%">用户名:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><b>${user.userName}</b></td>
</tr>
<s:action name="admin_userGroup_query"></s:action>
 
<tr>
<td bgcolor="#F8F9FC" width="21%">用户所在用户组:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left">
	<select size="1" name="userInf.userGroup.id">
	<option <s:if test="user.userGroup==null">selected</s:if> value="0">不在任何用户组</option>
	<s:iterator value="#request.allGroup" id="x" status="st"> 
	
	
		<option value="${x.id}" <s:if test="#x.id==user.userGroup.id">selected</s:if>>${x.groupName}</option>
	</s:iterator>
  </select>
</tr>

<tr>
<td bgcolor="#F8F9FC" width="21%">用户状态:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><p align="left"><input type="radio" value="true" <s:if test="user.state">checked</s:if> name="userInf.state">正常<input type="radio" value="false" <s:if test="user.state==false">checked</s:if> name="userInf.state">禁用</p></td>
</tr>

<tr>
<td bgcolor="#F8F9FC" width="21%">用户积分:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><p align="left"><input autocomplete="off" type="text" name="userInf.allScore" size="25" value="${user.allScore}"></p></td>
</tr>


<tr>
<td bgcolor="#F8F9FC" width="21%">密码:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input autocomplete="off" type="text" name="password" size="25">不修改请置空！</td>
</tr>

<tr>
<td bgcolor="#F8F9FC" width="21%">邮箱地址:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.email" size="25" value="${user.email}"></td>
</tr>


<tr>
<td colspan="2" class="header">用户个人资料 - 选填内容</td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">个人头像:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.avatarFile" size="25" value="${user.avatarFile}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">个人签名:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.personalSignature" size="25" value="${user.personalSignature}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">真实姓名:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.trueName" size="25" value="${user.trueName}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">性别:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="radio" value="1" <s:if test="user.sex">checked</s:if> name="sex">男 <input type="radio" <s:if test="user.sex==false">checked</s:if> name="sex" value="2">女 <input type="radio" <s:if test="user.sex==null">checked</s:if> name="sex" value="0">秘密</td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">出生年月:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="birthdayYear" size="4" maxlength="4" value="<s:if test="birthdayYear>0">${birthdayYear}</s:if>">年<input type="text" name="birthdayMonth" size="2" maxlength="2" value="<s:if test="birthdayMonth>0">${birthdayMonth}</s:if>">月<input type="text" name="birthdayDay" size="2" maxlength="2"  value="<s:if test="birthdayDay>0">${birthdayDay}</s:if>">日</td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">国家:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.country" size="25" value="${user.country}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">省份:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.province" size="25" value="${user.province}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">城市:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.city" size="25" value="${user.city}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">住址:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.address" size="25" value="${user.address}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">联系电话:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.phone" size="25" value="${user.phone}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">手机:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.mobile" size="25" value="${user.mobile}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">QQ:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.qq" size="25" value="${user.qq}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">MSN:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.msn" size="25" value="${user.msn}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">注入信息1:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.priTag1" size="25" value="${user.priTag1}"></td>
</tr>
<tr>
<td bgcolor="#F8F9FC" width="21%">注入信息2:</td>
<td bgcolor="#FFFFFF"  width="79%"  align="left"><input type="text" name="userInf.priTag2" size="25" value="${user.priTag2}"></td>
</tr>


  <tr>
    <td width="40%" class="forumRow" align="right" colspan="2">
      <p align="center"><input type="submit" value="提交"></p>
    </td>
     </tr>

  <tr>
    <td width="40%" class="forumRow" align="right" colspan="2">
      <p align="center"><a href="./admin_user_findList.action?group=&page=">返回</a></p>
    </td>
     </tr>

   </table>
</form> 
     
</div>
   </td>
     </tr>
   </table>
</div>

</body>

</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>