<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="spaceadmin_const.jsp" />
<s:if test="#session.LerxSpaceAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Lerx用户空间后台管理页面</title>
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
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>

</head>

<body bgcolor="#E8E8E8">

<p>　</p>
<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" width="90%">
    <tr>
      <td width="100%" bgcolor="#FFFFFF">
      <div align="center">
        <table border="0" cellpadding="0" cellspacing="14" width="100%">
          <tr>
            <td width="100%"><font size="2"><br>
            </font>
            <p align="center"><b><font size="2">Lerx用户空间后台管理页面</font></b></p>
            <p><font size="2"><b>您使用的Lerx当前版本</b>：<s:text name="lerx.currentVersionNumber"></s:text> <s:text name="lerx.currentVersionBuild"></s:text></p><br>
            <script type="text/javascript" src='http://www.lerx.com/version/checkver.jsp?sn=<s:text name="lerx.currentVersion"></s:text>'></script>
            <br>
            </font> </p>
              <p align="center"><b><font size="2">Lerx</font></b><font size="2">版权所有　Copyright &copy; 2005-<span id="year"></span> 
              <a href="http://www.lerx.com" target="_blank">www.lerx.com</a>　 All Rights Reserved</font><p align="center"><font size="2">代码：<a href="mailto:lzhjy@163.com">lzh</a> 　全站美工：<a href="mailto:yanjpcn@qq.com">小燕子</a></font></td> 
          </tr>
        </table>
      </div>
      </td>
    </tr>
  </table>
  </center>
</div>
<%
	String contextPath = request.getContextPath();
%>
<script type="text/javascript">
year();
function year(){
	$("#year").html("正在检测...");
	$.ajax({
		url:'<%=contextPath%>/ajax_util_year.action',
		type: 'post',
		success:function(data){
		 $("#year").html(data);
		}
	 });
 
}
</script>
</body>

</html>

</s:if>
<s:else>
<%
	String path = request.getContextPath();
%>
<script>alert('对不起！您需要重新登录！');window.location='<%=path%>/<s:i18n name="ApplicationResources"><s:text name="lerx.adminFolder" /></s:i18n>/m_login.jsp';
	</Script>
</s:else>
