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
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet"
			href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>线索信息列表</title>
	</head>
	<body>
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		<s:property value="pageTileName" />
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</nav>

		<div class="pd-20">
			<div class="text-c">
				<form name="clueListForm" method="post" action="clueAction!list"
					target="_self">
					<s:hidden name="ctype"></s:hidden>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="line-height: 35px;">
						<tr height="35">
							<td width="21%" align="right" style="padding-right: 50px;">
								<s:select
									list="#{0:'选择类型',1:'线索编号',2:'密级',3:'线索情报类型',4:'录入人员姓名'}"
									cssClass="input-text" name="con" listKey="key"
									listValue="value" cssStyle="width:180px"></s:select>
							</td>
							<td width="22%">
								<s:textfield name="convalue" id="convalue" cssClass="input-text"></s:textfield>
							</td>
							<td align="left" style="padding-left: 50px;" >
								信息状态:
								<s:select list="#{0:'全部',1:'未办理',2:'在办理',3:'已完结'}"
									cssClass="input-text" name="queryState" listKey="key"
									listValue="value" cssStyle="width:180px"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								录入时间：
								<input type="text" name="starttime"
									value="<s:property value="starttime"/>"
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')||\'%y-%M-%d\'}'})"
									id="logmin" class="input-text Wdate" style="width: 150px;">
								-
								<input type="text" name="endtime"
									value="<s:property value="endtime"/>"
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'%y-%M-%d'})"
									id="endtime" class="input-text Wdate" style="width: 150px;">
							</td>
							<td align="left" style="padding-left: 172px;">
								<button type="submit" class="btn btn-success" id="button2"
									name="" onClick="">
									<i class="Hui-iconfont">&#xe665;</i> 查询
								</button>
								<button type="button" class="btn btn-success" id="button"
									name="">
									<i class="Hui-iconfont">&#xe66b;</i> 清空
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<s:if test="#session.userRoleo.userLimit>0">
				<a href="javascript:;"
					onclick="deleteAllCheckedPersons();" class="btn btn-danger radius">
					<i
						class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
						
						</s:if> <a
					class="btn btn-primary radius"
					onclick="childPageFull('新增<s:property value="pageTileName"/>','clueAction!goToAdd?ctype=<s:property value="ctype"/>')"
					href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增<s:property
							value="pageTileName" />
				</a> </span>
				<span class="r">共有数据：<strong><s:property
							value="totalCount" /> </strong> 条</span>
			</div>
			<table class="table table-border table-bordered table-hover table-bg">
				<thead>
					<tr class="text-c">
						<th width="41">
							<input type="checkbox" name="" value="">
						</th>
						<th width="70">
							序号ID
						</th>
						<th width="71">
							线索编号
						</th>
						<th width="81">
							线索类型
						</th>
						<th width="109">
							录入单位
						</th>
						<th width="107">
							录入民警
						</th>
						<th width="103">
							录入时间
						</th>
						<th width="81">
							办理状态
						</th>
						<th width="79">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="clues" var="clue" status="status">
						<tr class="text-c va-m">
							<td>
								<input name="indexID" class="indexID" type="checkbox"
									value="<s:property value="id"/>">
							</td>
							<td>
								<s:property value="#status.index+1+(page-1)*10" />
							</td>
							<td>
								<s:property value="number" />
							</td>
							<td>
								<s:if test="ctype==1">刑侦线索</s:if>
							</td>
							<td>
								<s:property value="userRole.unit.name" />
							</td>
							<td>
								<s:property value="userRole.realname" />
							</td>
							<td>
								<s:property value="joinDate" />
							</td>
							<td>
								<s:if test="handleState==0">未办理</s:if>
								<s:if test="handleState==1">未办理</s:if>
								<s:if test="handleState==2">在办理</s:if>
								<s:if test="handleState==3">已完结</s:if>

							</td>
							<td class="td-manage">
								<a style="text-decoration: none" class="ml-5"
									onclick="childPageFull('编辑线索','clueAction!load?id=<s:property value="id"/>')"
									href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
								</a>
								<s:if test="#session.userRoleo.userLimit>0">
								<a style="text-decoration: none" class="ml-5"
									href="clueAction!delete?id=<s:property value="id" />"
									onclick="return confirm('你确定删除该信息吗？')" title="删除"><i
									class="Hui-iconfont">&#xe6e2;</i> </a>
								</s:if> 
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<ul class="forminfo" style="line-height: 40px; font-size: 14px;">
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
					<tr align="right" bgcolor="#EEF4EA">
						<td height="34" align="center" bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
							<a
								href="javascript:jumpPersonPage('clueAction!list',1,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="ctype"/>,<s:property value="queryState"/>,'<s:property value="starttime"/>','<s:property value="endtime"/>');"
								target="rightFrame">首页</a>&nbsp;&nbsp;
							<a
								href="javascript:jumpPersonPage('clueAction!list',<s:property value="page-1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="ctype"/>,<s:property value="queryState"/>,'<s:property value="starttime"/>','<s:property value="endtime"/>');"
								target="rightFrame">上一页</a>&nbsp;&nbsp;&nbsp;
							<a
								href="javascript:jumpPersonPage('clueAction!list',<s:property value="page+1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="ctype"/>,<s:property value="queryState"/>,'<s:property value="starttime"/>','<s:property value="endtime"/>');"
								target="rightFrame">下一页</a>&nbsp;&nbsp;&nbsp;
							<a
								href="javascript:jumpPersonPage('clueAction!list',<s:property value="pageCount"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="ctype"/>,<s:property value="queryState"/>,'<s:property value="starttime"/>','<s:property value="endtime"/>');"
								target="rightFrame">尾页</a>&nbsp;&nbsp;&nbsp;
							<input type='button' class="btn btn-primary radius size-S"
								onclick="jumpPersonPage('clueAction!list',document.getElementById('page').value,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="ctype"/>,<s:property value="queryState"/>,'<s:property value="starttime"/>','<s:property value="endtime"/>');"
								value='转到' />
							&nbsp; 当前页：
							<input onpaste="return false"
								onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (! /^\d+$/ig.test(this.value)){this.value='';}}"
								id="page" type="text" name="page"
								value="<s:property value="page" />" size="2"
								style="width: 25px; height: 20px; line-height: 18px; BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #cccccc 1px solid; COLOR: #000000; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff;" />
							/共
							<s:property value="pageCount" />
							页
						</td>
					</tr>
				</table>
			</ul>
		</div>
	</body>
</html>