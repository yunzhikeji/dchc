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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>全局搜索列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页<span class="c-gray en">&gt;</span>全局搜索列表
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
     
</nav>


<div class="pd-20">
  <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a onclick="javascript:history.go(-1)" class="btn btn-warning radius"><i class="Hui-iconfont">&#xe678;</i> 返回</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="80">流水编号</th>
					<th>标题</th>
					<th width="120">上报单位</th>
					<th width="120">信息状态</th>
					<th width="120">当前环节时间</th>
					<th width="120">办结类型</th>
					<th width="120">上报民警</th>
					<th width="120">上报时间</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>10001</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看">资讯标题</u></td>
					<td>城北派出所</td>
					<td>21212</td>
					<td class="f-14 td-manage"></td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">张然</td>
					<td class="f-14 td-manage">2014-6-11</td>
					<td class="f-14 td-manage">&nbsp;</td>
				</tr>
                <tr class="text-c">
					
					<td>10001</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看">资讯标题</u></td>
					<td>城北派出所</td>
					<td>21212</td>
					<td class="f-14 td-manage"></td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">&nbsp;</td>
					<td class="f-14 td-manage">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>

</body>
</html>