<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>北京中润无线广告有限公司</title>
<%-- <link href="${pageContext.request.contextPath}/html/images/jnico.ico" rel="SHORTCUT ICON"> --%>
<link href="${pageContext.request.contextPath}/html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/html/js/main.js"></script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<div id="main">
	<div id="flash">
    	<%@ include file="/html/include/flv.jsp" %> 
    </div>
	<!--广告模式介绍 begin-->
    <div id="model">
    	<div class="top"><img src="${pageContext.request.contextPath }/html/images/ggmst.jpg" width="960" height="51"></div>
        <div class="middle">
         <ul >
        	<li>
        	<h3>1.图片广告</h3><br/>
       		<img src="${pageContext.request.contextPath }/html/images/hengfu.gif" alt="" /><br/>
			广告形式：在投放页面以图片方式展现，包括横幅，通栏等。<br/>
			记费方式：按CPC(点击付费)或CPA(有效注册)<br/>
			图片格式：Jpg，Gif，Flash<br/>
			规格大小：468*60,658*60,728*90,760*90,950*90,250*60,300*250,250*250,200*200,180*250,250*300,336*280,120*240,120*60等十几种规格<br/>
			广告效果：目前最常用最有效的广告方式之一，浏览不收费，效果极佳 <br/>
 			</li>
 			<li>
        	<h3>2.对联广告</h3><br/>
        	<div style="float: left;">
       		<img src="${pageContext.request.contextPath }/html/images/duilian.gif" alt="" />
       		</div>
       		<div style="float: left;width:500px;border: 0px solid red;padding:0px 60px;margin: 0 auto;">
       		<br/>
       		广告形式：在网站的页面投放，以飘浮在网页两边展现。<br/>
			记费方式：按CPC(点击付费)或CPA(有效注册)<br/>
			图片格式：Jpg，Gif，Flash<br/>
			规格大小：90*240等几种规格<br/>
			广告效果：目前经常用的广告方式之一，浏览不收费，效果极佳<br/>
			</div>
			<div style="float: left;">
			<img src="${pageContext.request.contextPath }/html/images/duilian.gif" alt="" />
			</div>
 			</li>
 			<li>
        	<h3>3.漂浮广告</h3><br/>
        	<div style="float: left;">
       		<img src="${pageContext.request.contextPath }/html/images/piaofu.gif" alt="" />
       		</div>
       		<div style="float: left;padding:0px 30px;display: inline; text-indent:0px;">
			广告形式：在网站的页面投放，以飘浮在网页右下角展现。<br/>
			记费方式：按CPC(点击付费)或CPA(有效注册)<br/>
			图片格式：Jpg，Gif，Flash<br/>
			规格大小：256*159<br/>
			广告效果：飘浮广告是目前最常用的广告方式，保证每个浏览者均可浏览此广告，效果极佳 <br/>
			</div>
 			</li>
 			<li>
        	<h3>4.弹窗广告</h3><br/>
       		<img src="${pageContext.request.contextPath }/html/images/tanchuang.jpg" alt="" /><br/>
			广告形式：在网站的页面投放，当打开该页面的同时弹出客户的网页。<br/>
			记费方式：按CPM,IP弹窗数计算<br/>
			广告格式：：网页Jpg，Gif，Flash<br/>
			规格大小：730*500<br/>
			广告效果：纯弹或显弹广告是网络最经典的广告方式；是目前提升流量、打造知名度的最好方式<br/>
 			</li>
 			</ul>
 </div>
        <div class="bottom"><img src="${pageContext.request.contextPath }/html/images/ggmsb.jpg" width="960" height="9"></div>
    </div>
	<!--广告模式介绍 end-->
    
  <div class="clear"></div>
</div>

<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>