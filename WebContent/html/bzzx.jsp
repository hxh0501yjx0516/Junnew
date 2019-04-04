<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="www.junnew.com,版权所有，违版必究" />
<meta name="description" content="广告联盟 广告赚钱" />
<meta name="keywords" content="广告联盟 广告赚钱" />
<title>北京中润无线广告有限公司</title>
<%-- <link href="${pageContext.request.contextPath}/html/images/jnico.ico" rel="SHORTCUT ICON"> --%>
<link href="${pageContext.request.contextPath}/html/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/jquery.blockUI.js"></script>
<script type="text/javascript">
/**查看动态和公告**/
$(function(){
	$(".click").bind("click",function(){
		var name = $(this).attr("myvalue");
        $.blockUI({
            message: $("#content"+name), 
            css: { 
		        top:		'50%',
		        left:		'50%',
		        textAlign:	'left',
		        marginLeft:     '-320px', 
		        marginTop:      '-145px', 
                width: '600px',
                background:'none'
            } 
        }); 
		$('.blockOverlay').attr('title','单击关闭').click($.unblockUI); 
	 });
	});
</script>
</head>

<body>
<%@ include file="/html/include/top.jsp" %>

<div id="main">
	<!--flash begin-->
	<div id="flash">
    	<%@ include file="/html/include/flv.jsp" %> 
    </div>
	<!--flash end-->
    
	<!--help begin-->
	<div class="main_left">
	<div id="help">
    	<div class="top"><img src="${path}/html/images/ht1.jpg" width="716" height="51"></div>
        <div class="middle" >
        <ul >
        	<li><a class="click" href="javascript:void(0);" myvalue="1">· 联盟有哪些广告类型</a>
        	<div id="content1" style="display: none;" class="box">
        	<br />1.图片广告: 支持Flash文件广告,让广告更有特色,实时统计更新,模式包括通栏、横幅、画中画、按钮及旗帜等，有多种尺寸规格。
        	系统采用扁平化的项目管理方式，每个广告项目对应一个广告样式，广告主会员可以任意发布多条相同或不同广告地址、广告样式的广告，
        	系统默认采用的自动轮播的方式让你的网站更加美观。
        	<br />2.文字广告: 文字广告以文字形式较为详细的介绍广告主的广告产品，扩大企业或产品的知名度。
        	文字广告位的安排非常灵活，可以出现在页面的任何位置，可以任意排放。
        	<br />3.弹窗广告: 弹窗广告是互联网上最古老也最常用的网络推广形式之一。
        	随着网络广告的发展 ，弹窗广告虽然逐渐退位不再有那么多的倡导者，但弹窗广告依然广泛的应用于网站 、企业的产品快速推广和宣传。
        	在竞争激烈的今天，弹窗广告更是企业快速占领用户 和市场的手段之一，同时也是宣传成本较低的方法之一
        	<br />4.销售广告: 引导注册统既CPA计价方式是指按广告投放实际效果，即按回应的有效问卷或定单来计费，而不限广告投 放量。
        	 CPA的计价方式对于网站而言有一定的风险，但若广告投放成功，其收益也比CPM的计 价方式要大得多。
        	</div>
        	</li>
        	<li><a class="click" href="javascript:void(0);" myvalue="10">· 对联盟的网络广告产品不了解，怎么办？</a>
        	<div id="content10" style="display: none;" class="box">
        	请与本公司客户服务人员或管理员取得联系，向我们咨询您想要了解的情况，我们亦会通过传真或E-mail将本产品相关信息发给您，精心为您免费量身定制网络广告投放计划。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="2">· 网站主会员在什么情况下会被拒付广告费</a>
        	<div id="content2" style="display: none;" class="box">
        	<br />1、网站本身及广告违反了国家法律或含有恶性代码与病毒，及包含成人、性、色情、 淫秽、赌博、暴力、反动等等不健康的内容。 
        	<br />2、所投放广告的网页不属于会员拥有。 　　
        	<br />3、牵涉到知识产权纠纷的网站（如非法 MP3 、盗版软件网站，黑客站点，软件序列 号站点，注册机、注册码站点，或链接至这些网页的站点）。 　　
        	<br />4、链接、讨论或提供影响网络秩序的内容，如提供邮件炸弹、计算机病毒等的网站。 　　
        	<br />5、对作弊的帐户我们一旦查出，将拒绝支付当周全部的佣金，并锁定帐户。 　　
        	<br />6、被查出同一人注册多个帐号的网站。 　　
        	<br />7、使用非 HTML 手段、 JAVASRIPT 窗口、隐藏 FRAME 、CGI 自动生成、网页 REFRESH/AUTOLOAD 等手段来演绎广告代码。 　　
        	<br />8、不可由会员本人或指示、暗示第三方点击广告以获取非法广告费。
        	<br />9、页面存在3个或者3个以上弹窗者。
        	<br />10 色情站点，以及交换链插件网址导航站点！11 网站来源为不相关站点跳转流量。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="3">· 网站主会员审核标准是什么</a>
        	<div id="content3" style="display: none;" class="box">
        	<br />1. 网站本身及广告不能包含任何违反国家法律的内容。 
			<br />2. 网站本身及广告不能含有恶性代码及病毒，不能包含不健康的内容，如成人、性、色
			情、淫秽、赌博、暴力、反动等等。 
			<br />3. 必须有自己的国际顶级域名或国家顶级域名(如:http://www.abc.com)，原则上不通过
			使用二级域名或免费域名网站的审核。 
			<br />4. 对只有论坛或无实际内容或页面排版不够专业美观的网站一律不予通过。 
			<br />5. 每个网页的弹出窗口不得超过1个，不得自动弹出一次以上要求设为首页或加入收藏等
			类似对话框。如网站在通过审核后违反或不再符合以上标准中的任何一条，联盟将保留与其终止合作
			关系的权力。
        	</div>
        	</li>
        	<li><a class="click" href="javascript:void(0);" myvalue="4">· 什么时候付钱给我</a>
        	<div id="content4" style="display: none;" class="box">
        	当您的帐户余额超过我们指定的支付额时，我们会自动给你支付。
        	</div>
        	</li>
        	<li><a class="click" href="javascript:void(0);" myvalue="5">· 你们怎么付费给我</a> 
        	<div id="content5" style="display: none;" class="box">
        	我们目前通过银行汇款对客户结算，没有的会员请到当地免费办理。
        	</div>
        	</li>
        	<li><a class="click" href="javascript:void(0);" myvalue="6">· 我如何赚钱？</a>
        	<div id="content6" style="display: none;" class="box">
        	本站提供多种方法，供您获得广告点击。广告点击可以用来兑换人民币现金。
        	简单的说，只要有点击，就表示您已经赚到钱了
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="7">· 可否把广告代码放在多个页面里？或者同一个页面放多个广告代码？</a>
        	<div id="content7" style="display: none;" class="box">
        	为了增加弹窗/点击的机率，或是避免未知因素导致某页面 无法弹出窗口或无法点击，
        	您可以在多个页面上投放广告代码或在同一个页面投放多个广告代码。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="8">· 可不可以将代码放在空白页里？</a>
        	<div id="content8" style="display: none;" class="box">
        	不要将代码放在空白页面或是只有广告代码的页面！
        	因为这将导致广告主在查看 广告数据明细时看到空白页面或是只有广告代码页面而产生误会，从而导致广告主对您的投诉。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="9">· 什么是网站主</a>
        	<div id="content9" style="display: none;" class="box">
        	是广告交易双方的其中一方，即网站的拥有者，具有修改、新增、删除网站内容的权力，并承担相关法律责任的法人。
        	在自已网站上投放广告主的广告后，并按照本平台规定通过本平台收取佣金。
        	</div>
        	</li>
        </ul>
</div>
        <div class="bottom"><img src="${path}/html/images/infob.jpg" width="716" height="9"></div>
    </div>
	<div id="help" style="margin:20px 0px;">
    	<div class="top"><img src="${path}/html/images/ht1.jpg" width="716" height="51"></div>
        <div class="middle" style="height: 160px;">
           <ul >
        	<li><a class="click" href="javascript:void(0);" myvalue="11">· 广告主会员如何发布广告？</a>
        	<div id="content11" style="display: none;" class="box">
        	<br />第一步：注册成为联盟的广告主会员； 
			<br />第二步：选择您希望投放的广告模式并填写广告资料(如购买的广告投放数量、单价、最高点/弹比例、广告地址、广告类型等)； 
			<br />第三步：待媒介审核并通过广告资料后，您的广告就正式开始投放了。 
			<br />如果您有任何疑问，欢迎与我公司联系，如果您不熟悉操作的流程，您只需要告诉我们您
			的要求，我公司将免费为您定制全套的广告发布方案，最大限度的发挥广告宣传的有效性。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="12">· 关于广告主广告页面的规定</a>
        	<div id="content12" style="display: none;" class="box">
        	<br />1. 放置广告信息的弹窗/点击页面必须是真实页面，不得以任何形式将代码放置于非法
			页面上，也就是不能通过javascript、ifram、js等已知或未知的方式在其它页面调出，如有
			此类情况一经查实，我们将撤销该广告并清除该广告剩余投放金额。 
			　　<br />2. 放置广告信息的弹窗页面不能再有弹出窗口，否则广告主广告将被暂停并清除该广告
			剩余投放金额。 
			　<br />3. 不允许弹出超过一次以上将广告页面设为主页和设为收藏的对话框，否则广告主广告
			将被暂停并清除该广告剩余投放金额
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="13">· 广告正式投放之后还能进行修改吗？</a>
        	<div id="content13" style="display: none;" class="box">
			　　<br />广告主可实时针对自己营销策略的变更而修改广告内容，如增加广告投放数量、改变单价、改变链接地址等。 
			　　注：新增加的广告在通过管理员审核前可以随意修改内容，正在投放中的广告修改后需管
			理员审核
        	</div>
        	  </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="14">· 如何评价网络广告效果</a>
        	<div id="content14" style="display: none;" class="box">
			　　网络广告效果最直接评价标准是弹窗次数和点击率，即有多少人看到了此广告，并且又有
			多少人对此广告感兴趣而点击了该广告。
			<br />可能大多广告主比较看中点击率，但关于 Banner 显示时所带来的品牌传播作用，广告站点与广告主之间一直存在长期的争论。
			<br />在直销型电子商务站点，最终的效果评价无疑是在线销售额的增长，因为 Web 服务器端的跟踪程序能判断任何一
			笔销售的买主是从哪个站点链接过来的。
        	</div>
        	 </li>
        	<li><a class="click" href="javascript:void(0);" myvalue="15">· 广告数据多久统计一次</a> 
        	<div id="content15" style="display: none;" class="box">
        	<br />实时统计。
			<br />通过本系统提供的精确详细的数据及直观的图表统计，广告主会员可以实时查看到每天的广告
			数据及历史详细数据，可以查询到每天有多少人看了您的广告(可依据弹窗数、点击数)，显示您的产品在各个时间段的受众状况。	
        	</div>
        	 </li>
        </ul>
       </div>
        <div class="bottom"><img src="${path}/html/images/infob.jpg" width="716" height="9"></div>
    </div>
    </div>
    <!--help end-->
    <div class="main_right">
    <!--用户登录 begin-->
    <div id="users">
    	<%@ include file="/html/include/login.jsp" %>
    </div>
    <!--用户登录 end-->
        
    <!--客服 begin-->
  <div id="service" style=" margin:0px;">
   	<%@ include file="/html/include/service.jsp" %>
    </div>
    <!--客服 end-->
	</div>

</div>

<%@ include file="/html/include/bottom.jsp" %>
</body>
</html>