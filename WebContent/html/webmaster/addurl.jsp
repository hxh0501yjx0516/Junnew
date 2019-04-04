<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加域名</title> 
<link href="html/images/jnico.ico" rel="SHORTCUT ICON">
<link href="html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="html/js/main.js"></script>
<script type="text/javascript" src="html/js/jquery.min.js"></script>
<script type="text/javascript" src="html/js/formValidator-4.1.1.js"></script>
<script type="text/javascript" src="html/js/formValidatorRegex.js"></script>
<script type="text/javascript">
  $(function(){
	$.formValidator.initConfig({theme:"126",submitOnce:true,formID:"addUrlAddressForm",
		onError:function(msg){},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#url").formValidator({onShowFixText:"请输入正确域名，例如：www.example.com",onShowText:"请输入域名",onCorrect:"该域名可以添加"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"域名不能为空"}).regexValidator({regExp:"url",dataType:"enum",onError:"域名格式不正确"})
	    .ajaxValidator({
	    type : 'POST',
		dataType : "html",
		async : true,
		url : "${pageContext.request.contextPath }/webmaster.do?action=validate",
		success : function(data){
            if( data.indexOf("success") > 0 ) return true;
            if( data.indexOf("faild") > 0 ) return false;
			return false;
		},
		buttons: $("#button21"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该域名已存在，请更换域名",
		onWait : "正在进行合法性校验，请稍候..."
	});
	$("#urlName").formValidator({ }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"网站名称不能为空"});
	$("#urlIcp").formValidator({ }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"ICP不能为空"});
	$("#urlDayIp").formValidator({ }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"日IP不能为空"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请正确填写日流量"});
	$("#urlArea").formValidator({ }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"所属地区不能为空"});
	$("#urlTypeId").formValidator({ }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空符号"},onError:"网站类别不能为空"});
	});
  
    function getSelectedText(obj,outObject){
   var strsel = obj.options[obj.selectedIndex].text;
   if(obj.selectedIndex == 0){
   document.getElementById(outObject).value="";
   }else{
   document.getElementById(outObject).value=strsel;
   }
  }
 </script>
</head>
<body>
		<form id="addUrlAddressForm" name="addUrlAddressForm" method="post" action="${pageContext.request.contextPath }/webmaster.do?action=addUrladdress&flag=save">
			<input type="hidden" id="urlTypeName" name="urlTypeName" />
			 <table width="99%" border="0" style=" margin:0px auto;font-size: 12px;">
                  <tr>
                  <td height="45" width="40%"  align="right" style="border-bottom: 1px dashed silver;"><h3>域名添加</h3></td>
                  <td height="45"  width="43%" align="left" style="border-bottom: 1px dashed silver;">&nbsp;&nbsp; </td>
                  <td height="45"  style="border-bottom: 1px dashed silver;">&nbsp;&nbsp; </td>
                   </tr>
                   <tr>
                  <td height="35"  align="right" > </td>
                  <td align="left"  ></td>
                  <td  ></td>
                   </tr>
                  <tr>
                  <td height="45" width="40%" align="right">域名：</td>
                  <td align="left" width="43%"><input id="url" name="url" type="text" size="30" /><span style="color: red">*</span></td>
                  <td><div id="urlTip" style="width:280px"></div></td>
                   </tr>
                    <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="urlFixTip">请输入正确域名，例如：www.example.com</div></td>
			     </tr>
                    <tr>
                  <td height="45" align="right">网站名称 ：</td>
                  <td align="left"><input id="urlName" name="urlName"  type="text" size="30"  /><span style="color: red">*</span></td>
                  <td><div id="urlNameTip" style="width:280px"></div></td>
                   </tr>
                    <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="urlNameFixTip"></div></td>
			     </tr>
                     <tr>
                  <td height="45" align="right">ICP：</td>
                  <td align="left"><input  id="urlIcp"  name="urlIcp"  type="text" size="30"  /><span style="color: red">*</span></td>
                  <td><div id="urlIcpTip" style="width:280px"></div></td>
                   </tr>
                    <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="urlIcpFixTip"></div></td>
			     </tr>
                    <tr>
                  <td height="45" align="right">日IP量 ：</td>
                  <td align="left"><input  id="urlDayIp" name="urlDayIp"  type="text" size="30"  /><span style="color: red">*</span></td>
                  <td><div id="urlDayIpTip" style="width:280px"></div></td>
                   </tr>
                    <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="urlDayIpFixTip"></div></td>
			     </tr>
                    <tr>
                  <td height="45" align="right">所属地区 ：</td>
                  <td align="left"><input  id="urlArea" name="urlArea"  type="text" size="30"  /><span style="color: red">*</span></td>
                  <td><div id="urlAreaTip" style="width:280px"></div></td>
                   </tr>
                    <tr>
			      <td align="right">&nbsp;</td>
			      <td colspan="2" valign="top"><div id="urlAreaFixTip"></div></td>
			     </tr>
                    <tr>
                  <td height="45" align="right">网站类型  ：</td>
                  <td align="left">
                  <select style="width: 300px;height: 30px;" id="urlTypeId" name="urlTypeId" class="" onchange="getSelectedText(this,'urlTypeName')">
					<option value="0" selected="selected">请选择类别</option>
					<option value=1>在线音乐</option>
					<option value=2>在线电影</option>
					<option value=3>视频分享</option>
					<option value=4>网络游戏</option>
					<option value=5>单机游戏</option>
					<option value=6>flash小游戏</option>
					<option value=7>QQ娱乐</option>
					<option value=8>文学小说</option>
					<option value=9>社区论坛</option>
					<option value=10>博客网站</option>
					<option value=11>BT下载</option>
					<option value=12>p2p共享</option>
					<option value=13>网吧资源</option>
					<option value=14>电脑网络</option>
					<option value=15>教育培训</option>
					<option value=16>英语培训</option>
					<option value=17>IT培训</option>
					<option value=18>动漫网站</option>
					<option value=19>育儿母婴</option>
					<option value=20>WAP服务</option>
					<option value=21>娱乐综合</option>
					<option value=22>交友网站</option>
					<option value=23>网址导航</option>
					<option value=24>电子杂志</option>
					<option value=25>女性网站</option>
					<option value=26>网上招聘</option>
					<option value=27>网上购物</option>
					<option value=28>地方门户</option>
					<option value=29>门户综合</option>
					<option value=30>科学资讯</option>
					<option value=31>餐饮旅游</option>
					<option value=32>软件下载</option>
					<option value=33>汽车资讯</option>
					<option value=34>服装鞋包</option>
					<option value=35>工商经济</option>
					<option value=36>工艺礼品</option>
					<option value=37>广告营销</option>
					<option value=38>化工产品</option>
					<option value=39>机械工业</option>
					<option value=40>家居家电</option>
					<option value=41>金融证券</option>
					<option value=42>轻工纺织</option>
					<option value=43>日用化妆</option>
					<option value=44>时尚美容</option>
					<option value=45>食品饮料</option>
					<option value=46>体育休闲</option>
					<option value=47>新闻媒体</option>
					<option value=48>休闲娱乐</option>
					<option value=49>医疗保健</option>
					<option value=50>仪器仪表</option>
					<option value=51>造纸印刷</option>
					<option value=52>增值业务</option>
					<option value=53>广告联盟</option>
					<option value=54>其它</option>
				</select><span style="color: red">*</span>
                  </td>
                  <td><div id="urlTypeIdTip" style="width:280px"></div></td>
                   </tr>
                   <tr>
                   <td  height="25" style="text-align: center;"></td>
					<td  style="text-align: center;">
					<span style="color: red;">${msg}</span>
				</td>
				<td  style="text-align: center;"></td>
				</tr>
				<tr>
				<td  height="45" style="text-align: center;"></td>
				<td  style="text-align: center;">
					<input id="button1" type="submit" class="button_btn" value="添加"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="button_btn" value="重置"/>
					
				</td>
				<td  style="text-align: center;"></td>
				</tr>
				</table>
		</form>
  </body>

</html>