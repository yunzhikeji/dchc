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
		<title>查看所有媒体视频</title>
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
			<div class="later-play-container" style="height: 680px">
				<ul id="later-play-list" class="video-list clearfix" data-total="">
					<s:iterator value="medias" var="media" status="status">
						<li class="video-list-item video-no-tag " data-id="66666">
							<div class="video-cover">
								<img class="video-img video-img-lazy"
									src="<s:property value="src"/>"
									style="border: 1px solid rgb(100, 100, 100); display: block;"
									alt="暂无图片">
							</div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</body>
</html>
