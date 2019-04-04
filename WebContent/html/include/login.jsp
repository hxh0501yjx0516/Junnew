<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
function onLogin(){
		if(document.getElementById("username").value == ""){
			document.getElementById("msg").innerHTML = "用户名不能为空！";
		}else if(document.getElementById("userpwd").value == ""){
			document.getElementById("msg").innerHTML = "密码不能为空！";
		}else if(document.getElementById("randCode").value == ""){
			document.getElementById("msg").innerHTML = "验证码不能为空！"; 
		}else{
		document.loginForm.action="login.do?action=login";
		document.loginForm.submit();
		}
	}
	function onRegister(){
		location.href = "register.do?action=forward";
	}
//-->
</script>
<c:set var="path" value="${pageContext.request.contextPath}"/>
    	<div class="top" ><img src="${path}/html/images/usertop.jpg"  width="230" height="60"></div>
        <div class="middle" >
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
        	    <a href="javascript:void(0);" onclick="getRandCode('randImg');return false;" title="刷新验证码" ><img  id="randImg" src="${path}/html/include/checkCode.jsp" style="float: right;padding-right: 13px;border: 0px solid red;"/></a>
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
        <div class="bottom"><img src="${path}/html/images/newb.jpg" width="230" height="6"></div>
