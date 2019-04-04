<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>骏易传媒 - 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/comment.js"></script>
</head>
<body>
    <!--内容 begin-->
     <form name="adplanForm" method="post" action="adplan.do?action=list" >
    <div class="adplanmain">
    <c:forEach items="${adPlanList}" var="adPlan" varStatus="s">
    	<div class="main">
    		<div class="img" ><span ><img src="${adPlan.adPlanImg}" width="120" height="80"></span></div>
    	<div class="details" >
    		<ul>
    			<li>名称：${adPlan.adPlanName}</li>
    			<li>广告主：${adPlan.customerName}</li>
    			<li>发布日期：
    			 <fmt:parseDate value="${adPlan.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		  		<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
    			</li>
    			<li><a href="">申请此计划</a></li>
    		</ul>
    	</div>
    	</div>
    </c:forEach>
    	</div>
    	 <div id="commentPage" name="commentPage" class="page"></div>
	</div>
    </form>
    <!--内容 end-->
 
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "adplan.do";
		pg.args = "?action=list";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.adplanForm.action = pg.url+pg.args;
		document.adplanForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>