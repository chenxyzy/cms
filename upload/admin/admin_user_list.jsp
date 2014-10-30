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
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7,.list_8,.list_10,.list_11{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_9{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:right;}
.list_2{width:10%; text-align:left;}
.list_3{width:4%;}
.list_4{width:3%;}
.list_5{width:8%;}
.list_6{width:15%;}
.list_7{width:10%;}
.list_8{width:15%;}
.list_9{width:10%;}
.list_10{width:17%;}
.list_a{text-align:right;width:40%;}
.list_b{width:60%;text-align:left;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>用户列表</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　用户列表</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">用户名</td>
    <td class="list_4">ID</td>
    <td class="list_3">状态</td>
    <td class="list_4">积分</td>
    <td class="list_5">所属用户组</td>
    <td class="list_6">注册时间</td>
    <td class="list_7">注册IP</td>
    <td class="list_8">上次登录时间</td>
    <td class="list_9">上次登录IP</td>
    <td class="list_10">操作</td>
   </tr>

   <s:iterator value="rs.list" id="x" status="st">      
   <tr >
    <td class="list_1">${(rs.page-1)*rs.pageSize+st.index+1}</td>
    <td class="list_2">${x.userName} </td>
    <td class="list_4">${x.id} </td>
    
    <td class="list_3"><s:if test="state">正常</s:if><s:else><font color="#FF0000">禁用</font></s:else></td>
    <td class="list_4">${x.allScore}</td>
    <td class="list_5">${x.userGroup.groupName}</td>
    <td class="list_6">${x.regTimstamp}</td>
    <td class="list_7">${x.regIp}</td>
    <td class="list_8">${x.lastLoginTimstamp}</td>
    <td class="list_9">${x.lastLoginIp}</td>
    <s:action name="qq_chk">
		<s:param name="user.id">${x.id}</s:param>
	</s:action>
    <td class="list_10"><s:if test='#request.qq_ic.trim().equals("yes")'><a href="qq_clear.action?user.id=${x.id}&f=admin">清除互联</a></s:if> <a href="admin_user_del.action?id=${x.id}&page=${rs.page}&pageSize=${rs.pageSize}" onclick="{if(confirm('确定删除用户“${x.userName}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a><s:if test="state"> <a href="admin_user_changeState.action?state=false&id=${x.id}&page=${rs.page}&pageSize=${rs.pageSize}">禁用</a> </s:if><s:else> <a href="admin_user_changeState.action?state=true&id=${x.id}&page=${rs.page}&pageSize=${rs.pageSize}">恢复</a></s:else>  <a href="admin_user_findByID.action?id=${x.id}&page=${rs.page}&pageSize=${rs.pageSize}">修改</a></td>

   </tr>
   
</s:iterator>
  </table>
  <div class="list_oneline"><s:if test="rs.pageCount>0"><s:if test="rs.page>1"><a href="admin_user_findList.action?groupId=<s:property value="#parameters.groupId"/>&order=<s:property value="#parameters.order"/>&orderMod=<s:property value="#parameters.orderMod"/>&findStr=<s:property value="#parameters.findStr"/>&page=${rs.page-1}&pageSize=${rs.pageSize}">上一页</a></s:if> <s:if test="rs.page<rs.pageCount"><a href="admin_user_findList.action?groupId=<s:property value="#parameters.groupId"/>&order=<s:property value="#parameters.order"/>&orderMod=<s:property value="#parameters.orderMod"/>&findStr=<s:property value="#parameters.findStr"/>&page=${rs.page+1}&pageSize=${rs.pageSize}">下一页</a></s:if></s:if> 共${rs.pageCount}页</div>
  <div class="list_oneline2">共有${rs.count}&nbsp;个用户   排序(按用户名  <a href='admin_user_findList.action?groupId=<s:property value="#parameters.groupId"/>&findStr=<s:property value="#parameters.findStr"/>&order=1&orderMod=0'> ↑ </a>　<a href='admin_user_findList.action?groupId=<s:property value="#parameters.groupId"/>&findStr=<s:property value="#parameters.findStr"/>&order=1&orderMod=1'> ↓ </a>)</div>
   </div>
   <s:action name="admin_userGroup_query"></s:action>
   <div class="list_oneline">
  <form method="POST" action="admin_user_findList.action">
   选择用户组：<select size="1" name="groupId">
	<option <s:if test="#parameters.groupId[0]==0">selected</s:if> value="0">全部用户</option>
	<s:iterator value="#request.allGroup" id="x" status="st"> 
	
	
		<option value="${x.id}" <s:if test="#x.id==#parameters.groupId[0]">selected</s:if>>${x.groupName}</option>
	</s:iterator>
  </select> 查找： <input type="text" name="findStr" size="20"><input type="submit" value="确定" name="B1">
  </form>

  </div>

  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　快速增加用户</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <s:property value="actionErrors[0]"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_user_addQuickly.action">
  <tr >
    <td class="list_a">用户名：</td>
    <td class="list_b"><input type="text" name="userInf.userName" size="60"></td>
  </tr>
 
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 
说明：快速增加用户将自动读取配置文件/WEB-INF/classes/resourceApplication_zh_CN.properties中的默认用户密码和Email。
<br>当前使用的默认密码为：<s:i18n name="ApplicationResources"><s:text name="lerx.default.user.password" /></s:i18n>
<br>当前使用的默认用户邮箱为：<s:i18n name="ApplicationResources"><s:text name="lerx.default.user.email" /></s:i18n>
<br>新增用户状态默认为：<s:i18n name="ApplicationResources"><s:text name="lerx.default.user.state" /></s:i18n> (enabled为正常，disabled或其它为禁用)
<br>当前用户级联状态为：<s:i18n name="ApplicationResources"><s:text name="lerx.rule.cascade.thread.delete" /></s:i18n> (true为删除用户时也将删除该用户全部文章，false或其它为不删除并置文章用户为空)
</div>
</div>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>