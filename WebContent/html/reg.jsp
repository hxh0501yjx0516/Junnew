<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>北京中润无线广告有限公司</title>
<!-- <link href="html/images/jnico.ico" rel="SHORTCUT ICON"> -->
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/formValidator-4.1.1.js"></script>
<script type="text/javascript" src="html/js/formValidatorRegex.js"></script>
<script type="text/javascript">
	$(function(){
	$.formValidator.initConfig({theme:"126",submitOnce:true,formID:"RegForm",
		onError:function(msg){},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#username").formValidator({onShowFixText:"6~12个字符，包括字母、数字、下划线，以字母开头，字母或数字结尾",onShowText:"请输入用户名",onShow:"请输入用户名,只有输入\"maodong\"才是对的",onCorrect:"该用户名可以注册"}).inputValidator({min:6,max:12,onError:"你输入的用户长度不正确,请确认"}).regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	    .ajaxValidator({
	    type : 'POST',
		dataType : "html",
		async : true,
		url : "${pageContext.request.contextPath }/register.do?action=validate",
		success : function(data){
            if( data.indexOf("success") > 0 ) return true;
            if( data.indexOf("faild") > 0 ) return false;
			return false;
		},
		buttons: $("#button21"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该用户名不可用，请更换用户名",
		onWait : "正在进行合法性校验，请稍候..."
	});
	$("#password").formValidator({onShowFixText:"6~16个字符，包括字母、数字、特殊符号，区分大小写",onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"}).passwordValidator({compareID:"username"});
	$("#password2").formValidator({onShowFixText:"请再次输入密码",onShow:"输再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"两次密码不一致,请确认"});
   	$("#contact").formValidator({onShowFixText:"2~10个字符，包括字母、数字、特殊符号或中文，区分大小写",onShowText:"请输入真实姓名",onShow:"请输入真实姓名",onFocus:"至少2个长度",onCorrect:"&nbsp;"}).inputValidator({min:2,max:10,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"联系人长度错误,请确认"}).regexValidator({regExp:["chinese","username"],dataType:"enum",onError:"联系人格式不正确"});
	$("#card").formValidator({onShowFixText:"15~18位数字或者以x结尾",onShowText:"请输入身份证号码",onCorrect:"&nbsp;"}).inputValidator({min:15,max:18,onError:"身份证长度错误,请确认"}).regexValidator({regExp:"idcard",dataType:"enum",onError:"身份证格式不正确"});
	$("#telephone").formValidator({onShowFixText:"11位数字手机号码",onShowText:"请输入电话号码",onCorrect:"&nbsp;"}).inputValidator({min:11,max:11,onError:"电话号码长度错误,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"电话号码格式不正确"});
	$("#address").formValidator({onShowFixText:"请输入详细地址，例如：xx省xx市xx县（区）",onShowText:"请输入详细地址",onShow:"请输入详细地址",onCorrect:"&nbsp;"}).inputValidator({min:1,max:300,onError:"地址不能为空"});
	$("#post").formValidator({onShowFixText:"6位数字邮政编码",onShowText:"请输入邮政编码",onCorrect:"&nbsp;"}).inputValidator({min:6,max:6,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"邮编长度错误,请确认"}).regexValidator({regExp:"zipcode",dataType:"enum",onError:"邮编格式不正确"});
	$("#qq").formValidator({onShowFixText:"数字组成的QQ号码",onShowText:"请输入QQ号码",onCorrect:"&nbsp;"}).inputValidator({empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"}}).regexValidator({regExp:"qq",dataType:"enum",onError:"QQ号码不正确"});
	$("#bank").formValidator({onShowFixText:"请务必填写详细的开户分行，如：工商银行xx省xx市xx分行xx支行。",onShowText:"请输入开户银行地址",onShow:"请输入开户银行地址",onFocus:"至少1个长度",onCorrect:"&nbsp;"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"开户银行两边不能有空符号"},onError:"开户银行不能为空,请确认"});
	$("#bankname").formValidator({onShowFixText:"必须要跟开户收款帐号一致。注册后不可修改",onShowText:"请输入开户名称",onShow:"请输入开户名称",onFocus:"至少2个长度",onCorrect:"&nbsp;"}).inputValidator({min:2,max:50,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"开户名称长度错误,请确认"}).regexValidator({regExp:["chinese","username"],dataType:"enum",onError:"开户名称格式不正确"});
	$("#banknumber").formValidator({onShowFixText:"15~21位数字,包括信用卡和银联卡",onShowText:"请输入银行卡号",onCorrect:"&nbsp;"}).inputValidator({min:15,max:21,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"银行账号长度错误,请确认"}).regexValidator({regExp:"num1",dataType:"enum",onError:"银行账号格式不正确"});
	$("#randCode").formValidator({onShowFixText:"请输入验证码",onShow:"请输入验证码",onFocus:"至少1个长度",onCorrect:"&nbsp;"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"验证码两边不能有空符号"},onError:"验证码不能为空,请确认"});
	
	});
	  function userLogin()
        {
            $.blockUI({ 
            	message: $('#users1'),
            	css: {
		        top:		'50%',
		        left:		'50%',
		        textAlign:	'left',
		        marginLeft:     '-320px', 
		        marginTop:      '-145px', 
                width: '600px',
                background:'none'
            }}); 
            $('.blockOverlay').attr('title','单击关闭').click($.unblockUI); 
        }
        function login()
        {
            var uName = $("#username").val();
            var uPwd = $("#userpwd").val();
            $.blockUI({ message: "<h1>登录中...</h1>" }); 
            $.ajax({ 
                url: 'login.do?action=login&username='+uName+'&userpwd='+uPwd, 
                cache: false, 
                complete: function(rs) { 
                   // $.unblockUI();
                   alert(rs);
                }             
            }); 
        }
        function calcle()
        {
            $.unblockUI(); 
        }
	</script>
</head>
<body>
<%@ include file="/html/include/top.jsp" %>
 
<div id="main">
	<!--flash begin
	<div id="flash">
    	<img src="images/flash.jpg" width="960" height="317">
    </div>
	<!--flash end-->
  
	<!--网站主注册 begin-->
    <div id="model">
    	<div class="top"><img src="html/images/regt.jpg" width="960" height="51"></div>
        <div class="middle">
        	<div id="regt">
            	如果您已经拥有网站主帐户，请从这里<a href="index.jsp" ><span  class="fcred">登录</span></a>
            </div>
            <div id="regform">
            <form id="RegForm" name="RegForm" action="register.do?action=add" method="post">
              <table width="80%" border="0" cellpadding="0" style=" margin:0 auto">
                <tr>
                  <td width="18%" height="10" align="right">&nbsp;</td>
                  <td width="40%" align="left" >&nbsp;</td>
                  <td width="42%" align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="50" colspan="3" align="left" class=" regclass f14 fweight"><img src="html/images/members0.png" width="25" height="25" align="absmiddle">&nbsp;&nbsp;帐户信息</td>
                </tr>
                <tr>
                  <td height="10" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="35" align="right">用户名：</td>
                  <td align="left"><input id="username" name="username" type="text" class="reginput" ><span style="color: red">*</span></td>
                   <td><div id="usernameTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="usernameFixTip">6~12个字符，包括字母、数字、下划线，以字母开头，字母或数字结尾</div></td>
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
                  <td height="30" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="50" colspan="3" align="left" class=" regclass f14 fweight"><img src="html/images/contacts.png" width="25" height="25" align="absmiddle">&nbsp;&nbsp;联系方式</td>
                </tr>
                <tr>
                  <td height="10" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="35" align="right">联系人：</td>
                  <td align="left"><input id="contact" name="contact" type="text" class="reginput"  /><span style="color: red">*</span></td>
                  <td><div id="contactTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="contactFixTip">2~10个字符，包括字母、数字、特殊符号或中文，区分大小写</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">身份证：</td>
                  <td align="left"><input id="card" name="card" type="text" class="reginput"  /><span style="color: red">*</span></td>
                   <td><div id="cardTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="cardFixTip"> 15~18位数字或者以x结尾</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">电话：</td>
                  <td align="left"><input id="telephone" name="telephone" type="text" class="reginput"/><span style="color: red">*</span></td>
                   <td><div id="telephoneTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="telephoneFixTip">11位数字手机号码</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">地址：</td>
                  <td align="left"><input id="address" name="address" type="text" class="reginput"  /><span style="color: red">*</span></td>
                   <td><div id="addressTip" style="width:280px"></div></td>
                </tr>
                   <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="addressFixTip">请输入详细地址，例如：xx省xx市xx县（区）</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">邮编：</td>
                  <td align="left"><input id="post" name="post" type="text" class="reginput" /><span style="color: red">*</span></td>
                   <td><div id="postTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="postFixTip">请输入邮政编码</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">QQ/MSN：</td>
                  <td align="left"><input id="qq" name="qq" type="text" class="reginput" /><span style="color: red">*</span></td>
                  <td><div id="qqTip" style="width:280px"></div></td>
                </tr>
                 <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="qqFixTip">数字组成的QQ号码</div></td>
			    </tr>
                <tr>
                  <td height="30" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="50" colspan="3" align="left" class=" regclass f14 fweight">银行帐户信息</td>
                </tr>
                <tr>
                  <td height="10" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="35" align="right">开户银行：</td>
                  <td align="left"><input id="bank" name="bank" type="text" class="reginput" /><span style="color: red">*</span></td>
                  <td><div id="bankTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="bankFixTip">请务必填写详细的开户分行，如：工商银行xx省xx市xx分行xx支行。</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">开户名称：</td>
                  <td align="left"><input id="bankname" name="bankname" type="text" class="reginput" /><span style="color: red">*</span></td>
                   <td><div id="banknameTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="banknameFixTip">必须要跟开户收款帐号一致。注册后不可修改</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">银行帐号：</td>
                  <td align="left"><input id="banknumber" name="banknumber" type="text" class="reginput"  /><span style="color: red">*</span></td>
                   <td><div id="banknumberTip" style="width:280px"></div></td>
                </tr>
                <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="banknumberFixTip">15~21位数字，请输入开户银行号码</div></td>
			    </tr>
                <tr>
                  <td height="35" align="right">验证码：</td>
                  <td align="left" >
                  <span style="float: left;padding-left: 0;margin-left: 0">
                  <input type="text" id="randCode" name="randCode" class="logininput" style="width:60px;padding-left: 0px;padding-right: 0px;" maxlength="4">
                  </span> 
        	    	<a href="javascript:void(0);" onclick="getRandCode('randImg1');return false;" title="刷新验证码" style="float: left;padding-left: 0;margin-left: 0"><img style="border: 0px solid black;" id="randImg1" src="html/include/checkCode.jsp" /></a>
        	    	<a href="javascript:void(0);" onclick="getRandCode('randImg1');return false;" title="看不清？换一张" style="float: left;padding-left: 0;margin-left: 0">看不清？换一张</a>
                  </td>
                  <td><div id="randCodeTip" style="width:280px"></div></td>
                </tr>
                 <tr>
                  <td height="30" align="right">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr>
                  <td height="50" colspan="3" align="left" class=" regclass f14 fweight"><img src="html/images/xieyi_03.gif" width="20" height="20" align="absmiddle">&nbsp;&nbsp;中润无线服务协议</td>
                </tr>
                <tr>
                  <td height="30" colspan="3" align="center" style=" text-indent:0">
                  <textarea rows="20" cols="110">
&nbsp;&nbsp;&nbsp;&nbsp;“中润无线”（即http://www.junnew.com）所提供的各项服务的所有权和运作权、使用权等由北京盘酷科技有限公司（以下简称“中润”）拥有。中润通过中润网络传媒向联盟会员（以下简称“联盟会员”）提供本服务条款项下的服务。中润提供的服务将完全按照其发布的本服务条款、中润网络传媒使用流程严格执行。联盟会员必须完全同意所有本服务条款以及相关使用流程，在完成注册程序、并经中润网络传媒确认后，才能成为“中润网络传媒 ”的联盟会员。

一．申请资格
本服务条款中所指的联盟会员是指下述符合要求的网站所有人：
（1）个人网站：网站的所有人应为拥有中华人民共和国公民资格，具有完全的民事行为能力，并能够独立承担法律责任的自然人。
（2）商业网站：商业网站是指除个人网站之外的，从事商务行为的企业法人、实体、组织机构等所拥有的网站。商业网站的所有人应为在中华人民共和国领域内合法登记注册的企业法人或实体、组织机构。
（3）对网站的要求：联盟会员应保证其向中润提交的网站已经获得了政府有关部门的所有许可和批准，有权进行网站的运行和经营。网站的经营严格遵守相关法律法规，网站所进行的市场开拓、广告宣传及相关经营活动合法。网站不得包含下列内容：（以下简称“违法内容”）
a.反对宪法所确定的基本原则的；
b.危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；
c.损害国家荣誉和利益的；
d.煽动民族仇恨、民族歧视，破坏民族团结的；
e.破坏国家宗教政策，宣扬邪教和封建迷信的；
f.散布谣言，扰乱社会秩序，破坏社会稳定的；
g.散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；
h.侮辱或者诽谤他人，侵害他人合法权益的；
i. 侵犯他人知识产权的，包括但不限于专利权、商标权、著作权；
j. 侵犯他人商业秘密的；
k.含有法律、行政法规禁止的其他内容的。
无论个人网站还是商业网站，网站的所有人对自己的主页应具有完全的所有权、使用权、决策权等相应权利，并确保其网站的网页能够在800×600的解析度下正常显示。
（4）网站的所有人必须拥有一个固定的常住地址或办公地址，并具有经常上网收发电子邮件的能力。 
（5）对于被中润暂时停止或者永久终止帐户的会员，中润将不再提供本中润网络传媒项下的系列服务。 

二．联盟会员注册、广告投放程序
（1） 在按照中润网络传媒网页提示申请并注册后，中润将在两天内审查资格，并发出确认或者拒绝加入中润网络传媒的邮件 。
（2） 通过确认信件中的链接地址激活帐号，并以自己的帐号和密码登录会员区，所有的会员操作包括各种说明都能够在会员区中获取。
（3） 将会员区中的广告代码放置在联盟会员的网站页面中，通过联盟会员的网站上的广告代码引导的有效注册用户都将被记录下来作为中润与联盟会员进行结算的依据。

三．联盟会员的权利
（1）联盟会员在注册登记、得到中润网络传媒的确认，并激活中润网络传媒确认邮件中提供的链接以后，正式成为中润网络传媒的联盟会员。享有中润网络传媒提供的服务。
（1）联盟会员有权按联盟支付条款的规定获得联盟的广告分成。

四．联盟会员的义务
（1）联盟会员必须保证其网站的所有网页不包含任何本条款第一条第（3）款定义的违法内容、并且保证不连接到含有违法内容的网页：
（2）如果会员提供给中润网络传媒的资料、信息等发生变化，包括网页地址、Email、联系电话等，应立刻通过会员区中的个人资料修改及时更改。
（3）中润有权随时单方面决定更改本服务条款。如服务条款有任何变更，中润将在"中润中润网络传媒"上刊载公告，通知联盟会员。经修订的须知一经公布后，立即自动生效。
（4）本服务条款变更后，如联盟会员继续使用中润网络传媒提供的服务，则表示联盟会员接受经修订的服务条款。如联盟会员不同意服务条款的相关变更，应书面通知中润。自中润接到会员的书面通知起，视为该联盟会员自动退出中润网络传媒。
（5）联盟会员不可擅自修改来自中润，或来自向中润提供服务的第三方网站、公司，或来自与中润有合作关系的第三方的广告源代码；未经中润同意，也不得将此源代码提供给其它任何第三方使用、参考等。否则，由此产生任何第三方向中润的追偿、索赔，中润有权向联盟会员追偿。
（6）联盟会员对所有来自中润，或来自向中润提供服务的第三方网站、公司，或来自与中润有合作关系的第三方的广告内容、文本、图像等，未经中润或有关第三方同意，不得对其进行任何形式的修改、补充、变更。
（7）联盟会员有义务根据中润，或向中润提供服务的第三方网站、公司，或与中润有合作关系的第三方对广告的内容、文本、图像等的修改、补充、删减、更新等，在其网站上对相应的广告做出变更，以保证广告内容的准确性、完整性。
（8）联盟会员不得在其网页上置放任何 " 联盟广告主竞争者 " 的网络广告（包括但不限于按钮、条幅、导航钮），也不得向其用户发送 " 联盟广告主竞争者 " 的促销电子邮件。 

五．违约责任
（1）中润严禁通过设备、程序以及其他不合法的手段提高个人收入。一经发现，中润有权立即取消其联盟会员资格，撤回正在投放的广告，并追回已经支付的全部广告发布费，中润保留进一步追索责任的权利；同时，该会员必须承担因此给中润带来的所有损失。
（2）联盟会员承诺其向中润网络传媒提交的任何资料，包括但不限于其注册信息、网页地址，联系方式等的真实性。中润不承担对上述资料的真实性的审查责任。但是，一旦中润发现联盟会员提供虚假信息或采取其它欺骗手段，有权暂停或终止联盟会员的帐户，并保留追究联盟会员责任的权利。
（3）联盟会员承诺，其在中润网络传媒中的任何行为均不会使中润、中润网络传媒的其它联盟会员、中润的任何其它用户、向中润提供服务的第三方网站、公司，或与中润有合作关系的第三方承担任任何法律责任和处罚。否则，联盟会员应作出相应的赔偿。
（4）在不限制任何其他补救措施的前提下，中润倘若发现会员从事涉及中润网络传媒网站的诈骗等非法活动、或其它有可能损害中润、中润网络传媒的联盟会员、中润的任何其它用户、向中润提供服务的第三方网站、公司，或与中润有合作关系的第三方的利益时，中润可暂停或终止联盟会员的帐户，并有权停止支付相关费用。 
（5）联盟会员同意，因联盟会员违反本服务条款或在本服务条款中提及的应遵守的其他文件，或因联盟会员违反了法律或侵害了第三方的权利，而使第三方对中润及其子公司、分公司、董事、职员、代理人提出索赔要求（包括但不限于司法费用和其他专业人士的费用），联盟会员必须全额赔偿给中润。

六．不可抗力
对于因中润合理控制范围以外的原因，包括但不限于自然灾害、罢工、骚乱、物质短缺或定量配给、暴动、战争行为、政府行为、通讯或其他设施故障或严重伤亡事故等，致使新中润延迟或未能履约的，中润不对联盟会员承担任何责任。

七．仲裁
因本服务条款或中润网络传媒服务所引起联盟会员与中润之间的任何争议，应提交北京仲裁委员会并根据其适用的仲裁规则进行仲裁裁决。任何该等争议应单独地仲裁，不得与任何其他方的争议在任何仲裁中合并处理。仲裁应在北京市进行，而仲裁裁决可提交对其有管辖权的任何法院予以强制执行。在仲裁进行期间，可向北京市地区内具有管辖权的法院寻求为保护联盟会员或中润的权利或财产所需的任何临时或初步补救措施，包括但不限于财产保全或证据保全措施。

北京中润无线广告有限公司
2009年2月
                  </textarea>
                  </td>
                </tr>
                 <tr>
                  <td colspan="3" align="center"><input id="button1" type="submit" value="同意以下 注册条款，提交注册信息" class="regsubmit"></td>
                </tr>
              </table>
            </form>
            </div>
        </div>
        <div class="bottom"><img src="html/images/ggmsb.jpg" width="960" height="9"></div>
    </div>
	<!--网站主注册 end-->
    
  <div class="clear"></div>
</div>
<!--用户登录 begin-->
    <div id="users1" style="display: none;" class="box">
    	<div class="top"><img src="html/images/usertop.jpg" width="230" height="60"></div>
        <div class="middle">
          <form name="loginForm" action="" method="post">
       	  <table width="95%" border="0">
        	  <tr>
        	    <td colspan="2"><span id="msg" style="color: red;">${mes}</span></td>
      	    </tr>  
      	    <tr>
        	    <td height="30" align="right">登&nbsp;&nbsp;&nbsp;录：</td>
        	    <td align="left">
                	<select name="type" class="se">
                    	<option value="0" ${loginType ==0?"selected":"" } >网站主登陆</option>
                        <option value="1" ${loginType ==1?"selected":"" }>广告主登陆</option>
                    </select>
                </td>
      	    </tr>
        	  <tr>
        	    <td width="32%" height="30" align="right">用户名：</td>
        	    <td width="68%" align="left"><input type="text" id="username"  name="username" class="logininput" value="${username}"></td>
      	    </tr>
        	  <tr>
        	    <td height="30" align="right">密&nbsp;&nbsp;&nbsp;码：</td>
        	    <td align="left"><input type="password" id="userpwd" name="userpwd" class="logininput" value="${userpwd}"></td>
      	    </tr>
      	      <tr>
        	    <td height="30" align="right">验证码：</td>
        	    <td align="left">
        	    <input type="text" id="randCode" name="randCode" class="logininput" style="width:60px;" maxlength="4" onKeyDown="if(event.keyCode==13)onLogin();">
        	    <a href="javascript:void(0);" onclick="getRandCode('randImg')" title="刷新验证码" ><img  id="randImg" src="html/include/checkCode.jsp" style="float: right;padding-right: 13px;border: 0px solid red;"/></a>
        	    </td>
      	    </tr>
        	  <tr>
        	    <td height="50" colspan="2">
        	    &nbsp;&nbsp;<input type="button" value="" class="lo" onclick="onLogin();" >
        	    <input type="button" value="" class="re" onclick="onRegister();">
        	    </td>
       	    </tr>
      	  </table>
          </form>
</div>
        <div class="bottom"><img src="html/images/newb.jpg" width="230" height="6"></div>
    </div>
    <!--用户登录 end-->
<%@ include file="include/bottom.jsp" %>
</body>
</html>