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
.trlist td{height:24px;font-weight:bold;}
.aelist tr:hover{ background:#e6e6e6;}
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_a{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_b{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:center;}
.list_2{width:30%; text-align:left;}
.list_3{width:10%;}
.list_4{width:10%;}
.list_5{width:10%;}
.list_6{width:10%;}
.list_7{width:10%;}
.list_a{width:35%;}
.list_b{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<s:action name="admin_vote_subject_findById">
	<s:param name="id"><s:property value="#parameters.subId"/></s:param>
</s:action>
<title>投票项目详细设置 →<s:property value="#request.curVs.title"/></title>
</head>
<SCRIPT LANGUAGE=javascript> 
<!--
function gopreview()
{
var popupWin = window.open("", "preview_page", "scrollbar=no,width=500,height=360");
document.preview.submit()
}
//
-->
</SCRIPT>


<s:action name="admin_vote_item_findAll">
	<s:param name="subId"><s:property value="#parameters.subId"/></s:param>
	<s:param name="mod"><s:property value="#parameters.mod"/></s:param>
</s:action>
<body>
	<div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　投票内容详情 → <s:property value="#request.curVs.title"/></div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
   <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">投票子内容</td>
    <td class="list_3">状态</td>
    <td class="list_4">操作</td>
   </tr>
 <s:iterator value="#request.viAll" id="x" status="st">
 	<s:action name="admin_vote_item_findById">
 	<s:param name="id">${x}</s:param>
 	</s:action>
    <tr class="trlist">
    <td class="list_1">${st.index+1}</td>
    <td class="list_2"><s:property value="#request.curVi.title"/></td>
    <td class="list_3"><s:if test="#request.curVi.state">正常</s:if><s:else><font color="#FF0000">禁用</font></s:else></td>
    <td class="list_4"><a href="admin_vote_item_findById.action?id=${x}&subId=<s:property value="#parameters.subId"/>">编辑</a> <s:if test="#request.curVi.state"><a href="admin_vote_item_changeState.action?state=false&id=${x}&subId=<s:property value="#parameters.subId"/>">禁用</a> </s:if><s:else> <a href="admin_vote_item_changeState.action?state=true&id=${x}&subId=<s:property value="#parameters.subId"/>">恢复</a></s:else> <a href="admin_vote_item_delById.action?id=${x}&title=${vi.title}&subId=<s:property value="#parameters.subId"/>" onclick="{if(confirm('确定删除投票子内容“${vi.title}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a></td>
  
   </tr>
 </s:iterator>
  
   </table>
   <div class="list_oneline2">共有<s:property value="#request.viAll.size()"/>&nbsp;个子内容</div>
   
   </div>
 
 
 	<div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　增加投票内容</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_vote_item_add.action?subId=<s:property value="#parameters.subId"/>">
  <tr >
    <td class="list_a">项目名称：</td>
    <td class="list_b"><input type="text" name="vi.title" size="60"></td>
  </tr>
  <tr >
    <td class="list_a">详细内容：</td>
    <td class="list_b"><textarea rows="10" name="vi.body" cols="80"></textarea><br /><input type=button value="上传文件" name=Button onclick="gopreview();"></td>
  </tr>

    <tr >
    <td class="list_a">item1：</td>
    <td class="list_b"><input type="text" name="vi.item1" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item2：</td>
    <td class="list_b"><input type="text" name="vi.item2" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item3：</td>
    <td class="list_b"><input type="text" name="vi.item3" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item4：</td>
    <td class="list_b"><input type="text" name="vi.item4" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item5：</td>
    <td class="list_b"><input type="text" name="vi.item5" size="60"></td>
  </tr>


    <tr >
    <td class="list_a">item6：</td>
    <td class="list_b"><input type="text" name="vi.item6" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item7：</td>
    <td class="list_b"><input type="text" name="vi.item7" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item8：</td>
    <td class="list_b"><input type="text" name="vi.item8" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item9：</td>
    <td class="list_b"><input type="text" name="vi.item9" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item10：</td>
    <td class="list_b"><input type="text" name="vi.item10" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item11：</td>
    <td class="list_b"><input type="text" name="vi.item11" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item12：</td>
    <td class="list_b"><input type="text" name="vi.item12" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item13：</td>
    <td class="list_b"><input type="text" name="vi.item13" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item14：</td>
    <td class="list_b"><input type="text" name="vi.item14" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item15：</td>
    <td class="list_b"><input type="text" name="vi.item15" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item16：</td>
    <td class="list_b"><input type="text" name="vi.item16" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item17：</td>
    <td class="list_b"><input type="text" name="vi.item17" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item18：</td>
    <td class="list_b"><input type="text" name="vi.item18" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item19：</td>
    <td class="list_b"><input type="text" name="vi.item19" size="60"></td>
  </tr>

    <tr >
    <td class="list_a">item20：</td>
    <td class="list_b"><input type="text" name="vi.item20" size="60"></td>
  </tr>

   
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 
 
 <form name=preview action="/upload.jsp" method=post target=preview_page>
</form>

 <div class="list_oneline"><a href="./admin_vote_nav_list.jsp?page=1">返回</a></div>
</div>
<s:debug></s:debug>
</body>
</html>
</s:if>

<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>
