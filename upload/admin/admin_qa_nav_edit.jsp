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

<style type=text/css>
body { background:#CAD7F7; font-size: 12px; margin-top:0px;
SCROLLBAR-FACE-COLOR: #799AE1; SCROLLBAR-HIGHLIGHT-COLOR: #799AE1; 
SCROLLBAR-SHADOW-COLOR: #799AE1; SCROLLBAR-DARKSHADOW-COLOR: #799AE1; 
SCROLLBAR-3DLIGHT-COLOR: #799AE1; SCROLLBAR-ARROW-COLOR: #FFFFFF;
SCROLLBAR-TRACK-COLOR: #AABFEC;
}
TD {
	FONT-SIZE: 12px
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
<title>问答系统栏目修改</title>
</head>

<body>

<table width="100%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1 solid #808080">

<tr><th class="tableHeaderText" height=25 colspan="2">问答系统栏目修改</th>
	<form method="POST" action="admin_qa_nav_modify.action?qn.id=${qn.id}">
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">栏目名：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.title" size="50"
				value="${qn.title}">
    </td>
  </tr>
  
<tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">分类：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><select size="1" name="qn.parentNav.id">
    <s:if test="qn.parentNav==null"><option value="0" selected>自已为分类</option></s:if>
    <s:else>
    <s:action name="admin_qa_nav_findAllClass">
	</s:action>
    <s:iterator value="#request.qaClassAll" id="y" status="sd">
    <option <s:if test="qn.parentNav.id==#y.id">selected</s:if> <s:if test="qn.id==#y.id">disabled="true"</s:if> value="${y.id}">${y.title}</option>
    </s:iterator>
    </s:else>
    
    </select>
    </td>
  </tr>
  
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">状态：</p>
    </td>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="radio" value="true" <s:if test="#request.qn.state">checked</s:if> name="qn.state">正常<input type="radio"  <s:if test="#request.qn.state==false">checked</s:if> name="qn.state" value="false">禁用</p>
    </td>
  </tr>
  
  <s:action name="admin_qa_style_query"></s:action>

		<tr>
			<td width="35%" class="forumRow" align="right" ><p align="right">栏目风格：</p></td>
			<td width="65%" class="forumRow"><p align="left"><select size="1" name="qn.style.id">
				<option value="0">--系统默认--</option>
				
				<s:iterator value="#request.qaStyleAll" id="x" status="st">
					<option <s:if test="#x.id==qn.style.id">selected</s:if>
						value=${x.id}>${x.styleName}</option>
				</s:iterator>

			</select>
			</p>
			</td>
		</tr>
	
	<s:if test="qn.parentNav!=null" >	
	
		
	 <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">是否需要登录后发表：</p>
    </td>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="radio" value="true" <s:if test="#request.qn.loginNeed">checked</s:if> name="qn.loginNeed">是<input type="radio"  <s:if test="#request.qn.loginNeed==false">checked</s:if> name="qn.loginNeed" value="false">否</p>
    </td>
  </tr>	
		
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">是否采用自动邮件回复：</p>
    </td>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="radio" value="true" <s:if test="#request.qn.sendMail">checked</s:if> name="qn.sendMail">是<input type="radio"  <s:if test="#request.qn.sendMail==false">checked</s:if> name="qn.sendMail" value="false">否</p>
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">显示数据条数：</p>
    </td>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="text"
				name="qn.numberShowOn" size="50"
				value="${qn.numberShowOn}"> 0为默认值</p>
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">描述：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.description" size="50"
				value="${qn.description}">
    </td>
  </tr>
  

  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">管理员邮箱：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.adminEmail" size="50"
				value="${qn.adminEmail}">
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">用于发送邮件的邮箱：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.sendEmail" size="50"
				value="${qn.sendEmail}">注：无则使用系统设置
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">用于发送邮件的服务器（下同）：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.serverOfSendEmail" size="50"
				value="${qn.serverOfSendEmail}">
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">用户名：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.usernameOfSendEmail" size="50"
				value="${qn.usernameOfSendEmail}">
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">密码：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.passwordOfSendEmail" size="50"
				value="${qn.passwordOfSendEmail}">
    </td>
  </tr>
  
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">邮件标题：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.sendTitle" size="50"
				value="${qn.sendTitle}">
    </td>
  </tr>
  
  

		
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">增加时Email回复内容模板：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><textarea rows="5" name="qn.sendTemplateForAdd" cols="60">${qn.sendTemplateForAdd}</textarea>
    </td>
  </tr>
   <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">处理完毕时Email回复内容模板：</p>
    </td>
    <td width="65%" class="forumRow" align="left"><textarea rows="5" name="qn.sendTemplateForReply" cols="60">${qn.sendTemplateForReply}</textarea>
    </td>
  </tr>
  </s:if>
    <tr>
			<td width="35%" class="forumRow" align="right" >
      <p align="right">静态文件所在文件夹名：</p></td>
			<td width="65%" class="forumRow" align="left"><input type="text"
				name="qn.staticHtmlFolder" size="50"
				value="${qn.staticHtmlFolder}"> <input type="checkbox"
						name="qn.refuseStaticHtml" value="true" 
						<s:if test="qn.refuseStaticHtml" >checked</s:if>>强制不静态</td>
		</tr>
  
  <tr>
    <td width="100%" class="forumRow" colspan="2">
      <p align="center"><input type="submit" value="提交" name="B1"></p>
    </td>
  </tr>
  
   <tr>

    <td width="100%" class="forumRow" colspan="2">
      <p align="center"><a href="admin_qa_nav_list.jsp">返回</a></p>
    </td>
  </tr>
  
  </form>
</table>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>