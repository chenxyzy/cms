<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_a{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_b{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:center;}
.list_2{ width:5%;border-left:0px; text-align:center;}
.list_3{width:30%; text-align:left;}
.list_4{width:5%;}
.list_5{width:10%;}
.list_6{width:10%;}
.list_7{width:10%;}
.list_8{width:15%;}
.list_a{width:35%;}
.list_b{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function dateClearM(s){
	//var s = "2012-04-05 06:05:34:345";
	s=s.replace(/\s\s/g," ");
	s=s.substr(0,19);
	return s;
}
</script>
<s:action name="admin_vote_subject_findAll">
	<s:param name="mod"><s:property value="#parameters.mod"/></s:param>
</s:action>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　投票项目设置</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">



  <table width="100%" border="0" cellpadding="0" cellspacing="0">

        
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">id</td>
    <td class="list_3">投票项目名称</td>
    <td class="list_4">状态</td>
    <td class="list_5">建立投票时间</td>
    <td class="list_6">投票开始时间</td>
    <td class="list_7">投票结束时间</td>
    <td class="list_8">操作</td>
   </tr>
   
   <s:iterator value="#request.vsAll" id="x" status="st">
    
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.id}</td>
    <td class="list_3"><a href="/vote.jsp?id=${x.id}" target="_blank">${x.title}</a></td>
    <td class="list_4">正常</td>
    <td class="list_5">${x.createTime}</td>
    <td class="list_6">${x.voteStartTime}</td>
    <td class="list_7">${x.voteEndTime}</td>
    <td class="list_8"><a href="admin_vote_subject_findById.action?id=${x.id}">编辑</a> <a href="admin_vote_subject_del.action?id=${x.id}&title=${x.title}&page=1" onclick="{if(confirm('确定删除“${x.title}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a> <a href="admin_vote_item_findAll.action?subId=${x.id}">内容</a> <a href="./admin_vote_copy.jsp?id=${x.id}">复制</a> <a href="admin_vote_subject_clear.action?id=${x.id}&title=${x.title}&page=1" onclick="{if(confirm('确定清空投票“${x.title}”吗?\n该清空操作将不可撤消！\n一旦清空，所有投票内容和记录将被初始为0！')){return true;}return false;}">清空</a></td>
    
   </tr>
   </s:iterator>
    
  
   
   
  </table>
  <div class="list_oneline2">共有<s:property value="#request.vsAll.size()"/>&nbsp;个投票</div>
  
   </div>
<table align='center'><tr><td>&nbsp;<font color=#000000>&nbsp;&nbsp;第</font>&nbsp;<b><font color=red>1</font></b><font color=#000000>&nbsp;页&nbsp;&nbsp;</font>&nbsp;<font color=#000000>&nbsp;&nbsp;共<strong>1</strong>页</font><font color=#000000>&nbsp;&nbsp;转到第<input type='text' name='page' size='3' maxlength='5' style='text-align: center' value='1' onKeyPress="if (event.keyCode==13) window.location='./admin_vote_nav_list.jsp?page='+this.value;">页</font></td></tr></table>

</div>
  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　增加投票项目</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_vote_subject_add.action">
  <tr >
    <td class="list_a">投票名称：</td>
    <td class="list_b"><input type="text" name="vs.title" size="60"></td>
  </tr>
 
   
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 

</div>
</div>
</body>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>