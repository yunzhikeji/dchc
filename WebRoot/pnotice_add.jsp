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
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript"
			src="lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
		<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="pnotice.article"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
		<title>新增通知公告</title>
	</head>
	<body>
		<div class="pd-20">
			<form name="pnoticeAddForm" action="pnoticeAction!add" method="post"
				class="form form-horizontal" id="form-article-add" onsubmit="">
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>标题：
					</label>
					<div class="formControls col-3">
						<s:textfield id="title" name="pnotice.title"
							cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						作者：
					</label>
					<div class="formControls col-3">
						<input type="text" id="author" name="pnotice.author"
							value="${session.userRoleo.realname}"
							class="input-text radius size-M " style="width: 200px;" />
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						填写时间：
					</label>
					<div class="formControls col-3">
						<input type="text" name="pnotice.releaseTime"
							style="width: 200px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
							id="releaseTime" class="input-text Wdate">

					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						内容：
					</label>
					<div class="formControls col-10">
						<s:textarea name="pnotice.article"
							cssStyle="width:95%;height:300px;"></s:textarea>
					</div>
				</div>

				<div class="row cl">
					<div class="col-10 col-offset-2">
						<s:token></s:token>
						<input type="submit" class="btn btn-success radius" id="button"
							value="保存并提交"></input>
						<button onclick="childPage_close();"
							class="btn btn-default radius" type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>