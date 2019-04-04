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
    <form id="stepForm" name="stepForm" action="code.do?action=step3" method="post">
    <table width="99%" border="0" cellpadding="0" style=" margin:0 auto; font-size: 14px">
    <input type="hidden" name="plan" value="${plan}" />
    <input type="hidden" name="url" value="${url}" />
                <tr>
                   <td width="12%"  height="20"></td>
                   <td width="50%"  height="20"></td>
                 </tr>
                 <tr>
                   <td align="right" class="fweight borderdashed" style=" font-size: 16px; color:#D74937">
                                                         第二步：
                   </td>
                   <td align="left" height=60 class="fweight borderdashed" style=" font-size: 16px; color:#D74937">请选取推广创意</td>
                 </tr>
                 
                 <tr>
                   <td></td>
                   <td align="left">
                   <table width="100%" border="0" cellpadding="0" style=" margin:0 auto; font-size: 14px">
                    
                    <c:forEach items="${creativeList}" var="l" varStatus="c">
	                    <c:if test="${c.index % 2 eq 0}">
				         <tr>
				        </c:if>
	                    <td height='30' align='left'>
                            <input type="checkbox" name="chk" value="${l.adCreativeId}" />&nbsp;&nbsp;${l.adCreativeName}
                        </td>
                        
                    </c:forEach>
                    </tr>
                    </table>
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