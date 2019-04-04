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
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function submitPay(target){
		 document.payForm.action="webmaster.do?action="+target;
		 document.payForm.submit();
	}
function viewDetails(payId){
	
}
</script>
</head>

<body>
    <!--内容 begin-->
      <form action="webmaster.do?action=balance" method="post" name="payForm" id="payForm">
     <table width="100%" border="0" cellpadding="2" class="table">
        <tr>
          <td height="40" colspan="7" align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;
           广告计划：
            <select name="adPlanId">
              <option value="">按计划筛选</option>
              <c:forEach items="${readyPlanList}" var="readyPlan">
              <option value="${readyPlan.adPlanId}" ${readyPlan.adPlanId == adPlanId?"selected":""}>${readyPlan.adPlanName}</option>
              </c:forEach>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
           	结算日期：
            <input type="text" id="beginTime" name="beginTime" size="8" onfocus="WdatePicker({skin:'default',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" readonly="readonly" value="${beginTime}"/>
            &nbsp;-&nbsp;
            <input type="text" id="endTime" name="endTime" size="8" onfocus="WdatePicker({skin:'default',minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" readonly="readonly" value="${endTime}"/>
            &nbsp;&nbsp;
            <input class="button_btn" type="button" value="检索" onclick="submitPay('balance');"/>
            <input class="button" type="button" value="导出报表" onclick="submitPay('excel');"/>
          </td>
        </tr>
        <tr class="th fweight">
        <td width="10%" align="center">编号</td>
          <td width="18%" height="30" align="center">广告计划</td>
          <td width="10%" align="center">结算数</td>
          <td width="10%" align="center">结算佣金</td>
          <td width="21%" align="center">结算周期</td>
          <td width="16%" align="center">结算时间</td>
          <td width="10%" align="center">操作</td>
        </tr>
       <c:forEach var="pay" items="${payList}" varStatus="s">
        	<c:choose>
        		<c:when test="${s.count%2 != 0}">
        			<tr class="trbg2">
        		</c:when>
        		<c:otherwise>
        		 <tr class="trbg1">
        		</c:otherwise>
        	</c:choose>
          <td height="22" align="center">${s.count}</td>
          <td height="22" align="center">${pay.adPlanName}</td>
          <td height="22" align="center"><fmt:formatNumber value="${pay.realCount}" pattern="#,###.###"/></td>
          <td height="22" align="center">￥<fmt:formatNumber value="${pay.trueMoney}" pattern="#,###.###"/></td>
          <td height="22" align="center">
          <fmt:parseDate value="${pay.payBeginTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		   <fmt:parseDate value="${pay.payBeginTime}" var="date1" pattern="yyyy-MM-dd HH:mm:ss"/>
		  <fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />~
		  <fmt:formatDate value="${date1}" type="date" pattern="yyyy-MM-dd" />
          </td>
          <td height="22" align="center">
          <fmt:parseDate value="${pay.payAddTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
		  <fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
          </td>
           <td height="22" align="center"><a href="javascript:void(0);" onclick="parent.showDiv('${pay.payId}');return false;">查看详情</a></td>
        </tr>
        </c:forEach>
         <tr class="">
          <td colspan="7" style="height: 40px;"></td>
        </tr>
      </table>
         </form>
    <!--内容 end-->
         <div id="commentPage" name="commentPage"  class="page"></div>
</body>
 <script type="text/javascript">
		var pg = new showPages('pg');
		pg.pageCount = ${pageCount}; // 定义总页数(必要)
		pg.showDiv = document.getElementById("commentPage");
		pg.url = "webmaster.do";
		pg.args = "?action=balance";
		pg.argName="pageNum";
		pg.formSubmit = function(){
		document.payForm.action = pg.url+pg.args;
		document.payForm.submit();
		}
		pg.printHtml(2);
    </script>
</html>