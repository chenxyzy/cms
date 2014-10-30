<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.List"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<title>栏目顺序设置</title>
</head>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<body>
<s:set name="pid" value="#parameters.parentGroupID[0]"></s:set>
<div align="center">
  <center>
  <table border="0" width="100%">
    <tr>
      <td width="100%"  align="center"  class="bodytitle" >

<!--栏目设置区--> 
 <div align="center">
 
 <table  width="100%" border="0" class="tableBorder" align=center>
<tr><th height=25>栏目顺序设置</th>
  

  
  
  </center>
<tr><td  height=23  align="center" class="bodytitle">
 
 <!--选择栏目区--> 

<table width="100%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1 solid #808080">

<tr><th class="tableHeaderText" height=25>选择栏目</th>
  

  
  
  </center>
<tr><td class="bodytitle" height=23  align="center">
 <s:action name="admin_article_group_findAll"></s:action>
    
<form method="POST" action="admin_article_group_order.jsp">
<table border="0" width="100%">

   <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">选择<s:property value="#parameters.parentGroupID"/>父栏目：</p>
    </td>
  <center>

    <td width="65%" class="forumRow" >
      <p align="left"><select size="1" name="parentGroupID">
				<option value="0">根栏目</option>
				<s:iterator value="#request.groupAll" id="x" status="st">
					<option
						<s:if test="#x.articleGroup.id==#parameters.parentGroupID[0]">selected</s:if>
						value="${x.articleGroup.id}">${x.prefixStr}
					${x.articleGroup.groupName}</option>
				</s:iterator>

			</select>&nbsp;&nbsp;&nbsp;<input type="submit" value="提交" name="B1"></p>
    </td>
  </tr>
  
  
  <tr>
    <td width="35%" class="forumRow" align="center"  colspan="2" ></td>
  </tr>
</table>
</form>

</td>
  </tr>

</table>




<!--选择栏目区结束--> 
	

<s:action name="admin_article_group_findAllByParent">
<s:param name="parentGroupID"><s:property value="#parameters.parentGroupID"/></s:param>
</s:action>
<table id="tc" width="100%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1 solid #808080">
<tr height="23" valign="middle" align="center"> 
    <th width="3%">序号</th>
    	<th width="3%">ID</th>
    	<th width="12%">栏目名称</th>
    	<th width="12%">栏目序号值</th>
<th width="12%">状态</th>
   
    <th width="10%">排列序号</th>
    
  </tr>
  <script type="text/jscript">
   var list=new Array();
  </script>

	<s:iterator value="#request.subGroupAll" id="z" status="zt">
		<script type="text/jscript">
			list.push(<s:property value="#z.id"/>);
		</script>

	</s:iterator> 
<s:iterator value="#request.subGroupAll" id="y" status="rt">


<tr height="25" bgcolor="#FFFFFF" valign="middle" align="center" onmouseover="this.style.backgroundColor='E4E8EF'" onmouseout="this.style.backgroundColor=''"> 


    <td width="3%">${rt.index+1}</td>
    <td width="3%">${y.id}</td>
    <td width="12%" align="left">${y.groupName}</td>
    <td width="12%">${y.displayOrder}</td>
    
<td width="12%"><s:if test="#y.state">正常</s:if><s:else><font color="#FF0000">隐藏</font></s:else></td>
    <td width="10%">
    <table border="0" width="100%">
    <tr>
    
      <td width="20%" align="center"></td>
      <td width="80%" align="center"> <s:if test="#rt.index > 0">&nbsp; <span id="666<s:property value="#y.id"/>"><a id="up<s:property value="#y.id"/>" Title="向上" href=""><b>↑</b></a></span>&nbsp;</s:if> <s:if test="#rt.index<(#request.subGroupAll.size()-1)"><a Title="向下" id="down<s:property value="#y.id"/>" href=""><b>↓</b></a></s:if>
      
<script type="text/jscript">
	$("#up<s:property value="#y.id"/>").attr("href","admin_article_group_swap.action?parentGroupID=<s:property value="#pid"/>&sid=<s:property value="#y.id"/>&tid="+list[<s:property value="#rt.index"/>-1]);
	$("#down<s:property value="#y.id"/>").attr("href","admin_article_group_swap.action?parentGroupID=<s:property value="#pid"/>&sid=<s:property value="#y.id"/>&tid="+list[<s:property value="#rt.index"/>+1]);
</script>
      
      </td>
      
    </tr>
  </table>
</td>
  
  </tr>
 </s:iterator> 
  
</table>

</div>

 <!--栏目设置区结束-->
 
</td>
    </tr>
  </table>
  <br>
  
 </div> 
 
 
  
  </td>
    </tr>
  </table>
  
  
   </div> 
  </center>
</body>

</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>