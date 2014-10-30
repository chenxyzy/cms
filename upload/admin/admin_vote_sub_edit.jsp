<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<style type="text/css">
body {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0 0 0 5px;
}

a:link {
	text-decoration: none;
	color: #333;
}  /*链接*/
a:visited {
	text-decoration: none;
	color: #333;
}  /*已访问链接*/
a:hover {
	text-decoration: none;
	color: #f00;
}  /*鼠标经过链接*/
a:active {
	text-decoration: none;
	color: #333;
}  /*鼠标按下链接*/
.aebox {
	width: 100%;
}

.aeheadbox {
	background: url(images/btbgm.jpg);
	height: 34px;
}

.aeheadl {
	height: 34px;
	width: 5px;
	background: url(images/btbgl.jpg) no-repeat;
	float: left;
}

.aeheadm {
	height: 25px;
	padding: 9px 0 0 15px;
	color: #FFF;
	font-size: 14px;
	font-weight: bold;
	float: left;
}

.helplist {
	height: 25px;
	padding: 5px 0 0 30px;
	float: left;
}

.aeheadr {
	height: 34px;
	width: 3px;
	background: url(images/btbgr.jpg) no-repeat;
	float: right;
}

.aelist {
	width: 99%;
	margin: auto;
}

.aelist tr {
	background: #f5f5f5;
}

.trlist td {
	background: #aaa;
	height: 24px;
	font-weight: bold;
	color: #FFF;
}

.aelist tr:hover {
	background: #e6e6e6;
}

.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7 {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: center;
	padding: 5px;
}

.list_a {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: right;
	padding: 5px;
}

.list_b {
	border-bottom: #e4e4e4 1px solid;
	border-right: #e4e4e4 1px solid;
	border-left: #fff 1px solid;
	border-top: #fff 1px solid;
	vertical-align: middle;
	text-align: left;
	padding: 5px;
}

.list_1 {
	width: 5%;
	border-left: 0px;
	text-align: center;
}

.list_2 {
	width: 30%;
	text-align: left;
}

.list_3 {
	width: 10%;
}

.list_4 {
	width: 10%;
}

.list_5 {
	width: 10%;
}

.list_6 {
	width: 10%;
}

.list_7 {
	width: 10%;
}

.list_a {
	width: 35%;
}

.list_b {
	width: 65%;
}

.list_oneline {
	background: #CCC;
	border-right: #999 1px solid;
	border-bottom: #999 1px solid;
	border-left: 0;
	border-top: #fff 1px solid;
	height: 30px;
	text-align: center;
	padding-top: 10px;
}

.list_oneline2 {
	background: #eee;
	border-right: #e4e4e4 1px solid;
	border-bottom: #e4e4e4 1px solid;
	border-left: 0;
	border-top: #fff 1px solid;
	height: 20px;
	text-align: center;
	padding-top: 5px;
}

