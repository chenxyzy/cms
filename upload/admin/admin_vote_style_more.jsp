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
.aeheadbox{ background:url(images/btbgm_lt.jpg); height:34px;}
.aeheadl{ height:34px; width:5px; background:url(images/btbgl_lt.jpg) no-repeat; float:left;}
.aeheadm{ height:25px;  padding: 9px 0 0 15px; color:#FFF; font-size:14px;font-weight:bold; float:left;}
.aeheadr{ height:34px; width:3px; background:url(images/btbgr_lt.jpg) no-repeat; float:right;}

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
.list_4{width:35%;}
.list_5{width:5%;}
.list_6{width:15%;}
.list_7{width:35%;}
.list_8{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}

/*增加pop*/
.myStyleDetailsDiv {
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
.myStyleDetailsDiv_top{ background:#3CC; height:30px;}
.myStyleDetailsDiv_top_left{ float:left; padding:7px 0 0 20px;}
.myStyleDetailsDiv_top_rig{ float:right; padding:7px 10px 0 0;}

.bg,.PopUpLoadIframe {
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
.PopUpLoadIframe {
filter:alpha(opacity=0);/*IE*/
opacity:0;/*FF*/
}

</style>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<script type="text/javascript"> 
function showStyleDetailsDiv(url2)
{
	$(document).ready(function(){
 
	$("#StyleDetailsIframe").attr("src",url2);
	$("#StyleDetailsIframe").attr("src",url2);
	//$("#UpLoadIframe").attr("src","upload.jsp");

	//$("#StyleDetailsIframe").attr("src","../upload.jsp");
		$("#StyleDetailsDiv").show();
		$("#PopUpLoadIframe").show();
		$("#bg").show();
	});
}
 
function closeStyleDetailsDiv()
{
	$(document).ready(function(){
		$("#StyleDetailsDiv").hide();
		$("#PopUpLoadIframe").hide();
		$("#bg").hide();
	});
}

</script>

<title>投票系统风格模板详情</title>
</head>
<s:action name="admin_vote_style_findNameById">
	<s:param name="id"><s:property  value="#request.styleID"/></s:param>
</s:action>
<s:set name="curStyleName" value="#request.colValue" scope="request"></s:set>
<s:set name="styleID" value="#request.dataId" scope="request"></s:set>

<body>
<div id="StyleDetailsDiv" class="myStyleDetailsDiv" style="display:none;">
	<div class="myStyleDetailsDiv_top">
		<div class="myStyleDetailsDiv_top_left">变量详情</div>
		<div class="myStyleDetailsDiv_top_rig"><a href="javascript:closeStyleDetailsDiv();">关闭</a></div>
	</div>
	<div class="myStyleDetailsDiv_input">
	
	
		<iframe id="StyleDetailsIframe" width="100%" height="90%" src="" class="StyleDetailsIframe" frameborder="0" ></iframe>
	</div>
</div>
<div id="bg" class="bg" style="display:none;"></div>
<iframe id="PopUpLoadIframe" class="PopUpLoadIframe" frameborder="0" ></iframe>

<script type="text/javascript">
$("#StyleDetailsIframe").load(function(){
var mainheight = $(this).contents().find("body").height()+30;
$(this).height(mainheight);
}); 
</script>

<div class="aebox">
 <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　“<s:property value="#request.currentStyleName"/>”——问答系统风格模板详情</div>
  <div class="aeheadr"></div>
 </div>
 <div class="aelist">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

       
   <tr class="trlist">
    <td class="list_1">序号</td>
    <td class="list_2">标签变量名</td>
    <td class="list_3">类型</td>
    <td class="list_4">操作</td>
   </tr>
 <tr>
    <td height="30" colspan="4">通用设置</td>
 </tr>
   <s:iterator value="#request.metaData" id="x" status="st">      
   <tr >
    <td class="list_1">${st.index+1}</td>
    <td class="list_2">${x.colName} </td>
    <td class="list_3">${x.colType}</td>
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=all&id='+<s:property  value="#request.styleID"/>+'&col=${x.colName}&table=vote_style');">详情</a></td>

   </tr>
   
</s:iterator>
 
<tr>
    <td height="30" colspan="4">public共用模块</td>
 </tr>
<s:action name="admin_vote_style_getMetaDataByID" >
	<s:param name="id"><s:property  value="#request.pid"/></s:param>
	<s:param name="table">vote_style_element</s:param>
</s:action>
<s:iterator value="#request.metaData" id="xp" status="stp">  
<tr >
    <td class="list_1">${stp.index+1}</td>
    <td class="list_2">${xp.colName} </td>
    <td class="list_3">${xp.colType}</td>
    
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=public&id='+<s:property  value="#request.pid"/>+'&col=${xp.colName}&table=vote_style_element');">详情</a></td>
	
   </tr>
   
</s:iterator>

<tr>
    <td height="30" colspan="4">join模块</td>
 </tr>
<s:action name="admin_vote_style_getMetaDataByID" >
	<s:param name="id"><s:property  value="#request.jid"/></s:param>
	<s:param name="table">vote_style_element</s:param>
</s:action>
<s:iterator value="#request.metaData" id="xp" status="stp">  
<tr >
    <td class="list_1">${stp.index+1}</td>
    <td class="list_2">${xp.colName} </td>
    <td class="list_3">${xp.colType}</td>
    
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=join&id='+<s:property  value="#request.jid"/>+'&col=${xp.colName}&table=vote_style_element');">详情</a></td>
	
   </tr>
   
</s:iterator>

<tr>
    <td height="30" colspan="4">post模块</td>
 </tr>
<s:action name="admin_vote_style_getMetaDataByID" >
	<s:param name="id"><s:property  value="#request.vid"/></s:param>
	<s:param name="table">vote_style_element</s:param>
</s:action>
<s:iterator value="#request.metaData" id="xp" status="stp">  
<tr >
    <td class="list_1">${stp.index+1}</td>
    <td class="list_2">${xp.colName} </td>
    <td class="list_3">${xp.colType}</td>
    
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=vote&id='+<s:property  value="#request.vid"/>+'&col=${xp.colName}&table=vote_style_element');">详情</a></td>
	
   </tr>
   
</s:iterator>

<tr>
    <td height="30" colspan="4">item共用模块</td>
 </tr>
<s:action name="admin_vote_style_getMetaDataByID" >
	<s:param name="id"><s:property  value="#request.iid"/></s:param>
	<s:param name="table">vote_style_element</s:param>
</s:action>
<s:iterator value="#request.metaData" id="xp" status="stp">  
<tr >
    <td class="list_1">${stp.index+1}</td>
    <td class="list_2">${xp.colName} </td>
    <td class="list_3">${xp.colType}</td>
    
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=item&id='+<s:property  value="#request.iid"/>+'&col=${xp.colName}&table=vote_style_element');">详情</a></td>
	
   </tr>
   
</s:iterator>

<tr>
    <td height="30" colspan="4">result共用模块</td>
 </tr>
<s:action name="admin_vote_style_getMetaDataByID" >
	<s:param name="id"><s:property  value="#request.rid"/></s:param>
	<s:param name="table">vote_style_element</s:param>
</s:action>
<s:iterator value="#request.metaData" id="xp" status="stp">  
<tr >
    <td class="list_1">${stp.index+1}</td>
    <td class="list_2">${xp.colName} </td>
    <td class="list_3">${xp.colType}</td>
    
    <td class="list_4"><a href="javascript:showStyleDetailsDiv('admin_vote_style_var_find.action?styleID='+<s:property  value="#request.styleID"/>+'&curModel=result&id='+<s:property  value="#request.rid"/>+'&col=${xp.colName}&table=vote_style_element');">详情</a></td>
	
   </tr>
   
</s:iterator>

  </table>

   </div>


</div>
<s:debug></s:debug>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>