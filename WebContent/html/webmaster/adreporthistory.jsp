<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=request.getContextPath() %>/html/webmaster/"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>骏易传媒 - 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/main.js"></script>
<script language="javascript" type="text/javascript" src="../../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function reportExcel(){
		 document.reportForm.action="${pageContext.request.contextPath }/report.do?action=reportExcel";
		 document.reportForm.submit();
	}
</script>
</head>

<body>
    <!--内容 begin-->
        <form name="reportHistoryForm" method="post" action="${pageContext.request.contextPath }/report.do?action=history" >
      <table width="100%" border="0" cellpadding="2">
        <tr>
          <td height="40" colspan="11" align="left">
          <form action="" method="post" name="ReportForm" id="ReportForm">
           &nbsp;&nbsp;&nbsp;&nbsp;
           广告计划：
            <select name="adplan">
              <option value="">按计划筛选</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            日期选择：
            <input type="text" id="beginTime" name="beginTime" size="8" onfocus="WdatePicker({skin:'default',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" readonly="readonly"/>
            &nbsp;-&nbsp;
            <input type="text" id="endTime" name="endTime" size="8" onfocus="WdatePicker({skin:'default',minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" readonly="readonly"/>
            &nbsp;&nbsp;
            <input type="submit" value="检索" />
            <input type="button" class="button" onclick="reportExcel()" value="导出报表">
          </form></td>
        </tr>
        <tr class="th fweight">
          <td width="8%" height="30" align="center">编号</td>
          <td width="17%" align="center">创意</td>
          <td width="15%" align="center">广告位</td>
          <td width="15%" align="center">域名</td>
          <td width="14%" align="center">所属计划</td>
          <td width="18%" align="center">点击数（站长查看列）</td>
          <td width="13%" align="center">日期</td>
        </tr>
        <tr class="trbg1">
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center" class="fcblue fweight">汇总：</td>
          <td height="22" align="center">&nbsp;</td>
        </tr>
        <tr class="trbg2">
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
          <td height="22" align="center">&nbsp;</td>
        </tr>
        <tr class="trbg2">
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
          <td height="22" align="center" class="trbg1">&nbsp;</td>
        </tr>
      </table>
  视图:ViewAdBoxCount 
    <!--内容 end-->
</body>
</html>