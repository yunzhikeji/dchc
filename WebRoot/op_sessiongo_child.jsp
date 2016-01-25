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
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
<title>消息提示页面</title>
	</head>
	<body>
		<div class="pd-20">
			<div class="cl pd-5 bg-1 bk-gray Huialert Huialert-info" >
				<table  class="table table-border table-bordered table-bg ">
                <thead>
					<tr>
						<th >
							<span>用户操作消息：</span>
						</th>
					</tr>
                    </thead>
                    <tbody>
					 <tr bgcolor="#FFFFFF">
						<td>
							会话时间过长，session丢失，请重新登陆.   <button onclick="childPage_close();" class="btn btn-success radius" type="button">关闭并退出</button>
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
						<td  bgcolor="#FFFFFF">
							感谢您的使用：
						</td>
						<td  bgcolor="#FFFFFF">
							${session.userRoleo.username}
						</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td>
							系统版本信息：
						</td>
						<td>
							<span>德城公安合成平台v1.0</span>
						</td>
					</tr>
				</table>

			</div>

		</div>
	</body>
</html>