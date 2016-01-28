﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/ncss.css" rel="stylesheet" type="text/css" />
<link href="skin/blue/skin.css" rel="stylesheet" type="text/css"  />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>管理系统</title>
<meta name="keywords" content="">
</head>
<body>
<header class="Hui-header cl">
 <a class="Hui-logo l" href="#"><img src="images/logo.png" width="489" height="70"></a>  
<ul class="nav" style="margin-right:30px;">
    <li><a data-toggle="modal" href="#search"  title="一键搜索"><img src="images/icon02.png" title="一键搜索" /><h4>一键搜索</h4></a></li>
    <li><a href="javascript:;" onClick="" title="待办任务"><img src="images/icon05.png" title="待办任务" /><h4>待办任务</h4></a></li>
    <li><a href="javascript:;" onClick="ypgj('研判工具','ypgj.html','500px','300px')" title="研判工具"><img src="images/icon04.png" title="研判工具" /><h4>研判工具</h4></a></li>
    <li><a href="javascript:;" onClick="location='userRoleAction!logout'" title="注销"><img src="images/icon06.png" title="注销" /><h4>注销</h4></a></li>
    </ul>
    
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
<aside class="Hui-aside" style="overflow-y:auto; overflow-x:hidden; overflow-y:hidden;">
	<div class="menu_dropdown bk_2">
    <div class="my-profile dropDown dropDown_click "  >
						<a href="javascript:;" class="my-profile-pic dropDown_A" aria-expanded="false" aria-haspopup="true" data-toggle="dropdown">
							<img src="<%=basePath%>${session.userRoleo.photo}" alt="暂无照片" />
						</a>
                        <div class="dropDown-menu box-shadow text-c pt-10 mr-10">
                               <ul style=" color:#333; text-shadow:none">
                               <p>所属机构：</p>
                               <p>${session.userRoleo.unit.name}</p>
                               </ul>
                          </div>
						<span class="first-child">您好 ，<strong>${session.userRoleo.username}</strong></span>
						<span>警号：${session.userRoleo.number}</span>
	</div>
   
    <div class="divider"></div>
    
    <ul class="ce">
		  <li><a href="#"><i class="Hui-iconfont ">&#xe62c;</i> 涉案人员<img class="more" src="images/more.png"/></a>
			   <ul class="er" style="display:block;">
				 <li class="e_li"> <a href="#">治安人员<i class="Hui-iconfont menu_dropdown-arrow f-r" style="margin-right:20px;">&#xe6d5;</i></a> 
					<ul class="thr">
						<li> <a _href="personAction!list?type=1" href="javascript:void(0)">赌博人员</a> </li>
						<li> <a _href="personAction!list?type=2" href="javascript:void(0)">涉恶人员</a></li>
						<li> <a _href="personAction!list?type=3" href="javascript:void(0)">涉黄人员</a></li>
						<li> <a _href="personAction!list?type=4" href="javascript:void(0)">食药环人员 </a></li>
						<div class="clear"></div>
					</ul>
				 </li>
				<li class="e_li"> <a  href="#">刑事人员<i class="Hui-iconfont menu_dropdown-arrow f-r" style="margin-right:20px;">&#xe6d5;</i></a> 
					<ul class="thr">
                         <li><a _href="drug-list.html" href="javascript:void(0)">涉毒人员</a></li>
						<li> <a _href="run-list.html" href="javascript:void(0)">负案在逃人员</a></li>
						<li> <a _href="personAction!list?type=6" href="javascript:void(0)">留置盘问人员</a> </li>
						<li> <a _href="goods-list.html" href="javascript:void(0)">侵财人员</a> </li>
						<li> <a _href="summon-list.html" href="javascript:void(0)">刑事传唤人员</a> </li>
						<div class="clear"></div>
					</ul>
				</li>
                <li><a _href="lost-list.html" href="javascript:void(0)">失踪人员</a></li>
                <li><a _href="stability-list.html" href="javascript:void(0)">维稳人员</a></li>
                <li><a _href="goodsman-list.html" href="javascript:void(0)">侵财人员分析</a></li>
                <li><a _href="jishu-list.html" href="javascript:void(0)">技术比中人员</a></li>
			   </ul>
		  </li>
          <li><a href="#"><i class="Hui-iconfont ">&#xe665;</i> 线索管理<img class="more" src="images/more.png"/></a>
			   <ul class="er">
				 <li><a _href="clues-list.html" href="javascript:void(0)">普通线索</a></li>
				 <li><a _href="inves-list.html" href="javascript:void(0)">刑侦线索</a></li>
			   </ul>
		  </li>
          <li><a href="#"><i class="Hui-iconfont ">&#xe636;</i> 案件管理<img class="more" src="images/more.png"/></a>
			   <ul class="er">
				 <li><a _href="gangs-list.html" href="javascript:void(0)">团伙系列案件</a></li>
				 <li><a _href="hurt-list.html" href="javascript:void(0)">重伤案件</a></li>
			   </ul>
		  </li>          
		  <li><a href="#"><i class="Hui-iconfont ">&#xe61d;</i> 系统管理<img class="more" src="images/more.png"/></a>
			   <ul class="er">
					<li> <a _href="unitAction!list" href="javascript:void(0)">机构管理</a> </li>
					<li> <a _href="userRoleAction!list" href="javascript:void(0)">角色管理</a> </li>
					<li> <a _href="change-password.html" href="javascript:void(0)">个人资料</a> </li>
					<li> <a _href="system-base.html" href="javascript:void(0)">IP限制</a> </li>
                    <li> <a _href="notice-list.html" href="javascript:void(0)">通知公告</a> </li>
			   </ul>
		  </li>
		  <div class="clear"></div>
</ul>
	</div>
    

</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)" ></a></div>
<section class="Hui-article-box">
  <div id="iframe_box" class="Hui-article">
	  <div class="show_iframe">
			<div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
	  </div>
	</div>
  <div id="Hui-tabNav" class="Hui-tabNav">
    <div class="Hui-tabNav-wp">
      <ul id="min_title_list" class="acrossTab cl">
        <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
      </ul>
    </div>
    <div class="Hui-tabNav-more btn-group"> <a id="js-tabNav-del"   class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6a6;</i></a> <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a> <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
  </div>
</section>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript" src="lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript">

/*研判工具*/
function ypgj(title,url,w,h){
	layer.open({
    type: 2,
    title: title,
    shadeClose: true,
    shade: 0.8,
    area: [w, h],
    content: url //iframe的url
}); 
}

 

</script> 

<div id="yyp" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel" style="height:30px;"><i class="Hui-iconfont" style="font-size:40px">&#xe6b1;</i>云研判</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
 </div>
  <div class="modal-body">
 <p>
 <input type="text" placeholder="请输入关键字" class="input-text radius size-s"></p>
 </div>
  <div class="modal-footer">
 <button class="btn btn-primary">确定</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
 </div>
</div>

<div id="search" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel" style="height:30px;"><i class="Hui-iconfont" style="font-size:40px">&#xe665;</i>一键搜索</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
 </div>
  <div class="modal-body">
 <p>
 <input type="text" placeholder="请输入关键字" class="input-text radius size-s"></p>
 </div>
  <div class="modal-footer">
 <button class="btn btn-primary">确定</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
 </div>
</div>

<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
</body>
</html>