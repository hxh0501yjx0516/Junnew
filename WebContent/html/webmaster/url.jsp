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
<script type="text/javascript">
	function addUrlSubmit(){
		document.urlForm.action = "webmaster.do?action=addUrladdress";
		document.urlForm.submit();
	}
</script>
</head>
<body>

    <!--内容 begin-->
     <form id="urlForm" name="urlForm" action="webmaster.do?action=urladdress" method="post">
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr class="th fweight">
          <td width="12%" height="30" align="center">网站名称</td>
          <td width="18%" align="center">域名</td>
          <td width="10%" align="center">日IP</td>
          <td width="9%" align="center"><p>Aleax</p></td>
          <td width="12%" align="center">网站类型</td>
          <td width="11%" align="center">所属地区</td>
          <td width="13%" align="center">广告位数量</td>
          <td width="15%" align="center">状态</td>
        </tr>
        <c:forEach var="urlAddress" items="${urlList}" varStatus="s">
        	 	<c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
          <td height="22" align="center">${urlAddress.urlName}</td>
          <td align="center">${urlAddress.url}</td>
          <td align="center">
          <fmt:formatNumber  value="${urlAddress.urlDayIp}" pattern="#,###.###"/>
          </td>
          <td align="center">
           <fmt:formatNumber  value="${urlAddress.urlAlexa}" pattern="#,###.###"/>
          </td>
          <td align="center">${urlAddress.urlTypeName}</td>
          <td align="center">${urlAddress.urlArea}</td>
          <td align="center">
     		<fmt:formatNumber  value="${urlAddress.adBoxCount}" pattern="#,###.###"/> 个</td>
          <td align="center">
          <c:choose>
          	<c:when test="${urlAddress.urlStatus == 0}">
          		<span style="color: orange">待审核</span>
          	</c:when>
          	<c:when test="${urlAddress.urlStatus == 1}">
          		<span style="color: blue">审核通过</span>
          	</c:when>
          	<c:when test="${urlAddress.urlStatus == 2}">
          		<span style="color: red">锁定</span>
          	</c:when>
          </c:choose>
          </td>
        </tr>
        </c:forEach>
         <tr class="">
          <td colspan="5" style="height: 40px;"></td>
        </tr>
      </table>
      
    <!--内容 end-->
     <div id="addUrlDiv" name="addUrl" style="border: 0px solid blue;left: 10px;bottom: 10px;width:100px;text-align: center;position: absolute;">
     <input type="button" class="button" onclick="addUrlSubmit();" id="addUrl" name="addUrl" value="添加域名"/>
     </div>
     </form>
     <div id="commentPage" name="commentPage" class="page" style="border: 0px solid red;"></div>
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "webmaster.do";
		pg.args = "?action=urladdress";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.urlForm.action = pg.url+pg.args;
		document.urlForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>