.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8
	{
	border-right: #999;
	border-bottom: #999;
	text-align: center;
}
</style>
	<title>投票项目详细设置</title>



	<link rel="stylesheet" media="all" type="text/css"
		href="../js/jquery/ui/1.8.21/themes/ui-lightness/jquery-ui.css" />
	<link rel="stylesheet" media="all" type="text/css"
		href="../js/jquery/timepicker/jquery-ui-timepicker-addon.css" />

	<script type="text/javascript"
		src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
		
	<script type="text/javascript"
		src="../js/jquery/ui/1.8.21/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="../js/jquery/timepicker/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript"
		src="../js/jquery/examples/timepicker/jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript"
		src="../js/simpleDateFormat.js"></script>

	</head>

	<body>

	<div class="aebox">
	<div class="aeheadbox">
	<div class="aeheadl"></div>
	<div class="aeheadm">> 投票项目详细设置</div>
	<div class="aeheadr"></div>
	</div>
	<div class="aelist">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">

		<form name="f" id="f" method="post"
			action="admin_vote_subject_modify.action?vs.id=${vs.id}">
		<tr>
			<td class="list_a">投票名称：</td>
			<td class="list_b"><input type="text" name="vs.title" size="60"
				value="${vs.title}"></td>
		</tr>
		<tr>
			<td class="list_a">状态：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.state" >checked</s:if> name="vs.state">正常<input
				type="radio" <s:if test="vs.state==false">checked</s:if>
				name="vs.state" value="false">禁用</td>
		</tr>
		<s:action name="admin_vote_style_query"></s:action>
		<tr>
			<td class="list_a">风格模板：</td>
			<td class="list_b"><select size="1" name="vs.style.id">
				<option value="0">--无--</option>
				<s:iterator value="#request.voteStyleAll" id="x" status="st">
					<option <s:if test="#x.id==vs.style.id">selected</s:if>
						value=${x.id}>${x.title}</option>
				</s:iterator>

			</select></td>
		</tr>

		<tr>
			<td class="list_a">是否允许查看投票结果：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.openResult" >checked</s:if> name="vs.openResult">允许<input
				type="radio" <s:if test="vs.openResult==false">checked</s:if>
				name="vs.openResult" value="false">禁止</td>
		</tr>
		<tr>
			<td class="list_a">是否允许网上报名：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.netJoin" >checked</s:if> name="vs.netJoin">允许<input
				type="radio" <s:if test="vs.netJoin==false">checked</s:if>
				name="vs.netJoin" value="false">禁用</td>
		</tr>
		<tr>
			<td class="list_a">网上报名方式：</td>
			<td class="list_b"><input type="radio" value="false"
				<s:if test="vs.netJoinMustBeMember==false">checked</s:if>
				name="vs.netJoinMustBeMember">自由报名<input type="radio"
				<s:if test="vs.netJoinMustBeMember">checked</s:if>
				name="vs.netJoinMustBeMember" value="true">仅注册会员可以报名</td>
		</tr>
		<tr>
			<td class="list_a">网上报名审核方式：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.netJoinAutoPassed" >checked</s:if>
				name="vs.netJoinAutoPassed">网上报名默认通过<input type="radio"
				<s:if test="vs.netJoinAutoPassed==false">checked</s:if>
				name="vs.netJoinAutoPassed" value="false">默认不通过</td>
		</tr>
		
		<tr>
			<td class="list_a">投票时留言通过方式：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.mesAutoPassed" >checked</s:if>
				name="vs.mesAutoPassed">默认通过<input type="radio"
				<s:if test="vs.mesAutoPassed==false">checked</s:if>
				name="vs.mesAutoPassed" value="false">需审核</td>
		</tr>

		<tr>
			<td class="list_a">报名起止时间：</td>
			<td class="list_b"><input type="text" id="signStartTime"
				name="vs.signStartTime" size="20" value="${vs.signStartTime}">
			- <input type="text" id="signEndTime" name="vs.signEndTime" size="20"
				value="${vs.signEndTime}"></td>
		</tr>

		<tr>
			<td class="list_a">最大投票数：</td>
			<td class="list_b"><input type="text" name="vs.upperLimit"
				size="10" value="${vs.upperLimit}"> <input type="checkbox"
				name="vs.fullUpperConstraint" value="true"
				<s:if test="vs.fullUpperConstraint">checked</s:if>>强制(多选少选均无效，不选中可以少选。)</td>
		</tr>
		<tr>
			<td class="list_a">IP限制：</td>
			<td class="list_b"><input type="text" name="vs.hoursAtIpRule"
				size="10" value="${vs.hoursAtIpRule}">填数字，以小时算，0代表不限</td>
		</tr>
		<tr>
			<td class="list_a">机器身份限制(cookie方式)：</td>
			<td class="list_b"><input type="text"
				name="vs.hoursAtMachineRule" size="10"
				value="${vs.hoursAtMachineRule}">填数字，以小时算，0代表不限</td>
		</tr>
		<tr>
			<td class="list_a">投票人身份防刷检查：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.identityRule">checked</s:if> name="vs.identityRule">使用<input
				type="radio" <s:if test="vs.identityRule==false">checked</s:if>
				name="vs.identityRule" value="false">禁用</td>
		</tr>
		<tr>
			<td class="list_a">投票人联系电话或手机防刷检查：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.phoneCodeRule">checked</s:if> name="vs.phoneCodeRule">使用<input
				type="radio" <s:if test="vs.phoneCodeRule==false">checked</s:if>
				name="vs.phoneCodeRule" value="false">禁用</td>
		</tr>
		
		<tr>
			<td class="list_a">投票人姓名汉字检查：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.posterNameCCUChk">checked</s:if> name="vs.posterNameCCUChk">使用<input
				type="radio" <s:if test="vs.posterNameCCUChk==false">checked</s:if>
				name="vs.posterNameCCUChk" value="false">禁用</td>
		</tr>

		<tr>
			<td class="list_a">投票使用验证码：</td>
			<td class="list_b"><input type="radio" value="true"
				<s:if test="vs.useVerifyCode">checked</s:if> name="vs.useVerifyCode">使用<input
				type="radio" <s:if test="vs.useVerifyCode==false">checked</s:if>
				name="vs.useVerifyCode" value="false">禁用</td>
		</tr>
		<tr>
			<td class="list_a">投票起止时间：</td>
			<td class="list_b"><input type="text" id="voteStartTime"
				name="vs.voteStartTime" size="20" value="${vs.voteStartTime}">
			- <input type="text" id="voteEndTime" name="vs.voteEndTime" size="20"
				value="${vs.voteEndTime}"></td>
		</tr>

		<tr>
			<td class="list_a">密码投票：</td>
			<td class="list_b"><input id="votePassword" type="text"
				name="vs.votePassword" size="60"
				value="<s:if test="vs.votePassword.trim().equals('')">不采用，若要采用请在此输入框内输入密码</s:if><s:else>已设置，此输入框内可修改密码，清空请输入0</s:else>">
			如果采用密码，投票页面必须加入密码输入框才可投票。</td>
		</tr>
		<tr>
			<td class="list_a">排序方式：</td>
			<td class="list_b"><select size="1" name="vs.orderType">
				<option value="0" <s:if test="vs.orderType==0">selected</s:if>>序号</option>
				<option value="1" <s:if test="vs.orderType==1">selected</s:if>>音序</option>
				<option value="2" <s:if test="vs.orderType==2">selected</s:if>>笔画</option>
				<option value="3" <s:if test="vs.orderType==3">selected</s:if>>随机</option>
			</select></td>
		</tr>

		<tr>
			<td class="list_a">密钥：</td>
			<td class="list_b"><input type="text" name="vs.secStr"
				size="10" value="${vs.secStr}"> 建议不作修改</td>
		</tr>
		<tr>
			<td class="list_a">计分百分比：</td>
			<td class="list_b"><input type="text" name="vs.percent"
				size="10" value="${vs.percent}">% 默认规则为：(当前投票数/最高投票数)* 百分比%</td>
		</tr>
		
		
	</table>
	</div>
	<div class="list_oneline"><input type="submit" value="提交"
		onclick="javascript:gook();" name="B1"> <input type="reset"
		value="复位" name="B2"></div>
	</form>





	<div class="list_oneline"><a
		href="./admin_vote_subject_findAll.action">返回</a></div>

	</div>


	<script>
  
	 $.datepicker.regional['ru'] = {
		  		
				dateFormat: 'yy-mm-dd',
				changeYear: true,
				yearRange: '-10:+10',
				monthNames: ['元月','二月','三月','四月','五月',
				         	'六月','七月','八月','九月','十月','十一月','十二月'],
				monthNamesShort: ['一','二','三','四','五','六',
				         	'七','八','九','十','十一','十二'],
				dayNamesMin: ['日','一','二','三','四','五','六'],
				weekHeader: '星期'
			};

	  $.datepicker.setDefaults($.datepicker.regional['ru']);
	  
	  $.timepicker.regional['ru'] = {
			  showSecond: true,
			  timeFormat: 'hh:mm:ss',
			  timeOnlyTitle: '选择时间',
			  timeText: '时间',
			  hourText: '时',
			  minuteText: '分',
			  secondText: '秒',
				millisecText: '毫秒',
				currentText: '当前',
				closeText: '关闭',
				stepHour: 1,
				stepMinute: 1,
				stepSecond: 1,
				
				ampm: false
			};
	$.timepicker.setDefaults($.timepicker.regional['ru']);


