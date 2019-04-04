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
      <form name="readyBoxForm" method="post" action="adplan.do?action=readybox" >
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr class="th fweight">
          <td width="6%" height="30" align="center">编号</td>
          <td width="13%" align="center">创意</td>
          <td width="8%" align="center">广告位</td>
          <td width="10%" align="center">域名</td>
          <td width="21%" align="center">投放周期</td>
          <td width="15%" align="center">所属计划</td>
          <td width="14%" align="center">计费类型</td>
          <td width="13%" align="center">状态</td>
        </tr>
         <c:forEach var="readyBox" items="${readyBoxList}" varStatus="s">
        <c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
          <td height="22" align="center">${s.count}</td>
          <td height="22" align="center">${readyBox.adCreativeName}</td>
          <td height="22" align="center">${readyBox.adBoxName}</td>
          <td height="22" align="center">${readyBox.urlName}</td>
          <td height="22" align="center">${readyBox.adPlanCycleName}</td>
          <td height="22" align="center">${readyBox.adPlanName}</td>
          <td height="22" align="center">${readyBox.payTypeName}</td>
          <td height="22" align="center">
          	<c:choose>
          		<c:when test="${readyBox.readyBoxStatus == 0}">
          			<span style="color: blue;">正常</span>
          		</c:when>
          		<c:when test="${readyBox.readyBoxStatus == 1}">
          			<span style="color: orange;">锁定</span>
          		</c:when>
          		<c:when test="${readyBox.readyBoxStatus == 2}">
          			<span style="color: red;">废弃</span>
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
    <div id="commentPage" name="commentPage" class="page"></div>
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "adplan.do";
		pg.args = "?action=readybox";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.readyBoxForm.action = pg.url+pg.args;
		document.readyBoxForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>