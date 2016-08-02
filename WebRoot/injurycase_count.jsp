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
		<title>案件信息统计列表</title>
	</head>
	<body>
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 案件信息统计
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</nav>

		<div class="pd-20">
			<div class="text-c">
				<form name="countForm" method="post" action="countAction!injurycaseCount"
					target="_self">
					<s:hidden name="type"></s:hidden>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="line-height: 35px;">
						<tr height="35">
							<td width="21%" align="right" style="padding-right: 50px;">
								<s:select
									list="#{0:'选择类型',1:'录入人员姓名',2:'录入人员警号'}"
									cssClass="input-text" name="con" listKey="key"
									listValue="value" cssStyle="width:180px"></s:select>
							</td>
							<td width="22%">
								<s:textfield name="convalue" id="convalue" cssClass="input-text"></s:textfield>
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
			<table class="table table-border table-bordered table-hover table-bg"
				style="margin-top: 20px;">
				<thead>
					<tr class="text-c">
						<th width="41">
						</th>
						<th width="109">
							一般案件
						</th>
						<th width="81">
							重伤案件
						</th>
						<th width="107">
							团伙系列案件
						</th>
						<th width="107">
							合计
						</th>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c va-m">
						<td>
							未办理
						</td>
						<td>
							<s:property value="injurycaseCounts.get(0)[0]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(0)[1]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(0)[2]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(0)[3]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							在办理
						</td>
						<td>
							<s:property value="injurycaseCounts.get(1)[0]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(1)[1]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(1)[2]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(1)[3]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							已完结
						</td>
						<td>
							<s:property value="injurycaseCounts.get(2)[0]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(2)[1]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(2)[2]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(2)[3]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							合计
						</td>
						<td>
							<s:property value="injurycaseCounts.get(4)[0]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(4)[1]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(4)[2]"/>
						</td>
						<td>
							<s:property value="injurycaseCounts.get(4)[3]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td style="color: red;">
							超期办理
						</td>
						<td style="color: red;">
							<s:property value="injurycaseCounts.get(3)[0]"/>
						</td>
						<td style="color: red;">
							<s:property value="injurycaseCounts.get(3)[1]"/>
						</td>
						<td style="color: red;">
							<s:property value="injurycaseCounts.get(3)[2]"/>
						</td>
						<td style="color: red;">
							<s:property value="injurycaseCounts.get(3)[3]"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>