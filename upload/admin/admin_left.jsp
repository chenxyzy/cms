<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理页面</title>
<script src="js/sprycollapsiblepanel.js" type="text/javascript"></script>
<style type="text/css">
a:link{text-decoration : none ;color : #333 ;} /*链接*/
a:visited {text-decoration : none ;color : #333 ;} /*已访问链接*/
a:hover {text-decoration : none ;color : #f00 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #333 ;}  /*鼠标按下链接*/

.aelogo{ width:180px; height:100px; background:url(images/aelogo.jpg)}
ul,li{ margin:0; padding:0;}
.CollapsiblePanel {
	margin: 0px;
	width:180px;
	padding: 0px;

}


.CollapsiblePanelTab {
	color:#FFF;
    font-size:12px;
	font-weight:bold;
	height:20px;
	background: url(images/listbg.jpg);
	border-bottom: solid 1px #CCC;
	margin: 0px;
	padding:8px 0 0 15px;
	cursor: pointer;
	-moz-user-select: none;
	-khtml-user-select: none;
}


.logout {
	color:#FFF;
    font-size:12px;
	font-weight:bold;
	height:20px;
	background: url(images/listbg.jpg);
	border-bottom: solid 1px #CCC;
	margin: 0px;
	padding:8px 0 0 15px;
	cursor: pointer;
	-moz-user-select: none;
	-khtml-user-select: none;
	text-align:left;
	

	

}
.logout ul li{
list-style-type:none;
}

.CollapsiblePanelContent{
	background:#fff;
	border-left: solid 1px #CCC;
	border-right: solid 1px #999;
	border-top: solid 1px #999;
	border-bottom: solid 1px #CCC;
}


.CollapsiblePanelContent ul {
    margin:3px 0;
	
}
.CollapsiblePanelText {
	font-size:12px;
	margin-left: 5px;
	padding: 4px 0px 4px 20px;
	border-bottom:#bbb 1px dotted;
	width:152px;
}
.CollapsiblePanelContent ul li{
	font-size:12px;
	margin-left: 5px;
	padding: 4px 0px 4px 20px;
	border-bottom:#bbb 1px dotted;
	width:152px;
	background:url(images/li.jpg) no-repeat 3px 7px;
}


.CollapsiblePanelTab a {
	color: black;
	text-decoration: none;
}

.logout a {
	color:#FFF;
	text-decoration: none;
}

.CollapsiblePanelOpen .CollapsiblePanelTab {
	background-color: #EEE;
}



.CollapsiblePanelClosed .CollapsiblePanelTab {

}


.CollapsiblePanelTabHover,  .CollapsiblePanelOpen .CollapsiblePanelTabHover {
	background-color: #CCC;
}


.CollapsiblePanelFocused .CollapsiblePanelTab {
	background-color: #3399FF;
}

</style>
</head>
 
<BODY style="overflow:auto;overflow-x:hidden">
<div class="aelogo"></div>
<div id="CollapsiblePanel1" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">系统管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_site_query.action target=main>网站基本设置</a></li>
    <li><a href=admin_administrator_queryAll.action target=main>管理员设置</a></li>
    <li><a href=admin_otherSiteInfo_query.action target=main>其它设置</a></li>
    <li><a href=admin_ehc_list.jsp target=main>外部服务器编码设置</a></li>
    
   </ul>
  </div>
</div>


<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">用户管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_userGroup_query.action target=main>用户组管理</a></li>
    <li><a href=admin_user_findList.action target=main>用户管理</a></li>
    <li><a href=admin_passer_query.action target=main>用户审核员管理</a></li>
    <li><a href=admin_userOption_query.action target=main>用户功能选项</a></li>
    <li><a href=admin_integral_rule_query.action?localPostion=1 target=main>积分设置</a></li>
   </ul>
  </div>
</div>


<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">风格模板管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_site_style_list.jsp target=main>主站风格模板列表</a></li>
    <li><a href=admin_space_style.jsp onclick="javascript:s();return false;" target=main>个人空间风格模板</a></li>
    <li><a href=admin_bbs_style_list.jsp target=main>论坛风格模板</a></li>
    <li><a href=admin_qa_style_list.jsp target=main>问答系统风格模板</a></li>
    <li><a href=admin_vote_style_list.jsp target=main>投票系统风格模板</a></li>
    <li><a href=admin_draw_style_list.jsp target=main>抽奖系统风格模板</a></li>
   </ul>
  </div>
</div>


<div id="CollapsiblePanel4" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">内容管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_article_group_findAll.action target=main>门户栏目设置</a></li>
    <li><a href=admin_article_group_order.jsp target=main>门户栏目顺序</a></li>
    <li><a href=admin_qa_nav_list.jsp target=main>问答系统栏目设置</a></li>
    <li><a href=admin_custom_code_category.jsp target=main>用户代码设置</a></li>
   </ul>
  </div>
</div>

<div id="CollapsiblePanel5" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">论坛管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
   <li><a href=admin_bbs_info_query.action target=main>论坛基本设置</a></li>
    <li><a href=admin_bbs_forum_list.jsp target=main>版块设置</a></li>
    <li><a href=admin_bbs_nav_order.jsp onclick="javascript:s();return false;" target=main>版块顺序</a></li>
    <li><a href=admin_bbs_integral_rule.jsp onclick="javascript:s();return false;" target=main>论坛规则</a></li>
    <li><a href=admin_bbs_score_schemes.jsp target=main>积分方案设置</a></li>
    
   </ul>
  </div>
</div>


<div id="CollapsiblePanel6" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">投票与抽奖管理</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_vote_subject_findAll.action target=main>投票管理</a></li>
    <li><a href=admin_draw_list.jsp target=main>抽奖管理</a></li>

   </ul>
  </div>
</div>


<div id="CollapsiblePanel7" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">系统工具</div>
  <div class="CollapsiblePanelContent">
   <ul>
    <li><a href=admin_server_inf.jsp target=main>服务器测试</a></li>
    <li><a href=admin_tools_import_setup.jsp target=main>数据导入</a></li>
    <li><a href=admin_users_art_cout.jsp target=main>文章统计</a></li>
    <li><a href=tools_navViewsStat.action?ss=<s:i18n name="ApplicationResources"><s:text name="lerx.createStaticSafeStr" /></s:i18n> target=main>栏目访问统计</a></li>
    <li><a href=admin_static.jsp target=main>手工静态</a></li>
    <li><a href=admin_main.jsp target=main>后台主页</a></li>

   </ul>
  </div>
</div>

<script type="text/javascript">
<!--
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2", {contentIsOpen:false});
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3", {contentIsOpen:false});
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4", {contentIsOpen:false});
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5", {contentIsOpen:false});
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6", {contentIsOpen:false});
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7", {contentIsOpen:false});

//-->

function s(){
	alert("未提供本模块！");
}

</script>
<div class="CollapsiblePanelContent">
<div class="CollapsiblePanelText">当前管理员：</div>

   <ul>
   <li> 
   <b><s:property value="#parameters.admin"/></b>
    </li>
<div class="CollapsiblePanelText">〉〉 <a href="admin_administrator_logout.action" target="_top">安全退出</a> 〈〈</div>
  </ul>
</div>  
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>