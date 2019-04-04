<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
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
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/formValidator-4.1.1.js"></script>
<script type="text/javascript" src="html/js/formValidatorRegex.js"></script>
<script type="text/javascript">
	$(function(){
	$.formValidator.initConfig({theme:"126",submitOnce:true,formID:"webMasterForm",
		onError:function(msg){},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#password").formValidator({onShowFixText:"6~16个字符，包括字母、数字、特殊符号，区分大小写",onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"}).passwordValidator({compareID:"username"});
	$("#password2").formValidator({onShowFixText:"请再次输入密码",onShow:"输再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"两次密码不一致,请确认"});
   	$("#contact").formValidator({onShowFixText:"2~10个字符，包括字母、数字、特殊符号或中文，区分大小写",onShow:"请输入真实姓名",onFocus:"至少2个长度",onCorrect:"&nbsp;"}).inputValidator({min:2,max:10,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"联系人长度错误,请确认"}).regexValidator({regExp:["chinese","username"],dataType:"enum",onError:"联系人格式不正确"});
	$("#card").formValidator({onShowFixText:"15~18位数字或者以x结尾",onCorrect:"&nbsp;"}).inputValidator({min:15,max:18,onError:"身份证长度错误,请确认"}).regexValidator({regExp:"idcard",dataType:"enum",onError:"身份证格式不正确"});
	$("#telephone").formValidator({onShowFixText:"11位数字手机号码",onCorrect:"&nbsp;"}).inputValidator({min:11,max:11,onError:"电话号码长度错误,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"电话号码格式不正确"});
	$("#address").formValidator({onShowFixText:"请输入详细地址，例如：xx省xx市xx县（区）",onShow:"请输入详细地址",onCorrect:"&nbsp;"}).inputValidator({min:1,max:300,onError:"地址不能为空"});
	$("#post").formValidator({onShowFixText:"6位数字邮政编码",onCorrect:"&nbsp;"}).inputValidator({min:6,max:6,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"邮编长度错误,请确认"}).regexValidator({regExp:"zipcode",dataType:"enum",onError:"邮编格式不正确"});
	$("#qq").formValidator({onShowFixText:"数字组成的QQ号码",onCorrect:"&nbsp;"}).inputValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"}}).regexValidator({regExp:"qq",dataType:"enum",onError:"QQ号码不正确"});
	$("#bank").formValidator({onShowFixText:"请务必填写详细的开户分行，如：工商银行xx省xx市xx分行xx支行。",onShow:"请输入开户银行地址",onFocus:"至少1个长度",onCorrect:"&nbsp;"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"开户银行两边不能有空符号"},onError:"开户银行不能为空,请确认"});
	$("#bankname").formValidator({onShowFixText:"必须要跟开户收款帐号一致。注册后不可修改",onShow:"请输入开户名称",onFocus:"至少2个长度",onCorrect:"&nbsp;"}).inputValidator({min:2,max:10,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"开户名称长度错误,请确认"}).regexValidator({regExp:["chinese","username"],dataType:"enum",onError:"开户名称格式不正确"});
	$("#banknumber").formValidator({onShowFixText:"15~19位数字,包括信用卡和银联卡",onCorrect:"&nbsp;"}).inputValidator({min:15,max:19,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"银行账号长度错误,请确认"}).regexValidator({regExp:"num1",dataType:"enum",onError:"银行账号格式不正确"});
	});
	</script>
</head>
<body>
    <!--内容 begin-->
     <form id="webMasterForm" name="RegForm" action="webmaster.do?action=mypage&flag=edit" method="post">
              <table width="99%" border="0" cellpadding="0" style=" margin:0 auto">
                 <tr>
				          <td width="18%" align="right" class="fweight borderdashed">基本信息</td>
				          <td width="40%" height="37" align="center" class="borderdashed">&nbsp;</td>
				          <td width="22%" align="center" class="borderdashed">&nbsp;</td>
				        </tr>
                <tr>
                  <td height="35" align="right">用户名：</td>
                  <td align="left"><h2>${webMaster.webMasterName}</h2></td>
                  <td><div id="usernameTip" style="width:280px"></div><input id="username" name="username" type="hidden" value="${webMaster.webMasterName}"/></td>
                </tr>
                <tr>
                  <td height="35" align="right">密码：</td>
                  <td align="left"><input id="password" name="password" type="password" class="reginput" ><span style="color: red">*</span></td>
                  <td><div id="passwordTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="passwordFixTip">6~16个字符，包括字母、数字、特殊符号，区分大小写</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">密码确认：</td>
                  <td align="left"><input id="password2" name="password2" type="password" class="reginput" /><span style="color: red">*</span></td>
                 <td><div id="password2Tip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="password2FixTip">请再次输入密码</div></td>
			    </tr>
                <tr>
          <td align="right" class="fweight borderdashed">联系方式</td>
          <td height="37" align="center"  class="borderdashed">&nbsp;</td>
          <td align="left" class="borderdashed">&nbsp;</td>
        </tr>
                <tr>
                  <td height="10" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="35" align="right">联系人：</td>
                  <td align="left"><input id="contact" name="contact" type="text" class="reginput"  value="${webMaster.webMasterContactName}"/><span style="color: red">*</span></td>
                  <td><div id="contactTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="contactFixTip">2~10个字符，包括字母、数字、特殊符号或中文，区分大小写</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">身份证：</td>
                  <td align="left"><input id="card" name="card" type="text" class="reginput" value="${webMaster.webMasterCard}" /><span style="color: red">*</span></td>
                   <td><div id="cardTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="cardFixTip"> 15~18位数字或者以x结尾</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">电话：</td>
                  <td align="left"><input id="telephone" name="telephone" type="text" class="reginput" value="${webMaster.webMasterMobile}"/><span style="color: red">*</span></td>
                   <td><div id="telephoneTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="telephoneFixTip">11位数字手机号码</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">地址：</td>
                  <td align="left"><input id="address" name="address" type="text" class="reginput" value="${webMaster.webMasterAddress}" /><span style="color: red">*</span></td>
                   <td><div id="addressTip" style="width:280px"></div></td>
                </tr>
                   <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="addressFixTip">请输入详细地址，例如：xx省xx市xx县（区）</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">邮编：</td>
                  <td align="left"><input id="post" name="post" type="text" class="reginput" value="${webMaster.webMasterPost}"/><span style="color: red">*</span></td>
                   <td><div id="postTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="postFixTip">请输入邮政编码</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">QQ/MSN：</td>
                  <td align="left"><input id="qq" name="qq" type="text" class="reginput" value="${webMaster.webMasterQQ}"/><span style="color: red">*</span></td>
                  <td><div id="qqTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="qqFixTip">数字组成的QQ号码</div></td>
			    </tr>
                  <tr>
          <td height="37" align="right"  class=" fweight borderdashed">银行信息</td>
          <td align="left" class=" borderdashed"></td>
          <td align="left" class="borderdashed">&nbsp;</td>
        </tr>
                <tr>
                  <td height="10" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="35" align="right">开户银行：</td>
                  <td align="left"><input id="bank" name="bank" type="text" class="reginput" value="${webMaster.webMasterBank}"/><span style="color: red">*</span></td>
                  <td><div id="bankTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="bankFixTip">请务必填写详细的开户分行，如：工商银行xx省xx市xx分行xx支行。</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">开户名称：</td>
                  <td align="left"><input id="bankname" name="bankname" type="text" class="reginput" value="${webMaster.webMasterBankName}"/><span style="color: red">*</span></td>
                   <td><div id="banknameTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="banknameFixTip">必须要跟开户收款帐号一致。注册后不可修改</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">银行帐号：</td>
                  <td align="left"><input id="banknumber" name="banknumber" type="text" class="reginput" value="${webMaster.webMasterBankNumber}" /><span style="color: red">*</span></td>
                   <td><div id="banknumberTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="banknumberFixTip">15~19位数字，请输入开户银行号码</div></td>
			    </tr>
			    <tr>
		          <td height="37" align="center">&nbsp;</td>
		          <td align="center"><span style="color: red;">${msg}</span></td>
		          <td  align="center">&nbsp;</td>
        	</tr>
        	<tr>
          <td height="37" align="center">&nbsp;</td>
          <td align="center"><input   class="button" id="button1" name="button1" type="submit" value="修改资料" />
            &nbsp;&nbsp;
            <input  class="button" type="reset" value="重置信息" /></td>
          <td  align="center">&nbsp;</td>
        </tr>
      </table>
    <!--内容 end-->
</body>
</html>