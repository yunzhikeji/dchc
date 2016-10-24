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
		<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>全局搜索列表</title>
	</head>
	<body>
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>全局搜索列表
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</nav>

		<div class="pd-20">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a onclick="javascript:history.go(-1)"
					class="btn btn-warning radius"><i class="Hui-iconfont">&#xe678;</i>
						返回</a> </span>
				<span class="r">共有数据：<strong><s:property
							value="totalCount" /> </strong> 条</span>
			</div>
			<div class="mt-20">
				<table
					class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="80">
								编号
							</th>
							<th width="80">
								名称
							</th>
							<th width="120">
								类型
							</th>
							<th width="120">
								上报民警
							</th>
							<th width="120">
								上报单位
							</th>
							<th width="120">
								办结类型
							</th>
							<th width="120">
								上报时间
							</th>
							<th width="120">
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="persons" var="person" status="status">
							<tr class="text-c">
								<td>
									<s:property value="number" />
								</td>
								<td class="text-l">
									<s:property value="name" />
								</td>
								<td>
									<s:if test="type==1">赌博人员</s:if>
									<s:if test="type==2">涉恶人员</s:if>
									<s:if test="type==3">涉黄人员</s:if>
									<s:if test="type==4">食药环人员</s:if>
									<s:if test="type==5">涉毒人员</s:if>
									<s:if test="type==6">留置盘问</s:if>
									<s:if test="type==7">侵财人员</s:if>
									<s:if test="type==8">刑事传唤</s:if>
									<s:if test="type==9">负案在逃人员</s:if>
									<s:if test="type==10">维稳人员</s:if>
									<s:if test="type==11">失踪人员</s:if>
									<s:if test="type==12">侵财人员分析</s:if>
									<s:if test="type==13">技术比中人员</s:if>
									<s:if test="type==14">普通线索</s:if>
									<s:if test="type==15">社会人员</s:if>
								</td>
								<td>
									<s:property value="userRole.realname" />
								</td>
								<td class="f-14 td-manage">
									<s:property value="userRole.unit.name" />
								</td>
								<td>
									<s:if test="handleState==0">未办理</s:if>
									<s:if test="handleState==1">未办理</s:if>
									<s:if test="handleState==2">在办理</s:if>
									<s:if test="handleState==3">已完结</s:if>

								</td>
								<td class="f-14 td-manage">
									<s:property value="joinDate" />
								</td>
								<td class="td-manage">
									<a style="text-decoration: none" class="ml-5"
										onclick="childPageFull('编辑人员','personAction!load?id=<s:property value="id"/>&type=<s:property value="type" />')"
										href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
									</a>

									<s:if test="#session.userRoleo.userLimit>0">
										<a style="text-decoration: none" class="ml-5"
											href="personAction!delete?id=<s:property value="id" />&type=<s:property value="type" />"
											onclick="return confirm('你确定删除该信息吗？')" title="删除"><i
											class="Hui-iconfont">&#xe6e2;</i> </a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
						<s:iterator value="injurycases" var="injurycase" status="status">
							<tr class="text-c">
								<td>
									<s:property value="caseNumber" />
								</td>
								<td class="text-l">
									<s:property value="caseName" />
								</td>
								<td>
									<s:if test="itype==1">一般刑事案件</s:if>
									<s:if test="itype==2">重伤案件</s:if>
									<s:if test="itype==3">团伙系列案件</s:if>
									<s:if test="itype==4">刑侦案件</s:if>
								</td>
								<td>
									<s:property value="userRole.realname" />
								</td>
								<td class="f-14 td-manage">
									<s:property value="userRole.unit.name" />
								</td>
								<td class="f-14 td-manage">
									<s:if test="handleState==0">未办理</s:if>
									<s:if test="handleState==1">未办理</s:if>
									<s:if test="handleState==2">在办理</s:if>
									<s:if test="handleState==3">已完结</s:if>
								</td>
								<td class="f-14 td-manage">
									<s:property value="joinDate" />
								</td>
								<td class="td-manage">
									<a style="text-decoration: none" class="ml-5"
										onclick="childPageFull('编辑案件','injurycaseAction!load?id=<s:property value="id"/>&itype=<s:property value="itype" />')"
										href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
									</a>
									<s:if test="#session.userRoleo.userLimit>0">
										<a style="text-decoration: none" class="ml-5"
											href="injurycaseAction!delete?id=<s:property value="id" />&itype=<s:property value="itype" />"
											onclick="return confirm('你确定删除该信息吗？')" title="删除"><i
											class="Hui-iconfont">&#xe6e2;</i> </a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
	</body>
</html>