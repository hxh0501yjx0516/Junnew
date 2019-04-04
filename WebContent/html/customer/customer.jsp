<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>首信联盟- 网络广告专业营销机构</title>
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/formValidator-4.1.1.js"></script>
<script type="text/javascript" src="html/js/formValidatorRegex.js"></script>
<script type="text/javascript">
	$(function(){
	$.formValidator.initConfig({theme:"126",submitOnce:true,formID:"customerForm",
		onError:function(msg){}, 
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#password").formValidator({onShowFixText:"6~16个字符,字母、数字、特殊符号，区分大小写",onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"});
	$("#password1").formValidator({onShowFixText:"请再次输入密码",onShow:"输再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"两次密码不一致,请确认"});
   	$("#contact").formValidator({});
	$("#telephone").formValidator({});
	$("#address").formValidator({});
	$("#post").formValidator({});
	$("#url").formValidator({});
	});
	</script>
</head>

<body>
    <!--内容 begin-->
         <div id="contitle">会员信息</div>
            <div id="context">
      <form id="customerForm" name="customerForm" action="customer.do?action=mypage&flag=edit" method="post">
      <input type="hidden" name="customerId" value="${customer.customerId}"/>
      <table width="100%" border="0" cellpadding="2">
        <tr>
          <td width="7%" height="37" align="center" class="borderdashed">&nbsp;</td>
          <td width="20%" align="left" class="fweight borderdashed">基本信息</td>
          <td width="48%" align="center" class="borderdashed">&nbsp;</td>
          <td width="25%" align="center" class="borderdashed">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td height="22" align="right">客户名称：</td>
          <td align="left"><h2>${customer.customerName}</h2></td>
          <td>&nbsp;</td>
        </tr>
         <tr>
	      <td align="right">&nbsp;</td>
	      <td align="right">&nbsp;</td>
	      <td colspan="2" valign="top">&nbsp;</td>
	    </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td height="22" align="right">密码：</td>
          <td align="left"><input type="password" name="password" id="password" class="reginput"><span style="color: red">*</span></td>
         <td><div id="passwordTip" style="width:280px"></div></td>
        </tr>
        <tr>
	      <td align="right">&nbsp;</td>
	      <td align="right">&nbsp;</td>
	      <td colspan="2" valign="top"><div id="passwordFixTip">6~16个字符，包括字母、数字、特殊符号，区分大小写</div></td>
	    </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td height="22" align="right">密码确认：</td>
          <td align="left"><input type="password" name="password1" id="password1" class="reginput"><span style="color: red">*</span></td>
          <td><div id="password1Tip" style="width:280px"></div></td>
        </tr>
        <tr>
	      <td align="right">&nbsp;</td>
	      <td align="right">&nbsp;</td>
	      <td colspan="2" valign="top"><div id="password1FixTip">请再次输入密码</div></td>
	    </tr>
        <tr>
          <td height="30" align="center"  class="borderdashed">&nbsp;</td>
          <td height="22" align="left" class="fweight borderdashed">&nbsp;</td>
          <td align="left" class="borderdashed">&nbsp;</td>
          <td align="center" class="borderdashed">&nbsp;</td>
        </tr>
        <tr>
          <td height="37" align="center"  class="borderdashed">&nbsp;</td>
          <td align="left" class="fweight borderdashed">联系方式</td>
          <td align="left" class="borderdashed">&nbsp;</td>
          <td align="center" class="borderdashed">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td align="right">联系人：</td>
          <td align="left"><input type="text" name="contact" id="contact" class="reginput" value="${customer.customerContactName}"></td>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td align="right">电话：</td>
          <td align="left"><input type="text" name="telephone" id="telephone" class="reginput" value="${customer.customerContactMobile}"></td>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td align="right">地址：</td>
          <td align="left"><input type="text" name="address" id="address" class="reginput" value="${customer.customerAddress}"></td>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td align="right">邮编：</td>
          <td align="left"><input type="text" name="post" id="post" class="reginput" value="${customer.customerPost}"></td>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td align="right">网址：</td>
          <td align="left"><input type="text" name="url" id="url" class="reginput" value="${customer.customerUrl}"></td>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td height="30" align="center" class="borderdashed">&nbsp;</td>
          <td align="right" class="borderdashed">&nbsp;</td>
          <td align="center" class="borderdashed"><span style="color: red;">${msg}</span></td>
          <td align="center" class="borderdashed">&nbsp;</td>
        </tr>
        <tr>
          <td height="37" align="center">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td align="center">
          <input class="button" type="submit" id="button1" name="button1" value="修改资料" />&nbsp;&nbsp;
          <input class="button" type="reset" id="button2" value="重置信息"></td>
          <td align="center">&nbsp;</td>
        </tr>
      </table>
      </form>
      </div>
    <!--内容 end-->
</body>
</html>