<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<style type=text/css>
body { background:#CAD7F7; font-size: 12px; margin-top:0px;
SCROLLBAR-FACE-COLOR: #799AE1; SCROLLBAR-HIGHLIGHT-COLOR: #799AE1; 
SCROLLBAR-SHADOW-COLOR: #799AE1; SCROLLBAR-DARKSHADOW-COLOR: #799AE1; 
SCROLLBAR-3DLIGHT-COLOR: #799AE1; SCROLLBAR-ARROW-COLOR: #FFFFFF;
SCROLLBAR-TRACK-COLOR: #AABFEC;
}
TD {
	FONT-SIZE: 12px
}
INPUT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
TEXTAREA {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SELECT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SPAN {
	FONT-SIZE: 12px; POSITION: static
}
A:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
a:hover  { color:#428EFF;text-decoration:underline; }
A.highlight:link {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:visited {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:hover {
	COLOR: red
}
A.thisclass:link {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:visited {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:hover {
	FONT-WEIGHT: bold
}
A.navlink:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:hover {
	COLOR: #003399; TEXT-DECORATION: none
}
.twidth {
	WIDTH: 760px
}
.content {
	FONT-SIZE: 14px; MARGIN: 5px 20px; LINE-HEIGHT: 140%; FONT-FAMILY: Tahoma,宋体
}
.aTitle {
	FONT-WEIGHT: bold; FONT-SIZE: 15px
}
TD.forumHeaderBackgroundAlternate {
	BACKGROUND-IMAGE: url(./images/admin_top_bg.gif); COLOR: #000000; BACKGROUND-COLOR: #799ae1
}
#TableTitleLink A:link {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:visited {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:active {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:hover {
	COLOR: #ffffff; TEXT-DECORATION: underline
}
TD.forumRow {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #F1F3F5; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TH {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(./images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR: #4455aa
}
TD.bodytitle {
	BACKGROUND-IMAGE: url(./images/admin_bg_2.gif)
}
TD.bodytitle1 {
	BACKGROUND-IMAGE: url(./images/admin_bg_3.gif)
}
TD.tablebody1 {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #bebbdb; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TD.forumRowHighlight {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #E4EDF9; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
.tableBorder {
	BORDER-RIGHT: #183789 1px solid; BORDER-TOP: #183789 1px solid; BORDER-LEFT: #183789 1px solid; WIDTH: 98%; BORDER-BOTTOM: #183789 1px solid; BACKGROUND-COLOR: #ffffff
}
.tableBorder1 {WIDTH: 98%; }
.helplink {
	FONT: 10px verdana,arial,helvetica,sans-serif; CURSOR: help; TEXT-DECORATION: none
}
.copyright {
	PADDING-RIGHT: 1px; BORDER-TOP: #6595d6 1px dashed; PADDING-LEFT: 1px; PADDING-BOTTOM: 1px; FONT: 11px verdana,arial,helvetica,sans-serif; COLOR: #4455aa; PADDING-TOP: 1px; TEXT-DECORATION: none
}
</style>
<title>抽奖信息修改</title>
<link rel="stylesheet" media="all" type="text/css" href="../js/jquery/ui/1.8.21/themes/ui-lightness/jquery-ui.css" />
		<link rel="stylesheet" media="all" type="text/css" href="../js/jquery/timepicker/jquery-ui-timepicker-addon.css" />
		
		<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery/ui/1.8.21/jquery-ui.min.js"></script>
		<script type="text/javascript" src="../js/jquery/timepicker/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="../js/jquery/examples/timepicker/jquery-ui-sliderAccess.js"></script>

</head>

<body>

<table width="100%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1 solid #808080">

<tr><th class="tableHeaderText" height=25 colspan="2">抽奖信息修改</th>
	<form method="POST" action="admin_draw_modify.action?draw.id=${draw.id}">
	 </center>
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">抽奖名称：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="text" autocomplete="off" name="draw.title" size="60" value="${draw.title}"></p>
    </td>
  </tr>
  
  	 </center>
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">密码：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" >
      <p align="left"><input id="password" type="text" name="draw.password" size="60" value="<s:if test="draw.password==null || draw.password.trim().equals('')">不采用，若要采用请在此输入框内输入密码</s:if><s:else>已设置，此输入框内可修改密码，清空请输入0</s:else>"> 如果采用密码，抽奖页面必须加入密码输入框才可抽奖。</p>
    </td>
  </tr>
  
  	 </center>
  <s:action name="admin_draw_style_query"></s:action>
  <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">风格模板：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" ><p align="left"><select size="1" name="draw.ds.id">
				<option value="0">--默认--</option>
				<s:iterator value="#request.drawStyleAll" id="x" status="st">
					<option <s:if test="#x.id==draw.ds.id">selected</s:if>
						value=${x.id}>${x.styleName}</option>
				</s:iterator>

			</select></p></td>
  </tr>
  
  </center>
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">状态：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="radio" value="true" <s:if test="draw.state">checked</s:if> name="draw.state">正常<input type="radio"  <s:if test="draw.state==false">checked</s:if> name="draw.state" value="false">禁用</p>
    </td>
  </tr>
  
  </center>
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">抽奖个数：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" align="left"><input type="text" autocomplete="off" name="draw.resultNum" size="60" value="${draw.resultNum}"> </td>
  </tr>
  
  </center>
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">投票id序列：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" align="left"><input type="text" autocomplete="off" name="draw.votesRange" size="60" value="${draw.votesRange}"> 以,(英文)分隔。如： 3,8,12,4</td>
  </tr>
  </center>
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">抽奖时排除下面结果：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" align="left"><input type="text" autocomplete="off" name="draw.exceptedRecStr" size="60" value="${draw.exceptedRecStr}"> 以,(英文)分隔。如： 3,8,12,4</td>
  </tr>
  
  </center>
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">抽奖结果：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" align="left"><input type="text" autocomplete="off" name="draw.resultRecStr" size="60" value="${draw.resultRecStr}"> 以,(英文)分隔。如： 3,8,12,4</td>
  </tr>
  
  </center>
  
    <tr>
    <td width="35%" class="forumRow" align="right" >
      <p align="right">抽奖有效时间段：</p>
    </td>
  <center>
    <td width="65%" class="forumRow" >
      <p align="left"><input type="text" id="drawStartTime" name="draw.drawStartTime" size="20"  value="${draw.drawStartTime}"> - <input type="text" id="drawEndTime" name="draw.drawEndTime" size="20" value="${draw.drawEndTime}"></p>
    </td>
  </tr>
    </center>

  <center>
    <td width="100%" class="forumRow" colspan="2">
      <p align="center"><input type="submit" value="提交" name="B1"></p>
    </td>
  </tr>
  
  </center>
  
   <tr>
  
  

  <center>
    <td width="100%" class="forumRow" colspan="2">
      <p align="center"><a href="admin_draw_list.jsp">返回</a></p>
    </td>
  </tr>
  
  </center>
  
  </form>
</table>
<script>
  
  $.datepicker.regional['ru'] = {
		  changeYear: true,	
			dateFormat: 'yy-mm-dd',
			
			monthNames: ['元月','二月','三月','四月','五月',
			         	'六月','七月','八月','九月','十月','十一月','十二月'],
			monthNamesShort: ['一','二','三','四','五','六',
			         	'七','八','九','十','十一','十二'],
			dayNamesMin: ['日','一','二','三','四','五','六'],
			weekHeader: '星期',
			
			closeText: '关闭'
		};

  $.datepicker.setDefaults($.datepicker.regional['ru']);
  
  $.timepicker.regional['ru'] = {
		  timeOnlyTitle: '选择时间',
		  timeText: '时间',
		  hourText: '时',
		  minuteText: '分',
		  secondText: '秒',
			millisecText: '毫秒',
			currentText: '当前',
			closeText: '关闭',
			timeFormat: 'hh:mm:ss',
			stepHour: 1,
			stepMinute: 1,
			stepSecond: 1,
			showSecond: true,
			ampm: false
		};
  $.timepicker.setDefaults($.timepicker.regional['ru']);
  function dateClearM(s){
		//var s = "2012-04-05 06:05:34:345";
		s=s.replace(/\s\s/g," ");
		s=s.substr(0,19);
		return s;
	}
  
  $('#drawStartTime').val(dateClearM($('#drawStartTime').val()));
  $('#drawEndTime').val(dateClearM($('#drawEndTime').val()));
  $('#drawStartTime').datetimepicker();
  $('#drawEndTime').datetimepicker();
  
  $('#drawStartTime').datetimepicker('setDate', $('#drawStartTime').val());
  $('#drawEndTime').datetimepicker('setDate', $('#drawEndTime').val());
  //$('#votePassword').css("backgrounp", "red"); 
  
  $("#password").focus(function cls() {
      //捕获触发事件的对象，并设置为以下语句的默认对象 
      with (event.srcElement)
      //如果当前值为默认值，则清空 
          if (value == defaultValue) value = ""
  });
  $("#password").blur(function res() {
      //捕获触发事件的对象，并设置为以下语句的默认对象 
      with (event.srcElement)
      //如果当前值为空，则重置为默认值 
          if (value == "") value = defaultValue
  });
  
  	</script>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>