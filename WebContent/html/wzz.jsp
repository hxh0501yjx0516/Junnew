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
	<!--网站主介绍 begin-->
    	<div id="info">
        	<div class="top"><img src="${pageContext.request.contextPath }/html/images/wzzt.jpg" width="716" height="51"></div>
            <div class="middle">
			<h3>什么是联盟会员</h3>

			联盟会员即网站主<br />
			如果你有一个或多个网站，想通过流量就可以赚钱，那就马上注册骏易会员吧！
			网站主可以免费注册成为联盟会员。通过在自已网站上放置一定的网络广告，通过该网站上的客户浏览到广告主的页面，从而为其创造收益。
			
			
			 <h3>如何成为骏易联盟会员</h3>
			
			<img src="${pageContext.request.contextPath}/html/images/wzzlc.jpg" alt="" />
			<br />1. 注册成为联盟会员<br />
			凡申请加入骏易联盟会员的网站，需要确认网站的真伪及经营权是否属实等。一般加入联盟就可以成为联盟会员，也就可以选择广告进行放置处理了。对于审查不合格的网站，骏易联盟有权拒绝该网站的申请。
			
			<br />2. 选择广告等待审核<br />
			选择合适的广告，联盟会员申请投放其广告，等待媒介审核，获得媒介审核资格的联盟会员就可以在自己的网站中放置其广告了。 
			
			
			
			<br />3. 获取代码挂上广告<br />
			在经过核对的广告样例中选择适合自己网站的广告样式及链接代码，然后在联盟会员自己网站页面的适当位置插入该代码即可。
			
			
			
			<br />4. 每期结算获取佣金<br />
			所谓业绩，就是联盟会员在放置广告主广告的时候所产生的效果，是按照广告主事先约定好的。如广告点击、广告主网站注册等行为的统计效果。如果网络用户发生以上行为，骏易联盟将按广告主返回的有效数据，进行结算。
			在一定时间周期内，按照协议约定的结算办法，骏易联盟将把通过该联盟会员产生的收入汇款到该联盟会员的帐号上。
			因此，成为联盟会员后，你不需要花一分钱，就可以拥有以联盟为基础的广告主。
							
				 </div>
            <div class="bottom"><img src="${pageContext.request.contextPath }/html/images/infob.jpg" width="716" height="9"></div>
        </div>
       
	<!--广告主介绍 end-->
    
    <!--合作案例 begin-->
    <div id="anli">
    	<div class="top"><img src="${pageContext.request.contextPath }/html/images/ggzalt.jpg" width="716" height="51"></div>
        <div class="middle">
        	<img src="${pageContext.request.contextPath }/html/images/qiangguo.jpg" width="200" height="150">
        	<img src="${pageContext.request.contextPath }/html/images/youxun.jpg" width="200" height="150">
        	<img src="${pageContext.request.contextPath }/html/images/youxun.jpg" width="200" height="150">
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