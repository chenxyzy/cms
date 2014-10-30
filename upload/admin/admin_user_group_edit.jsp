<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
	<html>
	<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

	<style type=text/css>
body {
	background: #CAD7F7;
	font-size: 12px;
	margin-top: 0px;
	SCROLLBAR-FACE-COLOR: #799AE1;
	SCROLLBAR-HIGHLIGHT-COLOR: #799AE1;
	SCROLLBAR-SHADOW-COLOR: #799AE1;
	SCROLLBAR-DARKSHADOW-COLOR: #799AE1;
	SCROLLBAR-3DLIGHT-COLOR: #799AE1;
	SCROLLBAR-ARROW-COLOR: #FFFFFF;
	SCROLLBAR-TRACK-COLOR: #AABFEC;
}

TD {
	FONT-SIZE: 12px
}

INPUT {
	BORDER-TOP-WIDTH: 1px;
	BORDER-LEFT-WIDTH: 1px;
	FONT-SIZE: 12px;
	BORDER-BOTTOM-WIDTH: 1px;
	BORDER-RIGHT-WIDTH: 1px
}

TEXTAREA {
	BORDER-TOP-WIDTH: 1px;
	BORDER-LEFT-WIDTH: 1px;
	FONT-SIZE: 12px;
	BORDER-BOTTOM-WIDTH: 1px;
	BORDER-RIGHT-WIDTH: 1px
}

SELECT {
	BORDER-TOP-WIDTH: 1px;
	BORDER-LEFT-WIDTH: 1px;
	FONT-SIZE: 12px;
	BORDER-BOTTOM-WIDTH: 1px;
	BORDER-RIGHT-WIDTH: 1px
}

SPAN {
	FONT-SIZE: 12px;
	POSITION: static
}

A:link {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #000000;
	TEXT-DECORATION: none
}

a:hover {
	color: #428EFF;
	text-decoration: underline;
}

A.highlight:link {
	COLOR: red;
	TEXT-DECORATION: none
}

A.highlight:visited {
	COLOR: red;
	TEXT-DECORATION: none
}

A.highlight:hover {
	COLOR: red
}

A.thisclass:link {
	FONT-WEIGHT: bold;
	TEXT-DECORATION: none
}

A.thisclass:visited {
	FONT-WEIGHT: bold;
	TEXT-DECORATION: none
}

A.thisclass:hover {
	FONT-WEIGHT: bold
}

A.navlink:link {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A.navlink:visited {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A.navlink:hover {
	COLOR: #003399;
	TEXT-DECORATION: none
}

.twidth {
	WIDTH: 760px
}

.content {
	FONT-SIZE: 14px;
	MARGIN: 5px 20px;
	LINE-HEIGHT: 140%;
	FONT-FAMILY: Tahoma, 宋体
}

.aTitle {
	FONT-WEIGHT: bold;
	FONT-SIZE: 15px
}

TD.forumHeaderBackgroundAlternate {
	BACKGROUND-IMAGE: url(./images/admin_top_bg.gif);
	COLOR: #000000;
	BACKGROUND-COLOR: #799ae1
}

#TableTitleLink A:link {
	COLOR: #ffffff;
	TEXT-DECORATION: none
}

#TableTitleLink A:visited {
	COLOR: #ffffff;
	TEXT-DECORATION: none
}

#TableTitleLink A:active {
	COLOR: #ffffff;
	TEXT-DECORATION: none
}

#TableTitleLink A:hover {
	COLOR: #ffffff;
	TEXT-DECORATION: underline
}

TD.forumRow {
	PADDING-RIGHT: 3px;
	PADDING-LEFT: 3px;
	BACKGROUND: #F1F3F5;
	PADDING-BOTTOM: 3px;
	PADDING-TOP: 3px
}

TH {
	FONT-WEIGHT: bold;
	FONT-SIZE: 12px;
	BACKGROUND-IMAGE: url(./images/admin_bg_1.gif);
	COLOR: white;
	BACKGROUND-COLOR: #4455aa
}

TD.bodytitle {
	BACKGROUND-IMAGE: url(./images/admin_bg_2.gif)
}

TD.bodytitle1 {
	BACKGROUND-IMAGE: url(./images/admin_bg_3.gif)
}

TD.tablebody1 {
	PADDING-RIGHT: 3px;
	PADDING-LEFT: 3px;
	BACKGROUND: #bebbdb;
	PADDING-BOTTOM: 3px;
	PADDING-TOP: 3px
}

TD.forumRowHighlight {
	PADDING-RIGHT: 3px;
	PADDING-LEFT: 3px;
	BACKGROUND: #E4EDF9;
	PADDING-BOTTOM: 3px;
	PADDING-TOP: 3px
}

.tableBorder {
	BORDER-RIGHT: #183789 1px solid;
	BORDER-TOP: #183789 1px solid;
	BORDER-LEFT: #183789 1px solid;
	WIDTH: 98%;
	BORDER-BOTTOM: #183789 1px solid;
	BACKGROUND-COLOR: #ffffff
}

.tableBorder1 {
	WIDTH: 98%;
}

