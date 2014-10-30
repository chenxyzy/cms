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
.list_2{width:10%; text-align:left;}
.list_3{width:15%;}
.list_4{width:15%;}
.list_5{width:15%;}
.list_6{width:15%;}
.list_7{width:25%;text-align:center; }
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>审核员列表</title>
</head>

<body>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　审核员列表</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">登陆帐号</td>
     <td class="list_3">授权用户组</td>
    <td class="list_4">注入信息1</td>
    <td class="list_5">注入信息2</td>
    <td class="list_6">状态</td>
    <td class="list_7">操作</td>
   </tr>

   <s:iterator value="passerAll" id="x" status="st">      
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.user.userName} </td>
    <td class="list_3">${x.toUG.groupName}</td>
    <td class="list_4"><table><tr><td align="center" width="50%">${x.priTag1}</td><td align="left" width="50%"> <s:if test="lockPriTag1"><img src="images/locked.jpg"></s:if></td></tr></table></td>
    <td class="list_5"><table><tr><td align="center" width="50%">${x.priTag2}</td><td align="left" width="50%"> <s:if test="lockPriTag2"><img src="images/locked.jpg"></s:if></td></tr></table></td>
    <td class="list_6"><s:if test="state">正常</s:if><s:else><font color="#FF0000">禁用</font></s:else></td>
    <td class="list_7"><a href="admin_passer_del.action?passer.id=${x.id}" onclick="{if(confirm('确定删除审核员“${x.user.userName}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a><s:if test="state"> <a href="admin_passer_changeState.action?state=false&passer.id=${x.id}">禁用</a> </s:if><s:else> <a href="admin_passer_changeState.action?state=true&passer.id=${x.id}">恢复</a></s:else>  <a href="admin_passer_queryByID.action?state=false&passer.id=${x.id}">修改</a></td>

   </tr>
</s:iterator>
  </table>
  <div class="list_oneline2">共有<s:property value="#request.adminAll.size()"/>&nbsp;位审核员</div>
   </div>

  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　增加审核员</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <s:property value="actionErrors[0]"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_passer_add.action">
  <tr >
    <td class="list_7">用户ID：</td>
    <td class="list_8"><input type="text" autocomplete="off" name="passer.user.id" size="60"></td>
  </tr>
  <s:action name="admin_userGroup_query"></s:action>
  <tr >
    <td class="list_7">授权用户组：</td>
    <td class="list_8"><select size="1" name="passer.toUG.id">
	
	<s:iterator value="#request.allGroup" id="x" status="st"> 
	
	
		<option value="${x.id}" >${x.groupName}</option>
	</s:iterator>
  </select></td>
  </tr>
  <tr >
    <td class="list_7">审核员信息1：</td>
    <td class="list_8"><input type="text" name="passer.passerInf1" autocomplete="off" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">审核员信息2：</td>
    <td class="list_8"><input type="text" name="passer.passerInf2" autocomplete="off" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">验证问题1：</td>
    <td class="list_8"><input type="text" name="passer.question1" autocomplete="off" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">验证答案1：</td>
    <td class="list_8"><input type="text" name="passer.answer1" autocomplete="off" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">验证问题2：</td>
    <td class="list_8"><input type="text" name="passer.question2" autocomplete="off" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">验证答案2：</td>
    <td class="list_8"><input type="text" name="passer.answer2" autocomplete="off" size="60"></td>
  </tr>
  
  <tr >
    <td class="list_7">注入信息1：</td>
    <td class="list_8">信息：<input type="text" name="passer.priTag1" autocomplete="off" size="40"><input type="checkbox"
						name="passer.lockPriTag1" >锁定 <br />提示：<input type="text" name="passer.questionForPriTag1" autocomplete="off" size="40">如果需要根据提示输入信息，请在<b>信息</b>内输入?(注：英文小写)</td>
  </tr>
  
  
  <tr >
    <td class="list_7">注入信息2：</td>
    <td class="list_8">信息：<input type="text" name="passer.priTag2" autocomplete="off" size="40"><input type="checkbox"
						name="passer.lockPriTag2" >锁定 <br />提示：<input type="text" name="passer.questionForPriTag2" autocomplete="off" size="40">同上</td>
  </tr>
  
      
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 

</div>
<div>
<a href="admin_passer_lock_batch.jsp">批量信息锁定</a>
</div>
<div><p>说明：1.审核员信息用于方便请求的新注册用户找到该审核员</p>
<p>　　　2.验证问题及答案用于检测请求的用户是否符合要求</p>
<p>　　　3.注入信息用于将特殊信息注入到成功通过审核的用户信息中，如果该信息是由用户输入，则应在信息中输入?。注意是“信息”而非“提示”输入 框</p>
<p>举例：一教育局下有若干学校。现将该地区的教师注册审核权交由学校专人审核。以上信息可依下述方面输入。</p>
<p>　　　1.用户ID：在用户列表中已经存在的用户的id号，可通过用户管理模块查询，为数字类型。如已有的一个用户lzh，id号是6，此处填6。</p>
<p>　　　2.授权用户组：比如“普通教师”（需在用户组中创建）。</p>
<p>　　　3.审核员信息1-2：指lzh的信息，比如1填“市实验小学”，2填“lzh”。这个是为了方便用户查找</p>
<p>　　　4.验证问题和答案1-2：应该是“市实验小学”的教师都知道的。比如：lzh的手机号码</p>
<p>　　　5.注入信息1-2：当存在信息时，该信息将注入到被成功审核的新用户注息中，如果要求用户填写，则在“信息”中输入?。</p>
<p>　　　　　如注入信息1 -->　　　　信息：市实验小学 　　　　提示：（不填）　　锁定：是</p>
<p>　　　　　　注入信息2 -->　　　　信息：?   　　　　提示：请输入您的姓名　　锁定：否</p>
<p>　　　6.锁定：即审核员lzh自己将不能修改该信息。上例中，注入信息1，lzh将不可修改。注入信息2，lzh可以改，如把“请输入您的姓名”改为请输入您的尊姓大名</p>
</div>
</div>

</body>
</html>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>