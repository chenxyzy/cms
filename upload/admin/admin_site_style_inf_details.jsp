<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
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
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_01,.list_02,.list_03,.list_04,.list_05,.list_06{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_8{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:15%;border-left:0px; text-align:center;}
.list_2{width:20%; text-align:left;}
.list_3{width:15%;}
.list_4{width:10%;}
.list_5{width:30%;}
.list_6{width:10%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_01{ width:5%;border-left:0px; text-align:center;}
.list_02{width:15%; text-align:left;}
.list_03{width:45%;text-align:left;}
.list_04{width:18%;}
.list_05{width:7%;}
.list_06{width:10%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
.trlist .list_01,.trlist .list_02,.trlist .list_03,.trlist .list_04,.trlist .list_05,.trlist .list_06,.trlist .list_07,.trlist .list_08{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>变量详情</title>
</head>

<body>
<s:action name="admin_site_style_findNameById">
	<s:param name="id"><s:property  value="#request.styleID"/></s:param>
</s:action>
  
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　“<s:property value="#request.currentStyleName"/>”——风格模板变量详情修改 <s:property value="#request.currentModelName"/> </div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
<form method="POST" action="admin_site_style_var_modify.action?styleID=${styleID}&id=${id}&col=${col}&table=${table}">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
   <tr class="trlist">
    <td class="list_1">变量名称：${col}</td>

   </tr>

   <tr>
    <td class="list_1"><textarea rows="20" name="colValue" cols="80">${returnStrFromSql}</textarea></td>

   </tr>
 
  </table>
 

 <div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
  </form>
  <div class="list_oneline"><a href="admin_site_style_getMetaDataByID.action?styleID=${styleID}&table=site_sitestyle">返回</a></div>
  
  
  
</div>
</div>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>