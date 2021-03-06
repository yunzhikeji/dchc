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
		<title>修改文件信息</title>
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
		<script type="text/javascript" src="js/checkUtil.js"></script>
	</head>

	<body>
		<form name="mediaUpdateForm" action="mediaAction!update" method="post"
			enctype="multipart/form-data" onsubmit="return checkMedia();">
			<s:hidden name="media.id" />
			
			<c:if test="${media.injurycase!=null}">
			<s:hidden name="media.injurycase.id" />
			</c:if>
			
			
			<c:if test="${media.judge!=null}">
			<s:hidden name="media.judge.id" />
			</c:if>
			
			<s:hidden name="media.mtype" />
			<s:hidden name="media.src" />
			<div class="pd-20">
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							文件名称：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="title" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="media.title"></s:textfield>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							文件描述：
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
							value="<s:property value="media.uptime"/>"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
							id="logmin" class="input-text Wdate" style="width: 200px;">
					</div>
				</div>

				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							文件：
						</label>
					</div>
					<div class="col-10">
						<div class="col-3">
							<s:file name="file"  onchange="change1();"
								id="myfile1"></s:file>
							<script type="text/javascript">
								function change1() {
								   var     file1 = document.getElementById("myfile1");
								    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
								     // gif在IE浏览器暂时无法显示
								     if(ext1!='xlsx'&&ext1!='xls'&&ext1!='mp4'&&ext1!='avi'&&ext1!='doc'&&ext1!='docx'&&ext1!='jpg'&&ext1!='png'){
								         alert("文件格式不正确！");
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
						<button onclick="childPage_close();"
							class="btn btn-default radius" type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
		</form>
	</body>
</html>
