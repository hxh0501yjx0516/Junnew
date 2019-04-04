<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>北京中润无线广告有限公司</title>
<!-- <link href="html/images/jnico.ico" rel="SHORTCUT ICON"> -->
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/jquery.blockUI.js"></script>
<script type="text/javascript">
/**查看动态和公告**/
$(function(){
	$(".click").bind("click",function(){
		var name = $(this).attr("myvalue");
        $.blockUI({
            message: $("#content"+name), 
            css: { 
		        top:		'50%',
		        left:		'50%',
		        textAlign:	'left',
		        marginLeft:     '-320px', 
		        marginTop:      '-145px', 
                width: '600px',
                background:'none'
            } 
        }); 
		$('.blockOverlay').attr('title','单击关闭').click($.unblockUI); 
	 });
	});
</script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<div id="main">
	<!--flash begin-->
	<div id="flash">
		<%@ include file="/html/include/flv.jsp" %> 
    </div>
	<!--flash end-->
    
    <!--用户登录 begin-->
    <div id="users">
    	<%@ include file="/html/include/login.jsp" %>
    </div>
    <!--用户登录 end-->
    
    <!--动态 begin-->
    <div id="news">
    	<div class="top"><img src="html/images/newtop.jpg" width="230" height="60"></div>
        <div class="middle">
        	<ul>
        	  <c:forEach var="trend" items="${trendList}" varStatus="s">
        	  <c:if test="${s.count <= 7}">
            <li class="lil">
            <a class="click" href="javascript:void(0);" title="${trend.cmsName}" myvalue="${trend.cmsId}">
             <c:choose> 
		     <c:when test="${fn:length(trend.cmsName)>9 }">
		                    ${fn:substring(trend.cmsName, 0, 9)}
		       </c:when>
		      <c:otherwise>
		                  ${trend.cmsName}
		      </c:otherwise>
		    </c:choose>
            </a>
            </li>
            <li class="lir">
            	${fn:substring(trend.addTime,0,10)}
    		</li>
    		<div id="content${trend.cmsId}" class="box"> 
			<h3>${trend.cmsName}</h3> 
			${trend.cmsContent}
			</div> 
    		</c:if>
            </c:forEach>
            <div class="clear"></div>
            </ul>
        </div>
        <div class="bottom"><img src="html/images/newb.jpg" width="230" height="6"></div>
    </div>
	<!--动态 end-->
    
    <!--公告 begin-->
	<div id="news_2">
    	<div class="top"><img src="html/images/newtop2.jpg" width="234" height="60"></div>
        <div class="middle">
        	<ul>
         	 <c:forEach var="notice" items="${noticeList}" varStatus="s">
        	  <c:if test="${s.count <=7}">
            <li class="lil">
            <a class="click" href="javascript:void(0);" title="${notice.cmsName}" myvalue="${notice.cmsId}">
             <c:choose> 
		     <c:when test="${fn:length(notice.cmsName)>9 }">
		                    ${fn:substring(notice.cmsName, 0, 9)}
		       </c:when>
		      <c:otherwise>
		                  ${notice.cmsName}
		      </c:otherwise>
		    </c:choose>
            </a>
            </li>
            <li class="lir">
            ${fn:substring(notice.addTime, 0, 10)}
    		</li>
    		<div id="content${notice.cmsId}"  class="box"> 
			<h3>${notice.cmsName}</h3> 
			${notice.cmsContent}
			</div> 
    		</c:if>
            </c:forEach>
            <div class="clear"></div>
            </ul>
        </div>
        <div class="bottom"><img src="html/images/newb.jpg" width="230" height="6"></div>
    </div>
    <!--公告 end-->
    
    <!--客服 begin-->
    <div id="service">
   	<%@ include file="/html/include/service.jsp" %>
   	</div>
    <!--客服 end-->
    
    <div id="friend">
    	<div class="top">
        	<div id="a1" class="s1" onclick="changeStyle('a1'),changeTAB(1,2,'c','friendcon show','friendcon hide')"></div>
            <div id="a2" class="s2" onclick="changeStyle('a2'),changeTAB(2,2,'c','friendcon show','friendcon hide')"></div>
        </div>
        <div class="middle">
            <div id="c1" class="friendcon show">
			<%--<div class="scroll_one">  
			    <ul style="list-style-type: none;overflow: hidden;">  
			         <c:forEach var="adPlan" items="${adPlanList}" varStatus="s">
							<li><img src="${serverAddress}${adPlan.adPlanImg}" width="200" height="80"></li> 
						 </c:forEach>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
						 <li><img src="images/fl.jpg" width="200" height="80"></li>
			    </ul>  
			</div> --%> 
	<div id="demo7" style="padding:0px 3px; width:930px; overflow:hidden;">
    <table width="100%" border="0" cellpadding="0" cellspacing="5">
        <tr>
             <td id="demo8">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
     	 <c:forEach var="adPlan" items="${adPlanList}" varStatus="s">
        <td align="center" style=" padding-top:10px"><img src="${serverAddress}${adPlan.cmsImg}" width="200" height="80"></td> 
      	</c:forEach>
      </tr>
    </table>
                    </td>
                    <td id="demo9"></td>
                  </tr>
                </table>
                </div>
            </div>
            <div id="c2" class="friendcon hide">
          <div id="demo1" style="padding:0px 3px; width:930px; overflow:hidden;">
    <table width="100%" border="0" cellpadding="0" cellspacing="5">
        <tr>
             <td id="demo2">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
      <c:forEach var="cases" items="${caseList}" varStatus="s">
        <td align="center" style=" padding-top:10px"><img src="${serverAddress}${cases.cmsImg}" width="200" height="80"></td> 
      	</c:forEach>
      </tr>
    </table>
                    </td>
                    <td id="demo3"></td>
                  </tr>
                </table>
                </div>
            </div>
        </div>
        <div class="bottom"><img src="html/images/fb.jpg" width="960" height="15"></div>
    </div>

</div>

<%@ include file="/html/include/bottom.jsp" %>
</body>
<script type="text/javascript">
function Marquee(d7,d8,d9){
if(d9.offsetWidth-d7.scrollLeft<=0)
d7.scrollLeft-=d8.offsetWidth
else{
d7.scrollLeft++
}
}
function scrollJs(d7,d8,d9,sp){
var speed=sp||50;
d9.innerHTML = d8.innerHTML
var MyMar=setInterval(function(){
	Marquee(d7,d8,d9);
},speed)
d7.onmouseover=function() {clearInterval(MyMar)}
d7.onmouseout=function() {MyMar=setInterval(function(){
	Marquee(d7,d8,d9);
},speed)}
}
scrollJs(demo7,demo8,demo9,30);
scrollJs(demo1,demo2,demo3,30);
</script>
</html>