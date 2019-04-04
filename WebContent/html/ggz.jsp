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
<link href="${pageContext.request.contextPath }/html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/html/js/main.js"></script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<div id="main">
	<!--flash begin-->
	<div id="flash">
    	<%@ include file="/html/include/flv.jsp" %> 
    </div>
	<!--flash end-->
  	<div class="main_left">
	<!--广告主介绍 begin-->
    	<div id="info" >
        	<div class="top"><img src="${pageContext.request.contextPath }/html/images/ggzt.jpg" width="716" height="51"></div>
            <div class="middle" style="height: 460px;">
            <img src="${pageContext.request.contextPath }/html/images/ggzlc.jpg"><br />
			北京中润无线广告有限公司是为广告主和网站主提供公平公正、诚信共赢的第三方交易平台。广告主可根据自身需求选择 不同的广告形式进行投放，获得高性价比的广告效果。 
			<br />1、项目洽谈：通过电话或QQ联系联盟市场部门，展开合作洽谈。 
			<br />2、方案确认：根据客户需求和产品特性结合联盟资源优势沟通出具体方案。
			<br />3、签订合同：具体方案确认后，签订广告投放合同书。
			<br />4、支付款项：根据合同签订情况支付广告费用。 
			<br />5、开放后台：注册并开通广告主后台。
			<br />6、广告创意：根据需求方案制作广告素材。
			<br />7、广告投放：广告的测试与正式发布。
			<br />8、效果优化：根据后台反馈数据对广告进行调整和提高。
			
 </div>
            <div class="bottom"><img src="${pageContext.request.contextPath }/html/images/infob.jpg" width="716" height="9"></div>
        </div>
	<!--广告主介绍 end-->
    
    <!--合作案例 begin-->
    <div id="anli">
    	<div class="top"><img src="${pageContext.request.contextPath }/html/images/ggzalt.jpg" width="716" height="51"></div>
        <div class="middle">
        	<img src="${pageContext.request.contextPath }/html/images/taobao.jpg" width="200" height="150">
        	<img src="${pageContext.request.contextPath }/html/images/yahoo.jpg" width="200" height="150">
        	<img src="${pageContext.request.contextPath }/html/images/shengda.jpg" width="200" height="150">
        </div>
        <div class="bottom"><img src="${pageContext.request.contextPath }/html/images/infob.jpg" width="716" height="9"></div>
    </div>
    <!--合作案例 end-->
    </div>
     <div class="main_right">
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
    <div class="clear"></div>
</div>

<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>