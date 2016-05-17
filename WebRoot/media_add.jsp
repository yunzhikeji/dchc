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
		<title>视频截图</title>
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
		<form name="mediaAddForm" action="mediaAction!add" method="post"
			enctype="multipart/form-data" onsubmit="">
			<input type="hidden" name="media.injurycase.id" value="${inid}" />
			<input type="hidden" name="media.mtype" value="${mtype}" />
			<div class="pd-20">
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							媒体名称：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="number" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="media.title"></s:textfield>
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
							cssStyle="width: 260px; height: 180px; float: left;"></s:textarea>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							上传时间：
						</label>
					</div>
					<div class="col-4">
						<input type="text" name="media.uptime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
							id="logmin" class="input-text Wdate" style="width: 200px;">
					</div>
				</div>

				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							视频：
						</label>
					</div>
					<div class="col-10">
						<div class="col-3">
							<s:file name="picture1" accept="video/*" onchange="change1();"
								id="myfile1"></s:file>
							<script type="text/javascript">
								function change1() {
								   var     file1 = document.getElementById("myfile1");
								    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
								     // gif在IE浏览器暂时无法显示
								     if(ext1!='mp4'&&ext1!='avi'){
								         alert("视频的格式必须为mp4或者avi！");
								         file1.value=""; 
								         return;
								     }
								     }
								</script>
						</div>
					</div>
				</div>
				<div class="row cl">
					<s:token></s:token>
					<div class="col-10 col-offset-2">
						<input type="submit" class="btn btn-primary radius" value="保存并提交" />
						<button onclick="window.close();"
							class="btn btn-default radius" type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
		</form>
	</body>
</html>
