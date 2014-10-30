<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成功！</title>
</head>
<script type="text/javascript" src="../js/jquery/1.7.2/jquery-1.7.2.min.js"></script>
<body>
<p>修改成功！</p>
<p><a href="javascript:closeMsg();">确定</a></p>

<script type="text/javascript">
//$(window.parent.document).find("#StyleDetailsIframe").load(function(){
//	var main = $(window.parent.document).find("#StyleDetailsIframe");
//	var thisheight = $(document).height()+30;
//	main.height(thisheight);
//	});

function closeMsg(){

window.parent.closeStyleDetailsDiv();
}
</script>
</body>
</html>