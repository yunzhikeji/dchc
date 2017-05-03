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
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<title>消息提示页面</title>
		<script type="text/javascript">     
		function countDown(secs,surl){     
		 //alert(surl);     
		 surl = surl.replace("&amp;","&");
		 var jumpTo = document.getElementById('jumpTo');
		 jumpTo.innerHTML=secs;  
		 if(--secs>0){
		     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
		     }
		 else{       
		     location.href=surl;     
		     }     
		 }     
		</script>
	</head>
	<body>
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 消息提示页面
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</nav>
		<div class="pd-20">
			<div class="cl pd-5 bg-1 bk-gray Huialert Huialert-info" >
				<table  class="table table-border table-bordered table-bg ">
                <thead>
					<tr>
						<th >
							<span>用户操作消息:</span>
						</th>
					</tr>
                    </thead>
                    <tbody>
					 <tr >
						<td>
							信息提交失败,请刷新界面.&nbsp;
						</td>
					</tr>
                    </tbody>
				</table>

				<table class="table  table-bg ">
                <thead>
					<tr bgcolor="#EEF4EA">
						<th colspan="2"  >
							<span>系统基本信息</span>
						</th>
					</tr>
                    </thead>
					<tr bgcolor="#FFFFFF">
						<td width="10%" bgcolor="#FFFFFF">
							感谢您的使用：
						</td>
						<td width="90%" bgcolor="#FFFFFF">
							${session.currentUserRole.username}
						</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td>
							系统版本信息：
						</td>
						<td>
							德城公安合成平台v1.0
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>