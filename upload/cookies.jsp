<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>cookie测试</title>
</head>
<body>

<%

Cookie cookies[]=request.getCookies(); 
Cookie sCookie=null; 
String svalue=null; 
String sname=null; 
for(int i=0;i<cookies.length;i++){ 
sCookie=cookies[i]; 
svalue=sCookie.getValue(); 
sname=sCookie.getName(); 
%>
键：<%=sname%> 值：<%=svalue%><br>
<%
} 
%>
</body>
</html>

