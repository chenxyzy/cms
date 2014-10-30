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

a:link{text-decoration : underline;color : #333;} /*链接*/
a:visited {text-decoration : underline ;color : #333 ;} /*已访问链接*/
a:hover {text-decoration : underline;color : #f00 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #333 ;}  /*鼠标按下链接*/

.aebox{ width:100%;}
.aeheadbox{ background:url(images/btbgm.jpg); height:34px;}
.aeheadl{ height:34px; width:5px; background:url(images/btbgl.jpg) no-repeat; float:left;}
.aeheadm{ height:25px;  padding: 9px 0 0 15px; color:#FFF; font-size:14px;font-weight:bold; float:left;}
.aeheadr{ height:34px; width:3px; background:url(images/btbgr.jpg) no-repeat; float:right;}

.aelist{ width:99%; margin:auto; margin-bottom:5px;}
.aelist tr{ background:#f5f5f5;}
.trlist td{background:#aaa; height:24px;font-weight:bold;color:#FFF;}
.aelist tr:hover{ background:#e6e6e6;}
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_8{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:right;}
.list_2{width:20%; text-align:left;}
.list_3{width:20%;}
.list_4{width:20%;}
.list_5{width:15%;}
.list_6{width:20%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>用户组列表</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　用户组列表</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">组名</td>
    <td class="list_3">状态</td>
    <td class="list_4">用户数</td>
    <td class="list_6">操作</td>
   </tr>

   <s:iterator value="#request.allGroup" id="x" status="st">      
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.groupName} </td>
    
    <td class="list_3"><s:if test="state">正常</s:if><s:else><font color="#FF0000">禁用</font></s:else></td>
    <td class="list_3">${x.userCount}</td>
    <td class="list_6"><s:if test="userCount==0"><a href="admin_userGroup_del.action?id=${x.id}" onclick="{if(confirm('确定删除用户组“${x.groupName}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a></s:if><s:if test="state"> <a href="admin_userGroup_changeState.action?state=false&id=${x.id}">禁用</a> </s:if><s:else> <a href="admin_userGroup_changeState.action?state=true&id=${x.id}">恢复</a></s:else>  <a href="admin_userGroup_findByID.action?state=false&id=${x.id}">修改</a></td>

   </tr>
   
</s:iterator>
  </table>
  <div class="list_oneline2">共有<s:property value="#request.allGroup.size()"/>&nbsp;个用户组</div>
   </div>

  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　增加用户组</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <s:property value="actionErrors[0]"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_userGroup_add.action">
  <tr >
    <td class="list_7">用户组名：</td>
    <td class="list_8"><input type="text" name="userGroup.groupName" size="40"></td>
  </tr>
  <tr >
    <td class="list_7">权限码：</td>
    <td class="list_8"><input type="text" name="userGroup.powerMask" size="40"> (可增加后进行列表式编辑)</td>
  </tr>
   
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 

</div>
</div>
<center><a href="admin_userGroup_repair.action">全部修复</a></center>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>