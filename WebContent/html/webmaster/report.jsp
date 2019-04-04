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
    <form name="reportForm" method="post" action="report.do?action=daily" >
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr class="th fweight">
          <td width="8%" height="30" align="center">编号</td>
          <td width="20%" align="center">创意</td>
          <td width="14%" align="center">所属计划</td>
          <td width="15%" align="center">域名</td>
          <c:choose>
          	<c:when test="${sessionScope.webmaster.webMasterId == 43}">
          	  <td width="10%" align="center">点击数(PV)</td>
	          <td width="10%" align="center">点击数(UV)</td>
	          <td width="10%" align="center">点击数(IP)</td>
          	</c:when>
          	<c:otherwise>
          		 <td width="18%" align="center">点击数</td>
          	</c:otherwise>
          </c:choose>
         
          <td width="18%" align="center">日期</td>
        </tr>
        <c:forEach items="${adBoxCountList}" var="list" varStatus="v">
          <c:choose>
          <c:when test="${v.count%2 == 0}">
          <tr class="trbg2">
          </c:when>
          <c:otherwise>
          	<tr class="trbg1">
          </c:otherwise>
          </c:choose>
        
        		
          <td height="22" align="center">${list.readyBoxId}</td>
          <td height="22" align="center">${list.adCreativeName}</td>
          <td height="22" align="center">${list.adPlanName }</td>
          <td height="22" align="center">${list.url }</td>
          <c:choose>
          	<c:when test="${sessionScope.webmaster.webMasterId == 43}">
          	   <td height="22" align="center">
          	   <fmt:formatNumber value="${list.pv}" pattern="#,###.###"/>
          	  </td>
          	    <td height="22" align="center">
          	    <fmt:formatNumber value="${list.uv}" pattern="#,###.###"/>
          	    </td>
          	     <td height="22" align="center">
          	     <fmt:formatNumber value="${list.ip}" pattern="#,###.###"/>
          	     </td>
          	</c:when>
          	<c:otherwise>
          		 <td height="22" align="center">
          		 <fmt:formatNumber value="${list.viewCols}" pattern="#,###.###"/>
          		 </td>
          	</c:otherwise>
          </c:choose>
          <td height="22" align="center">
          <fmt:parseDate value="${list.adBoxCountTime }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		  <fmt:formatDate value="${date}" type="date"  pattern="yyyy-MM-dd"/>
          </td>
        </tr>
		</c:forEach>
		<tr class="">
          <td colspan="${sessionScope.webmaster.webMasterId == 43?'8':'6'}" style="height: 40px;"></td>
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
		pg.url = "report.do";
		pg.args = "?action=daily";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.reportForm.action = pg.url+pg.args;
		document.reportForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>