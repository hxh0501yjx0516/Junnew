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
<title>骏易联盟 - 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript">
 function jumpCustomer(target,parms){
	 var url = "customer.do?action="+target;
	 if(parms){
		 url += "&state="+parms; 
	 }
	document.myHomeForm.action = url;
	document.myHomeForm.submit();
}
</script>
</head>
<body style="background: ">
<!--information begin-->
 <div id="contitle">我的首页</div>
            <div id="context">
	<form name="myHomeForm" method="post" action="customer.do?action=myhome" >
		<table width="100%" border="0" cellpadding="2" class="table">
          <tr>
            <td height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;今日统计：</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30" colspan="3">
            	<table width="100%" border="0" cellpadding="2">
                    <tr class="trbg fweight">
                      <td width="25%" height="30" align="center">广告计划</td>
                      <td width="20%" align="center">广告排期</td>
                      <td width="36%" align="center">点击数</td>
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
                      <td height="22" align="center">${adPlanCycle.adPlanName}</td>
                      <td align="center">${adPlanCycle.adPlanCycleName}</td>
                      <td align="center"><fmt:formatNumber value="${adPlanCycle.realFlowList}" pattern="#,###.###"/></td>
                      <td align="center">
                      	<fmt:parseDate value="${adPlanCycle.currentTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${date}" type="date" pattern="yyyy-MM-dd" />
                      </td>
                    </tr>
                    </c:forEach>
                </table>
		</td>
          </tr>
          <tr>
            <td width="11%" height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;计划状态：</td>
            <td width="56%">&nbsp;</td>
            <td width="33%">&nbsp;</td>
      </tr>
          <tr>
          <td width="11%">&nbsp;</td>
            <td height="30" colspan="2" align="left">
            所有计划&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b2');jumpCustomer('adplan');return false;" class="fcyellow">${adPlanSum}</a>&nbsp;个&nbsp;，&nbsp;&nbsp;&nbsp;&nbsp;
            投放中&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b2');jumpCustomer('adplan','0');return false;" class="fcyellow">${adPlanRun}</a>&nbsp;个，&nbsp;&nbsp;&nbsp;&nbsp;
            已停止&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b2');jumpCustomer('adplan','1');return false;" class="fcyellow">${adPlanOver}</a>&nbsp;个
            </td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;排期状态：</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
          <td width="11%">&nbsp;</td>
            <td height="30" colspan="2" align="left">
            所有排期&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b3');jumpCustomer('adplancycle');return false;"  class="fcyellow">${cycleSum}</span></a>&nbsp;个&nbsp;，&nbsp;&nbsp;&nbsp;&nbsp;
            投放中&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b3');jumpCustomer('adplancycle','0');return false;" class="fcyellow">${cycleRun}</span></a>&nbsp;个，&nbsp;&nbsp;&nbsp;&nbsp;
            已停止&nbsp;<a style="color: orange;" href="javascript:void(0);" onclick="parent.changeLeftTab('b3');jumpCustomer('adplancycle','1');return false;" class="fcyellow">${cycleOver}</span></a>&nbsp;个
            </td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
    </table>
    </form>
    </div>
    </body>
    </html>