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
<title>网站其它设置</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　网站其它设置</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
<form method="post" action="admin_otherOption_modify.action">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
        
   <tr >
    <td class="list_1">发送邮件标题：</td>
    <td class="list_2"><input type="text" name="site.mailTitleFromSite" size="50" value="${site.mailTitleFromSite}"></td>
   </tr>
   
   
   
   <tr>
    <td class="list_1">新注册用户审核邮件正文：</td>
    <td class="list_2"><textarea rows="5" name="site.mailBodyForReg" cols="66">${site.mailBodyForReg}</textarea><br>认证URL用{$$userPassingUrl$$}标签插入</td>
   </tr>
   
   <tr>
    <td class="list_1">问答系统新增条目邮件正文：</td>
    <td class="list_2"><textarea rows="5" name="site.mailBodyForQaAdd" cols="66">${site.mailBodyForQaAdd}</textarea></td>
   </tr>
   <tr>
    <td class="list_1">问答系统反馈邮件正文：</td>
    <td class="list_2"><textarea rows="5" name="site.mailBodyForQaReply" cols="66">${site.mailBodyForQaReply}</textarea></td>
   </tr>
   
   <tr>
    <td class="list_1">发送邮件服务器：</td>
    <td class="list_2"><input type="text" name="site.mailSmtpServer" size="50" value="${site.mailSmtpServer}"></td>
   </tr>
   
   
   
   <tr>
    <td class="list_1">发送邮件服务器中的有效帐户：</td>
    <td class="list_2"><input type="text" name="site.mailSmtpUser" size="50" value="${site.mailSmtpUser}"></td>
   </tr>
   
   
   <tr>
    <td class="list_1">发送邮件服务器中的有效帐户的密码：</td>
    <td class="list_2"><input type="text" name="site.mailSmtpPws" size="50" value="${site.mailSmtpPws}"></td>
   </tr>
   
   <tr>
    <td class="list_1">发件人信箱（Email）：</td>
    <td class="list_2"><input type="text" name="site.mailSenderAddr" size="50" value="${site.mailSenderAddr}">（注：请尽可能保证该帐户在发送邮件服务器上，否则可能发送失败）</td>
   </tr>
   
   <tr>
    <td class="list_1">允许上传的文件类型：</td>
    <td class="list_2"><input type="text" name="site.fileUploadExtName" size="50" value="${site.fileUploadExtName}"></td>
   </tr>
   
   <tr>
    <td class="list_1">敏感词过滤：</td>
    <td class="list_2"><input type="text" name="site.filterWords" size="50" value="${site.filterWords}"></td>
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
