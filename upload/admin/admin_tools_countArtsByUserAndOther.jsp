<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<form name="f" id="f" method="post"
			action="tools_countArtByUserAndOther.action">
			<s:action name="admin_userGroup_query"></s:action>
<p>请选择用户组：<select size="1" name="ugid">
	<option <s:if test="user.userGroup==null">selected</s:if> value="0">全部</option>
	<s:iterator value="#request.allGroup" id="x" status="st"> 
	
	
		<option value="${x.id}" <s:if test="#x.id==user.userGroup.id">selected</s:if>>${x.groupName}</option>
	</s:iterator>
  </select>
</p>
<p>请输入栏目id：<input type="text" id="navsStr"
				name="navsStr" size="20">(注：上级栏目将包含下级栏目，无须重复输入)</p>
<p>请输入查询时间段：<input type="text" id="beginTime"
				name="begin" size="20">
			- <input type="text" id="endTime" name="end" size="20">(注：不包括结束时间。如：2011-03-04 00:00:00 至 2011-03-11 00:00:00，查询的是2011年3月4日至2011年3月10号之间的记录。)</p>
<p><input type="submit" value="提交" name="B1"><input type="reset" value="重置" name="B2"></p>
</form>
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


//var   d   =   new   Date(Date.parse($('#signStartTime').val().replace(/-/g,   "/")));
//$('#signStartTime').val(new Date(date1).format('yyyy-MM-dd hh:mm:ss'));

$('#beginTime').datetimepicker();
$('#endTime').datetimepicker();

</script>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>