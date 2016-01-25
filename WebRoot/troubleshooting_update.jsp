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
		<title>编辑疑难解答</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
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
			<form name="troubleshootingUpdateForm" class="form form-horizontal"
				action="personAction!updateTroubleshooting" method="post" onsubmit="">
				<s:hidden name="troubleshooting.id"></s:hidden>
				<s:hidden name="troubleshooting.person.id"></s:hidden>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>标题：
					</label>
					<span class="form-label col-6"><span
						class="formControls col-10"> <s:textfield id="caseName"
								cssClass="input-text" name="troubleshooting.title"></s:textfield>
					</span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>发布人：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield id="issuer"
								cssClass="input-text" name="troubleshooting.issuer"></s:textfield>
					</span> </span>
					<label class="form-label col-2">
						发布时间：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <input type="text"
								name="troubleshooting.releaseTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
								id="troubleshooting" class="input-text Wdate"
								style="width: 200px;" value="${troubleshooting.releaseTime}"> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						疑难问题：
					</label>
					<span class="form-label col-9"> <span
						class="formControls col-10"> <s:textfield id="question"
								cssClass="input-text radius size-M"
								cssStyle="width: 420px; float: left;"
								name="troubleshooting.question">
							</s:textfield> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						疑难解答：
					</label>
					<span class="form-label col-9"><span
						class="formControls col-10"> <s:textarea
								name="troubleshooting.answer" cssClass="input-text" id="input"
								cssStyle="width:420px; height:120px; float:left;"
								placeholder="请填写解答"></s:textarea> </span> </span>

				</div>
				<div class="row cl">
					<s:token></s:token>
					<div class="col-10 col-offset-2">
						<input type="submit" class="btn btn-primary radius" value="保存并提交">
						</input>
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
