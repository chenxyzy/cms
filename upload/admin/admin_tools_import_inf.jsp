<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<body>

<p><s:property value="#request.msg" /></p>
<p>您输入的数据库及相应的数据表检测如下：</p>
<p>--记录数：${con.recCount}</p>
<p>--字段数：${con.colCount}</p>



   
<script type="text/javascript">
chkArticle();
function chkArticle(){
	$("#article").show();
	$("#nav").hide();
	$("#user").hide();
	$("#userGroup").hide();
	$("#qa").hide();
	
	$("#col .article").attr("disabled",false);
	$("#col .nav").attr("disabled","disabled");
	$("#col .user").attr("disabled","disabled");
	$("#col .userGroup").attr("disabled","disabled");
	$("#col .qa").attr("disabled","disabled");
}
function chkNav(){
	$("#article").hide();
	$("#nav").show();
	$("#user").hide();
	$("#userGroup").hide();
	$("#qa").hide();
	
	$("#col .article").attr("disabled","disabled");
	$("#col .nav").attr("disabled",false);
	$("#col .user").attr("disabled","disabled");
	$("#col .userGroup").attr("disabled","disabled");
	$("#col .qa").attr("disabled","disabled");
}
function chkUser(){
	$("#article").hide();
	$("#nav").hide();
	$("#user").show();
	$("#userGroup").hide();
	$("#qa").hide();
	
	$("#col .article").attr("disabled","disabled");
	$("#col .nav").attr("disabled","disabled");
	$("#col .user").attr("disabled",false);
	$("#col .userGroup").attr("disabled","disabled");
	$("#col .qa").attr("disabled","disabled");
	
}
function chkUserGroup(){
	$("#article").hide();
	$("#nav").hide();
	$("#user").hide();
	$("#userGroup").show();
	$("#qa").hide();
	
	$("#col .article").attr("disabled","disabled");
	$("#col .nav").attr("disabled","disabled");
	$("#col .user").attr("disabled","disabled");
	$("#col .userGroup").attr("disabled",false);
	$("#col .qa").attr("disabled","disabled");
}
function chkQa(){
	$("#article").hide();
	$("#nav").hide();
	$("#user").hide();
	$("#userGroup").hide();
	$("#qa").show();
	
	$("#col .article").attr("disabled","disabled");
	$("#col .nav").attr("disabled","disabled");
	$("#col .user").attr("disabled","disabled");
	$("#col .userGroup").attr("disabled","disabled");
	$("#col .qa").attr("disabled",false);
	
}
</script>

<form method="POST" action="tools_import_exe.action">
<p><b>您想导入什么？请在这里选择！</b></p>
<p>如果您是全站导入，您应该的顺序是按“用户组”、“用户”、“栏目”、“文章”这样的顺序进行导入！</p>
<p><input type="radio" onclick="javascript:chkArticle();" value="1" checked name="dataType">导入文章</p>
<p><input type="radio" onclick="javascript:chkNav();" name="dataType" value="2">导入栏目</p>
<p><input type="radio" onclick="javascript:chkUser();" name="dataType" value="3">导入用户</p>
<p><input type="radio" onclick="javascript:chkUserGroup();" name="dataType" value="4">导入用户组</p>
<p><input type="radio" onclick="javascript:chkQa();" name="dataType" value="5">导入问答数据</p>
<p>--------------请在下方根据上面的导入选项仔细选择--------------</p>
<table>

