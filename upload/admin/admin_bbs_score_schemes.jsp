<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />

<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
body{ font-size:12px; font-family:Arial, Helvetica, sans-serif; margin:0; padding:0 0 0 5px;}

a:link{text-decoration : none ;color : #333 ;} /*链接*/
a:visited {text-decoration : none ;color : #333 ;} /*已访问链接*/
a:hover {text-decoration : none ;color : #f00 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #333 ;}  /*鼠标按下链接*/

.aebox{ width:100%;}
.aeheadbox{ background:url(images/btbgm.jpg); height:34px;}
.aeheadl{ height:34px; width:5px; background:url(images/btbgl.jpg) no-repeat; float:left;}
.aeheadm{ height:25px;  padding: 9px 0 0 15px; color:#FFF; font-size:14px;font-weight:bold; float:left;}
.helplist{height:25px;  padding: 5px 0 0 30px; float:left;}
.aeheadr{ height:34px; width:3px; background:url(images/btbgr.jpg) no-repeat; float:right;}

.aelist{ width:99%; margin:auto;}
.aelist tr{ background:#f5f5f5;}
.trlist td{background:#aaa; height:24px;font-weight:bold;color:#FFF;}
.aelist tr:hover{ background:#e6e6e6;}
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_8{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:center;}
.list_2{width:20%; text-align:left;}
.list_3{width:35%;}
.list_4{width:20%;}
.list_5{width:20%;}
.list_6{width:10%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>积分用户组设置</title>
</head>

<body>
	
 
  
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　积分方案</div>
  <div class="aeheadr"></div>
 </div>
  
  
<div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">方案名称</td>
    <td class="list_3">作者</td>
    <td class="list_4">状态</td>
    <td class="list_5">操作</td>
   </tr>

<s:action name="admin_score_schemes_queryAll"></s:action>
<s:iterator value="#request.schemeAll" id="x" status="st"> 
  <tr >
  	
  	<td class="list_1">${st.index+1}</td>
  	<td class="list_2">${x.schemeName}</td>
    <td class="list_3">${x.author}</td>
    <td class="list_4"><s:if test="state"><b>当前默认使用</b></s:if><s:else>未使用 <a href="admin_score_schemes_use.action?id=${x.id}">设为默认</a></s:else></td>
    <td class="list_5"><a href="admin_score_schemes_del.action?id=${x.id}" onclick="{if(confirm('确定删除积分方案“${x.schemeName}”吗?')){return true;}return false;}">删除</a> <a href="admin_bbs_score_groups_edit.jsp?id=${x.id}">用户组详情</a> <a href="admin_bbs_score_scheme_edit.jsp?id=${x.id}">分值</a></td>
	
  </tr>
  </s:iterator>
  </table>
</div>
<div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　新建积分方案</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_score_schemes_add.action">
  <tr >
    <td class="list_a">方案名称：</td>
    <td class="list_b"><input type="text" name="scheme.schemeName" size="30"></td>
  </tr>
  <tr >
    <td class="list_a">作者：</td>
    <td class="list_b"><input type="text" name="scheme.author" size="30"></td>
  </tr>
  <tr >
    <td class="list_a">说明：</td>
    <td class="list_b"><input type="text" name="scheme.description" size="20"></td>
  </tr>
  
  
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 

</div>
</div>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>
