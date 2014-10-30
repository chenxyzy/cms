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
.aeheadr{ height:34px; width:3px; background:url(images/btbgr.jpg) no-repeat; float:right;}

.aelist{ width:99%; margin:auto;}
.aelist tr{ background:#f5f5f5;}
.trlist td{background:#aaa; height:24px;font-weight:bold;color:#FFF;}
.aelist tr:hover{ background:#e6e6e6;}
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7,.list_8{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_1{ width:20%;border-left:0px; text-align:right;}
.list_2{width:80%; text-align:left;}
.list_3{width:20%;}
.list_4{width:20%;}
.list_5{width:10%;}
.list_6{width:10%;}
.list_7{}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}

.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>网站基本设置</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　网站基本设置</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
<form method="post" action="admin_site_modify.action">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
        
   <tr >
    <td class="list_1">网站名称：</td>
    <td class="list_2"><input type="text" name="site.fullSiteName" size="50" value="${site.fullSiteName}"></td>
   </tr>
   
   
   
   <tr>
    <td class="list_1">网站简称：</td>
    <td class="list_2"><input type="text" name="site.shortSiteName" size="50" value="${site.shortSiteName}"></td>
   </tr>
   
   <tr>
    <td class="list_1">网站空间名称：</td>
    <td class="list_2"><input type="text" name="site.spaceName" size="50" value="${site.spaceName}"></td>
   </tr>
   
   <tr>
    <td class="list_1">网站论坛名称：</td>
    <td class="list_2"><input type="text" name="site.bbsName" size="50" value="${site.bbsName}"></td>
   </tr>
   
   
   
   <tr>
    <td class="list_1">网站Session键值：</td>
    <td class="list_2"><input type="text" name="site.sessionKey" size="50" value="${site.sessionKey}">最好使用域名以保证唯一性。</td>
   </tr>
   
   
   <tr>
    <td class="list_1">关键字：</td>
    <td class="list_2"><input type="text" name="site.keyWords" size="50" value="${site.keyWords}"></td>
   </tr>
   
   <tr>
    <td class="list_1">页面描述：</td>
    <td class="list_2"><textarea rows="5" name="site.description" cols="66">${site.description}</textarea></td>
   </tr>
   
   <tr>
    <td class="list_1">网址或域名：</td>
    <td class="list_2"><input type="text" name="site.host" size="50" value="${site.host}">不加http://</td>
   </tr>
   
   <tr>
    <td class="list_1">网站状态：</td>
    <td class="list_2"><input type="radio" value="true" <s:if test="site.state">checked</s:if> name="site.state">开启&nbsp;&nbsp;       
      <input type="radio" name="site.state" <s:if test="site.state==false">checked</s:if> value="false">关闭</td>
   </tr>
 
   <tr>
    <td class="list_1">网站关闭通知：</td>
    <td class="list_2"><input type="text" name="site.closeAnnounce" size="50" value="${site.closeAnnounce}"></td>
   </tr>
   
   <tr>
    <td class="list_1">限制可访问的IP范围：</td>
    <td class="list_2"><input type="text" name="site.hostVisitAllow" size="50" value="${site.hostVisitAllow}"></td>
   </tr>
   
    <tr>
    <td class="list_1">网站欢迎词：</td>
    <td class="list_2"><input type="text" name="site.welcomeStr" size="50" value="${site.welcomeStr}"></td>
   </tr> 
   

<tr>
    <td class="list_1">论坛状态：</td>
    <td class="list_2"><input type="radio" value="true" <s:if test="site.bbsState">checked</s:if> name="site.bbsState">开启&nbsp;&nbsp;       
      <input type="radio" <s:if test="site.bbsState==false">checked</s:if>  name="site.bbsState" value="false">关闭</td>
   </tr>   
   
   
  <tr>
    <td class="list_1">静态页设置：</td>
    <td class="list_2"><input type="radio" value="0" <s:if test="site.staticHtmlMode==0">checked</s:if> name="site.staticHtmlMode">不使用&nbsp;&nbsp;       
      <input type="radio"   name="site.staticHtmlMode" <s:if test="site.staticHtmlMode==1">checked</s:if> value="1">首页静态&nbsp;&nbsp;       
      <input type="radio"   name="site.staticHtmlMode" <s:if test="site.staticHtmlMode==2">checked</s:if> value="2">全部静态</td>
   </tr>   
   
   
  </table>
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