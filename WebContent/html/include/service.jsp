<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
    	<div class="top"><img src="${path}/html/images/servicetop.jpg" width="230" height="60"></div>
        <div class="middle">
       	  <table width="95%" border="0">
        	  <tr>
        	    <td height="5"></td>
        	    <td></td>
      	    </tr>
        	  <tr>
        	    <td width="46%" height="30" align="right"><img src="${path}/html/images/s1.gif" width="83" height="15"></td>
        	    <td width="54%" align="left">&nbsp;</td>
      	    </tr>
      	     <tr>
      	     <c:forEach var="service" items="${serviceList}">
        		<c:if test="${service.typeId == 1}">
        	    <td height="30"  align="right">
            	<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${service.qq}&site=qq&menu=yes">
            	<img border="0" src="http://wpa.qq.com/pa?p=2:${service.qq}:41" alt="与我交谈" title="与我交谈">
            	</a>
            	</td>
            	</c:if>
            </c:forEach>
        	 </tr>
        	  <tr>
        	    <td height="30" align="right"><img src="${path}/html/images/s2.gif" width="83" height="15"></td>
        	    <td align="left">&nbsp;</td>
      	    </tr>
        	  <tr>
      	     <c:forEach var="service" items="${serviceList}">
        		<c:if test="${service.typeId == 0}">
        	    <td height="30"  align="right">
            	<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${service.qq}&site=qq&menu=yes">
            	<img border="0" src="http://wpa.qq.com/pa?p=2:${service.qq}:41" alt="与我交谈" title="与我交谈">
            	</a>
            	</td>
            	</c:if>
            </c:forEach>
        	 </tr>
      	  </table>
</div>
        <div class="bottom"><img src="${path}/html/images/newb.jpg" width="230" height="6"></div>
