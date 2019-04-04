<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div id="top">
	<div class="logo">
    	<a href="${path}/index.jsp"><img src="${path}/html/images/logo_red.png" border="0" /></a>
    </div>
    <div class="nav">
    	<a href="${path}/index.jsp" class="first"><img src="${path}/html/images/sy.jpg" width="51" height="15" border="0" /></a>
        <a href="${path}/transfer.do?action=ggz"><img src="${path}/html/images/ggz.jpg" width="51" height="15" border="0" /></a>
        <a href="${path}/transfer.do?action=wzz"><img src="${path}/html/images/wzz.jpg" width="51" height="15" border="0" /></a>
        <a href="${path}/transfer.do?action=ggms"><img src="${path}/html/images/ggms.jpg" width="51" height="15" border="0" /></a>
        <a href="${path}/transfer.do?action=bzzx"><img src="${path}/html/images/bzzx.jpg" width="51" height="15" border="0" /></a>
        <a href="${path}/transfer.do?action=cus"><img src="${path}/html/images/lxwm.jpg" width="51" height="15" border="0" /></a>
    </div>
</div>