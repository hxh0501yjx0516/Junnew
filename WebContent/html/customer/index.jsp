<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>首信联盟 - 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.jclock.js"></script>
<script type="text/javascript">
$(function(){$("#divTime").jclock({withDate:true, withWeek:true});});
function logout(obj){
		document.logoutWForm.submit(); 
		return true;
	}
//点击客户左侧选项卡显示内容
function customerList(target,parms){
			var url = "customer.do?action="+target;
			if(parms){
				url+="&flag="+parms;
			}
    		document.getElementById("listFrame").src = url;
}
</script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<!--information begin-->
 <div id="logininfo" align="center" >
	<form name="logoutWForm" method="post" action="logout.do?action=customer" >
	<table width="950px;" border="0" cellpadding="0"  >
      <tr>
        <td width="20%">您好，${customer.customerName}！</td>
        <td width="33%"><div id="divTime"></div></td>
        <td width="45%" align="right" ><a href="###" onclick="logout('logoutCForm');" >【退出登陆】</a></td>
      </tr>
    </table>
    </form>
    </div>
<!--information end-->
<div id="main">
    <div id="webtree">	
        <!--站长菜单-->
       <div id="b1" class="webtree style2" onclick="changeLeftTab(this);customerList('myhome');"><img src="html/images/Home.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">我的首页</a></div>
      <div id="b2" class="webtree style1" onclick="changeLeftTab(this);customerList('adplan');"><img src="html/images/checklist256.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">广告计划</a></div>
      <div id="b3" class="webtree style1" onclick="changeLeftTab(this);customerList('adplancycle');"><img src="html/images/members0.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">广告排期</a></div>
      <div id="b4" class="webtree style1" onclick="changeLeftTab(this);customerList('report','index');"><img src="html/images/domain.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">数据报表</a></div>
      <div id="b5" class="webtree style1" onclick="changeLeftTab(this);customerList('mypage');"><img src="html/images/schedule256.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">会员信息</a></div>
    </div>  
      
  <div id="con">
  <iframe id="listFrame" src="customer.do?action=myhome" scrolling=no  frameBorder=0 width="100%" onload="iFrameHeight()"></iframe>
  </div>
    <div class="clear"></div>
</div>


<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>