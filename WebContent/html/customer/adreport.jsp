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
<script type="text/javascript" src="html/js/comment.js"></script>
<script language="javascript"  type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"> 
function reportExcel(){
		 document.reportForm.action="customer.do?action=reportExcel";
		 document.reportForm.submit();
	}
</script>
</head>
<body >
    
    <!--内容 begin-->
         <div id="contitle">数据报表</div>
            <div id="context">
     <form name="reportForm" method="post" action="customer.do?action=report" >
      <table width="100%" border="0" cellpadding="2" class="table">
        <tr>
          <td style="border: 0px solid red;" colspan="8" align="left">
          广告计划：
          <select name="adPlanId" style="width: 100px;">
           <option value="">按计划筛选</option>
          <c:forEach items="${adPlanList}" var="adPlan" >
          <option value="${adPlan.adPlanId}" ${adPlanId == adPlan.adPlanId?"selected":""}>${adPlan.adPlanName}</option>
          </c:forEach>
         
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          广告排期：
          <select name="adPlanCycleId" style="width: 100px;">
          <option value="">按排期筛选</option>
          <c:forEach items="${cycleList}" var="adPlanCycle" >
          <option value="${adPlanCycle.adPlanCycleId}" ${adPlanCycleId == adPlanCycle.adPlanCycleId?"selected":""}>${adPlanCycle.adPlanCycleName}</option>
          </c:forEach>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;
          日期：
          <input value="${beginTime}" type="text" id="beginTime" name="beginTime" size="8"  onfocus="WdatePicker({skin:'default',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" readonly="readonly">&nbsp;-&nbsp;
          <input value="${endTime}" type="text" id="endTime" name="endTime" size="8" onfocus="WdatePicker({skin:'default',minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" readonly="readonly">&nbsp;&nbsp;
          <input type="submit" class="button_btn" value="检索">
          <input type="button" class="button_btn" onclick="reportExcel()" value="导出">
          </td>
        </tr>
        <tr class="trbg fweight">
          <td width="6%" height="30" align="center">ID</td>
          <td width="25%" height="30" align="center">广告计划</td>
          <td width="20%" align="center">广告排期</td>
          <td width="36%" align="center">${customer.customerId eq 126 ? "展示" : "点击数"}</td>
          <td width="19%" align="center">日期</td>
        </tr>
        <c:forEach items="${adPlanCycleList}" var="adPlanCycle" varStatus="s">
        	<c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
        	 <td height="22" align="center"  >${s.count} </td>
          <td height="22" align="center"  >${adPlanCycle.adPlanName} </td>
          <td align="center"  >${adPlanCycle.adPlanCycleName}</td>
          <td align="center"><fmt:formatNumber  value="${adPlanCycle.realFlowList}" pattern="#,###.###"/></td>
          <td align="center"> 
          <fmt:parseDate value="${adPlanCycle.currentTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		  <fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
		  </td>
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
		pg.args = "?action=report";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.reportForm.action = pg.url+pg.args;
		document.reportForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>