<p><input TYPE="hidden" name="con.dbFile" value="${con.dbFile}" size="20">
<input TYPE="hidden" name="con.table" value="${con.table}"  size="20">
<input TYPE="hidden" name="con.charSet" value="${con.charSet}"  size="20">
<input TYPE="hidden" id="server" value="${con.server}" name="con.server" size="20"></p>
<input TYPE="hidden" id="port" value="${con.port}" name="con.port" value="3306" size="20"></p>
<input TYPE="hidden" value="${con.db}" name="con.db" size="20"></p>
<input TYPE="hidden" value="${con.user}" name="con.user" size="20"></p>
<input TYPE="hidden" value="${con.password}" name="con.password" size="20"></p>
<input TYPE="hidden" value="${con.dbType}" name="con.dbType" size="20"></p>
</p>
<s:bean name="org.apache.struts2.util.Counter" id="counter" >     
  <s:param name="first"  value= "0"  />     
  <s:param name="last"  value= "#request.con.colCount-1"  />   
 
  <s:iterator>   
  <tr><td>
  <s:property value="#request.col[current-1]" /> </td><td>
  <select size="1" id="col" name="col" tabindex="${current}">
   <option value="0">0</option>
	<option class="article" value="1">文章_分类ID <数据类型：int></option>
	<option class="article" value="2">文章_标题 <数据类型：String></option>
	<option class="article" value="3">文章_精简标题 <数据类型：String></option>
	<option class="article" value="4">文章_眉头标题 <数据类型：String></option>
	<option class="article" value="5">文章_附加标题 <数据类型：String></option>
	<option class="article" value="6">文章_作者 <数据类型：String></option>
	<option class="article" value="7">文章_作者单位 <数据类型：String></option>
	<option class="article" value="8">文章_作者Email <数据类型：String></option>
	<option class="article" value="9">文章_作者网址 <数据类型：String></option>
	<option class="article" value="10">文章_发布时间 <数据类型：datetime></option>
	<option class="article" value="11">文章_发布时间 <数据类型：long></option>
	<option class="article" value="12">文章_简介 <数据类型：String></option>
	<option class="article" value="13">文章_正文 <数据类型：String></option>
	<option class="article" value="14">文章_浏览数 <数据类型：int></option>
	<option class="article" value="15">文章_审核状态 <数据类型：boolean></option>
	<option class="article" value="16">文章_缩略图 <数据类型：String></option>
	<option class="article" value="17">文章_标题图 <数据类型：String></option>
	<option class="article" value="18">文章_标题图说明 <数据类型：String></option>
	<option class="article" value="19">文章_发表情况 <数据类型：String></option>
	<option class="article" value="20">文章_是否跳转 <数据类型：boolean></option>
	<option class="article" value="21">文章_链接URL <数据类型：String></option>
	<option class="article" value="22">文章_指导者 <数据类型：String></option>
	<option class="article" value="23">文章_上次浏览IP <数据类型：String></option>
	<option class="article" value="24">文章_审核者 <数据类型：String></option>
	<option class="article" value="25">文章_发布会员名 <数据类型：String></option>
	<option class="article" value="26">文章_文章位置(0网站1个人空间) <数据类型：String></option>
	<option class="article" value="27">文章_是否公告 <数据类型：boolean></option>
	<option class="article" value="28">文章_是否文本编辑器编辑 <数据类型：boolean></option>
	
	<option class="nav" value="31">栏目_上级栏目ID <数据类型：int></option>
	<option class="nav" value="32">栏目_标题 <数据类型：String></option>
	<option class="nav" value="33">栏目_排序字符串 <数据类型：String></option>
	<option class="nav" value="34">栏目_是否公开 <数据类型：boolean></option>
	<option class="nav" value="35">栏目_是否作为分类 <数据类型：boolean></option>
	<option class="nav" value="36">栏目_栏目状态 <数据类型：boolean></option>
	<option class="nav" value="37">栏目_特色图标Url <数据类型：String></option>
	<option class="nav" value="38">栏目_跳转Url <数据类型：String></option>
	<option class="nav" value="39">栏目_是否显示在首页 <数据类型：boolean></option>
	<option class="nav" value="40">栏目_首页标题长度 <数据类型：int></option>
	<option class="nav" value="41">栏目_首页数据行数 <数据类型：int></option>
	<option class="nav" value="42">栏目_是否显示在上级栏目 <数据类型：boolean></option>
	<option class="nav" value="43">栏目_上级栏目标题长度 <数据类型：int></option>
	<option class="nav" value="44">栏目_上级栏目数据行数 <数据类型：int></option>
	
	<option class="user" value="51">用户_用户名 <数据类型：String></option>
	<option class="user" value="52">用户_用户密码 <数据类型：String></option>
	<option class="user" value="53">用户_salt值<数据类型：String></option>
	<option class="user" value="54">用户_所在用户组ID<数据类型：int></option>
	<option class="user" value="55">用户_Email<数据类型：String></option>
	<option class="user" value="56">用户_头像Url<数据类型：String></option>
	<option class="user" value="57">用户_注册IP<数据类型：String></option>
	<option class="user" value="58">用户_上次登录IP<数据类型：String></option>
	<option class="user" value="59">用户_注册时间<数据类型：Timestamp></option>
	<option class="user" value="60">用户_上次登录时间<数据类型：Timestamp></option>
	<option class="user" value="61">用户_被审核通过的文章数 <数据类型：long></option>
	<option class="user" value="62">用户_发布文章数 <数据类型：long></option>
	<option class="user" value="63">用户_总积分 <数据类型：int></option>
	<option class="user" value="64">用户_论坛积分 <数据类型：int></option>
	<option class="user" value="65">用户_真实姓名<数据类型：String></option>
	<option class="user" value="66">用户_性别<数据类型：boolean></option>
	<option class="user" value="67">用户_出生时间<数据类型：Date></option>
	<option class="user" value="68">用户_单位<数据类型：String></option>
	<option class="user" value="69">用户_国家<数据类型：String></option>
	<option class="user" value="70">用户_省<数据类型：String></option>
	<option class="user" value="71">用户_市<数据类型：String></option>
	<option class="user" value="72">用户_家庭住址<数据类型：String></option>
	<option class="user" value="73">用户_qq<数据类型：String></option>
	<option class="user" value="74">用户_状态<数据类型：boolean></option>
	
	<option class="userGroup" value="81">用户组_名称<数据类型：String></option>
	<option class="userGroup" value="82">用户组_权限码<数据类型：String></option>
	<option class="userGroup" value="83">用户组_状态<数据类型：boolean></option>
	
	<option class="qa" value="91">问答_组ID<数据类型：long></option>
	<option class="qa" value="92">问答_标题<数据类型：String></option>
	<option class="qa" value="93">问答_提问内容<数据类型：String></option>
	<option class="qa" value="94">问答_提问人<数据类型：String></option>
	<option class="qa" value="95">问答_提问时间<数据类型：datetime></option>
	<option class="qa" value="96">问答_提问IP<数据类型：String></option>
	<option class="qa" value="97">问答_提问人email<数据类型：String></option>
	<option class="qa" value="98">问答_提问人地址<数据类型：String></option>
	<option class="qa" value="99">问答_提问人qq<数据类型：String></option>
	<option class="qa" value="100">问答_提问人电话<数据类型：String></option>
	<option class="qa" value="101">问答_查看密码<数据类型：String></option>
	<option class="qa" value="102">问答_答复人<数据类型：String></option>
	<option class="qa" value="103">问答_答复内容<数据类型：String></option>
	<option class="qa" value="104">问答_答复时间<数据类型：datetime></option>
	<option class="qa" value="105">问答_是否公开<数据类型：boolean></option>
	<option class="qa" value="106">问答_被浏览数<数据类型：int></option>
	</select>
 </p>
 </td></tr>
  </s:iterator>     
</s:bean>
</table>
<div id="article">
<p>1.文本中替换<input type="text" size=20 name="replaceS1">为<input type="text" size=20 name="replaceD1">;</p>
<p>2.文本中替换<input type="text" size=20 name="replaceS2">为<input type="text" size=20 name="replaceD2">;</p>
<p><b>注意：两个替换方式不能相同！</b></p>
<p><input type="checkbox" name="byEditor" value="true" checked>原正文为在线编辑器产生（如有html标签或word格式标签）</p>
</div>
<p>SQL后缀<input type="text" size=20 name="sqlOrder"> 例：order by id asc 或 where state=true</p>
<div id="nav">
</div>
<div id="user">
</div>
<div id="userGroup">
</div>
<div id="qa">
<p>注意：你首先要建立问答组，且在数据库中将组的id与导入的问答的组id相对应</p>
</div>
<p><input type="checkbox" name="allPass" value="true">全部审核通过</p>
<p><b>您应该对当前的数据进行一次备份再点击这个提交按钮！！！</b></p>
<p><input type="submit" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>

</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>