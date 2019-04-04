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
      <form id="myPlanForm" name="myPlanForm" action="adplan.do?action=myadplan" method="post">
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr class="th fweight">
          <td width="29%" height="30" align="center">广告计划</td>
          <td width="39%" align="center">状态</td>
          <td align="center">添加时间</td>
        </tr>
        <c:forEach var="readyPlan" items="${readyPlanList}" varStatus="s">
        	<c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
          <td height="22" align="center">${readyPlan.adPlanName}</td>
          <td align="center">
			<c:choose>
				<c:when test="${readyPlan.readyPlanStatus == 0}">
					<span style='color:blue;'>审核通过</span>
				</c:when>
				<c:otherwise>
					<span style='color:orange;'>待审核</span>
				</c:otherwise>
			</c:choose>
		</td>
          <td align="center">
		  <fmt:parseDate value="${readyPlan.addTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		  <fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
		</td>
        </tr>
        </c:forEach>
         <tr class="">
          <td colspan="5" style="height: 40px;"></td>
        </tr>
      </table>
      </form>
    <!--内容 end-->
         <div id="commentPage" name="commentPage" class="page"></div>
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "adplan.do";
		pg.args = "?action=myadplan";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.myPlanForm.action = pg.url+pg.args;
		document.myPlanForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>