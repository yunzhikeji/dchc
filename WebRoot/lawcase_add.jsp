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
		<title>新增涉案情况</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
	</head>

	<body>
		<form name="lawcaseAddForm" action="personAction!addLawcase" method="post"
			onsubmit="">
			<input type="hidden" name="lawcase.person.id" value="${id}"/>
			<div class="pd-20">
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							案件编号：
						</label>
					</div>
					<div class="col-4">
							<input type="text" id="caseNumber" class="input-text radius size-M"
							style="width:200px;" name="lawcase.caseNumber" value=""/>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							案件性质：
						</label>
					</div>
					<div class="col-4">
					<s:select list="#{1:'案件性质1',2:'案件性质2'}"
									cssClass="input-text" name="lawcase.caseType" listKey="key"
							listValue="value" cssStyle="width:200px"></s:select>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							案件名称：
						</label>
					</div>
					<div class="col-4">
						<s:textfield id="caseName" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="lawcase.caseName"></s:textfield>
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							填报人：
						</label>
					</div>
					<div class="col-4">
					<input type="text" id="fillName" class="input-text radius size-M"
							style="width:200px;" name="lawcase.fillName" value="${person.userRole.realname}"/>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							填报时间：
						</label>
					</div>
					<div class="col-4">
						<input type="text" name="lawcase.fillTime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
							id="fillTime" class="input-text Wdate" style="width: 200px;" value="${person.joinDate}">
					</div>
					<div class="col-2">
						<label class="form-label text-r">
							填报单位：
						</label>
					</div>
					<div class="col-4">
		<input type="text" id="fillUnit" class="input-text radius size-M"
							style="width:200px;" name="lawcase.fillUnit" value="${person.userRole.unit.name}"/>
					</div>
				</div>
				<div class="row cl mb-10">
					<div class="col-2">
						<label class="form-label text-r">
							简要案情：
						</label>
					</div>
					<div class="col-4"></div>
					<div class="col-10">
							<s:textarea name="lawcase.briefCase" cssClass="input-text radius size-M "
							cssStyle="width: 95%; height: 80px;"></s:textarea>
					</div>
				</div>
				<div class="row cl">
				    <s:token></s:token>
			    <div class="col-10 col-offset-2">
			      <input type="submit"  class="btn btn-primary radius" value="保存并提交"> </input>
			      <button onclick="childPage_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			    </div>
			</div>
	</body>
</html>
