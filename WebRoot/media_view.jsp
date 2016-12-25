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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>查看媒体视频</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
	</head>

	<body>
		<div class="pd-20">
			<div class="row cl mb-10">
				<div class="col-2">
					<label class="form-label text-r">
						媒体名称：
					</label>
				</div>
				<div class="col-4">
					<s:textfield id="number" cssClass="input-text radius size-M"
						cssStyle="width:200px;" name="media.title" readonly="true"></s:textfield>
				</div>
			</div>
			<div class="row cl mb-10">
				<div class="col-2">
					<label class="form-label text-r">
						媒体描述：
					</label>
				</div>
				<div class="col-4">
					<s:textarea name="media.descript" cssClass="input-text"
						cssStyle="width: 260px; height: 180px; float: left;"
						readonly="true"></s:textarea>
				</div>
			</div>

			<div class="row cl mb-10">
				<div class="col-2">
					<label class="form-label text-r">
						上传时间：
					</label>
				</div>
				<div class="col-4">
					<s:textfield id="number" cssClass="input-text radius size-M"
						cssStyle="width:200px;" name="media.uptime" readonly="true"></s:textfield>
				</div>
			</div>

			<div class="row cl mb-10">
				<div class="col-2">
					<label class="form-label text-r">
						视频：
					</label>
				</div>
				<div class="col-10">
					<video src="${media.src}" controls="controls">
							您的浏览器不支持 video 标签。
					</video>
				</div>
			</div>
	</body>
</html>
