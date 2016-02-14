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
		<title>发布成功案例</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>

	</head>

	<body>
		<div class="pd-20">
			<form name="successexampleAddForm" action="successexampleAction!update" method="post"
				class="form form-horizontal" id="form-article-add"  onsubmit="">
				<div class="row cl">
					<div class="row cl">
						<label class="form-label col-2">
							<span class="c-red">*</span>标题：
						</label>
						<div class="formControls col-3">
						<s:textfield id="title" name="successexample.title"
							cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
						</div>
						<label class="form-label col-2">
							流水编号：
						</label>
					<div class="formControls col-3">
						<s:textfield id="title" name="successexample.number"
							cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
							</div>
					</div>
					<div class="row cl">
						<label class="form-label col-2">
							<span class="c-red">*</span>发布人 ：
						</label>
						<div class="formControls col-3">
							<s:textfield id="author" name="successexample.releaseName" 
							cssClass="input-text radius size-M " cssStyle="width: 200px;"></s:textfield>
						</div>
						<label class="form-label col-2">
							上报时间：
						</label>
						<div class="formControls col-3">
							<input type="text" name="successexample.releaseTime"  style="width: 200px;" value="${successexample.releaseTime}"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"
							id="releaseTime" class="input-text Wdate">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-2">
							发布内容：
						</label>
						<span class="form-label col-9">
						<span class="formControls col-10">
									<s:textarea name="successexample.content"
									cssClass="input-text" id="content" 
									cssStyle="width: 113%; height: 120px; float: left;" ></s:textarea>
						</span> </span>
					</div>
					
					<s:hidden name="successexample.id"></s:hidden>
					<s:hidden name="successexample.perid"></s:hidden>
					<s:hidden name="successexample.clid"></s:hidden>
					<s:hidden name="successexample.inid"></s:hidden>
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
