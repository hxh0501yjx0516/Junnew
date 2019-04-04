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
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/main.js"></script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<div id="main">
	<!--flash begin-->
	<div id="flash">
    	<%@ include file="/html/include/flv.jsp" %> 
    </div>
	<!--flash end-->
    
	<!--help begin-->
	<div id="cus">
    	<div class="top"><img src="${pageContext.request.contextPath}/html/images/ct1.jpg" width="716" height="51"></div>
        <div class="middle" >
        <ul >
        	<li>
        	<h3>北京中润无线广告有限公司</h3><br />	
			<span>地址：北京市朝阳区光华路22号 光华soho 2单元17层</span><br />
			<span>邮编：100025</span><br />
			<span>手机：13146856806</span><br />
			<span>电话：+86 010-85865545</span><br />
			<span>传真：+86 010-85863575</span><br />
			<span>E-mail：sujian@pancou.com</span><br />
        	</li>
        </ul>
          </div>
        <div class="bottom"><img src="${pageContext.request.contextPath}/html/images/infob.jpg" width="716" height="9"></div>
    </div>
    <!--help end-->
    
    <!--用户登录 begin-->
    <div id="users">
    	<%@ include file="/html/include/login.jsp" %>
    </div>
    <!--用户登录 end-->
     <!--客服 begin-->
  <div id="service" style=" margin-top:-5px;margin-left: 0px;">
   	<%@ include file="/html/include/service.jsp" %>
    </div>
    <!--客服 end-->
    
    
</div>

<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>