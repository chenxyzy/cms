<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
          
 response.setHeader("Cache-Control","no-store");           
 response.setHeader("Pragrma","no-cache");           
 response.setDateHeader("Expires",0);           

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">         
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">         
<META HTTP-EQUIV="expires" CONTENT="0">
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
.list_2{width:30%; text-align:left;}
.list_3{width:10%;}
.list_4{width:25%;}
.list_5{width:15%;}
.list_6{width:15%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
/*增加pop*/
.myStyleCopyDiv {
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
.myStyleCopyDiv_top{ background:#3CC; height:30px;}
.myStyleCopyDiv_top_left{ float:left; padding:7px 0 0 20px;}
.myStyleCopyDiv_top_rig{ float:right; padding:7px 10px 0 0;}
 
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
<script type="text/javascript"> 
function showStyleCopyDiv(url2)
{
	$(document).ready(function(){
 
	$("#StyleCopyIframe").attr("src",url2);

		$("#StyleCopyDiv").show();
		$("#PopIframe").show();
		$("#bg").show();
	});
}
 
function closeStyleCopyDiv()
{
	$(document).ready(function(){
		$("#StyleCopyDiv").hide();
		$("#PopIframe").hide();
		$("#bg").hide();
	});
}
function reload(){
	window.location.reload();
}
</script>
<title>问答系统风格模板列表</title>
</head>
<s:action name="admin_qa_style_query">

</s:action>
<body>
<div id="StyleCopyDiv" class="myStyleCopyDiv" style="display:none;">
	<div class="myStyleCopyDiv_top">
		<div class="myStyleCopyDiv_top_left">模板复制</div>
		<div class="myStyleCopyDiv_top_rig"><a href="javascript:closeStyleCopyDiv();">关闭</a></div>
	</div>
	<div class="myStyleCopyDiv_input">
	
	
		<iframe id="StyleCopyIframe" width="100%" height="90%" src="" class="StyleCopyIframe" frameborder="0" ></iframe>
	</div>
</div>
<div id="bg" class="bg" style="display:none;"></div>
<iframe id="PopIframe" class="PopIframe" frameborder="0" ></iframe>
<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　问答系统风格模板列表</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">模板名称</td>
    <td class="list_3">作者或版权</td>
    <td class="list_4">描述</td>
    <td class="list_5">状态</td>
    <td class="list_6">操作</td>
   </tr>

   <s:iterator value="#request.qaStyleAll" id="x" status="st">      
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.styleName} </td>
    <td class="list_3">${x.author}</td>
    <td class="list_4">${x.description}</td>
    <td class="list_5"><s:if test="state"><b><img src="./images/ok.gif" /> 当前默认</b></s:if><s:else><font color="#FF0000"><a href="admin_qa_style_setDefault.action?id=${x.id}">设为默认</a></font></s:else></td>
    
    <td class="list_6"><a href="admin_qa_style_del.action?id=${x.id}" onclick="{if(confirm('确定删除风格模板“${x.styleName}”吗?\n该删除将不可撤消！')){return true;}return false;}">删除</a> <a href="javascript:showStyleCopyDiv('admin_qa_style_copy.jsp?id=${x.id}');">复制</a> <a href="admin_qa_style_getMetaDataByID.action?styleID=${x.id}&table=qa_style">编辑</a></td>

   </tr>
</s:iterator>
  </table>
  <div class="list_oneline2">共有<s:property value="#request.qaStyleAll.size()"/>&nbsp;个风格模板</div>
   </div>

  <div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　增加风格模板</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <s:property value="actionErrors[0]"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="POST" action="admin_qa_style_add.action">
  <tr >
    <td class="list_7">风格模板名：</td>
    <td class="list_8"><input type="text" name="qs.styleName" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">作者或版权信息：</td>
    <td class="list_8"><input type="text" name="qs.author" size="60"></td>
  </tr>
  <tr >
    <td class="list_7">描述：</td>
    <td class="list_8"><input type="text" name="qs.description" size="60"></td>
  </tr>
   
</table>
</div>

<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 

<center><br><a href="javascript:reload();">刷新</a></center>
</div>
</div>
<div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　导入风格模板</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">
 <form name="upload" action="admin_qa_style_importFromFile.action" method="post" enctype="multipart/form-data">
	<span class="sub_file"><input type="file" name="f.file"></span><span class="sub_but"><input type="submit" value="导入" /></span>
</form>
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