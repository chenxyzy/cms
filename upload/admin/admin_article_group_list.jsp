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
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7,.list_8,.list_9,.list_10,.list_11{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_left{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_right{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:right;}
.list_2{width:5%; text-align:left;}
.list_3{width:23%; text-align:left;}
.list_4{width:8%;}
.list_5{width:5%;}
.list_6{width:8%;}
.list_7{width:7%;}
.list_8{width:7%;}
.list_9{width:7%;}
.list_10{width:5%; text-align:center;}
.list_11{width:6%; text-align:center;}
.list_12{width:15%; text-align:center;}
.list_left{width:35%;}
.list_right{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
/*增加pop*/
.myNavsSelectDiv {
background: #fff;border: 2px solid #666;text-align: center;z-index:999;width: 600px;height: 300px;left:50%;top:40%;border-left:2px solid #eee;border-top:2px solid #eee;
margin-left:-300px!important;/*FF IE7 该值为本身宽的一半 */
margin-top:-150px!important;/*FF IE7 该值为本身高的一半*/
margin-top:0px;
position:fixed!important;/* FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
.myNavsSelectDiv_top{ background:#3CC; height:30px;}
.myNavsSelectDiv_top_left{ float:left; padding:7px 0 0 20px;}
.myNavsSelectDiv_top_rig{ float:right; padding:7px 10px 0 0;}
 
.bg,.PopIframe {
background-color: #666; display:none;width: 100%;height: 100%;left:0;top:0;/*FF IE7*/
filter:alpha(opacity=50);/*IE*/
opacity:0.5;/*FF*/
z-index:1;
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/* www.codefans.net IE5 IE5.5*/
}
.PopIframe {
filter:alpha(opacity=0);/*IE*/
opacity:0;/*FF*/
}
</style>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<title>栏目列表</title>
</head>

<body>
<script type="text/javascript">

function scrollWindow() {
	scroll(0, 100000);
	}
window.onload = function() {
	scrollWindow();
	} 
</script> 
<script type="text/javascript"> 
function showNavsSelectDiv(url2)
{
	//alert("aaaa");
	$(document).ready(function(){
 	
	$("#NavsSelectIframe").attr("src",url2);

		$("#NavsSelectDiv").show();
		$("#PopIframe").show();
		$("#bg").show();
	});
}
 
function closeNavsSelectDiv()
{
	$(document).ready(function(){
		$("#NavsSelectDiv").hide();
		$("#PopIframe").hide();
		$("#bg").hide();
	});
}
function reload(){
	window.location.reload();
}
</script>
<div id="NavsSelectDiv" class="myNavsSelectDiv" style="display:none;">
	<div class="myNavsSelectDiv_top">
		<div class="myNavsSelectDiv_top_left">栏目数据移动</div>
		<div class="myNavsSelectDiv_top_rig"><a href="javascript:closeNavsSelectDiv();">关闭</a></div>
	</div>
	<div class="myNavsSelectDiv_input">
	
	
		<iframe id="NavsSelectIframe" width="100%" height="90%" src="" class="NavsSelectIframe" frameborder="0" ></iframe>
	</div>
</div>
<div id="bg" class="bg" style="display:none;"></div>
<iframe id="PopIframe" class="PopIframe" frameborder="0" ></iframe>
<script type="text/javascript">
$("#NavsSelectIframe").load(function(){
var mainheight = $(this).contents().find("body").height()+30;
$(this).height(mainheight);
}); 
</script>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　栏目列表</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">ID号</td>
    <td class="list_3">栏目名</td>
    <td class="list_4">键值对</td>
    <td class="list_5">价格</td>
    <td class="list_6">静态目录名</td>
    <td class="list_7">分类</td>
    <td class="list_8">首页提取</td>
    <td class="list_9">独立模板</td>
    <td class="list_10">状态</td>
    <td class="list_11">评论</td>
    <td class="list_12">操作</td>
   </tr>

   <s:iterator value="#request.groupAll" id="x" status="st">     
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.articleGroup.id}</td>
    <td class="list_3">${x.prefixStr} <a target="_blank" href="../<s:i18n name="ApplicationResources"><s:text name="lerx.articleGroupPageFileName" /></s:i18n>?gid=${x.articleGroup.id}">${x.articleGroup.groupName}</a></td>
    <td class="list_4">${x.articleGroup.footerLeft} - ${x.articleGroup.footerRight}</td>
    <td class="list_5">${x.articleGroup.price}</td>
    
    <td class="list_6">${x.articleGroup.staticHtmlFolder}</td>
    <td class="list_7"><s:if test="#x.articleGroup.asClass" >是</s:if><s:else>-</s:else></td>
    
    <td class="list_8"><s:if test="#x.articleGroup.showOnIndex" ><font color="#FF0000">是</font></s:if><s:else>否</s:else>/${x.articleGroup.numberShowOnIndex}/${x.articleGroup.lengthShowOnIndex}</td>
    
    <td class="list_9"><s:if test="#x.articleGroup.style.id>0">有</s:if><s:else>-</s:else></td>
    <td class="list_10"><s:if test="#x.articleGroup.state">正常<s:if test="#x.articleGroup.share==false">/<font color="#FF0000">不公开</font></s:if></s:if><s:else><font color="#FF0000">禁用</font></s:else></td>
    <td class="list_11"><s:if test="#x.articleGroup.commentMode==1">禁止</s:if><s:elseif test="#x.articleGroup.commentMode==2">用户</s:elseif><s:elseif test="#x.articleGroup.commentMode==3">游客</s:elseif><s:else>默认</s:else></td>
    <td class="list_12"><a href="admin_article_group_del.action?id=${x.articleGroup.id}" onclick="{if(confirm('确定删除栏目“${x.articleGroup.groupName}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a> <a href="admin_article_group_findById.action?id=${x.articleGroup.id}">设置</a> <a href="javascript:showNavsSelectDiv('admin_article_group_select.jsp?sid=${x.articleGroup.id}');">移动数据</a></td>

   </tr>
   
</s:iterator>
  </table>
  <div class="list_oneline2">共有<s:property value="#request.groupAll.size()"/>个频道</div>
   </div>

  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　快速增加文章组</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <s:property value="actionErrors[0]"/>
  <form method="POST" action="admin_article_group_add.action">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  
  <tr >
    <td class="list_left">栏目名：</td>
    <td class="list_right"><input type="text" name="articleGroup.groupName" size="60"></td>
  </tr>
  <tr >
    <td class="list_left">上级栏目：</td>
    <td class="list_right"><select size="1" name="parentGroupID">
    <option value="0">根栏目</option>
    <s:iterator value="#request.groupAll" id="x" status="st">
    <option  value="${x.articleGroup.id}">${x.prefixStr} ${x.articleGroup.groupName}</option>
    </s:iterator>
    </select>
    </td>
  </tr>
 
</table>
</div>
<div class="list_oneline"><s:token></s:token><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 
</div>
</div>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>