.helplink {
	FONT: 10px verdana, arial, helvetica, sans-serif;
	CURSOR: help;
	TEXT-DECORATION: none
}

.copyright {
	PADDING-RIGHT: 1px;
	BORDER-TOP: #6595d6 1px dashed;
	PADDING-LEFT: 1px;
	PADDING-BOTTOM: 1px;
	FONT: 11px verdana, arial, helvetica, sans-serif;
	COLOR: #4455aa;
	PADDING-TOP: 1px;
	TEXT-DECORATION: none
}

.myPopPowerMaskCodeDiv_input tr:hover {
	background: #e6e6e6;
}

/*增加pop*/
.myPopPowerMaskCodeDiv {
	background: #fff;
	border: 2px solid #666;
	text-align: center;
	z-index: 999;
	width: 600px;
	height: 300px;
	left: 50%;
	top: 40%;
	border-left: 2px solid #eee;
	border-top: 2px solid #eee;
	margin-left: -300px !important; /*FF IE7 该值为本身宽的一半 */
	margin-top: -150px !important; /*FF IE7 该值为本身高的一半*/
	margin-top: 0px;
	position: fixed !important; /* FF IE7*/
	position: absolute; /*IE6*/
	_top: expression(eval(document.compatMode && 
             document.compatMode == 'CSS1Compat') ? 
             documentElement.scrollTop +   (
		document.documentElement.clientHeight-this.offsetHeight )/2 : /*IE6*/ 
             document.body.scrollTop +   ( document.body.clientHeight -
		  this.clientHeight )/2 ); /*IE5 IE5.5*/
}

.myPopPowerMaskCodeDiv_top {
	background: #3CC;
	height: 30px;
}

.myPopPowerMaskCodeDiv_top_left {
	float: left;
	padding: 7px 0 0 20px;
}

.myPopPowerMaskCodeDiv_top_rig {
	float: right;
	padding: 7px 10px 0 0;
}

.myPopPowerMaskCodeDiv_input {
	float: left;
	width: 600px;
	height: 270px;
	overflow-y: auto;
	overflow-x: hidden;
}

.bg,.PopPowerMaskCodeIframe {
	background-color: #666;
	display: none;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0; /*FF IE7*/
	filter: alpha(opacity = 50); /*IE*/
	opacity: 0.5; /*FF*/
	z-index: 1;
	position: fixed !important; /*FF IE7*/
	position: absolute; /*IE6*/
	_top: expression(eval(document.compatMode && 
             document.compatMode == 'CSS1Compat') ? 
             documentElement.scrollTop +   (
		document.documentElement.clientHeight-this.offsetHeight )/2 : /*IE6*/ 
             document.body.scrollTop +   ( document.body.clientHeight -
		  this.clientHeight )/2 ); /* www.codefans.net IE5 IE5.5*/
}

.PopPowerMaskCodeIframe {
	filter: alpha(opacity = 0); /*IE*/
	opacity: 0; /*FF*/
}
</style>
	<title>用户组资料修改</title>
	<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>

	<script type="text/javascript">
	function showDivPowerMaskCodeDiv() {
		$(document).ready(function() {
			powerArea();
			$("#popPowerMaskCodeDiv").show();
			$("#PopPowerMaskCodeIframe").show();
			$("#bg").show();

			//$("#PowerMaskCodeBody").load('PowerMaskCode.txt');
			//$("#PowerMaskCodeBody").each(function() { $(this).text($(this).val()); });
			//$("#PowerMaskCodeBody").text("asdfasdf");
		});
	}

	function closeDivPowerMaskCodeDiv() {
		$(document).ready(function() {
			$("#popPowerMaskCodeDiv").hide();
			$("#PopPowerMaskCodeIframe").hide();
			$("#bg").hide();
		});
	}

	function savePowerMask() {
		var codeAll = "", tmp, adminStr = "";
		var allAdd=false,qaAll=false;
		var add;
		$('#popPowerMaskCodeDiv tr').each(function() {
				
				tmp = $(this).find(':input[type=radio]:checked').val();
				tmp=$.trim(tmp);
				add=false;
				if (tmp.substring(0,1) != "" && tmp.substring(0,1) != "0") {
					
					if (tmp.length==2 && tmp.substring(0,2) == "a0"){
						if (!allAdd){
							add=true;
							allAdd=true;
						}
						
					}else if (tmp.length==1 && tmp == "q"){
						if (!qaAll){
							add=true;
							qaAll=true;
						}
					}else{
						if (!allAdd && (tmp.length>1 && tmp.substring(0,1) == "a")){
							add=true;
						}else
						if (!qaAll && (tmp.length>1 && tmp.substring(0,1) == "q")){
							add=true;
						}else
						
						
						
						if (!(tmp.substring(0,1) == "q" || tmp.substring(0,1) == "a")){
							add=true;
						}
					
						
					}
					
				
					if (add){
						if (codeAll == "") {
							codeAll += tmp;
						} else {
							codeAll += "," + tmp;
						}
					}
					
					//if (tmp.length==2 && tmp.substring(0,2) == "a0"){
					//	allAdd=true;
					//	if (codeAll == "") {
					//		codeAll += tmp;
					//	} else {
					//		codeAll += "," + tmp;
					//	}
					//	//alert(allAdd);
					//}else if (tmp.length==1 && tmp == "q"){
					//	qaAll=true;
					//	if (codeAll == "") {
					//		codeAll += tmp;
					//	} else {
					//		codeAll += "," + tmp;
					//	}
					//}else{
						
					//	if (!(allAdd && tmp.length==2 && tmp.substring(0,1) == "a")){
					//		if (!(qaAll && tmp.length==1 && tmp == "q")){
					//			if (codeAll == "") {
					//				codeAll += tmp;
					//			} else {
					//				codeAll += "," + tmp;
					//			}
					//		}
							
					//	}
					//}
					
					
					

				} else if (tmp == "0") {
					adminStr = "0";
				}

		});
		if (adminStr == "") {
			$('#powerMask').val(codeAll);
		} else {
			$('#powerMask').val(adminStr);
		}

		closeDivPowerMaskCodeDiv();
	}

	function powerArea() {
		$("#myPopPowerMaskCodeDiv_input").html("正在检测...");
		$.ajax({
			url : 'ajax_articleGroupMask_Show.action?code=' + $("#powerMask").val(),
			type : 'post',
			success : function(data) {
				$("#myPopPowerMaskCodeDiv_input").html(data);
			}
		});

	}
