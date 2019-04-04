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
<title>首信联盟 - 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/comment.js"></script > 
<script type="text/javascript">
function jumpCustomer(target,parms){
     var url = "customer.do?action="+target;
	 if(parms){
		 url +="&adPlanId="+parms;
	 }
	document.adPlanForm.action = url;
	document.adPlanForm.submit();
}
</script>
</head>
<body style="background: ">
    <!--内容 begin-->
     <div id="contitle">广告计划</div>
            <div id="context">
    <form name="adPlanForm" method="post" action="customer.do?action=adplan" >
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr class="trbg fweight">
          <td height="30" align="center">计划ID</td>
          <td align="center">计划名称</td>
          <td align="center">操作</td>
        </tr>
        <c:forEach items="${adPlanList}" var="adPlan" varStatus="s">
        	<c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
          <td height="22" align="center">${s.count}</td>
          <td align="center">${adPlan.adPlanName} </td>
          <td align="center"><a href="javascript:void(0);" onclick="parent.changeLeftTab('b3');jumpCustomer('adplancycle','${adPlan.adPlanId}');return false;">查看排期</a></td>
        </tr>
        </c:forEach>
         <tr class="">
          <td colspan="5" style="height: 40px;"></td>
        </tr>
      </table> 
      </form>
      </div>
    <!--内容 end-->
     <div id="commentPage" name="commentPage" class="page"></div>
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "customer.do";
		pg.args = "?action=adplan&state=${state}";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.adPlanForm.action = pg.url+pg.args;
		document.adPlanForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>