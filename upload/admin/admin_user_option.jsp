<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
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
.list_1{ width:30%;border-left:0px; text-align:center;}
.list_2{width:70%; text-align:left;}
.list_3{width:10%;}
.list_4{width:27%;}
.list_5{width:8%;}
.list_6{width:10%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>用户注册及登录设置</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　用户注册及登录设置</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
<form method="post" action="admin_userOption_modify.action">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">

   <tr >
    <td class="list_7">开放注册：</td>
    <td class="list_8"><input type="radio" value="true" <s:if test="site.userRegAllow">checked</s:if> name="site.userRegAllow">开启&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.userRegAllow==false">checked</s:if>  name="site.userRegAllow" value="false">关闭</td>
   </tr>
        
      
   <s:action name="admin_userGroup_query"></s:action>
   
   <tr >
    <td class="list_7">默认用户组：</td>
    <td class="list_8">
<select size="1" name="site.userGroupOfAutoPassed">
	<option <s:if test="site.userGroupOfAutoPassed==0">selected</s:if> value="0">无</option>
	<s:iterator value="#request.allGroup" id="x" status="st"> 
		<option value="${x.id}" <s:if test="#x.id==site.userGroupOfAutoPassed">selected</s:if>>${x.groupName}</option>
	</s:iterator>
  </select>
</td>
   </tr>
   
   
   <tr >
    <td class="list_7">新用户验核方式：</td>
    <td class="list_8">
<select size="1" name="site.actAfterReg">
	<option <s:if test="site.actAfterReg==0">selected</s:if> value="0">自动归属默认组</option>
	<option <s:if test="site.actAfterReg==1">selected</s:if> value="1">邮箱验证</option>
	<option <s:if test="site.actAfterReg==2">selected</s:if> value="2">选择审核员验证</option>
	
  </select>
</td>
   </tr>
   
   
  
   
   
   <tr >
    <td class="list_7">一个Email信箱只允许注册一个帐号：</td>
    <td class="list_8"><input type="radio" value="true" <s:if test="site.oneMailForReg">checked</s:if> name="site.oneMailForReg">开启&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.oneMailForReg==false">checked</s:if>  name="site.oneMailForReg" value="false">关闭</td>
   </tr>
   
   <tr >
    <td class="list_7">是否允许用户登录：</td>
    <td class="list_8"><input type="radio" value="true" <s:if test="site.userLoginAllow">checked</s:if> name="site.userLoginAllow">开启&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.userLoginAllow==false">checked</s:if>  name="site.userLoginAllow" value="false">关闭</td>
   </tr>
   
   
    <tr >
    <td class="list_7">禁止登录时允许某些用户组登录：</td>
    <td class="list_8"><input type="text" name="site.userGroupWhenNotLoginAllow" size="50" value="${site.userGroupWhenNotLoginAllow}"></td>
   </tr>
   
   <tr >
    <td class="list_7">用户发文自动审核：</td>
    <td class="list_8"><input type="radio" value="true" <s:if test="site.articleAutoPass">checked</s:if> name="site.articleAutoPass">开启&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.articleAutoPass==false">checked</s:if>  name="site.articleAutoPass" value="false">关闭</td>
   </tr>
   
    <tr >
    <td class="list_7">评论控制：</td>
    <td class="list_8">
<select size="1" name="site.modeOfComment">
    <option value="1" <s:if test="site.modeOfComment==1">selected</s:if>>禁止评论</option>
    <option value="2" <s:if test="site.modeOfComment==2">selected</s:if>>仅允许注册用户发表评论</option>
		<option value="3" <s:if test="site.modeOfComment==3">selected</s:if>>允许游客发表评论</option>
    
  </select>
</td>
   </tr>
   
   <tr >
    <td class="list_7">评论显示控制：</td>
    <td class="list_8"><input type="radio" value="true" <s:if test="site.commentsOpen">checked</s:if> name="site.commentsOpen">完全开放&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.commentsOpen==false">checked</s:if>  name="site.commentsOpen" value="false">仅对有管理权用户开放（作者或管理员）</td>
   </tr>
   
   <tr >
    <td class="list_7">用户发文统计指定用户组：：</td>
    <td class="list_8"><input type="text" name="site.userGroupsForStat" size="50" value="${site.userGroupsForStat}"></td>
   </tr>  
   
   
  </table>
   <div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 


</form> 
 </div>
</div>
<font color="#FF0000"><s:property value="actionErrors[0]"/></font>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>