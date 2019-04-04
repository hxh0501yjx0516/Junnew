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
    <form id="stepForm" name="stepForm" action="code.do?action=step2" method="post">
    <table width="99%" border="0" cellpadding="0" style=" margin:0 auto; font-size: 14px">
                <tr>
                   <td width="12%"  height="20"></td>
                   <td width="50%"  height="20"></td>
                 </tr>
                 <tr>
                   <td align="right" class="fweight borderdashed" style=" font-size: 16px; color:#D74937">
                                                         第一步：
                   </td>
                   <td align="left" height=60 class="fweight borderdashed" style=" font-size: 16px; color:#D74937">请按提示完成以下选择</td>
                 </tr>
                <tr>
                  <td height="60" align="right">计划选取：</td>
                  <td align="left">
                  <select name="plan" class="select">
                  <c:forEach items="${readyPlanList}" var="l">
                        <option value="${l.adPlanId }-${l.adPlanName }">${l.adPlanName }</option>                 
                  </c:forEach>
                  </select>
                  <span style=" margin-left:30px; color:orange">选择推广项目</span>
                  </td>
                </tr>
                
                <tr>
                  <td height="60" align="right">域名选取：</td>
                  <td align="left">
                  <select name="url"  class="select">
                  <c:forEach items="${urlList}" var="l">
                        <option value="${l.urlId }-${l.url }">${l.url }</option>                 
                  </c:forEach>
                  </select>
                  <span style=" margin-left:30px; color:orange">选择媒体网站</span>
                  </td>
                </tr>
                
            <tr>
	          <td height="37" align="center"></td>
	          <td align=left><input class="button" id="button1" name="button1" type="submit" value="确认操作" /></tr>
      </table>
      </form>
    <!--内容 end-->
    
 
</body>
</html>