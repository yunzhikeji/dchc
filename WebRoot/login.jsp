<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<title>德城合成平台登陆界面</title>
<meta name="keywords" content="">
<meta name="description" content="">

<script type="text/javascript">
//解决页面嵌套问题
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
<script type="text/javascript">
 
 //设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
//清除cookie  
function clearCookie(name) {  
    setCookie(name, "", -1);  
    
}  
function checkCookie() {
    var user = getCookie("username");
    if (user != "") {
        alert("Welcome again " + user);
    } else {
        user = prompt("Please enter your name:", "");
        if (user != "" && user != null) {
            setCookie("username", user, 365);
        }
    }
}
//checkCookie(); 


function loadthis(){
	var checkup = getCookie("checkup");
	console.log(checkup);
	if(checkup=='on'){
		document.getElementById("username").value=getCookie("username");
		document.getElementById("password").value=getCookie("password");
		document.getElementById("checkup").checked=true;
	}else{
		document.getElementById("username").value="";
		document.getElementById("password").value="";
		document.getElementById("checkup").checked=false;
	}
}

function checkIsUp(){
	 if(document.getElementById("checkup").checked==true){
		setCookie('username', document.getElementById("username").value, 365);	 
		setCookie('password', document.getElementById("password").value, 365);	 
		setCookie('checkup', document.getElementById("checkup").value, 365);	 
	 }else{
	 	clearCookie("username");
		clearCookie("password");
		clearCookie("checkup");
	 }
	 return true;
 }
 </script>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form name="userForm" class="form form-horizontal" action="userRoleAction!login" method="post">
     <div class="row cl">
         <div class="col-3"></div>
         <div class="col-8">
         
             <div class="row cl">
             <div style="height: 30px;">
							<span style="color:#ff0000;font-size:16px;margin-left:100px;">${loginFail }</span><br />
						</div>
        <label class="form-label col-3" style="line-height:30px;"><i class="Hui-iconfont">&#xe60d;</i></label>
        
        <div class="formControls col-5">
          <input id="" name="username" type="text" placeholder="账户" class="input-text size-m" style="width:250px;">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3 " style="line-height:30px;"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-5">
          <input id="" name="password" type="password" placeholder="密码" class="input-text size-m" style="width:250px;">
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
          <input name="checkup" id="checkup" type="checkbox"
								checked="checked" style="margin-bottom: 8px;" />
            记住密码</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
         
         </div>
      
     </div>
    
    
      
    </form>
  </div>
</div>
<div class="footer">Copyright 德城公安 by 江苏云知智能科技有限公司</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>