function dateClearM(s){
	//var s = "2012-04-05 06:05:34:345";
	s=s.replace(/\s\s/g," ");
	s=s.substr(0,19);
	return s;
}



$('#signStartTime').val(dateClearM($('#signStartTime').val()));
$('#signEndTime').val(dateClearM($('#signEndTime').val()));
$('#voteStartTime').val(dateClearM($('#voteStartTime').val()));
$('#voteEndTime').val(dateClearM($('#voteEndTime').val()));

//var   d   =   new   Date(Date.parse($('#signStartTime').val().replace(/-/g,   "/")));
//$('#signStartTime').val(new Date(date1).format('yyyy-MM-dd hh:mm:ss'));

$('#signStartTime').datetimepicker();
$('#signEndTime').datetimepicker();
$('#voteStartTime').datetimepicker();
$('#voteEndTime').datetimepicker();

$('#signStartTime').datetimepicker('setDateTime', $('#signStartTime').val());
$('#signEndTime').datetimepicker('setDateTime', $('#signEndTime').val());
$('#voteStartTime').datetimepicker('setDateTime', $('#voteStartTime').val());
$('#voteEndTime').datetimepicker('setDateTime', $('#voteEndTime').val());

	
	
	
	
  $('#votePassword').css("backgrounp", "red"); 
  
  $("#votePassword").focus(function cls() {
      //捕获触发事件的对象，并设置为以下语句的默认对象 
      with (event.srcElement)
      //如果当前值为默认值，则清空 
          if (value == defaultValue) value = ""
  });
  $("#votePassword").blur(function res() {
      //捕获触发事件的对象，并设置为以下语句的默认对象 
      with (event.srcElement)
      //如果当前值为空，则重置为默认值 
          if (value == "") value = defaultValue
  });
  
  
  function gook() {
		
		

		var pw1=$.trim($('#votePassword')[0].defaultValue);
		var pw2=$.trim($('#votePassword').val());
		
		if (pw1==pw2){
			$('#votePassword').val("~");
			//alert("与初始值相同！");
			
		}
			//$('#password').value=pw1;
			f.submit();
		
		
	}
  
  	</script>
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>