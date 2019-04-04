<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>北京中润无线广告有限公司</title>
<!-- <link href="html/images/jnico.ico" rel="SHORTCUT ICON"> -->
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.jclock.js"></script>
<script type="text/javascript" src="html/js/jquery.blockUI.js"></script>
<script type="text/javascript">
$(function(){$("#divTime").jclock({withDate:true, withWeek:true});});
function logout(obj){
		document.logoutWForm.submit();
	}
//点击客户左侧选项卡显示内容
function webMasterList(action,target,parms){
			var url = action+".do?action="+target;
			if(parms){
				//当天时间标识
				url+="&flag="+parms;
			}
    		document.getElementById("listFrame").src = url;
}
function showDiv(id){
	 $.blockUI({ message: $('#iframeDiv'),
		 		css: { 
		        top:		'30%',
		        left:		'50%',
		        textAlign:	'left',
		        marginLeft:     '-320px', 
		        marginTop:      '-145px', 
                width: '600px',
                background:'none'
            } }); 
	 $('.blockOverlay').attr('title','单击关闭').click($.unblockUI); 
	 document.getElementById("details").src = "webmaster.do?action=balanceDetails&payId="+id;
}
</script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<!--information begin-->
<div id="logininfo">
	<form name="logoutWForm" method="post" action="logout.do?action=webmaster" >
	<table width="100%" border="0" cellpadding="0">
      <tr>
        <td width="15%">&nbsp;</td>
        <td width="15%">您好，${webmaster.webMasterName}！</td>
        <td width="43%"><div id="divTime"></div></td>
        <td width="8%"><a href="###" onclick="logout('logoutWForm');"  style="color: white;">【退出登陆】</a></td>
        <td width="11%"></td>
      </tr>
    </table>
    </form>
</div>
<!--information end-->

<div id="main">
    
    <!--button begin-->
    <div id="leftmain">
      <div id="b1" class="button2" onclick="changeLeftTab(this);webMasterList('webmaster','myhome');return false;"><img src="html/images/Home.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">我的首页</a></div>
      <div id="b12" class="button" onclick="changeLeftTab(this);webMasterList('code','step1');return false;"><img src="html/images/page_white_code_red.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">代码获取</a></div>
      <div id="b2" class="button" onclick="changeLeftTab(this);webMasterList('adplan','list');return false;"><img src="html/images/product-sales-report0.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">广告计划</a></div>
      <div id="b3" class="buttonnone"></div>
      <div id="b4" class="button" onclick="changeLeftTab(this);webMasterList('webmaster','mypage');return false;"><img src="html/images/members0.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">资料管理</a></div>
      <div id="b5" class="button" onclick="changeLeftTab(this);webMasterList('webmaster','urladdress');return false;"><img src="html/images/domain.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">域名列表</a></div>
      <div id="b6" class="button" onclick="changeLeftTab(this);webMasterList('adplan','myadplan');return false;"><img src="html/images/schedule256.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">我的计划</a></div>
      <div id="b7" class="button" onclick="changeLeftTab(this);webMasterList('adplan','readybox');return false;"><img src="html/images/checklist256.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">投放列表</a></div>
      <div id="b8" class="buttonnone"></div>
      <div id="b10" class="button" onclick="changeLeftTab(this);webMasterList('report','history');return false;"><img src="html/images/statistics0.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">数据统计</a></div>
      <div id="b9" class="button" onclick="changeLeftTab(this);webMasterList('report','unbalance');return false;"><img src="html/images/order-history0.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">数据返回</a></div>
      <div id="b11" class="button" onclick="changeLeftTab(this);webMasterList('webmaster','balance');return false;"><img src="html/images/yuan256.png" align="absmiddle">&nbsp;&nbsp;<a href="javascript:void(0);">收入查询</a></div>
    </div>
    <!--button end-->
     <!--内容 begin-->
    <div id="rightmain">
    <iframe id="listFrame" src="webmaster.do?action=myhome" scrolling=no  frameBorder=0 width="100%" onload="iFrameHeight()"></iframe>
    </div>
    <!--内容 end-->
</div>
<div id="iframeDiv" class="box" >
    <iframe id="details" src="webmaster.do?action=myhome" scrolling=no  frameBorder=0 width="100%" onload="iFrameHeight('details','iframeDiv','400')"></iframe>
</div>
<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>