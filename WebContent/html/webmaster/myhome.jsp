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
<script type="text/javascript">
 function jumpWebMaster(action,target,parms){
	 var url = action+".do?action="+target;
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
	<form name="myHomeForm" method="post" action="webmaster.do?action=myhome" >
		<table width="100%" border="0" cellpadding="2" style=" font-size:14px;">
          <tr>
            <td width="21%" height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;媒介专员：</td>
           <td  colspan="2" align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <c:choose>
            	<c:when test="${medium == null}">暂无</c:when>
            	<c:otherwise>${medium.realname}</c:otherwise>
            </c:choose>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：
			<c:choose>
            	<c:when test="${medium == null}">暂无</c:when>
            	<c:otherwise>${medium.tel == ""?"暂无":medium.tel}</c:otherwise>
            </c:choose>
            </td>
      </tr>
          <tr>
            <td height="30" class="title">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;我的域名：</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
           <td height="30" class="title"></td>
            <td height="30" colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            所有域名&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b5');jumpWebMaster('webmaster','urladdress');">${urlSum}</a>&nbsp;个&nbsp;，&nbsp;&nbsp;&nbsp;&nbsp;
            审核通过&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b5');jumpWebMaster('webmaster','urladdress','1');">${urlRun}</a>&nbsp;个，&nbsp;&nbsp;&nbsp;&nbsp;
            待审核&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b5');jumpWebMaster('webmaster','urladdress','0');">${urlOver}</a>&nbsp;个，&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;&nbsp;&nbsp;&nbsp;我的计划：</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
          <td height="30" class="title"></td>
            <td height="30" colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            所有计划&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b6');jumpWebMaster('adplan','myadplan');">${planSum}</a>&nbsp;个&nbsp;，&nbsp;&nbsp;&nbsp;&nbsp;
            审核通过&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b6');jumpWebMaster('adplan','myadplan','0');">${planRun}</a>&nbsp;个，&nbsp;&nbsp;&nbsp;&nbsp;
            待审核&nbsp;<a style="cursor: pointer;" class="fcyellow" onclick="parent.changeLeftTab('b6');jumpWebMaster('adplan','myadplan','1');">${planOver}</a>&nbsp;个
            </td>
          </tr>
          <tr>
            <td height="30" class="title">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
          <td height="30" class="title"></td>
            <td height="30" colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0);" class="webmaster" onclick="parent.changeLeftTab('b9');jumpWebMaster('report','daily');return false;">【今日数据查看】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0);" class="webmaster" onclick="parent.changeLeftTab('b9');jumpWebMaster('report','history');return false;">【历史数据查看】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0);" class="webmaster" onclick="parent.changeLeftTab('b11');jumpWebMaster('webmaster','balance');return false;">【历史收入查看】</a>
            </td>
          </tr>
    </table>
    </form>
    </body>
    </html>