</script>
	</head>

	<body>
	<div id="popPowerMaskCodeDiv" class="myPopPowerMaskCodeDiv"
		style="display: none;">
	<div class="myPopPowerMaskCodeDiv_top">
	<div class="myPopPowerMaskCodeDiv_top_left">权限设置</div>
	<div class="myPopPowerMaskCodeDiv_top_rig"><a
		href="javascript:savePowerMask()">确定</a> <a
		href="javascript:closeDivPowerMaskCodeDiv()">取消</a></div>
	</div>
	<div class="myPopPowerMaskCodeDiv_input"
		id="myPopPowerMaskCodeDiv_input"></div>
	</div>
	<div id="bg" class="bg" style="display: none;"></div>
	<iframe id='PopPowerMaskCodeIframe' class='PopPowerMaskCodeIframe'
		frameborder='0'></iframe>

	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		align="center" style="border: 1 solid #808080">

		<tr>
			<th class="tableHeaderText" height=25 colspan="2">用户组资料修改</th>
			<form method="POST"
				action="admin_userGroup_modify.action?userGroup.id=${userGroup.id}">
			</center>
			<tr>
				<td width="35%" class="forumRow" align="right">
				<p align="right">>用户组名：</p>
				</td>
				<center>
				<td width="65%" class="forumRow">
				<p align="left"><input type="text"  size="60" name="userGroup.groupName" value="${userGroup.groupName}"></p>
				</td>
			</tr>

			</center>
			<tr>
				<td width="35%" class="forumRow" align="right">
				<p align="right">权限码：</p>
				</td>
				<center>
				<td width="65%" class="forumRow">
				<p align="left"><input type="text" id="powerMask"
					name="userGroup.powerMask" size="60" value="${userGroup.powerMask}">
				<a href="javascript:showDivPowerMaskCodeDiv();">设置</a></p>
				</td>
			</tr>

			</center>

			<tr>
				<td width="35%" class="forumRow" align="right">
				<p align="right">状态：</p>
				</td>
				<center>
				<td width="65%" class="forumRow">
				<p align="left"><input type="radio" value="true"
					<s:if test="userGroup.state">checked</s:if> name="userGroup.state">正常<input
					type="radio" <s:if test="userGroup.state==false">checked</s:if>
					name="userGroup.state" value="false">禁用</p>
				</td>
			</tr>

			</center>
			
			<tr>
				<td width="35%" class="forumRow" align="right">
				<p align="right">日发文限制：</p>
				</td>
				<center>
				<td width="65%" class="forumRow">
				<p align="left"><input type="radio" value="true"
					<s:if test="userGroup.numberAppearRestrict">checked</s:if> name="userGroup.numberAppearRestrict">使用<input
					type="radio" <s:if test="userGroup.numberAppearRestrict==false">checked</s:if>
					name="userGroup.numberAppearRestrict" value="false">禁用</p>
				</td>
			</tr>

			</center>
			
			<tr>
				<td width="35%" class="forumRow" align="right">
				<p align="right">私有html：</p>
				</td>
				<center>
				<td width="65%" class="forumRow">
				<p align="left"><textarea rows="5" name="userGroup.privateHtml" cols="60">${userGroup.privateHtml}</textarea></p>
				</td>
			</tr>

			</center>
			
			<tr>



				<center>
				<td width="100%" class="forumRow" colspan="2">
				<p align="center"><input type="submit" value="提交" name="B1"></p>
				</td>
			</tr>

			</center>

			<tr>



				<center>
				<td width="100%" class="forumRow" colspan="2">
				<p align="center"><a href="admin_userGroup_query.action">返回</a></p>
				</td>
			</tr>

			</center>

			</form>
	</table>
	</body>
	</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>