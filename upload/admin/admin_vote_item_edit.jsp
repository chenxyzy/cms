<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="admin_const.jsp" />
<s:if test="#session.LerxAdmin=='true'">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
body{ font-size:12px; font-family:Arial, Helvetica, sans-serif; margin:0; padding:0 0 0 5px;}

a:link{text-decoration : none ;color : #333 ;} /*链接*/
a:visited {text-decoration : none ;color : #333 ;} /*已访问链接*/
a:hover {text-decoration : none ;color : #f00 ;} /*鼠标经过链接*/
a:active {text-decoration : none ;color : #333 ;}  /*鼠标按下链接*/

.aebox{ width:100%;}
.aeheadbox{ background:url(images/btbgm.jpg); height:34px;}
.aeheadl{ height:34px; width:5px; background:url(images/btbgl.jpg) no-repeat; float:left;}
.aeheadm{ height:25px;  padding: 9px 0 0 15px; color:#FFF; font-size:14px;font-weight:bold; float:left;}
.helplist{height:25px;  padding: 5px 0 0 30px; float:left;}
.aeheadr{ height:34px; width:3px; background:url(images/btbgr.jpg) no-repeat; float:right;}

.aelist{ width:99%; margin:auto;}
.aelist tr{ background:#f5f5f5;}
.trlist td{background:#aaa; height:24px;font-weight:bold;color:#FFF;}
.aelist tr:hover{ background:#e6e6e6;}
.list_1,.list_2,.list_3,.list_4,.list_5,.list_6,.list_7{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:center; padding:5px;}
.list_a{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:right; padding:5px;}
.list_b{border-bottom:#e4e4e4 1px solid;border-right:#e4e4e4 1px solid;
border-left:#fff 1px solid;border-top:#fff 1px solid;vertical-align: middle; text-align:left; padding:5px;}
.list_1{ width:5%;border-left:0px; text-align:center;}
.list_2{width:30%; text-align:left;}
.list_3{width:10%;}
.list_4{width:10%;}
.list_5{width:10%;}
.list_6{width:10%;}
.list_7{width:10%;}
.list_a{width:35%;}
.list_b{width:65%;}
.list_oneline{ background: #CCC;border-right:#999 1px solid; border-bottom:#999 1px solid; border-left:0;border-top:#fff 1px solid; height: 30px; text-align:center; padding-top: 10px;}
.list_oneline2{ background: #eee;border-right:#e4e4e4 1px solid; border-bottom:#e4e4e4 1px solid; border-left:0;border-top:#fff 1px solid; height: 20px; text-align:center; padding-top: 5px;}
.trlist .list_1,.trlist .list_2,.trlist .list_3,.trlist .list_4,.trlist .list_5,.trlist .list_6,.trlist .list_7,.trlist .list_8{ border-right:#999; border-bottom:#999; text-align:center;}
</style>
<title>投票子项目详细设置</title>
</head>

<body>
	
	<div class="aebox">
   <div class="aeheadbox">
  <div class="aeheadl"></div>
  <div class="aeheadm">>　投票子项目详细设置</div>
  <div class="aeheadr"></div>
 </div>
  <div class="aelist">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <form method="post" action="admin_vote_item_modify.action?vi.id=${vi.id}&subId=<s:property value="#parameters.subId"/>">
  <tr >
    <td class="list_a">投票子项目名称：</td>
    <td class="list_b"><input type="text" name="vi.title" size="60" value="${vi.title}"></td>
  </tr>
  <tr >
    <td class="list_a">详情：</td>
    <td class="list_b"><textarea rows="10" name="vi.body" cols="80">${vi.body}</textarea></td>
  </tr>
  </tr>
    <tr >
    <td class="list_a">状态：</td>
    <td class="list_b"><input type="radio" value="true" <s:if test="vi.state">checked</s:if> name="vi.state">正常<input type="radio" <s:if test="vi.state==false">checked</s:if> name="vi.state" value="false">禁用</td>
  </tr>

    <tr >
    <td class="list_a">item1：</td>
    <td class="list_b"><input type="text" name="vi.item1" size="60" value="${vi.item1}"></td>
  </tr>

    <tr >
    <td class="list_a">item2：</td>
    <td class="list_b"><input type="text" name="vi.item2" size="60" value="${vi.item2}"></td>
  </tr>

    <tr >
    <td class="list_a">item3：</td>
    <td class="list_b"><input type="text" name="vi.item3" size="60" value="${vi.item3}"></td>
  </tr>

    <tr >
    <td class="list_a">item4：</td>
    <td class="list_b"><input type="text" name="vi.item4" size="60" value="${vi.item4}"></td>
  </tr>

    <tr >
    <td class="list_a">item5：</td>
    <td class="list_b"><input type="text" name="vi.item5" size="60" value="${vi.item5}"></td>
  </tr>

    <tr >
    <td class="list_a">item6：</td>
    <td class="list_b"><input type="text" name="vi.item6" size="60" value="${vi.item6}"></td>
  </tr>

    <tr >
    <td class="list_a">item7：</td>
    <td class="list_b"><input type="text" name="vi.item7" size="60" value="${vi.item7}"></td>
  </tr>

    <tr >
    <td class="list_a">item8：</td>
    <td class="list_b"><input type="text" name="vi.item8" size="60" value="${vi.item8}"></td>
  </tr>

    <tr >
    <td class="list_a">item9：</td>
    <td class="list_b"><input type="text" name="vi.item9" size="60" value="${vi.item9}"></td>
  </tr>

    <tr >
    <td class="list_a">item10：</td>
    <td class="list_b"><input type="text" name="vi.item10" size="60" value="${vi.item10}"></td>
  </tr>

    <tr >
    <td class="list_a">item11：</td>
    <td class="list_b"><input type="text" name="vi.item11" size="60" value="${vi.item11}"></td>
  </tr>

    <tr >
    <td class="list_a">item12：</td>
    <td class="list_b"><input type="text" name="vi.item12" size="60" value="${vi.item12}"></td>
  </tr>

    <tr >
    <td class="list_a">item13：</td>
    <td class="list_b"><input type="text" name="vi.item13" size="60" value="${vi.item13}"></td>
  </tr>

    <tr >
    <td class="list_a">item14：</td>
    <td class="list_b"><input type="text" name="vi.item14" size="60" value="${vi.item14}"></td>
  </tr>

    <tr >
    <td class="list_a">item15：</td>
    <td class="list_b"><input type="text" name="vi.item15" size="60" value="${vi.item15}"></td>
  </tr>

    <tr >
    <td class="list_a">item16：</td>
    <td class="list_b"><input type="text" name="vi.item16" size="60" value="${vi.item16}"></td>
  </tr>

    <tr >
    <td class="list_a">item17：</td>
    <td class="list_b"><input type="text" name="vi.item17" size="60" value="${vi.item17}"></td>
  </tr>

    <tr >
    <td class="list_a">item18：</td>
    <td class="list_b"><input type="text" name="vi.item18" size="60" value="${vi.item18}"></td>
  </tr>

    <tr >
    <td class="list_a">item19：</td>
    <td class="list_b"><input type="text" name="vi.item19" size="60" value="${vi.item19}"></td>
  </tr>

    <tr >
    <td class="list_a">item20：</td>
    <td class="list_b"><input type="text" name="vi.item20" size="60" value="${vi.item20}"></td>
  </tr>


  
   
</table>
</div>
<div class="list_oneline"><input type="submit" value="提交" name="B1">　　　<input type="reset" value="复位" name="B2"></div> 
</form> 



	
  <div class="list_oneline"><a href="./admin_vote_items_list.jsp?id=35&page=1">返 回</a></div> 

</div>
</body>
</html>
</s:if>
<s:else>
<s:include value="./admin_err_alt.jsp"></s:include>
</s:else>
