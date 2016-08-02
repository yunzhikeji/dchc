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
		<title>人员信息统计列表</title>
	</head>
	<body>
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 人员信息统计
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</nav>

		<div class="pd-20">
			<div class="text-c">
				<form name="countForm" method="post" action="countAction!personCount"
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
							赌博
						</th>
						<th width="81">
							涉恶
						</th>
						<th width="107">
							涉黄
						</th>
						<th width="107">
							食药环
						</th>
						<th width="107">
							涉毒
						</th>
						<th width="107">
							负案在逃
						</th>
						<th width="107">
							留置盘问
						</th>
						<th width="107">
							侵财
						</th>
						<th width="107">
							刑事传唤
						</th>
						<th width="107">
							失踪
						</th>
						<th width="107">
							维稳
						</th>
						<th width="107">
							侵财分析
						</th>
						<th width="107">
							技术比中
						</th>
						<th width="107">
							社会
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
							<s:property value="personCounts.get(0)[0]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[1]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[2]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[3]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[4]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[5]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[6]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[7]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[8]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[9]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[10]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[11]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[12]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[13]"/>
						</td>
						<td>
							<s:property value="personCounts.get(0)[14]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							在办理
						</td>
						<td>
							<s:property value="personCounts.get(1)[0]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[1]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[2]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[3]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[4]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[5]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[6]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[7]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[8]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[9]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[10]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[11]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[12]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[13]"/>
						</td>
						<td>
							<s:property value="personCounts.get(1)[14]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							已完结
						</td>
						<td>
							<s:property value="personCounts.get(2)[0]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[1]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[2]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[3]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[4]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[5]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[6]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[7]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[8]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[9]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[10]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[11]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[12]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[13]"/>
						</td>
						<td>
							<s:property value="personCounts.get(2)[14]"/>
						</td>
					</tr>
					<tr class="text-c va-m">
						<td>
							合计
						</td>
						<td>
							<s:property value="personCounts.get(4)[0]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[1]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[2]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[3]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[4]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[5]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[6]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[7]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[8]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[9]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[10]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[11]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[12]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[13]"/>
						</td>
						<td>
							<s:property value="personCounts.get(4)[14]"/>
						</td>
					</tr>
					<tr class="text-c va-m" >
						<td style="color:red">
							超期办理
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[0]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[1]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[2]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[3]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[4]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[5]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[6]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[7]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[8]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[9]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[10]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[11]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[12]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[13]"/>
						</td>
						<td style="color:red">
							<s:property value="personCounts.get(3)[14]"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>